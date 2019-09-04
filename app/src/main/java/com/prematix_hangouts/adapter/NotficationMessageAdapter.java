package com.prematix_hangouts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prematix_hangouts.ModleClass.MessageSave.SendMessageStore;
import com.prematix_hangouts.R;
import com.prematix_hangouts.fcm.Notification_Activity;

import java.util.ArrayList;
import java.util.List;

public class NotficationMessageAdapter extends RecyclerView.Adapter<NotficationMessageAdapter.NotificationMessageHolder> {
    List<SendMessageStore> sendMessageStores = new ArrayList<>();
    private Context context;
    String typevalues="";
    public NotficationMessageAdapter( Context context, List<SendMessageStore> sendMessageStores) {
        this.context = context;
        this.sendMessageStores = sendMessageStores;
        notifyDataSetChanged();
    }

    @Override
    public NotificationMessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chatmessage_list_details, parent, false);
        NotficationMessageAdapter.NotificationMessageHolder chatmessageViewHolder = new NotficationMessageAdapter.NotificationMessageHolder(view);
        return chatmessageViewHolder;
    }

    @Override
    public void onBindViewHolder(NotificationMessageHolder holder, int position) {
        typevalues = sendMessageStores.get(position).getType();
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

    public class NotificationMessageHolder extends RecyclerView.ViewHolder {
        public TextView Send_Message, Active_Time,Receive_message;
        LinearLayout SendLinear,ReceiveLinear;

        public NotificationMessageHolder(View itemView) {
            super(itemView);
            Send_Message = (TextView) itemView.findViewById(R.id.message_tv);
            Receive_message = (TextView) itemView.findViewById(R.id.message_tv_r);
            Active_Time = (TextView) itemView.findViewById(R.id.time_tv);
            ReceiveLinear = (LinearLayout) itemView.findViewById(R.id.receive_linear);
            SendLinear = (LinearLayout) itemView.findViewById(R.id.send_linear);
        }
    }
}
