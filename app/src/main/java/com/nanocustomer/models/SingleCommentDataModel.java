package com.nanocustomer.models;

import java.io.Serializable;

public class SingleCommentDataModel implements Serializable {
    private boolean status;
    private String message;
    private CommentModel data;

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public CommentModel getData() {
        return data;
    }
}
