package com.example.tugas7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etNim, etPassword;
    Button btnRegister;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.et_name);
        etNim = findViewById(R.id.et_nim);
        etPassword = findViewById(R.id.et_password);

        btnRegister = findViewById(R.id.btn_register);

        sharedPreferences = getSharedPreferences("data_pref", MODE_PRIVATE);

        btnRegister.setOnClickListener(v -> {
            String nim = etNim.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (name.isEmpty() && nim.isEmpty() && password.isEmpty()) {
                etNim.setError("Please enter your NIM");
                etName.setError("Please enter your Name");
                etPassword.setError("Please enter your Password");
            } else if (name.isEmpty() && nim.isEmpty()) {
                etName.setError("Please enter your Name");
                etNim.setError("Please enter your NIM");
            } else if (nim.isEmpty() && password.isEmpty()) {
                etNim.setError("Please enter your NIM");
                etPassword.setError("Please enter your Password");
            } else if (name.isEmpty() && password.isEmpty()) {
                etName.setError("Please enter your Name");
                etPassword.setError("Please enter your Password");
            } else if (nim.isEmpty()) {
                etNim.setError("Please enter your NIM");
            } else if (name.isEmpty()) {
                etName.setError("Please enter your Name");
            } else if (password.isEmpty()) {
                etPassword.setError("Please enter your Password");
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", name);
                editor.putString("nim", nim);
                editor.putString("password", password);

                boolean isRegist = editor.commit();

                if (isRegist) {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etNim.setText("");
                    etPassword.setText("");

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);

                    finish();
                }
            }
        });
    }
}
