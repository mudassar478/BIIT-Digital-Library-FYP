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
import com.example.biitdigitallibrarysystem.teacherActivities.TimeSpentHistory;

import java.util.ArrayList;

public class EnrollStudentsAdapter extends RecyclerView.Adapter<EnrollStudentsAdapter.ViweHolder> {
    Context context;
    ArrayList<Bookscreen> enrollstudents;

    public EnrollStudentsAdapter(Context context, ArrayList<Bookscreen> enrollstudents) {
        this.context = context;
        this.enrollstudents = enrollstudents;
    }

    @NonNull
    @Override
    public EnrollStudentsAdapter.ViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_enrollstudents, parent, false);
        return new ViweHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnrollStudentsAdapter.ViweHolder holder, int position) {
        Bookscreen obj = enrollstudents.get(position);
        holder.stdname.setText(obj.getBooks());
        holder.stdregno.setText(obj.getArid());

    }

    @Override
    public int getItemCount() {
        return enrollstudents.size();
    }

    public class ViweHolder extends RecyclerView.ViewHolder {
        TextView stdname,stdregno;
        public ViweHolder(@NonNull View itemView) {
            super(itemView);
            stdregno=itemView.findViewById(R.id.stdRegNo);
            stdname=itemView.findViewById(R.id.stdName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, TimeSpentHistory.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
