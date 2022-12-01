package com.database.service.impl;

import com.database.dao.OrderMapper;
import com.database.entity.Order;
import com.database.service.OrderService;
import com.database.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> getOrderList() {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            return mapper.getOrderList();
        }
    }

    @Override
    public List<Order> getOrderListByUsername(String username) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            return mapper.getOrderListByUsername(username);
        }
    }

    @Override
    public List<Order> getOrderListByOrderid(String Orderid) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
            return mapper.getOrderListByOrderid(Orderid);
        }
    }

}
