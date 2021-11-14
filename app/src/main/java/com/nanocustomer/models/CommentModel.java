package com.nanocustomer.models;

import java.io.Serializable;

public class CommentModel implements Serializable {
    private int id;
    private String user_id;
    private String product_id;
    private String text;
    private UserModel.Data.User user;

    public int getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getText() {
        return text;
    }

    public UserModel.Data.User getUser() {
        return user;
    }
}
