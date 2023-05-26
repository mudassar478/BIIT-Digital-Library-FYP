package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UploadLessonPlanActivity extends AppCompatActivity {
    Button btnsave , btnchoose ;
    int tid , cid ;
    String title , week;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_lesson_plan);
        btnsave = findViewById(R.id.save_lessonplan);
        btnchoose = findViewById(R.id.btnCHooseFile);
        Intent intent = getIntent();
        tid = intent.getIntExtra("tid",0);
        cid = intent.getIntExtra("cid",0);

        //spinner code
        Spinner spinner;
        List<String> items;
        spinner = findViewById(R.id.spinnerUploadLesson);
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


        btnchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = APIClient.getClient();
                Endpoint endpoint = retrofit.create(Endpoint.class);
                endpoint.teacherUpload(tid , cid , title , week).enqueue(new Callback<ResponseBody>()
                {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(UploadLessonPlanActivity.this, "Lesson Upload Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(UploadLessonPlanActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(UploadLessonPlanActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}