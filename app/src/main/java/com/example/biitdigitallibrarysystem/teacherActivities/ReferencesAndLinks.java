package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.ReferencesAndLinksAdapter;
import com.example.biitdigitallibrarysystem.models.Bookscreen;

import java.util.ArrayList;

public class ReferencesAndLinks extends AppCompatActivity {
    RecyclerView recyclerView;
    ReferencesAndLinksAdapter adapter ;
    Bookscreen bookscreen;
    ArrayList<Bookscreen> list = new ArrayList<>();
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
    }
}