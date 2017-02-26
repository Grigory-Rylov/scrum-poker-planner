package com.grishberg.mvvmstatelessexample.domain.repository;

import com.grishberg.mvvmstatelessexample.domain.model.SprintInfo;
import com.grishberg.mvvmstatelessexample.domain.model.UserContainer;
import com.grishberg.mvvmstatelessexample.domain.model.rest.RestResponse;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by grishberg on 26.02.17.
 */

public interface Api {

    @POST("registerMember")
    Observable<RestResponse<UserContainer>> registerMember(
            @Query("name") String name,
            @Query("sprintToken") String sprintToken
    );

    @GET("getCurrentTaskForSprint")
    Observable<RestResponse<UserContainer>> getCurrentTaskForSprint(
            @Query("sprintToken") String sprintToken
    );

    @GET("getSprintInfo")
    Observable<RestResponse<SprintInfo>> getSprintInfo(
            @Query("sprintToken") String sprintToken
    );
}
