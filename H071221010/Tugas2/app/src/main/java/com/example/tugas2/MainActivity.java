package com.example.tugas2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;
    private ImageView pic;
    private EditText name;
    private EditText username;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnSubmit);
        pic = findViewById(R.id.addPic);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);

        btn.setOnClickListener(view -> {
            String nameValue = name.getText().toString();
            String usernameValue = username.getText().toString();

            Intent submit = new Intent(this, ActivityTwo.class);
            if (!nameValue.isEmpty()) {
                submit.putExtra("NAME", nameValue);
            } else {
                name.setError("Please enter your name");
                return;
            }

            if (!usernameValue.isEmpty()) {
                submit.putExtra("USERNAME", usernameValue);
            } else {
                username.setError("Please enter your username");
                return;
            }

            if (selectedImageUri != null) {
                submit.putExtra("IMAGE_URI", selectedImageUri.toString());
            } else {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
                return;
            }

            startActivity(submit);
        });


        pic.setOnClickListener(view -> {
            Intent gallryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallryIntent, PICK_IMAGE_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent image) {
        super.onActivityResult(requestCode, resultCode, image);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && image != null && image.getData() != null) {
            selectedImageUri = image.getData();
            pic.setImageURI(selectedImageUri);
        }
    }
}