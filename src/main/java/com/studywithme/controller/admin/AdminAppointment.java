package com.studywithme.controller.admin;

import com.studywithme.paging.PageRequest;
import com.studywithme.paging.Pageable;
import com.studywithme.service.impl.AppointmentService;
import com.studywithme.sort.Sorter;
import com.studywithme.util.FormUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin-appointment")
public class AdminAppointment extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pageable pageble = new PageRequest();
        pageble = FormUtil.toModel(PageRequest.class, request);
        pageble.setSorter(FormUtil.toModel(Sorter.class, request));

        int totalPages = (int) Math.ceil((double) AppointmentService.getInstance().countAll()/ pageble.getMaxPageItem());
        request.setAttribute("totalPages",totalPages==1?0:totalPages);
        request.setAttribute("pageable",pageble);
        request.setAttribute("appointments", AppointmentService.getInstance().findAllAppointment(pageble));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/admin/admin_appointment.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Pageable pageble = new PageRequest();
        pageble = FormUtil.toModel(PageRequest.class, request);
        pageble.setSorter(FormUtil.toModel(Sorter.class, request));
        if (action.equals("deleteAppointment")) {
            String idAppointment = request.getParameter("idAppointment");
            AppointmentService.getInstance().delete(idAppointment);
            response.sendRedirect("/PBL3/admin-appointment?page="+pageble.getPage()+"&maxPageItem="+pageble.getMaxPageItem()+"&sortName="+pageble.getSorter().getSortName()+"&sortBy="+pageble.getSorter().getSortBy());
        }
    }
}