package com.example.tugas7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView tvWelcome;
    Button btnSetting, btnLogout;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvWelcome = findViewById(R.id.tv_welcome);

        btnLogout = findViewById(R.id.btn_logout);
        btnSetting = findViewById(R.id.btn_setting);

        sharedPreferences = getSharedPreferences("data_pref", MODE_PRIVATE);

        String name = sharedPreferences.getString("name", "");
        String text = tvWelcome.getText() + " " + name;
        tvWelcome.setText(text);

        btnSetting.setOnClickListener(v -> {
            Intent intent = new Intent(this, ThemeActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}