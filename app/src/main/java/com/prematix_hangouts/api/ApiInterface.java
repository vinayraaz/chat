package com.prematix_hangouts.api;


import com.prematix_hangouts.ModleClass.PostNotificationModel;
import com.prematix_hangouts.ResponseModel.PostNotResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by vinay on 12/8/2017.
 */

public interface ApiInterface {
    @GET("user")
    Call<String> getusers();



  /*  @POST("/tasks")
    Call<Task> createTask(@Body Task task);*/

}


