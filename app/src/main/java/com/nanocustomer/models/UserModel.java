package com.nanocustomer.models;

import java.io.Serializable;

public class UserModel implements Serializable {
    private boolean status;
    private Data data;

    public boolean isStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    public class Data implements Serializable {
        public String token;
        public User user;

        public String getToken() {
            return token;
        }

        public User getUser() {
            return user;
        }

        public class User implements Serializable {
            private int id;
            private String full_name;
            private String phone;
            private String email;
            private String fax;
            private String propic;
            private String zip_code;
            private String city;
            private String country;
            private String address;
            private String balance;
            private String email_verified;
            private String affilate_code;
            private String affilate_income;
            private String affilate_link;
            private String ban;
            private String type;
            private String package_end_date;
            private String firebase_token;
            private String photo;

            public int getId() {
                return id;
            }

            public String getFull_name() {
                return full_name;
            }

            public String getPhone() {
                return phone;
            }

            public String getEmail() {
                return email;
            }

            public String getFax() {
                return fax;
            }

            public String getPropic() {
                return propic;
            }

            public String getZip_code() {
                return zip_code;
            }

            public String getCity() {
                return city;
            }

            public String getCountry() {
                return country;
            }

            public String getAddress() {
                return address;
            }

            public String getBalance() {
                return balance;
            }

            public String getEmail_verified() {
                return email_verified;
            }

            public String getAffilate_code() {
                return affilate_code;
            }

            public String getAffilate_income() {
                return affilate_income;
            }

            public String getAffilate_link() {
                return affilate_link;
            }

            public String getBan() {
                return ban;
            }

            public String getType() {
                return type;
            }

            public String getPackage_end_date() {
                return package_end_date;
            }

            public String getFirebase_token() {
                return firebase_token;
            }

            public void setFirebase_token(String firebase_token) {
                this.firebase_token = firebase_token;
            }

            public String getPhoto() {
                return photo;
            }
        }
    }
}
