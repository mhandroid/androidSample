package com.android.mh.telstra.api;

import com.android.mh.telstra.model.AboutCanada;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface of api resource
 */
public interface ApiInterface {

    @GET("/s/2iodh4vg0eortkl/facts.js")
    Call<AboutCanada> getListAboutCanada();
}
