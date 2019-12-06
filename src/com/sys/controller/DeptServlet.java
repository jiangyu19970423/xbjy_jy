package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.entity.Dept;
import com.sys.entity.Page;
import com.sys.entity.User;
import com.sys.service.impl.DeptServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

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
        //查询条件
        String name = req.getParameter("name");
        name = name == null ? "" : name;
        //当前页
        String pageStr = req.getParameter("page");
        //查询总记录数
        Integer count = service.getCount(name);
        Dept dept1 = new Dept();

        Page page = new Page();
        page.setCount(count);
        Integer pageCurrent = pageStr == null ? 1 : Integer.valueOf(pageStr);
        page.setPageCurrent(pageCurrent);
        List<Dept> dept = service.listAll(name, page);
        req.setAttribute("dept", dept);
        req.setAttribute("name", name);
        req.setAttribute("page", page);

        req.getRequestDispatcher("/view/sys/dept/list.jsp").forward(req, resp);

    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException {
        Dept dept = new Dept();
        //获取请求体里面的数据
        Map<String, String[]> map = req.getParameterMap();
        BeanUtils.populate(dept, map);
        dept.setCreateBy(super.getLoginUser().getId());
        service.addDept(dept);
        resp.sendRedirect("/sys/dept/list");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        if (id == null) {
            return;
        }
        Integer count = service.ListUser(Integer.valueOf(id));
        if (count == 0) {
            service.deleteById(Integer.valueOf(id));
            response.sendRedirect("/sys/dept/list");
        } else {
            response.sendRedirect("/sys/dept/list");
        }
    }


}
