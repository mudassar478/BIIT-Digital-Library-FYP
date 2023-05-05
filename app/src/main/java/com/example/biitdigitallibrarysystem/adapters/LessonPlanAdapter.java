package com.example.biitdigitallibrarysystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.teacherActivities.LessonPlanActivity;
import com.example.biitdigitallibrarysystem.teacherActivities.ReferencesAndLinks;
import com.example.biitdigitallibrarysystem.teacherActivities.UploadLessonPlanActivity;

import java.util.ArrayList;

public class LessonPlanAdapter extends RecyclerView.Adapter<LessonPlanAdapter.ViewHolder> {

    Context context;
    ArrayList<Bookscreen> lesson;

    public LessonPlanAdapter(Context context, ArrayList<Bookscreen> lesson) {
        this.context = context;
        this.lesson = lesson;
    }
    @NonNull
    @Override
    public LessonPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_lesson_plan, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LessonPlanAdapter.ViewHolder holder, int position) {
        Bookscreen obj = lesson.get(position);
        holder.filename.setText(obj.getBooks());

        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,UploadLessonPlanActivity.class);
                context.startActivity(intent);
            }
        });
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ReferencesAndLinks.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lesson.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView filename;
        ImageButton button1,button2,button3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            filename= itemView.findViewById(R.id.filename_lessonplan);
            button1=itemView.findViewById(R.id.refrences_lessonplan);
            button2=itemView.findViewById(R.id.edit_lessonplan);
            button3=itemView.findViewById(R.id.delete_lessonplan);

        }
    }
}
