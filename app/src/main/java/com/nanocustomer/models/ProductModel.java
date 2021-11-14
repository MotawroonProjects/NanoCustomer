package com.nanocustomer.models;

import java.io.Serializable;
import java.util.List;

public class ProductModel implements Serializable {
    private int id;
    private String title;
    private String thumbnail;
    private double rating;
    private double current_price;
    private double previous_price;
    private String sale_end_date;
    private String policy;
    private String details;
    private List<GalleryModel> images;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public double getRating() {
        return rating;
    }

    public double getCurrent_price() {
        return current_price;
    }

    public double getPrevious_price() {
        return previous_price;
    }

    public String getSale_end_date() {
        return sale_end_date;
    }

    public String getPolicy() {
        return policy;
    }

    public List<GalleryModel> getImages() {
        return images;
    }

    public String getDetails() {
        return details;
    }
}
