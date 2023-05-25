package com.example.biitdigitallibrarysystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.models.SectionsInfo;
import com.example.biitdigitallibrarysystem.models.StudentLogModel;
import com.example.biitdigitallibrarysystem.teacherActivities.EnrollStudents;
import com.example.biitdigitallibrarysystem.teacherActivities.StudentLogs;
import com.example.biitdigitallibrarysystem.teacherActivities.WeeksActivity;

import java.util.ArrayList;

public class StudentLogAdapter extends RecyclerView.Adapter<StudentLogAdapter.StudentLogsHolder> {
    Context context;
    ArrayList<StudentLogModel> Sections;
    public static int tid;
    public static String sectionid;

    public StudentLogAdapter(Context context, ArrayList<StudentLogModel> sections) {
        this.context = context;
        this.Sections = sections;
    }

    @NonNull
    @Override
    public StudentLogAdapter.StudentLogsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.studentlogs_sections, parent, false);
        return new StudentLogsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentLogAdapter.StudentLogsHolder holder, int position) {
        StudentLogModel object = Sections.get(position);
//        holder.tid.setText(object.gettid()+"");
        holder.Section.setText(object.getsections());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EnrollStudents.class);
                tid = Sections.get(holder.getAdapterPosition()).gettid();
                sectionid = Sections.get(holder.getAdapterPosition()).getsections();

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return Sections.size();
    }

    public class StudentLogsHolder extends RecyclerView.ViewHolder {

        TextView tid,Section;

        public StudentLogsHolder(@NonNull View itemView) {
            super(itemView);
            tid=itemView.findViewById(R.id.tid);
            Section = itemView.findViewById(R.id.rv_section);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent=new Intent(context, EnrollStudents.class);
//                    context.startActivity(intent);
//                }
//            });
        }

    }

}

