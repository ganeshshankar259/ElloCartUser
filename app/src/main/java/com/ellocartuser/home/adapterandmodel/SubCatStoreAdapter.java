package com.ellocartuser.home.adapterandmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;

import java.util.List;

public class SubCatStoreAdapter extends RecyclerView.Adapter<SubCatStoreAdapter.ViewHolder> {

    List<String> titles;
    List<SubcategoryStoreAll> datalist;
    LayoutInflater inflater;
    //declare interface
    private SubCatStoreAdapter.OnItemClickedSubcat onClick;

    public SubCatStoreAdapter(Context ctx, List<SubcategoryStoreAll> datalist, SubCatStoreAdapter.OnItemClickedSubcat onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public SubCatStoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout_subcat,parent,false);
        return new SubCatStoreAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SubCatStoreAdapter.ViewHolder holder, int position) {

        holder.title.setText(datalist.get(position).getSubcategoryName());
//      holder.rating.setRating(Float.parseFloat(datalist.get(position).getSellerRating()));
//        holder.address.setText(datalist.get(position).getSellerStoreAddress()+", "+datalist.get(position).getSellerCity());
//        if(String.valueOf(datalist.get(position).getSellerDistance()).equals("1")) {
//            holder.km.setText(String.valueOf(datalist.get(position).getSellerDistance()) + "km");
//        }else{
//            holder.km.setText(String.valueOf(datalist.get(position).getSellerDistance()) + "kms");
//        }
//        holder.rating.setRating(Float.parseFloat(datalist.get(position).getSellerRating()));
        Glide.with(holder.itemView)
                .load(datalist.get(position).getSubcategoryImage())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.subcatimg);

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,km,address;
        ImageView subcatimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
//            title.setSelected(true);
            subcatimg = itemView.findViewById(R.id.imageView10);
//            rating = itemView.findViewById(R.id.rateing);
//            km = itemView.findViewById(R.id.kmne);
//            address = itemView.findViewById(R.id.addressne);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent i= new Intent(v.getContext(), SubCategory.class);
//                    i.putExtra("id",datalist.get(getAdapterPosition()).getId());
//                    i.putExtra("name",datalist.get(getAdapterPosition()).getName());
//                    v.getContext().startActivity(i);
                    onClick.onItemClickSubCat(getAdapterPosition(),datalist.get(getAdapterPosition()).getSubcategoryId(),datalist.get(getAdapterPosition()).getSubcategoryName());
                  //  Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public interface OnItemClickedSubcat {
        void onItemClickSubCat(int position,String subcatid,String name);
    }

}