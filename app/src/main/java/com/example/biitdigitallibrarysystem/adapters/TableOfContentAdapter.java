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
import com.example.biitdigitallibrarysystem.models.AppDatabase;
import com.example.biitdigitallibrarysystem.models.LibraryBook;
import com.example.biitdigitallibrarysystem.models.TableOfContentModel;
import com.example.biitdigitallibrarysystem.models.User;
import com.example.biitdigitallibrarysystem.models.UserDao;

import java.util.ArrayList;
import java.util.List;

public class TableOfContentAdapter extends RecyclerView.Adapter<TableOfContentAdapter.TableOfContentViewHolder> {

    Context mContext;
    boolean isStarFilled = false;
    List<TableOfContentModel> tableOFContentList;


    //constructor to receive data from the activity
    public TableOfContentAdapter(Context mContext, List<TableOfContentModel> tableOFContentList) {
        this.mContext = mContext;
        this.tableOFContentList = tableOFContentList;
    }

    @NonNull
    @Override
    public TableOfContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_tableofcontent, parent, false);
        return new TableOfContentViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TableOfContentAdapter.TableOfContentViewHolder holder, int position) {
        final TableOfContentModel tableOFContent = tableOFContentList.get(position);

        holder.txtViewPageno.setText(""+tableOFContent.getPageno());
        holder.txtViewTOC.setText(""+tableOFContent.getKeywords());
        UserDao userDao= AppDatabase.getAppDatabase(mContext).getUserDao();
        List<User>booknameslist=userDao.getFvtFilePath(String.valueOf(tableOFContent.getPageno()));
        if (!(booknameslist.isEmpty())){
            holder.imgViewStar.setImageResource(R.drawable.starfull);
        }else {
            holder.imgViewStar.setImageResource(R.drawable.star);
        }


        holder.imgViewStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> booknames=userDao.getFvtFilePath(String.valueOf(tableOFContent.getPageno()));
                if (booknames.isEmpty()){
                    userDao.insertAll(new User(0,String.valueOf(tableOFContent.getPageno())));
                    holder.imgViewStar.setImageResource(R.drawable.starfull);
                    Toast.makeText(mContext, "Added to Bookmarks", Toast.LENGTH_SHORT).show();
                }else {
                    userDao.deleteFavourites(String.valueOf(tableOFContent.getPageno()));
                    holder.imgViewStar.setImageResource(R.drawable.star);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return tableOFContentList.size();
    }



    public class TableOfContentViewHolder extends RecyclerView.ViewHolder{

        TextView txtViewPageno,txtViewTOC;
        ImageView imgViewStar;

        public TableOfContentViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewPageno = itemView.findViewById(R.id.txtViewPageno);
            txtViewTOC = itemView.findViewById(R.id.txtViewTOC);
            imgViewStar= itemView.findViewById(R.id.imgViewStarTOC);
        }
    }
}
