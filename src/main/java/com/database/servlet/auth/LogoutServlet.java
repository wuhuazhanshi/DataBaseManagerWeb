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

@WebServlet("/logout")
//public class LogoutServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getSession().removeAttribute("user");
//        resp.sendRedirect("login");
//    }
//}
public class LogoutServlet extends HttpServlet {

//    UserService service;
//    @Override
//    public void init() throws ServletException {
//        service = new UserServiceImpl();
//    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        resp.sendRedirect("login");
    }
}