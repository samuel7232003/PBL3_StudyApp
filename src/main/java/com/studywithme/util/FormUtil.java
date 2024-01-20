package com.studywithme.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtil {
    public static <T> T toModel (Class<T> clazz, HttpServletRequest request){
        T object = null;
        try {
            object = clazz.newInstance();
            BeanUtils.populate(object,request.getParameterMap());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
}
