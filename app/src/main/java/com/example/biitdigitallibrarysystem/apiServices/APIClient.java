package com.example.biitdigitallibrarysystem.apiServices;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static APIClient instance = null;
    private Endpoint myApi;

    private static Retrofit retrofit = null;
    public static String ip = "192.168.187.147";

    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).
                connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.MINUTES)
                .addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder().baseUrl("http://" + ip + "/Final_Year_Project/api/")
                .addConverterFactory(GsonConverterFactory.create()).client(client).build();

        return retrofit;
    }

    public static String getFilePath() {

        return "http://" + ip + "/Final_Year_Project/Content/Books/";
    }

    public static String getImagePath() {
        return "http://" + ip + "/Final_Year_Project/Content/BookImages/";
    }

    public static synchronized APIClient getInstance() {
        if (instance == null) {
            instance = new APIClient();
        }
        return instance;
    }

    public Endpoint getMyApi() {

        if(myApi==null){
            myApi = retrofit.create(Endpoint.class);
        }
        return myApi;
    }

}