package com.database.dao;

import com.database.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {
    @Select("select * from Book")
    List<Product> getProductList();
    @Select("select * from Book where current_price >= #{lower} and current_price <= #{upper}")
    List<Product> getProductListByPrice(@Param("lower") int lower,@Param("upper") int upper);
}
