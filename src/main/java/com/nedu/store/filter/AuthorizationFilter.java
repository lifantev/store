package com.nedu.store.filter;

import com.nedu.store.user.User;
import com.nedu.store.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(200)
@Slf4j
public class AuthorizationFilter extends HttpFilter {

    private UserService userService;

    public  AuthorizationFilter(UserService userService){
        this.userService = userService;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if(request.getRequestURI().startsWith("/api/v1/products")) {
            User user = userService.getUserByLogin(AuthenticationFilter.getUSERNAME_THR().get());

            if (!"admin".equals(user.getLogin())){
                response.getWriter().write("Access forbidden.");
                response.setStatus(403);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
