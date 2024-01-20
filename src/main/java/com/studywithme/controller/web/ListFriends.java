package com.studywithme.controller.web;

import com.studywithme.model.User;
import com.studywithme.paging.PageRequest;
import com.studywithme.paging.Pageable;
import com.studywithme.service.IAppointmentService;
import com.studywithme.service.IFriendshipService;
import com.studywithme.service.impl.AppointmentService;
import com.studywithme.service.impl.FriendshipService;
import com.studywithme.sort.Sorter;
import com.studywithme.util.FormUtil;
import com.studywithme.util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/list-friends")
public class ListFriends extends HttpServlet {
    public ListFriends() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) SessionUtil.getInstance().getValue(request, "user");

        Pageable pageble = new PageRequest();
        pageble = FormUtil.toModel(PageRequest.class, request);
        pageble.setSorter(FormUtil.toModel(Sorter.class, request));
        int totalFriend = FriendshipService.getInstance().totalFriend(user);
        int totalPages = (int) Math.ceil((double) totalFriend / pageble.getMaxPageItem());

        request.setAttribute("totalFriend", totalFriend);
        request.setAttribute("totalPages",totalPages==1?0:totalPages);
        request.setAttribute("pageable", pageble);
        request.setAttribute("listFriend", FriendshipService.getInstance().pagingFriend(pageble, user));
        request.setAttribute("requestFriend", FriendshipService.getInstance().getRequest(user));
        request.setAttribute("appointmentJoined", AppointmentService.getInstance().findByParticipantCurrent(user));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/web/list-friends.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
