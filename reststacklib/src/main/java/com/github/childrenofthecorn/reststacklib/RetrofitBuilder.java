package com.github.childrenofthecorn.reststacklib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by grishberg on 24.04.17.
 */

public class RetrofitBuilder<T> {
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final SoftErrorDelegate<?> softErrorDelegate;
    private final Class<T> serviceClass;
    private final String baseUrl;
    private Gson gson;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    public <E> RetrofitBuilder(String baseUrl,
                               Class<T> serviceClass,
                               SoftErrorDelegate<E> softErrorDelegate) {
        this.baseUrl = baseUrl;
        this.serviceClass = serviceClass;
        this.softErrorDelegate = softErrorDelegate;
    }

    public T provideService() {
        if (gson == null) {
            gson = provideGson();
        }
        if (okHttpClient == null) {
            okHttpClient = provideOkHttpClient();
        }
        if (retrofit == null) {
            retrofit = provideRetrofit(gson, okHttpClient, softErrorDelegate);
        }
        return retrofit.create(serviceClass);
    }

    public RetrofitBuilder setGson(Gson gson) {
        this.gson = gson;
        return this;
    }

    public RetrofitBuilder setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        return this;
    }

    public RetrofitBuilder setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
        return this;
    }

    private Gson provideGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        // Register an adapter to manage the date types as long values
        gsonBuilder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()));
        return gsonBuilder
                .setPrettyPrinting()
                .setDateFormat(DATE_PATTERN)
                .create();
    }

    private OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideInterceptor())
                .build();
    }

    private Interceptor provideInterceptor() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    private Retrofit provideRetrofit(final Gson gson,
                                     final OkHttpClient okHttpClient,
                                     final SoftErrorDelegate<?> softErrorDelegate) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create(softErrorDelegate))
                .build();
    }
}
