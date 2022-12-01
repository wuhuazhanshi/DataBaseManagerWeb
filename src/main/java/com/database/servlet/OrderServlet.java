package com.database.servlet;

import com.database.entity.User;
import com.database.service.OrderService;
import com.database.service.impl.OrderServiceImpl;
import com.database.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    OrderService service;
    @Override
    public void init() throws ServletException {
        service = new OrderServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();

        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("username", user.getUsername());
        context.setVariable("order_list", service.getOrderListByUsername(user.getUsername()));
        ThymeleafUtil.process("order.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String Orderid = req.getParameter("Orderid");
        if (Orderid != "") {
            User user = (User) req.getSession().getAttribute("user");
            context.setVariable("username", user.getUsername());
            context.setVariable("order_list", service.getOrderListByOrderid(Orderid));
        } else {
            User user = (User) req.getSession().getAttribute("user");
            context.setVariable("username", user.getUsername());
            context.setVariable("order_list", service.getOrderListByUsername(user.getUsername()));
        }
        ThymeleafUtil.process("order.html", context, resp.getWriter());

    }
}
