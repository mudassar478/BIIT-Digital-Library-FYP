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

import com.example.biitdigitallibrarysystem.GlobalData;
import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.models.CoursesClassModel;
import com.example.biitdigitallibrarysystem.teacherActivities.CoursesActivity;
import com.example.biitdigitallibrarysystem.teacherActivities.LessonPlanActivity;
import com.example.biitdigitallibrarysystem.teacherActivities.Tech_LibraryBooks;
import com.example.biitdigitallibrarysystem.teacherActivities.WeeksActivity;

import java.util.ArrayList;

public class CourseTAdapter extends RecyclerView.Adapter<CourseTAdapter.CourseHolder> {
    Context context;
    ArrayList<CoursesClassModel> list;
    public static int idc;

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    public CourseTAdapter(Context context, ArrayList<CoursesClassModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CourseTAdapter.CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_design_for_courses, parent, false);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseTAdapter.CourseHolder holder, int position) {
        final CoursesClassModel coursesClassModel = list.get(position);
        holder.id.setText(coursesClassModel.getcid() + "");
        holder.tv_course_name.setText(coursesClassModel.getname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WeeksActivity.class);
                int tid= CoursesActivity.tid;
                String role=CoursesActivity.role;
                idc = list.get(holder.getAdapterPosition()).getcid();
                intent.putExtra("tid",tid);
                intent.putExtra("role",role);

                context.startActivity(intent);
            }
        });

        holder.id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WeeksActivity.class);
                GlobalData.cid = list.get(holder.getAdapterPosition()).getcid();
                intent.putExtra("cid", list.get(holder.getAdapterPosition()).getcid());
                // intent.putExtra("ttid", list.get(holder.getAdapterPosition()).);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CourseHolder extends RecyclerView.ViewHolder {

        ImageView iv_course;
        TextView tv_course_name, id;
        RelativeLayout rv_lesson;

        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            iv_course = itemView.findViewById(R.id.iv_course_image);
            tv_course_name = itemView.findViewById(R.id.tv_course_name);
            rv_lesson = itemView.findViewById(R.id.rv_lesson);
            id = itemView.findViewById(R.id.course_id);

        }
    }
}
