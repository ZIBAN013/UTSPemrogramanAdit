package com.example.utsadit;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utsadit.data.response.GithubUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<GithubUser> users;

    public UserAdapter(List<GithubUser> users) {
        this.users = users;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GithubUser user = users.get(position);
        holder.usernameTextView.setText(user.getUsername());
        Picasso.get().load(user.getAvatarUrl()).into(holder.avatarImageView);

        holder.itemView.setOnClickListener(click -> {
            Intent intent = new Intent(click.getContext(), DetailActivity.class);
            intent.putExtra("Nama ", user.getName());
            intent.putExtra("Nama Pengguna", user.getUsername());
            intent.putExtra("Bio", user.getBio());
            intent.putExtra("gambar", user.getAvatarUrl());
            click.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return users.size();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImageView;
        TextView usernameTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avatar_image_view);
            usernameTextView = itemView.findViewById(R.id.username_text_view);
        }
    }
}
