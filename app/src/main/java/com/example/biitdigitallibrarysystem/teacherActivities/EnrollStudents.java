package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.MainActivity;
import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.EnrollStudentsAdapter;
import com.example.biitdigitallibrarysystem.adapters.StudentLogAdapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.EnrollStudent_Model;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EnrollStudents extends AppCompatActivity {
    RecyclerView rv_enrollment;
    EnrollStudentsAdapter enrollStudentsAdapter;
    ArrayList<EnrollStudent_Model> list;
    EnrollStudent_Model enrollStudentModel;
    JsonObject jsonObject = null;
    JsonArray jsonArray;
    int ttid = 0;
    String section;
    int teacherId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll_students);


        rv_enrollment = findViewById(R.id.rv_enrollStudents);
        ttid = MainActivity.tid;

        teacherId = StudentLogAdapter.tid;
        section = StudentLogAdapter.sectionid;
//                ArrayList<EnrollStudentModel> sections = new ArrayList<>();
        jsonArray = new JsonArray();

        list = new ArrayList<>();
        rv_enrollment = findViewById(R.id.rv_enrollStudents);

        Retrofit retrofit = APIClient.getClient();
        Endpoint endpoint = retrofit.create(Endpoint.class);
        endpoint.FetchStudent(ttid,section).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    jsonArray = response.body();
                    for (int i = 0; i < Objects.requireNonNull(jsonArray).size(); i++) {
                        enrollStudentModel = new EnrollStudent_Model();
                        jsonObject = jsonArray.get(i).getAsJsonObject();
                        int sid = jsonObject.get("sid").getAsInt();
                        String regno = jsonObject.get("reg_no").getAsString();
                        String name = jsonObject.get("name").getAsString();


                        enrollStudentModel.setSid(sid);
                        enrollStudentModel.setReg_no(regno);
                        enrollStudentModel.setName(name);

                        list.add(enrollStudentModel);

                    }

                    enrollStudentsAdapter = new EnrollStudentsAdapter(EnrollStudents.this, list);
                    rv_enrollment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv_enrollment.setAdapter(enrollStudentsAdapter);
                } else {
                    Toast.makeText(com.example.biitdigitallibrarysystem.teacherActivities.EnrollStudents.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(EnrollStudents.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}


//        endpoint.FetchStudentAgainstSection(com.example.biitdigitallibrarysystem.teacherActivities.EnrollStudents.tid).enqueue(new Callback<JsonArray>() {
//        @Override
//        public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
//            if(response.isSuccessful()) {
//                jsonArray = response.body();
//                for (int i = 0; i < Objects.requireNonNull(jsonArray).size(); i++) {
//                    enrollStudentModel = new EnrollStudentModel();
//                    jsonObject = jsonArray.get(i).getAsJsonObject();
//
//                    String section;
//                    int tid;
//
//                    section=jsonObject.get("section").getAsString();
//                    tid = jsonObject.get("tid").getAsInt();
//
//
//                    enrollStudentModel.setSection(section);
//                    enrollStudentModel.setTid(tid);
//
//
//                    list.add(enrollStudentModel);
//
//                }
//                EnrollStudentsAdapter enrollStudentsAdapter=new EnrollStudentsAdapter(com.example.biitdigitallibrarysystem.teacherActivities.EnrollStudents.this,list);
//                rv_enrollment.setAdapter(enrollStudentsAdapter);
//                rv_enrollment.setLayoutManager(new LinearLayoutManager(com.example.biitdigitallibrarysystem.teacherActivities.EnrollStudents.this));
//            }
//
//            else
//            {
//                Toast.makeText(com.example.biitdigitallibrarysystem.teacherActivities.EnrollStudents.this, ""+response.message(), Toast.LENGTH_SHORT).show();
//            }
//
//        }
//
//            @Override
//            public void onFailure(Call<JsonArray> call, Throwable t) {
//                Toast.makeText(com.example.biitdigitallibrarysystem.teacherActivities.EnrollStudents.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//


//            ArrayList<Bookscreen> week = new ArrayList<>();
//        rv_enrollment=findViewById(R.id.rv_enrollStudents);
////        week.add(new Bookscreen("Mudassar","2019-arid-0110" ));
////        week.add(new Bookscreen("Usama","2019-arid-0084" ));
////
//
//
//        enrollStudentsAdapter = new EnrollStudentsAdapter(this , week);
//        rv_enrollment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        rv_enrollment.setAdapter(enrollStudentsAdapter);
//    }
