package com.example.tugas6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvItem;
    TextView tvError;
    ProgressBar pb;
    Button btnRetry, btnLoad;
    UserAdapter userAdapter;
    int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvItem = findViewById(R.id.rv_item);
        tvError = findViewById(R.id.tv_error);
        pb = findViewById(R.id.progress_bar);
        btnRetry = findViewById(R.id.btn_retry);
        btnLoad = findViewById(R.id.btn_load);

        userAdapter = new UserAdapter(new ArrayList<>());
        rvItem.setLayoutManager(new LinearLayoutManager(this));
        rvItem.setAdapter(userAdapter);

        calling();

        btnLoad.setOnClickListener(v -> {
            currentPage++;
//            loadMore();
            calling();
        });

        btnRetry.setOnClickListener(v -> {
            pb.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.GONE);
            btnRetry.setVisibility(View.GONE);
            calling();
        });
    }

    public void calling() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        tvError.setVisibility(View.GONE);
        btnRetry.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);
        btnLoad.setVisibility(View.GONE);

        executorService.execute(() -> {
            Call<DataResponse> client = ApiConfig.getApiService().getData(String.valueOf(currentPage));
            client.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(@NonNull Call<DataResponse> call, @NonNull Response<DataResponse> response) {
                    if (response.isSuccessful()) {
                        rvItem.setVisibility(View.VISIBLE);
                        if (response.body() != null) {
                            ArrayList<UserResponse> userResponses = response.body().getData();
                            userAdapter.addData(userResponses);
                            pb.setVisibility(View.GONE);
                            btnLoad.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (response.body() != null) {
                            Log.e("MainActivity", "onFailure: " + response.message());
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<DataResponse> call, @NonNull Throwable t) {
                    pb.setVisibility(View.GONE);
                    tvError.setVisibility(View.VISIBLE);
                    btnRetry.setVisibility(View.VISIBLE);
                    rvItem.setVisibility(View.GONE);
                }
            });
        });
    }
}