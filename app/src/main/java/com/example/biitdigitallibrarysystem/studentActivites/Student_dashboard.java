package com.example.biitdigitallibrarysystem.studentActivites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.teacherActivities.CoursesActivity;
import com.example.biitdigitallibrarysystem.teacherActivities.Teacher_dashboard;
import com.example.biitdigitallibrarysystem.teacherActivities.Tech_LibraryBooks;

public class Student_dashboard extends AppCompatActivity {
    ImageView im1,im2,im3;
    TextView tv_student;
    private String studentName;
    private int student_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_dashboard);
        im1=findViewById(R.id.img_std_Courses);
        im2=findViewById(R.id.img_std_Books);
        im3=findViewById(R.id.img_std_Bookmarks);
        tv_student=findViewById(R.id.tv_student);

        Intent intent = getIntent();
        studentName = intent.getStringExtra("TName");
        String Reg_no = intent.getStringExtra("U_ID");
        student_id = intent.getIntExtra("S_ID",0);
       // Toast.makeText(Student_dashboard.this,"Student id"+student_id,Toast.LENGTH_LONG).show();
        tv_student.setText("Welcome \n" + studentName + "\n" + Reg_no );
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Student_dashboard.this, Student_Courses.class);
                i.putExtra("S_ID",student_id);
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