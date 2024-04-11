package com.example.tugas3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    private final ArrayList<User>users;
    private final ArrayList<User> shuffledUsers;

    public PostAdapter(ArrayList<User> users) {

        this.users = users;
        this.shuffledUsers = new ArrayList<>(users);
        Collections.shuffle(shuffledUsers);
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        User user = shuffledUsers.get(position);

        holder.ivProfile.setImageResource(user.getFoto_profil());
        holder.ivPost.setImageResource(user.getFoto_post());
        holder.tvUsername.setText(user.getUsername());
        holder.tvCaption.setText(user.getCaption());
        holder.tvUsernameCapt.setText(user.getUsername());

        holder.ivProfile.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), StoryActivity.class);
            intent.putExtra("user",user);
            view.getContext().startActivity(intent);
        });

        holder.tvUsernameCapt.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ProfileActivity.class);
            intent.putExtra("user",user);
            view.getContext().startActivity(intent);
        });

        holder.tvUsername.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ProfileActivity.class);
            intent.putExtra("user",user);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile, ivPost;
        TextView tvUsername, tvCaption, tvUsernameCapt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.iv_profile);
            ivPost = itemView.findViewById(R.id.iv_post);
            tvCaption = itemView.findViewById(R.id.tv_caption);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvUsernameCapt = itemView.findViewById(R.id.tv_username_capt);
        }
    }
}
