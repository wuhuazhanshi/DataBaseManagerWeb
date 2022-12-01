package com.database.entity;

import lombok.Data;

@Data
public class Product {
    String productid;
    String productname;
    String productintroduction;
    int originalprice;
    int currentprice;
    String imgurl;
}