package com.ellocartuser.servicesscreens.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.home.adapterandmodel.SubcategoryStoreAll;

import java.util.List;

public class SubCatServiceAdapter extends RecyclerView.Adapter<SubCatServiceAdapter.ViewHolder> {

    List<String> titles;
    List<SubcategoryStoreAll> datalist;
    LayoutInflater inflater;
    // declare interface
    Context ctx;
    private SubCatServiceAdapter.OnItemClickedSubcat onClick;

    public SubCatServiceAdapter(Context ctx, List<SubcategoryStoreAll> datalist, SubCatServiceAdapter.OnItemClickedSubcat onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx = ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }


    public void setDataList( List<SubcategoryStoreAll> datalist){
        this.datalist = datalist;
    }


    @NonNull
    @Override
    public SubCatServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout_subcat_service,parent,false);
        return new SubCatServiceAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SubCatServiceAdapter.ViewHolder holder, int position) {

        holder.title.setText(datalist.get(position).getScat_name());


        if(datalist.get(position).getSelected().equals("1")){
            holder.layout.setBackgroundColor(ContextCompat.getColor(ctx, R.color.yellow));
        }else{

            holder.layout.setBackgroundColor(ContextCompat.getColor(ctx, R.color.bggray));

        }

        holder.subcatimg.setVisibility(View.GONE);

//        Glide.with(holder.itemView)
//                .load(datalist.get(position).getScat_image())
//                .fitCenter()
//                .into(holder.subcatimg);

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,km,address;
        ConstraintLayout layout;
        ImageView subcatimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
//            title.setSelected(true);
            subcatimg = itemView.findViewById(R.id.imageView10);
            layout = itemView.findViewById(R.id.layout);
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
                    onClick.onItemClickSubCat(getAdapterPosition(),datalist.get(getAdapterPosition()).getScat_id());
                  //  Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public interface OnItemClickedSubcat {
        void onItemClickSubCat(int position,String subcatid);
    }



}