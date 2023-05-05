package com.example.biitdigitallibrarysystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.models.Bookscreen;

import java.util.ArrayList;

public class LibraryBooksAdapter extends RecyclerView.Adapter<LibraryBooksAdapter.MyViewHolder>{
    Context context;
    ArrayList<Bookscreen> books;

    public LibraryBooksAdapter(Context context, ArrayList<Bookscreen> books) {
        this.context = context;
        this.books = books;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Books;
        ImageView imageupload , button2 , button3;
        Button button1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Books = itemView.findViewById(R.id.book_name);
            imageupload=itemView.findViewById(R.id.img_bookupload);
            button1=itemView.findViewById(R.id.btn_toc);
            button2=itemView.findViewById(R.id.btn_download);
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
    public void onBindViewHolder(@NonNull LibraryBooksAdapter.MyViewHolder holder, int position) {
        holder.Books.setText(books.get(position).getBooks());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
