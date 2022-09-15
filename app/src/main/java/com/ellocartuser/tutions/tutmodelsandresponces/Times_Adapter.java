package com.ellocartuser.tutions.tutmodelsandresponces;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;

import java.util.ArrayList;
import java.util.List;

public class Times_Adapter extends RecyclerView.Adapter<Times_Adapter.ViewHolder>{

    List<TTime_M> datalist;
    LayoutInflater inflater;
    Context ctx;
    List<View>itemViewList = new ArrayList<>();
    FragmentActivity aty;
    boolean animi=true;
    int row_index = -1;
    int position = -1;
    //declare interface
    private Times_Adapter.OnItemClickedtimes onClick1;

    public Times_Adapter(Context ctx,List<TTime_M> datalist, Times_Adapter.OnItemClickedtimes onClick1) {
        this.datalist = datalist;
        this.onClick1 = onClick1;
        this.ctx=ctx;
        this.aty= (FragmentActivity) ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<TTime_M> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    public void setDataList( List<TTime_M> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public Times_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       final View view = inflater.inflate(R.layout.single_time,parent,false);
        return new Times_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Times_Adapter.ViewHolder holder,  int position) {

        //position = position1;

        holder.time.setText(datalist.get(position).getTime());

       // holder.time.setTag(position);
        holder.time_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int position = holder.getAdapterPosition();
                //position = -1;

            onClick1.onItemClickTime(position,datalist.get(position).getDate(),datalist.get(position).getTime());
                //int position = (Integer) v.getTag();
            // holder.time.setBackgroundColor(Color.parseColor("#ff9e29"));
                row_index=position;
//
                notifyDataSetChanged();
               //notifyItemChanged(position);



            }
        });

        if(row_index==position){
            holder.time.setBackgroundColor(Color.parseColor("#ff9e29"));
            holder.time.setTextColor(Color.parseColor("#ffffff"));
            //holder.tv1.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            holder.time.setBackgroundColor(Color.parseColor("#ffffff"));

            holder.time.setTextColor(Color.parseColor("#ff9e29"));

            //holder.tv1.setTextColor(Color.parseColor("#000000"));
        }
       // holder.time.setBackgroundColor(Color.parseColor("#ffffff"));
//        if(row_index==position){
//            holder.time.setBackgroundColor(Color.parseColor("#ff9e29"));
//            holder.time.setTextColor(Color.parseColor("#ffffff"));
//            //holder.tv1.setTextColor(Color.parseColor("#ffffff"));
//        }
//        else
//        {
//            holder.time.setBackgroundColor(Color.parseColor("#ffffff"));
//
//            holder.time.setTextColor(Color.parseColor("#ff9e29"));
//
//            //holder.tv1.setTextColor(Color.parseColor("#000000"));
//        }


    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView time;
        LinearLayout time_layout;


        public ViewHolder(@NonNull View itemView ) {
            super(itemView);

            time =  itemView.findViewById(R.id.txt_date);
            time_layout = itemView.findViewById(R.id.date_layout);


        }
    }

    public interface OnItemClickedtimes {

        void onItemClickTime(int position,String Date,String Time);
        void onItemClickaddtocart(int position,String catid);
        void onItemClickaddtocartupdatecount(int position,String proid,String qty);
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
