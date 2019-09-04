package com.prematix_hangouts.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vinay on 12/8/2017.
 */

public class ApiClient {
    public static final String BASE_URL = "https://apiapplication-f3e77.firebaseio.com/";
 //https://apiapplication-f3e77.firebaseio.com/register
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
