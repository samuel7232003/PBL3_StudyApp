package com.studywithme.dao;

import java.util.List;

public interface InterfaceDAO <T> {
    public List<T> findAll();
    public T findOne(Integer id);
    public T save(T t);
    public boolean deleteId(Integer id);
}
