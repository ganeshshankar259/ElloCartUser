package com.ellocartuser.home.adapterandmodel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.apiservices.model.Stores;

import java.util.List;

public class NearByStoreAdapter extends RecyclerView.Adapter<NearByStoreAdapter.ViewHolderNear> {

    List<String> titles;
    List<Stores> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    OnItemClickedNear onClick;
    String from;

    public void updateList(List<Stores> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    public NearByStoreAdapter(Context ctx, List<Stores> datalist,OnItemClickedNear  onclick,String from){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
        this.from=from;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolderNear onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nearbystoresinglenew,parent,false);
        return new ViewHolderNear(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNear holder, int position) {

        //FOR scratch and win static sir
      //  datalist.get(position).setSeller_offer("SCRATCH AND WIN");

//        if(datalist.get(position).getSeller_offer()!=null) {
//
//            if (datalist.get(position).getSeller_offer().length() == 0) {
//
//                holder.offerrs1.setVisibility(View.GONE);
//
//            } else {
//
//                holder.offerrs1.setVisibility(View.VISIBLE);
//
//                holder.offerrs1.setText( datalist.get(position).getSeller_offer());
//            }
//        } else {
//
//            holder.offerrs1.setVisibility(View.GONE);
//
//        }
//green for top
        if(datalist.get(position).getSeller_scratch()!=null) {

            if (datalist.get(position).getSeller_scratch().length() == 0) {
                //  holder.offerrsview.setVisibility(View.GONE);
                holder.offerrs.setVisibility(View.GONE);
                holder.ll_promo.setVisibility(View.GONE);
                // holder.cond.setVisibility(View.GONE);
            } else {
                //  holder.offerrsview.setVisibility(View.VISIBLE);
                holder.offerrs.setVisibility(View.VISIBLE);
                holder.ll_promo.setVisibility(View.VISIBLE);
                // holder.cond.setVisibility(View.VISIBLE);
                holder.offerrs.setText( datalist.get(position).getSeller_scratch());
            }
        } else {
            //  holder.offerrsview.setVisibility(View.GONE);
            holder.offerrs.setVisibility(View.GONE);
            holder.ll_promo.setVisibility(View.GONE);
            //   holder.cond.setVisibility(View.GONE);
        }


        holder.minn.setText("Min Order : ₹"+datalist.get(position).getSeller_minimum_order());
        holder.tvAmount.setText("Min Order : ₹"+datalist.get(position).getSeller_minimum_order());
        holder.tvOfferAount.setText("Min Order : ₹"+datalist.get(position).getSeller_minimum_order());
        holder.title.setText(datalist.get(position).getSellerStoreName());
        holder.tvHotelName.setText(datalist.get(position).getSellerStoreName());
//        if(datalist.get(position).getSeller_time().equals("")) {
//            holder.timeicon.setVisibility(View.INVISIBLE);
//        }

        if (datalist.get(position).getWish_status().equals("1")) {
            holder.favimg.setImageResource(R.drawable.ic_green);
            holder.imgFav.setImageResource(R.drawable.ic_green);
        } else {
            holder.favimg.setImageResource(R.drawable.ic_gray);
            holder.imgFav.setImageResource(R.drawable.ic_gray);
        }

//        } else{
//            holder.favimg.setVisibility(View.INVISIBLE);
//        }
//          holder.rating.setRating(Float.parseFloat(datalist.get(position).getSellerRating()));

        if(datalist.get(position).getSellerRating().equals("0")){
            holder.min.setText( "");
            holder.min.setVisibility(View.GONE);
            holder.timeicon.setVisibility(View.GONE);
            holder.cvRating.setVisibility(View.GONE);

        } else {
            holder.timeicon.setVisibility(View.VISIBLE);
            holder.min.setVisibility(View.VISIBLE);
            holder.cvRating.setVisibility(View.VISIBLE);
            holder.min.setText( datalist.get(position).getSellerRating());
        }

        holder.timee.setVisibility(View.VISIBLE);
        holder.timee.setText(datalist.get(position).getSeller_time());
        holder.Duration.setText(datalist.get(position).getSeller_time());


//        if(!String.valueOf(datalist.get(position).getSellerDistance()).equals("0")) {
//            holder.locicon.setVisibility(View.VISIBLE);
//
//            if (String.valueOf(datalist.get(position).getSellerDistance()).equals("1")) {
//                holder.km.setText(String.valueOf(datalist.get(position).getSellerDistance()) + "km");
//            } else {
//                holder.km.setText(String.valueOf(datalist.get(position).getSellerDistance()) + "kms");
//            }
//        }else{
//            holder.km.setText("");
//            holder.locicon.setVisibility(View.INVISIBLE);
//
//        }

        // holder.rating.setRating(Float.parseFloat(datalist.get(position).getSellerRating()));
 //       holder. rateingtxt.setText("★ "+datalist.get(position).getSellerRating());
        //    holder.rating.setRating(Float.parseFloat("1"));
        Glide.with(holder.itemView)
                .load(datalist.get(position).getSellerStoreImage())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.gridIcon);

        Glide.with(holder.itemView)
                .load(datalist.get(position).getSellerStoreImage())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.imgHotel);

        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(datalist.get(position).getStore_o().equals("1")) {
                    onClick.onItemClicked(position, datalist.get(position).getSellerId(), datalist.get(position).getSellerStoreName(), "");
                }else{
                    onClick.onItemClicked(position, datalist.get(position).getSellerId(), datalist.get(position).getSellerStoreName(), "");
                    Toast.makeText(ctx,"Currently not accepting orders",Toast.LENGTH_LONG).show();
                }
                //          Toast.makeText(ctx, "Clicked -> " + position, Toast.LENGTH_SHORT).show();
            }
        });

        //if(!from.equals("wishlist")) {
        holder.favimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClicked(position,datalist.get(position).getSellerId(),datalist.get(position).getSellerStoreName(),"fav");

            }
        });
        //  }

        if (datalist.get(position).getStore_o().equals("1")) {
            holder.minn.setVisibility(View.VISIBLE);
            holder.minn.setTextColor( Color.parseColor("#000000"));
            holder.minn.setTextSize(12f);

            holder.notaccepting.setVisibility(View.GONE);
            holder.ll_offline.setVisibility(View.GONE);


        } else {

//            holder.llayout.getBackground().setColorFilter(ContextCompat.getColor(ctx, R.color.disabled), android.graphics.PorterDuff.Mode.MULTIPLY);
//            holder.gridIcon.getDrawable().setColorFilter(ContextCompat.getColor(ctx, R.color.gray), PorterDuff.Mode.MULTIPLY );

//            holder.minn.setText("Currently Not Accepting Orders");
//            holder.minn.setTextSize(13f);
//            holder.minn.setTextColor( Color.parseColor("#ed1c24"));
//
//            holder.minn.setVisibility(View.VISIBLE);
            holder.notaccepting.setVisibility(View.VISIBLE);
            holder.ll_offline.setVisibility(View.VISIBLE);
        }



        // free delivery
        if(datalist.get(position).getSeller_dlv_status().equals("1")){
            holder.freedel.setVisibility(View.VISIBLE);
            holder.imgFreeDelivery.setVisibility(View.VISIBLE);
        }else{
            holder.freedel.setVisibility(View.GONE);
            holder.imgFreeDelivery.setVisibility(View.GONE);
        }

        //promo wallet
        if(datalist.get(position).getSeller_promo_status().equals("1") && !datalist.get(position).getSeller_promo_eligible().equals("0")){
            holder.prowall_txt.setVisibility(View.VISIBLE);
            holder.prowall_txt.setText("₹"+datalist.get(position).getSeller_promo_eligible());
        }else{
            holder.prowall_txt.setVisibility(View.GONE);
        }


