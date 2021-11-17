package com.nanocustomer.mvp.activity_order_steps_mvp;

import android.content.Context;
import android.util.Log;

import com.nanocustomer.R;
import com.nanocustomer.models.OrderModel;
import com.nanocustomer.models.SingleOrderModel;
import com.nanocustomer.models.UserModel;
import com.nanocustomer.mvp.activity_cart_mvp.CartActivityView;
import com.nanocustomer.preferences.Preferences;
import com.nanocustomer.remote.Api;
import com.nanocustomer.tags.Tags;

import java.io.IOException;

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

    public void getProduct(OrderModel orderModel) {
        if (userModel == null) {
            return;
        }
        String user_id = String.valueOf(userModel.getData().getUser().getId());
        view.onProgressShow();
        Api.getService(Tags.base_url)
                .getSingleOrders(userModel.getData().getToken(),String.valueOf(orderModel.getId()))
                .enqueue(new Callback<SingleOrderModel>() {
                    @Override
                    public void onResponse(Call<SingleOrderModel> call, Response<SingleOrderModel> response) {
                        view.onProgressHide();
                        if (response.isSuccessful()) {
                            if (response.body() != null && response.body().getOrder() != null) {
                                view.onSuccess(response.body());

                            }


                        } else {
                            view.onProgressHide();
                            try {
                                Log.e("errorNotCode", response.code() + "__" + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if (response.code() == 500) {
                                view.onFailed("Server Error");

                            } else {
                                view.onFailed(context.getString(R.string.failed));

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SingleOrderModel> call, Throwable t) {
                        try {
                            view.onProgressHide();


                            if (t.getMessage() != null) {
                                Log.e("error_not_code", t.getMessage() + "__");

                                if (t.getMessage().toLowerCase().contains("failed to connect") || t.getMessage().toLowerCase().contains("unable to resolve host")) {
                                    view.onFailed(context.getString(R.string.something));
                                } else {
                                    view.onFailed(context.getString(R.string.failed));

                                }
                            }
                        } catch (Exception e) {
                            Log.e("Error", e.getMessage() + "__");
                        }
                    }
                });
    }


}
