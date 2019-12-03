package com.sys.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.SortedMap;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/02/10:57
 * @Description: 父类反射以便于多个子类继承调用方法
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getRequestURI();
        String[] split = method.split("/");
        method = split[split.length - 1];
        //使用this可以多个子类Servlet都使用
        Class clazz = this.getClass();

        try {
            Method method1 = clazz.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //暴力反射
            method1.setAccessible(true);
            method1.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
