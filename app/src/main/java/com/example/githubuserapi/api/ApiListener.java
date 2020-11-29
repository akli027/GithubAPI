package com.example.githubuserapi.api;

import com.example.githubuserapi.model.GithubUser;

import java.util.ArrayList;
//ubah ke dinamis
public interface ApiListener<T> {
    void onSuccess(T items);
    void onFailed(String msg);
}
