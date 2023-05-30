package com.example.biitdigitallibrarysystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.LessonPlanModel;
import com.example.biitdigitallibrarysystem.models.ReferencesModel;
import com.example.biitdigitallibrarysystem.teacherActivities.EditLessonPlan;
import com.example.biitdigitallibrarysystem.teacherActivities.LessonPlanActivity;
import com.example.biitdigitallibrarysystem.teacherActivities.ReferencesAndLinks;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReferanceAdapter extends RecyclerView.Adapter<ReferanceAdapter.ViewHolder> {

    Context context;
    public static int lid;

    ArrayList<ReferencesModel> referencesModels;

    public ReferanceAdapter(Context context, ArrayList<ReferencesModel> referencesModels) {
        this.context = context;
        this.referencesModels = referencesModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_refrencesandlinks, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ReferanceAdapter.ViewHolder holder, int position) {
        ReferencesModel object = referencesModels.get(position);
        holder.txt_ReferenceAndLink.setText(object.getContent());



    }

    @Override
    public int getItemCount() {
        return referencesModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_ReferenceAndLink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_ReferenceAndLink= itemView.findViewById(R.id.txt_ReferenceAndLink);


        }
    }
}
