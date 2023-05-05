package com.example.biitdigitallibrarysystem.teacherActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.adapters.EnrollStudentsAdapter;
import com.example.biitdigitallibrarysystem.adapters.WeeksAdapter;
import com.example.biitdigitallibrarysystem.models.Bookscreen;

import java.util.ArrayList;

public class EnrollStudents extends AppCompatActivity {
    RecyclerView rv_enrollment;
    EnrollStudentsAdapter enrollStudentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll_students);

        ArrayList<Bookscreen> week = new ArrayList<>();
        rv_enrollment=findViewById(R.id.rv_enrollStudents);
        week.add(new Bookscreen("Mudassar","2019-arid-0110" ));
        week.add(new Bookscreen("Usama","2019-arid-0084" ));
        week.add(new Bookscreen("Ali","2019-arid-0113" ));
        week.add(new Bookscreen("Ehtisham","2019-arid-0198" ));
        week.add(new Bookscreen("Shaban","2019-arid-0115" ));
        week.add(new Bookscreen("Furqan","2019-arid-0086" ));
        week.add(new Bookscreen("Jawad","2019-arid-0103" ));
        week.add(new Bookscreen("Basit","2019-arid-0033" ));
        week.add(new Bookscreen("Hamza","2019-arid-0126" ));
        week.add(new Bookscreen("Rafey","2019-arid-0128" ));
        week.add(new Bookscreen("Adeel","2019-arid-0130" ));
        week.add(new Bookscreen("Salman","2019-arid-0133" ));
        week.add(new Bookscreen("Hammad","2019-arid-0135" ));
        week.add(new Bookscreen("Zohaib","2019-arid-0140" ));


        enrollStudentsAdapter = new EnrollStudentsAdapter(this , week);
        rv_enrollment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_enrollment.setAdapter(enrollStudentsAdapter);
    }
}
