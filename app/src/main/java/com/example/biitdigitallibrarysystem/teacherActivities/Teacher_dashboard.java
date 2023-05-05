package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.biitdigitallibrarysystem.R;

public class Teacher_dashboard extends AppCompatActivity {
 ImageView im,im2,im3,im4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
     im=findViewById(R.id.img_course);
        im2=findViewById(R.id.img_studentlogs);
        im3=findViewById(R.id.img_books);
        im4=findViewById(R.id.img_mybooks);
     im.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent i=new Intent(Teacher_dashboard.this,CoursesActivity.class);
             startActivity(i);

         }
     });


        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Teacher_dashboard.this,StudentLogs.class);
                startActivity(i);

            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Teacher_dashboard.this, Tech_LibraryBooks.class);
                startActivity(i);

            }
        });

    }
}