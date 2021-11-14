package com.nanocustomer.mvp.activity_order_details_mvp;

import com.nanocustomer.models.CartDataModel;
import com.nanocustomer.models.ProductModel;
import com.nanocustomer.models.SingleOrderModel;

public interface ActivityOrderDetailsView {

    void onSuccess(SingleOrderModel data);

    void onFailed(String msg);

    void onProgressShow();

    void onProgressHide();


}
