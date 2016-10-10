package com.mnikn.mylibrary.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class RetrofitBuilder {

    private static RetrofitBuilder INSTANCE;

    private RetrofitBuilder() {}

    public static RetrofitBuilder getInstance(){
        if(INSTANCE == null){
            INSTANCE = new RetrofitBuilder();
        }
        return INSTANCE;
    }

    public Retrofit retrofit(String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
