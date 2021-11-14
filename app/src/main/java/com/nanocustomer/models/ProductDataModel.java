package com.nanocustomer.models;

import java.io.Serializable;
import java.util.List;

public class ProductDataModel implements Serializable {
    private Data data;

    public Data getData() {
        return data;
    }

    public  class Data implements Serializable {
        private List<ProductModel> data;


        public List<ProductModel> getData() {
            return data;
        }
    }

}
