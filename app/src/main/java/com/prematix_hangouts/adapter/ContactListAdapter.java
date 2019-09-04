package com.prematix_hangouts.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prematix_hangouts.ModleClass.FriendList;
import com.prematix_hangouts.ModleClass.RegisterModule;
import com.prematix_hangouts.R;
import com.prematix_hangouts.activity.ChatMessage_Activity;
import com.prematix_hangouts.activity.Chat_ContactList_Activity;
import com.prematix_hangouts.extra.ConstantClass;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder> {
    private Context context;
    List<RegisterModule> registerModules = new ArrayList<>();
    String FName="";
    //List<FriendList> friendLists = new ArrayList<>();
   /* public ContactListAdapter(Context context, List<FriendList> friendLists) {
        this.context = context;
        this.friendLists = friendLists;
        notifyDataSetChanged();
    }*/
    public ContactListAdapter(Context context, List<RegisterModule> registerModules) {
        this.context = context;
        this.registerModules = registerModules;
        notifyDataSetChanged();

    }

    @Override
    public ContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_list_details, parent, false);
        ContactListAdapter.ContactListViewHolder contactViewHolder = new ContactListAdapter.ContactListViewHolder(view);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactListViewHolder holder,  final int position) {

        holder.UserName.setText(registerModules.get(position).getName());
        holder.Active_Time.setText(registerModules.get(position).getUserId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chat_intent = new Intent(context, ChatMessage_Activity.class);
                ConstantClass.USER_ToOKEN = registerModules.get(position).getUserToken();
                ConstantClass.CHATWITH_USERNAME = registerModules.get(position).getName();

                System.out.println("F_USER****LISTADAPTER"+ConstantClass.CURRENTUSER_NAME);
                System.out.println("T_USER****LISTADAPTER"+ConstantClass.CHATWITH_USERNAME);
                FName = registerModules.get(position).getName();
                ConstantClass.CHAT_WITH_FROM_TO= ConstantClass.CHATWITH_USERNAME+ConstantClass.CURRENTUSER_NAME;
                chat_intent.putExtra("CLICK_VALUES","have");
                chat_intent.putExtra("FRIEND_NAME",FName);
                chat_intent.putExtra("CURRENT_USERNAME",ConstantClass.CURRENTUSER_NAME);
                //chat_intent.putExtra("USER_SELECT","yes");

               // System.out.println("FConstat list"+ ConstantClass.REFERENCE_TOKEN);
                chat_intent.putExtra(ConstantClass.USER_ToOKEN,ConstantClass.USER_ToOKEN);
                chat_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(chat_intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return registerModules.size();
    }

    public class ContactListViewHolder extends RecyclerView.ViewHolder {
        public TextView UserName,Active_Time;
        public ContactListViewHolder(View itemView) {
            super(itemView);
            UserName =(TextView)itemView.findViewById(R.id.friend_name_tv);
            Active_Time =(TextView)itemView.findViewById(R.id.time_tv);
        }
    }
}
