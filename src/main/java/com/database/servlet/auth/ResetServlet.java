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

@WebServlet("/forgotpw")
public class ResetServlet extends HttpServlet {
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
        if (req.getSession().getAttribute("reset-failure") != null) {
            context.setVariable("init", false);
            context.setVariable("failure", true);
            req.getSession().removeAttribute("reset-failure");
        }
        ThymeleafUtil.process("forgot-pws.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if (service.reset(email, req.getSession())) {
            req.getSession().setAttribute("reset-success", new Object());
            resp.sendRedirect("login");
        } else {
            req.getSession().setAttribute("reset-failure", new Object());
            this.doGet(req, resp);
        }
    }
}
