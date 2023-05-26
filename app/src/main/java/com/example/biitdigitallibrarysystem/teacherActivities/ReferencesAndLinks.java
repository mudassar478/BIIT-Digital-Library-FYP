package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.MainActivity;
import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.CourseTAdapter;
import com.example.biitdigitallibrarysystem.adapters.LessonPlanAdapter;
import com.example.biitdigitallibrarysystem.adapters.ReferencesAndLinksAdapter;
import com.example.biitdigitallibrarysystem.adapters.Weeks1Adapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.models.LessonPlanModel;
import com.example.biitdigitallibrarysystem.models.ReferenceAndLinkModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReferencesAndLinks extends AppCompatActivity {
    Spinner spinner;
    List<String> items;
    RecyclerView recyclerView;
    ReferencesAndLinksAdapter adapter ;
    ;
//    ReferenceAndLinkModel referenceAndLinkModel;
    ArrayList<ReferenceAndLinkModel> list = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.references_and_links);
        recyclerView = findViewById(R.id.rv_referencesLinks);
//        referenceAndLinkModel = new ReferenceAndLinkModel("C++");
//        list.add(referenceAndLinkModel);
//        adapter = new ReferencesAndLinksAdapter(this,list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setAdapter(adapter);

        //spinner code
        spinner = findViewById(R.id.spinnerReferencesLinks);
        items = new ArrayList<>();
        items.add("Public");
        items.add("Private");

        spinner.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,items));


        int courseId = CourseTAdapter.idc;
        int teacherid = MainActivity.tid;
        String weekid = Weeks1Adapter.weekid;
        int lid = LessonPlanAdapter.lid;
        APIClient  client= APIClient.getInstance();
        Endpoint api = client.getMyApi();

        api.TeacherFetchRefrences(teacherid,lid,"teacher").enqueue(new Callback<ArrayList<LessonPlanModel>>() {
            @Override
            public void onResponse(Call<ArrayList<LessonPlanModel>> call, Response<ArrayList<LessonPlanModel>> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
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