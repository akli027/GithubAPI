package com.example.githubuserapi.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubuserapi.Detail_user;
import com.example.githubuserapi.R;
import com.example.githubuserapi.model.GithubUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<GithubUser> listUser = new ArrayList<>();

    public void setUser(ArrayList<GithubUser>listUser){
        this.listUser.clear();
        this.listUser.addAll(listUser);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.bind(listUser.get(position));
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       private ImageView ivAvatar;
       private TextView tvUser,tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_profile);
            tvUser = itemView.findViewById(R.id.tv_title);

        }

        public void bind(GithubUser githubUser) {
            tvUser.setText(githubUser.getLogin());
            Picasso.get()
                    .load(githubUser.getAvatar_url())
                    .into(ivAvatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(),Detail_user.class);
                    intent.putExtra(Detail_user.EXTRA_PARCEL, githubUser);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
