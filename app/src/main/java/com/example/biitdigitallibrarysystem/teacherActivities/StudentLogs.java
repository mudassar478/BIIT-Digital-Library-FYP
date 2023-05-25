package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.MainActivity;
import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.CourseTAdapter;
import com.example.biitdigitallibrarysystem.adapters.StudentLogAdapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.CoursesClassModel;
import com.example.biitdigitallibrarysystem.models.SectionsInfo;
import com.example.biitdigitallibrarysystem.models.StudentLogModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StudentLogs extends AppCompatActivity {
    RecyclerView Students;
    StudentLogAdapter adapter;
    ArrayList<StudentLogModel> list ;
    StudentLogModel studentLogModel;
    JsonArray jsonArray;
public static String section;
    String name;
    int ttid = 0 ;
    JsonObject jsonObject=null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_logs);

        Students= findViewById(R.id.rv_Studentslog);
        ttid=MainActivity.tid;
      ArrayList<SectionsInfo> sections = new ArrayList<>();
        jsonArray = new JsonArray();
        list = new ArrayList<>();
//        tid = MainActivity.mGetUserID();
        Students = findViewById(R.id.rv_Studentslog);
        Retrofit client = APIClient.getClient();
        Endpoint endpoint = client.create(Endpoint.class);
        endpoint.fetchSection(ttid).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(@NonNull Call<JsonArray> call, @NonNull Response<JsonArray> response) {

                if (response.isSuccessful()) {

                    jsonArray = response.body();
                    for (int i = 0; i < Objects.requireNonNull(jsonArray).size(); i++) {
                        studentLogModel = new StudentLogModel();
                        jsonObject = jsonArray.get(i).getAsJsonObject();
                         section=jsonObject.get("section").getAsString();
                        studentLogModel.setsections(section);
                        list.add(studentLogModel);
                    }
                    adapter = new StudentLogAdapter(StudentLogs.this , list);
                    Students.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    Students.setAdapter(adapter);
                } else {
                    Toast.makeText(StudentLogs.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {


                Toast.makeText(StudentLogs.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    }