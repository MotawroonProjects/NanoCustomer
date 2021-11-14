package com.nanocustomer.mvp.activity_editprofile_mvp;


import com.nanocustomer.models.UserModel;

public interface EditprofileActivityView {
    void onFinished();

    void onFailed(String msg);


    void onLoad();

    void onFinishload();

    void onSuccess(UserModel body);

}
