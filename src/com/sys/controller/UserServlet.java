package com.sys.controller;

import com.Utils.MDUtil;
import com.sys.constant.SysConstant;
import com.sys.entity.Page;
import com.sys.entity.User;
import com.sys.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/02/10:57
 * @Description:
 */
@WebServlet("/sys/user/*")
public class UserServlet extends BaseServlet {
    private UserServiceImpl service = new UserServiceImpl();

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询条件
        String account = req.getParameter("account");
        account = account == null ? "" : account;
        //当前页
        String pageStr = req.getParameter("page");
        //查询总记录数
        Integer count = service.getCount(account);

        Page page = new Page();
        page.setCount(count);
        Integer pageCurrent = pageStr == null ? 1 : Integer.valueOf(pageStr);
        page.setPageCurrent(pageCurrent);
        List<User> list = service.listAll(account, page);
        //查询的数据
        req.setAttribute("list", list);
        //查询的条件
        req.setAttribute("account", account);
        //分页信息
        req.setAttribute("page", page);
        req.getRequestDispatcher("/view/sys/user/list.jsp").forward(req, resp);

    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException {
        User user = new User();
        //获取请求体里面的数据
        Map<String, String[]> map = req.getParameterMap();
        BeanUtils.populate(user, map);
        service.addUser(user);
        resp.sendRedirect("/sys/user/list");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        if (id == null) {
            return;
        }
        service.deleteById(Integer.valueOf(id));
        response.sendRedirect("/sys/user/list");
    }

    public void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null) {
            return;
        }
        User user = service.getById(Integer.valueOf(id));
        request.setAttribute("user", user);
        request.getRequestDispatcher("/view/sys/user/userUpdate.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {
//        String id = request.getParameter("id");
//        String deptId = request.getParameter("deptId");
//        String account = request.getParameter("account");
//        String password = request.getParameter("password");
//        String name = request.getParameter("name");
//        String age = request.getParameter("age");
//        String sex = request.getParameter("sex");
//        String email = request.getParameter("email");
//        String birthDate = request.getParameter("birthDate");
//        String createTime = request.getParameter("createTime");

        User user = new User();
        Map<String, String[]> map = request.getParameterMap();
        BeanUtils.populate(user, map);
        service.updateUser(user);
//        user.setId(Integer.valueOf(id));
//        user.setDeptId(Integer.valueOf(deptId));
//        user.setAccount(account);
//        user.setPassword(password);
//        user.setName(name);
//        user.setAge(Integer.valueOf(age));
//        user.setSex(Integer.valueOf(sex));
//        user.setEmail(email);
//        user.setBirthDate(birthDate);
//        user.setCreateTime(createTime);

        response.sendRedirect("/sys/user/list");

    }

    public void forgetPassWord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        //前端输入的验证码
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        Object object = session.getAttribute(SysConstant.SESSION_EMAIL_CODE_NAME);
        //比较前端code和session中的code
        if (object == null || !object.equals(code)) {
            response.sendRedirect("/view/sys/user/forget.jsp");
            return;
        }
        User user = new User();
        user.setAccount(account);
        user.setPassword(MDUtil.md5(password));
        service.updatePassWord(user);
        //密码修改完毕，跳转到登录界面
        response.sendRedirect("/index.jsp");

    }

}
