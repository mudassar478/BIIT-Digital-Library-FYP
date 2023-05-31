package com.example.biitdigitallibrarysystem.studentActivites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.MainActivity;
import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.CourseTAdapter;
import com.example.biitdigitallibrarysystem.adapters.Std_CoursesAdapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.CoursesClassModel;
import com.example.biitdigitallibrarysystem.models.Std_CoursesModel;
import com.example.biitdigitallibrarysystem.teacherActivities.CoursesActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Student_Courses extends AppCompatActivity {


    RecyclerView rv_Course;
    ArrayList<Std_CoursesModel> list;
    Std_CoursesAdapter adapter;
    Std_CoursesModel std_coursesModel;
    JsonArray jsonArray;
    JsonObject jsonObject = null;

    int sid=MainActivity.sid;
//    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_courses);

        jsonArray = new JsonArray();
        list = new ArrayList<>();

        sid = MainActivity.sid;
        rv_Course = findViewById(R.id.std_rv_courses);
        Retrofit client =APIClient.getClient();
        Endpoint endpoint = client.create(Endpoint.class);
        endpoint.Course(sid).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful())
                {

                    jsonArray = response.body();
//                    Toast.makeText(Student_Courses.this,"Response"+jsonArray,Toast.LENGTH_LONG).show();
                    if (jsonArray != null && jsonArray.size() > 0)
                    {
                        //ArrayList<Std_CoursesModel> coursesList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.size(); i++) {
                            std_coursesModel = new Std_CoursesModel();
                            jsonObject = jsonArray.get(i).getAsJsonObject();
                            int id = jsonObject.get("cid").getAsInt();
                            String name = jsonObject.get("name").getAsString();
                            std_coursesModel.setCid(id);
                            std_coursesModel.setName(name);
                            list.add(std_coursesModel);
                        }
                        adapter = new Std_CoursesAdapter(Student_Courses.this, list);
                        rv_Course.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        rv_Course.setAdapter(adapter);
                    }
                    else {
                        Toast.makeText(Student_Courses.this, "No courses available", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Student_Courses.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(Student_Courses.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        Intent i= getIntent();
        int S_ID=i.getIntExtra("S_ID",0);



        rv_Course.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        rv_Course.setAdapter(adapter);

    }

}











//
//        ArrayList<String> list = new ArrayList<>();
//        adapter = new Std_CoursesAdapter(Student_Courses.this,list);
//        list.add("DS");
//        list.add("OOP");
//        Rv_CourseModel.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        Rv_CourseModel.setAdapter(adapter);
//        Rv_CourseModel.setHasFixedSize(true);




