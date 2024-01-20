package com.studywithme.controller.admin;

import com.studywithme.model.School;
import com.studywithme.model.User;
import com.studywithme.paging.PageRequest;
import com.studywithme.paging.Pageable;
import com.studywithme.service.impl.RoleService;
import com.studywithme.service.impl.SchoolService;
import com.studywithme.service.impl.UserService;
import com.studywithme.sort.Sorter;
import com.studywithme.util.FormUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin-home")
public class AdminHome extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pageable pageble = new PageRequest();
        pageble = FormUtil.toModel(PageRequest.class, request);
        pageble.setSorter(FormUtil.toModel(Sorter.class, request));
        String error = request.getParameter("error");
        if (error != null) {
            if (error.equals("errorCreate")) {
                request.setAttribute("errorCreate","Email đã tồn tại");
            }
            try {
                Integer.parseInt(error);
                request.setAttribute("errorEdit","Email đã tồn tại");
                request.setAttribute("idUser",error);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        int totalPages = (int) Math.ceil((double) UserService.getInstance().countFindAllUser()/ pageble.getMaxPageItem());
        request.setAttribute("totalPages",totalPages==1?0:totalPages);
        request.setAttribute("pageable", pageble);
        request.setAttribute("roles", RoleService.getInstance().findAll());
        request.setAttribute("users", UserService.getInstance().findAllUser(pageble));
        request.setAttribute("listSchool", SchoolService.getInstance().findAll());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/admin/admin_home.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        Pageable pageble = new PageRequest();
        pageble = FormUtil.toModel(PageRequest.class, request);
        pageble.setSorter(FormUtil.toModel(Sorter.class, request));
        if (action.equals("editProfile")) {
            String profileUserId = request.getParameter("profileUserId");
            String idRole = request.getParameter("role");
            String lastName = request.getParameter("lastName");
            String firstName = request.getParameter("firstName");
            String gender = request.getParameter("gender");
            String idSchool = request.getParameter("idSchool");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (!UserService.getInstance().editUser(profileUserId,idRole,lastName,firstName,gender,idSchool,dateOfBirth,email,password)){
                String url = "/PBL3/admin-home?page="+pageble.getPage()+"&maxPageItem="+pageble.getMaxPageItem()+"&sortName="+pageble.getSorter().getSortName()+"&sortBy="+pageble.getSorter().getSortBy()+"&error="+profileUserId;
                response.sendRedirect(url);
            } else{
                response.sendRedirect("/PBL3/admin-home?page="+pageble.getPage()+"&maxPageItem="+pageble.getMaxPageItem()+"&sortName="+pageble.getSorter().getSortName()+"&sortBy="+pageble.getSorter().getSortBy());
            }
        } else if (action.equals("createUser")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String gender = request.getParameter("gender");
            String idSchool = request.getParameter("idSchool");
            String dateOfBirth = request.getParameter("dateOfBirth");
            if (UserService.getInstance().createUser(email,password,firstName,lastName,gender,idSchool,dateOfBirth)) {
                response.sendRedirect("/PBL3/admin-home?page="+pageble.getPage()+"&maxPageItem="+pageble.getMaxPageItem()+"&sortName="+pageble.getSorter().getSortName()+"&sortBy="+pageble.getSorter().getSortBy());
            } else {
                String url = "/PBL3/admin-home?page="+pageble.getPage()+"&maxPageItem="+pageble.getMaxPageItem()+"&sortName="+pageble.getSorter().getSortName()+"&sortBy="+pageble.getSorter().getSortBy()+"&error="+"errorCreate";
                response.sendRedirect(url);
            }
        } else if (action.equals("lockUser")){
            String profileUserId = request.getParameter("profileUserId");
            UserService.getInstance().lockUser(profileUserId);
            response.sendRedirect("/PBL3/admin-home?page="+pageble.getPage()+"&maxPageItem="+pageble.getMaxPageItem()+"&sortName="+pageble.getSorter().getSortName()+"&sortBy="+pageble.getSorter().getSortBy());
        } else if (action.equals("deleteUser")){
            String profileUserId = request.getParameter("profileUserId");
            UserService.getInstance().deleteUser(profileUserId);
            response.sendRedirect("/PBL3/admin-home?page="+pageble.getPage()+"&maxPageItem="+pageble.getMaxPageItem()+"&sortName="+pageble.getSorter().getSortName()+"&sortBy="+pageble.getSorter().getSortBy());
        }
    }
}
