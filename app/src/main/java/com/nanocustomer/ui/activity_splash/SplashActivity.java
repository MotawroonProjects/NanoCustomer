package com.nanocustomer.ui.activity_splash;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.nanocustomer.R;
import com.nanocustomer.databinding.ActivitySplashBinding;
import com.nanocustomer.language.Language;
import com.nanocustomer.models.UserModel;
import com.nanocustomer.preferences.Preferences;
import com.nanocustomer.ui.activity_home.HomeActivity;
import com.nanocustomer.ui.activity_login.LoginActivity;

import io.paperdb.Paper;
public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    private Preferences preferences;
    private UserModel userModel;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Transition transition = new TransitionSet();
            transition.setInterpolator(new LinearInterpolator());
            transition.setDuration(500);
            getWindow().setEnterTransition(transition);
            getWindow().setExitTransition(transition);

        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        initView();
    }

    private void initView() {
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        new Handler().postDelayed(() -> {
            Intent intent;
            if (userModel == null) {
                intent = new Intent(this, LoginActivity.class);
            } else {
                intent = new Intent(this, HomeActivity.class);
            }
            startActivity(intent);
            finish();
        }, 2000);
    }

}






