package com.lc.belajar.retrofit.services.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static final String API_BASE_URL = "http://ibacor.com/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    public static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient client = httpClient.sslSocketFactory(SocketFactoryUtil.getSocketFactory()).build();
        Retrofit retrofit = builder.client(client).build();

        return retrofit.create(serviceClass);
    }

    public static <S> S createBasicService(Class<S> serviceClass, String username, String password) {
        if (username != null && password != null) {
            String credentials = username + ":" + password;
            final String basic = "Basic " + new String(Base64.encodeBase64(credentials.getBytes()));

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder().header("Authorization", basic)
                            .header("Accept", "application/json").method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.sslSocketFactory(SocketFactoryUtil.getSocketFactory()).build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createTokenService(Class<S> serviceClass, String token) {
        if (token != null) {
            final String basic = "Token " + token;
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder().header("Authorization", basic)
                            .header("Accept", "application/json").method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.sslSocketFactory(SocketFactoryUtil.getSocketFactory()).build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}