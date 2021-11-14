package com.nanocustomer.models;

import android.content.Context;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;


import com.nanocustomer.BR;
import com.nanocustomer.R;

import java.io.Serializable;

public class LoginModel extends BaseObservable implements Serializable {
    private String email;
    private String password;
    public ObservableField<String> error_email = new ObservableField<>();
    public ObservableField<String> error_password = new ObservableField<>();

    public LoginModel() {
        email ="";
        password = "";
    }

    public boolean isDataValid(Context context){
        if ( !email.isEmpty() &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches() &&!password.isEmpty()){
            error_email.set(null);
            error_password.set(null);
            return true;
        }else {

            if (email.isEmpty()){
                error_email.set(context.getString(R.string.field_req));

            }else {
                error_email.set(null);

            }

            if (password.isEmpty()){
                error_password.set(context.getString(R.string.field_req));
            }else {
                error_password.set(null);
            }
            return false;
        }
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.phone);

    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}