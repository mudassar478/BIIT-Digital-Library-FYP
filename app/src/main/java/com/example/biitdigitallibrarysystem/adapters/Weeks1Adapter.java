package com.example.biitdigitallibrarysystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.teacherActivities.LessonPlanActivity;

import java.util.List;

public class Weeks1Adapter extends RecyclerView.Adapter<Weeks1Adapter.ViewHolder> {
    private List<String> booksList;
    private Weeks1Adapter.RecyclerViewClickListener listener;
    Context context;
    public static String weekid;

    public Weeks1Adapter(List<String> booksList, Weeks1Adapter.RecyclerViewClickListener listener, Context context) {
        this.booksList = booksList;
        this.listener = listener;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_weeks, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Weeks1Adapter.ViewHolder holder, int position) {
        String item = booksList.get(position);
        holder.weeks.setText(item);

        holder.layoutweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, LessonPlanActivity.class);
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

//        @Override
//        public void onClick(View view) {
////            Toast.makeText(view.getContext(), "hhhh",Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(context, LessonPlanActivity.class);
//            intent.putExtra("cid",lid.getText().toString());
//            intent.putExtra("tid",tid.getText().toString());
//            intent.putExtra("week",weeks.getText().toString());
//            context.startActivity(intent);
//        }
    }

}
