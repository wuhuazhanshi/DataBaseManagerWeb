package com.database.servlet;

import com.database.entity.User;
import com.database.service.ProductService;
import com.database.service.impl.ProductServiceImpl;
import com.database.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    ProductService service;
    @Override
    public void init() throws ServletException {
        service = new ProductServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();

        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("username", user.getUsername());
        context.setVariable("product_list", service.getProductList());
        ThymeleafUtil.process("product.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String Lowerlimit = req.getParameter("Lowerlimit");
        String Upperlimit = req.getParameter("Upperlimit");
        if (Lowerlimit == "") {
            Lowerlimit = "0";
        }
        if (Upperlimit == "") {
            Upperlimit = "2147483647";
        }
        int lower = Integer.parseInt(Lowerlimit);
        int upper = Integer.parseInt(Upperlimit);
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("username", user.getUsername());
        context.setVariable("product_list", service.getProductListByPrice(lower, upper));
        ThymeleafUtil.process("product.html",context,resp.getWriter());
    }
}
