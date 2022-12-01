package com.database.service;

import com.database.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductList();
    List<Product>  getProductListByPrice(int lower, int upper);
}
