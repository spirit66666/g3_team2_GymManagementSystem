package org.gym.servet.service;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@WebFilter(filterName = "WebVisitFilter",urlPatterns = {"/*"})
public class WebVisitFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("进入目标资源之前先干点啥");
        System.out.println("处理一下服务端返回的response");
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession(true);
        String request_uri = req.getRequestURI();
        String ctxPath = req.getContextPath();
        String uri = request_uri.substring(ctxPath.length());
        System.out.println("访问的资源路request径为：" + request_uri);
        System.out.println("访问的资源cts路径为：" + ctxPath);
        System.out.println("访问的资源路uri径为：" + uri);
        if(uri.contains("get")){
            // pass the request along the filter chain
            System.out.println("访问App资源，放行");
            if(null != session.getAttribute("token")){
                System.out.println("访问App资源，已登录，放行");
                chain.doFilter(request, response);}
            else {
                System.out.println("访问App资源，未登录，转到登录页面");
                HttpServletResponse resp = (HttpServletResponse)response;
                resp.setContentType("text/html;");
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().println("请先登录！ 3秒后转到登录页面。");
                resp.setHeader("refresh", "3;url="+ctxPath + "/");
                return;
            }}
        else {
            System.out.println("访问非App资源，放行");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("过滤器被销毁了");
    }}