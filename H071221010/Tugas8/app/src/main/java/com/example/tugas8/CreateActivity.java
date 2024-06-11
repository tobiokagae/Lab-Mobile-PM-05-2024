package com.example.tugas8;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateActivity extends AppCompatActivity {

    DBConfig dbConfig;
    Button btnSave;
    EditText etTitle, etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbConfig = new DBConfig(this);
        btnSave = findViewById(R.id.btn_save);
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);

        // Mengatur toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String content = etContent.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(this, "Title Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            } else {
                saveNoteToDatabase(title, content);
            }
        });
    }

    private void saveNoteToDatabase(String title, String content) {
        String currentTime = getCurrentTime();
        Notes notes = new Notes();
        notes.setTitle(title);
        notes.setContent(content);
        notes.setCreatedAt(currentTime);

        long result = dbConfig.insertData(notes);

        if (result == -1) {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            showExitConfirmationDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Apakah Anda yakin ingin meninggalkan halaman ini? Perubahan yang tidak disimpan akan hilang.")
                .setPositiveButton("Ya", (dialog, id) -> finish())
                .setNegativeButton("Tidak", null)
                .show();
    }
}
