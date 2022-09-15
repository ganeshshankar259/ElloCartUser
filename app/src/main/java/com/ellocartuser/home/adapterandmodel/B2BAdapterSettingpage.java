package com.ellocartuser.home.adapterandmodel;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;

import java.util.List;

public class B2BAdapterSettingpage extends RecyclerView.Adapter<B2BAdapterSettingpage.ViewHolder> {

    List<String> titles;
    List<B2order> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface

    public B2BAdapterSettingpage(Context ctx, List<B2order> datalist){
        this.datalist = datalist;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<B2order> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    public void setDataList( List<B2order > datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public B2BAdapterSettingpage.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.b2bsingle,parent,false);
        return new B2BAdapterSettingpage.ViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull B2BAdapterSettingpage.ViewHolder holder, int position) {

        holder.title.setText(datalist.get(position).getProductName());
        holder.qty.setText("Qty : "+datalist.get(position).getCartQty());
        holder.store.setText("store : "+datalist.get(position).getSellerStoreName());

        Glide.with(holder.itemView)
                .load(datalist.get(position).getProductImg1())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.img);

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView qty,title,store,strickamt;
        ImageView img;
        LinearLayout llayout;
        View view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);

            llayout = itemView.findViewById(R.id.llayout);
            qty = itemView.findViewById(R.id.qty);
            store = itemView.findViewById(R.id.store);
            view = itemView.findViewById(R.id.view);
            strickamt = itemView.findViewById(R.id.strickamt);

            img = itemView.findViewById(R.id.img);


        }
    }


}