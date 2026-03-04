package com.itheima.interceptor;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        //获取请求路径
//        String requestURI = request.getRequestURI();
//        //判断是否为登录请求
//        if(requestURI.contains("/login")){
//            log.info("登录请求,放行");
//            return true;
//        }
        //获取请求头token
        String token = request.getHeader("token");
        //判断是否存在
        if(token==null || token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(401);
            return  false;
        }
        //验证token
        try {
            Claims claims = JwtUtils.parseJWT(token);
//            Integer empId = Integer.valueOf(claims.get("id").toString());
//            CurrentHolder.setCurrentId(empId);
//            log.info("当前用户id为：{}，存入",empId);
        } catch (Exception e) {
            log.info("令牌验证失败，响应401");
            response.setStatus(401);
            return  false;
        }
        //放行
        log.info("令牌验证通过，放行");
        return true;
    }
}
