package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.ReferencesAndLinksAdapter;
import com.example.biitdigitallibrarysystem.models.Bookscreen;

import java.util.ArrayList;
import java.util.List;

public class ReferencesAndLinks extends AppCompatActivity {
    Spinner spinner;
    List<String> items;
    RecyclerView recyclerView;
    ReferencesAndLinksAdapter adapter ;
    Bookscreen bookscreen;
    ArrayList<Bookscreen> list = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.references_and_links);
        recyclerView = findViewById(R.id.rv_referencesLinks);
        bookscreen = new Bookscreen("C++");
        list.add(bookscreen);
        adapter = new ReferencesAndLinksAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);


        //spinner code
        spinner = findViewById(R.id.spinnerReferencesLinks);
        items = new ArrayList<>();
        items.add("Public");
        items.add("Private");

        spinner.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,items));



    }
}