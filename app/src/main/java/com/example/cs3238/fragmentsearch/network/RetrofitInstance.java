package com.example.cs3238.fragmentsearch.network;

import android.content.Context;

import com.example.cs3238.fragmentsearch.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInstance {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    public static Retrofit.Builder builder = null;
    private static String BASE_URL = "https://restcountries.eu/";


    public static <S> S createService(Class<S> serviceClass, Context ctx) {
        if(builder == null){
            if (!httpClient.interceptors().contains(getInter())) {
                try {
                    //For logging
                    if(BuildConfig.DEBUG) {
                        httpClient.addInterceptor(getInter());
                    }

                    httpClient.retryOnConnectionFailure(true);
                    int CONNECT_TIMEOUT_MILLIS = 1000 * 30;
                    httpClient.connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
                    int READ_TIMEOUT_MILLIS = 1000 * 30;
                    httpClient.readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }}
        if (builder == null) {

            builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create());

        }

        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }



    public static HttpLoggingInterceptor getInter() {
        {
            //Here a logging interceptor is created
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set our desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            return logging;
        }

    }

}
