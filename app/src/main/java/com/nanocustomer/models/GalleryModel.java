package com.nanocustomer.models;

import java.io.Serializable;

public class GalleryModel implements Serializable {
   private int id;
   private String image;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}
