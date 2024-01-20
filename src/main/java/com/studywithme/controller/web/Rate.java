package com.studywithme.controller.web;

import com.studywithme.model.User;
import com.studywithme.paging.PageRequest;
import com.studywithme.paging.Pageable;
import com.studywithme.service.impl.AppointmentService;
import com.studywithme.service.impl.FriendshipService;
import com.studywithme.service.impl.RateService;
import com.studywithme.sort.Sorter;
import com.studywithme.util.FormUtil;
import com.studywithme.util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/rate")
public class Rate extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) SessionUtil.getInstance().getValue(request, "user");

        Pageable pageble = new PageRequest();
        pageble = FormUtil.toModel(PageRequest.class, request);
        pageble.setSorter(FormUtil.toModel(Sorter.class, request));
        request.setAttribute("pageable",pageble);
        String listFriend = request.getParameter("listFriend");
        int totalPages = (int) Math.ceil((double) AppointmentService.getInstance().totalFindAllAppointmentByRate(user) / pageble.getMaxPageItem());
        request.setAttribute("totalPages",totalPages==1?0:totalPages);
        request.setAttribute("appointments", AppointmentService.getInstance().findAllAppointmentByRate(user, pageble));
        request.setAttribute("appointmentJoined", AppointmentService.getInstance().findByParticipantCurrent(user));
        request.setAttribute("listFriend", FriendshipService.getInstance().listFriend(listFriend,user));
        request.setAttribute("requestFriend", FriendshipService.getInstance().getRequest(user));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/web/rate.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) SessionUtil.getInstance().getValue(request, "user");
        String idAppointment = request.getParameter("idAppointment");
        String rate = request.getParameter("rate");
        RateService.getInstance().createRate(idAppointment, rate, user);
        response.sendRedirect("/PBL3/rate?page=1&maxPageItem=7&sortName=createdDate&sortBy=desc");
    }
}
