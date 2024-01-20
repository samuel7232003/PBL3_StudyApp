package com.studywithme.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class School extends AbstractModel{

	private String nameSchool;
	
	@OneToMany(mappedBy = "school", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Set<User> listUser = new HashSet<>();
	
	@OneToMany(mappedBy = "schoolModified", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Modify> modifiedBy = new HashSet<>();

	public School(Integer id, String school) {
		this.id = id;
		this.nameSchool = school;
	}
	public School(String nameSchool) {
		this.nameSchool = nameSchool;
	}
	public School(Integer id){
		this.id = id;
	}

	public School() {
	}


	public String getNameSchool() {
		return nameSchool;
	}

	public void setNameSchool(String nameSchool) {
		this.nameSchool = nameSchool;
	}

	public Set<User> getListUser() {
		return listUser;
	}

	public void setListUser(Set<User> listUser) {
		this.listUser = listUser;
	}

	public Set<Modify> getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Set<Modify> modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void removeUser(User user) {
		user.setSchool(null);
	}
}
