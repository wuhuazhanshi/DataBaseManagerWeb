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



@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    UserService service;
    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        context.setVariable("failure", false);
        if(req.getSession().getAttribute("register-failure") != null){
            context.setVariable("failure", true);
            req.getSession().removeAttribute("register-failure");
        }
        ThymeleafUtil.process("register.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if(service.ist(username, email, password, req.getSession())) {
            req.getSession().setAttribute("register-success", new Object());
            resp.sendRedirect("login");
        } else {
            req.getSession().setAttribute("register-failure", new Object());
            this.doGet(req, resp);
        }
    }
}
