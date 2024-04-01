package com.example.tugas2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityThree extends AppCompatActivity {
    private ImageView imageResult;
    private TextView nameResult;
    private TextView usernameResult;
    private TextView titleResult;
    private TextView contentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        imageResult = findViewById(R.id.image);
        nameResult = findViewById(R.id.name);
        usernameResult = findViewById(R.id.username);
        titleResult = findViewById(R.id.title);
        contentResult = findViewById(R.id.content);

        Intent result = getIntent();
        if(result != null) {
            if ( result.getStringExtra("IMAGE_URI") != null) {
                Uri imageUri = Uri.parse(result.getStringExtra("IMAGE_URI"));
                imageResult.setImageURI(imageUri);
            }

            nameResult.setText(result.getStringExtra("NAME"));
            usernameResult.setText(result.getStringExtra("USERNAME"));
            titleResult.setText(result.getStringExtra("TITLE"));
            contentResult.setText(result.getStringExtra("CONTENT"));
        }
    }
}