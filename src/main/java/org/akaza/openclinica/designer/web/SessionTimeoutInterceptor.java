package org.akaza.openclinica.designer.web;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionTimeoutInterceptor extends HandlerInterceptorAdapter {

    CookieGenerator cookieGenerator;

    public SessionTimeoutInterceptor() {
        cookieGenerator = new CookieGenerator();
        cookieGenerator.setCookieName("lastAccessedTime");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        long sessionWillTimeoutIn = request.getSession().getLastAccessedTime() + (request.getSession().getMaxInactiveInterval() * 1000);
        cookieGenerator.addCookie(response, String.valueOf(sessionWillTimeoutIn));
        return true;
    }
}
