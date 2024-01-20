package com.studywithme.controller.web;

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.studywithme.model.User;
import com.studywithme.service.*;
import com.studywithme.service.impl.*;
import com.studywithme.util.SessionUtil;


@WebServlet("/edit-profile")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
		maxFileSize=1024*1024*50,      	// 50 MB
		maxRequestSize=1024*1024*100	// 100 MB
)
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EditProfile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) SessionUtil.getInstance().getValue(request, "user");
		String listFriendStr = request.getParameter("listFriend");
		request.setAttribute("listFriend", FriendshipService.getInstance().listFriend(listFriendStr,user));
		request.setAttribute("listSchool", SchoolService.getInstance().findAll());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/web/edit-profile.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}