package com.example.githubuserapi.service;

import com.example.githubuserapi.api.ApiListener;
import com.example.githubuserapi.model.GithubUser;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubAPI {
    private Retrofit retrofit = null;
    private static final String URL_BASE = "https://api.github.com/";
    public GithubInterface getAPI(){
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GithubInterface.class);
    }
    public void getALLUser(final ApiListener<ArrayList<GithubUser>> listener){
        int random = new Random().nextInt(1000);
        getAPI().getUser(random).enqueue(new Callback<ArrayList<GithubUser>>() {
            @Override
            public void onResponse(Call<ArrayList<GithubUser>> call, Response<ArrayList<GithubUser>> response) {
             ArrayList<GithubUser> listUser = response.body();
             if (listUser != null){
                 listener.onSuccess(listUser);

             }
            }

            @Override
            public void onFailure(Call<ArrayList<GithubUser>> call, Throwable t) {
            listener.onFailed(t.getMessage());
            }
        });

    }
    public void getDetailUser(final ApiListener<GithubUser> listener,String username){
    getAPI().getDetailUser(username).enqueue(new Callback<GithubUser>() {
        @Override
        public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
            GithubUser githubUser = response.body();
            if (githubUser != null);
            listener.onSuccess(githubUser);
        }

        @Override
        public void onFailure(Call<GithubUser> call, Throwable t) {
            listener.onFailed(t.getMessage());
        }
    });
    }

}
