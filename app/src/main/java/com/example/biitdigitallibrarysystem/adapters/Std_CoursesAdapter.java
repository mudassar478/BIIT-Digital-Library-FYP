package com.example.biitdigitallibrarysystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.models.Std_CoursesModel;
import com.example.biitdigitallibrarysystem.studentActivites.Week_Student;
import com.example.biitdigitallibrarysystem.teacherActivities.WeeksActivity;

import java.util.ArrayList;

public class Std_CoursesAdapter extends RecyclerView.Adapter<Std_CoursesAdapter.StdHolder> {
    Context context;
    ArrayList<Std_CoursesModel> list;
    public static int stdCourseID;


    public Std_CoursesAdapter(Context context, ArrayList<Std_CoursesModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Std_CoursesAdapter.StdHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_design_std_courses, parent, false);
        return new StdHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Std_CoursesAdapter.StdHolder holder, int position) {
        final Std_CoursesModel std_coursesModel = list.get(position);
        holder.id.setText(String.valueOf(std_coursesModel.getCid()));
        holder.tv_course_name.setText(std_coursesModel.getName());
        // holder.tv_course_name.setText(list.get(holder.getAdapterPosition()).getCid());
        holder.rv_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Week_Student.class);
                stdCourseID = list.get(holder.getAdapterPosition()).getCid();
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class StdHolder extends RecyclerView.ViewHolder {

        TextView tv_course_name, id;
        ImageView iv_course_image;
        RelativeLayout rv_lesson;


        public StdHolder(@NonNull View itemView) {
            super(itemView);
            iv_course_image = itemView.findViewById(R.id.iv_course_image1);
            tv_course_name = itemView.findViewById(R.id.tv_course_name1);
            rv_lesson = itemView.findViewById(R.id.rv_lessonc);
            id = itemView.findViewById(R.id.course_id1);

        }
    }

}
