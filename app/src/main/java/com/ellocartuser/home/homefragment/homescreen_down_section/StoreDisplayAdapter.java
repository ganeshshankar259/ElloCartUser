package com.ellocartuser.home.homefragment.homescreen_down_section;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Stores;
import com.ellocartuser.home.homefragment.CategoryFragment;
import com.ellocartuser.home.homefragment.NearbystoreFragment;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.home.homefragment.viewall.OffersViewAll;
import com.ellocartuser.utils.Util;

import java.util.List;

public class StoreDisplayAdapter extends RecyclerView.Adapter<StoreDisplayAdapter.ViewHolder> {

    List<String> titles;
    List<Stores> datalist;
    LayoutInflater inflater;
    FragmentActivity ctx;
    String color;
    StoreDisplayAdapter.OnItemClickeGrid onItemClickeGrid;
    int layout;
    homefragment homefragment;
    String catid;
    public StoreDisplayAdapter(FragmentActivity ctx, List<Stores> datalist, String color, homefragment homefragment,String catid){
        this.datalist = datalist;
        this.catid = catid;
        this.layout = layout;
        this.homefragment = homefragment;
        this.color = color;
        this.ctx = ctx;
   //     this.onItemClickeGrid = onItemClickeGrid;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setDataList( List<Stores > datalist){
        this.datalist = datalist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.homesingle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


//        if(!color.equals("0")) {
//
//            int val = Color.parseColor(color);
//            holder.title.setBackgroundColor(val);
//
//        }else {

//            int val = Color.parseColor("#002768");
//            holder.title.setBackgroundColor(val);
 //   }

        holder.title.setText(datalist.get(position).getSellerStoreName());
        holder.tvDuration.setText(datalist.get(position).getSeller_time());
        holder.tvrating.setText(datalist.get(position).getSellerRating());

        Glide.with(holder.itemView)
                .load(datalist.get(position).getSellerStoreImage())
                .fitCenter()//.placeholder(R.drawable.placeholderello)
                .into(holder.img);

        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(datalist.get(position).getStore_o().equals("0")) {

                    Toast.makeText(ctx,"Currently not accepting orders",Toast.LENGTH_LONG).show();
                }
            //    onItemClickeGrid.onItemClick(position,datalist.get(position).getCategory_id(),datalist.get(position).getCategory_name());
                Util.loadFragment(CategoryFragment.newInstance(catid, datalist.get(position).getSellerId(), datalist.get(position).getSellerStoreName()), ctx, homefragment);
            }
        });

        if(datalist.get(position).getStore_o().equals("0")) {

            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);

            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            holder.img.setColorFilter(filter);


            holder.title.setTextColor( Color.parseColor("#808080"));


        }else{
            holder.img.setColorFilter(null);
            holder.title.setTextColor( Color.parseColor("#002768"));
        }


//      holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout llayout;
        private TextView title,tvDuration,tvrating;
        private ImageView img;

        LinearLayout imgbgll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            llayout = itemView.findViewById(R.id.llayout);
            title = itemView.findViewById(R.id.txtview);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            img = itemView.findViewById(R.id.img);
            tvrating = itemView.findViewById(R.id.tvrating);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onItemClickeGrid.onItemClick(getAdapterPosition(),datalist.get(getAdapterPosition()).getCategory_id(),datalist.get(getAdapterPosition()).getCategory_name());
//                }
//            });
        }
    }

    public interface OnItemClickeGrid {
        void onItemClick(int position,String carid,String name);
    }
}