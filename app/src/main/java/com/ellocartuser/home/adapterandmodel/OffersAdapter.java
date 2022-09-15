package com.ellocartuser.home.adapterandmodel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Stores;
import com.ellocartuser.apiservices.model.TrendStore;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {

    List<String> titles;
    List<Stores> datalist;
    LayoutInflater inflater;
    OffersAdapter.OnItemClickeGrid onItemClickeGrid;
    int layout;
    String clr;

    public OffersAdapter(Context ctx, List<Stores> datalist,String clr,OffersAdapter.OnItemClickeGrid onItemClickeGrid,int Layout){
       this.datalist = datalist;
       this.clr = clr;
       this.layout = Layout;
//        this.layout = layout;
       this.onItemClickeGrid = onItemClickeGrid;
      //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(datalist.get(position).getSellerStoreName());

        holder.offertxt.setText(datalist.get(position).getCoup_stitle());

        holder.offertxt.setBackgroundColor(Color.parseColor(clr));
        holder.title.setBackgroundColor(Color.parseColor(clr));


        Glide.with(holder.itemView)
                .load(datalist.get(position).getSellerStoreImage())
                .fitCenter()//.placeholder(R.drawable.placeholderello)
                .into(holder.img);

        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickeGrid.onItemClickedtrend(position, datalist.get(position).getSellerId(),
                        datalist.get(position).getSellerStoreName(),
                        "no_cat",datalist.get(position).getSellerId(),datalist.get(position).getStore_o());
            }
        });


    }

    public class ViewHolder extends RecyclerView.ViewHolder{
     //   private RelativeLayout llayout;
        private TextView title,offertxt;
        private ImageView img;
        RelativeLayout llayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        //    llayout = itemView.findViewById(R.id.layout);
            llayout = itemView.findViewById(R.id.llayout);
            offertxt = itemView.findViewById(R.id.txtviewoffer);
            title = itemView.findViewById(R.id.txtview);
            img = itemView.findViewById(R.id.img);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//               //     onItemClickeGrid.onItemClick(getAdapterPosition(),datalist.get(getAdapterPosition()).getCategory_id(),datalist.get(getAdapterPosition()).getCategory_name());
//                }
//            });
        }
    }

    public interface OnItemClickeGrid {
        void onItemClick(int position,String carid,String name);
        void onItemClickedtrend(int position,String mParam1,String storename,String catid,String sellerid,String storestatus);
    }
}