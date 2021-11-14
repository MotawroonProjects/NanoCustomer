package com.nanocustomer.models;

import java.io.Serializable;
import java.util.List;

public class SliderDataModel implements Serializable {
    private boolean status;
    private List<Data> data;

    public boolean isStatus() {
        return status;
    }

    public List<Data> getData() {
        return data;
    }

    public class Data implements Serializable{
        private int id;
        private String subtitle;
        private String title;
        private String small_text;
        private String image;
        private String redirect_url;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getTitle() {
            return title;
        }

        public String getSmall_text() {
            return small_text;
        }

        public String getImage() {
            return image;
        }

        public String getRedirect_url() {
            return redirect_url;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }

}