//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }


    public class ViewHolderNear extends RecyclerView.ViewHolder{
        TextView title,km,min,minn,offerrs,notaccepting,timee,offerrs1,prowall_txt,tvOfferAount;
        ImageView gridIcon,favimg,timeicon,timeeicon,freedel;//locicon;
        ImageView imgFreeDelivery,imgFav,imgHotel;
        TextView Duration,tvAmount,tvHotelName;
        LinearLayout ll_offline;
        RelativeLayout ll_promo;
        CardView cvRating;

        ConstraintLayout llayout;
        RatingBar rating;
        // View offerrsview;
        public ViewHolderNear(@NonNull View itemView) {
            super(itemView);
          //  rate = itemView.findViewById(R.id.rate);
          // locicon = itemView.findViewById(R.id.imageView12);
            prowall_txt = itemView.findViewById(R.id.textView10);
            freedel = itemView.findViewById(R.id.freedel);
            offerrs1 = itemView.findViewById(R.id.offerrs1);
            timee = itemView.findViewById(R.id.timee);
            timeeicon = itemView.findViewById(R.id.imageView14);
            timeicon = itemView.findViewById(R.id.imageView13);
            title = itemView.findViewById(R.id.titlene);
            notaccepting = itemView.findViewById(R.id.notaccepting);
            minn = itemView.findViewById(R.id.minn);
            offerrs = itemView.findViewById(R.id.offerrs);
            //     offerrsview = itemView.findViewById(R.id.offerrsview);
            min = itemView.findViewById(R.id.min);
            gridIcon = itemView.findViewById(R.id.gridIconne);
            // rating = itemView.findViewById(R.id.rateing);
            km = itemView.findViewById(R.id.kmne);
          //  address = itemView.findViewById(R.id.addressne);
            llayout = itemView.findViewById(R.id.llayout);
       //     rateingtxt = itemView.findViewById(R.id.rateingtxt);
            favimg = itemView.findViewById(R.id.imageView7);
            tvOfferAount = itemView.findViewById(R.id.tvOfferAount);

             imgFreeDelivery=itemView.findViewById(R.id.imgFreeDelivery);
             imgFav=itemView.findViewById(R.id.imgFav);
             imgHotel=itemView.findViewById(R.id.imgHotel);
             Duration=itemView.findViewById(R.id.Duration);
             tvAmount=itemView.findViewById(R.id.tvAmount);
             tvHotelName=itemView.findViewById(R.id.tvHotelName);
             ll_offline=itemView.findViewById(R.id.ll_offline);
            ll_promo=itemView.findViewById(R.id.ll_promo);
            cvRating=itemView.findViewById(R.id.cvRating);


            // cond = itemView.findViewById(R.id.cond);
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

    public interface OnItemClickedNear {
        void onItemClicked(int position,String mParam1,String storename,String type);
    }

}