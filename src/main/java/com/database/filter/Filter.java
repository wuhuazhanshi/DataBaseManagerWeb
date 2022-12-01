package com.database.filter;

import com.database.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class Filter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String url = req.getRequestURL().toString();
        if (!url.contains("/assets/") && !url.endsWith("login") && !url.endsWith("register") && !url.endsWith("forgotpw")) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                res.sendRedirect("login");
                return;
            }
            if (!url.endsWith("index") && !url.endsWith("product") && !url.endsWith("order") && !url.endsWith("error")) {
                res.sendRedirect("error");
                return;
            }
        }
        chain.doFilter(req, res);
    }
}
