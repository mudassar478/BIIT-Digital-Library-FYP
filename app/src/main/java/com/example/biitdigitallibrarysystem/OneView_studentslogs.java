package com.example.biitdigitallibrarysystem;

import android.view.View;
import android.widget.TextView ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OneView_studentslogs extends RecyclerView.ViewHolder {
    TextView tv_sections;

    public OneView_studentslogs(@NonNull View itemView) {
        super(itemView);
        tv_sections=itemView.findViewById(R.id.rv_Studentslog);
    }
}
