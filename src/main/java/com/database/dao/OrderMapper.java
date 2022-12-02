package com.database.dao;

import com.database.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    @Select("select * from sale")
    List<Order> getOrderList();

    @Select("select * from sale where customer_id = #{customerid} ")
    List<Order> getOrderListByUsername(@Param("customerid") String username);

    @Select("select * from sale where sale_id = #{saleid}")
    List<Order> getOrderListByOrderid(@Param("saleid") String Orderid);

}
