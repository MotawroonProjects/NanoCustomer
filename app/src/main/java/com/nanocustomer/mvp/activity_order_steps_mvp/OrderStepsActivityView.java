package com.nanocustomer.mvp.activity_order_steps_mvp;


import com.nanocustomer.models.SingleOrderModel;

public interface OrderStepsActivityView {
    void onFinished();
    void onSuccess(SingleOrderModel data);

    void onFailed(String msg);

    void onProgressShow();

    void onProgressHide();

}
