package com.example.githubuserapi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.githubuserapi.adapter.UserAdapter;
import com.example.githubuserapi.api.ApiListener;
import com.example.githubuserapi.model.GithubUser;
import com.example.githubuserapi.service.GithubAPI;

import java.util.ArrayList;


public class Home extends Fragment {

    private RecyclerView rvGithub;
    private UserAdapter userAdapter;
    private ArrayList<GithubUser> lisUser = new ArrayList<>();

    public View v;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

       // initView();
        userAdapter = new UserAdapter();
        rvGithub = v.findViewById(R.id.lv_item);
        rvGithub.setHasFixedSize(true);
        rvGithub.setLayoutManager(new LinearLayoutManager(getContext()));
        rvGithub.setAdapter(userAdapter);

        GithubAPI githubApi = new GithubAPI();
        githubApi.getALLUser(userListener);

        return v;
    }

    ApiListener<ArrayList<GithubUser>> userListener = new ApiListener<ArrayList<GithubUser>>() {
        @Override
        public void onSuccess(ArrayList<GithubUser> items) {
            lisUser.addAll(items);
            userAdapter.setUser(lisUser);
        }

        @Override
        public void onFailed(String message) {
            Log.d("Error", message);
        }
    };
}