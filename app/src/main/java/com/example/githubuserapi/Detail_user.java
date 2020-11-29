package com.example.githubuserapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.githubuserapi.api.ApiListener;
import com.example.githubuserapi.model.GithubUser;
import com.example.githubuserapi.service.GithubAPI;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Detail_user extends AppCompatActivity {
    public static final String EXTRA_PARCEL = "extra_parcel";
    private GithubUser githubUser;
    private CircleImageView imageView;
    private TextView login, name, followers, following, repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        if (getIntent() != null) {
            githubUser = getIntent().getParcelableExtra(EXTRA_PARCEL);
        }
        imageView = findViewById(R.id.iv_photo);
        login = findViewById(R.id.tv_login);
        name = findViewById(R.id.tv_name);
        followers = findViewById(R.id.followers);
        following = findViewById(R.id.following);
        repos = findViewById(R.id.repositories);

        GithubAPI githubAPI = new GithubAPI();
        githubAPI.getDetailUser(userListener, githubUser.getLogin());
    }

    ApiListener<GithubUser> userListener = new ApiListener<GithubUser>() {
        @Override
        public void onSuccess(GithubUser items) {

            Picasso.get()
                    .load(items.getAvatar_url())
                    .into(imageView);

            login.setText(items.getLogin());
            name.setText(items.getName());
            followers.setText("Followers : " + items.getFollowers());
            following.setText("Following : " + items.getFollowing());
            repos.setText("Repositories: " + items.getPublic_repos());


        }

        @Override
        public void onFailed(String msg) {
            Log.d("ERROR API", msg);
        }
    };
}