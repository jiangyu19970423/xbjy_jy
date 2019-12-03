package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.entity.Menu;
import com.sys.service.impl.MenuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/11/29/16:41
 * @Description: 菜单控制器
 */
@WebServlet("/sys/menu")
public class MenuServlet extends HttpServlet {
    private MenuServiceImpl service = new MenuServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Menu> list = service.listAll();
        //1级菜单
        List<Menu> listParent = new ArrayList<>();
        //2级菜单
        List<Menu> listSon = new ArrayList<>();

        listParent = list.stream().filter(menu -> {
            return menu.getType().equals("1");
        }).collect(Collectors.toList());

        listSon = list.stream().filter(menu -> {
            return menu.getType().equals("2");
        }).collect(Collectors.toList());

        Map<String, List<Menu>> map = new HashMap<>();
        map.put("parent", listParent);
        map.put("son", listSon);
        String str = JSON.toJSONString(map);
        PrintWriter out = resp.getWriter();
        out.append(str);

    }
}
