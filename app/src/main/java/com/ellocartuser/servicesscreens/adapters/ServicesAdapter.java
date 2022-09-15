package com.ellocartuser.servicesscreens.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.OtherServiceFOrServices;
import com.ellocartuser.home.adapterandmodel.ProductVariables;
import com.ellocartuser.home.adapterandmodel.SubcategoryStoreAll;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    List<String> titles;
    List<SubcategoryStoreAll> datalist;
    LayoutInflater inflater;
    Context ctx;
    String bgcolor;
    //declare interface
    List<OtherServiceFOrServices> otherServices;
    private ServicesAdapter.OnItemClickedVarient onClick;

    public ServicesAdapter(Context ctx, List<SubcategoryStoreAll> datalist, List<OtherServiceFOrServices> otherServices, String bgcolor){
        this.datalist = datalist;
        this.bgcolor = bgcolor;
        this.otherServices = otherServices;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setDataList( List<SubcategoryStoreAll> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout_service,parent,false);
        return new ServicesAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(bgcolor.equals("yellow")){
            return (datalist==null)?0: datalist.size();
        }else  {
            return (otherServices == null) ? 0 : otherServices.size();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.ViewHolder holder, int position) {
//
        if(bgcolor.equals("yellow")){
            holder.title.setText(datalist.get(position).getScat_name());

            holder.title.setTextColor(ContextCompat.getColor(ctx, R.color.white));
            holder.layout.setBackgroundColor(ContextCompat.getColor(ctx, R.color.yellow));
        } else {
            holder.title.setText(otherServices.get(position).getServcatName());
            holder.layout.setBackgroundColor(ContextCompat.getColor(ctx, R.color.bggray));
        }
//        Glide.with(holder.itemView)
//                .load(datalist.get(position).getCategory_image())
//                .fitCenter()
//                .into(holder.gridIcon);

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,km,address;
        ImageView gridIcon;
        ConstraintLayout layout;
        RatingBar rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
//            title.setSelected(true);
//            gridIcon = itemView.findViewById(R.id.imageView2);
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
                   //  Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    // onClick.onItemClickVarent(getAdapterPosition(),datalist.get(getAdapterPosition()).vproductId);
                }
            });
        }
    }

    public interface OnItemClickedVarient {
        void onItemClickVarent(int position,String catid);
    }

}