package com.ellocartuser.orders;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.List;

class PendingOrdersAdapter extends RecyclerView.Adapter<PendingOrdersAdapter.ViewHolder> {

    List<String> titles;
    List<OrderModel> datalist;
    LayoutInflater inflater;
    Context ctx;
 // declare interface
    private PendingOrdersAdapter.OnItemClickedpend onClick;

    public PendingOrdersAdapter(Context ctx, List<OrderModel> datalist, PendingOrdersAdapter.OnItemClickedpend onclick){
        this.datalist = datalist;
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
    public PendingOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pendingordersingle,parent,false);
        return new PendingOrdersAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull PendingOrdersAdapter.ViewHolder holder, int position) {

        int status=0;

        holder.title.setText(datalist.get(position).getSellerStoreName());
        holder.phnnum.setText(datalist.get(position).getSellerPhone());

        if(datalist.get(position).getOrderTotalItems().toString().equals("1")) {
            holder.items.setText(String.valueOf(datalist.get(position).getOrderTotalItems()) );
        }else{
            holder.items.setText(String.valueOf(datalist.get(position).getOrderTotalItems()));
// + " Items "
        }

        holder.order.setText(datalist.get(position).getOrderDate());

        if(datalist.get(position).getOrderType().equals("1")){
            holder.type.setText("Home Delivery");
        } else {
            holder.type.setText("Pickup");
        }

//        if(datalist.get(position).getOrderType().equals("0") && datalist.get(position).getOrderStatus().equals("0")){
////            holder.bar.setImageResource(R.drawable.bar);
//
//        } else
        //pick
            if(datalist.get(position).getOrderType().equals("0")){
                if( datalist.get(position).getOrderStatus().equals("1")){
                  //  holder.bar.setImageResource(R.drawable.bar);   dot undali
                 //   holder.bar.setImageResource(R.drawable.dry_clean);
                    status=1;
                }else
                if( datalist.get(position).getOrderStatus().equals("2")){
                 //   holder.bar.setImageResource(R.drawable.bar);
                    status=2;
                }

              //  holder.started.setVisibility(View.GONE);
             //   holder.deliveryasign.setVisibility(View.GONE);
            }
            //hm d
      else
        if(datalist.get(position).getOrderAssign().equals("0")){
            if( datalist.get(position).getOrderStatus().equals("1")){
             // holder.bar.setImageResource(R.drawable.dry_clean);  //dotundali
                //orderasign
                status=1;

            }else
            if( datalist.get(position).getOrderStatus().equals("2")){
                //sellerconfirem
              //   holder.bar.setImageResource(R.drawable.bar);
                status=2;

            }
        }else  if(datalist.get(position).getOrderAssign().equals("1")){

            if( datalist.get(position).getOrderStatus().equals("2")){
                //sellerconfirem
                status=2;

              //  holder.bar.setImageResource(R.drawable.bar);
            }

        }

        if(datalist.get(position).getOrderAssign().equals("2")){
            //delivery accept
          //  holder.bar.setImageResource(R.drawable.bar2);
            status=3;

        } else  if(datalist.get(position).getOrderAssign().equals("3")){
            //boy start
        //    holder.bar.setImageResource(R.drawable.bar3new);
            status=4;
        }
        else if(datalist.get(position).getOrderStatus().equals("3")){  //delivery confirm
         //   holder.bar.setImageResource(R.drawable.bar2);
            status=3;
        }

        if(datalist.get(position).getOrderPayType().toLowerCase().equals("cod")){
            holder.pay.setText("Paid Via COD");
        }else {
            holder.pay.setText("Paid Via " + datalist.get(position).getOrderPayType());
        }

        holder.amt.setText("₹"+datalist.get(position).getOrderFinal());
        Glide.with(holder.itemView)
                .load(datalist.get(position).getSellerStoreImage())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.gridIcon);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClickPend(position,datalist.get(position).getOrderId(),"","",datalist.get(position).getOrderType(),datalist.get(position).getOrderStatus(),datalist.get(position).getOrderAssign(),datalist.get(position).getOrder_type2());
            }
        });
//        holder.phnnum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onClick.onItemClickPend(position,datalist.get(position).getOrderId(),"call",datalist.get(position).getSellerPhone(),"","","",datalist.get(position).getOrder_type2());
//            }
//        });

//        if(datalist.get(position).getOrder_type2().equals("1")){
//            holder.tracktitle.setVisibility(View.GONE);
//            holder.bar.setVisibility(View.GONE);
//            holder.line.setVisibility(View.GONE);
//        }else{
//            holder.tracktitle.setVisibility(View.VISIBLE);
//            holder.bar.setVisibility(View.VISIBLE);
//            holder.line.setVisibility(View.VISIBLE);
//        }

        switch (status) {
            case 1:
                holder.stateprogress.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                break;
            case 2:
                holder.stateprogress.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                break;
            case 3:
                holder.stateprogress.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                break;
            case 4:
                holder.stateprogress.setAllStatesCompleted(true);
                break;
        }

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,phnnum,items,order,type,pay,amt;
        ImageView gridIcon; //,phnicon;
        ConstraintLayout layout;
        RatingBar rating;
        View line;
        StateProgressBar stateprogress;
        String[] descriptionData = {"Received", "Confirmed", "Assigned", "started"};

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            line = itemView.findViewById(R.id.view2);
            title = itemView.findViewById(R.id.title2);
        //    phnicon = itemView.findViewById(R.id.imageView6);
        //    title.setSelected(true);
            stateprogress = itemView.findViewById(R.id.stateprogress);
            stateprogress.setStateDescriptionData(descriptionData);
            stateprogress.setStateNumberTextSize(17f);
            stateprogress.setStateNumberForegroundColor(ContextCompat.getColor(ctx, android.R.color.white));
            stateprogress.setStateDescriptionSize(12f);
            stateprogress.setMaxDescriptionLine(2);
            stateprogress.setJustifyMultilineDescription(true);
            stateprogress.setDescriptionLinesSpacing(5f);
            stateprogress.setDescriptionTopSpaceIncrementer(10f);
        //    deliveryasign = itemView.findViewById(R.id.textView9);

         //   started = itemView.findViewById(R.id.textView10);

            amt = itemView.findViewById(R.id.amt);
            phnnum = itemView.findViewById(R.id.phnnum);
            items = itemView.findViewById(R.id.phonenumber);
            order = itemView.findViewById(R.id.order);
            pay = itemView.findViewById(R.id.pay);
            type = itemView.findViewById(R.id.type);
          //  schedule = itemView.findViewById(R.id.status);
            gridIcon = itemView.findViewById(R.id.imgicon);
            layout = itemView.findViewById(R.id.layout);
//            rating = itemView.findViewById(R.id.rateing);
//            km = itemView.findViewById(R.id.kmne);
//            address = itemView.findViewById(R.id.addressne);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Intent i= new Intent(v.getContext(), SubCategory.class);
////                    i.putExtra("id",datalist.get(getAdapterPosition()).getId());
////                    i.putExtra("name",datalist.get(getAdapterPosition()).getName());
////                    v.getContext().startActivity(i);
//                    // Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
//
//                }
//            });
        }
    }

    public interface OnItemClickedpend {
        void onItemClickPend(int position,String catid,String type,String num,String order_type ,String order_status,String order_asign,String order2);
    }

}