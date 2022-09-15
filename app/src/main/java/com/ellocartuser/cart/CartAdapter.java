package com.ellocartuser.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Stores;
import com.ellocartuser.home.adapterandmodel.Cart;
import com.ellocartuser.home.adapterandmodel.NearByStoreAdapter;

import java.util.List;

class CartAdapter  extends RecyclerView.Adapter<CartAdapter.ViewHolderNear> {

    List<String> titles;
    List<Cart> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    OnItemClickedCart onClick;

    public CartAdapter(Context ctx, List<Cart> datalist, OnItemClickedCart onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
        //  this.images = images;
        if(ctx!=null) {
            this.inflater = LayoutInflater.from(ctx);
        }
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolderNear onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cartsingle,parent,false);
        return new CartAdapter.ViewHolderNear(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolderNear holder, int position) {

        holder.titlename.setText(datalist.get(position).getProductName());
        holder.count.setText(datalist.get(position).getCartQty());
        holder.totalamt.setText("₹"+datalist.get(position).getCartPrice());
        //   holder.rating.setRating(Float.parseFloat(datalist.get(position).getSellerRating()));
     //   holder.amtitem.setText("₹ "+datalist.get(position).getProductSale()+"/ "+datalist.get(position).getProductMeasure());
        holder.amtitem.setText("₹"+datalist.get(position).getProductMrp());
        Glide.with(holder.itemView)
                .load(datalist.get(position).getProductImg1())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.gridIconne);

        if(datalist.get(position).getSeller_day().length()!=0){
            holder.schedule.setVisibility(View.VISIBLE);
            holder.schedule.setText("Pre Order : "+datalist.get(position).getSeller_day());
        }else{
            holder.schedule.setVisibility(View.GONE);
        }



        holder.delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClick.onItemClickedCart(position,"delet",datalist.get(position).getCartQty(),datalist.get(position).getProductId(),datalist.get(position).getSproductId(),datalist.get(position).getSellerId());


            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClick.onItemClickedCart(position,"plus",datalist.get(position).getCartQty(),datalist.get(position).getProductId(),datalist.get(position).getSproductId(),datalist.get(position).getSellerId());


            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if(Integer.valueOf(datalist.get(position).getCartQty())>1) {
    onClick.onItemClickedCart(position, "minus", datalist.get(position).getCartQty(), datalist.get(position).getProductId(), datalist.get(position).getSproductId(), datalist.get(position).getSellerId());
}else{
    Toast.makeText(ctx,"Minimum qty 1",Toast.LENGTH_LONG).show();
}

            }
        });
        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }


    public class ViewHolderNear extends RecyclerView.ViewHolder{
        TextView schedule,titlename,amtitem,count,totalamt;
        ImageView delet,gridIconne;
        LinearLayout llayout;
        Button minus,plus;
        public ViewHolderNear(@NonNull View itemView) {
            super(itemView);
            schedule = itemView.findViewById(R.id.schedule);
            gridIconne = itemView.findViewById(R.id.gridIconne);
            totalamt = itemView.findViewById(R.id.totalamt);
            titlename = itemView.findViewById(R.id.titlename);
            amtitem = itemView.findViewById(R.id.amtitem);
            count = itemView.findViewById(R.id.count);
            delet = itemView.findViewById(R.id.delet);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            llayout = itemView.findViewById(R.id.llayout);

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

    public interface OnItemClickedCart {
        void onItemClickedCart(int position,String mParam1,String qty,String productid,String sproductid,String sellerid);
    }

}