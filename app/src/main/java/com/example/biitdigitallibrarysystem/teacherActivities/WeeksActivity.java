package com.example.biitdigitallibrarysystem.teacherActivities;

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
import com.example.biitdigitallibrarysystem.adapters.WeeksAdapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.WeekNumModel;
import com.example.biitdigitallibrarysystem.models.WeeksModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeeksActivity extends AppCompatActivity {

    RecyclerView rv_weeks;
    WeeksAdapter weeksAdapter;
    FloatingActionButton fbtn;
    ArrayList<WeeksModel> list;
    ArrayList<WeekNumModel> week_list;
    WeeksModel weeksModel;
    int ttid;
//    JsonArray jsonArray;
//    JsonObject jsonObject;

    String name;
    int cid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weeks);
        ttid= MainActivity.tid;
//        jsonArray = new JsonArray();
        list = new ArrayList<>();
        week_list = new ArrayList<>();
        RecyclerView rv_weeks = findViewById(R.id.rv_week);
        Intent intent = getIntent();
        cid = intent.getIntExtra("cid", 0);
        Retrofit client = APIClient.getClient();
        Endpoint endpoint = client.create(Endpoint.class);
        endpoint.TeacherFetchLessonplan(cid,ttid).enqueue(new Callback<ArrayList<WeekNumModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<WeekNumModel>> call, @NonNull Response<ArrayList<WeekNumModel>> response) {

                if (response.isSuccessful()) {
                    week_list = response.body();

                    WeeksAdapter weeksAdapter = new WeeksAdapter(WeeksActivity.this, week_list);
                    rv_weeks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv_weeks.setAdapter(weeksAdapter);
                } else {
                    Toast.makeText(WeeksActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<WeekNumModel>> call, Throwable t) {


                Toast.makeText(WeeksActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        fbtn = findViewById(R.id.floatBtn_uploadweeks);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WeeksActivity.this, UploadLessonPlanActivity.class);
                intent.putExtra("tid",week_list.get(0).getList().get(0).getTid());
                intent.putExtra("cid",week_list.get(0).getList().get(0).getCid());
                startActivity(i);

            }
        });
    }
}