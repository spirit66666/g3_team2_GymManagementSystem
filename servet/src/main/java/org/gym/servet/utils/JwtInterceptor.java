package org.gym.servet.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gym.servet.entity.User;
import org.gym.servet.entity.admin;
import org.gym.servet.service.AdminService;
import org.gym.servet.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.lang.reflect.Method;



@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private Userservice Userservice;
    @Autowired
    private AdminService adminService;
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }System.out.println(token);
        // 执行认证
        if (token == null) {
            throw new RuntimeException("无token，请重新登录");
        }
// 获取 token 中的 user id
        String userId;
        String adminId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
            adminId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        User user = Userservice.getById(userId);
        admin admin = adminService.getById(adminId);
        if (user == null && admin == null) {
            throw new RuntimeException("用户不存在，请重新登录");
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassWord())).build();
        JWTVerifier jwtVerifierAdmin = JWT.require(Algorithm.HMAC256(admin.getPassWord())).build();
        try {
            jwtVerifier.verify(token);
            jwtVerifierAdmin.verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("402");
        }



        return true;
    }

}