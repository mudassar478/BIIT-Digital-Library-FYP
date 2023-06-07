package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.LibraryBooksAdapter;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.models.LibraryBook;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Tech_LibraryBooks extends AppCompatActivity {
    RecyclerView librarybooks;
    Button btnMyBook;
//sre
    EditText editTextsearch;
    ImageView imgsearch,imageback;
    TextView tvteacher;

    LibraryBooksAdapter booksAdapter;
    ArrayList<LibraryBook> list;
//endsre

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books);


        ArrayList<Bookscreen> books = new ArrayList<>();
        librarybooks = findViewById(R.id.rv_Librarybooks);
        Retrofit retrofit = APIClient.getClient();
        Endpoint endpoint = retrofit.create(Endpoint.class);
        endpoint.fetchLibraryBooks().enqueue(new Callback<ArrayList<LibraryBook>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<LibraryBook>> call, @NonNull Response<ArrayList<LibraryBook>> response) {
                if (response.isSuccessful()) {
                    list = new ArrayList<>();
                    list.clear();
                    list = response.body();
                    librarybooks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    booksAdapter = new LibraryBooksAdapter(Tech_LibraryBooks.this, list);
                    librarybooks.setAdapter(booksAdapter);
                    librarybooks.setHasFixedSize(true);


                } else {
                    Toast.makeText(Tech_LibraryBooks.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<LibraryBook>> call, Throwable t) {
                Toast.makeText(Tech_LibraryBooks.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        btnMyBook = findViewById(R.id.btn_mybooks);
        btnMyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Tech_LibraryBooks.this, MyBookTeacher.class);
                startActivity(i);
            }
        });

        //sre
        editTextsearch = findViewById(R.id.etsearch);
        imgsearch = findViewById(R.id.imagesearch);
        tvteacher = findViewById(R.id.tvproduct);
        imageback = findViewById(R.id.imageback);
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvteacher.setVisibility(View.GONE);
                editTextsearch.setVisibility(View.VISIBLE);
                editTextsearch.requestFocus();
                imageback.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
            }
        });
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        editTextsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//endsre



    }
    //sre
    private void filter(String text)
    {
        ArrayList<LibraryBook> filterproduct = new ArrayList<>();
        for(int i = 0; i < list.size() ; i ++)
        {
            if(list.get(i).getTitle().toLowerCase().contains(text.toLowerCase()))
            {
                filterproduct.add(list.get(i));
            }
            else
            if(list.get(i).getPublisher().toLowerCase().contains(text.toLowerCase()))
            {
                filterproduct.add(list.get(i));
            }
            else
                if(list.get(i).getAuthor().toLowerCase().contains(text.toLowerCase()))
            {
                filterproduct.add(list.get(i));
            }


        }
        if(filterproduct.isEmpty())
        {
            try {
                ArrayList<LibraryBook> emptyList = new ArrayList<>();
                booksAdapter.filterList((ArrayList<LibraryBook>) emptyList);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            booksAdapter.filterList((ArrayList<LibraryBook>) filterproduct);
        }
    }

//endsre

}