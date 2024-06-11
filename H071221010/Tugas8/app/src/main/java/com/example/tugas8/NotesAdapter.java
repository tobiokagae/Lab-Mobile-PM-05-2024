package com.example.tugas8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Notes> notes;
    private final Context context;
    private final DBConfig dbConfig;
    private final ActivityResultLauncher<Intent> resultLauncher;

    public NotesAdapter(List<Notes> notes, Context context, ActivityResultLauncher<Intent> resultLauncher) {
        this.notes = notes;
        this.context = context;
        this.dbConfig = new DBConfig(context);
        this.resultLauncher = resultLauncher;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_notes, parent, false);
        return new NotesViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Notes note = notes.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvContent.setText(note.getContent());

        String updatedAt = note.getUpdatedAt();
        if (updatedAt != null) {
            holder.tvDate.setText("Updated at " + updatedAt);
        } else {
            holder.tvDate.setText("Created at " + note.getCreatedAt());
        }

        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Konfirmasi Hapus")
                    .setMessage("Apakah Anda yakin ingin menghapus catatan ini?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        dbConfig.deleteNote(note.getId());
                        notes.remove(note);
                        notifyDataSetChanged();
                        ((MainActivity) context).reloadData();
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
        });

        holder.cardEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("id", note.getId());
            resultLauncher.launch(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent, tvDate;
        ImageView btnDelete;
        CardView cardEdit;

        public NotesViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_judul);
            tvContent = itemView.findViewById(R.id.tv_konten);
            tvDate = itemView.findViewById(R.id.tv_tanggal);
            btnDelete = itemView.findViewById(R.id.iv_delete);
            cardEdit = itemView.findViewById(R.id.card_edit);
        }
    }
}

