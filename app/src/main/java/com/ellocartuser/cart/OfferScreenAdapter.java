package com.ellocartuser.cart;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.home.adapterandmodel.Address;
import com.ellocartuser.home.adapterandmodel.Coupons;
import com.ellocartuser.utils.Util;

import java.util.List;

public class OfferScreenAdapter extends RecyclerView.Adapter<OfferScreenAdapter.ViewHolderNear> {

    List<String> titles;
    List<Coupons> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    OfferScreenAdapter.OnItemClickedAdd onClick;

    public OfferScreenAdapter(Context ctx, List<Coupons> datalist, OfferScreenAdapter.OnItemClickedAdd onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
    //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public OfferScreenAdapter.ViewHolderNear onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.offerssingle,parent,false);
        return new OfferScreenAdapter.ViewHolderNear(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull OfferScreenAdapter.ViewHolderNear holder, int position) {

        holder.name.setText(datalist.get(position).getCoupName());
        holder.peragraph.setText(String.valueOf(datalist.get(position).getCoup_title()));

        Glide.with(holder.itemView)
                .load(datalist.get(position).getCoup_banner())
                .fitCenter()
                .into(holder.imgicon);

        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (datalist.get(position).getCoup_status().equals("1")) {
                    onClick.onItemClickedCart(position, datalist.get(position));
                }else  {
                    if(!datalist.get(position).getCoup_msg().equals("")) {
                        Util.AlertWithOK((Activity) ctx, datalist.get(position).getCoup_msg());
                    }
                }
            }
        });

        if (datalist.get(position).getCoup_status().equals("1")) {
//            holder.layout.getBackground().clearColorFilter();
//            holder.imgicon.getBackground().clearColorFilter();
            holder.apply.setBackground(ContextCompat.getDrawable(ctx, R.drawable.btncornor));


        } else {

            holder.apply.setBackground(ContextCompat.getDrawable(ctx, R.drawable.btncornordisable));
           // holder.layout.getBackground().setColorFilter(ContextCompat.getColor(ctx, R.color.disabled), android.graphics.PorterDuff.Mode.MULTIPLY);
            //  holder.llayout.getBackground().setColorFilter(ContextCompat.getColor(ctx, R.color.disabled), android.graphics.PorterDuff.Mode.MULTIPLY);
         //   holder.imgicon.getBackground().setColorFilter(ContextCompat.getColor(ctx, R.color.gray), PorterDuff.Mode.MULTIPLY );

        }

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }


    public class ViewHolderNear extends RecyclerView.ViewHolder{
        TextView name,peragraph;
        ImageView imgicon;
        ConstraintLayout layout;
        AppCompatButton apply;

        public ViewHolderNear(@NonNull View itemView) {
            super(itemView);

            apply = itemView.findViewById(R.id.apply);
            name = itemView.findViewById(R.id.name);
            peragraph = itemView.findViewById(R.id.peragraph);
            imgicon = itemView.findViewById(R.id.imgicon);
            layout = itemView.findViewById(R.id.layout);



//            itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                onClick.onItemClickedCart(getAdapterPosition(),datalist.get(getAdapterPosition()));
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

    public interface OnItemClickedAdd {
        void onItemClickedCart(int position,Coupons cpn);
    }

}