package com.sheela.cabbooking.api;

import com.sheela.cabbooking.model.User;

import com.sheela.cabbooking.serverresponse.ImageResponse;
import com.sheela.cabbooking.serverresponse.SignUpResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UserAPI {
    @POST("user/signup")
    Call<SignUpResponse> registerUser(@Body User users);
//    @POST("user/login")
//    Call<SignUpResponse> checklogin(@Body username Username);
   @FormUrlEncoded
  @POST("user/login")
  Call<SignUpResponse> checklogin(@Field("username") String username, @Field("password") String password);
    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("user/me")
    Call<User> getUserDetails(@Header("Authorization") String token);


}
