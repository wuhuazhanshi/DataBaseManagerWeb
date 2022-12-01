package com.database.service.impl;

import com.database.dao.OrderMapper;
import com.database.dao.ProductMapper;
import com.database.entity.Product;
import com.database.service.ProductService;
import com.database.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getProductList() {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            return mapper.getProductList();
        }
    }

    @Override
    public List<Product> getProductListByPrice(int lower, int upper) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
            return mapper.getProductListByPrice(lower, upper);
        }
    }
}
