package com.nanocustomer.mvp.activity_order_details_mvp;

import android.content.Context;
import android.util.Log;

import com.nanocustomer.R;
import com.nanocustomer.models.AddFavoriteDataModel;
import com.nanocustomer.models.OrderModel;
import com.nanocustomer.models.ProductDataModel;
import com.nanocustomer.models.ProductModel;
import com.nanocustomer.models.SingleOrderModel;
import com.nanocustomer.models.UserModel;
import com.nanocustomer.mvp.activity_favorite_mvp.ActivityFavoriteView;
import com.nanocustomer.preferences.Preferences;
import com.nanocustomer.remote.Api;
import com.nanocustomer.share.Common;
import com.nanocustomer.tags.Tags;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityOrderDetailsPresenter {
    private Context context;
    private ActivityOrderDetailsView view;
    private Preferences preference;
    private UserModel userModel;

    public ActivityOrderDetailsPresenter(Context context, ActivityOrderDetailsView view) {
        this.context = context;
        this.view = view;
        preference = Preferences.getInstance();
        userModel = preference.getUserData(context);

    }


    public void getProduct(OrderModel orderModel) {
        if (userModel == null) {
            return;
        }
        String user_id = String.valueOf(userModel.getData().getUser().getId());
        view.onProgressShow();
        Api.getService(Tags.base_url)
                .getSingleOrders(String.valueOf(orderModel.getId()))
                .enqueue(new Callback<SingleOrderModel>() {
                    @Override
                    public void onResponse(Call<SingleOrderModel> call, Response<SingleOrderModel> response) {
                        view.onProgressHide();
                        if (response.isSuccessful()) {
                            if (response.body() != null && response.body().getStatus() == 200 && response.body().getOrder() != null) {
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
