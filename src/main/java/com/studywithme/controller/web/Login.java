package com.studywithme.controller.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.studywithme.model.User;
import com.studywithme.service.IUserService;
import com.studywithme.service.impl.UserService;
import com.studywithme.util.FormatDate;
import com.studywithme.util.SessionUtil;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/web/login.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		request.setAttribute("emailLogin", email);
		String error = "";
		String url ="";
		User u1 = UserService.getInstance().findByEmailAndPassword(email, password);
		if(u1!=null) {
			SessionUtil.getInstance().putValue(request,"user",u1);
			response.sendRedirect("/PBL3/home?page=1&maxPageItem=6&sortName=createdDate&sortBy=desc");
		}else {
			error+="Sai Email hoặc sai mật khẩu";
			request.setAttribute("errorLogin", error);
			url = "/view/web/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}
}
