package com.database.entity;

import lombok.Data;

@Data
public class Product {
//    ----book
    String bookisbn;
    String bookname;
    String bookpubdate;
    int bookprice;
    int bookstock;
    int currentprice;
    String authorid;
    String publisherid;
}