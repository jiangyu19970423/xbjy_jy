package com.sys.controller;

import com.Utils.ImgCodeUtil;
import com.Utils.MDUtil;
import com.alibaba.fastjson.JSON;
import com.sys.constant.SysConstant;
import com.sys.entity.User;
import com.sys.service.impl.UserServiceImpl;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/04/16:23
 * @Description:
 */
@WebServlet("/sys/login/*")
public class LoginServlet extends BaseServlet {
    private UserServiceImpl service = new UserServiceImpl();


    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String picCode = req.getParameter("picCode");
        String remember = req.getParameter("remember");

        HttpSession session = req.getSession();
        Object obj = session.getAttribute(SysConstant.SESSION_PIC_CODE_NAME);
        if (obj == null || !obj.toString().equals(picCode)) {
            //验证码不正确
            resp.sendRedirect("/index.jsp");
            return;
        }
        //从数据库里设置账号密码
        User user = new User();
        user.setAccount(account);
        //密文
        user.setPassword(MDUtil.md5(password));
        //验证账号密码是否正确
        List<User> list = service.checkLogin(user);
        //账号或密码不正确或系统存在相同的账号和密码
        if (list == null || list.size() == 0 || list.size() > 1) {
            //登录失败，跳转到登录界面
            resp.sendRedirect("/index.jsp");
            return;
        } else {
            User loginUser = list.get(0);
            //验证通过，成功路径，并且把登录信息存入session中，可以存对象loginUser
            session.setAttribute(SysConstant.SESSION_LOGIN_NAME, loginUser);
            //上下文设置在线人数
            ServletContext application = getServletContext();
            //把在线人数取出来
            Object countObj = application.getAttribute(SysConstant.APPLICATION_LOGIN_COUNT);
            int count = 1;
            if (countObj != null) {
                count = Integer.valueOf(countObj.toString()) + 1;
            }
            //把新的在线人数存入上下文中去
            System.out.println(count);
            application.setAttribute(SysConstant.APPLICATION_LOGIN_COUNT, count);

            //如果勾选了7天内免登陆，则要把登录信息存入cookie，cookie不能存对象，只能存字符串
            if ("1".equals(remember)) {
                //cookie中需要存入未加密前的密码
                loginUser.setPassword(password);
                //cookie不能存特殊字符
                //先编码再解码
                String strJson = JSON.toJSONString(loginUser);
                strJson = URLEncoder.encode(strJson, "utf-8");
                Cookie cookieLoginUser = new Cookie(SysConstant.COOKIE_LOGIN_USER, strJson);
                //有效期为7天,单位是秒
                cookieLoginUser.setMaxAge(7 * 24 * 60 * 60);
                //任何请求都能获取cookie
                cookieLoginUser.setPath("/");
                resp.addCookie(cookieLoginUser);

            }

            req.getRequestDispatcher("/view/common_test/home.jsp").forward(req, resp);
            return;
        }
    }

    public void getPic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取验证码的图片
        ImgCodeUtil imgCodeUtil = new ImgCodeUtil();
        BufferedImage image = imgCodeUtil.getImage();

        //获取验证码文本内容
        String code = imgCodeUtil.getText();

        HttpSession session = req.getSession();
        //把图片验证码存入session
        session.setAttribute(SysConstant.SESSION_PIC_CODE_NAME, code);

        // 禁止图像缓存
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.flush();
        sos.close();

    }
}
