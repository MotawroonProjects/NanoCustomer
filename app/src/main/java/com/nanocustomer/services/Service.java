package com.nanocustomer.services;


import com.nanocustomer.models.AddFavoriteDataModel;
import com.nanocustomer.models.AddressDataModel;
import com.nanocustomer.models.AllCategoryModel;
import com.nanocustomer.models.BottomImageDataModel;
import com.nanocustomer.models.CategoryDataModel;
import com.nanocustomer.models.CategorySubCategoryDataModel;
import com.nanocustomer.models.CouponDataModel;
import com.nanocustomer.models.LogoutModel;
import com.nanocustomer.models.MenuDataModel;
import com.nanocustomer.models.OrderDataModel;
import com.nanocustomer.models.PlaceGeocodeData;
import com.nanocustomer.models.PlaceMapDetailsData;
import com.nanocustomer.models.ProductDataModel;
import com.nanocustomer.models.ProductDataModel2;
import com.nanocustomer.models.SendCartModel;
import com.nanocustomer.models.SingleCommentDataModel;
import com.nanocustomer.models.SingleOrderModel;
import com.nanocustomer.models.SingleProductDataModel;
import com.nanocustomer.models.SliderDataModel;
import com.nanocustomer.models.SocialSettingsModel;
import com.nanocustomer.models.SubCategoryDataModel;
import com.nanocustomer.models.UserModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("place/findplacefromtext/json")
    Call<PlaceMapDetailsData> searchOnMap(@Query(value = "inputtype") String inputtype,
                                          @Query(value = "input") String input,
                                          @Query(value = "fields") String fields,
                                          @Query(value = "language") String language,
                                          @Query(value = "key") String key
    );

    @GET("geocode/json")
    Call<PlaceGeocodeData> getGeoData(@Query(value = "latlng") String latlng,
                                      @Query(value = "language") String language,
                                      @Query(value = "key") String key);

    @FormUrlEncoded
    @POST("api/user/registration")
    Call<UserModel> signUp(@Field("phone") String phone,
                           @Field("fullname") String fullname,
                           @Field("email") String email,
                           @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api/user/login")
    Call<UserModel> login(@Field("phone") String phone,
                          @Field("password") String password
    );


    @GET("api/front/sliders")
    Call<SliderDataModel> get_slider();

    @GET("api/front/categories")
    Call<AllCategoryModel> getCategory(@Query("lang") String lang);

    @GET("api/front/products")
    Call<ProductDataModel> getFeatureProducts(@Query("highlight") String highlight);


    @GET("api/front/products")
    Call<ProductDataModel> getMostSellerProducts(@Query("highlight") String highlight);

    @GET("api/front/products")
    Call<ProductDataModel> getOtherProducts(@Query("highlight") String highlight);

    @GET("api/front/products")
    Call<ProductDataModel> getOfferProducts(@Query("highlight") String highlight);

    @GET("api/user/sub-with-child")
    Call<SubCategoryDataModel> getSubCategoryByCategoryId(@Query("category_id") int category_id);

    @GET("api/user/get-product-by-name")
    Call<ProductDataModel> search(@Query("user_id") String user_id,
                                  @Query("search_name") String search_name
    );

    @GET("api/user/get-product-by-name")
    Call<ProductDataModel> getProducts(@Query("user_id") String user_id,
                                       @Query("category_id") String category_id,
                                       @Query("category_id") String subcategory_id,
                                       @Query("childcategory_id") String childcategory_id

    );

    @GET("api/user/get-product-and-sup")
    Call<ProductDataModel2> getProductsBySubCategory(@Query("sub_id") int sub_id,
                                                     @Query("user_id") String user_id


    );

    @GET("api/front/product/{id}/details")
    Call<SingleProductDataModel> getProductById(@Path("id") String id);

    @FormUrlEncoded
    @POST("api/user/action-wishlists")
    Call<AddFavoriteDataModel> add_remove_favorite(@Header("Authorization") String token,
                                                   @Field("user_id") String user_id,
                                                   @Field("product_id") String product_id
    );

    @GET("api/user/my-wishlists")
    Call<ProductDataModel> getMyFavorite(@Header("Authorization") String token,
                                         @Query("user_id") String user_id
    );

    @GET("api/user/my-address")
    Call<AddressDataModel> getMyAddress(@Header("Authorization") String token,
                                        @Query("user_id") String user_id
    );


    @FormUrlEncoded
    @POST("api/user/add-address")
    Call<ResponseBody> addAddress(@Header("Authorization") String token,
                                  @Field("user_id") String user_id,
                                  @Field("phone") String phone,
                                  @Field("name") String name,
                                  @Field("address") String address,
                                  @Field("google_lat") double google_lat,
                                  @Field("google_long") double google_long,
                                  @Field("type") String type
    );

    @FormUrlEncoded
    @POST("api/user/edit-address")
    Call<ResponseBody> updateAddress(@Header("Authorization") String token,
                                     @Field("id") String address_id,
                                     @Field("user_id") String user_id,
                                     @Field("phone") String phone,
                                     @Field("name") String name,
                                     @Field("address") String address,
                                     @Field("google_lat") double google_lat,
                                     @Field("google_long") double google_long,
                                     @Field("type") String type
    );

    @FormUrlEncoded
    @POST("api/user/delete-address")
    Call<ResponseBody> deleteAddress(@Header("Authorization") String token,
                                     @Field("id") String address_id
    );

    @FormUrlEncoded
    @POST("api/user/logout")
    Call<LogoutModel> logout(@Header("Authorization") String token,
                             @Field("user_id") int user_id,
                             @Field("phone_token") String phone_token,
                             @Field("software_type") String software_type

    );

    @FormUrlEncoded
    @POST("api/user/update-firebase")
    Call<LogoutModel> updateFirebaseToken(@Header("Authorization") String token,
                                          @Field("user_id") int user_id,
                                          @Field("phone_token") String phone_token,
                                          @Field("software_type") String software_type

    );

    @FormUrlEncoded
    @POST("api/user/StoreContact")
    Call<ResponseBody> contactUs(@Field("name") String name,
                                 @Field("email") String email,
                                 @Field("phone") String phone,
                                 @Field("text") String message
    );

    @FormUrlEncoded
    @POST("api/user/StoreComment")
    Call<SingleCommentDataModel> addComment(@Header("Authorization") String token,
                                            @Field("user_id") String user_id,
                                            @Field("product_id") String product_id,
                                            @Field("text") String message
    );


    @GET("api/user/GetMenu")
    Call<MenuDataModel> getMenu(@Header("Authorization") String token,
                                @Query("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("api/user/DeleteMenu")
    Call<LogoutModel> deleteITemMenu(@Header("Authorization") String token,
                                     @Field("menu_id") String menu_id
    );

    @FormUrlEncoded
    @POST("api/user/StoreMenu")
    Call<LogoutModel> addToMenu(@Header("Authorization") String token,
                                @Field("user_id") String user_id,
                                @Field("product_id") String product_id,
                                @Field("status") String status,
                                @Field("amount") int amount
    );

    @GET("api/user/GetCoupon")
    Call<CouponDataModel> checkCouponData(@Query("code") String code
    );


    @POST("api/user/StoreOrder")
    Call<SingleOrderModel> sendOrder(@Header("Authorization") String token,
                                     @Body SendCartModel body);


    @GET("api/user/GetOrders")
    Call<OrderDataModel> getOrders(@Header("Authorization") String token,
                                   @Query("user_id") String user_id
    );

    @GET("api/user/OneOrder")
    Call<SingleOrderModel> getSingleOrders(@Query("order_id") String order_id
    );

    @GET("api/front/{id}/category")
    Call<CategoryDataModel> getProductsByAnyCategoryId(@Path("id") int id

    );

    @GET("api/user/get-category-and-sup")
    Call<CategorySubCategoryDataModel> getCategorySubCategory();

    @GET("api/user/top-keys")
    Call<SliderDataModel> getTopImages();

    @GET("api/user/get-bottom-slider")
    Call<BottomImageDataModel> getBottomImages();

    @FormUrlEncoded
    @POST("api/user/update-profile")
    Call<UserModel> Editprofile(
            @Header("Authorization") String Authorization,
            @Field("user_id") String user_id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password

    );

    @Multipart
    @POST("api/user/update-profile")
    Call<UserModel> updateProfileWithImage(@Header("Authorization") String user_token,
                                           @Part("user_id") RequestBody user_id,
                                           @Part("name") RequestBody name,
                                           @Part("email") RequestBody email,
                                           @Part("password") RequestBody password,
                                           @Part MultipartBody.Part logo


    );

    @GET("api/user/products-by-key")
    Call<ProductDataModel> getProductsBytype(
                                             @Query("user_id") String user_id


    );

    @GET("api/user/socialSetting")
    Call<SocialSettingsModel> getSocialSetting();
}