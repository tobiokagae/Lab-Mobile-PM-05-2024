package com.example.tugas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    ImageView ivProfile, ivStory;
    TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        ivProfile = findViewById(R.id.iv_profile);
        ivStory = findViewById(R.id.iv_story);
        tvUsername = findViewById(R.id.tv_username);

        User user = getIntent().getParcelableExtra("user");
        assert user != null;
        ivProfile.setImageResource(user.getFoto_profil());
        ivStory.setImageResource(user.getFoto_story());
        tvUsername.setText(user.getUsername());

        ivProfile.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });

        tvUsername.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });
    }
}