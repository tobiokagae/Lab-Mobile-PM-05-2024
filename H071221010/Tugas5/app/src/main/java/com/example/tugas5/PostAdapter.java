package com.example.tugas5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ArrayList<User> users;

    public PostAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post, parent, false);
        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {

        User user = users.get(position);

        holder.tvUsername.setText(user.getUsername());
        holder.tvName.setText(user.getName());
        holder.ivProfpic.setImageResource(user.getFotoProfile());
        holder.ivPostpic.setImageResource(user.getFotoPostingan());
        holder.addPic.setImageURI(user.getSelectedImageUri());
        holder.tvCaption.setText(user.getDesc());

        holder.ivProfpic.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("user", user);
            holder.context.startActivity(intent);
        });

        holder.tvName.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("user", user);
            holder.context.startActivity(intent);
        });
        holder.tvUsername.setOnClickListener(v -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("user", user);
            holder.context.startActivity(intent);
        });

        holder.ivDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.context);
            builder.setTitle("Konfirmasi");
            builder.setMessage("Apakah Anda yakin ingin menghapus postingan ini?");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        users.remove(adapterPosition);
                        notifyItemRemoved(adapterPosition);
                    }
                }
            });
            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPostpic, ivProfpic, ivDelete, addPic;
        TextView tvCaption, tvUsername, tvName;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPostpic = itemView.findViewById(R.id.iv_postpic);
            addPic = itemView.findViewById(R.id.iv_postpic);
            ivProfpic = itemView.findViewById(R.id.iv_profpic);
            ivDelete = itemView.findViewById(R.id.iv_delete);
            tvCaption = itemView.findViewById(R.id.tv_caption);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvName = itemView.findViewById(R.id.tv_name);
            context = itemView.getContext();
        }
    }
}
