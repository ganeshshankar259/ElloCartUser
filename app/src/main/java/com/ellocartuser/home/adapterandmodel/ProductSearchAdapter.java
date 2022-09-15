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

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Stores;

import java.util.List;

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.ViewHolderNear> {

    List<String> titles;
    List<ProductSearchList> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    OnItemClickedProductsearch onClick;
    String from;
    public ProductSearchAdapter(Context ctx, List<ProductSearchList> datalist, OnItemClickedProductsearch  onclick, String from){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
        this.from=from;
      //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }


    @NonNull
    @Override
    public ViewHolderNear onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.productsearchsingle,parent,false);
        return new ViewHolderNear(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNear holder, int position) {

        if(from.equals("storename")){
            holder.storename.setVisibility(View.GONE);
        }else{
            holder.storename.setVisibility(View.VISIBLE);

            holder.storename.setText(datalist.get(position).getSellerStoreName());
        }
            holder.title.setText(datalist.get(position).getProductName());
            holder.price.setText("₹"+datalist.get(position).getProduct_price());




//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public class ViewHolderNear extends RecyclerView.ViewHolder{
        TextView title,price,storename;
        ImageView gridIcon,favimg;
        LinearLayout llayout;
        RatingBar rating;
        public ViewHolderNear(@NonNull View itemView) {
            super(itemView);
            storename = itemView.findViewById(R.id.storename);
            price = itemView.findViewById(R.id.price);
            title = itemView.findViewById(R.id.txtview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   //   onClick.onItemClicked(getAdapterPosition(),datalist.get(getAdapterPosition()).getCategoryId(),datalist.get(getAdapterPosition()).getSellerStoreName());
                      onClick.onItemClickedcatsearch(getAdapterPosition(),datalist.get(getAdapterPosition()).getSellerId(),datalist.get(getAdapterPosition()).getProductId());
                }
            });
        }
    }

    public interface OnItemClickedProductsearch {
     //   void onItemClicked(int position,String catcat,String name);
        void onItemClickedcatsearch(int position,String sellerid,String productid);
    }

}