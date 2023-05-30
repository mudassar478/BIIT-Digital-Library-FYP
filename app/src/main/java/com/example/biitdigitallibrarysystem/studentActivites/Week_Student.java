package com.example.biitdigitallibrarysystem.studentActivites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.MainActivity;
import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.CourseTAdapter;
import com.example.biitdigitallibrarysystem.adapters.Std_CoursesAdapter;
import com.example.biitdigitallibrarysystem.adapters.Student_WeeksAdapter;
import com.example.biitdigitallibrarysystem.adapters.Weeks1Adapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.teacherActivities.WeeksActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Week_Student extends AppCompatActivity {
    RecyclerView rv_weeks;
    String name;
    int cid = 0;
    Context context;
    JsonArray jsonArray;
    JsonObject jsonObject = null;
    int sid;
    Student_WeeksAdapter adapter;
    List<String> weeksList1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_student);
        context = this;
        rv_weeks = findViewById(R.id.student_rv_week);

        jsonArray = new JsonArray();

        sid = MainActivity.sid;
        cid = Std_CoursesAdapter.stdCourseID;



        Retrofit client = APIClient.getClient();
        Endpoint endpoint = client.create(Endpoint.class);


        Call<List<String>> call = endpoint. studentFetchWeekAgainstCourse(cid,sid);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {

                if (response.isSuccessful()) {
                    weeksList1 = new ArrayList<>();
                    weeksList1 = response.body();
                    adapter = new Student_WeeksAdapter(weeksList1, context);
                    rv_weeks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv_weeks.setAdapter(adapter);
                } else {
                    Toast.makeText(Week_Student.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(Week_Student.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });

//        Retrofit client = APIClient.getClient();
//        Endpoint endpoint = client.create(Endpoint.class);
//
//
//        Call<List<String>> call = endpoint.studentFetchWeekAgainstCourse(3,1);
//        call.enqueue(new Callback<List<String>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
//
//                if (response.isSuccessful()) {
//                    weeksList1 = new ArrayList<>();
//                    weeksList1 = response.body();
//                    adapter = new Student_WeeksAdapter(weeksList1, context);
//                    rv_weeks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                    rv_weeks.setAdapter(adapter);
//                } else {
//                    Toast.makeText(context, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<String>> call, Throwable t) {
//                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//
//        });
//    }
    }
}