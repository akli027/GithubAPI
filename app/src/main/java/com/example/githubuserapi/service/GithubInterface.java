package com.example.githubuserapi.service;

import com.example.githubuserapi.model.GithubUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubInterface {

    //CRUD = create ,read ,update,delete
    // get = read
    // post = create
    //  put = Update
    // DELETE = Delete
    //,@Query("apa") String

    @GET("users")
    Call<ArrayList<GithubUser>> getUser(@Query("since")int random);

    @GET("users/{username}")
    Call<GithubUser>getDetailUser(@Path("username")String username);

    // TODO: Langkah pertama
//    @GET("/search/users")
//    Call<ResponseSearch> getGithubSearch(
//            @Query("q") String username
//    );
}
