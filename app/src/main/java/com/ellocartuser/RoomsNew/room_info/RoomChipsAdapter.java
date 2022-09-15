package com.ellocartuser.RoomsNew.room_info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.TrendStore;
import com.ellocartuser.ellorooms_new.models.Amenities;

import java.util.List;

public class RoomChipsAdapter extends RecyclerView.Adapter<RoomChipsAdapter.ViewHolder> {

    List<String> titles;
    List<Amenities> datalist;
    LayoutInflater inflater;
    RoomChipsAdapter.OnItemClickeGrid onItemClickeGrid;
int layout;
    public RoomChipsAdapter(Context ctx, List<Amenities> datalist,  int layout){
        this.datalist = datalist;
        this.layout = layout;
      //  this.onItemClickeGrid = onItemClickeGrid;

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
        holder.title.setText(datalist.get(position).getaTitle());

//        Glide.with(holder.itemView)
//                .load(datalist.get(position).getSellerStoreImage())
//                .fitCenter()//.placeholder(R.drawable.placeholderello)
//                .into(holder.img);

//
//        holder.llayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                if(datalist.get(position).getStore_o().equals("1")) {
////                    onClick.onItemClicked(position, datalist.get(position).getSellerId(), datalist.get(position).getSellerStoreName(), "");
////                }else{
////                    onClick.onItemClicked(position, datalist.get(position).getSellerId(), datalist.get(position).getSellerStoreName(), "");
////                    Toast.makeText(ctx,"Currently not accepting orders",Toast.LENGTH_LONG).show();
////                }
//
//            }
//
//        });

//      holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
     //   private RelativeLayout llayout;
        private TextView title;
        private ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        //    llayout = itemView.findViewById(R.id.layout);
            title = itemView.findViewById(R.id.title);
           // img = itemView.findViewById(R.id.img);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    onItemClickeGrid.onItemClickedtrend(getAdapterPosition(), datalist.get(getAdapterPosition()).getSellerId(),
//                            datalist.get(getAdapterPosition()).getSellerStoreName(),
//                            "no_cat",datalist.get(getAdapterPosition()).getSellerId(),datalist.get(getAdapterPosition()).getSellerOstatus());
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