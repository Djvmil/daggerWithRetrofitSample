package com.example.daggersamplev2.dagger.backend;

import com.example.daggersamplev2.dagger.response.UserResponseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Djvmil_ on 13/11/2019.
 */

public interface ServiceUtil {

    @GET("posts")
    Call<List<UserResponseResponse>> getUsers();
}
