package com.example.tugas7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;

public class ThemeActivity extends AppCompatActivity {

    Switch modeSwitch;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        sharedPreferences = getSharedPreferences("data_pref", MODE_PRIVATE);
        modeSwitch = findViewById(R.id.switchTheme);

        int isDarkMode = sharedPreferences.getInt("isDarkMode", 0);
        modeSwitch.setChecked(isDarkMode == 1);

        modeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            editor.putInt("isDarkMode", isChecked? 1 : 0);
            editor.apply();
        });
    }
}
