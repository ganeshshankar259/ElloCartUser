package com.ellocartuser.home.adapterandmodel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.apiservices.model.TrendStore;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    List<String> titles;
    List<Categories> datalist;
    LayoutInflater inflater;
    String typee;
    ServicesAdapter.OnItemClickeGrid onItemClickeGrid;
    int layout;

    public ServicesAdapter(Context ctx, List<Categories> datalist, ServicesAdapter.OnItemClickeGrid onItemClickeGrid,int layout,String typee){
        this.datalist = datalist;
        this.typee = typee;
        this.layout = layout;
        this.onItemClickeGrid = onItemClickeGrid;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(datalist.get(position).getCat_name());

//        if(typee.equals("detail")){
//            holder.title.setBackgroundColor(Color.parseColor("#ffffff"));
//
//        }else {
//            if (datalist.get(position).getServcat_clr() != null) {
//                if (!datalist.get(position).getServcat_clr().equals("")) {
//                    holder.title.setBackgroundColor(Color.parseColor(datalist.get(position).getServcat_clr()));
//
//                } else {
//                    holder.title.setBackgroundColor(Color.parseColor("#858585"));
//                }
//            }
//        }

        Glide.with(holder.itemView)
                .load(datalist.get(position).getCat_image())
                .fitCenter()//.placeholder(R.drawable.placeholderello)
                .into(holder.img);

        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickeGrid.onItemClickservice(position,datalist.get(position).getCat_id(),datalist.get(position).getCat_name());
            }
        });

//      holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout llayout;
        private TextView title;
        private ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            llayout = itemView.findViewById(R.id.llayout);
            title = itemView.findViewById(R.id.txtview);
            img = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                
                    //     onItemClickeGrid.onItemClick(position,datalist.get(position).getCategory_id(),datalist.get(position).getCategory_name());
                }
            });
        }
    }

    public interface OnItemClickeGrid {
     //   void onItemClick(int position,String carid,String name);
        void onItemClickservice(int position,String carid,String name);
    }
}