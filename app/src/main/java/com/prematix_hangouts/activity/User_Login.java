package com.prematix_hangouts.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.prematix_hangouts.R;
import com.prematix_hangouts.api.ApiClient;
import com.prematix_hangouts.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Login extends AppCompatActivity {
    TextView Register_Tv, Login_Tv;
    TextInputEditText Mobileno, Password;
    SharedPreferences sharedpreferences;
    String RefToken = "", email = "vinaykumar1225876@gmail.com", password = "vinaykumar1987";
ApiInterface apiInterface;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Login_Tv = (TextView) findViewById(R.id.login_tv);
        Register_Tv = (TextView) findViewById(R.id.register_tv);
        Password = (TextInputEditText) findViewById(R.id.password);
        Mobileno = (TextInputEditText) findViewById(R.id.mobileno);

        Login_Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Response*****");
                String url = "https://androidchatapp-76776.firebaseio.com/user";
                Call<String> call = apiInterface.getusers();
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        System.out.println("Response*****"+response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });



            }
        });
    }
}
