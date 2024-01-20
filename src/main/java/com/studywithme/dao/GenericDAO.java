package com.studywithme.dao;

import java.util.List;

public interface GenericDAO<T> {

	public <T> List<T> query(String hql, Object... parameters);
	
	public T update (T t);
	
	public T insert (T t);
	
	public boolean delete(T t);
	
	public int count (String hql, Object... parameters);

	public T findId(Class<T> clazz ,Integer id);

}
