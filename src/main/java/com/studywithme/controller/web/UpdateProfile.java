package com.studywithme.controller.web;

import com.studywithme.model.School;
import com.studywithme.model.User;
import com.studywithme.service.ISchoolService;
import com.studywithme.service.IUserService;
import com.studywithme.service.impl.SchoolService;
import com.studywithme.service.impl.UserService;
import com.studywithme.util.SessionUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateProfile() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/web/edit-profile.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String school_id = request.getParameter("school_id");
		User user = (User) SessionUtil.getInstance().getValue(request, "user");

		try {
			if(!dateOfBirth.equals("null")) {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
				user.setDateOfBirth(new java.sql.Date(date.getTime()));
			}
		} catch (ParseException e) {
		}

		if(!lastName.equals("null")) {
			user.setLastName(lastName);
		}

		if(!firstName.equals("null")) {
			user.setFirstName(firstName);
		}
		if(!school_id.equals("null")) {
			School school = new School();
			user.setSchool(school);
		}
		UserService.getInstance().update(user);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/web/edit-profile.jsp");
		rd.forward(request, response);
	}

}
