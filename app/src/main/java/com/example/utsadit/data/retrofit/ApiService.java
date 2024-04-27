package com.example.utsadit.data.retrofit;

import com.example.utsadit.data.response.GithubSearchResponse;
import com.example.utsadit.data.response.GithubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({"Authorization: token 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"})
    @GET("search/users")
    Call<GithubSearchResponse> searchUsers(@Query("q") String query);

    @Headers({"Authorization: token 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"})
    @GET("users/{username}")
    Call<GithubUser> getUser(@Path("username") String username);


}
