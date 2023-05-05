package com.example.biitdigitallibrarysystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.teacherActivities.ReferencesAndLinks;

import java.util.ArrayList;

public class ReferencesAndLinksAdapter extends RecyclerView.Adapter<ReferencesAndLinksAdapter.ReferenceHolder> {
    Context context;
    ArrayList<Bookscreen> RefrencesAndLinks;

    public ReferencesAndLinksAdapter(Context context, ArrayList<Bookscreen> RefrencesAndLinks) {
        this.context = context;
        this.RefrencesAndLinks = RefrencesAndLinks;
    }

    @NonNull
    @Override
    public ReferencesAndLinksAdapter.ReferenceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_refrencesandlinks, parent, false);
        return new ReferenceHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ReferencesAndLinksAdapter.ReferenceHolder holder, int position) {
//        Bookscreen obj = RefrencesAndLinks.get(position);
//        holder.filename.setText(obj.getBooks());
    }

    @Override
    public int getItemCount() {
        return RefrencesAndLinks.size();
    }

    public static class ReferenceHolder extends RecyclerView.ViewHolder {


        public ReferenceHolder(@NonNull View itemView) {
            super(itemView);
//            filename = itemView.findViewById(R.id.filename_lessonplan);
//            button1 = itemView.findViewById(R.id.refrences_lessonplan);
//            button2 = itemView.findViewById(R.id.edit_lessonplan);
//            button3 = itemView.findViewById(R.id.delete_lessonplan);

        }
    }
}
