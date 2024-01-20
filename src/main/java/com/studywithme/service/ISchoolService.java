package com.studywithme.service;

import java.util.List;

import com.studywithme.model.School;
import com.studywithme.model.User;

public interface ISchoolService {
	public School save(School school);
	public List<School> findAll();
	public School findByName(String schoolName);
	public boolean createSchool(String nameSchool, User creator);
	public boolean deleteSchool(String id);
}
