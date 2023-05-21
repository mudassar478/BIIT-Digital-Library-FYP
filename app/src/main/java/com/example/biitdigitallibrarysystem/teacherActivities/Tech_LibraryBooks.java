package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.LibraryBooksAdapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.models.LibraryBook;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Tech_LibraryBooks extends AppCompatActivity {
    RecyclerView librarybooks;

    LibraryBooksAdapter booksAdapter;
    ArrayList<LibraryBook> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books);

        ArrayList<Bookscreen> books = new ArrayList<>();
        librarybooks=findViewById(R.id.rv_Librarybooks);
        Retrofit retrofit = APIClient.getClient();
        Endpoint endpoint = retrofit.create(Endpoint.class);
        endpoint.fetchLibraryBooks().enqueue(new Callback<ArrayList<LibraryBook>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<LibraryBook>> call, @NonNull Response<ArrayList<LibraryBook>> response) {
                if(response.isSuccessful())
                {
                    list = response.body();
                    librarybooks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    booksAdapter = new LibraryBooksAdapter(Tech_LibraryBooks.this , list);
                    librarybooks.setAdapter(booksAdapter);
                    librarybooks.setHasFixedSize(true);
                }
                else
                {
                    Toast.makeText(Tech_LibraryBooks.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<LibraryBook>> call, Throwable t) {
                Toast.makeText(Tech_LibraryBooks.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}