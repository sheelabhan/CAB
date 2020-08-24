package Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {

//    public static final String Base_Url="http://10.0.2.2:3001/";
    public static final String Base_Url="http://172.26.1.10:3001/";

    public static String token = "";

    public static Retrofit getInstance(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit;
    }
}
