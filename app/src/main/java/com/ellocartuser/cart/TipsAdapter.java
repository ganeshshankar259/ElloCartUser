package com.ellocartuser.cart;

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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Stores;
import com.ellocartuser.cart.checkoutmodel.TipsInsideModel;
import com.ellocartuser.home.homefragment.CategoryFragment;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.utils.Util;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {

    List<String> titles;
    List<TipsInsideModel> datalist;
    LayoutInflater inflater;
    Context ctx;
    String color;
    TipsAdapter.OnItemClickeGrid onItemClickeGrid;
    int layout;
    homefragment homefragment;
    String catid;
    
    public TipsAdapter(Context ctx, List<TipsInsideModel> datalist, TipsAdapter.OnItemClickeGrid onItemClickeGrid){
        this.datalist = datalist;
        this.onItemClickeGrid = onItemClickeGrid;
//        this.catid = catid;
//        this.layout = layout;
//        this.homefragment = homefragment;
//        this.color = color;
        this.ctx = ctx;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setDataList( List<TipsInsideModel > datalist){
        this.datalist = datalist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tipssingle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txt.setText("₹"+datalist.get(position).getAmt());

        if(datalist.get(position).getOption().equals("1")){
            holder.mosttiped.setVisibility(View.VISIBLE);
        }else{
            holder.mosttiped.setVisibility(View.GONE);
        }

        if(datalist.get(position).getSelected().equals("1")){


            holder.card.setBackgroundColor(Color.parseColor("#ff9e29"));
            holder.txt.setTextColor(Color.parseColor("#ffffff"));


        }else{

            holder.card.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.txt.setTextColor(Color.parseColor("#000000"));

        }
//


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickeGrid.onItemClicktip(position,datalist.get(position).getAmt(),"");
            }
        });


    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout llayout;
        private TextView txt,mosttiped;
        private ImageView img;
        CardView card;

        LinearLayout imgbgll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.card);
          //  llayout = itemView.findViewById(R.id.llayout);
            txt = itemView.findViewById(R.id.txt);
            mosttiped = itemView.findViewById(R.id.mosttiped);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onItemClickeGrid.onItemClick(getAdapterPosition(),datalist.get(getAdapterPosition()).getCategory_id(),datalist.get(getAdapterPosition()).getCategory_name());
//                }
//            });
        }
    }

    public interface OnItemClickeGrid {
        void onItemClicktip(int position,String carid,String name);
    }
}