package com.example.tugas8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.SearchView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnCreate;
    RecyclerView rvNotes;
    SearchView searchView;
    NotesAdapter noteAdapter;
    DBConfig dbConfig;
    TextView tvNoData;

    final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    loadDataFromDatabase();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvNotes = findViewById(R.id.rv_notes);
        searchView = findViewById(R.id.search_view);
        btnCreate = findViewById(R.id.btn_create);
        tvNoData = findViewById(R.id.tv_no_data);

        dbConfig = new DBConfig(this);

        btnCreate.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateActivity.class);
            resultLauncher.launch(intent);
        });

        loadDataFromDatabase();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchNotesByTitle(newText);
                return true;
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadDataFromDatabase() {
        List<Notes> notes = dbConfig.getAllNotes();

        if (notes.isEmpty()) {
            rvNotes.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        } else {
            rvNotes.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);
            if (noteAdapter == null) {
                noteAdapter = new NotesAdapter(notes, this, resultLauncher);
                rvNotes.setLayoutManager(new LinearLayoutManager(this));
                rvNotes.setAdapter(noteAdapter);
            } else {
                noteAdapter.setNotes(notes);
                noteAdapter.notifyDataSetChanged();
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void searchNotesByTitle(String title) {
        List<Notes> notes = dbConfig.searchNotesByTitle(title);
        if (noteAdapter != null) {
            noteAdapter.setNotes(notes);
            noteAdapter.notifyDataSetChanged();
        }
    }

    public void reloadData() {
        loadDataFromDatabase();
    }

}
