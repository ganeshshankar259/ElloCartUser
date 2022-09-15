package com.ellocartuser.AdaptersAndModel.MyRooms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.ellocartuser.AdaptersAndModel.RoomCart.RoomCardAdapter;
import com.ellocartuser.AdaptersAndModel.RoomCart.RoomCartModel;
import com.ellocartuser.R;
import com.ellocartuser.utils.CustomItemFive;
import java.util.List;

public class MyRoomCardAdapter extends RecyclerView.Adapter {
    private static final String TAG = "CategoryAdapter";
    private CustomItemFive customItemClick;
    Context context;
    List<MyRoomCartModel> my_data;
    MyRoomCartModel deliveryEarningListModel;
    public MyRoomCardAdapter(List<MyRoomCartModel> my_data, Context context, CustomItemFive customItemClick) {
        this.context = context;
        this.my_data = my_data;
        this.customItemClick = customItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_room_list_items, parent, false);
        return new MyRoomCardAdapter.MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,@SuppressLint("RecyclerView") final int position) {
        MyRoomCardAdapter.MenuViewHolder holder = (MyRoomCardAdapter.MenuViewHolder) viewHolder;
        deliveryEarningListModel = my_data.get(position);
        holder.txt_title.setText(deliveryEarningListModel.getSeller_store_name());
        holder.txt_date.setText("Appointment: "+deliveryEarningListModel.getRod_date());

        if (deliveryEarningListModel.getSeller_img()==null || deliveryEarningListModel.getSeller_img().equalsIgnoreCase("")){
            holder.img_product.setImageResource(R.drawable.placeholderello);
        }else {
            Glide.with(holder.itemView)
                    .load(deliveryEarningListModel.getSeller_img())
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

        public CardView card_view_continue;
        public ImageView img_product;
        public TextView txt_title;
        public TextView txt_date;


        MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            card_view_continue = itemView.findViewById(R.id.card_view_continue);
            img_product = itemView.findViewById(R.id.img_product);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_date = itemView.findViewById(R.id.txt_date);

            card_view_continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Seller_lat = my_data.get(getAdapterPosition()).getSeller_lat();
                    String Seller_long = my_data.get(getAdapterPosition()).getSeller_long();
                    String Seller_store_name = my_data.get(getAdapterPosition()).getSeller_store_name();
                    customItemClick.onItemClick(v, Seller_lat, Seller_long,Seller_store_name,"","");
                }
            });

        }
    }

    public void updateList(List<MyRoomCartModel> list){
        my_data = list;
        notifyDataSetChanged();
    }

}
