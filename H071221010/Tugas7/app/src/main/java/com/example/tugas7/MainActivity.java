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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Button btnRegister, btnLogin;
    TextView etPassword, etNim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNim = findViewById(R.id.et_nim);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        sharedPreferences = getSharedPreferences("data_pref", MODE_PRIVATE);

        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
            String nim = etNim.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String realNim = sharedPreferences.getString("nim", "");
            String realPass = sharedPreferences.getString("password", "");

            if (nim.isEmpty() && password.isEmpty()){
                etNim.setError("Please enter your NIM");
                etPassword.setError("Please enter your Password");
            } else if (password.isEmpty()) {
                etPassword.setError("Please enter your Password");
            } else if (nim.isEmpty()) {
                etNim.setError("Please enter your NIM");
            } else if (nim.equals(realNim) && password.equals(realPass)) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);

                etNim.setText("");
                etPassword.setText("");

                finish();
            } else {
                Toast.makeText(this, "Incorrect NIM or Password.", Toast.LENGTH_SHORT).show();
            }
        });

        int theme = sharedPreferences.getInt("isDarkMode", 0);
        if (theme == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
