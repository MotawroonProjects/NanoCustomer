package com.nanocustomer.models;

import java.io.Serializable;

public class CommentModel implements Serializable {
    private int id;
    private String user_image;
    private String user_id;
    private String name;
    private String comment;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public String getUser_image() {
        return user_image;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
