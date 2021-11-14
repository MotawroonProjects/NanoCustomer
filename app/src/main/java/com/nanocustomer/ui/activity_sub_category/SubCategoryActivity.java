package com.nanocustomer.ui.activity_sub_category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nanocustomer.R;
import com.nanocustomer.adapters.ProductAdapter2;
import com.nanocustomer.adapters.SubCategories2Adapter;
import com.nanocustomer.databinding.ActivityProductsBinding;
import com.nanocustomer.databinding.ActivitySubCategoryBinding;
import com.nanocustomer.language.Language;
import com.nanocustomer.models.CategorySubCategoryModel;
import com.nanocustomer.models.ProductDataModel;
import com.nanocustomer.models.SubCategoryModel;
import com.nanocustomer.mvp.activity_product_mvp.ActivityProductPresenter;
import com.nanocustomer.remote.Api;
import com.nanocustomer.tags.Tags;
import com.nanocustomer.ui.activity_products.ProductsActivity;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryActivity extends AppCompatActivity {

    private ActivitySubCategoryBinding binding;
    private String lang;
    private CategorySubCategoryModel categorySubCategoryModel;
    private List<SubCategoryModel> subCategoryModelList;
    private SubCategories2Adapter adapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sub_category);
        getDataFromIntent();
        initView();

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        categorySubCategoryModel = (CategorySubCategoryModel) intent.getSerializableExtra("data");

    }

    private void initView() {
        subCategoryModelList = new ArrayList<>();
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        binding.setModel(categorySubCategoryModel);
        binding.recView.setLayoutManager(new GridLayoutManager(this,2));
        subCategoryModelList.addAll(categorySubCategoryModel.getSubs());
        adapter = new SubCategories2Adapter(subCategoryModelList, this);
        binding.recView.setAdapter(adapter);

        if (subCategoryModelList.size() > 0) {
            binding.tvNoData.setVisibility(View.GONE);
        } else {
            binding.tvNoData.setVisibility(View.VISIBLE);

        }


        binding.llBack.setOnClickListener(view -> finish());

    }


    public void setItemModel(SubCategoryModel subCategoryModel) {
        Intent intent = new Intent(this, ProductsActivity.class);
        intent.putExtra("data", subCategoryModel);
        startActivity(intent);
    }
}