package com.example.biitdigitallibrarysystem.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.ReferencesModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Std_ReferanceAdapter extends RecyclerView.Adapter<Std_ReferanceAdapter.ViewHolder> {

    Context context;
    public static int lid, id;



    ArrayList<ReferencesModel> referencesModels;

    public Std_ReferanceAdapter(Context context, ArrayList<ReferencesModel> referencesModels) {
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
    public void onBindViewHolder(@NonNull Std_ReferanceAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ReferencesModel object = referencesModels.get(position);
        id=object.getId();
        holder.txt_ReferenceAndLink.setText(object.getContent());
        holder.state.setText(object.getType());
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit= APIClient.getClient();
                Endpoint endpoint= retrofit.create(Endpoint.class);
                endpoint.TeacherDeleteRefrences(id).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(context.getApplicationContext(), response.message()+"LessonPlan Deleted Successfully",Toast.LENGTH_LONG).show();
                            notifyDataSetChanged();
                            referencesModels.remove(position);
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
        return referencesModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_ReferenceAndLink, state;
        ImageView img_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            state= itemView.findViewById(R.id.ref_state);
            txt_ReferenceAndLink= itemView.findViewById(R.id.txt_ReferenceAndLink);
            img_delete= itemView.findViewById(R.id.img_deleteReference);
        }
    }
}
