package com.ellocartuser.home.adapterandmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;

import java.util.List;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.ViewHolderNear> {

    List<String> titles;
    List<Statement> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    OnItemClickedProductsearch onClick;
    String from;
    public StatementAdapter(Context ctx, List<Statement> datalist,String from){
        this.datalist = datalist;

        this.ctx=ctx;
        this.from=from;
    //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolderNear onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.statementsingle,parent,false);
        return new ViewHolderNear(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNear holder, int position) {

            holder.amt.setText(datalist.get(position).getUsrtAmount());
            holder.ref.setText(datalist.get(position).getUsrtRef());
            holder.date.setText(datalist.get(position).getUsrtDate());

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public class ViewHolderNear extends RecyclerView.ViewHolder{
        TextView ref,amt,date;
        ImageView gridIcon,favimg;
        LinearLayout llayout;
        RatingBar rating;
        public ViewHolderNear(@NonNull View itemView) {
            super(itemView);
            ref = itemView.findViewById(R.id.ref);
            date = itemView.findViewById(R.id.date);
            amt = itemView.findViewById(R.id.amt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   //   onClick.onItemClicked(getAdapterPosition(),datalist.get(getAdapterPosition()).getCategoryId(),datalist.get(getAdapterPosition()).getSellerStoreName());
                }
            });
        }
    }

    public interface OnItemClickedProductsearch {
        void onItemClicked(int position,String cat,String name);
    }

}