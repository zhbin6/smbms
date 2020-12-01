package com.zhbin.filter;

import com.zhbin.pojo.User;
import com.zhbin.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        //已经退出了，即移除了session
        if(user == null){
//            System.out.println("错了");
            resp.sendRedirect(req.getContextPath()+"/error.jsp");
        }
        else{
            filterChain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
