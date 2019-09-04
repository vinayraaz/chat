package com.prematix_hangouts.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.prematix_hangouts.ModleClass.MessageSave.SendMessageStore;
import com.prematix_hangouts.R;
import com.prematix_hangouts.activity.ChatMessage_Activity;
import com.prematix_hangouts.activity.Chat_ContactList_Activity;
import com.prematix_hangouts.activity.Login_Activity;
import com.prematix_hangouts.extra.ConstantClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    String title, message, type,fromToken,f_user,t_user;
    public static List<SendMessageStore> sendMessageStores = new ArrayList<>();
    Context context;
    ChatMessage_Activity chatMessage_activity;
    PendingIntent resultIntent;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("From:", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("Message data payload:", "Message data payload: " + remoteMessage.getData());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                // handleDataMessage(json);
            } catch (Exception e) {
                Log.e("ERROR", "Exception: " + e.getMessage());
            }
        }


        //getting the title and the body
        title = remoteMessage.getData().get("title");
        message = remoteMessage.getData().get("message");
        type = remoteMessage.getData().get("type");
        fromToken = remoteMessage.getData().get("from_token");
        f_user = remoteMessage.getData().get("f_user");
        t_user = remoteMessage.getData().get("t_user");


        System.out.println("F_USER***NOtificationCurrent " + ConstantClass.CURRENTUSER_NAME);
        System.out.println("F_USER***NOtification " + f_user);
        System.out.println("T_USER***NOtification " + t_user);
        ConstantClass.CHAT_WITH_FROM_TO =t_user+f_user;
        System.out.println(" ConstantClass.CHAT_WITH_FROM_TO***Notificatio " +  ConstantClass.CHAT_WITH_FROM_TO);

        type = "received";
        ConstantClass.USER_ToOKEN=fromToken;
        /*ConstantClass.CURRENTUSER_NAME=f_user;
        ConstantClass.CHATWITH_USERNAME=t_user;
        System.out.println("F_USER***NOtificationRe " + ConstantClass.CURRENTUSER_NAME);
        System.out.println("T_USER***NOtificationRe " +  ConstantClass.CHATWITH_USERNAME);
*/
        sendMessageStores = ChatMessage_Activity.sendMessageStores;
        sendMessageStores.add(new SendMessageStore(ConstantClass.REFERENCE_TOKEN, "P_Hangouts", message, type, ConstantClass.USER_ToOKEN,f_user,t_user));

        Intent intent = new Intent(getApplicationContext(), ChatMessage_Activity.class);
        intent.putExtra("Title", title);
        intent.putExtra("Message", message);
        intent.putExtra("Type", type);
        intent.putExtra("To_Token", fromToken);
        intent.putExtra("f_user", f_user);
        intent.putExtra("t_user", t_user);
        intent.putExtra("CLICK_VALUES","no");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        createNotification(title, message, type);


    }


    private void createNotification(String title, String message, String type) {
        Intent intent = new Intent(this, ChatMessage_Activity.class);
        intent.putExtra("Title", title);
        intent.putExtra("Message", message);
        intent.putExtra("Type", type);
        intent.putExtra("To_Token", fromToken);
        intent.putExtra("f_user", f_user);
        intent.putExtra("t_user", t_user);
        intent.putExtra("CLICK_VALUES","no");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        resultIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Prematix_Hangouts")
                .setContentText(title)
                .setAutoCancel(true)
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mNotificationBuilder.build());
    }
}





































 /*   private void handleDataMessage(JSONObject json) {
        try {
            title = json.getString("title");
            message = json.getString("message");
            type = json.getString("type");

            Bundle bundle = new Bundle();
            bundle.putString("message", message);
            bundle.putString("title", title);
            bundle.putString("type",type);

            Intent i2 = new Intent(getApplicationContext(),Notification_Activity.class);
            i2.putExtras(bundle);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("title***" + type);
        System.out.println("body***" + message);
        ConstantClass.Message = message;
        ConstantClass.TYPE = type;

        // sendMessageStores.add(new SendMessageStore(ConstantClass.REFERENCE_TOKEN, "P_Hangouts", message, ConstantClass.TYPE));
        //showNotificationMessage(getApplicationContext(), title, message, null, resultIntent);
        //createNotification(title,message,type);
    }*/