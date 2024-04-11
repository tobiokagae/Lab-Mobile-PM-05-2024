package com.example.tugas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView tvUsername, tvFollowers, tvFollowing;
    ImageView ivPost, ivProfil, ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUsername = findViewById(R.id.tv_username);
        tvFollowers = findViewById(R.id.tv_followers);
        tvFollowing = findViewById(R.id.tv_following);
        ivPost = findViewById(R.id.iv_post);
        ivProfil = findViewById(R.id.iv_profile);
        ivBack = findViewById(R.id.iv_back);

        User user = getIntent().getParcelableExtra("user");
        assert user != null;
        tvUsername.setText(user.getUsername());
        tvFollowing.setText(user.getFollowing());
        tvFollowers.setText(user.getFollowers());
        ivPost.setImageResource(user.getFoto_post());
        ivProfil.setImageResource(user.getFoto_profil());

        ivPost.setOnClickListener(v -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });

        ivProfil.setOnClickListener(v -> {
            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });

        ivBack.setOnClickListener(v -> finish());
    }
}