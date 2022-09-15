package com.ellocartuser.home.adapterandmodel;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.google.android.gms.common.data.DataHolder;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    List<String> titles;
    List<Products> datalist;
    LayoutInflater inflater;
    Context ctx;
    FragmentActivity aty;
    boolean animi=true;
    //declare interface
    private ProductsAdapter.OnItemClickedproduct onClick;

    public ProductsAdapter(Context ctx, List<Products> datalist, ProductsAdapter.OnItemClickedproduct onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
        this.aty= (FragmentActivity) ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<Products> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    public void setDataList( List<Products> datalist){
        this.datalist = datalist;
    }

    public void updateqty( int position,Integer qty){
        datalist.get(position).setProduct_qty(qty);
        animi=false;
        notifyItemChanged(position);


    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_single,parent,false);
        return new ProductsAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {

      //  ->status for is the product is already in cart ,and qty is needed if already in cart -> is it

        holder.title.setText(datalist.get(position).getProductName());
        holder.amt.setText("₹"+datalist.get(position).getProductMrp());   //change from original

        holder.percentage.setText("Offer "+datalist.get(position).getProductDiscount()+"%");

        if(datalist.get(position).getProductSale().equals(datalist.get(position).getProductMrp())){
//            holder.view.setVisibility(View.GONE);
//            holder.percentage.setVisibility(View.GONE);
            holder.save.setVisibility(View.INVISIBLE);
        }else{
            holder.save.setVisibility(View.VISIBLE);
//            holder.view.setVisibility(View.VISIBLE);
//            holder.percentage.setVisibility(View.VISIBLE);

           holder.strickamt.setText("");
//            holder.strickamt.setText("₹"+datalist.get(position).getProductMrp());
//            holder.strickamt.setPaintFlags(holder.strickamt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.save.setText("");
      //      holder.save.setText("Save ₹"+datalist.get(position).getProduct_save());
        }

        if(datalist.get(position).getSeller_day().length()!=0){
            holder.schedule.setVisibility(View.VISIBLE);
            holder.schedule.setText("Pre Order : "+datalist.get(position).getSeller_day());
        }else{
            holder.schedule.setVisibility(View.GONE);
        }

        holder.rateing.setRating(Float.valueOf(datalist.get(position).getProduct_rating()));

        Glide.with(holder.itemView)
                .load(datalist.get(position).getProductImages())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.img);

        //animation
//if(animi) {
//    aty.runOnUiThread(new Runnable() {
//        @Override
//        public void run() {
//            Animation rightSwipe = AnimationUtils.loadAnimation(ctx, R.anim.sliderighttoleft);
//            holder.llayout.startAnimation(rightSwipe);
//        }
//    });
//}
//        animi=true;

        //animation

        holder.viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClick.onItemClickProduct(position,datalist.get(position).getProductId());

            }
        });

        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(datalist.get(position).getProductVar().equals("1")) {

                    holder.qtylayout.setVisibility(View.GONE);
                    holder.addtocart.setVisibility(View.VISIBLE);
                    onClick.onItemClickaddtocart(position, datalist.get(position).getProductId());

                }else{
                    //add directly

                    onClick.onItemClickaddtocartupdatecount(position, datalist.get(position).getProductId(), String.valueOf(1));

                }

            }
        });

        if(datalist.get(position).getProduct_qty()==0 || datalist.get(position).getProductVar().equals("1")){  //qty and cusmize condition
            holder.qtylayout.setVisibility(View.GONE);
            holder.addtocart.setVisibility(View.VISIBLE);
        }else{
            holder.qtylayout.setVisibility(View.VISIBLE);
            holder.addtocart.setVisibility(View.GONE);
            holder.count.setText(String.valueOf(datalist.get(position).getProduct_qty()));
        }

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int num =  datalist.get(position).getProduct_qty()+1;
                onClick.onItemClickaddtocartupdatecount(position, datalist.get(position).getProductId(), String.valueOf(num));

            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int num =  datalist.get(position).getProduct_qty()-1;
                onClick.onItemClickaddtocartupdatecount(position, datalist.get(position).getProductId(), String.valueOf(num));

            }
        });

      if(datalist.get(position).getProductVar().equals("1")){
          holder.custumize.setVisibility(View.VISIBLE);
      }else{
          holder.custumize.setVisibility(View.INVISIBLE);
      }

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView schedule,count,percentage,title,amt,strickamt,save,custumize;
        ImageView img;
        RelativeLayout llayout;
        ConstraintLayout lay;
        View view;
        RatingBar rateing;
        TextView viewdetails;
        AppCompatButton addtocart;
        LinearLayout qtylayout;
        Button minus,plus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            schedule = itemView.findViewById(R.id.schedule);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            count = itemView.findViewById(R.id.count);
            qtylayout = itemView.findViewById(R.id.qtylayout);
            lay = itemView.findViewById(R.id.lay);
            custumize = itemView.findViewById(R.id.custumize);
            viewdetails = itemView.findViewById(R.id.viewdetails);
            addtocart = itemView.findViewById(R.id.addtocart);
            title = itemView.findViewById(R.id.title);
            save = itemView.findViewById(R.id.save);
//            title.setSelected(true);
            llayout = itemView.findViewById(R.id.llayout);
            percentage = itemView.findViewById(R.id.percentage);
            amt = itemView.findViewById(R.id.amt);
            view = itemView.findViewById(R.id.view);
            strickamt = itemView.findViewById(R.id.strickamt);
            rateing = itemView.findViewById(R.id.rateing);

            img = itemView.findViewById(R.id.img);
//            rating = itemView.findViewById(R.id.rateing);
//            km = itemView.findViewById(R.id.kmne);
//            address = itemView.findViewById(R.id.addressne);

//            llayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    onClick.onItemClickProduct(getAdapterPosition(),datalist.get(getAdapterPosition()).getProductId());
//
//                }
//            });
        }
    }

    public interface OnItemClickedproduct {
        void onItemClickProduct(int position,String catid);
        void onItemClickaddtocart(int position,String catid);
        void onItemClickaddtocartupdatecount(int position,String proid,String qty);
    }

}