package com.nanocustomer.mvp.fragment_category_mvp;

import android.content.Context;
import android.util.Log;

import com.nanocustomer.R;
import com.nanocustomer.models.AllCategoryModel;
import com.nanocustomer.models.CategoryDataModel;
import com.nanocustomer.models.ProductDataModel;
import com.nanocustomer.models.SliderDataModel;
import com.nanocustomer.models.SubCategoryDataModel;
import com.nanocustomer.models.UserModel;
import com.nanocustomer.mvp.fragment_home_mvp.FragmentHomeView;
import com.nanocustomer.preferences.Preferences;
import com.nanocustomer.remote.Api;
import com.nanocustomer.tags.Tags;

import java.io.IOException;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentCategoryPresenter {
    private Context context;
    private FragmentCategoryView view;
    private Preferences preference;
    private UserModel userModel;
    private double lat = 0.0, lng = 0.0;
    private String lang="ar";

    public FragmentCategoryPresenter(Context context, FragmentCategoryView view, double lat, double lng, String lang) {
        this.context = context;
        this.view = view;
        preference = Preferences.getInstance();
        userModel = preference.getUserData(context);
        this.lat = lat;
        this.lng = lng;
        this.lang = Paper.book().read("lang","ar");

    }



    public void getCategory()
    {
        view.onProgressCategoryShow();
        String type;
        if (lang.equals("ar")){
            type="2";
        }else {
            type="1";
        }
        Api.getService(Tags.base_url)
                .getCategory(type)
                .enqueue(new Callback<AllCategoryModel>() {
                    @Override
                    public void onResponse(Call<AllCategoryModel> call, Response<AllCategoryModel> response) {
                        view.onProgressCategoryHide();
                        if (response.isSuccessful()) {
                            if (response.body() != null  && response.body().getData() != null) {
                                view.onSuccess(response.body().getData());

                            }


                        } else {
                            view.onProgressCategoryHide();
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
                    public void onFailure(Call<AllCategoryModel> call, Throwable t) {
                        try {
                            view.onProgressCategoryHide();


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
    public void getSubCategory(int category_id)
    {
        view.onProgressSubCategoryShow();
        String type;
        if (lang.equals("ar")){
            type="2";
        }else {
            type="1";
        }
        Api.getService(Tags.base_url)
                .getProductsByAnyCategoryId(category_id)
                .enqueue(new Callback<CategoryDataModel>() {
                    @Override
                    public void onResponse(Call<CategoryDataModel> call, Response<CategoryDataModel> response) {
                        view.onProgressSubCategoryHide();
                        if (response.isSuccessful()) {
                            if (response.body() != null  && response.body().getData() != null) {
                                view.onSubCategorySuccess(response.body().getData());

                            }


                        } else {
                            view.onProgressSubCategoryHide();
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
                    public void onFailure(Call<CategoryDataModel> call, Throwable t) {
                        try {
                            view.onProgressSubCategoryHide();


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
