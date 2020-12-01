package com.zhbin.servlet.user;

import com.zhbin.pojo.User;
import com.zhbin.service.user.UserService;
import com.zhbin.service.user.UserServiceImpl;
import com.zhbin.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    //servlet控制层调用业务层


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet -- login");

        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

//        System.out.println("userCode="+userCode);
//        System.out.println("userPassword="+userPassword);

        UserService userService = new UserServiceImpl();
//        System.out.println("service");
        User user = userService.login(userCode, userPassword);

//        System.out.println(user.getUserName()+":"+user.getUserRole());

        //找到了人
        if(user!=null){
            //放入session
            req.getSession().setAttribute(Constants.USER_SESSION,user);

            //重定向页面
            resp.sendRedirect("jsp/frame.jsp");
        }
        //没有找到人
        else{
            req.setAttribute("error","用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
