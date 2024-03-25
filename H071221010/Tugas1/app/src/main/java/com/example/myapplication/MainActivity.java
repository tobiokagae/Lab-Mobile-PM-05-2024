package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public Button btn;
    public EditText editText;
    public ListView listView;
    public ArrayList<String> dataList;
    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.buttonOk);
        editText = findViewById(R.id.inputLainnya);
        listView = findViewById(R.id.listLainnya);

        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newText = editText.getText().toString().trim();
                if (!newText.isEmpty()) {
                    dataList.add(newText);
                    adapter.notifyDataSetChanged();
                    editText.getText().clear();
                }
            }
        });
    }
}