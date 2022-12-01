package com.database.dao;

import com.database.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    @Select("select * from t_order")
    List<Order> getOrderList();

    @Select("select * from t_order where consumerid = #{username} or businessid = #{username}")
    List<Order> getOrderListByUsername(@Param("username") String username);

    @Select("select * from t_order where orderid = #{Orderid} or productname = #{Orderid}")
    List<Order> getOrderListByOrderid(@Param("Orderid") String Orderid);
}
