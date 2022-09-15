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

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolderNear> {

    List<String> titles;
    List<Stores> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    OnItemClickedNear onClick;

    public WishListAdapter(Context ctx, List<Stores> datalist, OnItemClickedNear  onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
      //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolderNear onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nearbystoresingle,parent,false);
        return new ViewHolderNear(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNear holder, int position) {

            holder.title.setText(datalist.get(position).getSellerStoreName());
         //   holder.rating.setRating(Float.parseFloat(datalist.get(position).getSellerRating()));
            holder.address.setText(datalist.get(position).getSellerStoreAddress()+", "+datalist.get(position).getSellerCity());
            if(String.valueOf(datalist.get(position).getSellerDistance()).equals("1")) {
                holder.km.setText(String.valueOf(datalist.get(position).getSellerDistance()) + "km");
            }else{
                holder.km.setText(String.valueOf(datalist.get(position).getSellerDistance()) + "kms");
            }
           holder.rating.setRating(Float.parseFloat(datalist.get(position).getSellerRating()));
         //   holder.rating.setRating(Float.parseFloat("1"));
            Glide.with(holder.itemView)
                    .load(datalist.get(position).getSellerStoreImage())
                    .fitCenter().placeholder(R.drawable.placeholderello)
                    .into(holder.gridIcon);
            holder.llayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.onItemClicked(position,datalist.get(position).getSellerId(),datalist.get(position).getSellerStoreName());
          //          Toast.makeText(ctx, "Clicked -> " + position, Toast.LENGTH_SHORT).show();
                }
            });
//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public class ViewHolderNear extends RecyclerView.ViewHolder{
        TextView title,km,address;
        ImageView gridIcon;
        LinearLayout llayout;
        RatingBar rating;
        public ViewHolderNear(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlene);
            gridIcon = itemView.findViewById(R.id.gridIconne);
            rating = itemView.findViewById(R.id.rateing);
            km = itemView.findViewById(R.id.kmne);
            address = itemView.findViewById(R.id.addressne);
            llayout = itemView.findViewById(R.id.llayout);
//            itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                onClick.onItemClicked(getAdapterPosition(),datalist.get(getAdapterPosition()).getSellerId());
//
//                }
//            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(ctx,"clicked",Toast.LENGTH_LONG).show();
//                }
//            });

        }
    }

    public interface OnItemClickedNear {
        void onItemClicked(int position,String mParam1,String storename);
    }

}