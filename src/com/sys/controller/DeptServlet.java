package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.entity.Dept;
import com.sys.service.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/02/11:07
 * @Description:
 */
@WebServlet("/sys/dept/*")
public class DeptServlet extends BaseServlet {
    private DeptServiceImpl service = new DeptServiceImpl();

    public void listAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Dept> depts = service.listAll();
        String string = JSON.toJSONString(depts);
        PrintWriter out = resp.getWriter();
        out.append(string);
    }
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Dept> dept = service.listAll();
        req.setAttribute("dept",dept);
        req.getRequestDispatcher("/view/sys/dept/list.jsp").forward(req,resp);

    }


}
