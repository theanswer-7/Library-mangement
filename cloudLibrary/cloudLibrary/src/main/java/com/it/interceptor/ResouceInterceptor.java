package com.it.interceptor;

import com.it.pojo.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResouceInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws ServletException, IOException {
        //1.判断用户是否登录，如果登录，放行
        //2.如果没有登录，判断当前请求的页面是否是注册或者登录页面，如果是则放行
        //3.不放行
        User user=(User) request.getSession().getAttribute("USER_SESSION");
        if(user!=null){
            return  true;
        }
        String url=request.getRequestURI();
        if (url.indexOf("login")>=0 || url.indexOf("register")>=0){
            return true;
        }
        request.setAttribute("msg","您还没有登录，请先登录");
        request.getRequestDispatcher("/admin/login.jsp").forward(request,response);
        return false;
    }


}
