package com.it.interceptor;

import com.it.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@javax.servlet.annotation.WebFilter("*.jsp")
public class WebFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //1.判断当前用户是否登录，如果登录，我就放行，不拦截
        //2.如果没有登录，则需要判断啊当前访问的url地址，如果url地址的login或者注册，则放行
        //3.否则不放行
        HttpServletRequest request1=(HttpServletRequest) request;
        HttpServletResponse response1=(HttpServletResponse) response;
        User user=(User) request1.getSession().getAttribute("USER_SESSION");
        if (user!=null){
            //放行
            chain.doFilter(request, response);
            return;
        }
        //获取当前访问的url地址
        String url=request1.getRequestURI();
        if (url.indexOf("login")>=0||url.indexOf("register")>=0){
            chain.doFilter(request1,response1);
            return;
        }
//        request1.setAttribute("msg","您没有访问权限");
        request1.getRequestDispatcher("/admin/login.jsp").forward(request,response);
    }
}
