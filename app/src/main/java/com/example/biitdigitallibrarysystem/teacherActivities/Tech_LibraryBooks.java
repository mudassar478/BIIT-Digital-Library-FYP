package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.LibraryBooksAdapter;
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Tech_LibraryBooks extends AppCompatActivity {
    RecyclerView librarybooks;
    FloatingActionButton btn_float;
    LibraryBooksAdapter booksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books);

        btn_float=findViewById(R.id.floatBtn_uploadbook);


        ArrayList<Bookscreen> books = new ArrayList<>();
        librarybooks=findViewById(R.id.rv_Librarybooks);
        books.add(new Bookscreen("Introduction to C++"));
        books.add(new Bookscreen("Calculus Edition 2"));
        books.add(new Bookscreen("Arithmetic Algorithms"));
        books.add(new Bookscreen("Introduction to OOP"));
        books.add(new Bookscreen("C# Edition 1"));
        books.add(new Bookscreen("Introduction to Database "));
        books.add(new Bookscreen("Artificial Intelligence"));
        books.add(new Bookscreen("Compiler Construction"));
        books.add(new Bookscreen("Computer Networking"));
        books.add(new Bookscreen("Digital And Logical Designs"));
        books.add(new Bookscreen("English by Wren and Martin"));
        books.add(new Bookscreen("Introduction to Computer"));
        books.add(new Bookscreen("Web Designing"));

        booksAdapter = new LibraryBooksAdapter(this , books);
        librarybooks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        librarybooks.setAdapter(booksAdapter);

        btn_float.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Tech_LibraryBooks.this,UploadBook.class);
               startActivity(i);

//                Toast.makeText(Tech_LibraryBooks.this, "You click on dloat button", Toast.LENGTH_SHORT).show();
            }
        });




    }
}