package com.studywithme.controller.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.studywithme.model.User;
import com.studywithme.service.IModifyService;
import com.studywithme.service.IUserService;
import com.studywithme.service.impl.ModifyService;
import com.studywithme.service.impl.UserService;
import com.studywithme.util.SessionUtil;


@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
				maxFileSize=1024*1024*50,      	// 50 MB
				maxRequestSize=1024*1024*100	// 100 MB
)
@WebServlet("/upload-image")
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UploadImage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/web/edit-profile.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Part file = request.getPart("avatar");
		Part file1 = request.getPart("background");
		User user = (User) SessionUtil.getInstance().getValue(request, "user");


//		try {
//				InputStream is = file1.getInputStream();
//				byte[] data = new byte[is.available()];
//				is.read(data);
//				System.out.println(is);
//				is.close();
//				String base64=Base64.getEncoder().encodeToString(data);
//				if(!base64.equals("")) {
//					user.setBackground(base64);
//				}
//				modifyService.createModify(user,user,"Sửa đổi ảnh đại diện");
//				userService.update(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//				InputStream is = file.getInputStream();
//				byte[] data = new byte[is.available()];
//				System.out.println(is);
//				is.read(data);
//				is.close();
//				String base64 = Base64.getEncoder().encodeToString(data);
//				if(!base64.equals("")) {
//					user.setAvatar(base64);
//				}
//				modifyService.createModify(user,user,"Sửa đổi ảnh nền");
//				userService.update(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/web/edit-profile.jsp");
		rd.forward(request, response);
	}
}
