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

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Home() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) SessionUtil.getInstance().getValue(request, "user");

		Pageable pageble = new PageRequest();
		pageble = FormUtil.toModel(PageRequest.class, request);
		pageble.setSorter(FormUtil.toModel(Sorter.class, request));
		int totalPages = (int) Math.ceil((double) AppointmentService.getInstance().totalItem(pageble) / pageble.getMaxPageItem()) ;
		String listFriend = request.getParameter("listFriend");
		request.setAttribute("pageable",pageble);
		request.setAttribute("totalPages",totalPages==1?0:totalPages);
		request.setAttribute("appointmentJoined", AppointmentService.getInstance().findByParticipantCurrent(user));
		request.setAttribute("listFriend", FriendshipService.getInstance().listFriend(listFriend,user));
		request.setAttribute("requestFriend", FriendshipService.getInstance().getRequest(user));
		request.setAttribute("appointments", AppointmentService.getInstance().pagingAppointment(pageble));
		request.setAttribute("appointmentOf", AppointmentService.getInstance().findByHostCurrent(user));
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/web/home.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Pageable pageble = new PageRequest();
		pageble = FormUtil.toModel(PageRequest.class, request);
		pageble.setSorter(FormUtil.toModel(Sorter.class, request));
		if (action.equals("join")) {
			String idAppointment = request.getParameter("idAppointment");
			User user = (User) SessionUtil.getInstance().getValue(request, "user");
			AppointmentService.getInstance().joinAppointment(user, idAppointment);
		} else if (action.equals("leave")) {
			String idAppointment = request.getParameter("idAppointment");
			User user = (User) SessionUtil.getInstance().getValue(request, "user");
			AppointmentService.getInstance().leaveAppointment(user, idAppointment);
		}
		StringBuilder url = new StringBuilder("/PBL3/home?");
		url.append("page=" + pageble.getPage());
		url.append("&maxPageItem=" + pageble.getMaxPageItem());
		url.append("&sortName=" + pageble.getSorter().getSortName());
		url.append("&sortBy=" + pageble.getSorter().getSortBy());
		if (pageble.getSorter().getDateMeeting() != null) {
			url.append("&dateMeeting=" + pageble.getSorter().getDateMeeting());
		}
		response.sendRedirect(url.toString());
	}
}
