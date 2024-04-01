package com.example.tugas2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ActivityTwo extends AppCompatActivity {

    private EditText title;
    private EditText content;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

//        GET DATA DARI MAINACTIVITY
        Intent dataMain = getIntent();
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        save = findViewById(R.id.btnSave);

        save.setOnClickListener(view -> {
            String titleValue = title.getText().toString();
            String contentValue = content.getText().toString();

            Intent submit = new Intent(this, ActivityThree.class);

            if (!titleValue.isEmpty()){
                submit.putExtra("TITLE", titleValue);
            } else {
                title.setError("Please enter your title");
                return;
            }

            if (!contentValue.isEmpty()){
                submit.putExtra("CONTENT", contentValue);
            } else {
                content.setError("Please enter your content");
                return;
            }

            submit.putExtra("NAME", dataMain.getStringExtra("NAME"));
            submit.putExtra("USERNAME", dataMain.getStringExtra("USERNAME"));
            submit.putExtra("IMAGE_URI", dataMain.getStringExtra("IMAGE_URI"));

            startActivity(submit);
        });
    }
}