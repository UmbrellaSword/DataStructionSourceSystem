
package com.ruolan.spring.controller;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Controller
public class LoginController{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //接收参数
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");

        //判断登陆是否成功
        if(userName.equals("刘小斌") && userPwd.equals("123456")) {
            //登陆成功

            //创建session对象
            HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
            session.setAttribute("loginName", userName);
            //跳转到用户主页
            response.sendRedirect(request.getContextPath()+"/IndexServlet");
        } else {
            //登陆失败，请求重定向
            response.sendRedirect(request.getContextPath() + "/fail.html");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
