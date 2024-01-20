package com.studywithme.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Test2 {
    @Id
    private Integer id;

    private String detail;
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "test2s")
//    private Set<Test1> test1s = new HashSet<>();
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_test1")
    private Test1 test1;

    public Test2() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

//    public Set<Test1> getTest1s() {
//        return test1s;
//    }
//
//    public void setTest1s(Set<Test1> test1s) {
//        this.test1s = test1s;
//    }


    public Test1 getTest1() {
        return test1;
    }

    public void setTest1(Test1 test1) {
        this.test1 = test1;
    }
}
