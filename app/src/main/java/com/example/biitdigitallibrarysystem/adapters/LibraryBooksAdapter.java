package com.example.biitdigitallibrarysystem.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.models.Bookscreen;
import com.example.biitdigitallibrarysystem.models.DownloadImageTask;
import com.example.biitdigitallibrarysystem.models.LibraryBook;
import com.example.biitdigitallibrarysystem.models.PdfDownloader;
import com.example.biitdigitallibrarysystem.teacherActivities.CoursesActivity;
import com.example.biitdigitallibrarysystem.teacherActivities.TableOFContent;
import com.example.biitdigitallibrarysystem.teacherActivities.Teacher_dashboard;
import com.example.biitdigitallibrarysystem.teacherActivities.WeeksActivity;

import java.util.ArrayList;

public class LibraryBooksAdapter extends RecyclerView.Adapter<LibraryBooksAdapter.MyViewHolder>{
    Context context;
    ArrayList<LibraryBook> books;
    String imagePath = APIClient.getImagePath();
    String filePath = APIClient.getFilePath();
    public LibraryBooksAdapter(Context context, ArrayList<LibraryBook> books) {
        this.context = context;
        this.books = books;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Books;
        ImageView imageupload , btndownload , button3;
        Button btn_toc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Books = itemView.findViewById(R.id.book_name);
            imageupload=itemView.findViewById(R.id.img_bookupload);
            btn_toc = itemView.findViewById(R.id.btn_toc);
            btndownload = itemView.findViewById(R.id.btn_download);
            button3=itemView.findViewById(R.id.btn_fav);
        }

    }


    @NonNull
    @Override
    public LibraryBooksAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rvd_bookscreen, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryBooksAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.Books.setText(books.get(position).getTitle());
        new DownloadImageTask(holder.imageupload).execute(imagePath+books.get(position).getImage_path());
        holder.itemView.setOnClickListener(view ->
                openPDFFile(filePath+books.get(position).getPdf_path()));
        holder.btndownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PdfDownloader(context).downloadPdf(filePath+books.get(position).getPdf_path());
            }
        });
        holder.btn_toc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableOFContent.bid=books.get(position).getBid();
                Intent i = new Intent(context.getApplicationContext(), TableOFContent.class);
                context.startActivity(i);


            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
    private void openPDFFile(String filePath) {
        Uri file = Uri.parse(filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(file, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Intent intentChooser = Intent.createChooser(intent, "Open PDF with");

        // Verify that there is an app available to handle the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intentChooser);
        } else {
            Toast.makeText(context, "No PDF viewer app found.", Toast.LENGTH_SHORT).show();
        }
    }

}
