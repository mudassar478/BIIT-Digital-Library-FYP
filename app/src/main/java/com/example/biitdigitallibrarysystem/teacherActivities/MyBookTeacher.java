package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.biitdigitallibrarysystem.R;


public class MyBookTeacher extends AppCompatActivity {
Button btnUploadbook;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book_teachser);
        btnUploadbook= findViewById(R.id.btnUploadBook);

        btnUploadbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyBookTeacher.this, UploadBook.class);
                startActivity(i);
            }
        });

    }
}