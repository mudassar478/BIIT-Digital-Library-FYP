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
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.models.CoursesClassModel;
import com.example.biitdigitallibrarysystem.models.WeekNumModel;
import com.example.biitdigitallibrarysystem.models.WeeksModel;
import com.example.biitdigitallibrarysystem.teacherActivities.LessonPlanActivity;
import com.example.biitdigitallibrarysystem.teacherActivities.WeeksActivity;

import java.util.ArrayList;

public class WeeksAdapter extends RecyclerView.Adapter<WeeksAdapter.ViewHolder> {
    Context context;
    ArrayList<WeekNumModel> weeks;

    public WeeksAdapter(Context context, ArrayList<WeekNumModel> weeks) {
        this.context = context;
        this.weeks = weeks;

    }
    @NonNull
    @Override
    public WeeksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.rv_weeks, parent, false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeksAdapter.ViewHolder holder, int position) {
//        final WeeksModel weeksModel=weeks.get(position);
             holder.weeks.setText(weeks.get(position).getWeek());
             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {

                 }
             });
//            holder.cid.setText(weeksModel.getcid()+"");
//            holder.lid.setText(weeksModel.getlid()+"");
//            holder.tid.setText(weeksModel.gettid()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                context.startActivity(intent);
            }
        });

        }

    @Override
    public int getItemCount() {
        return weeks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView weeks,lid,tid,cid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weeks= itemView.findViewById(R.id.weeks);
            lid=itemView.findViewById(R.id.lid);
            tid=itemView.findViewById(R.id.tid);
            cid=itemView.findViewById(R.id.cid);
        }
    }
}
