package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.TimeSpentHistoryAdapter;
import com.example.biitdigitallibrarysystem.adapters.LessonPlanAdapter;
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.models.timespendModel;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeSpentHistory extends AppCompatActivity {
    RecyclerView rv_timeanddate;
    String date,currentTime;
    TimeSpentHistoryAdapter timeSpentHistoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_spent_history);

        ArrayList<timespendModel> lesson = new ArrayList<>();
        rv_timeanddate=findViewById(R.id.rv_historyLessonPlans);

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String Time = sdf.format(c.getTime());
        String fulldate = date + " " + Time;


        lesson.add(new timespendModel("Lesson Plan 6","2023-03-19  12:42:25  1:12:03"));
        lesson.add(new timespendModel("Lesson Plan 8","2023-03-19  12:42:25  1:12:03"));
        lesson.add(new timespendModel("Lesson Plan 2","2023-03-19  12:42:25  1:12:03"));
        lesson.add(new timespendModel("Lesson Plan 3","2023-03-19  12:42:25  1:12:03"));
        lesson.add(new timespendModel("Lesson Plan 1","2023-03-19  12:42:25  1:12:03"));
        lesson.add(new timespendModel("Lesson Plan 2","2023-03-19  12:42:25  1:12:03"));



        lesson.add(new timespendModel("Lesson Plan 6","2023-03-19  12:42:25  1:12:03"));
        lesson.add(new timespendModel("Lesson Plan 8","2023-03-19  12:42:25  1:12:03"));
        lesson.add(new timespendModel("Lesson Plan 2","2023-03-19  12:42:25  1:12:03"));
        lesson.add(new timespendModel("Lesson Plan 3","2023-03-19  12:42:25  1:12:03"));
        lesson.add(new timespendModel("Lesson Plan 1","2023-03-19  12:42:25  1:12:03"));
        lesson.add(new timespendModel("Lesson Plan 2","2023-03-19  12:42:25  1:12:03"));




        timeSpentHistoryAdapter = new TimeSpentHistoryAdapter(this , lesson);
        rv_timeanddate.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_timeanddate.setAdapter(timeSpentHistoryAdapter);



    }
}