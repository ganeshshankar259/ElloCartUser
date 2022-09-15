package com.ellocartuser.home.notification;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Notify;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.orders.OrdersMainClass;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    List<String> titles;
    List<Notify> datalist;
    LayoutInflater inflater;
    Context ctx;
    OnItemClickedNoti onclick;
    //declare interface

    public NotificationAdapter(Context ctx, List<Notify> datalist,OnItemClickedNoti onclick){
        this.datalist = datalist;
        this.onclick = onclick;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<Notify> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    public void setDataList( List<Notify > datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.notificationsingle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(datalist.get(position).getNotifyTitle());
        holder.detailtext.setText(datalist.get(position).getNotifyMessage());

        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

      onclick.onItemClickedNoti(position,datalist.get(position).getNotifyP1(),datalist.get(position).getNotifyP2(),datalist.get(position).getNotifyP3());



            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView qty,title,store,detailtext;

        ImageView img;
        LinearLayout llayout;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            detailtext = itemView.findViewById(R.id.detailtext);
            llayout = itemView.findViewById(R.id.llayout);


        }
    }


    public interface OnItemClickedNoti {
        void onItemClickedNoti(int position,String np1,String np2,String np3);
    }

}