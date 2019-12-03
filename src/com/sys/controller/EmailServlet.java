package com.sys.controller;

import com.Utils.EmailUtil;
import com.sys.constant.SysConstant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/03/15:55
 * @Description:
 */
@WebServlet("/sys/email/*")
public class EmailServlet extends BaseServlet {
    public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取邮箱地址
        String emailName = request.getParameter("email");
        //定义字符数组了，里面放26个英文字母和数字0~9
        char[] codes = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I',
                'O', 'P', 'L', 'K', 'J', 'H', 'G', 'F', 'D',
                'S', 'A', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '1',
                '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        //创建一个随机对象,生成四位不重复的验证码
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        while (true) {
            char c = codes[random.nextInt(codes.length)];
            if (stringBuilder.indexOf(c + "") == -1) {
                stringBuilder.append(c);
                count++;
                if (count == 4) {
                    break;
                }
            }
        }
        String code = stringBuilder.toString();
        //发送验证码
        EmailUtil.sendEmail(emailName, code);
        //把验证码存入session中
        HttpSession session = request.getSession();
        session.setAttribute(SysConstant.SESSION_EMAIL_CODE_NAME, code);
        session.setMaxInactiveInterval(60);
        PrintWriter out = response.getWriter();
        out.append("发送邮件成功");

    }

}
