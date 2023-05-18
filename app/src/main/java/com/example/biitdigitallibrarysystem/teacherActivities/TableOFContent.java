package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.TableOfContentAdapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.TableOfContentModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TableOFContent<TableOFContent> extends AppCompatActivity {

    RecyclerView table_of_content;
    ArrayList<TableOfContentModel> list = new ArrayList<>();
    TableOfContentModel tableOFContent;
    JsonObject jsonObject=null;
    JsonArray jsonArray;
    public static int bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_ofcontent);

        jsonArray = new JsonArray();
        Retrofit retrofit = APIClient.getClient();
        table_of_content = findViewById(R.id.rv_toc);
        Endpoint endpoint = retrofit.create(Endpoint.class);
        endpoint.TeacherFetchTableofContent(com.example.biitdigitallibrarysystem.teacherActivities.TableOFContent.bid).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful()) {
                    jsonArray = response.body();
                    for (int i = 0; i < Objects.requireNonNull(jsonArray).size(); i++) {
                        tableOFContent = new TableOfContentModel();
                        jsonObject = jsonArray.get(i).getAsJsonObject();
                        String title, keywords;
                        int id, bid, pageno;
                        title = jsonObject.get("title").getAsString();
                        keywords = jsonObject.get("keywords").getAsString();
                        id = jsonObject.get("id").getAsInt();
                        bid = jsonObject.get("bid").getAsInt();
                        pageno = jsonObject.get("pageno").getAsInt();

                        tableOFContent.setBid(bid);
                        tableOFContent.setId(id);
                        tableOFContent.setPageno(pageno);
                        tableOFContent.setTitle(title);
                        tableOFContent.setKeywords(keywords);


                        list.add(tableOFContent);

                    }
                    TableOfContentAdapter tableOfContentAdapter=new TableOfContentAdapter(com.example.biitdigitallibrarysystem.teacherActivities.TableOFContent.this,list);
                    table_of_content.setAdapter(tableOfContentAdapter);
                    table_of_content.setLayoutManager(new LinearLayoutManager(com.example.biitdigitallibrarysystem.teacherActivities.TableOFContent.this));
                }

                else
                {
                    Toast.makeText(com.example.biitdigitallibrarysystem.teacherActivities.TableOFContent.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(com.example.biitdigitallibrarysystem.teacherActivities.TableOFContent.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}