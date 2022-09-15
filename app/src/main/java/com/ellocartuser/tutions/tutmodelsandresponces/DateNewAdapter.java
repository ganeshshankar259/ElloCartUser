package com.ellocartuser.tutions.tutmodelsandresponces;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.utils.CustomItemFourListener;

import java.util.List;

public class DateNewAdapter extends RecyclerView.Adapter {
    private static final String TAG = "CategoryAdapter";
    private CustomItemFourListener customItemClick;
    Context context;
    List<DateNewModel> my_data;
    DateNewModel deliveryEarningListModel;
    AlphaAnimation buttonClick;
    int row_index = -1;

    View v;

    public DateNewAdapter(List<DateNewModel> my_data, Context context, CustomItemFourListener customItemClick) {
        this.context = context;
        this.my_data = my_data;
        this.customItemClick = customItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_date, parent, false);
        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        MenuViewHolder holder = (MenuViewHolder) viewHolder;
        deliveryEarningListModel = my_data.get(position);
        holder.txt_date.setText(deliveryEarningListModel.getDate());


        holder.date_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                row_index=position;
                notifyDataSetChanged();
            }
        });

        if(row_index==position){
            holder.txt_date.setBackgroundColor(Color.parseColor("#ff9e29"));
            holder.txt_date.setTextColor(Color.parseColor("#ffffff"));
            customItemClick.onItemClick(v, deliveryEarningListModel.getDate(), "","","Date");
        }
        else
        {
            holder.txt_date.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.txt_date.setTextColor(Color.parseColor("#000000"));
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
        TextView txt_date;
        LinearLayout date_layout;


        MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            //click animation
            buttonClick = new AlphaAnimation(1F, 0.3F);

            txt_date = itemView.findViewById(R.id.txt_date);
            date_layout = itemView.findViewById(R.id.date_layout);

//            date_layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    v.startAnimation(buttonClick);
//                    String getDate = my_data.get(getAdapterPosition()).getDate();
//                    customItemClick.onItemClick(v, getDate, "","","Date");
//                }
//            });


        }
    }

}
