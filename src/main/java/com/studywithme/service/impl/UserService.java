package com.studywithme.service.impl;


import java.io.*;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.studywithme.dao.impl.RoleDAO;
import com.studywithme.dao.impl.UserDAO;
import com.studywithme.model.Role;
import com.studywithme.model.School;
import com.studywithme.model.User;
import com.studywithme.paging.Pageable;
import com.studywithme.service.IUserService;
import com.studywithme.util.EncodeUtil;

import javax.servlet.http.Part;

public class UserService implements IUserService {
	private static IUserService userService;
	public static IUserService getInstance() {
		if(userService == null) {
			userService = new UserService();
		}
		return userService;
	}
	ResourceBundle resourceBundle = ResourceBundle.getBundle("url");
	@Override
	public User findByEmailAndPassword(String email, String password) {
		password = EncodeUtil.toSHA1(password);
		return UserDAO.getInstance().findByEmailAndPasswordAndStatus(email, password);
	}

	@Override
	public User register(String lastName, String firstName, String email, String password, Integer gender) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(EncodeUtil.toSHA1(password));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setFullName(firstName+" "+lastName);
		user.setGender(gender);
		user.setStatus(0);
		user.setRole(RoleDAO.getInstance().findByCode("USER"));
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		String path = resourceBundle.getString("url");
		try {
			File avatar = new File(path + File.separator + "avatarDefault.png");
			byte[] dataAvatar = Files.readAllBytes(avatar.toPath());
			String base64 = Base64.getEncoder().encodeToString(dataAvatar);
			user.setAvatar(base64);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			File avatar = new File(path + File.separator + "backgroundDefault.png");
			byte[] dataBackground = Files.readAllBytes(avatar.toPath());
			String base64 = Base64.getEncoder().encodeToString(dataBackground);
			user.setBackground(base64);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return UserDAO.getInstance().save(user);
	}

	@Override
	public User findByEmail(String email) {
		return UserDAO.getInstance().findByEmail(email);
	}

	@Override
	public User findById(Integer id) {
		return UserDAO.getInstance().findById(id);
	}

	@Override
	public User update(User user) {
		return UserDAO.getInstance().update(user);
	}

	@Override
	public User updateImg(User user, Part filePart, String img) {
		InputStream fileContent = null;
		try {
			fileContent = filePart.getInputStream();
			final byte[] bytes = new byte[fileContent.available()];
			fileContent.read(bytes);
			if(img.equals("avatar")){
				String base64 = Base64.getEncoder().encodeToString(bytes);
				user.setAvatar(base64);
			} else if (img.equals("background")) {
				String base64 = Base64.getEncoder().encodeToString(bytes);
				user.setBackground(base64);
			}
			fileContent.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return UserDAO.getInstance().update(user);
	}

	@Override
	public List<User> findAllUser(Pageable pageable) {
		return UserDAO.getInstance().findAllUser(pageable);
	}

	@Override
	public Integer countFindAllUser() {
		return UserDAO.getInstance().countFindAllUser();
	}

	@Override
	public List<User> findAllAdmin(Pageable pageable) {
		return UserDAO.getInstance().findAllAdmin(pageable);
	}

	@Override
	public Integer countFindAllAdmin() {
		return UserDAO.getInstance().countFindAllAdmin();
	}

	@Override
	public boolean editUser(String profileUserId, String idRole, String lastName, String firstName, String gender, String idSchool, String dateOfBirth, String email, String password) {
		User user = UserDAO.getInstance().findOne(Integer.parseInt(profileUserId));
		if (!email.equals("")) {
			User temp = UserDAO.getInstance().findByEmail(email);
			if (temp != null) {
				return false;
			} else {
				user.setEmail(email);
			}
		}
		if (!idRole.equals("")) {
			user.setRole(new Role(Integer.parseInt(idRole)));
		}
		if (!lastName.equals("")) {
			user.setLastName(lastName);
		}
		if (!firstName.equals("")) {
			user.setFirstName(firstName);
		}
		user.setFullName(user.getFirstName()+ " " +user.getLastName());
		if (!gender.equals("")) {
			if (gender.equals("male")) {
				user.setGender(0);
			} else if (gender.equals("female")) {
				user.setGender(1);
			} else if (gender.equals("other")) {
				user.setGender(2);
			}
		}
		if (!idSchool.equals("")) {
			try {
				user.setSchool(new School(Integer.parseInt(idSchool)));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		if (!dateOfBirth.equals("")){
			try {
				java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
				user.setDateOfBirth(new Date(date.getTime()));
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		if (!password.equals("")) {
			user.setPassword(EncodeUtil.toSHA1(password));
		}
		UserDAO.getInstance().update(user);
		return true;
	}

	@Override
	public boolean createUser(String email, String password,String firstName, String lastName, String gender, String idSchool, String dateOfBirth){
		User user = UserDAO.getInstance().findByEmail(email);
		if (user != null) {
			return false;
		}
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setPassword(EncodeUtil.toSHA1(password));
		if (gender.equals("male")) {
			newUser.setGender(0);
		} else if (gender.equals("female")) {
			newUser.setGender(1);
		} else {
			newUser.setGender(2);
		}
		try {
			newUser.setSchool(new School(Integer.parseInt(idSchool)));
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
			newUser.setDateOfBirth(new Date(date.getTime()));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setFullName(firstName + " " + lastName);
		newUser.setStatus(0);
		String path = resourceBundle.getString("url");
		try {
			File avatar = new File(path + File.separator + "avatarDefault.png");
			byte[] dataAvatar = Files.readAllBytes(avatar.toPath());
			String base64 = Base64.getEncoder().encodeToString(dataAvatar);
			newUser.setAvatar(base64);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			File avatar = new File(path + File.separator + "backgroundDefault.png");
			byte[] dataBackground = Files.readAllBytes(avatar.toPath());
			String base64 = Base64.getEncoder().encodeToString(dataBackground);
			newUser.setBackground(base64);
		} catch (IOException e) {
			e.printStackTrace();
		}
		newUser.setRole(RoleDAO.getInstance().findByCode("USER"));
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		Date today = new Date(date.getTime());
		newUser.setCreatedDate(today);
		UserDAO.getInstance().insert(newUser);
		return true;
	}
	@Override
	public boolean lockUser(String id) {
		User user = UserDAO.getInstance().findById(Integer.parseInt(id));
		if (user.getStatus() == 0) {
			user.setStatus(1);
		} else {
			user.setStatus(0);
		}
		UserDAO.getInstance().update(user);
		return false;
	}

	@Override
	public boolean deleteUser(String id) {
		return UserDAO.getInstance().deleteUser(UserDAO.getInstance().findOne(Integer.parseInt(id)));
	}

	@Override
	public boolean setRoleUser(String id) {
		User user = UserDAO.getInstance().findOne(Integer.parseInt(id));
		user.setRole(RoleDAO.getInstance().findByCode("USER"));
		UserDAO.getInstance().update(user);
		return true;
	}

}
