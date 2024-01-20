package com.studywithme.service.impl;

import com.studywithme.dao.ISchoolDAO;
import com.studywithme.dao.impl.SchoolDAO;
import com.studywithme.model.School;
import com.studywithme.model.User;
import com.studywithme.service.ISchoolService;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.List;

public class SchoolService implements ISchoolService {
	private static ISchoolService schoolService;
	public static ISchoolService getInstance() {
		if (schoolService == null) {
			schoolService = new SchoolService();
		}
		return schoolService;
	}
	@Override
	public School save(School school) {
		return SchoolDAO.getInstance().insert(school);
	}

	@Override
	public List<School> findAll() {
		return SchoolDAO.getInstance().findAll();
	}

	@Override
	public School findByName(String schoolName) {
		return SchoolDAO.getInstance().findByName(schoolName);
	}

	@Override
	public boolean createSchool(String nameSchool, User creator) {
		School school = SchoolDAO.getInstance().findByName(nameSchool);
		if (school != null) {
			return false;
		}
		school = new School();
		school.setNameSchool(nameSchool);
		school.setCreatedBy(creator);
		Date date = new Date(System.currentTimeMillis());
		java.sql.Date today = new java.sql.Date(date.getTime());
		school.setCreatedDate(today);
		SchoolDAO.getInstance().insert(school);
		return false;
	}

	@Override
	public boolean deleteSchool(String id) {
		return SchoolDAO.getInstance().removeSchool(SchoolDAO.getInstance().findOne(Integer.parseInt(id)));
	}
}
