package com.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sys.constant.SysConstant;
import com.sys.entity.User;

import javax.mail.Session;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/11/29/17:22
 * @Description:
 */
@WebFilter("/*")
public class SysFilter implements javax.servlet.Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        //获取请求路径
        String uri = request.getRequestURI();
        //拦截首页登录：index.jsp
        //如果cookie中有值，则直接跳转到home.jsp界面（实现7天免登陆方案），否则跳转index.jsp
        if (uri.endsWith("index.jsp")) {
            //获取cookie所有的值
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    //如果cookie中有登录信息，则直接跳转到home.jsp界面
                    if ("cookieLoginUser".equals(cookies[i].getName())) {
                        //获取cookie中的值，并解码，得到JSON字符串
                        String strJson = URLDecoder.decode(cookies[i].getValue(), "utf-8");
                        //把JSON字符串转化为java对象
                        User loginUser = JSON.parseObject(strJson, new TypeReference<User>(){});
                        //把从cookie中取出的登录信息存入到session中
                        session.setAttribute(SysConstant.SESSION_LOGIN_NAME,loginUser);
                        //登录成功跳转到home.jsp界面
                        response.sendRedirect("/view/common_test/home.jsp");
                    }
                }
            }

        } else if (uri.endsWith("sys/login/login") || uri.endsWith("sys/login/getPic") || uri.endsWith("forget.jsp")) {
            //直接放行登录验证请求，图片验证码请求,忘记密码界面否则是非法登录
        } else {
            Object obj = session.getAttribute(SysConstant.SESSION_LOGIN_NAME);

            //非法登录
            if (obj == null) {
                response.sendRedirect("/index.jsp");
            }
        }

        filterChain.doFilter(request, response);
    }
}
