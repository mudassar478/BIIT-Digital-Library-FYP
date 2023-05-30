package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.LibraryBooksAdapter;
import com.example.biitdigitallibrarysystem.adapters.MyBookTeacherAdapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.models.LibraryBook;
import com.example.biitdigitallibrarysystem.models.MyBookTeacherModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MyBookTeacher extends AppCompatActivity {
//    RecyclerView rv_TeacherUploadbook;
//    MyBookTeacherAdapter myBookTeacherAdapter;
//    ArrayList<LibraryBook> list;
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_book_teachser);
//        ArrayList<Bookscreen> bookscreens = new ArrayList<>();
//       rv_TeacherUploadbook = findViewById(R.id.rv_TeacherUploadbook);
//        Retrofit retrofit = APIClient.getClient();
//        Endpoint endpoint= retrofit.create(Endpoint.class);
//        endpoint.fetchOwnBooks().enqueue((new Callback<ArrayList<LibraryBook>>() {
//            @Override
//            public void onResponse(Call<ArrayList<LibraryBook>> call, Response<ArrayList<LibraryBook>> response) {
//              if(response.isSuccessful())
//              {
//                  list=new ArrayList<>();
//                  list.clear();
//                  list = response.body();
//                  rv_TeacherUploadbook.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                  rv_TeacherUploadbook.setAdapter(myBookTeacherAdapter);
//                  rv_TeacherUploadbook.setHasFixedSize(true);
//
//
//              }
//                else
//              {
//                  Toast.makeText(MyBookTeacher.this, ""+response.message(), Toast.LENGTH_SHORT).show();
//              }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<LibraryBook>> call, Throwable t) {
//                Toast.makeText(MyBookTeacher.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        }));
//
//        SearchView searchView = findViewById(R.id.searchViewLibraryBooks);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Retrofit retrofit1 = APIClient.getClient();
//                Endpoint endpoint = retrofit.create(Endpoint.class);
//                Toast.makeText(getApplicationContext(),"Query: "+searchView.getQuery().toString(),Toast.LENGTH_LONG).show();
//                endpoint.SearchLibarryBook(searchView.getQuery().toString()).enqueue(new Callback<ArrayList<LibraryBook>>() {
//                    @Override
//                    public void onResponse(Call<ArrayList<LibraryBook>> call, Response<ArrayList<LibraryBook>> response) {
//                        if(response.isSuccessful())
//                        {
//                            list = new ArrayList<>();
//                            list.clear();
//                            list = response.body();
//                            rv_TeacherUploadbook.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            myBookTeacherAdapter = new MyBookTeacherAdapter(MyBookTeacher.this , list);
//                            rv_TeacherUploadbook.setAdapter(myBookTeacherAdapter);
//                            rv_TeacherUploadbook.setHasFixedSize(true);
//                        }
//                        else{
//                            Toast.makeText(getApplicationContext(),"Error: "+response.code(),Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ArrayList<LibraryBook>> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//                });
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//
//
//    }
//}




//    ----------------------------------------------------------------------------------------------
    Button btnUploadbook;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book_teachser);
        btnUploadbook = findViewById(R.id.btnUploadBook);

        RecyclerView recyclerView = findViewById(R.id.rv_TeacherUploadbook);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<MyBookTeacherModel> itemList = new ArrayList<>();
        itemList.add(new MyBookTeacherModel("Item 1", "Description 1"));
        itemList.add(new MyBookTeacherModel("Item 2", "Description 2"));
        MyBookTeacherAdapter adapter = new MyBookTeacherAdapter(itemList); // Replace 'itemList' with your actual list of items
        recyclerView.setAdapter(adapter);

        btnUploadbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyBookTeacher.this, UploadBook.class);
                startActivity(i);
            }
        });

    }
}
