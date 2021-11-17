package com.nanocustomer.ui.activity_order_steps;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.nanocustomer.R;
import com.nanocustomer.adapters.CartAdapter;
import com.nanocustomer.databinding.ActivityCartBinding;
import com.nanocustomer.databinding.ActivityOrderStepsBinding;
import com.nanocustomer.language.Language;
import com.nanocustomer.models.BankDataModel;
import com.nanocustomer.models.OrderModel;
import com.nanocustomer.models.SingleOrderModel;
import com.nanocustomer.mvp.activity_cart_mvp.ActivityCartPresenter;
import com.nanocustomer.mvp.activity_cart_mvp.CartActivityView;
import com.nanocustomer.mvp.activity_order_steps_mvp.ActivityOrderStepsPresenter;
import com.nanocustomer.mvp.activity_order_steps_mvp.OrderStepsActivityView;

import io.paperdb.Paper;

public class OrderStepsActivity extends AppCompatActivity implements OrderStepsActivityView {
    private ActivityOrderStepsBinding binding;
    private ActivityOrderStepsPresenter presenter;
    private String lang;
    private OrderModel orderModel;


    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_steps);
        getDataFromIntent();
        initView();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        orderModel = (OrderModel) intent.getSerializableExtra("data");

    }

    private void initView() {

        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);

        presenter = new ActivityOrderStepsPresenter(this, this);

    }
    private void updateui1() {
      //  binding.fl.setBackground(getResources().getDrawable(R.drawable.rounded_strock_primary));
        binding.image1.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        binding.tv1.setTextColor(getResources().getColor(R.color.colorPrimary));

       // binding.fl1.setBackground(getResources().getDrawable(R.drawable.rounded_strock_gray7));
        binding.image2.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_IN);
        binding.tv2.setTextColor(getResources().getColor(R.color.black));

     //   binding.fl2.setBackground(getResources().getDrawable(R.drawable.rounded_strock_gray7));
        binding.image3.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_IN);
        binding.tv3.setTextColor(getResources().getColor(R.color.black));


       // binding.fl4.setBackground(getResources().getDrawable(R.drawable.rounded_strock_gray7));
        binding.image5.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_IN);
        binding.tv5.setTextColor(getResources().getColor(R.color.black));
    }

    private void updateui2() {
       // binding.fl1.setBackground(getResources().getDrawable(R.drawable.rounded_strock_primary));
        binding.image1.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        binding.tv1.setTextColor(getResources().getColor(R.color.colorPrimary));

       // binding.fl.setBackground(getResources().getDrawable(R.drawable.rounded_strock_gray7));
        binding.image2.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_IN);
        binding.tv2.setTextColor(getResources().getColor(R.color.black));

       // binding.fl2.setBackground(getResources().getDrawable(R.drawable.rounded_strock_gray7));
        binding.image3.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_IN);
        binding.tv3.setTextColor(getResources().getColor(R.color.black));



       // binding.fl4.setBackground(getResources().getDrawable(R.drawable.rounded_strock_gray7));
        binding.image5.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_IN);
        binding.tv5.setTextColor(getResources().getColor(R.color.black));
    }

    private void updateui3() {

        // binding.fl1.setBackground(getResources().getDrawable(R.drawable.rounded_strock_primary));
        binding.image1.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        binding.tv1.setTextColor(getResources().getColor(R.color.colorPrimary));

        // binding.fl.setBackground(getResources().getDrawable(R.drawable.rounded_strock_gray7));
        binding.image2.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_IN);
        binding.tv2.setTextColor(getResources().getColor(R.color.black));

        // binding.fl2.setBackground(getResources().getDrawable(R.drawable.rounded_strock_gray7));
        binding.image3.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_IN);
        binding.tv3.setTextColor(getResources().getColor(R.color.black));



        // binding.fl4.setBackground(getResources().getDrawable(R.drawable.rounded_strock_gray7));
        binding.image5.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_IN);
        binding.tv5.setTextColor(getResources().getColor(R.color.black));

    }
    @Override
    public void onBackPressed() {
        presenter.backPress();
    }


    @Override
    public void onFinished() {
        finish();
    }

    @Override
    public void onSuccess(SingleOrderModel data) {
        binding.viewback.setVisibility(View.GONE);
        orderModel = data.getOrder();

    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProgressShow() {
        binding.viewback.setVisibility(View.VISIBLE);
        binding.progBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProgressHide() {
        binding.progBar.setVisibility(View.GONE);

    }


}