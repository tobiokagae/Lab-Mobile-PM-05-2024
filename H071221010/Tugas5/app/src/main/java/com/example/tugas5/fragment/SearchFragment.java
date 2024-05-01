package com.example.tugas5.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas5.DataSource;
import com.example.tugas5.User;
import com.example.tugas5.R;
import com.example.tugas5.SearchAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment {

    private ArrayList<User> user;
    private RecyclerView rvPost;
    private SearchAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPost = view.findViewById(R.id.rv_user);
        rvPost.setLayoutManager(new LinearLayoutManager(getActivity()));

        user = new ArrayList<>();

        adapter = new SearchAdapter(user);
        rvPost.setAdapter(adapter);

        ProgressBar progressBar = view.findViewById(R.id.progressBar);

        androidx.appcompat.widget.SearchView searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                progressBar.setVisibility(View.VISIBLE);
                rvPost.setVisibility(View.GONE);

                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<User> filteredList = new ArrayList<>();
                        if (!newText.isEmpty()) {
                            for (User item : DataSource.users) {
                                if (item.getUsername().toLowerCase().contains(newText.toLowerCase()) ||
                                        item.getName().toLowerCase().contains(newText.toLowerCase())) {
                                    filteredList.add(item);
                                }
                            }
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(() -> {
                            progressBar.setVisibility(View.GONE);
                            user.clear();
                            if (!newText.isEmpty()) {
                                user.addAll(filteredList);
                                rvPost.setVisibility(View.VISIBLE);
                            }
                            adapter.notifyDataSetChanged();
                        });
                    }
                };
                handler.removeCallbacks(runnable);
                executor.execute(runnable);
                return true;
            }


        });
    }
}
