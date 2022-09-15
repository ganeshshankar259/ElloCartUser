package com.ellocartuser.ellorooms_new.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.ellorooms_new.ElloRoomListPage;
import com.ellocartuser.ellorooms_new.FilterPage;
import com.ellocartuser.ellorooms_new.models.RoomModel;

import java.util.List;

public class ListDisplayAdapter extends RecyclerView.Adapter<ListDisplayAdapter.ViewHolderNear> {

    List<String> titles;
    List<RoomModel> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    OnItemClickedCart onClick;

    public ListDisplayAdapter(Context ctx, List<RoomModel> datalist, OnItemClickedCart onclick){
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
    public ListDisplayAdapter.ViewHolderNear onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.room_list_single,parent,false);
        return new ListDisplayAdapter.ViewHolderNear(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ListDisplayAdapter.ViewHolderNear holder, int position) {

        holder.percentage.setText(datalist.get(position).getRoomSave());
        holder.des.setText(datalist.get(position).getRoom_text());
        holder.hotel_type.setText(datalist.get(position).getHotel_type());
        holder.name.setText(datalist.get(position).getHotelName());
        holder.place.setText(datalist.get(position).getHotelAddress());
        holder.amount.setText("₹"+datalist.get(position).getRoomPrice().toString());
        holder.amount_strick.setText("₹"+datalist.get(position).getRoomMaxPrice().toString());
        holder.amount_strick.setPaintFlags(holder.amount_strick.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.rating.setText(datalist.get(position).getRoomRating().toString());

        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClick.onItemClicked(position,datalist.get(position).getRoomId(),"");

            }
        });

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClickedView(position,datalist.get(position).getRoomId(),"");

            }
        });

        Glide.with(holder.itemView)
                .load(datalist.get(position).getRoomImage1())
                .fitCenter()//.placeholder(R.drawable.placeholderello)
                .into(holder.img);

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }


    public class ViewHolderNear extends RecyclerView.ViewHolder{
        TextView name,place,amount,amount_strick,rating,hotel_type,des,percentage;
        ImageView img;
        ConstraintLayout llayout;
        Button btn;
        public ViewHolderNear(@NonNull View itemView) {
            super(itemView);

            percentage = itemView.findViewById(R.id.percentage);
            btn = itemView.findViewById(R.id.btn);
            des = itemView.findViewById(R.id.des);
            hotel_type = itemView.findViewById(R.id.hotel_type);
            llayout = itemView.findViewById(R.id.llayout);
            name = itemView.findViewById(R.id.name);
            place = itemView.findViewById(R.id.place);
            amount = itemView.findViewById(R.id.amount);
            amount_strick = itemView.findViewById(R.id.amount_strick);
            rating = itemView.findViewById(R.id.rating);
            img = itemView.findViewById(R.id.gridIconne);



        }
    }

    public interface OnItemClickedCart {
        void onItemClicked(int position,String mParam1,String mParam2);
        void onItemClickedView(int position,String mParam1,String mParam2);
    }

}