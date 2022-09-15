package com.ellocartuser.servicesscreens;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.ServiceProfile;
import com.ellocartuser.home.adapterandmodel.NearByStoreAdapter;
import com.ellocartuser.home.adapterandmodel.Products;

import java.util.List;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ViewHolder> {

    List<String> titles;
    List<ServiceProfile> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    private ServiceListAdapter.OnItemClickedproduct onClick;

    public ServiceListAdapter(Context ctx, List<ServiceProfile> datalist, ServiceListAdapter.OnItemClickedproduct onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<ServiceProfile> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    public void setDataList( List<ServiceProfile> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public ServiceListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.service_profile_single,parent,false);
        return new ServiceListAdapter.ViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceListAdapter.ViewHolder holder, int position) {

        holder.title.setText(datalist.get(position).getPost_title());

//        holder.address.setText(datalist.get(position).getSellerStoreAddress()+", "+-datalist.get(position).getSellerCity()+", "+datalist.get(position).getSellerPincode());
//        if(String.valueOf(datalist.get(position).getSellerDistance()).equals("1")) {
//            holder.km.setText(String.valueOf(datalist.get(position).getSellerDistance()) + "km");
//        }else{
//            holder.km.setText(String.valueOf(datalist.get(position).getSellerDistance()) + "kms");
//        }
//        holder.rating.setRating(Float.parseFloat(datalist.get(position).getSellerRating()));

        //holder.rating.setRating(Float.parseFloat(datalist.get(position).getPost_rating()));
        holder.views.setText(datalist.get(position).getPost_views());
        holder.star.setText(datalist.get(position).getPost_rating());
        Glide.with(holder.itemView)
                .load(datalist.get(position).getPost_img1())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.gridIcon);
        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClickProduct(position,datalist.get(position).getProId(),datalist.get(position).getPost_title());

                SharedPreferences pref = ctx.getSharedPreferences("SERVICES_DATA", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                //   editor.putString("boy",resource.getBoy());
                editor.putString("post_id", datalist.get(position).getProId());
                editor.putString("title", datalist.get(position).getPost_title());
                editor.putString("tut_id", datalist.get(position).getPost_user());
                editor.putString("image", datalist.get(position).getPost_img1());
                editor.commit();

            }
        });


//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,km,address,views,star;
        ImageView gridIcon,favimg;
        ConstraintLayout llayout;
        RatingBar rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            star = itemView.findViewById(R.id.star);
            title = itemView.findViewById(R.id.titlene);
            gridIcon = itemView.findViewById(R.id.gridIconne);
            rating = itemView.findViewById(R.id.rateing);
            views = itemView.findViewById(R.id.views);
            address = itemView.findViewById(R.id.addressne);
            llayout = itemView.findViewById(R.id.llayout);
            favimg = itemView.findViewById(R.id.imageView7);
//            itemView.setOnClickListener(new View.OnClickListener() {
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

    public interface OnItemClickedproduct {
        void onItemClickProduct(int position,String catid,String name);
    }

}