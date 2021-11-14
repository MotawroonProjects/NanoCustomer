package com.nanocustomer.mvp.activity_product_mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.nanocustomer.R;
import com.nanocustomer.models.AddFavoriteDataModel;
import com.nanocustomer.models.CartDataModel;
import com.nanocustomer.models.LogoutModel;
import com.nanocustomer.models.ProductDataModel;
import com.nanocustomer.models.ProductDataModel2;
import com.nanocustomer.models.ProductModel;
import com.nanocustomer.models.UserModel;
import com.nanocustomer.mvp.fragment_search_mvp.FragmentSearchView;
import com.nanocustomer.preferences.Preferences;
import com.nanocustomer.remote.Api;
import com.nanocustomer.share.Common;
import com.nanocustomer.tags.Tags;
import com.nanocustomer.ui.activity_search.SearchActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityProductPresenter {
    private final String user_id;
    private Context context;
    private ActivityProductView view;
    private Preferences preference;
    private UserModel userModel;
    String category_id = "all", subCategory_id = "all", child_id = "all";
    private List<CartDataModel.CartModel> cartModelList;
    private CartDataModel cartDataModel;
    public ActivityProductPresenter(Context context, ActivityProductView view) {
        this.context = context;
        this.view = view;
        preference = Preferences.getInstance();
        userModel = preference.getUserData(context);
        if(userModel!=null){
            user_id=userModel.getData().getUser().getId()+"";
        }
        else {
            user_id="all";
        }
        cartDataModel = preference.getCartData(context);

        if (cartDataModel==null){

            cartModelList = new ArrayList<>();
            cartDataModel = new CartDataModel();
            cartDataModel.setCartModelList(cartModelList);
        }else {
            if (cartDataModel.getCartModelList()==null){
                cartModelList = new ArrayList<>();

            }else {
                cartModelList = cartDataModel.getCartModelList();

            }

        }
    }


    public void getProducts(int subCategory_id) {
        Log.e("sub_id", subCategory_id + "_");
        view.onProgressShow();
        Api.getService(Tags.base_url)
                .getProductsBySubCategory(subCategory_id,user_id)
                .enqueue(new Callback<ProductDataModel2>() {
                    @Override
                    public void onResponse(Call<ProductDataModel2> call, Response<ProductDataModel2> response) {
                        view.onProgressHide();
                        if (response.isSuccessful()) {
                            if (response.body() != null && response.body().getStatus() == 200 && response.body().getData() != null) {
                                view.onSuccess(response.body().getData().getData());
                                Log.e("ddd", "ddd");

                            } else {
                                Log.e("mm", "mm");

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
                    public void onFailure(Call<ProductDataModel2> call, Throwable t) {
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
    public void getProductsbytype() {
        Log.e("sub_id", subCategory_id + "_");
        view.onProgressShow();
        Api.getService(Tags.base_url)
                .getProductsBytype(user_id)
                .enqueue(new Callback<ProductDataModel>() {
                    @Override
                    public void onResponse(Call<ProductDataModel> call, Response<ProductDataModel> response) {
                        view.onProgressHide();
                        if (response.isSuccessful()) {
                            if (response.body() != null && response.body().getData() != null) {
                                view.onSuccess(response.body().getData().getData());
                                Log.e("ddd", "ddd");

                            } else {
                                Log.e("mm", "mm");

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
                    public void onFailure(Call<ProductDataModel> call, Throwable t) {
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

    public void getSearch(String query) {
        String user_id = "all";
        if (userModel != null) {
            user_id = String.valueOf(userModel.getData().getUser().getId());
        }
        view.onProgressShow();
        Api.getService(Tags.base_url)
                .search(user_id, query)
                .enqueue(new Callback<ProductDataModel>() {
                    @Override
                    public void onResponse(Call<ProductDataModel> call, Response<ProductDataModel> response) {
                        view.onProgressHide();
                        if (response.isSuccessful()) {
                            if (response.body() != null&& response.body().getData() != null) {
                                view.onSuccess(response.body().getData().getData());

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
                    public void onFailure(Call<ProductDataModel> call, Throwable t) {
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

    public void add_to_menu(ProductModel productModel, int amount)
    {
        if (userModel == null) {
            return;
        }
        ProgressDialog dialog = Common.createProgressDialog(context,context.getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        String user_id = String.valueOf(userModel.getData().getUser().getId());
        Api.getService(Tags.base_url)
                .addToMenu(userModel.getData().getToken(),user_id, String.valueOf(productModel.getId()),"no",amount)
                .enqueue(new Callback<LogoutModel>() {
                    @Override
                    public void onResponse(Call<LogoutModel> call, Response<LogoutModel> response) {
                        dialog.dismiss();
                        if (response.isSuccessful()) {
                            if (response.body() != null&&response.body().getStatus()==200) {
                                view.onAddToMenuSuccess();
                            }else {
                                view.onFailed(context.getString(R.string.failed));

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
                    public void onFailure(Call<LogoutModel> call, Throwable t) {
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

    public void add_to_cart(ProductModel productModel,int amount)
    {
        int pos = isProductItemSelected(productModel);

        if (pos==-1){

            CartDataModel.CartModel cartModel = new CartDataModel.CartModel(String.valueOf(productModel.getId()),productModel.getThumbnail(),productModel.getTitle(),amount,productModel.getCurrent_price());
            cartModelList.add(cartModel);
        }else {
            CartDataModel.CartModel cartModel = cartModelList.get(pos);
            cartModel.setAmount(amount);
            cartModelList.set(pos,cartModel);
        }
        if (cartDataModel==null){
            cartDataModel = new CartDataModel();
        }

        cartDataModel.setCartModelList(cartModelList);

        calculateTotalCost();
    }
    private void calculateTotalCost() {
        double total =0.0;
        for (CartDataModel.CartModel cartModel:cartModelList){
            total += cartModel.getAmount()*cartModel.getCost();
        }
        cartDataModel.setTotal(total);
        preference.createUpdateCartData(context,cartDataModel);
        view.onCartUpdated(total,cartModelList.size(),cartModelList);
    }


    public int isProductItemSelected(ProductModel productModel){

        int pos = -1;

        cartDataModel = preference.getCartData(context);
        if (cartDataModel!=null&&cartDataModel.getCartModelList()!=null)
        {
            cartModelList = cartDataModel.getCartModelList();
            for (int index =0;index<cartModelList.size();index++){
                CartDataModel.CartModel cartModel = cartModelList.get(index);
                if (String.valueOf(productModel.getId()).equals(cartModel.getId())){
                    pos = index;
                    return pos;
                }
            }
        }


        return pos;
    }

    public void getCartCount(){
        if (cartDataModel!=null&&cartDataModel.getCartModelList()!=null){
            view.onCartCountUpdated(cartDataModel.getCartModelList().size());

        }else {
            view.onCartCountUpdated(0);

        }
    }

    public void getItemAmount(ProductModel productModel){
        int pos = isProductItemSelected(productModel);
        if (pos==-1){
            view.onAmountSelectedFromCart(1);
        }else {
            view.onAmountSelectedFromCart(cartDataModel.getCartModelList().get(pos).getAmount());
        }

    }
    public void remove_favorite(ProductModel productModel)
    {

        if (userModel==null){
            return;
        }
        ProgressDialog dialog = Common.createProgressDialog(context,context.getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        String user_id = String.valueOf(userModel.getData().getUser().getId());
        Api.getService(Tags.base_url)
                .add_remove_favorite(userModel.getData().getToken(),user_id, String.valueOf(productModel.getId()))
                .enqueue(new Callback<AddFavoriteDataModel>() {
                    @Override
                    public void onResponse(Call<AddFavoriteDataModel> call, Response<AddFavoriteDataModel> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            if (response.body() != null) {
                                view.onRemoveFavoriteSuccess();
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
                    public void onFailure(Call<AddFavoriteDataModel> call, Throwable t) {
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
    public void add_remove_favorite(ProductModel productModel, int position, String type)
    {
        //Log.e("hdhhdh",productModel.getName());
//        if (userModel == null) {
//            if (productModel.getIs_wishlist()!=null){
//                productModel.setIs_wishlist(null);
//            }else {
//                productModel.setIs_wishlist(new ProductModel.IsWishList());
//            }
//            view.onUserNotRegister(context.getString(R.string.pls_signin_signup),productModel,position,type);
//            return;
//        }
        String user_id = String.valueOf(userModel.getData().getUser().getId());
        Api.getService(Tags.base_url)
                .add_remove_favorite(userModel.getData().getToken(),user_id, String.valueOf(productModel.getId()))
                .enqueue(new Callback<AddFavoriteDataModel>() {
                    @Override
                    public void onResponse(Call<AddFavoriteDataModel> call, Response<AddFavoriteDataModel> response) {
                        Log.e("lkkdkdk",response.code()+"");
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                               // productModel.setIs_wishlist(response.body().getData());
                                view.onFavoriteActionSuccess(productModel,position,type);
                            }


                        } else {

                            try {
                                Log.e("errorNotCode", response.code() + "__" + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

//                            if (productModel.getIs_wishlist()!=null){
//                                productModel.setIs_wishlist(null);
//                            }else {
//                                productModel.setIs_wishlist(new ProductModel.IsWishList());
//                            }
                            view.onFavoriteActionSuccess(productModel,position,type);

                            if (response.code() == 500) {
                                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                            } else {
                                view.onFailed(context.getString(R.string.failed));

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddFavoriteDataModel> call, Throwable t) {
                        try {
//                            if (productModel.getIs_wishlist()!=null){
//                                productModel.setIs_wishlist(null);
//                            }else {
//                                productModel.setIs_wishlist(new ProductModel.IsWishList());
//                            }
                            view.onFavoriteActionSuccess(productModel,position,type);
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
