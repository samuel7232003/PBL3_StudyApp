package com.studywithme.Filter;

import com.studywithme.model.User;
import com.studywithme.util.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private ServletContext context;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if(url.startsWith("/PBL3/login")){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            User user = (User) SessionUtil.getInstance().getValue(request, "user");
            if(user != null && user.getStatus() == 0 ) {
                if (url.startsWith("/PBL3/admin")) {
                    if (user.getRole().getCode().equals("ADMIN")) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else if (user.getRole().getCode().equals("USER")) {
                        response.sendRedirect(request.getContextPath() + "/home?page=1&maxPageItem=6&sortName=createdDate&sortBy=desc");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/login");
                    }
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                if (url.startsWith("/PBL3/logout")){
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            }
        }
        /*if (url.startsWith("/PBL3/admin")) {
            User user = (User) SessionUtil.getInstance().getValue(request, "user");
            if (user != null) {
                if (user.getRole().getCode().equals("ADMIN")) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else if (user.getRole().getCode().equals("USER")) {
                    response.sendRedirect(request.getContextPath() + "/home?page=1&maxPageItem=6&sortName=createdDate&sortBy=desc");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }*/
    }


    @Override
    public void destroy() {
    }
}
