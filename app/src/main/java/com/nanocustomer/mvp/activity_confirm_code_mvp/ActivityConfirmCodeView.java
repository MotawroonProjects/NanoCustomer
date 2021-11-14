package com.nanocustomer.mvp.activity_confirm_code_mvp;


import com.nanocustomer.models.UserModel;

public interface ActivityConfirmCodeView {
    void onSuccessCode();
    void onCounterStarted(String time);
    void onCounterFinished();
    void onCodeFailed(String msg);

}
