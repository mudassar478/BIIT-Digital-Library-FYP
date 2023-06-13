package com.example.biitdigitallibrarysystem.teacherActivities;

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
import com.example.biitdigitallibrarysystem.adapters.LessonPlanAdapter;
import com.example.biitdigitallibrarysystem.adapters.StudentLogAdapter;
import com.example.biitdigitallibrarysystem.adapters.TableOfContentAdapter;
import com.example.biitdigitallibrarysystem.adapters.Weeks1Adapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.models.LessonPlanModel;
import com.example.biitdigitallibrarysystem.models.StudentLogModel;
import com.example.biitdigitallibrarysystem.models.TableOfContentModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LessonPlanActivity extends AppCompatActivity {
    RecyclerView rv_lessonplan;
    LessonPlanAdapter adapter;
    public static int tid,lid;
    public static String role;

    ArrayList<LessonPlanModel> list;
    LessonPlanModel lessonPlanModel;
    JsonObject jsonObject = null;
    JsonArray jsonArray;
    public static int cid;
    String title, path;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_plan);

        Bundle extras = getIntent().getExtras();

        jsonArray = new JsonArray();
        Retrofit retrofit = APIClient.getClient();
        rv_lessonplan = findViewById(R.id.rv_LessonPlan);
        Intent intent=getIntent();
        role=intent.getStringExtra("role");
        tid=intent.getIntExtra("tid",0);
        int courseId = CourseTAdapter.idc;
        int teacherid = MainActivity.tid;
        String weekid = Weeks1Adapter.weekid;
        jsonArray = new JsonArray();
        list = new ArrayList<>();
        Retrofit client = APIClient.getClient();
        Endpoint endpoint = client.create(Endpoint.class);
        endpoint.TeacherFetchLessonPlanAgainstWeek(courseId, teacherid, weekid).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(@NonNull Call<JsonArray> call, @NonNull Response<JsonArray> response) {

                if (response.isSuccessful()) {

                    jsonArray = response.body();
                    for (int i = 0; i < Objects.requireNonNull(jsonArray).size(); i++) {
                        lessonPlanModel = new LessonPlanModel();
                        jsonObject = jsonArray.get(i).getAsJsonObject();
                        title = jsonObject.get("title").getAsString();
                        lid=jsonObject.get("lid").getAsInt();
                        path = jsonObject.get("path").getAsString();
                        //Toast.makeText(LessonPlanActivity.this,"title"+title,Toast.LENGTH_LONG).show();
                        lessonPlanModel.setTitle(title);
                        lessonPlanModel.setLid(lid);
                        lessonPlanModel.setPath(path);
                        //lessonPlanModel.setTitle(path);
                        list.add(lessonPlanModel);
                    }

                    adapter = new LessonPlanAdapter(LessonPlanActivity.this, list);
                    rv_lessonplan.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv_lessonplan.setAdapter(adapter);
                } else {
                    Toast.makeText(LessonPlanActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {


                Toast.makeText(LessonPlanActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
