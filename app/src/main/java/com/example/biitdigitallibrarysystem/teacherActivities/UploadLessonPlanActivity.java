package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UploadLessonPlanActivity extends AppCompatActivity {
    Button btnsave , btnchoose ;
    int tid , cid ;
    String title , week;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_lesson_plan);
        btnsave = findViewById(R.id.save_lessonplan);
        btnchoose = findViewById(R.id.btnCHooseFile);
        Intent intent = getIntent();
        tid = intent.getIntExtra("tid",0);
        cid = intent.getIntExtra("cid",0);
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
                endpoint.teacherUpload(tid , cid , title , week).enqueue(new Callback<ResponseBody>() {
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