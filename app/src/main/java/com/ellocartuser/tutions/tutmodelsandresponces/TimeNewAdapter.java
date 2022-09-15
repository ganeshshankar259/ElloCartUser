package com.ellocartuser.tutions.tutmodelsandresponces;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;
import com.ellocartuser.utils.CustomItemFourListener;

import java.util.List;

public class TimeNewAdapter extends RecyclerView.Adapter {
    private static final String TAG = "CategoryAdapter";
    private CustomItemFourListener customItemClick;
    Context context;
    List<TimeNewModel> my_data;
    TimeNewModel deliveryEarningListModel;
    AlphaAnimation buttonClick;
    int row_index = -1;
    View v;

    public TimeNewAdapter(List<TimeNewModel> my_data, Context context, CustomItemFourListener customItemClick) {
        this.context = context;
        this.my_data = my_data;
        this.customItemClick = customItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_time, parent, false);
        return new TimeNewAdapter.MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        TimeNewAdapter.MenuViewHolder holder = (TimeNewAdapter.MenuViewHolder) viewHolder;
        deliveryEarningListModel = my_data.get(position);

        if (deliveryEarningListModel.getStatus().equalsIgnoreCase("1")){
            holder.txt_date.setText(deliveryEarningListModel.getTime());
            holder.txt_date.setBackgroundResource(R.color.lightred);
            holder.txt_date.setTextColor(Color.parseColor("#ffffff"));
        }else {
            holder.txt_date.setText(deliveryEarningListModel.getTime());
            holder.txt_date.setBackgroundResource(R.color.green);
            holder.txt_date.setTextColor(Color.parseColor("#ffffff"));
        }

        holder.date_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                row_index=position;
                notifyDataSetChanged();
            }
        });

        if(row_index==position){
            if (deliveryEarningListModel.getStatus().equalsIgnoreCase("1")){
                holder.txt_date.setText(deliveryEarningListModel.getTime());
                holder.txt_date.setBackgroundResource(R.color.lightred);
                holder.txt_date.setTextColor(Color.parseColor("#ffffff"));
                customItemClick.onItemClick(v, deliveryEarningListModel.getId(), deliveryEarningListModel.getTime(),deliveryEarningListModel.getStatus(),"Date");
            }else {
                holder.txt_date.setBackgroundColor(Color.parseColor("#ff9e29"));
                holder.txt_date.setTextColor(Color.parseColor("#ffffff"));
                customItemClick.onItemClick(v, deliveryEarningListModel.getId(), deliveryEarningListModel.getTime(),deliveryEarningListModel.getStatus(),"Date");
            }
        }
        else
        {
            if (deliveryEarningListModel.getStatus().equalsIgnoreCase("1")){
                holder.txt_date.setText(deliveryEarningListModel.getTime());
                holder.txt_date.setBackgroundResource(R.color.lightred);
                holder.txt_date.setTextColor(Color.parseColor("#ffffff"));
            }else {
                holder.txt_date.setText(deliveryEarningListModel.getTime());
                holder.txt_date.setBackgroundResource(R.color.green);
                holder.txt_date.setTextColor(Color.parseColor("#ffffff"));
            }
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

            date_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.startAnimation(buttonClick);
                    String getId = my_data.get(getAdapterPosition()).getId();
                    String getTime = my_data.get(getAdapterPosition()).getTime();
                    String getStatus = my_data.get(getAdapterPosition()).getStatus();
                    customItemClick.onItemClick(view, getId, getTime,getStatus,"Date");
                }
            });



        }
    }

}
