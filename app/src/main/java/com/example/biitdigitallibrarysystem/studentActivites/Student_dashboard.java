package com.example.biitdigitallibrarysystem.studentActivites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.teacherActivities.CoursesActivity;
import com.example.biitdigitallibrarysystem.teacherActivities.Teacher_dashboard;
import com.example.biitdigitallibrarysystem.teacherActivities.Tech_LibraryBooks;

public class Student_dashboard extends AppCompatActivity {
    ImageView im1,im2,im3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_dashboard);
        im1=findViewById(R.id.img_std_Courses);
        im2=findViewById(R.id.img_std_Books);
        im3=findViewById(R.id.img_std_Bookmarks);

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Student_dashboard.this, CoursesActivity.class);
                startActivity(i);

            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Student_dashboard.this, Tech_LibraryBooks.class);
                startActivity(i);

            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Student_dashboard.this, St_SaveItems.class);
                startActivity(i);

            }
        });

    }
}