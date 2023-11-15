package com.example.retrofitwithjsonarray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("posts")
    Call<RecyclerData>getAllCourses();
}
