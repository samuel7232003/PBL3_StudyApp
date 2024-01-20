package com.studywithme.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {
    public static String Format(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
