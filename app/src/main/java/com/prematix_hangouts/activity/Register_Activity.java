package com.prematix_hangouts.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prematix_hangouts.ModleClass.RegisterModule;
import com.prematix_hangouts.R;
import com.prematix_hangouts.extra.ConstantClass;

public class Register_Activity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText Name, Email, Password, MobileNo;
    TextView SignUp;
    String name = "", email = "", password = "", mobileno = "";
    private DatabaseReference registerDBReference;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mFirebaseAuth;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        mFirebaseAuth = FirebaseAuth.getInstance();

        Name = (TextInputEditText) findViewById(R.id.name);
        Email = (TextInputEditText) findViewById(R.id.email);
        Password = (TextInputEditText) findViewById(R.id.password);
        MobileNo = (TextInputEditText) findViewById(R.id.mobileno);
        SignUp = (TextView) findViewById(R.id.signup);
        SignUp.setOnClickListener(this);

        sharedpreferences = getSharedPreferences("hangouts_preference", Context.MODE_PRIVATE);
        ConstantClass.REFERENCE_TOKEN  = sharedpreferences.getString("REFERENCE_TOKEN", "0");



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup:
                EntryForm();

                break;
        }
    }

    private void EntryForm() {
        System.out.println("ConstantClass.REFERENCE_TOKEN.Register**"+ConstantClass.REFERENCE_TOKEN);
        name = Name.getText().toString();
        email = Email.getText().toString();
        password = Password.getText().toString();
        mobileno = MobileNo.getText().toString();
        ConstantClass.CURRENTUSER_NAME = name;
        ConstantClass.CURRENTUSER_EMAIL = email;
        ConstantClass.CURRENTUSER_PASSWORD = password;
        ConstantClass.CURRENTUSER_MOBILENO = mobileno;
        String gender = "male";

        mFirebaseInstance = FirebaseDatabase.getInstance();
        registerDBReference = FirebaseDatabase.getInstance().getReference("new_user");

        String userId = registerDBReference.push().getKey();
        RegisterModule registerModule = new RegisterModule(userId, name, email, password, mobileno, gender, ConstantClass.REFERENCE_TOKEN);
        registerDBReference.child(userId).setValue(registerModule);


        sharedpreferences = getSharedPreferences("hangouts_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("C_NAME", name);
        editor.putString("C_EMAIL", email);
        editor.putString("C_PASSWORD", password);
        editor.putString("C_MOBILE", mobileno);
        editor.commit();
        Toast.makeText(Register_Activity.this, "Submitted successful", Toast.LENGTH_LONG).show();
        Intent i2 = new Intent(Register_Activity.this, Login_Activity.class);
        i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i2);

















/*
        mFirebaseInstance = FirebaseDatabase.getInstance();
        registerDBReference = FirebaseDatabase.getInstance().getReference("register");

        String userId = registerDBReference.push().getKey();
        RegisterModule registerModule = new RegisterModule(userId, name, email, password, mobileno, gender);
        registerDBReference.child(userId).setValue(registerModule);╥

        Toast.makeText(Register_Activity.this, "registration successful", Toast.LENGTH_LONG).show();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");

// Creating new user node, which returns the unique key value
// new user node would be /users/$userid/
String userId = mDatabase.push().getKey();

// creating user object
User user = new User("Ravi Tamada", "ravi@androidhive.info");

// pushing user to 'users' node using the userId
mDatabase.child(userId).setValue(user);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        registerDBReference = FirebaseDatabase.getInstance().getReference("registerModules");
        String userId = registerDBReference.push().getKey();
        RegisterModule registerModule = new RegisterModule(name,email,password,mobileno,gender);
        registerDBReference.child(userId).setValue(registerModule);*/


    }
}
