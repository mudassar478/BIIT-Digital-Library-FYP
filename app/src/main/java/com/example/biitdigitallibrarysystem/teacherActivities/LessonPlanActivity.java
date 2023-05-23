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
    protected void onCreate(Bundle savedInstanceState) {
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
            setContentView(R.layout.table_ofcontent);

            jsonArray = new JsonArray();
            Retrofit retrofit = APIClient.getClient();
            rv_lessonplan = findViewById(R.id.rv_LessonPlan);
            Endpoint endpoint = retrofit.create(Endpoint.class);
            endpoint.TeacherFetchTableofContent(com.example.biitdigitallibrarysystem.teacherActivities.LessonPlanActivity.cid).enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    if(response.isSuccessful()) {
                        jsonArray = response.body();
                        for (int i = 0; i < Objects.requireNonNull(jsonArray).size(); i++) {
                            lessonPlanModel = new LessonPlanModel();
                            jsonObject = jsonArray.get(i).getAsJsonObject();
                            int lid,tid,cid,page_no;
                            String title, path, week;
                            title = jsonObject.get("title").getAsString();
                            path = jsonObject.get("path").getAsString();
                            week = jsonObject.get("week").getAsString();
                            lid = jsonObject.get("lid").getAsInt();
                            tid = jsonObject.get("tid").getAsInt();
                            cid = jsonObject.get("cid").getAsInt();
                            page_no = jsonObject.get("page_no").getAsInt();

                            lessonPlanModel.setLid(lid);
                            lessonPlanModel.setCid(cid);
                            lessonPlanModel.setTid(tid);
                            lessonPlanModel.setPage_no(page_no);
                            lessonPlanModel.setLid(lid);
                            lessonPlanModel.setLid(lid);
                            lessonPlanModel.setTitle(title);
                            lessonPlanModel.setPath(path);
                            lessonPlanModel.setWeek(week);


                            list.add(lessonPlanModel);

                        }
                        TableOfContentAdapter tableOfContentAdapter=new TableOfContentAdapter(com.example.biitdigitallibrarysystem.teacherActivities.LessonPlanActivity.this,list);
                        lessonPlanModel.setAdapter(lessonPlanAdapter);
                        lessonPlanModel.setLayoutManager(new LinearLayoutManager(com.example.biitdigitallibrarysystem.teacherActivities.LessonPlanActivity.this));
                    }

                    else
                    {
                        Toast.makeText(com.example.biitdigitallibrarysystem.teacherActivities.LessonPlanActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    Toast.makeText(com.example.biitdigitallibrarysystem.teacherActivities.LessonPlanActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}