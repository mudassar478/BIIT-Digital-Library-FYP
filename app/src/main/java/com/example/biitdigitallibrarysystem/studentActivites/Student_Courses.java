package com.example.biitdigitallibrarysystem.studentActivites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.Std_CoursesAdapter;
import com.example.biitdigitallibrarysystem.models.Std_CoursesModel;

import java.util.ArrayList;

public class Student_Courses extends AppCompatActivity {


    RecyclerView Rv_CourseModel;
    Std_CoursesAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_courses);

        Rv_CourseModel= findViewById(R.id.std_rv_courses);
        ArrayList<String> list = new ArrayList<>();
        adapter = new Std_CoursesAdapter(Student_Courses.this,list);
        list.add("DS");
        list.add("OOP");
        Rv_CourseModel.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Rv_CourseModel.setAdapter(adapter);
        Rv_CourseModel.setHasFixedSize(true);



    }
}