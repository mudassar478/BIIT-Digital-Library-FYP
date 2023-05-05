package com.example.biitdigitallibrarysystem.studentActivites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.teacherActivities.CoursesActivity;
import com.example.biitdigitallibrarysystem.teacherActivities.StudentLogs;
import com.example.biitdigitallibrarysystem.teacherActivities.Teacher_dashboard;
import com.example.biitdigitallibrarysystem.teacherActivities.Tech_LibraryBooks;

public class St_SaveItems extends AppCompatActivity {
    ImageView im1,im2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.st_save_items);
        im1 = findViewById(R.id.img_std_SvCourses);
        im2 = findViewById(R.id.img_std_SvBooks);

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(St_SaveItems.this,CoursesActivity.class);
                startActivity(i);

            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(St_SaveItems.this, Tech_LibraryBooks.class);
                startActivity(i);

            }
        });


    }


}