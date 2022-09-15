package com.ellocartuser.servicesscreens;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.home.adapterandmodel.SubcategoryStoreAll;

import java.util.List;

public class SubCatServiceAdapter extends RecyclerView.Adapter<SubCatServiceAdapter.ViewHolder> {

    List<String> titles;
    List<SubcategoryStoreAll> datalist;
    LayoutInflater inflater;
    //declare interface
    Context ctx;
    private SubCatServiceAdapter.OnItemClickedSubcat onClick;

    public SubCatServiceAdapter(Context ctx, List<SubcategoryStoreAll> datalist, SubCatServiceAdapter.OnItemClickedSubcat onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
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
//      holder.rating.setRating(Float.parseFloat(datalist.get(position).getSellerRating()));
//        holder.address.setText(datalist.get(position).getSellerStoreAddress()+", "+datalist.get(position).getSellerCity());
//        if(String.valueOf(datalist.get(position).getSellerDistance()).equals("1")) {
//            holder.km.setText(String.valueOf(datalist.get(position).getSellerDistance()) + "km");
//        }else{
//            holder.km.setText(String.valueOf(datalist.get(position).getSellerDistance()) + "kms");
//        }
//        holder.rating.setRating(Float.parseFloat(datalist.get(position).getSellerRating()));
        Glide.with(holder.itemView)
                .load(datalist.get(position).getScat_image())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.img);

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());

//        holder.img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //onClick.onItemClickSubCat(position,datalist.get(position).getScat_id(),datalist.get(position).getScat_name());
//
//
//
//            }
//        });
//

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,km,address;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
            img = itemView.findViewById(R.id.imageView10);
//            title.setSelected(true);
        //    gridIcon = itemView.findViewById(R.id.imageView2);
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
                    onClick.onItemClickSubCat(getAdapterPosition(),datalist.get(getAdapterPosition()).getScat_id(),datalist.get(getAdapterPosition()).getScat_name());

                    SharedPreferences pref = ctx.getSharedPreferences("SERVICES_SUBCATID1", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    //   editor.putString("boy",resource.getBoy());
                    editor.putString("subcatid", datalist.get(getAdapterPosition()).getScat_id());
                    editor.putString("subcatname", datalist.get(getAdapterPosition()).getScat_name());


                    editor.commit();


                  //  Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public interface OnItemClickedSubcat {
        void onItemClickSubCat(int position,String subcatid,String name);
    }



}