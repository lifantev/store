package com.nedu.store.filter;

import com.nedu.store.user.User;
import com.nedu.store.user.service.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Component
@Order(199)
@Slf4j
public class AuthenticationFilter extends HttpFilter {

    private final UserService userService;

    @Getter
    private static final ThreadLocal<String> USERNAME_THR = new ThreadLocal<>();

    public AuthenticationFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        if (request.getRequestURI().startsWith("/api/v1/users/")) {

            String authorization = request.getHeader("Authorization");
            String base64 = authorization.substring(6);

            String[] cred = (new String(Base64.getDecoder().decode(base64))).split(":");
            String login = cred[0];
            String password = cred[1];

            User user = userService.getUserByLogin(login);

            if (null == user.getLogin()) {
                response.setStatus(401);
                return;
            }

            if (!password.equals(user.getPassword())) {
                response.setStatus(401);
                return;
            }

            USERNAME_THR.set(login);
        }

        chain.doFilter(request, response);
    }
}
