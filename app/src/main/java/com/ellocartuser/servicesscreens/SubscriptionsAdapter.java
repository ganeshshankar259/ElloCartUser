package com.ellocartuser.servicesscreens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.ServiveSubscription;

import java.util.List;

public class SubscriptionsAdapter extends RecyclerView.Adapter<SubscriptionsAdapter.ViewHolder> {

    List<String> titles;
    List<ServiveSubscription> datalist;
    LayoutInflater inflater;
    Context ctx;
    SubscriptionsAdapter.OnItemClick onItemClick;
    //declare interface

    public SubscriptionsAdapter(Context ctx, List<ServiveSubscription> datalist,SubscriptionsAdapter.OnItemClick onItemClick){
        this.datalist = datalist;
        this.onItemClick = onItemClick;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<ServiveSubscription> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    public void setDataList( List<ServiveSubscription > datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public SubscriptionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.subscriptionsingle,parent,false);
        return new SubscriptionsAdapter.ViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SubscriptionsAdapter.ViewHolder holder, int position) {

    //    datalist.get(position).setSubscTotal(datalist.get(position).getSubscAmount());
        holder.titlene.setText(datalist.get(position).getSubscName());
        holder.amount.setText("Amount : Rs"+datalist.get(position).getSubscAmount());
        holder.gst.setText("GST : Rs"+datalist.get(position).getSubscTax());
        holder.total.setText("Total : Rs"+datalist.get(position).getSubscTotal());

        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClicked(position,datalist.get(position));
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titlene,amount,gst,total;
        ImageView img;
        LinearLayout llayout;
        View view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titlene = itemView.findViewById(R.id.titlene);

            llayout = itemView.findViewById(R.id.llayout);
            amount = itemView.findViewById(R.id.amount);
            gst = itemView.findViewById(R.id.gst);
            total = itemView.findViewById(R.id.total);

            img = itemView.findViewById(R.id.img);


        }
    }

    public interface OnItemClick {
        void onItemClicked(int position,ServiveSubscription subc);
    }



}