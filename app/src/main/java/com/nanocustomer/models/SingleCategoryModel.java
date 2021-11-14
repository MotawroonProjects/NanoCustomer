package com.nanocustomer.models;

import java.io.Serializable;

public class SingleCategoryModel implements Serializable {
   private int id;
   private String name;
   private String icon;
   private String image;


   private boolean isSelected = false;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getIcon() {
        return icon;
    }

    public String getImage() {
        return image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
