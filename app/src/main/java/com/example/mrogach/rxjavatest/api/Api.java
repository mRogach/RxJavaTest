package com.example.mrogach.rxjavatest.api;

import com.example.mrogach.rxjavatest.model.ResponseModel;


import rx.Observable;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by
 * mRogach on 11.09.2015.
 */
public interface Api {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @GET("/currency-cash.json/")
    Observable<ResponseModel> getInfo();
}
