package com.ellocartuser.home.adapterandmodel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import java.util.List;

public class ProductDetailedAdapter extends RecyclerView.Adapter<ProductDetailedAdapter.ViewHolder> {

    List<String> titles;
    List<ProductVariables> datalist;
    int layout;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    private ProductDetailedAdapter.OnItemClickedVarient onClick;

    public ProductDetailedAdapter(Context ctx, List<ProductVariables> datalist, ProductDetailedAdapter.OnItemClickedVarient onclick,int layout){
        this.datalist = datalist;
        this.onClick = onclick;
        this.layout=layout;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setDataList( List<ProductVariables> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public ProductDetailedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layout,parent,false);
        return new ProductDetailedAdapter.ViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailedAdapter.ViewHolder holder, int position) {

        holder.title.setText(datalist.get(position).getVproductP1());
//
        if(datalist.get(position).getSelected().equals("1")){
            holder.layout.setBackgroundColor(ContextCompat.getColor(ctx, R.color.yellowselect));
            holder.title.setTextColor( Color.parseColor("#ffffff"));

        }else{
            holder.layout.setBackgroundColor(ContextCompat.getColor(ctx, R.color.bggray));
            holder.title.setTextColor( Color.parseColor("#000000"));

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
                     onClick.onItemClickVarent(getAdapterPosition(),datalist.get(getAdapterPosition()).vproductId);
                }
            });
        }
    }

    public interface OnItemClickedVarient {
        void onItemClickVarent(int position,String catid);
    }

}