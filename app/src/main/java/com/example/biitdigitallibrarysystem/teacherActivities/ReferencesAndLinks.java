package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.GlobalData;
import com.example.biitdigitallibrarysystem.MainActivity;
import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.CourseTAdapter;
import com.example.biitdigitallibrarysystem.adapters.LessonPlanAdapter;
import com.example.biitdigitallibrarysystem.adapters.ReferanceAdapter;
import com.example.biitdigitallibrarysystem.adapters.Weeks1Adapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.LoginModel;
import com.example.biitdigitallibrarysystem.models.ReferencesModel;
import com.example.biitdigitallibrarysystem.models.YourDataModel;
import com.example.biitdigitallibrarysystem.studentActivites.Student_dashboard;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReferencesAndLinks extends AppCompatActivity {
    Spinner spinner;
    List<String> items;
    RecyclerView recyclerView;
    ReferanceAdapter adapter;
    public static int tid, lid;
    public static String role;
    TextView textView;
    Button button;
    String selectedItem;
    String text;


    ArrayList<ReferencesModel> list;
    ReferencesModel referencesModel;
    JsonObject jsonObject = null;
    JsonArray jsonArray;

    //    ReferencesModel referencesModel;
//    ArrayList<ReferencesModel> list = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.references_and_links);
        recyclerView = findViewById(R.id.rv_referencesLinks);
        textView = findViewById(R.id.referensces_text);

        button = findViewById(R.id.btn_saveRefrences);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                text = textView.getText().toString();

                int tid = MainActivity.tid;
                int lid = LessonPlanAdapter.lid;
                String Role = MainActivity.userName;





                YourDataModel dataModel = new YourDataModel();
                dataModel.setSourceId(tid);
                dataModel.setLid(lid);
                dataModel.setContent(text);
                dataModel.setType(selectedItem);
                dataModel.setSourceName(role);

                Retrofit retrofit = APIClient.getClient();
                Endpoint endpoint = retrofit.create(Endpoint.class);
                ReferencesModel model=new ReferencesModel(text,selectedItem,role,tid,lid);

                endpoint.teacheraddrefrences(model).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()) {


                            Toast.makeText(ReferencesAndLinks.this, "inserted Successfully", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ReferencesAndLinks.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }

                });

            }
        });

        Intent i = getIntent();
        tid = i.getIntExtra("tid", 0);
        lid = i.getIntExtra("lid", 0);
        role = i.getStringExtra("role");

        //spinner code
        spinner = findViewById(R.id.spinnerReferencesLinks);
        items = new ArrayList<>();
        items.add("Public");
        items.add("Private");

        spinner.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = (String) spinner.getItemAtPosition(i);
                Toast.makeText(ReferencesAndLinks.this, "" + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        int courseId = CourseTAdapter.idc;
        int teacherid = MainActivity.tid;
        String role = MainActivity.loginRole;
        String weekid = Weeks1Adapter.weekid;
        int lid = LessonPlanAdapter.lid;


//        APIClient  client= APIClient.getInstance();
//        Endpoint api = client.getMyApi();
//
//        Toast.makeText(ReferencesAndLinks.this,"id"+tid+lid+role,Toast.LENGTH_LONG).show();
//        api.TeacherFetchRefrences(tid,lid,role).enqueue(new Callback<ArrayList<LessonPlanModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<LessonPlanModel>> call, Response<ArrayList<LessonPlanModel>> response) {
//                if(response.isSuccessful())
//                {
//                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"Error: "+response.code(),Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<LessonPlanModel>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        });

        jsonArray = new JsonArray();
        list = new ArrayList<>();
        Retrofit client = APIClient.getClient();
        Endpoint endpoint = client.create(Endpoint.class);
        endpoint.teacherfetchrefrences(tid, lid, role).enqueue(new Callback<ArrayList<ReferencesModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ReferencesModel>> call, @NonNull Response<ArrayList<ReferencesModel>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    list.addAll(response.body());
                    adapter = new ReferanceAdapter(ReferencesAndLinks.this, list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(ReferencesAndLinks.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ReferencesModel>> call, Throwable t) {


                Toast.makeText(ReferencesAndLinks.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}