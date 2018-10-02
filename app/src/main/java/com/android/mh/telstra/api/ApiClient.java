package com.android.mh.telstra.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class to responsible to create api client for retrofit.
 */
public class ApiClient {
    private static final String BASE_URL = "https://dl.dropboxusercontent.com";
    private static Retrofit retrofit = null;


    /**
     * to get the retrofit client object
     * @return Retrofit
     */
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
