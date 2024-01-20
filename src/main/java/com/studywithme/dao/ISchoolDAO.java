package com.studywithme.dao;


import com.studywithme.model.School;

public interface ISchoolDAO extends GenericDAO<School>, InterfaceDAO<School> {
	public School findByName(String schoolName);
	public boolean removeSchool(School school);
}
