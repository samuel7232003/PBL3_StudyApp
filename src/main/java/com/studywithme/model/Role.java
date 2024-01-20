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
public class Role extends AbstractModel{
	private String code;
	private String name;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Set<User> users= new HashSet<>();

	public Role() {
	}

	public Role(Integer id) {
		this.id = id;
	}

	public Role(Integer id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void removeUser(User user) {
		user.setRole(null);
	}
}
