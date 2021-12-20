package com.ecms.ndmecms;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(new GsonBuilder().
                serializeNulls()
                .create()))
                   .baseUrl("https://erecordsapis.azurewebsites.net/")
//                .baseUrl("https://erecordsapi.azurewebsites.net/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    private static Retrofit getRetrofit2(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(new GsonBuilder().
                serializeNulls()
                .create()))
                .baseUrl("http://102.133.164.64/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }


    public static Apis getUserService(){
        Apis userService = getRetrofit().create(Apis.class);

        return userService;
    }

    public static Apis getUserService2(){
        Apis userService = getRetrofit2().create(Apis.class);

        return userService;
    }


}


