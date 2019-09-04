package com.prematix_hangouts.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prematix_hangouts.ModleClass.ChatMessage;
import com.prematix_hangouts.ModleClass.RegisterModule;
import com.prematix_hangouts.R;
import com.prematix_hangouts.adapter.ChatMessageAdapter;
import com.prematix_hangouts.extra.ConstantClass;
import com.prematix_hangouts.videoCallModule.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class Login_Activity extends AppCompatActivity {
    TextView Register_Tv, Login_Tv,TASKFirst;
    private FirebaseAuth mFirebaseAuth;
    TextInputEditText Mobileno, Password;
    SharedPreferences sharedpreferences,logref;
    String RefToken = "", email = "", password = "";

    private DatabaseReference messageDBReference;
    private FirebaseDatabase mFirebaseInstance;
    RecyclerView recyclerView;
    ChatMessageAdapter chatMessageAdapter;
    DatabaseReference databasechatmessage;
    List<RegisterModule> registerModules = new ArrayList<>();
    String userid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        mFirebaseAuth = FirebaseAuth.getInstance();

        TASKFirst = (TextView) findViewById(R.id.taskui);
        Login_Tv = (TextView) findViewById(R.id.login_tv);
        Register_Tv = (TextView) findViewById(R.id.register_tv);
        Password = (TextInputEditText) findViewById(R.id.password);
        Mobileno = (TextInputEditText) findViewById(R.id.mobileno);
        System.out.println("NAME***LOGIN!@"+ConstantClass.CURRENTUSER_NAME);


        sharedpreferences = getSharedPreferences("hangouts_preference", Context.MODE_PRIVATE);
        RefToken = sharedpreferences.getString("REFERENCE_TOKEN", "0");

        ConstantClass.REFERENCE_TOKEN = RefToken;
        TASKFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(Login_Activity.this, ProductHome.class);
                startActivity(i2);
            }
        });


        Register_Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(i2);
               //finish();

            }
        });

        Login_Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Mobileno.getText().toString();
                password = Password.getText().toString();

                email = email.trim();
                password = password.trim();
                newLogin();
                // signIn(email,password);
               /* Intent i2= new Intent(Login_Activity.this,Chat_ContactList_Activity.class);
                startActivity(i2);*/

            }
        });

        databasechatmessage = FirebaseDatabase.getInstance().getReference("new_user");
    }



    private void newLogin() {
        databasechatmessage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                registerModules.clear();
                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()){
                    RegisterModule new_userData = noteSnapshot.getValue(RegisterModule.class);
                    registerModules.add(new_userData);

                }
                if (registerModules.size()!=0){

                     for (int i=0;i<registerModules.size();i++) {
                         if (Mobileno.getText().toString().equals(registerModules.get(i).getEmail())){
                              userid  = registerModules.get(i).getUserId();
                             ConstantClass.CURRENTUSER_NAME=registerModules.get(i).getName();
                             ConstantClass.CURRENTUSER_EMAIL=registerModules.get(i).getEmail();
                             ConstantClass.CURRENTUSER_PASSWORD=registerModules.get(i).getPassword();
                             ConstantClass.CURRENTUSER_MOBILENO=registerModules.get(i).getMobile();
                             ConstantClass.REFERENCE_TOKEN=registerModules.get(i).getUserToken();
                             System.out.println("BEFORE REFERENCE_TOKEN**"+ConstantClass.REFERENCE_TOKEN);

                         }
                     }
                    LoginUser(email,password);
                }else {
                    Toast.makeText(Login_Activity.this,"No have user ! Create new user",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ERROR", databaseError.getMessage());
            }
        });
    }

    private void LoginUser(String email, String password) {
        if (email.equals(ConstantClass.CURRENTUSER_EMAIL) && (password.equals(ConstantClass.CURRENTUSER_PASSWORD))){
            Toast.makeText(Login_Activity.this,"Submitted Successfull",Toast.LENGTH_SHORT).show();
            logref = getSharedPreferences("hangouts_preference", Context.MODE_PRIVATE);
            tokenupdate();
            SharedPreferences.Editor editor = logref.edit();

           /* ConstantClass.CURRENTUSER_NAME=logref.getString("C_NAME", "0");
            ConstantClass.CURRENTUSER_EMAIL=logref.getString("C_EMAIL", "0");
            ConstantClass.CURRENTUSER_PASSWORD=logref.getString("C_PASSWORD", "0");
            ConstantClass.CURRENTUSER_MOBILENO=logref.getString("C_MOBILE", "0");*/

            System.out.println("ConstantClass.CURRENTUSER_NAM****" + ConstantClass.CURRENTUSER_NAME);

            editor.putString("C_NAME", ConstantClass.CURRENTUSER_NAME);
            editor.putString("C_EMAIL", ConstantClass.CURRENTUSER_EMAIL);
            editor.putString("C_PASSWORD", ConstantClass.CURRENTUSER_PASSWORD);
            editor.putString("C_MOBILE", ConstantClass.CURRENTUSER_MOBILENO);
            editor.putString("F_TOKEN", ConstantClass.REFERENCE_TOKEN);
            editor.commit();

            Intent login_intent = new Intent(Login_Activity.this,Chat_ContactList_Activity.class);
            login_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(login_intent);
            finish();

        }else {
            Toast.makeText(Login_Activity.this,"No have user ! Create new user",Toast.LENGTH_SHORT).show();
        }
    }

    private void tokenupdate() {
        databasechatmessage.child(userid).child("userToken").setValue(ConstantClass.REFERENCE_TOKEN);

    }

    private void signIn(String email, String password) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("HEllo", "signInWithEmail:success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("OK", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login_Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }


                    }
                });
    }

}
