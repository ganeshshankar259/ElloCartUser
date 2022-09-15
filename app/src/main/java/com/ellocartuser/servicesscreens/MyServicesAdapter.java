    package com.ellocartuser.servicesscreens;

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
import com.ellocartuser.apiservices.model.ServicePostModel;
import com.ellocartuser.apiservices.model.ServiceProfile;

import java.util.List;

public class MyServicesAdapter extends RecyclerView.Adapter<MyServicesAdapter.ViewHolder> {

    List<String> titles;
    List<ServicePostModel> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    OnItemClickedMyServ onClick;

    public MyServicesAdapter(Context ctx, List<ServicePostModel> datalist, MyServicesAdapter.OnItemClickedMyServ onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<ServicePostModel> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    public void setDataList( List<ServicePostModel> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public MyServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.myservice_single,parent,false);
        return new MyServicesAdapter.ViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyServicesAdapter.ViewHolder holder, int position) {

        holder.title.setText(datalist.get(position).getPostTitle());

//        holder.address.setText(datalist.get(position).getSellerStoreAddress()+", "+-datalist.get(position).getSellerCity()+", "+datalist.get(position).getSellerPincode());
//        if(String.valueOf(datalist.get(position).getSellerDistance()).equals("1")) {
//            holder.km.setText(String.valueOf(datalist.get(position).getSellerDistance()) + "km");
//        }else{
//            holder.km.setText(String.valueOf(datalist.get(position).getSellerDistance()) + "kms");
//        }
//        holder.rating.setRating(Float.parseFloat(datalist.get(position).getSellerRating()));


      holder.views.setText(datalist.get(position).getPost_views());
        Glide.with(holder.itemView)
                .load(datalist.get(position).getPostImg1())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.gridIcon);
        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClickProduct(position,datalist.get(position).getPostId());

            }
        });


//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,km,address,views,service;
        ImageView gridIcon,favimg;
        LinearLayout llayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlene);
            gridIcon = itemView.findViewById(R.id.gridIconne);
            service = itemView.findViewById(R.id.service);
            views = itemView.findViewById(R.id.views);
            address = itemView.findViewById(R.id.addressne);
            llayout = itemView.findViewById(R.id.llayout);
            favimg = itemView.findViewById(R.id.imageView7);
        //    itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                onClick.onItemClickProduct(getAdapterPosition(),datalist.get(getAdapterPosition()).getProId());
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

    public interface OnItemClickedMyServ {
        void onItemClickProduct(int position,String catid);
    }

}