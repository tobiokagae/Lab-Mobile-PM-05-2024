package com.example.tugas6;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    ArrayList<UserResponse> users;

    public UserAdapter(ArrayList<UserResponse> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        UserResponse userResponse = users.get(position);
        holder.tvName.setText(userResponse.getFirstName());
        holder.tvEmail.setText(userResponse.getEmail());
        Picasso.get().load(userResponse.getAvatarUrl()).into(holder.ivPict);

        holder.itemView.setOnClickListener(v -> {
            int data = users.get(holder.getAdapterPosition()).getId();
            String id = Integer.toString(data);
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra("id", id);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void addData(List<UserResponse> newData) {
        int startPosition = users.size();
        users.addAll(newData);
        notifyItemRangeInserted(startPosition, newData.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPict;
        TextView tvName, tvEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPict = itemView.findViewById(R.id.iv_pict);
            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
        }
    }
}


