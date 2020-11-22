package com.klc.geziyerleri.network;

import com.klc.geziyerleri.constants.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeziYerleriClient {

    private static Retrofit retrofit;

    public static Retrofit newInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.WebService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(HttpClient.getUnsafeOkHttpClient())
                    .build();
        }
        return retrofit;
    }
}
