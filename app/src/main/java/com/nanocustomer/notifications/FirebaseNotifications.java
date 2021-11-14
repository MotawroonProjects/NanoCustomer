package com.nanocustomer.notifications;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.nanocustomer.R;
import com.nanocustomer.models.LogoutModel;
import com.nanocustomer.models.UserModel;
import com.nanocustomer.preferences.Preferences;
import com.nanocustomer.remote.Api;
import com.nanocustomer.tags.Tags;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirebaseNotifications extends FirebaseMessagingService {
    private Preferences preferences = Preferences.getInstance();
    private Map<String,String> map;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        map = remoteMessage.getData();

        for (String key :map.keySet()){
            Log.e("Key=",key+"_value="+map.get(key));
        }

    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        UserModel userModel = preferences.getUserData(this);
        if (userModel!=null)
        {
            Log.e("token",s);
            updateTokenFireBase(s);

        }

    }

    private void updateTokenFireBase(String token)
    {

        UserModel userModel = preferences.getUserData(this);

        if (userModel!=null){
            try {
                Api.getService(Tags.base_url)
                        .updateFirebaseToken(userModel.getData().getToken(),userModel.getData().getUser().getId(),token,"android")
                        .enqueue(new Callback<LogoutModel>() {
                            @Override
                            public void onResponse(Call<LogoutModel> call, Response<LogoutModel> response) {
                                if (response.isSuccessful() && response.body() != null&&response.body().getStatus()==200) {
                                    userModel.getData().getUser().setFirebase_token(token);
                                    preferences.create_update_userdata(FirebaseNotifications.this,userModel);

                                } else {
                                    try {

                                        Log.e("errorToken", response.code() + "_" + response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<LogoutModel> call, Throwable t) {
                                try {

                                    if (t.getMessage() != null) {
                                        Log.e("errorToken2", t.getMessage());
                                        if (t.getMessage().toLowerCase().contains("failed to connect") || t.getMessage().toLowerCase().contains("unable to resolve host")) {
                                            Toast.makeText(FirebaseNotifications.this, R.string.something, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(FirebaseNotifications.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                } catch (Exception e) {
                                }
                            }
                        });
            } catch (Exception e) {


            }
        }
    }

}
