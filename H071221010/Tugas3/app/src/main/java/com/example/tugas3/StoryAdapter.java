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
import java.util.HashSet;
import java.util.Set;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private final ArrayList<User> users;
    private final ArrayList<User> shuffledUsers;

    public StoryAdapter(ArrayList<User> users) {

        this.users = users;
        this.shuffledUsers = new ArrayList<>(users);
        Collections.shuffle(shuffledUsers);
    }

    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_story, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder holder, int position) {
        User user = shuffledUsers.get(position);

        holder.ivProfile.setImageResource(user.getFoto_profil());
        holder.tvUsername.setText(user.getUsername());

        holder.ivProfile.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), StoryActivity.class);
            intent.putExtra("user",user);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile;
        TextView tvUsername;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfile = itemView.findViewById(R.id.iv_profile);
            tvUsername = itemView.findViewById(R.id.tv_username);
        }
    }
}
