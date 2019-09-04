package com.prematix_hangouts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prematix_hangouts.ModleClass.ChatMessage;
import com.prematix_hangouts.ModleClass.ChatWithFriend;
import com.prematix_hangouts.ModleClass.MessageSave.SendMessageStore;
import com.prematix_hangouts.R;
import com.prematix_hangouts.activity.ChatMessage_Activity;
import com.prematix_hangouts.extra.ConstantClass;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ChatMessageHolder> {
    List<SendMessageStore> sendMessageStores = new ArrayList<>();
    private Context context;
    String typevalues="",F_USER="",T_USER="";
   // List<ChatWithFriend> chatWithFriends = new ArrayList<>();

    public ChatMessageAdapter(Context context, List<SendMessageStore> sendMessageStores) {
        this.context = context;
        this.sendMessageStores = sendMessageStores;
        notifyDataSetChanged();
    }

    @Override
    public ChatMessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chatmessage_list_details, parent, false);
        ChatMessageAdapter.ChatMessageHolder chatmessageViewHolder = new ChatMessageAdapter.ChatMessageHolder(view);
        return chatmessageViewHolder;
    }

    @Override
    public void onBindViewHolder(ChatMessageHolder holder, int position) {
        typevalues = sendMessageStores.get(position).getType();
        F_USER = sendMessageStores.get(position).getF_user();
        T_USER = sendMessageStores.get(position).getT_user();
        System.out.println("F_USER*** MEssageAdapter "+F_USER);
        System.out.println("T_USER***MEssageAdapter  "+T_USER);
        ConstantClass.CHAT_WITH_FROM_TO =T_USER+F_USER;
        System.out.println("ConstantClass.CHAT_WITH_FROM_TO MessageAdapter***"+ConstantClass.CHAT_WITH_FROM_TO);
        System.out.println("ConstantClass.CHAT_WITH_FROM_TO_NOTI**Adapter "+ConstantClass.CHAT_WITH_FROM_TO_NOTI);

        if (typevalues.equals("received")){
            holder.Receive_message.setText(sendMessageStores.get(position).getMessage());
            holder.ReceiveLinear.setVisibility(View.VISIBLE);
            holder.SendLinear.setVisibility(View.GONE);


        }else {

            holder.SendLinear.setVisibility(View.VISIBLE);
            holder.ReceiveLinear.setVisibility(View.GONE);
            holder.Send_Message.setText(sendMessageStores.get(position).getMessage());


        }





    }


    @Override
    public int getItemCount() {
        return sendMessageStores.size();
    }

    public class ChatMessageHolder extends RecyclerView.ViewHolder {
        public TextView Send_Message, Active_Time,Receive_message;
        LinearLayout SendLinear,ReceiveLinear;

        public ChatMessageHolder(View itemView) {
            super(itemView);
            Send_Message = (TextView) itemView.findViewById(R.id.message_tv);
            Receive_message = (TextView) itemView.findViewById(R.id.message_tv_r);
            Active_Time = (TextView) itemView.findViewById(R.id.time_tv);
            ReceiveLinear = (LinearLayout) itemView.findViewById(R.id.receive_linear);
            SendLinear = (LinearLayout) itemView.findViewById(R.id.send_linear);
        }
    }
}
