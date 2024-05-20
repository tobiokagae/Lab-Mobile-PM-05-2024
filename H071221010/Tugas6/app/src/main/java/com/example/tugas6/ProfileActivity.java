package com.example.tugas6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    ImageView ivPict;
    Button btnRetry;
    TextView tvName, tvEmail, tvError;
    ProgressBar progressBar;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivPict = findViewById(R.id.iv_pict);
        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        ll = findViewById(R.id.ll);
        progressBar = findViewById(R.id.progress_bar);
        btnRetry = findViewById(R.id.btn_retry);
        tvError = findViewById(R.id.tv_error);

        calling();
    }

    public void calling(){
        String id = getIntent().getStringExtra("id");

        tvError.setVisibility(View.GONE);
        btnRetry.setVisibility(View.GONE);
        ll.setVisibility(View.GONE);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(() -> {
            Call<ProfileResponse> client = ApiConfig.getApiService().getUserbyId(id);
            client.enqueue(new Callback<ProfileResponse>() {

                @Override
                public void onResponse(@NonNull Call<ProfileResponse> call, @NonNull Response<ProfileResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            UserResponse userResponse = response.body().getData();

                            handler.post(() -> {
                                String fullName = userResponse.getFirstName() + userResponse.getLastName();
                                tvName.setText(fullName);
                                tvEmail.setText(userResponse.getEmail());
                                Picasso.get().load(userResponse.getAvatarUrl()).into(ivPict);

                                progressBar.setVisibility(View.GONE);
                                ll.setVisibility(View.VISIBLE);
                            });
                        }
                    } else {
                        if (response.body() != null){
                            Log.e("MainActivity", "onFailure1: " + response.message());
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ProfileResponse> call, @NonNull Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    tvError.setVisibility(View.VISIBLE);
                    btnRetry.setVisibility(View.VISIBLE);

                    btnRetry.setOnClickListener(v -> {
                        progressBar.setVisibility(View.VISIBLE);
                        tvError.setVisibility(View.GONE);
                        btnRetry.setVisibility(View.GONE);
                        calling();
                    });
                }
            });
        });
    }
}