package com.example.biitdigitallibrarysystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.models.CoursesClassModel;
import com.example.biitdigitallibrarysystem.models.Std_CoursesModel;

import java.util.ArrayList;

public class Std_CoursesAdapter extends RecyclerView.Adapter<Std_CoursesAdapter.CourseHolder> {
    @NonNull

    Context context;
    ArrayList<String> list;

    public Std_CoursesAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }
    public Std_CoursesAdapter.CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_design_std_courses, parent, false);
        return new Std_CoursesAdapter.CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Std_CoursesAdapter.CourseHolder holder, int position) {
        final String std_coursesModel=list.get(position);
        holder.tv_course_name.setText(Std_CoursesModel.getBookname()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class CourseHolder extends RecyclerView.ViewHolder {

        TextView tv_course_name;


        public CourseHolder(@NonNull View itemView) {
            super(itemView);

            tv_course_name = itemView.findViewById(R.id.tv_course_name);


        }
    }
}
