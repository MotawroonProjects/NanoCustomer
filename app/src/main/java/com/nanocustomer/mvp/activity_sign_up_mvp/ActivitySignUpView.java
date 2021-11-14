package com.nanocustomer.mvp.activity_sign_up_mvp;


import com.nanocustomer.models.UserModel;

public interface ActivitySignUpView {
    void onSuccess(UserModel userModel);
    void onFailed(String msg);

}
