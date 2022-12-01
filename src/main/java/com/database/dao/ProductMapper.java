package com.database.dao;

import com.database.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {
    @Select("select * from t_product")
    List<Product> getProductList();
    @Select("select * from t_product where currentprice >= #{lower} and currentprice <= #{upper}")
    List<Product> getProductListByPrice(@Param("lower") int lower,@Param("upper") int upper);
}
