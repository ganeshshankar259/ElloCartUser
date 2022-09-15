package com.ellocartuser.orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.home.adapterandmodel.CarStoreAdapter;
import com.ellocartuser.home.adapterandmodel.CategoryStoreAll;

import java.util.List;

class CanceledAdapter extends RecyclerView.Adapter<CanceledAdapter.ViewHolder> {

    List<String> titles;
    List<CategoryStoreAll> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    private CarStoreAdapter.OnItemClickedcat onClick;

    public CanceledAdapter(Context ctx, List<CategoryStoreAll> datalist, CarStoreAdapter.OnItemClickedcat onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setDataList( List<CategoryStoreAll> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public CanceledAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.catsubcatsingle,parent,false);
        return new CanceledAdapter.ViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CanceledAdapter.ViewHolder holder, int position) {

        holder.title.setText(datalist.get(position).getCategory_name());
        holder.storereviewbtn.setVisibility(View.INVISIBLE);

//        if(datalist.get(position).getSelected().equals("1")){
//            holder.layout.setBackgroundColor(ContextCompat.getColor(ctx, R.color.yellow));
//        }else{
   //       holder.layout.setBackgroundColor(ContextCompat.getColor(ctx, R.color.bggray));
//        }

        Glide.with(holder.itemView)
                .load(datalist.get(position).getCategory_image())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.gridIcon);

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,km,address;
        ImageView gridIcon;
        ConstraintLayout layout;
        RatingBar rating;
        Button storereviewbtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
//            title.setSelected(true);
            gridIcon = itemView.findViewById(R.id.imageView2);
            layout = itemView.findViewById(R.id.layout);
            storereviewbtn = itemView.findViewById(R.id.storereviewbtn);
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
                    // Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    onClick.onItemClickCat(getAdapterPosition(),datalist.get(getAdapterPosition()).getCategory_id());
                }
            });
        }
    }

    public interface OnItemClickedcat {
        void onItemClickCat(int position,String catid);
    }

}
