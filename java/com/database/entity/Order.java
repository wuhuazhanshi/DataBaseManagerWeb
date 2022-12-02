package com.database.entity;

import lombok.Data;

@Data
public class Order {
//----sale
    String saleid;
    String saledate;
    int salenumber;
    int saleamount;
    String bookisbn;
    String customerid;
}
