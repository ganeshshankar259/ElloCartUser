package com.ellocartuser.home.adapterandmodel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.ellocartuser.R;

import java.util.List;

public class ProductDetailedSubVarentAdapter extends RecyclerView.Adapter<ProductDetailedSubVarentAdapter.ViewHolder> {

    List<String> titles;
    List<ProductSubs> datalist;
    LayoutInflater inflater;
    Context ctx;
    int layout;
    //declare interface
    private  ProductDetailedSubVarentAdapter.OnItemClickedSubVarient onClick;

    public ProductDetailedSubVarentAdapter(Context ctx, List<ProductSubs> datalist, ProductDetailedSubVarentAdapter.OnItemClickedSubVarient onclick,int layout){
        this.datalist = datalist;
        this.onClick = onclick;
        this.layout = layout;
        this.ctx=ctx;
    //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setDataList( List<ProductSubs> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public ProductDetailedSubVarentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layout,parent,false);
        return new ProductDetailedSubVarentAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailedSubVarentAdapter.ViewHolder holder, int position) {

        holder.title.setText(datalist.get(position).getSproductP2());

        if(datalist.get(position).getSelected().equals("1")){

            holder.layout.setBackgroundColor(ContextCompat.getColor(ctx, R.color.yellowselect));
            holder.title.setTextColor( Color.parseColor("#ffffff"));

        }else{

            holder.layout.setBackgroundColor(ContextCompat.getColor(ctx, R.color.bggray));
            holder.title.setTextColor( Color.parseColor("#000000"));

        }

//        Glide.with(holder.itemView)
//                .load(datalist.get(position).getCategory_image())
//                .fitCenter()
//                .into(holder.gridIcon);

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,km,address;
        ImageView gridIcon;
        ConstraintLayout layout;
        RatingBar rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
            layout = itemView.findViewById(R.id.layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    String selectidv="";
//                    for(int i=0;i<datalist.size();i++){
//                        if(datalist.get(i).getSelected().equals("1")){
//                            selectidv = datalist.get(i).get
//                        }
//
//                    }

                 //   Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                      onClick.onItemClickSubVarent(getAdapterPosition(),datalist.get(getAdapterPosition()).sproductId,datalist.get(getAdapterPosition()).vproductId);
                }
            });
        }
    }

    public interface OnItemClickedSubVarient {
        void onItemClickSubVarent(int position,String catid,String selectidv);
    }

}