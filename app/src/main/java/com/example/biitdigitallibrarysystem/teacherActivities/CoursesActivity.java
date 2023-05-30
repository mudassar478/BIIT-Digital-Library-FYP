package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.MainActivity;
import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.CourseTAdapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.CoursesClassModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class
CoursesActivity extends AppCompatActivity {
    RecyclerView rv_course;
    ArrayList<CoursesClassModel> list;
    CourseTAdapter adapter;
    CoursesClassModel coursesClassModel;
    JsonArray jsonArray;
    JsonObject jsonObject = null;

    String name;
    int cid = 0;
    int ttid;
    public static int id;
    public static int tid;
    public static String role;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        Intent intent=getIntent();
         role=intent.getStringExtra("role");
         tid=intent.getIntExtra("tid",0);
        jsonArray = new JsonArray();
        list = new ArrayList<>();
        cid = MainActivity.mGetUserID();
        ttid = MainActivity.tid;
        rv_course = findViewById(R.id.rvCourses);
        Retrofit client = APIClient.getClient();
        Endpoint endpoint = client.create(Endpoint.class);
        endpoint.TeacherCourses(ttid).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(@NonNull Call<JsonArray> call, @NonNull Response<JsonArray> response) {

                if (response.isSuccessful()) {

                    jsonArray = response.body();
                    for (int i = 0; i < Objects.requireNonNull(jsonArray).size(); i++) {
                        coursesClassModel = new CoursesClassModel();
                        jsonObject = jsonArray.get(i).getAsJsonObject();
                        id = jsonObject.get("cid").getAsInt();
                        String name = jsonObject.get("name").getAsString();
                        coursesClassModel.setcid((id));
                        coursesClassModel.setname(name);
                        list.add(coursesClassModel);
                    }
                    adapter = new CourseTAdapter(CoursesActivity.this, list);
                    rv_course.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv_course.setAdapter(adapter);
                } else {
                    Toast.makeText(CoursesActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {


                Toast.makeText(CoursesActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}


//        rv_course = findViewById(R.id.rvCourses);
//        list = new ArrayList<>();
//        adapter = new CourseTAdapter(CoursesActivity.this , list);
//        rv_course.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        rv_course.setAdapter(adapter);
//        rv_course.setHasFixedSize(true);


