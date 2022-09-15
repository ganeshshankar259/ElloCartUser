package com.ellocartuser.AdaptersAndModel.RoomsLitsBased;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.RoomsNew.RoomDetailingListActivtiy;
import com.ellocartuser.utils.CustomItemFive;

import java.util.List;

public class RoomsListBasedAdapter extends RecyclerView.Adapter {
    private static final String TAG = "CategoryAdapter";
    private CustomItemFive customItemClick;
    Context context;
    List<RoomsListBasedModel> my_data;
    RoomsListBasedModel deliveryEarningListModel;
    public RoomsListBasedAdapter(List<RoomsListBasedModel> my_data, Context context, CustomItemFive customItemClick) {
        this.context = context;
        this.my_data = my_data;
        this.customItemClick = customItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rooms_based_list_items, parent, false);
        return new RoomsListBasedAdapter.MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,@SuppressLint("RecyclerView") final int position) {
        RoomsListBasedAdapter.MenuViewHolder holder = (RoomsListBasedAdapter.MenuViewHolder) viewHolder;
        deliveryEarningListModel = my_data.get(position);
        holder.txt_title.setText(deliveryEarningListModel.getSeller_r_store_name());

        if (deliveryEarningListModel.getSeller_r_img()==null || deliveryEarningListModel.getSeller_r_img().equalsIgnoreCase("")){
            holder.img_product.setImageResource(R.drawable.placeholderello);
        }else {

            Glide.with(holder.itemView)
                    .load(deliveryEarningListModel.getSeller_r_img())
                    .fitCenter().placeholder(R.drawable.placeholderello)
                    .into(holder.img_product);
        }


    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private class MenuViewHolder extends RecyclerView.ViewHolder {
        public CardView cardview;
        public ImageView img_product;
        public TextView txt_title;


        MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            cardview = itemView.findViewById(R.id.cardview);
            img_product = itemView.findViewById(R.id.img_product);
            txt_title = itemView.findViewById(R.id.txt_title);

            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    String Seller_r_id = my_data.get(getAdapterPosition()).getSeller_r_id();
                    Intent intent = new Intent(context, RoomDetailingListActivtiy.class);
                    intent.putExtra("Seller_r_id",Seller_r_id);
                    context.startActivity(intent);
                }
            });


        }
    }

    public void updateList(List<RoomsListBasedModel> list){
        my_data = list;
        notifyDataSetChanged();
    }

}
