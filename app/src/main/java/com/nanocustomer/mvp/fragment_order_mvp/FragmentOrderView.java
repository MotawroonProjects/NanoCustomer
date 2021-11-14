package com.nanocustomer.mvp.fragment_order_mvp;

import com.nanocustomer.models.CommentModel;
import com.nanocustomer.models.OrderModel;

import java.util.List;

public interface FragmentOrderView {
    void onSuccess(List<OrderModel> data);
    void onFailed(String msg);
    void onProgressShow();
    void onProgressHide();

}
