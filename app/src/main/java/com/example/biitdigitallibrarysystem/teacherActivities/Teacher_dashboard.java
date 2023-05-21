package com.example.biitdigitallibrarysystem.teacherActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.biitdigitallibrarysystem.R;

public class Teacher_dashboard extends AppCompatActivity {
    LinearLayout im, im2, im3, im4;
    TextView tvTeacherName;
    private String teacherName;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        im = findViewById(R.id.layout_course);
        im2 = findViewById(R.id.layout_studentlogs);
        im3 = findViewById(R.id.layout_Books);
        im4 = findViewById(R.id.layout_Mybooks1);
        tvTeacherName = findViewById(R.id.tv_teacherName);
        Intent intent = getIntent();
        teacherName = intent.getStringExtra("TName");
        tvTeacherName.setText("Welcome: " + teacherName + "(Teacher)");
        im.setOnClickListener(view -> {
            Intent i = new Intent(Teacher_dashboard.this, CoursesActivity.class);
            startActivity(i);
        });
        im2.setOnClickListener(view -> {
            Intent i = new Intent(Teacher_dashboard.this, StudentLogs.class);
            startActivity(i);

        });
        im3.setOnClickListener(view -> {
            Intent i = new Intent(Teacher_dashboard.this, Tech_LibraryBooks.class);
            startActivity(i);

        });
        im4.setOnClickListener(view -> {
            Intent i = new Intent(Teacher_dashboard.this, MyBookTeacher.class);
            startActivity(i);

        });

    }
}