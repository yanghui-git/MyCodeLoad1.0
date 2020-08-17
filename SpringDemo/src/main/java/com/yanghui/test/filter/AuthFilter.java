package com.yanghui.test.filter;

import org.springframework.context.annotation.Configuration;;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

//@Configuration
@WebFilter(filterName = "自定义过滤器",urlPatterns = {"/test","/yh/*"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化   ：   "+filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();
        System.out.println("this is filter   "+uri);
        //在filter加入线程信息
         OpenrationUser openrationUser=new OpenrationUser();
         openrationUser.setDate(new Date());
         openrationUser.setAge(httpServletRequest.getParameterMap().get("age")[0]);
         openrationUser.setName(httpServletRequest.getParameterMap().get("name")[0]);

         ThreadLoacalUtil.set(openrationUser);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
