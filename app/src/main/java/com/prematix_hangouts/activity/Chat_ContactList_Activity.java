package com.prematix_hangouts.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prematix_hangouts.ModleClass.ChatWithFriend;
import com.prematix_hangouts.ModleClass.FriendList;
import com.prematix_hangouts.ModleClass.RegisterModule;
import com.prematix_hangouts.R;
import com.prematix_hangouts.adapter.ContactListAdapter;
import com.prematix_hangouts.extra.ConstantClass;

import java.util.ArrayList;
import java.util.List;

public class Chat_ContactList_Activity extends AppCompatActivity {
    View Toolbar_custom;
    ImageView Back_Button,Logout;
    TextView UserName;
    String user_Name="",ed_message;
    SharedPreferences sharedpreferences;
    List<RegisterModule> registerModules = new ArrayList<>();
    List<FriendList> friendLists = new ArrayList<>();
    DatabaseReference databaseArtists;

    private FirebaseUser user;
    RecyclerView recyclerView;
    ContactListAdapter contactListAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_contact_list_layout);

        Toolbar_custom = findViewById(R.id.cust_id);

        Logout = (ImageView)Toolbar_custom.findViewById(R.id.logout);
        Back_Button = (ImageView)Toolbar_custom.findViewById(R.id.back);
        UserName = (TextView)Toolbar_custom.findViewById(R.id.friend_name);


        sharedpreferences = getSharedPreferences("hangouts_preference", Context.MODE_PRIVATE);
        ConstantClass.CURRENTUSER_NAME=sharedpreferences.getString("C_NAME","0");
        ConstantClass.CURRENTUSER_EMAIL=sharedpreferences.getString("C_EMAIL","0");
        ConstantClass.CURRENTUSER_PASSWORD=sharedpreferences.getString("C_PASSWORD","0");
        ConstantClass.CURRENTUSER_MOBILENO=sharedpreferences.getString("C_MOBILE","0");
        ConstantClass.REFERENCE_TOKEN=sharedpreferences.getString("F_TOKEN","0");
        UserName.setText(ConstantClass.CURRENTUSER_NAME);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  con.getSharedPreferences("movilo_login", 0).edit().clear().commit();
                sharedpreferences = getSharedPreferences("hangouts_preference", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                //ConstantClass.CURRENTUSER_NAME ="";
                editor.clear();
                editor.commit();

                //settings = PreferenceManager.getDefaultSharedPreferences(context);

                //System.out.println("NAME***CCC"+ConstantClass.CURRENTUSER_NAME);
                Intent i2= new Intent(Chat_ContactList_Activity.this,Login_Activity.class);
                i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i2);
                finish();
            }
        });
        databaseArtists = FirebaseDatabase.getInstance().getReference("new_user");


    }

    @Override
    protected void onStart() {
        super.onStart();

        final ProgressDialog pd = new ProgressDialog(Chat_ContactList_Activity.this);
        pd.setMessage("Loading...");
        pd.show();
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pd.dismiss();
                registerModules.clear();
                friendLists.clear();
                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()){
                    RegisterModule note = noteSnapshot.getValue(RegisterModule.class);
                    registerModules.add(note);
                }
                for (int i=0;i<registerModules.size();i++){
                    if (!ConstantClass.CURRENTUSER_NAME.equals(registerModules.get(i).getName())){
                        friendLists.add(new FriendList(registerModules.get(i).getUserId(),registerModules.get(i).getName(),registerModules.get(i).getEmail(),
                                registerModules.get(i).getPassword(),registerModules.get(i).getMobile(),registerModules.get(i).getGender()));


                    }else {
                        registerModules.remove(i);
                    }

                }
                /*System.out.println("message********friendLists"+friendLists.size());
                System.out.println("message********registerModules"+registerModules.size());*/
                ContactListData(registerModules);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                pd.dismiss();
                Log.d("ERROR", databaseError.getMessage());
            }
        });
    }

    /*private void ContactListData(List<FriendList> friendLists) {
        recyclerView = (RecyclerView)findViewById(R.id.contact_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        contactListAdapter = new  ContactListAdapter (this,friendLists);
        recyclerView.setAdapter(contactListAdapter);
    }*/

    private void ContactListData(List<RegisterModule> registerModules) {
        recyclerView = (RecyclerView)findViewById(R.id.contact_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        contactListAdapter = new  ContactListAdapter (this,registerModules);
        recyclerView.setAdapter(contactListAdapter);
    }
}
