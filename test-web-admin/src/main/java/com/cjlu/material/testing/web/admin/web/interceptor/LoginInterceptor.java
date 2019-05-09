package com.cjlu.material.testing.web.admin.web.interceptor;

import com.cjlu.material.testing.commons.constant.ConstantUtils;
import com.cjlu.material.testing.domain.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        TbUser tbuser = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
        //未登录
        if (tbuser==null){
            httpServletResponse.sendRedirect("/login");
        }
        //登录

            return true;

    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
