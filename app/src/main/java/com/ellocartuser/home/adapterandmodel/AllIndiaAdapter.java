package com.ellocartuser.home.adapterandmodel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Categories;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllIndiaAdapter extends RecyclerView.Adapter<AllIndiaAdapter.ViewHolder> {

    List<String> titles;
    List<Categories> datalist;
    LayoutInflater inflater;
    AllIndiaAdapter.OnItemClickeGrid onItemClickeGrid;
    int layout;
    public AllIndiaAdapter(Context ctx, List<Categories> datalist, AllIndiaAdapter.OnItemClickeGrid onItemClickeGrid, int layout){
        this.datalist = datalist;
        this.layout = layout;
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

//        int val = Color.parseColor(datalist.get(position).getCategory_clr());
//        holder.title.setBackgroundColor(val);

        holder.title.setText(datalist.get(position).getCategory_name());

        Glide.with(holder.itemView)
                .load(datalist.get(position).getCategory_image())
                .fitCenter()//.placeholder(R.drawable.placeholderello)
                .into(holder.img);
//
        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickeGrid.onItemClickedtrendAllIndia(position,datalist.get(position).getCategory_id(),datalist.get(position).getCategory_name());
//                if(datalist.get(position).getStore_o().equals("1")) {
//                    onClick.onItemClicked(position, datalist.get(position).getSellerId(), datalist.get(position).getSellerStoreName(), "");
//                }else{
//                    onClick.onItemClicked(position, datalist.get(position).getSellerId(), datalist.get(position).getSellerStoreName(), "");
//                    Toast.makeText(ctx,"Currently not accepting orders",Toast.LENGTH_LONG).show();
//                }

            }

        });

//      holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
     //   private RelativeLayout llayout;
        private TextView title;
        private ImageView img;
        ConstraintLayout llayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        //    llayout = itemView.findViewById(R.id.layout);
            llayout = itemView.findViewById(R.id.llayout);
            title = itemView.findViewById(R.id.txtview);
            img = itemView.findViewById(R.id.img);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//

//                            datalist.get(getAdapterPosition()).getSellerStoreName(),
//                            "no_cat",datalist.get(getAdapterPosition()).getSellerId(),datalist.get(getAdapterPosition()).getSellerOstatus());

                }
            });
        }
    }

    public interface OnItemClickeGrid {
      //  void onItemClickAllIndia(int position,String carid,String name);
        void onItemClickedtrendAllIndia(int position,String catid,String storename);
    }
}