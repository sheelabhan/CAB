package com.sheela.cabbooking.bll;

import com.sheela.cabbooking.api.UserAPI;
import com.sheela.cabbooking.serverresponse.SignUpResponse;
import com.sheela.cabbooking.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    private String username;
    private String password;
    boolean isSuccess = false;

    public boolean checklogin(String username, String password){

        UserAPI usersAPI = Url.getInstance().create(UserAPI.class);
        Call<SignUpResponse> usersCall = usersAPI.checklogin(username,password);
        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                Url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}

