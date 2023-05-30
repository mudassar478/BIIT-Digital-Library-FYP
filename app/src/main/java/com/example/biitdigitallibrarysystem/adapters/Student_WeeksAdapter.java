package com.example.biitdigitallibrarysystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.studentActivites.Student_lessonPlan;
import com.example.biitdigitallibrarysystem.teacherActivities.LessonPlanActivity;
import com.example.biitdigitallibrarysystem.teacherActivities.WeeksActivity;

import java.util.List;

public class Student_WeeksAdapter extends RecyclerView.Adapter<Student_WeeksAdapter.ViewHolder> {
    private List<String> booksList;
    Context context;
    public static String weekid;

    public Student_WeeksAdapter(List<String> booksList, Context context) {
        this.booksList = booksList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_weeks, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Student_WeeksAdapter.ViewHolder holder, int position) {
        String item = booksList.get(position);
        holder.weeks.setText(item);

        holder.layoutweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Student_lessonPlan.class);

                weekid = booksList.get(holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView weeks, lid, tid, cid;
        LinearLayout layoutweek;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weeks = itemView.findViewById(R.id.weeks);
            lid = itemView.findViewById(R.id.lid);
            tid = itemView.findViewById(R.id.tid);
            cid = itemView.findViewById(R.id.cid);
            layoutweek = itemView.findViewById(R.id.layoutweek);

        }

    }

}
