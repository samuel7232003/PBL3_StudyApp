package com.studywithme.controller.admin;

import com.studywithme.paging.PageRequest;
import com.studywithme.paging.Pageable;
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


@WebServlet("/admin-list-admin")
public class ListAdmin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pageable pageble = new PageRequest();
        pageble = FormUtil.toModel(PageRequest.class, request);
        pageble.setSorter(FormUtil.toModel(Sorter.class, request));

        int totalPages = (int) Math.ceil((double) UserService.getInstance().countFindAllAdmin()/ pageble.getMaxPageItem());
        request.setAttribute("totalPages",totalPages==1?0:totalPages);
        request.setAttribute("pageable", pageble);
        request.setAttribute("admins", UserService.getInstance().findAllAdmin(pageble));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/admin/admin_list_admin.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pageable pageble = new PageRequest();
        pageble = FormUtil.toModel(PageRequest.class, request);
        pageble.setSorter(FormUtil.toModel(Sorter.class, request));
        String action = request.getParameter("action");
        if (action.equals("unSet")) {
            String profileUserId = request.getParameter("profileUserId");
            UserService.getInstance().setRoleUser(profileUserId);
            response.sendRedirect("/PBL3/admin-list-admin?page="+pageble.getPage()+"&maxPageItem="+pageble.getMaxPageItem()+"&sortName="+pageble.getSorter().getSortName()+"&sortBy="+pageble.getSorter().getSortBy());
        }
    }
}