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

import de.hdodenhof.circleimageview.CircleImageView;

class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.ViewHolder> {

    List<String> titles;
    List<OrderModel> datalist;
    LayoutInflater inflater;
    String from;
    Context ctx;
    //declare interface
    private CompletedAdapter.OnItemClickedcomp onClick;
    String tempfrom="";

    public CompletedAdapter(Context ctx, List<OrderModel> datalist, CompletedAdapter.OnItemClickedcomp onclick,String from){
        this.datalist = datalist;
        this.from = from;
        this.onClick = onclick;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setDataList( List<OrderModel> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public CompletedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.completeordersingle,parent,false);
        return new CompletedAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedAdapter.ViewHolder holder, int position) {

        holder.title.setText(datalist.get(position).getSellerStoreName());
        holder.phnnum.setText(datalist.get(position).getSellerPhone());
        //holder.items.setText(String.valueOf(datalist.get(position).getOrderTotalItems())+" Item ");
        holder.order.setText("Ordered : "+datalist.get(position).getOrderDate());
      //  holder.status.setText(datalist.get(position).getOrderStatus());
//        if(datalist.get(position).getOrderType().equals("1")){
//            holder.type.setText("Home Delivery");
//        } else {
//            holder.type.setText("Pickup");
//        }

        if(datalist.get(position).getSellerReview().equals("0")){
            holder.storereviewbtn.setVisibility(View.VISIBLE);
        }else{
            holder.storereviewbtn.setVisibility(View.GONE);
        }

        try {
            if (datalist.get(position).getDelivery_review().equals("0")) {
                holder.deliveryreviewbtn.setVisibility(View.VISIBLE);
            } else {
                holder.deliveryreviewbtn.setVisibility(View.GONE);
            }
        }catch (Exception ex){

        }

        if(datalist.get(position).getSellerReview().equals("0")){
            holder.storereviewbtn.setVisibility(View.VISIBLE);
        } else {
            holder.storereviewbtn.setVisibility(View.GONE);
        }
        if(datalist.get(position).getOrderStatus().equals("1")){
          //  holder.status.setText("Order received");
        } else if( datalist.get(position).getOrderStatus().equals("2")){
          //  holder.status.setText("Picked up");
        }else if(datalist.get(position).getOrderStatus().equals("3")){
           // holder.status.setText("Completed");
            tempfrom="complete";
        }else if( datalist.get(position).getOrderStatus().equals("4")){
            holder.storereviewbtn.setVisibility(View.GONE);
            holder.deliveryreviewbtn.setVisibility(View.GONE);
          //  holder.status.setText("Cancelled");
            tempfrom="canclelled";
        }
        if(datalist.get(position).getOrderPayType().equals("cod")){
            holder.pay.setText("Paid via COD");
        }else {
            holder.pay.setText("Paid via " + datalist.get(position).getOrderPayType());
        }
        String typee="";
        if(datalist.get(position).getOrderTotalItems().toString().trim().equals("1")){
            typee="item";
        }else{
            typee="items";
        }
        holder.items.setText(datalist.get(position).getOrderTotalItems().toString());
        holder.amt.setText("₹ "+datalist.get(position).getOrderFinal());
        Glide.with(holder.itemView)
                .load(datalist.get(position).getSellerStoreImage())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.gridIcon);

        holder.phnnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClickComp(position,datalist.get(position).getOrderId(),"call",datalist.get(position).getSellerPhone(),"",datalist.get(position).getOrder_type2());

            }
        });

        holder.phnicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClickComp(position,datalist.get(position).getOrderId(),"call",datalist.get(position).getSellerPhone(),"",datalist.get(position).getOrder_type2());
            }
        });

        holder.storereviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(ctx,ReviewScreen.class);
                ii.putExtra("type","seller");
                ii.putExtra("name",datalist.get(position).getSellerStoreName());
                ii.putExtra("img",datalist.get(position).getSellerStoreImage());
                ii.putExtra("address",datalist.get(position).getSellerStoreAddress());
                ii.putExtra("orderid",datalist.get(position).getOrderId());
                ii.putExtra("sellerid",datalist.get(position).getSellerId());
                ctx.startActivity(ii);
            }
        });

        holder.deliveryreviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(ctx,DeliveryReviewScreen.class);
                ii.putExtra("type","delivery");
                ii.putExtra("name",datalist.get(position).getBoy_name());
                ii.putExtra("img",datalist.get(position).getBoy_image());
                ii.putExtra("address","");
                ii.putExtra("orderid",datalist.get(position).getOrderId());
                ii.putExtra("sellerid",datalist.get(position).getSellerId());
                ctx.startActivity(ii);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,phnnum,items,order,status,type,pay,amt;
        ImageView phnicon;
        ImageView gridIcon;
        ConstraintLayout layout;
        RatingBar rating;
        Button storereviewbtn,deliveryreviewbtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deliveryreviewbtn = itemView.findViewById(R.id.deliveryreviewbtn);
            title = itemView.findViewById(R.id.title2);
//            title.setSelected(true);
            phnicon = itemView.findViewById(R.id.imageView6);
            amt = itemView.findViewById(R.id.amt);
            storereviewbtn = itemView.findViewById(R.id.storereviewbtn);
            phnnum = itemView.findViewById(R.id.phnnum);
            items = itemView.findViewById(R.id.phonenumber);
            order = itemView.findViewById(R.id.order);
            pay = itemView.findViewById(R.id.pay);
            type = itemView.findViewById(R.id.type);
            status = itemView.findViewById(R.id.status);
            gridIcon = itemView.findViewById(R.id.imgicon);
            layout = itemView.findViewById(R.id.layout);
//            rating = itemView.findViewById(R.id.rateing);
//            km = itemView.findViewById(R.id.kmne);
//            address = itemView.findViewById(R.id.addressne);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent i= new Intent(v.getContext(), SubCategory.class);
//                    i.putExtra("id",datalist.get(getAdapterPosition()).getId());
//                    i.putExtra("name",datalist.get(getAdapterPosition()).getName());
//                    v.getContext().startActivity(i);
                    // Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    onClick.onItemClickComp(getAdapterPosition(),datalist.get(getAdapterPosition()).getOrderId(),"","",tempfrom,datalist.get(getAdapterPosition()).getOrder_type2());
                }
            });
        }
    }

    public interface OnItemClickedcomp {
        void onItemClickComp(int position,String catid,String type,String num,String from,String order2);
    }

}