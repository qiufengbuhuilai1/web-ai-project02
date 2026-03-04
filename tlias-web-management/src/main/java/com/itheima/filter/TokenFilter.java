package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter  {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求路径
        String requestURI = request.getRequestURI();
        //判断是否为登录请求
        if(requestURI.contains("/login")){
            log.info("登录请求,放行");
            filterChain.doFilter(request,response);
            return;
        }
        //获取请求头token
        String token = request.getHeader("token");
        //判断是否存在
        if(token==null || token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(401);
            return;
        }
        //验证token
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌验证失败，响应401");
            response.setStatus(401);
            return;
        }
        //放行
        log.info("令牌验证通过，放行");
        filterChain.doFilter(request,response);
    }
}
