package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.biitdigitallibrarysystem.R;

import java.util.ArrayList;
import java.util.List;

public class EditLessonPlan extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_lesson_plan);


        //spinner code
        Spinner spinner;
        List<String> items;
        spinner= findViewById(R.id.spinnerEditLessonPlan);
        items = new ArrayList<>();
        items.add("General");
        items.add("1");
        items.add("2");
        items.add("3");
        items.add("4");
        items.add("5");
        items.add("6");
        items.add("7");
        items.add("8");
        items.add("9");
        items.add("10");
        items.add("11");
        items.add("12");
        items.add("13");
        items.add("14");
        items.add("15");
        items.add("16");

        spinner.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,items));


    }
}