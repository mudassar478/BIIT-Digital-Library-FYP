package com.example.biitdigitallibrarysystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.models.EnrollStudent_Model;
import com.example.biitdigitallibrarysystem.teacherActivities.TimeSpentHistory;

import java.util.ArrayList;

public class EnrollStudentsAdapter extends RecyclerView.Adapter<EnrollStudentsAdapter.ViweHolder> {
    Context context;
    ArrayList<EnrollStudent_Model> Enrollstudentslist;

    public EnrollStudentsAdapter(Context context, ArrayList<EnrollStudent_Model> enrollstudentslist) {
        this.context = context;
        this.Enrollstudentslist = enrollstudentslist;
    }

    @NonNull
    @Override
    public EnrollStudentsAdapter.ViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_enrollstudents, parent, false);
        return new ViweHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnrollStudentsAdapter.ViweHolder holder, int position) {

        EnrollStudent_Model object = Enrollstudentslist.get(position);


        holder.stdregno.setText(object.getReg_no());
        holder.stdname.setText(object.getName());

    }

    @Override
    public int getItemCount() {
        return Enrollstudentslist.size();
    }

    public class ViweHolder extends RecyclerView.ViewHolder {
        TextView Enrollstudentlist, stdname, stdregno;

        public ViweHolder(@NonNull View itemView) {
            super(itemView);
            stdregno = itemView.findViewById(R.id.stdRegNo);
            stdname = itemView.findViewById(R.id.stdName);
            Enrollstudentlist = itemView.findViewById(R.id.rv_enrollStudents);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TimeSpentHistory.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
