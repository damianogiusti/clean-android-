package com.damianogiusti.cleanandroid.data.datasources;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

/**
 * Created by Damiano Giusti on 03/05/17.
 */
public abstract class BaseRestDataSource {

    protected Gson gson;
    protected OkHttpClient httpClient;

    public BaseRestDataSource(Gson gson) {
        this.gson = gson;
        this.httpClient = new OkHttpClient();
    }
}
