package com.ellocartuser.orders;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;

import java.util.List;

class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    String from;
    List<String> titles;
    List<ProductOrderModal> datalist;
    LayoutInflater inflater;
    List<OrderModel> orderModals;
    Context ctx;
    //declare interface

    public OrderItemAdapter(Context ctx, List<ProductOrderModal> datalist,List<OrderModel> orderModals,String from){
        this.datalist = datalist;
        this.from = from;
        this.orderModals = orderModals;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setDataList( List<ProductOrderModal> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public OrderItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.orderitemsingle,parent,false);
        return new OrderItemAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemAdapter.ViewHolder holder, int position) {

        holder.title.setText(datalist.get(position).getProductName());
        holder.amt.setText("₹" +datalist.get(position).getOrderedAmount());
        if(datalist.get(position).getOrderedQty().equals("1")){
            holder.item.setText(datalist.get(position).getOrderedQty() + " Item");

        }else {
            holder.item.setText(datalist.get(position).getOrderedQty() + " Items");
        }
        Glide.with(holder.itemView)
                .load(datalist.get(position).getProductImg1())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.gridIcon);

        if(from.equals("complete")) {
            if (datalist.get(position).getProductReview().equals("0")) {
                holder.reviewbtn.setVisibility(View.VISIBLE);
            } else {
                holder.reviewbtn.setVisibility(View.INVISIBLE);
            }
        } else{
            holder.reviewbtn.setVisibility(View.INVISIBLE);
        }

        holder.reviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(ctx,ReviewScreen.class);
                 ii.putExtra("type","product");
                 ii.putExtra("name",datalist.get(position).getProductName());
                 ii.putExtra("img",datalist.get(position).getProductImg1());
                 ii.putExtra("amt",datalist.get(position).getOrderedAmount());
                 ii.putExtra("orderid",orderModals.get(0).getOrderId());
                 ii.putExtra("productid",datalist.get(position).getProductId());
                ctx.startActivity(ii);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,amt,item;
        ImageView gridIcon;
        RatingBar rating;
        Button reviewbtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.storename);
//          title.setSelected(true);
            amt = itemView.findViewById(R.id.amt);
            item = itemView.findViewById(R.id.address);
            gridIcon = itemView.findViewById(R.id.img);
            reviewbtn = itemView.findViewById(R.id.reviewbtn);

//            rating = itemView.findViewById(R.id.rateing);
//            km = itemView.findViewById(R.id.kmne);
//            address = itemView.findViewById(R.id.addressne);


        }
    }


}