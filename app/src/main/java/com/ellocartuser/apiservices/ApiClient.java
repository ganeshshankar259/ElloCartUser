package com.ellocartuser.apiservices;

import android.content.Context;
import android.util.Base64;

import com.ellocartuser.BuildConfig;
import com.ellocartuser.utils.Constants;
import com.ellocartuser.utils.Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    //   public static String BASE_URL=;

    private static ApiInterface sInstance;
    private static ApiInterface sInstanceservice;
    private static ApiInterface sInstancerooms;
    private static String tokenValue;
 //  static public String BASE_URL="bv";

    public static ApiInterface getInstance(Context context) {

        //  if (NetworkHelper.isNetworkAvaialble(context)) {
        //tokenValue = token;
        return getApiService();
        //} else {
        //   throw new NoInternetException("No Internet connection available");
        // }
    }


    public static class NoInternetException extends Exception {
        public NoInternetException(String message) {
            super(message);
        }
    }

    public static ApiInterface getApiService() {
        if (sInstance == null) {
            synchronized (ApiClient.class) {
                if (sInstance == null) {

                    sInstance = new Retrofit.Builder()
                            .baseUrl(Util.uurrll())
                            .client(getOkHttpClient()).addConverterFactory(GsonConverterFactory.create()).build()
                            .create(ApiInterface.class);
                }
            }
        }
        return sInstance;
    }
//Util.uurrllserv()
    public static ApiInterface getApiServiceforservice() {
        if (sInstanceservice == null) {
            synchronized (ApiClient.class) {
                if (sInstanceservice == null) {
                    sInstanceservice = new Retrofit.Builder()
                            .baseUrl(Util.uurrllserv())
                            .client(getOkHttpClient()).addConverterFactory(GsonConverterFactory.create()).build()
                            .create(ApiInterface.class);
                }
            }
        }
        return sInstanceservice;
    }

    public static ApiInterface getApiServiceforRooms() {
        if (sInstancerooms == null) {
            synchronized (ApiClient.class) {
                if (sInstancerooms == null) {
                    sInstancerooms = new Retrofit.Builder()
                            .baseUrl("https://user.ellocart.com/apir/")
                            .client(getOkHttpClient()).addConverterFactory(GsonConverterFactory.create()).build()
                            .create(ApiInterface.class);
                }
            }
        }
        return sInstancerooms;
    }


    private static OkHttpClient getOkHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true);

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder();
                //.header(Constant.API_KEY, Constant.API_KEY_VALUE);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

        builder.addInterceptor(interceptor);

        if (BuildConfig.DEBUG) {

            //Print Log
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);


        }
        return builder.readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

    }

}