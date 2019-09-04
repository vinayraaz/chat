package com.prematix_hangouts.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class PaypreApiClient {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    // https://fcm.googleapis.com/fcm/send
    public static final String BASE_URL = "https://fcm.googleapis.com/fcm/";
    //public static final String BASE_URL = "http://www.paypre.info/";
    //http://www.paypre.info/Display_challan_details?vehicle_no=KN60AH9632
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
