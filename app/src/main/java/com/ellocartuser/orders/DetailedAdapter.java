package com.ellocartuser.orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.HomeOrders;
import com.ellocartuser.home.adapterandmodel.CarStoreAdapter;

import java.util.List;

public class DetailedAdapter extends RecyclerView.Adapter<DetailedAdapter.ViewHolder> {

    List<HomeOrders> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    private CarStoreAdapter.OnItemClickedcat onClick;

    public DetailedAdapter(Context ctx, List<HomeOrders> datalist){
        this.datalist = datalist;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setDataList( List<HomeOrders> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public DetailedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.order_detail_single,parent,false);
        return new DetailedAdapter.ViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedAdapter.ViewHolder holder, int position) {
        System.out.println("custom raju  p"+position);

        holder.p1.setText(datalist.get(position).getP1());
        holder.p2.setText(datalist.get(position).getP2());

        if(datalist.get(position).getP2().contains("-") && datalist.get(position).getP1().toLowerCase().contains("discount")){
            holder.ll.setBackground(ContextCompat.getDrawable(ctx, R.drawable.bordergreen));


        }else if(position!=0){

            holder.ll.setBackground(ContextCompat.getDrawable(ctx, R.drawable.bordertop));

        }else{

            holder.ll.setBackground(null);

        }


        
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView p1,p2;
        LinearLayout ll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            p1 = itemView.findViewById(R.id.p1);
            p2 = itemView.findViewById(R.id.p2);
//
        }
    }


}
