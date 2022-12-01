package com.database.entity;

import lombok.Data;

@Data
public class Order {

    String orderid;
    String orderdate;
    String total;
    String paymentmethod;
    String paymentstatus;
    String productid;
    String productname;
    String consumerid;
    String businessid;
    String number;
    String price;
}
