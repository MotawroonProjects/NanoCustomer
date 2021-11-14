package com.nanocustomer.mvp.activity_my_address_mvp;

import com.nanocustomer.models.AddressModel;
import com.nanocustomer.models.ProductModel;

import java.util.List;

public interface ActivityMyAddressView {
    void onSuccess(List<AddressModel> data);
    void onFailed(String msg);
    void onProgressShow();
    void onProgressHide();
    void onRemovedSuccess();


}
