package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.LessonPlanAdapter;
import com.example.biitdigitallibrarysystem.adapters.TableOfContentAdapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.models.LessonPlanModel;
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
    LessonPlanAdapter lessonPlanAdapter;

    ArrayList<LessonPlanModel> list= new ArrayList<>();
    LessonPlanModel lessonPlanModel;
    JsonObject jsonObject=null;
    JsonArray jsonArray;
    public static int cid;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.lesson_plan);
//        ArrayList<Bookscreen> lesson = new ArrayList<>();
//        rv_lessonplan=findViewById(R.id.rv_LessonPlan);
//        lesson.add(new Bookscreen("Lesson Plan 1"));
//        lesson.add(new Bookscreen("Lesson Plan 2"));
//        lesson.add(new Bookscreen("Lesson Plan 3"));
//        lesson.add(new Bookscreen("Lesson Plan 4"));
//
//
//        lessonPlanAdapter = new LessonPlanAdapter(this , lesson);
//        rv_lessonplan.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        rv_lessonplan.setAdapter(lessonPlanAdapter);
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.lesson_plan);

            Bundle extras = getIntent().getExtras();
            String cid = extras.getString("cid").toString();
            String tid = extras.getString("tid","abc").toString();
            String weeks = extras.getString("week","def").toString();
//            Toast.makeText(getApplicationContext(),"weeks: "+weeks,Toast.LENGTH_LONG).show();
            jsonArray = new JsonArray();
            Retrofit retrofit = APIClient.getClient();
            rv_lessonplan = findViewById(R.id.rv_LessonPlan);
            Endpoint endpoint = retrofit.create(Endpoint.class);
            endpoint.TeacherFetchLessonPlanAgainstWeek(Integer.parseInt(cid),Integer.parseInt(tid),weeks).enqueue(new Callback<ArrayList<LessonPlanModel>>() {
                @Override
                public void onResponse(Call<ArrayList<LessonPlanModel>> call, Response<ArrayList<LessonPlanModel>> response) {
                    if (response.isSuccessful())
                    {
                        if(response.body()!=null) {
                            //Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                            list.clear();
                            list.addAll(response.body());

                            lessonPlanAdapter = new LessonPlanAdapter(getApplicationContext(), list);
                            rv_lessonplan = findViewById(R.id.rv_LessonPlan);
                            rv_lessonplan.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rv_lessonplan.setAdapter(lessonPlanAdapter);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "No Lesson Plans Found!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Error: "+response.code(),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<LessonPlanModel>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });

        }
    }
