package com.example.tugas5.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tugas5.DataSource;
import com.example.tugas5.PostAdapter;
import com.example.tugas5.R;
import com.example.tugas5.User;

public class HomeFragment extends Fragment {

    public static final String EXTRA_USER = "extra_user";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvPost = view.findViewById(R.id.rv_post);
        rvPost.setHasFixedSize(true);
        PostAdapter postAdapter = new PostAdapter(DataSource.users);
        rvPost.setAdapter(postAdapter);

        Bundle bundle = getArguments();
        if (bundle != null) {
            User user = bundle.getParcelable(EXTRA_USER);
            if (user != null) {
                DataSource.users.add(0, user);
//                postAdapter.notifyDataSetChanged();
            }
        }
    }
}