package com.studywithme.test;

import java.sql.Time;
import java.time.LocalTime;

public class Test4 {
    public static void main(String[] args) {
        Time time = Time.valueOf(LocalTime.now());
        System.out.println(time);
    }
}
