package com.ellocartuser.home.adapterandmodel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.servicesscreens.ServiceMainScreenAdapter;

import java.util.List;

public class GridAdapterService extends RecyclerView.Adapter<GridAdapterService.ViewHolder> {

    List<String> titles;
    List<Categories> datalist;
    LayoutInflater inflater;
    Context ctx;
    GridAdapterService.OnItemClickeGrid onItemClickeGrid;
    int layout;

    public GridAdapterService(Context ctx, List<Categories> datalist, GridAdapterService.OnItemClickeGrid onItemClickeGrid,int layout){
        this.datalist = datalist;
        this.layout = layout;
        this.ctx = ctx;
        this.onItemClickeGrid = onItemClickeGrid;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setDataList( List<Categories > datalist){
        this.datalist = datalist;
        notifyDataSetChanged();
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

//        try {
//            if (!datalist.get(position).getCategory_clr().equals("0")) {
//                int val = Color.parseColor(datalist.get(position).getCategory_clr());
//                holder.title.setBackgroundColor(val);
//            } else {
//                int val = Color.parseColor(" #ff9e29");
//                holder.title.setBackgroundColor(val);
//            }
//        }catch (Exception ex){ ex.printStackTrace(); }

        holder.title.setText(datalist.get(position).getCategory_name());

        Glide.with(holder.itemView)
                .load(datalist.get(position).getCategory_image())
                .fitCenter()//.placeholder(R.drawable.placeholderello)
                .into(holder.img);

        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onItemClickeGrid.onItemClick(position,datalist.get(position).getCategory_id(),datalist.get(position).getCategory_name());

            }
        });

//      holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout llayout;
        private TextView title;
        private ImageView img;

        LinearLayout imgbgll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            llayout = itemView.findViewById(R.id.llayout);
            title = itemView.findViewById(R.id.txtview);
            img = itemView.findViewById(R.id.img);


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