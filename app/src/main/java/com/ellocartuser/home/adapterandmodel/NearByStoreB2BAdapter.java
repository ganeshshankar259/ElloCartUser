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

public class NearByStoreB2BAdapter extends RecyclerView.Adapter<NearByStoreB2BAdapter.ViewHolderNear> {

    List<String> titles;
    List<B2BStore> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
     OnItemClickedNear onClick;
    String from;
    public NearByStoreB2BAdapter(Context ctx, List<B2BStore> datalist, OnItemClickedNear  onclick, String from){
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
        View view = inflater.inflate(R.layout.nearbystoresingleb2b,parent,false);
        return new ViewHolderNear(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNear holder, int position) {

            holder.title.setText(datalist.get(position).getB2bStoreName());

            //fav condition
//       if(!from.equals("wishlist")) {
            if (datalist.get(position).getB2b_wished().equals("1")) {
                holder.favimg.setImageResource(R.drawable.like_heart_3x);
            } else {
                holder.favimg.setImageResource(R.drawable.like_light_3x);
            }
//        }else{
//            holder.favimg.setVisibility(View.INVISIBLE);
//        }
         //   holder.rating.setRating(Float.parseFloat(datalist.get(position).getSellerRating()));
            holder.address.setText(datalist.get(position).getB2bAddress()+", "+datalist.get(position).getB2bCity()+", "+datalist.get(position).getB2bState());
         //   holder.rating.setRating(Float.parseFloat("1"));
            Glide.with(holder.itemView)
                    .load(datalist.get(position).getB2bStoreImage())
                    .fitCenter().placeholder(R.drawable.placeholderello)
                    .into(holder.gridIcon);
            holder.llayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.onItemClicked(position,datalist.get(position).getB2bId(),datalist.get(position).getB2bStoreName(),"");
          //          Toast.makeText(ctx, "Clicked -> " + position, Toast.LENGTH_SHORT).show();
                }
            });

      //  if(!from.equals("wishlist")) {
            holder.favimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.onItemClicked(position,datalist.get(position).getB2bId(),datalist.get(position).getB2bStoreName(),"fav");

                }
            });
    //    }
//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }


    public class ViewHolderNear extends RecyclerView.ViewHolder{
        TextView title,address;
        ImageView gridIcon,favimg;
        LinearLayout llayout;
        public ViewHolderNear(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlene);
            gridIcon = itemView.findViewById(R.id.gridIconne);
            address = itemView.findViewById(R.id.addressne);
            llayout = itemView.findViewById(R.id.llayout);
            favimg = itemView.findViewById(R.id.imageView7);
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
        void onItemClicked(int position,String mParam1,String storename,String type);
    }

}