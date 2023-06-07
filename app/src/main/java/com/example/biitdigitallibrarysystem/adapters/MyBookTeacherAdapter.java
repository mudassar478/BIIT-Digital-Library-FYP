package com.example.biitdigitallibrarysystem.adapters;


import android.annotation.SuppressLint;
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
import com.example.biitdigitallibrarysystem.models.DownloadImageTask;
import com.example.biitdigitallibrarysystem.models.LibraryBook;
import com.example.biitdigitallibrarysystem.models.MyBookTeacherModel;
import com.example.biitdigitallibrarysystem.models.PdfDownloader;
import com.example.biitdigitallibrarysystem.teacherActivities.MyBookTeacher;
import com.example.biitdigitallibrarysystem.teacherActivities.TableOFContent;


import java.util.ArrayList;
import java.util.List;


public class MyBookTeacherAdapter extends RecyclerView.Adapter<MyBookTeacherAdapter.ItemViewHolder> {
    Context context;
    boolean isStarFilled = false;

    ArrayList<LibraryBook> books;
    String imagePath = APIClient.getImagePath();
    String filePath = APIClient.getFilePath();
    public MyBookTeacherAdapter(Context context, ArrayList<LibraryBook> books) {
        this.context = context;
        this.books = books;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView Books;
        ImageView imageupload , btndownload , btnfav;
        Button btn_toc;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            Books = itemView.findViewById(R.id.book_name);
            imageupload=itemView.findViewById(R.id.img_bookupload);
            btn_toc = itemView.findViewById(R.id.btn_toc);
            btndownload = itemView.findViewById(R.id.btn_download);
            btnfav=itemView.findViewById(R.id.btn_fav);
        }


    }

//    private List<MyBookTeacherModel> itemList;
//
//    public MyBookTeacherAdapter(List<MyBookTeacherModel> itemList) {
//        this.itemList = itemList;
//    }

    @NonNull
    @Override
    public MyBookTeacherAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvd_mybookteacher, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookTeacherAdapter.ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        MyBookTeacherModel item = itemList.get(position);
//        holder.itemNameTextView.setText(item.getName());
//        holder.itemDescriptionTextView.setText(item.getDescription());
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


        holder.btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView btnfav = holder.btnfav;
                if (isStarFilled) {
                    btnfav.setImageResource(R.drawable.star);  // Change to empty star
                    isStarFilled = false;
                } else {
                    btnfav.setImageResource(R.drawable.starfull);  // Change to dark star
                    isStarFilled = true;
                }
            }

        });

    }

//    @Override
//    public int getItemCount() {
//        return itemList.size();
//    }



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