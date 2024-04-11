package com.example.tugas3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Story */
        RecyclerView rvStory = findViewById(R.id.rv_story);
        rvStory.setHasFixedSize(true);
        StoryAdapter storyAdapter = new StoryAdapter(DataSource.users);
        rvStory.setAdapter(storyAdapter);

        /* Postingan */
        RecyclerView rvPost = findViewById(R.id.rv_post);
        rvPost.setHasFixedSize(true);
        PostAdapter postAdapter = new PostAdapter(DataSource.users);
        rvPost.setAdapter(postAdapter);
    }
}