package com.ellocartuser.home.adapterandmodel;

import android.content.Context;
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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CatogeryNewAdapter extends RecyclerView.Adapter<CatogeryNewAdapter.ViewHolder> {

    List<String> titles;
    List<Categories> datalist;
    LayoutInflater inflater;
    Context ctx;
    CatogeryNewAdapter.OnItemClickeGridNew onItemClickeGridnew;
    int layout;

    public CatogeryNewAdapter(Context ctx, List<Categories> datalist, CatogeryNewAdapter.OnItemClickeGridNew onItemClickeGrid,int layout){
        this.datalist = datalist;
        this.layout = layout;
        this.ctx = ctx;
        this.onItemClickeGridnew = onItemClickeGrid;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setDataList( List<Categories > datalist){
        this.datalist = datalist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CatogeryNewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layout,parent,false);
        return new CatogeryNewAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CatogeryNewAdapter.ViewHolder holder, int position) {

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

                onItemClickeGridnew.onItemClick(position,datalist.get(position).getCategory_id(),datalist.get(position).getCategory_name());

            }
        });

//      holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView llayout;
        private TextView title;
        private CircleImageView img;

        LinearLayout imgbgll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            llayout = itemView.findViewById(R.id.cvMain);
            title = itemView.findViewById(R.id.tvTitle);
            img = itemView.findViewById(R.id.food);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onItemClickeGrid.onItemClick(getAdapterPosition(),datalist.get(getAdapterPosition()).getCategory_id(),datalist.get(getAdapterPosition()).getCategory_name());
//                }
//            });
        }
    }

    public interface OnItemClickeGridNew {
        void onItemClick(int position,String carid,String name);
    }
}