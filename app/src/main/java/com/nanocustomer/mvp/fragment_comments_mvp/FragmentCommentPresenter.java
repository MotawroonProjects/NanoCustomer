package com.nanocustomer.mvp.fragment_comments_mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.nanocustomer.R;
import com.nanocustomer.models.AddFavoriteDataModel;
import com.nanocustomer.models.ProductDataModel;
import com.nanocustomer.models.ProductModel;
import com.nanocustomer.models.SingleCommentDataModel;
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


public class FragmentCommentPresenter {
    private Context context;
    private FragmentCommentView view;
    private Preferences preference;
    private UserModel userModel;

    public FragmentCommentPresenter(Context context, FragmentCommentView view) {
        this.context = context;
        this.view = view;
        preference = Preferences.getInstance();
        userModel = preference.getUserData(context);

    }




    public void add_comment(String comment,ProductModel productModel)
    {
Log.e("dddd",userModel.getData().getToken());
        if (userModel==null){
            return;
        }
        ProgressDialog dialog = Common.createProgressDialog(context,context.getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        String user_id = String.valueOf(userModel.getData().getUser().getId());
        Api.getService(Tags.base_url)
                .addComment( userModel.getData().getToken(), String.valueOf(productModel.getId()),comment)
                .enqueue(new Callback<SingleCommentDataModel>() {
                    @Override
                    public void onResponse(Call<SingleCommentDataModel> call, Response<SingleCommentDataModel> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            if (response.body() != null&&response.body().getStatus()) {
                                view.onSuccess(response.body().getData());
                            }


                        } else {

                            dialog.dismiss();
                            try {
                                Log.e("errorNotCode", response.code() + "__" + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if (response.code() == 500) {
                                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                            } else {
                                view.onFailed(context.getString(R.string.failed));

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SingleCommentDataModel> call, Throwable t) {
                        try {
                            dialog.dismiss();
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
