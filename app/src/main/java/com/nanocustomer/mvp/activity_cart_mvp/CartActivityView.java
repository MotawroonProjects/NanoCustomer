package com.nanocustomer.mvp.activity_cart_mvp;


import com.nanocustomer.models.CartDataModel;
import com.nanocustomer.models.CouponDataModel;
import com.nanocustomer.models.SingleOrderModel;

public interface CartActivityView {
    void onFinished();
    void onCheckOut();
    void onDataSuccess(CartDataModel cartDataModel);
    void onCartItemRemoved(int pos);
    void onCostUpdate(double totalItemCost,double discount,double totalCost);
    void onFailed(String msg);
    void onCouponSuccess(CouponDataModel.CouponModel couponModel);
    void onCouponFailed();
    void onDeliveryPriceSuccess(double cost);
    void onPackagingPriceSuccess(double cost);
    void onPaymentSuccess(int method);

    void onOrderSendSuccessfully(SingleOrderModel singleOrderModel);


}
