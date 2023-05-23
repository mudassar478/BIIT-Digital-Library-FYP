package com.example.biitdigitallibrarysystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biitdigitallibrarysystem.R;
import com.example.biitdigitallibrarysystem.models.TableOfContentModel;

import java.util.List;

public class TableOfContentAdapter extends RecyclerView.Adapter<TableOfContentAdapter.TableOfContentViewHolder> {

    Context mContext;
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

    @Override
    public void onBindViewHolder(@NonNull TableOfContentAdapter.TableOfContentViewHolder holder, int position) {
        final TableOfContentModel tableOFContent = tableOFContentList.get(position);

        holder.txtViewPageno.setText(""+tableOFContent.getPageno());
        holder.txtViewTOC.setText(""+tableOFContent.getKeywords());


    }


    @Override
    public int getItemCount() {
        return tableOFContentList.size();
    }



    public class TableOfContentViewHolder extends RecyclerView.ViewHolder{

        TextView txtViewPageno,txtViewTOC;

        public TableOfContentViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewPageno = itemView.findViewById(R.id.txtViewPageno);
            txtViewTOC = itemView.findViewById(R.id.txtViewTOC);
        }
    }
}
