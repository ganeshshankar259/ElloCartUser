package com.ellocartuser.ellorooms_new.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.ellorooms_new.models.RoomModel;
import com.ellocartuser.home.adapterandmodel.Cart;

import java.util.List;

public class HomeDisplayAdapter extends RecyclerView.Adapter<HomeDisplayAdapter.ViewHolderNear> {

    List<String> titles;
    List<RoomModel> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    OnItemClickedCart onClick;

    public HomeDisplayAdapter(Context ctx, List<RoomModel> datalist, OnItemClickedCart onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
        //  this.images = images;
        if(ctx!=null) {
            this.inflater = LayoutInflater.from(ctx);
        }
    }

    @NonNull
    @Override
    public HomeDisplayAdapter.ViewHolderNear onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ellorooms_home_single,parent,false);
        return new HomeDisplayAdapter.ViewHolderNear(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull HomeDisplayAdapter.ViewHolderNear holder, int position) {

        holder.name.setText(datalist.get(position).getHotelName());
        holder.place.setText(datalist.get(position).getHotelAddress());
        holder.amount.setText("₹"+datalist.get(position).getRoomPrice().toString());
        holder.amount_strick.setText("₹"+datalist.get(position).getRoomMaxPrice().toString());
        holder.amount_strick.setPaintFlags(holder.amount_strick.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.rating.setText(datalist.get(position).getRoomRating().toString());

        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        Glide.with(holder.itemView)
                .load(datalist.get(position).getRoomImage1())
                .fitCenter()//.placeholder(R.drawable.placeholderello)
                .into(holder.img);

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }


    public class ViewHolderNear extends RecyclerView.ViewHolder{
        TextView name,place,amount,amount_strick,rating;
        ImageView img;
        ConstraintLayout llayout;
        public ViewHolderNear(@NonNull View itemView) {
            super(itemView);

            llayout = itemView.findViewById(R.id.llayout);
            name = itemView.findViewById(R.id.name);
            place = itemView.findViewById(R.id.place);
            amount = itemView.findViewById(R.id.amount);
            amount_strick = itemView.findViewById(R.id.amount_strick);
            rating = itemView.findViewById(R.id.rating);
            img = itemView.findViewById(R.id.img);



        }
    }

    public interface OnItemClickedCart {
        void onItemClicked(int position,String mParam1,String mParam2);
    }

}