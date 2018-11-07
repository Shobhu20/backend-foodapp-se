package com.windsor.foodapp.interceptor;

import com.windsor.foodapp.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {


    @Resource
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("\n-------- user interceptor.preHandle --- ");
        String email = request.getParameter("email");
        String token = request.getParameter("token");

        boolean result =  userService.validateToken(email, token);
        if(!result)
            response.sendRedirect("/loginFailed");
        return result;
    }
}
