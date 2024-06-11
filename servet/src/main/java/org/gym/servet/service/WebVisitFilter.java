package org.gym.servet.service;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.gym.servet.Result.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@WebFilter
public class WebVisitFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器");
    }

    @Autowired
    private ResultGenerator generator;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getServletPath();
        System.out.println("访问的资源路径为：" + path);
        HttpSession session = req.getSession(true);
        String request_uri = req.getRequestURI();
        String ctxPath = req.getContextPath();
        String uri = request_uri.substring(ctxPath.length());
        System.out.println("访问的资源路request径为：" + request_uri);
        System.out.println("访问的资源cts路径为：" + ctxPath);
        System.out.println("访问的资源路uri径为：" + uri);

        if (uri.contains("AppHome")) {
            // pass the request along the filter chain
            if (null != session.getAttribute("token")) {
                System.out.println("访问App资源，已登录，放行");
                chain.doFilter(request, response);
            } else {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.sendRedirect(req.getContextPath() + "http://localhost:5173/");

                generator.getFailResult("未登录，请先登录");
            }
        } else {
            System.out.println("访问非App资源，放行");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("过滤器被销毁了");
    }
}
