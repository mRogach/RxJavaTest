package com.example.mrogach.rxjavatest.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import static com.example.mrogach.rxjavatest.api.ApiConst.TIMEOUT;

/**
 * Created by
 * mRogach on 11.09.2015.
 */
public class RestClient {

    private static Api api;

    static {
        setupRestClient();
    }

    private RestClient() {}

    public static Api getApiService() {
        return api;
    }

    public static void setupRestClient() {
        final RestAdapter restAdapter = getAdapter(ApiConst.PATH_GET_ORGANIZATIONS);
        api = restAdapter.create(Api.class);
    }

    private static RestAdapter getAdapter(final String _url) {

        OkHttpClient ok = new OkHttpClient();
        ok.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
        ok.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder()
                .create();

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(_url)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(ok))
                .build();
    }
}
