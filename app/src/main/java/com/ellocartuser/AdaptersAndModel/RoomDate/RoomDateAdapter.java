package com.ellocartuser.AdaptersAndModel.RoomDate;

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
import com.ellocartuser.tutions.tutmodelsandresponces.DateNewAdapter;
import com.ellocartuser.tutions.tutmodelsandresponces.DateNewModel;
import com.ellocartuser.utils.CustomItemFourListener;

import java.util.List;

public class RoomDateAdapter extends RecyclerView.Adapter {
    private static final String TAG = "CategoryAdapter";
    private CustomItemFourListener customItemClick;
    Context context;
    List<RoomsdateModel> my_data;
    RoomsdateModel deliveryEarningListModel;
    AlphaAnimation buttonClick;
    int row_index = -1;

    View v;

    public RoomDateAdapter(List<RoomsdateModel> my_data, Context context, CustomItemFourListener customItemClick) {
        this.context = context;
        this.my_data = my_data;
        this.customItemClick = customItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_date, parent, false);
        return new RoomDateAdapter.MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        RoomDateAdapter.MenuViewHolder holder = (RoomDateAdapter.MenuViewHolder) viewHolder;
        deliveryEarningListModel = my_data.get(position);
        holder.txt_date.setText(deliveryEarningListModel.getSell_assign_date());


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
            customItemClick.onItemClick(v, deliveryEarningListModel.getSell_assign_date(), "","","Date");
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

            date_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(buttonClick);
                    String getDate = my_data.get(getAdapterPosition()).getSell_assign_date();
                    customItemClick.onItemClick(v, getDate, "","","Date");
                }
            });


        }
    }

}
