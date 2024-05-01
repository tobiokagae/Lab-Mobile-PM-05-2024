package com.example.tugas5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<User> users;

    public SearchAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user, parent, false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {

        User user = users.get(position);

        holder.ivProfpic.setImageResource(user.getFotoProfile());
        holder.tvUsername.setText(user.getUsername());
        holder.tvName.setText(user.getName());

        holder.ivProfpic.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("user", user);
            holder.context.startActivity(intent);
        });

        holder.tvUsername.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("user", user);
            holder.context.startActivity(intent);
        });
        holder.tvName.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("user", user);
            holder.context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfpic;
        TextView tvUsername, tvName;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfpic = itemView.findViewById(R.id.iv_profpic);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvName = itemView.findViewById(R.id.tv_name);
            context = itemView.getContext();
        }
    }
}
