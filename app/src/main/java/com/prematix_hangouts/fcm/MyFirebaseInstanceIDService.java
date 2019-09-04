package com.prematix_hangouts.fcm;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.prematix_hangouts.extra.ConstantClass;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    SharedPreferences sharedpreferences;
    @Override
    public void onTokenRefresh() {

        //For registration of token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        sendRegistrationToServer(refreshedToken);

        System.out.println("TOKENKey**** " + refreshedToken);
        ConstantClass.REFERENCE_TOKEN =refreshedToken;
        sharedpreferences = getSharedPreferences("hangouts_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("REFERENCE_TOKEN", refreshedToken);
        editor.commit();
        //To displaying token on logcat
        System.out.println("TOKENKey**** " + refreshedToken);



    }

    private void sendRegistrationToServer(String refreshedToken) {

    }
}
