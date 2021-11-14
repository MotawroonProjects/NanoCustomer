package com.nanocustomer.mvp.fragment_category_mvp;

import com.nanocustomer.models.CategoryModel;
import com.nanocustomer.models.ProductModel;
import com.nanocustomer.models.SingleCategoryModel;
import com.nanocustomer.models.SliderDataModel;
import com.nanocustomer.models.SubCategoryModel;

import java.util.List;

public interface FragmentCategoryView {
    void onSuccess(List<SingleCategoryModel> data);
    void onSubCategorySuccess(List<CategoryModel> data);
    void onFailed(String msg);
    void onProgressCategoryShow();
    void onProgressCategoryHide();
    void onProgressSubCategoryShow();
    void onProgressSubCategoryHide();
}
