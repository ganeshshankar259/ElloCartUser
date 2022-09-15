package com.ellocartuser.servicesscreens;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Categories;

import java.util.List;

public class ServiceMainScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> titles;
    List<Categories> datalist;
    LayoutInflater inflater;
    OnItemClickeGrid onItemClickeGrid;
    Context ctx;
    private static final int LEFT= 0;
    private static final int RIGHT= 1;

    public ServiceMainScreenAdapter(Context ctx, List<Categories> datalist, OnItemClickeGrid onItemClickeGrid){
        this.datalist = datalist;
        this.ctx = ctx;
        this.onItemClickeGrid = onItemClickeGrid;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =null;
        RecyclerView.ViewHolder viewHolder = null;

        if(viewType==LEFT)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serviceleftsingle,parent,false);
            viewHolder = new Left(view);
        }
        else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.servicerightsingle,parent,false);
            viewHolder= new Right(view);
        }

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if(holder.getItemViewType()== LEFT)
        {

            Left lft = (Left) holder;
         lft.title.setText(datalist.get(position).getCat_name());
          //  lft.title.setText(datalist.get(position).getCategory_name());

            Animation rightSwipe = AnimationUtils.loadAnimation(ctx, R.anim.sliderighttoleft);
            lft.llayout.startAnimation(rightSwipe);

            Glide.with(holder.itemView)
                    .load(datalist.get(position).getCat_image())
                    .fitCenter().placeholder(R.drawable.placeholderello)
                    .into(lft.img);

        }
        else {

            Right right = (Right) holder;
          right.title.setText(datalist.get(position).getCat_name());
       //     right.title.setText(datalist.get(position).getCategory_name());

            Animation rightSwipe = AnimationUtils.loadAnimation(ctx, R.anim.sliderlefttoright);
            right.llayout.startAnimation(rightSwipe);

            Glide.with(holder.itemView)
                    .load(datalist.get(position).getCat_image())
                    .fitCenter().placeholder(R.drawable.placeholderello)
                    .into(right.img);

        }


    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position%2==0)
            return LEFT;
        else
            return RIGHT;
    }

//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.custom_grid_layout,parent,false);
//        return new ViewHolder(view);
//    }
//    @Override
//    public int getItemCount() {
//        return (datalist==null)?0: datalist.size();
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.title.setText(datalist.get(position).getCat_name());
//
//        Glide.with(holder.itemView)
//                .load(datalist.get(position).getCat_image())
//                .fitCenter()
//                .into(holder.gridIcon);
//
////        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
//    }



    class Left extends RecyclerView.ViewHolder{

        private CardView llayout;
        private TextView title;
        private ImageView img;

        public Left(@NonNull View itemView) {
            super(itemView);

           llayout = itemView.findViewById(R.id.llayout);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.txtview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickeGrid.onItemClick(getAdapterPosition(),datalist.get(getAdapterPosition()).getCat_id(),datalist.get(getAdapterPosition()).getCat_name());
                }
            });


        }
    }


    class Right extends RecyclerView.ViewHolder{

        private CardView llayout;
        private TextView title;
        private ImageView img;


        public Right(@NonNull View itemView) {
            super(itemView);

            llayout = itemView.findViewById(R.id.llayout);
            title = itemView.findViewById(R.id.txtview);
            img = itemView.findViewById(R.id.img);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickeGrid.onItemClick(getAdapterPosition(),datalist.get(getAdapterPosition()).getCat_id(),datalist.get(getAdapterPosition()).getCat_name());
                }
            });

        }
    }


    public interface OnItemClickeGrid {
        void onItemClick(int position,String carid,String name);
    }
}