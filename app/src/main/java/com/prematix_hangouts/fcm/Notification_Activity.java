package com.prematix_hangouts.fcm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.prematix_hangouts.ModleClass.MessageSave.SendMessageStore;
import com.prematix_hangouts.R;
import com.prematix_hangouts.adapter.ChatMessageAdapter;
import com.prematix_hangouts.adapter.NotficationMessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class Notification_Activity extends AppCompatActivity{
    List<Object> list = new ArrayList<>();
    SendMessageStore sendMessageStore;
    String message,type,title,g_message,g_type,g_title;
    RecyclerView recyclerView;
    NotficationMessageAdapter messageAdapter;
    boolean hasOpen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        Bundle b= getIntent().getExtras();
        if (b!= null){

            title = b.getString("Title");
            type = b.getString("Type");
            message = b.getString("Message");
            hasOpen = b.getBoolean("PageOpen");
            System.out.println("hasOpen****"+hasOpen);
            /*System.out.println("title****"+title);
            System.out.println("type****"+type);
            System.out.println("message****"+message);*/
        }
        list.add(MyFirebaseMessagingService.sendMessageStores);
     /*   System.out.println("sendMessageStore****"+list);
        System.out.println("sendMessageStore****"+MyFirebaseMessagingService.sendMessageStores.size());*/
        for (int i=0;i<MyFirebaseMessagingService.sendMessageStores.size();i++){
            g_message = MyFirebaseMessagingService.sendMessageStores.get(i).getMessage();
            g_title = MyFirebaseMessagingService.sendMessageStores.get(i).getTitle();
            g_type = MyFirebaseMessagingService.sendMessageStores.get(i).getType();


        }
        loadMessage(MyFirebaseMessagingService.sendMessageStores);

    }

    private void loadMessage(List<SendMessageStore> sendMessageStores) {
        recyclerView = (RecyclerView)findViewById(R.id.contact_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        messageAdapter = new  NotficationMessageAdapter  (this,sendMessageStores);
        recyclerView.setAdapter(messageAdapter);
    }


}
