package com.ellocartuser.tutions.tutmodelsandresponces;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;

import java.util.List;

public class Dates_Adapter_TUT extends RecyclerView.Adapter<Dates_Adapter_TUT.ViewHolder>{

    List<TDates_M> datalist;
    LayoutInflater inflater;
    Context ctx;
    FragmentActivity aty;
    boolean animi=true;
    //declare interface
    int row_index = -1;
    private Dates_Adapter_TUT.OnItemClickedproduct onClick;

    public Dates_Adapter_TUT(Context ctx, List<TDates_M> datalist, Dates_Adapter_TUT.OnItemClickedproduct onClick) {
        this.datalist = datalist;
        this.onClick = onClick;
        this.ctx=ctx;
        this.aty= (FragmentActivity) ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }


    public void updateList(List<TDates_M> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    public void setDataList( List<TDates_M> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public Dates_Adapter_TUT.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_date,parent,false);
        return new Dates_Adapter_TUT.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Dates_Adapter_TUT.ViewHolder holder, int position) {

        holder.date.setText(datalist.get(position).getDate());
//        holder.docdisg.setText(datalist.get(position).getd_edu());
//        holder.docprice.setText(datalist.get(position).getd_price());

//        Glide.with(holder.itemView)
//                .load(datalist.get(position).getd_image())
//                .fitCenter().placeholder(R.drawable.placeholderello)
//                .into(holder.doctappintlogo);

        holder.date_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClick.onItemClickProduct(position,datalist.get(position).getDtId());
                row_index = position;
                notifyDataSetChanged();
                //notifyItemChanged(position);
            }
        });

        if(row_index==position){
            holder.date.setBackgroundColor(Color.parseColor("#ff9e29"));
            holder.date.setTextColor(Color.parseColor("#ffffff"));
            //holder.tv1.setTextColor(Color.parseColor("#ffffff"));
        }
        else {
            holder.date.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.date.setTextColor(Color.parseColor("#ff9e29"));
        }
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        LinearLayout date_layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date =  itemView.findViewById(R.id.txt_date);
            date_layout = itemView.findViewById(R.id.date_layout);

        }
    }

    public interface OnItemClickedproduct {
        void onItemClickProduct(int position,String catid);
        void onItemClickaddtocart(int position,String catid);
        void onItemClickaddtocartupdatecount(int position,String proid,String qty);
    }
}
