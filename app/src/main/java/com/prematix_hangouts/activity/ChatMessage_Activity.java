package com.prematix_hangouts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.prematix_hangouts.ModleClass.ChatMessage;
import com.prematix_hangouts.ModleClass.ChatWithFriend;
import com.prematix_hangouts.ModleClass.MessageData;
import com.prematix_hangouts.ModleClass.MessageSave.SendMessageStore;
import com.prematix_hangouts.ModleClass.PostNotificationModel;
import com.prematix_hangouts.R;
import com.prematix_hangouts.ResponseModel.PostNotResponse;
import com.prematix_hangouts.adapter.ChatMessageAdapter;
import com.prematix_hangouts.api.ApiClient;
import com.prematix_hangouts.api.ApiInterface;
import com.prematix_hangouts.api.NotificationInterface;
import com.prematix_hangouts.api.PaypreApiClient;
import com.prematix_hangouts.extra.ConstantClass;
import com.prematix_hangouts.fcm.MyFirebaseMessagingService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatMessage_Activity extends AppCompatActivity implements View.OnClickListener {
    View Toolbar_custom;
    ImageView Back_Button;
    TextView FriendName;
    String friend_Name = "", ed_message;
    EditText Edit_Message;
    ImageView Send_Message, Attached;
    private DatabaseReference messageDBReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseInstance;
    RecyclerView recyclerView;
    ChatMessageAdapter chatMessageAdapter;
    DatabaseReference databasechatmessage;
    List<ChatMessage> chatMessageslist = new ArrayList<>();
    List<ChatWithFriend> chatWithFriends = new ArrayList<>();
    NotificationInterface notificationInterface;
    PostNotificationModel postNotificationModel = new PostNotificationModel();
    MessageData messageData = new MessageData();
    public static  List<SendMessageStore> sendMessageStores = new ArrayList<>();
    String toToken,chat_with_user;
    ImageView Step_Count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_message_layout);
        mFirebaseAuth = FirebaseAuth.getInstance();
        Toolbar_custom = findViewById(R.id.cust_id);


        Back_Button = (ImageView) Toolbar_custom.findViewById(R.id.back);
        Send_Message = (ImageView) findViewById(R.id.send_im);
        Attached = (ImageView) findViewById(R.id.attached);
        Step_Count = (ImageView) findViewById(R.id.step_count);
        FriendName = (TextView) Toolbar_custom.findViewById(R.id.friend_name);
        Edit_Message = (EditText) findViewById(R.id.message_ed);
        Send_Message.setOnClickListener(this);
        Attached.setOnClickListener(this);
        Step_Count.setOnClickListener(this);
        notificationInterface = PaypreApiClient.getClient().create(NotificationInterface.class);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            if (b.getString("CLICK_VALUES").equals("have")){
                friend_Name = b.getString("FRIEND_NAME");
                toToken = b.getString("To_Token");
                ConstantClass.CURRENTUSER_NAME =b.getString("CURRENT_USERNAME");
                FriendName.setText(friend_Name);
                ConstantClass.CHATWITH_USERNAME = friend_Name;
                sendMessageStores.clear();
            }else {
                String F_user_Noti= b.getString("f_user");
                String T_user_Noti= b.getString("t_user");
                ConstantClass.CHAT_WITH_FROM_TO_NOTI =T_user_Noti+F_user_Noti;
                System.out.println("F_USER***NOtify" +F_user_Noti);
                System.out.println("T_USER***NOtify" +T_user_Noti);
                System.out.println("ConstantClass.CHAT_WITH_FROM_TO_NOTI***NOtify" +ConstantClass.CHAT_WITH_FROM_TO_NOTI);
                friend_Name = F_user_Noti;
                ConstantClass.CHATWITH_USERNAME = friend_Name;
            }






        }
        System.out.println("ConstantClass.CHAT_WITH_FROM_TO.MessagePage" +ConstantClass.CHAT_WITH_FROM_TO);
       // System.out.println("MyFirebaseMessagingService.sendMessageStores.size()" + MyFirebaseMessagingService.sendMessageStores.size());
        /*sendMessageStores.add(new SendMessageStore(ConstantClass.REFERENCE_TOKEN,"P_Hangouts",ConstantClass.Message,ConstantClass.TYPE));
        LoadMessageMethod(sendMessageStores);*/

        databasechatmessage = FirebaseDatabase.getInstance().getReference("chatWithFriendMessage");
        LoadMessageMethod(MyFirebaseMessagingService.sendMessageStores);

    }

    @Override
    protected void onStart() {
        super.onStart();
        databasechatmessage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chatMessageslist.clear();
                chatWithFriends.clear();
                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    ChatMessage chatmessagelistdata = noteSnapshot.getValue(ChatMessage.class);
                    chatMessageslist.add(chatmessagelistdata);

                }
                if (chatMessageslist.size() != 0) {
                    for (int i = 0; i < chatMessageslist.size(); i++) {
                        System.out.println("USer NAme Message***" + ConstantClass.CURRENTUSER_NAME);
                        System.out.println("ChatWith USer Message***" + ConstantClass.CHATWITH_USERNAME);
                       /* if (friend_Name.equals(chatMessageslist.get(i).getChatWith())){
                            chatWithFriends.add(new ChatWithFriend(chatMessageslist.get(i).getUserId(),chatMessageslist.get(i).getUserName(),
                                    chatMessageslist.get(i).getMessage(),chatMessageslist.get(i).getRefToken(),chatMessageslist.get(i).getChatWith()));
                        }*/
                    }
                    System.out.println("message********" + chatWithFriends.size());

                    //LoadChatMessage(chatWithFriends);
                    //  LoadChatMessage(chatMessageslist);
                } else {
                    Toast.makeText(ChatMessage_Activity.this, "No summeries", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ERROR", databaseError.getMessage());
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_im:
                SendNotification();
                // MessageSend();

                break;
            case R.id.attached:
                //  SendNotification();
                MessageSend();
                break;
            case R.id.step_count:
                Intent i3= new Intent(ChatMessage_Activity.this,Step_Count_Activity.class);
                startActivity(i3);
                break;
        }
    }

    private void SendNotification() {
        System.out.println("F_USER****CHATMEssAGE "+ConstantClass.CURRENTUSER_NAME);
        System.out.println("T_USER**** "+ConstantClass.CHATWITH_USERNAME);
       // chat_with_user = ConstantClass.CHAT_WITH_FROM_TO;


        ConstantClass.TYPE = "received";
        postNotificationModel.setTo(ConstantClass.USER_ToOKEN);//client token
        messageData.setTitle("P_Hangouts");
        messageData.setMessage(Edit_Message.getText().toString());
        messageData.setType("send");
        messageData.setFrom_token(ConstantClass.REFERENCE_TOKEN);//user token
        messageData.setF_user(ConstantClass.CURRENTUSER_NAME);//user token
        messageData.setT_user(ConstantClass.CHATWITH_USERNAME);//user token
        postNotificationModel.setData(messageData);

        sendMessageStores.add(new SendMessageStore(ConstantClass.REFERENCE_TOKEN, "P_Hangouts",
                Edit_Message.getText().toString(), "send", ConstantClass.USER_ToOKEN,ConstantClass.CURRENTUSER_NAME,
                ConstantClass.CHATWITH_USERNAME));

        Call<PostNotResponse> call = notificationInterface.sendNotification(postNotificationModel);
        call.enqueue(new Callback<PostNotResponse>() {
            @Override
            public void onResponse(Call<PostNotResponse> call, Response<PostNotResponse> response) {

                Gson gson1 = new Gson();
                LoadMessageMethod(sendMessageStores);
                Edit_Message.setText("");
            }

            @Override
            public void onFailure(Call<PostNotResponse> call, Throwable t) {
                System.out.println("ERROR **" + t.toString());
            }
        });
    }

    private void LoadMessageMethod(List<SendMessageStore> sendMessageStores) {
        recyclerView = (RecyclerView) findViewById(R.id.contact_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        chatMessageAdapter = new ChatMessageAdapter(this, sendMessageStores);
        recyclerView.setAdapter(chatMessageAdapter);
    }

    private void MessageSend() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        messageDBReference = FirebaseDatabase.getInstance().getReference("chatWithFriendMessage");
        String userId = messageDBReference.push().getKey();
        ChatMessage chatMessage = new ChatMessage(userId, ConstantClass.CURRENTUSER_NAME, Edit_Message.getText().toString(), ConstantClass.REFERENCE_TOKEN, ConstantClass.CHATWITH_USERNAME);
        messageDBReference.child(userId).setValue(chatMessage);
        Toast.makeText(ChatMessage_Activity.this, " successful", Toast.LENGTH_LONG).show();
        Edit_Message.setText(null);
    }
}
