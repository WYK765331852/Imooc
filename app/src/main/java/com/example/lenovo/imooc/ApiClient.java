package com.example.lenovo.imooc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.internal.platform.Platform.INFO;

/**
 * Created by lenovo on 2017/10/21.
 */

public class ApiClient {
    protected Retrofit mRetrofit;
    private static final String imoocApi = "http://www.imooc.com/";
    private static Interceptor requestInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder();
            Request request = builder.build();
            return chain.proceed(request);
        }
    };

    private ApiClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (message.startsWith("{")) {
                    com.orhanobut.logger.Logger.json(message);
                } else {
                    Platform.get().log(INFO, message, null);
                }
            }
        });
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(imoocApi)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static class SingletonHolder {
        private static final ApiClient INSTANCE = new ApiClient();
    }

    public static Retrofit getRetrofit() {
        return SingletonHolder.INSTANCE.mRetrofit;
    }

}
