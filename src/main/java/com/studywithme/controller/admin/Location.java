package com.studywithme.controller.admin;

import com.studywithme.model.User;
import com.studywithme.service.impl.DistrictService;
import com.studywithme.service.impl.WardService;
import com.studywithme.util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin-location")
public class Location extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("districts", DistrictService.getInstance().findAll());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/admin/admin_location.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        User user = (User) SessionUtil.getInstance().getValue(request, "user");
        String action = request.getParameter("action");
        if (action.equals("createWard")) {
            String ward = request.getParameter("ward");
            String idDistrict = request.getParameter("idDistrict");
            WardService.getInstance().createWard(ward, idDistrict, user);
        } else if (action.equals("createDistrict")) {
            String district = request.getParameter("district");
            DistrictService.getInstance().createDistrict(district);
        } else if (action.equals("deleteWard")) {
            String idWard = request.getParameter("idWard");
            WardService.getInstance().deleteWard(idWard);
        } else if (action.equals("deleteDistrict")) {
            String idDistrict = request.getParameter("idDistrict");
            DistrictService.getInstance().deleteDistrict(idDistrict);
        }
        response.sendRedirect("/PBL3/admin-location");
    }
}
