package com.prematix_hangouts.api;

import com.prematix_hangouts.ModleClass.PostNotificationModel;
import com.prematix_hangouts.ResponseModel.PostNotResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NotificationInterface {

    @Headers({
            "Authorization: key=AAAAUOz4m-s:APA91bGQIUBH1W1Aa5ac9rnoEYJubM9wwZpRN7ubASOxyWJdPDeD4C8od954CHoExZqKHUsWZACVoUQFytOxGvTkQDfKx3JCpCbRYZIjc1kW2ZEflUuj6-St9uR3jCkbmagEBrYg4noo",
            "Content-Type: application/json"
    })
    @POST("send")
    Call<PostNotResponse> sendNotification(@Body PostNotificationModel postNotificationModel);
}
