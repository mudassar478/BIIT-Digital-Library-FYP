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
import com.example.biitdigitallibrarysystem.models.EnrollStudent_Model;
import com.example.biitdigitallibrarysystem.models.LessonPlanModel;
import com.example.biitdigitallibrarysystem.teacherActivities.ReferencesAndLinks;
import com.example.biitdigitallibrarysystem.teacherActivities.UploadLessonPlanActivity;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LessonPlanAdapter extends RecyclerView.Adapter<LessonPlanAdapter.ViewHolder> {

    Context context;

    ArrayList<LessonPlanModel> lessonPlanModelList;

    public LessonPlanAdapter(Context context, ArrayList<LessonPlanModel> lessonPlanModelList) {
        this.context = context;
        this.lessonPlanModelList = lessonPlanModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_lesson_plan, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LessonPlanAdapter.ViewHolder holder, int position) {
        LessonPlanModel object = lessonPlanModelList.get(position);
//        holder.filename.setText(lessonPlanModel.getPath());

        holder.filename.setText(object.getTitle());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,UploadLessonPlanActivity.class);
                context.startActivity(intent);
            }
        });
        holder.btnRefrenece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ReferencesAndLinks.class);
                context.startActivity(intent);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit= APIClient.getClient();
                Endpoint endpoint= retrofit.create(Endpoint.class);
                endpoint.TeacherDeleteLessonPlan(object.getLid()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(context.getApplicationContext(), response.message()+"LessonPlan Deleted Successfully",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(context.getApplicationContext(), response.message()+"Failed to Delete",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessonPlanModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView filename;
        ImageButton btnRefrenece,btnEdit,btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            filename= itemView.findViewById(R.id.filename_lessonplan);
            btnRefrenece=itemView.findViewById(R.id.refrences_lessonplan);
            btnEdit=itemView.findViewById(R.id.edit_lessonplan);
            btnDelete=itemView.findViewById(R.id.delete_lessonplan);

        }
    }
}
