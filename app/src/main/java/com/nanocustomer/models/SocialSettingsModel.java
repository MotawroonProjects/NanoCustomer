package com.nanocustomer.models;

import java.io.Serializable;

public class SocialSettingsModel implements Serializable {
    public Data data;


    public Data getData() {
        return data;
    }


    public class Data implements Serializable{
        private String id;
        private String facebook;
        private String gplus;
        private String twitter;
        private String linkedin;
        private String dribble;
        private String f_status;
        private String g_status;
        private String t_status;
        private String l_status;
        private String d_status;
        private String f_check;
        private String g_check;
        private String fclient_id;
        private String fclient_secret;
        private String fredirect;
        private String gclient_id;
        private String gclient_secret;
        private String gredirect;

        public String getId() {
            return id;
        }

        public String getFacebook() {
            return facebook;
        }

        public String getGplus() {
            return gplus;
        }

        public String getTwitter() {
            return twitter;
        }

        public String getLinkedin() {
            return linkedin;
        }

        public String getDribble() {
            return dribble;
        }

        public String getF_status() {
            return f_status;
        }

        public String getG_status() {
            return g_status;
        }

        public String getT_status() {
            return t_status;
        }

        public String getL_status() {
            return l_status;
        }

        public String getD_status() {
            return d_status;
        }

        public String getF_check() {
            return f_check;
        }

        public String getG_check() {
            return g_check;
        }

        public String getFclient_id() {
            return fclient_id;
        }

        public String getFclient_secret() {
            return fclient_secret;
        }

        public String getFredirect() {
            return fredirect;
        }

        public String getGclient_id() {
            return gclient_id;
        }

        public String getGclient_secret() {
            return gclient_secret;
        }

        public String getGredirect() {
            return gredirect;
        }
    }

}
