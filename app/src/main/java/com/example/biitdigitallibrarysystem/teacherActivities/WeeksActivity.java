package com.example.biitdigitallibrarysystem.teacherActivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.MainActivity;
import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.CourseTAdapter;
import com.example.biitdigitallibrarysystem.adapters.Weeks1Adapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeeksActivity extends AppCompatActivity {


    Context context;
    FloatingActionButton fbtn;
    public static int tid;
    public static String role;

    int ttid;
    RecyclerView rv_weeks;
//    JsonArray jsonArray;
//    JsonObject jsonObject;

    String name;
    int cid = 0;
    JsonArray jsonArray;
    JsonObject jsonObject = null;

    Weeks1Adapter adapter;
    List<String> weeksList1;
    Weeks1Adapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weeks);
        ttid = MainActivity.tid;
        Intent intent = getIntent();
        role = intent.getStringExtra("role");
        tid = intent.getIntExtra("tid", 0);

//        jsonArray = new JsonArray();
        context = this;

        jsonArray = new JsonArray();


        rv_weeks = findViewById(R.id.rv_week);

        cid = CourseTAdapter.idc;

        Retrofit client = APIClient.getClient();
        Endpoint endpoint = client.create(Endpoint.class);


        Call<List<String>> call = endpoint.TeacherFetchWeekAgainstCourse(cid, ttid);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {

                if (response.isSuccessful()) {
                    weeksList1 = new ArrayList<>();
                    weeksList1 = response.body();
                    adapter = new Weeks1Adapter(weeksList1, listener, context);
                    rv_weeks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv_weeks.setAdapter(adapter);
                } else {
                    Toast.makeText(WeeksActivity.this, "Upload LessonPlan First" , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(WeeksActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

            private void setOnClickListner() {
                listener = new Weeks1Adapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        Toast.makeText(getApplicationContext(), "Clicked!", Toast.LENGTH_LONG).show();
                    }
                };
            }
        });

        fbtn = findViewById(R.id.floatBtn_uploadweeks);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WeeksActivity.this, UploadLessonPlanActivity.class);
                i.putExtra("cid", cid);
                startActivity(i);

            }
        });
    }
}