package com.example.biitdigitallibrarysystem.adapters;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.apiServices.APIClient;
import com.example.biitdigitallibrarysystem.apiServices.Endpoint;
import com.example.biitdigitallibrarysystem.models.DownloadImageTask;
import com.example.biitdigitallibrarysystem.models.LibraryBook;
import com.example.biitdigitallibrarysystem.models.PdfDownloader;
import com.example.biitdigitallibrarysystem.teacherActivities.TableOFContent;

import java.util.ArrayList;

class Blval {
    public boolean bolcheck;

    public Blval() {
        bolcheck = false;
    }

}

public class LibraryBooksAdapter extends RecyclerView.Adapter<LibraryBooksAdapter.MyViewHolder> {
    Context context;
    boolean isStarFilled = false;

    ArrayList<LibraryBook> books;
    String imagePath = APIClient.getImagePath();
    String filePath = APIClient.getFilePath();
    ArrayList<Blval> blvals;
//sre
    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<LibraryBook> filterllist) {
        books = filterllist;
        notifyDataSetChanged();
    }
//endsre
    public LibraryBooksAdapter(Context context, ArrayList<LibraryBook> books) {
        this.context = context;
        this.books = books;

        blvals = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Blval b = new Blval();
            blvals.add(b);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Books;
        ImageView imageupload, btndownload, btnfav;
        Button btn_toc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Books = itemView.findViewById(R.id.book_name);
            imageupload = itemView.findViewById(R.id.img_bookupload);
            btn_toc = itemView.findViewById(R.id.btn_toc);
            btndownload = itemView.findViewById(R.id.btn_download);
            btnfav = itemView.findViewById(R.id.btn_fav);
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
        new DownloadImageTask(holder.imageupload).execute(imagePath + books.get(position).getImage_path());

        LibraryBook obj= books.get(position);




        holder.itemView.setOnClickListener(view ->
                openPdfFile(filePath + books.get(position).getPdf_path()));

        holder.btndownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PdfDownloader(context).downloadPdf(filePath + books.get(position).getPdf_path());
            }
        });
        holder.btn_toc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableOFContent.bid = books.get(position).getBid();
                Intent i = new Intent(context.getApplicationContext(), TableOFContent.class);
                context.startActivity(i);
            }
        });




        Blval val = blvals.get(position);

        holder.btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.btnfav.setSelected(val.bolcheck);

                ImageView btnfav = holder.btnfav;

//                if (isStarFilled) {
//                    btnfav.setImageResource(R.drawable.star);  // Change to empty star
//                    isStarFilled = false;
//                } else {
//                    btnfav.setImageResource(R.drawable.starfull);  // Change to dark star
//                    isStarFilled = true;
//                }
//                btnfav.setImageResource(R.drawable.star);

                        if(blvals.get(position).bolcheck){
            btnfav.setImageResource(R.drawable.star);
        }
        else{
            btnfav.setImageResource(R.drawable.starfull);
        }
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
