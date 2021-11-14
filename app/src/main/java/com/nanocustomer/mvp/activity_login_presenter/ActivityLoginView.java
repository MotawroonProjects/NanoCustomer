package com.nanocustomer.mvp.activity_login_presenter;

import com.nanocustomer.models.UserModel;

public interface ActivityLoginView {
    void onLoginSuccess(UserModel userModel);
    void onFailed(String msg);

}
