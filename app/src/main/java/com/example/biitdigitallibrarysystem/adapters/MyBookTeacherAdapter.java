package com.example.biitdigitallibrarysystem.adapters;


import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
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
import com.example.biitdigitallibrarysystem.models.AppDatabase;
import com.example.biitdigitallibrarysystem.models.DownloadImageTask;
import com.example.biitdigitallibrarysystem.models.LibraryBook;
import com.example.biitdigitallibrarysystem.models.MyBookTeacherModel;
import com.example.biitdigitallibrarysystem.models.PdfDownloader;
import com.example.biitdigitallibrarysystem.models.User;
import com.example.biitdigitallibrarysystem.models.UserDao;
import com.example.biitdigitallibrarysystem.teacherActivities.AddTOC_book;
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

        UserDao userDao= AppDatabase.getAppDatabase(context).getUserDao();
        List<User>booknameslist=userDao.getFvtFilePath(books.get(position).getTitle());
        if (!(booknameslist.isEmpty())){
            holder.btnfav.setImageResource(R.drawable.starfull);
        }else {
            holder.btnfav.setImageResource(R.drawable.star);
        }

//        MyBookTeacherModel item = itemList.get(position);
//        holder.itemNameTextView.setText(item.getName());
//        holder.itemDescriptionTextView.setText(item.getDescription());
        holder.Books.setText(books.get(position).getTitle());
        new DownloadImageTask(holder.imageupload).execute(imagePath+books.get(position).getImage_path());
//        holder.itemView.setOnClickListener(view ->
//                openPDFFile(filePath+books.get(position).getPdf_path()));

        holder.itemView.setOnClickListener(view ->
                openPdfFile(filePath + books.get(position).getPdf_path()));


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
                Intent i = new Intent(context.getApplicationContext(), AddTOC_book.class);
                context.startActivity(i);
            }
        });


        holder.btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////////fav start
                List<User> booknames=userDao.getFvtFilePath(books.get(position).getTitle());
                if (booknames.isEmpty()){
                    userDao.insertAll(new User(0,books.get(position).getTitle()));
                    holder.btnfav.setImageResource(R.drawable.starfull);
                    Toast.makeText(context, "Added to Bookmarks", Toast.LENGTH_SHORT).show();
                }else {
                    userDao.deleteFavourites(books.get(position).getTitle());
                    holder.btnfav.setImageResource(R.drawable.star);
                }
                /////fav end
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