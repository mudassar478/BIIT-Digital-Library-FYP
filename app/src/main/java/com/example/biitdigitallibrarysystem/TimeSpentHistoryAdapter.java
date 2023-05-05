package com.example.biitdigitallibrarysystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.adapters.LessonPlanAdapter;
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.models.SectionsInfo;
import com.example.biitdigitallibrarysystem.models.timespendModel;

import java.util.ArrayList;

public class TimeSpentHistoryAdapter extends RecyclerView.Adapter<TimeSpentHistoryAdapter.ViewHolder> {
    Context context;
    ArrayList<timespendModel> timespenthistory;

    public TimeSpentHistoryAdapter(Context context, ArrayList<timespendModel> timespenthistory) {
        this.context = context;
        this.timespenthistory = timespenthistory;
    }
    @NonNull
    @Override
    public TimeSpentHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_timespenthistory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSpentHistoryAdapter.ViewHolder holder, int position) {
        timespendModel obj = timespenthistory.get(position);
        holder.lesson.setText(obj.getLessonplan());
        holder.time.setText(obj.getTimedate());

    }

    @Override
    public int getItemCount() {
        return timespenthistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lesson,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lesson=itemView.findViewById(R.id.lessonplanNo);
            time=itemView.findViewById(R.id.timeanddate);
        }
    }
}
