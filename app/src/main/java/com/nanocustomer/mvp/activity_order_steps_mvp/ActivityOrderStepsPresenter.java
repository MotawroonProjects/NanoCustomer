package com.nanocustomer.mvp.activity_order_steps_mvp;

import android.content.Context;

import com.nanocustomer.models.UserModel;
import com.nanocustomer.mvp.activity_cart_mvp.CartActivityView;
import com.nanocustomer.preferences.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityOrderStepsPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private OrderStepsActivityView view;
    private Context context;

    public ActivityOrderStepsPresenter(OrderStepsActivityView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void backPress() {

        view.onFinished();


    }



}
