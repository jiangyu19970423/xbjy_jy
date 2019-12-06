package com.sys.controller;

import com.sys.constant.SysConstant;
import com.sys.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/02/10:57
 * @Description: 父类反射以便于多个子类继承调用方法
 */
public class BaseServlet extends HttpServlet {

    //登陆信息
    private User loginUser = new User();

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取出session中的登陆信息
        HttpSession session = req.getSession();
        loginUser = (User)session.getAttribute(SysConstant.SESSION_LOGIN_NAME);
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
