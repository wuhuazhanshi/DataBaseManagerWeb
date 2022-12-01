package com.database.servlet.auth;

import com.database.service.UserService;
import com.database.service.impl.UserServiceImpl;
import com.database.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService service;
    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        context.setVariable("init", true);
        context.setVariable("failure", false);
        context.setVariable("success", false);
        context.setVariable("reset", false);
        if(req.getSession().getAttribute("login-failure") != null){
            context.setVariable("init", false);
            context.setVariable("failure", true);
            context.setVariable("success", false);
            context.setVariable("reset", false);
            req.getSession().removeAttribute("login-failure");
        }
        if (req.getSession().getAttribute("register-success") != null) {
            context.setVariable("init", false);
            context.setVariable("failure", false);
            context.setVariable("success", true);
            context.setVariable("reset", false);
            req.getSession().removeAttribute("register-success");
        }
        if (req.getSession().getAttribute("reset-success") != null) {
            context.setVariable("init", false);
            context.setVariable("failure", false);
            context.setVariable("success", false);
            context.setVariable("reset", true);
            req.getSession().removeAttribute("register-success");
        }
        if (req.getSession().getAttribute("remember-me") != null) {
            resp.sendRedirect("index");
            return;
        }
        ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember-me");
        if (Objects.equals(remember, "on")) {
            req.getSession().setAttribute("remember-me", new Object());
        } else {
            if (req.getSession().getAttribute("remember-me") != null) {
                req.getSession().removeAttribute("remember-me");
            }
        }
        if(service.auth(username, password, req.getSession())){
            resp.sendRedirect("index");
        }else {
            req.getSession().setAttribute("login-failure", new Object());
            this.doGet(req, resp);
        }
    }
}
