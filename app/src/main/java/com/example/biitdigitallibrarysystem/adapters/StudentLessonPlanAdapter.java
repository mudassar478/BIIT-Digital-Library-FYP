package com.example.biitdigitallibrarysystem.adapters;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.MainActivity;
import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.AppDatabase;
import com.example.biitdigitallibrarysystem.models.LessonPlanModel;
import com.example.biitdigitallibrarysystem.models.PdfDownloader;
import com.example.biitdigitallibrarysystem.models.User;
import com.example.biitdigitallibrarysystem.models.UserDao;
import com.example.biitdigitallibrarysystem.studentActivites.Std_ReferenceAndLink;
import com.example.biitdigitallibrarysystem.teacherActivities.EditLessonPlan;
import com.example.biitdigitallibrarysystem.teacherActivities.LessonPlanActivity;
import com.example.biitdigitallibrarysystem.teacherActivities.ReferencesAndLinks;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StudentLessonPlanAdapter extends RecyclerView.Adapter<StudentLessonPlanAdapter.ViewHolder> {

    Context context;
    boolean isStarFilled = false;
    public static int lid;
    String filePath = APIClient.getFilePath();

    ArrayList<LessonPlanModel> lessonPlanModelList;

    public StudentLessonPlanAdapter(Context context, ArrayList<LessonPlanModel> lessonPlanModelList) {
        this.context = context;
        this.lessonPlanModelList = lessonPlanModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rvd_std_lessonplan, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StudentLessonPlanAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LessonPlanModel object = lessonPlanModelList.get(position);

        holder.filename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // openPdfFile(filePath + object.getPath());
            }
        });
//        holder.filename.setText(lessonPlanModel.getPath());
        UserDao userDao= AppDatabase.getAppDatabase(context).getUserDao();
        List<User> booknameslist=userDao.getFvtFilePath(String.valueOf(object.getTitle()));
        if (!(booknameslist.isEmpty())){
            holder.btnfav.setImageResource(R.drawable.starfull);
        }else {
            holder.btnfav.setImageResource(R.drawable.star);
        }

        holder.filename.setText(object.getTitle());
//        holder.lid.setText(object.getLid());

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
        holder.btnRefrenece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lid = lessonPlanModelList.get(holder.getAdapterPosition()).getLid();
                int tid= LessonPlanActivity.tid;
                int lid=LessonPlanActivity.lid;
                int sid = MainActivity.sid;
                String role= LessonPlanActivity.role;
//                Intent intent=new Intent(context,ReferencesAndLinks.class);
                Intent intent=new Intent(context, Std_ReferenceAndLink.class);
                intent.putExtra("tid",tid);
                intent.putExtra("sid",sid);
                intent.putExtra("lid",lid);
                intent.putExtra("role",role);
                context.startActivity(intent);
            }
        });
        holder.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String url = "https://example.com/path/to/file.pdf"; // Replace with the URL of the PDF or Word file
//
//                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//                Toast.makeText(context, "Downloading PDF...", Toast.LENGTH_SHORT).show();
//                request.setTitle("File Download"); // Set the title of the download notification
//                request.setDescription("Downloading File"); // Set the description of the download notification
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); // Show the download progress in the notification bar
//
//                // Determine the file name and file type from the URL
//                String fileName = "file.pdf";
//                String fileExtension = MimeTypeMap.getFileExtensionFromUrl(url);
//                if (fileExtension != null && !fileExtension.isEmpty()) {
//                    fileName = "file." + fileExtension;
//                }
//
//                // Set the destination path for the downloaded file
//                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
//
//                DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//                if (downloadManager != null) {
//                    downloadManager.enqueue(request);
//                }
//            }

                new PdfDownloader(context).downloadPdf(filePath + lessonPlanModelList.get(position).getPath());
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
    ///////openpdf
    public void openPdfFile(String pdfFilePath)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
//        String pathFile= Environment.getExternalStorageDirectory()
//                + "/Download/13_CSS Web Design For Dummies.pdf";
//        String pathFile= Environment.getExternalStorageDirectory()
//                + "/Download/"+pdfFilePath;
//        Toast.makeText(context, ""+pdfFilePath, Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse( pdfFilePath);

        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try
        {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e)
        {
            Toast.makeText(context, "No PDF viewer application found.", Toast.LENGTH_SHORT).show();
        }
    }
}
