package com.ellocartuser.home.adapterandmodel;

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
import com.ellocartuser.apiservices.model.Categories;

import java.util.List;

public class GridAdapter2 extends RecyclerView.Adapter<GridAdapter2.ViewHolder> {

    List<String> titles;
    List<B2category> datalist;
    LayoutInflater inflater;
    OnItemClickeGrid onItemClickeGrid;

    public GridAdapter2(Context ctx, List<B2category> datalist, OnItemClickeGrid onItemClickeGrid){
        this.datalist = datalist;
        this.onItemClickeGrid = onItemClickeGrid;
      //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.servicersingle,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(datalist.get(position).getB2categoryName());

        Glide.with(holder.itemView)
                .load(datalist.get(position).getB2categoryImage())
                .fitCenter()
                .into(holder.gridIcon);

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtview);
            title.setSelected(true);
            gridIcon = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickeGrid.onItemClick(getAdapterPosition(),datalist.get(getAdapterPosition()).getB2categoryId(),datalist.get(getAdapterPosition()).getB2categoryName());
//                    Intent i= new Intent(v.getContext(), NearByStore.class);
//                    v.getContext().startActivity(i);
//                    i.putExtra("id",datalist.get(getAdapterPosition()).getId());
//                    i.putExtra("name",datalist.get(getAdapterPosition()).getName());

                //    Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public interface OnItemClickeGrid {
        void onItemClick(int position,String carid,String name);
    }
}