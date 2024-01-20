package com.studywithme.controller.admin;

import com.studywithme.model.User;
import com.studywithme.service.impl.SchoolService;
import com.studywithme.util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-school")
public class AdminSchool extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = request.getParameter("error");
        if (error != null){
            request.setAttribute("error", "Tên trường đã tồn tại");
        }
        request.setAttribute("schools", SchoolService.getInstance().findAll());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/admin/admin_school.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        User user = (User) SessionUtil.getInstance().getValue(request, "user");
        String action = request.getParameter("action");
        if (action.equals("deleteSchool")) {
            String idSchool = request.getParameter("idSchool");
            SchoolService.getInstance().deleteSchool(idSchool);
            response.sendRedirect("/PBL3/admin-school");
        } else if (action.equals("createSchool")) {
            String nameSchool = request.getParameter("nameSchool");
            if(!SchoolService.getInstance().createSchool(nameSchool, user)) {
                response.sendRedirect("/PBL3/admin-school");
            } else {
                response.sendRedirect("/PBL3/admin-school?error=createSchool");
            }
        }

    }
}