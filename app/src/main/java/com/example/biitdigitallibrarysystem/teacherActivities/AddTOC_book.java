package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.MainActivity;
import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.MyBookTeacherAdapter;
import com.example.biitdigitallibrarysystem.adapters.TableOfContentAdapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.LibraryBook;
import com.example.biitdigitallibrarysystem.models.TableOfContentModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddTOC_book extends AppCompatActivity {

    EditText edt_AddTOCbook, edt_TOCPage;
    Button btn_addToc;
    RecyclerView rv_TOCbook;
    ArrayList<TableOfContentModel> list = new ArrayList<>();
    TableOfContentModel tableOFContent;
    JsonObject jsonObject=null;
    JsonArray jsonArray;
    public static int bid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_toc_book);
        Retrofit retrofit = APIClient.getClient();
        Endpoint endpoint = retrofit.create(Endpoint.class);
        edt_AddTOCbook=findViewById(R.id.edt_AddTOCbook);
       edt_TOCPage= findViewById(R.id.edt_TOCPage);
        btn_addToc= findViewById(R.id.btn_addToc);
        rv_TOCbook=  findViewById(R.id.rv_TOCbook);

        btn_addToc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // TableOFContent.bid=books.get(position).getBid();

                bid = TableOFContent.bid;

                endpoint.TeacherUploadTableofContent(bid,"Select query","20","select,select data from database" ).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(AddTOC_book.this, ""+response.code(), Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });


            }
        });

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
                    TableOfContentAdapter tableOfContentAdapter=new TableOfContentAdapter(getApplicationContext(),list);
                    rv_TOCbook.setAdapter(tableOfContentAdapter);
                    rv_TOCbook.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }

                else
                {
                    Toast.makeText(getApplicationContext(), ""+response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(getApplicationContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}