package com.nanocustomer.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SingleOrderModel implements Serializable {
    private OrderModel data;
    private OrderModel order;


    public OrderModel getOrder() {
        return data;
    }

    public OrderModel getData() {
        return order;
    }
}
