package com.ellocartuser.AdaptersAndModel.RoomCart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.ellocartuser.AdaptersAndModel.RoomsLitsBased.RoomsListBasedAdapter;
import com.ellocartuser.AdaptersAndModel.RoomsLitsBased.RoomsListBasedModel;
import com.ellocartuser.R;
import com.ellocartuser.RoomsNew.RoomDetailingListActivtiy;
import com.ellocartuser.utils.CustomItemFive;

import java.util.List;

public class RoomCardAdapter extends RecyclerView.Adapter {
    private static final String TAG = "CategoryAdapter";
    private CustomItemFive customItemClick;
    Context context;
    List<RoomCartModel> my_data;
    RoomCartModel deliveryEarningListModel;
    public RoomCardAdapter(List<RoomCartModel> my_data, Context context, CustomItemFive customItemClick) {
        this.context = context;
        this.my_data = my_data;
        this.customItemClick = customItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_cart_list_items, parent, false);
        return new RoomCardAdapter.MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,@SuppressLint("RecyclerView") final int position) {
        RoomCardAdapter.MenuViewHolder holder = (RoomCardAdapter.MenuViewHolder) viewHolder;
        deliveryEarningListModel = my_data.get(position);
        holder.txt_title.setText(deliveryEarningListModel.getRchild_title());
        holder.txt_price.setText("₹"+deliveryEarningListModel.getBcart_price()+" - "+deliveryEarningListModel.getBcart_date());
        holder.txt_person.setText("Persons: "+deliveryEarningListModel.getBcart_per());



        if (deliveryEarningListModel.getRchild_image1()==null || deliveryEarningListModel.getRchild_image1().equalsIgnoreCase("")){
            holder.img.setImageResource(R.drawable.placeholderello);
        }else {
            Glide.with(holder.itemView)
                    .load(deliveryEarningListModel.getRchild_image1())
                    .fitCenter().placeholder(R.drawable.placeholderello)
                    .into(holder.img);
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

        public ImageView img;
        public ImageView delete_img;
        public TextView txt_title;
        public TextView txt_price;
        public TextView txt_person;


        MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            delete_img = itemView.findViewById(R.id.delete_img);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_price = itemView.findViewById(R.id.txt_price);
            txt_person = itemView.findViewById(R.id.txt_person);

            delete_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    String Bcart_id = my_data.get(getAdapterPosition()).getBcart_id();
                    customItemClick.onItemClick(v, Bcart_id, "delete","","","");
                }
            });


        }
    }

    public void updateList(List<RoomCartModel> list){
        my_data = list;
        notifyDataSetChanged();
    }

}
