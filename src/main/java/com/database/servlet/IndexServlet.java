package com.database.servlet;

import com.database.entity.Order;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    class OrderComparator implements Comparator<Order>  {
        @Override
        public int compare(Order o1, Order o2) {

            if (o1.getOrderdate().compareTo(o2.getOrderdate()) < 0) {
                return 1;
            } else if (o1.getOrderdate().compareTo(o2.getOrderdate()) > 0) {
                return -1;
            } else {
                return 0;
            }
        }
    };
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
        context.setVariable("user", user);
        List<Order> order_list = service.getOrderListByUsername(user.getUsername());
        Collections.sort(order_list, new OrderComparator());
        context.setVariable("order_list", order_list);
        ThymeleafUtil.process("home.html", context, resp.getWriter());
    }
}
