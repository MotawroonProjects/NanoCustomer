package com.nanocustomer.models;

import java.io.Serializable;
import java.util.List;

public class OrderModel implements Serializable {
    public int id;
    public String number;
    public String total;
    public String status;
    public String details;
    public List<CartProductModel> products;

    public String payment_url;

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    public List<CartProductModel> getProducts() {
        return products;
    }

    public String getPayment_url() {
        return payment_url;
    }

    public static class CartProductModel implements Serializable{
        private int qty;
        private int size_key;
        private String size_qty;
        private String size_price;
        private String size;
        private String color;
        private String stock;
        private String keys;
        private String values;
        private String item_price;
        private int id;
        private String name;
        private String vendor_id;
        private String type;
        private String feature_image;
        private double total_price;

        public int getQty() {
            return qty;
        }

        public int getSize_key() {
            return size_key;
        }

        public String getSize_qty() {
            return size_qty;
        }

        public String getSize_price() {
            return size_price;
        }

        public String getSize() {
            return size;
        }

        public String getColor() {
            return color;
        }

        public String getStock() {
            return stock;
        }

        public String getKeys() {
            return keys;
        }

        public String getValues() {
            return values;
        }

        public String getItem_price() {
            return item_price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getVendor_id() {
            return vendor_id;
        }

        public String getType() {
            return type;
        }

        public String getFeature_image() {
            return feature_image;
        }

        public double getTotal_price() {
            return total_price;
        }
    }

}
