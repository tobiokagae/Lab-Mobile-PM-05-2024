package com.example.tugas6;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/users")
    Call<DataResponse> getData(@Query("page") String page);

    @GET("api/users")
    Call<ProfileResponse> getUserbyId(@Query("id") String id);
}
