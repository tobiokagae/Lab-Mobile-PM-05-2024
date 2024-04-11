package com.example.tugas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView tvUsername, tvCaption, tvUsernameCapt;
    ImageView ivPost, ivProfile, ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvUsername = findViewById(R.id.tv_username);
        tvCaption = findViewById(R.id.tv_caption);
        ivPost = findViewById(R.id.iv_post);
        ivProfile = findViewById(R.id.iv_profile);
        tvUsernameCapt = findViewById(R.id.tv_username_capt);
        ivBack = findViewById(R.id.iv_back);

        User user = getIntent().getParcelableExtra("user");
        assert user != null;
        tvUsername.setText(user.getUsername());
        tvUsernameCapt.setText(user.getUsername());
        tvCaption.setText(user.getCaption());
        ivProfile.setImageResource(user.getFoto_profil());
        ivPost.setImageResource(user.getFoto_post());

        tvUsername.setOnClickListener(v -> finish());

        ivProfile.setOnClickListener(v -> {
            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });

        tvUsernameCapt.setOnClickListener(v -> finish());

        ivBack.setOnClickListener(v -> finish());
    }
}