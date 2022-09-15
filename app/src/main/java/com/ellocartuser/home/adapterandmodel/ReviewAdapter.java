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

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolderNear> {

    List<String> titles;
    List<Review> datalist;
    LayoutInflater inflater;
    Context ctx;
    String from,userid;
    //declare interface
    OnItemClickedNear onClick;
//shopereview,productpage
    public ReviewAdapter(Context ctx,List<Review> datalist,String from,String userid,OnItemClickedNear onClick){
        this.datalist = datalist;
        this.from = from;
        this.userid = userid;
        this.onClick = onClick;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolderNear onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.review_single,parent,false);
        return new ReviewAdapter.ViewHolderNear(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolderNear holder, int position) {

        System.out.println("rajuuu user id - "+userid+" <--> "+datalist.get(position).getUserId());
        try {



            holder.title.setText(datalist.get(position).getUserName());
            holder.rating.setRating(Float.parseFloat(datalist.get(position).getShoprateing()));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        if(from.equals("shopereview")) {
            if(datalist.get(position).getUser_rated().equals("1")){
                holder.edit.setVisibility(View.VISIBLE);
            }else {
                holder.edit.setVisibility(View.GONE);
            }
            holder.reviewdata.setText(datalist.get(position).getReviewsMessage());
        }else{
            if(userid.equals(datalist.get(position).getUserId())){
                holder.edit.setVisibility(View.VISIBLE);
            }else {
                holder.edit.setVisibility(View.GONE);
            }

            holder.reviewdata.setText(datalist.get(position).getReviewMessage());

        }
        try {
            holder.rating.setRating(Float.parseFloat(datalist.get(position).getReviewRate()));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        //   holder.rating.setRating(Float.parseFloat("1"));
        Glide.with(holder.itemView)
                .load(datalist.get(position).getUserImage())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.gridIcon);
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if(from.equals("shopereview")) {
    onClick.onItemClickededit(position, datalist.get(position).getReviewsId(), datalist.get(position).getUserImage(), datalist.get(position).getUserName(), datalist.get(position).getReviewsMessage(), datalist.get(position).getShoprateing());
}else{
    onClick.onItemClickededit(position, datalist.get(position).getReviewId(), datalist.get(position).getUserImage(), datalist.get(position).getUserName(), datalist.get(position).getReviewMessage(), datalist.get(position).getReviewRate());

}
                //          Toast.makeText(ctx, "Clicked -> " + position, Toast.LENGTH_SHORT).show();
            }
        });
//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public class ViewHolderNear extends RecyclerView.ViewHolder{
        TextView title,km,reviewdata;
        ImageView gridIcon,edit;
        LinearLayout llayout;
        RatingBar rating;
        public ViewHolderNear(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlene);
            gridIcon = itemView.findViewById(R.id.gridIconne);
            rating = itemView.findViewById(R.id.rateing);
            reviewdata = itemView.findViewById(R.id.rewiewdata);
            llayout = itemView.findViewById(R.id.llayout);
            edit = itemView.findViewById(R.id.edit);
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
        void onItemClickededit(int position,String id,String img,String username,String msg,String rate);
    }

}
