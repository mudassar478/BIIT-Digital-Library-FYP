package com.example.biitdigitallibrarysystem.adapters;

import android.content.Context;
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
import com.example.biitdigitallibrarysystem.models.AppDatabase;
import com.example.biitdigitallibrarysystem.models.LessonPlanModel;
import com.example.biitdigitallibrarysystem.models.User;
import com.example.biitdigitallibrarysystem.models.UserDao;

import java.util.ArrayList;
import java.util.List;

public class StarLessonPlanAdapter extends RecyclerView.Adapter<StarLessonPlanAdapter.ViewHolder> {

    Context context;
    boolean isStarFilled = false;
    public static int lid;
    String filePath = APIClient.getFilePath();

    ArrayList<LessonPlanModel> lessonPlanModelList;
    List<User> booknameslist;

    public StarLessonPlanAdapter(Context context, ArrayList<LessonPlanModel> lessonPlanModelList) {
        this.context = context;
        this.lessonPlanModelList = lessonPlanModelList;
    }

    @NonNull
    @Override
    public StarLessonPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rvd_favlessonplan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarLessonPlanAdapter.ViewHolder holder, int position) {
        LessonPlanModel object = lessonPlanModelList.get(position);
        UserDao userDao= AppDatabase.getAppDatabase(context).getUserDao();
        List<User> booknameslist=userDao.getFvtFilePath(String.valueOf(object.getTitle()));
        if (!(booknameslist.isEmpty())){
            holder.btnfav.setImageResource(R.drawable.starfull);
        }else {
            holder.btnfav.setImageResource(R.drawable.star);
        }

        holder.filename.setText(object.getTitle());

        holder.btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> booknames=userDao.getFvtFilePath(String.valueOf(object.getTitle()));
                if (booknames.isEmpty()){
                    userDao.insertAll(new User(0,String.valueOf(object.getTitle())));
                    holder.btnfav.setImageResource(R.drawable.starfull);
                    Toast.makeText(context, "Added to Bookmarks", Toast.LENGTH_SHORT).show();
                }else {
                    userDao.deleteFavourites(String.valueOf(object.getTitle()));
                    holder.btnfav.setImageResource(R.drawable.star);
                }
            }

        });

    }

    @Override
    public int getItemCount() {
        return lessonPlanModelList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView filename,lid;
        ImageButton btnRefrenece,btnfav,btnDownload;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            filename= itemView.findViewById(R.id.filename_lessonplan);
            lid = itemView.findViewById(R.id.text_view_lid);
            btnRefrenece=itemView.findViewById(R.id.refrences_lessonplan);
            btnfav=itemView.findViewById(R.id.fav_lessonplan);
            btnDownload=itemView.findViewById(R.id.download_lessonplan);

        }
    }

}
