package com.xiangshang.xsclub.web.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthHandlerInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private AuthHandler authHandler;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AssertAuth assertAuth = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), AssertAuth.class);
        if (assertAuth == null) {
            return true;
        }

        boolean autoLogin = authHandler.autoLogin(request, response);
        if (!autoLogin) {
            throw new InvalidAuthException();
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        SecurityContextHolder.clearContext();
    }
}
