package com.example.tugas5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView ivProfpic = findViewById(R.id.iv_profpic);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvUsername = findViewById(R.id.tv_username);
        ImageView ivBack = findViewById(R.id.iv_back);

        ivBack.setOnClickListener(v -> finish());

        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user");

        ivProfpic.setImageResource(user.getFotoProfile());
        tvName.setText(user.getName());
        tvUsername.setText(user.getUsername());

        ProgressBar progressBar = findViewById(R.id.progress_bar);

        ivProfpic.setVisibility(View.GONE);
        tvName.setVisibility(View.GONE);
        tvUsername.setVisibility(View.GONE);

        progressBar.setVisibility(View.VISIBLE);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent1 = getIntent();
            User user1 = intent1.getParcelableExtra("user");

            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);

                ivProfpic.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                tvUsername.setVisibility(View.VISIBLE);

                ivProfpic.setImageResource(user1.getFotoProfile());
                tvName.setText(user1.getName());
                tvUsername.setText(user1.getUsername());
            });
        });

    }
}