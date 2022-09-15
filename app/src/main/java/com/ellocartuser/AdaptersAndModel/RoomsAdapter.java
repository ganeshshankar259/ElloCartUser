package com.ellocartuser.AdaptersAndModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.utils.CustomItemFive;

import java.util.ArrayList;

public class RoomsAdapter extends RecyclerView.Adapter {
    private static final String TAG = "HomeAdapter";
    private CustomItemFive customItemClick;
    public Context context;
    public ArrayList<RoomsModel> my_data;
    RoomsModel deliveryEarningListModel;
    public RoomsAdapter(ArrayList<RoomsModel> my_data, Context context, CustomItemFive customItemClick) {
        this.context = context;
        this.my_data = my_data;
        this.customItemClick = customItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rooms_items_list, parent, false);

        return new RoomsAdapter.MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,@SuppressLint("RecyclerView") final int position) {
        RoomsAdapter.MenuViewHolder holder = (RoomsAdapter.MenuViewHolder) viewHolder;
        deliveryEarningListModel = my_data.get(position);


        holder.txtview.setText(deliveryEarningListModel.getRcat_name());

        if (deliveryEarningListModel.getRcat_image1().equalsIgnoreCase("")||deliveryEarningListModel.getRcat_image1()==null){
            holder.img.setImageResource(R.drawable.placeholderello);
        }else {
            Glide.with(holder.itemView)
                    .load(deliveryEarningListModel.getRcat_image1())
                    .fitCenter()//.placeholder(R.drawable.placeholderello)
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

        public TextView txtview;
        public ImageView img;


        MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            txtview = itemView.findViewById(R.id.txtview);
            img = itemView.findViewById(R.id.img);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Rcat_id = my_data.get(getAdapterPosition()).getRcat_id();
                    String Rcat_name = my_data.get(getAdapterPosition()).getRcat_name();
                    String Rcat_image1 = my_data.get(getAdapterPosition()).getRcat_image1();
                    customItemClick.onItemClick(v, Rcat_id, Rcat_name,Rcat_image1,"","");
                }
            });

        }
    }

}


