package com.sheela.cabbooking.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
    public static final String base_Url = "http://10.0.2.2:3001/";
  // public static final String base_Url = "http://192.168.10.183001/";
   //public static final String base_url ="http://192.168.10.1:3001/";

    public  static String token="Bearer ";
    public static String imagePath = base_Url + "uploads/" ;

 public static Retrofit getInstance() {
     Retrofit retrofit = new Retrofit.Builder()
             .baseUrl(base_Url).addConverterFactory(GsonConverterFactory.create()).build();

     return retrofit;

 }
    }

