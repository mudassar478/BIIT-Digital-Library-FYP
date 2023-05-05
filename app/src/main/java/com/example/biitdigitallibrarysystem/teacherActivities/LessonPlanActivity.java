package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.LessonPlanAdapter;
import com.example.biitdigitallibrarysystem.models.Bookscreen;

import java.util.ArrayList;

public class LessonPlanActivity extends AppCompatActivity {
    RecyclerView rv_lessonplan;
    LessonPlanAdapter lessonPlanAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_plan);
        ArrayList<Bookscreen> lesson = new ArrayList<>();
        rv_lessonplan=findViewById(R.id.rv_LessonPlan);
        lesson.add(new Bookscreen("Lesson Plan 1"));
        lesson.add(new Bookscreen("Lesson Plan 2"));
        lesson.add(new Bookscreen("Lesson Plan 3"));
        lesson.add(new Bookscreen("Lesson Plan 4"));




        lessonPlanAdapter = new LessonPlanAdapter(this , lesson);
        rv_lessonplan.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_lessonplan.setAdapter(lessonPlanAdapter);
    }
}