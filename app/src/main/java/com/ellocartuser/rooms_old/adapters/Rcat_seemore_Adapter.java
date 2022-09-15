package com.ellocartuser.rooms_old.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.rooms_old.ModelsandResponces.Roomcategories_M;

import java.util.List;

public class Rcat_seemore_Adapter extends RecyclerView.Adapter<Rcat_seemore_Adapter.ViewHolder> {

    List<String> titles;
    List<Roomcategories_M> datalist;
    LayoutInflater inflater;
    Context ctx;
    Rcat_seemore_Adapter.onItemClickeRoom onItemClickeRoom;
    int layout;
    FragmentActivity aty;
    SharedPreferences pref;

    public Rcat_seemore_Adapter(Context ctx, List<Roomcategories_M> datalist, Rcat_seemore_Adapter.onItemClickeRoom onItemClickeRoom){
        this.datalist = datalist;
        //this.layout = layout;
        this.ctx = ctx;
        this.onItemClickeRoom = onItemClickeRoom;
        this.aty= (FragmentActivity) ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<Roomcategories_M> list){
        this.datalist = list;
        notifyDataSetChanged();
    }
    public void setDataList( List<Roomcategories_M> datalist){
        this.datalist = datalist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Rcat_seemore_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_seemore_category_list,parent,false);
        return new Rcat_seemore_Adapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Rcat_seemore_Adapter.ViewHolder holder, int position) {

//        try {
//            if (!datalist.get(position).getCategory_clr().equals("0")) {
//                int val = Color.parseColor(datalist.get(position).getCategory_clr());
//                holder.title.setBackgroundColor(val);
//            } else {
//                int val = Color.parseColor(" #ff9e29");
//                holder.title.setBackgroundColor(val);
//            }
//        }catch (Exception ex){ ex.printStackTrace(); }

        holder.title.setText(datalist.get(position).getRcatName());

        Glide.with(holder.itemView)
                .load(datalist.get(position).getRcatImage1())
                .fitCenter()//.placeholder(R.drawable.placeholderello)
                .into(holder.img);

        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onItemClickeRoom.onItemClick_Room(position,datalist.get(position).getRcatId(),datalist.get(position).getRcatName());

                pref=((FragmentActivity) ctx)
                        .getSharedPreferences("ROOM_CATID", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("r_catid",datalist.get(position).getRcatId());
                editor.putString("r_cattitle",datalist.get(position).getRcatName());

                editor.commit();

            }
        });



//      holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout llayout;
        private TextView title;
        private ImageView img;

        LinearLayout imgbgll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            llayout = itemView.findViewById(R.id.rla1);
            title = itemView.findViewById(R.id.hotel_name);
            img = itemView.findViewById(R.id.imag1);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onItemClickeGrid.onItemClick(getAdapterPosition(),datalist.get(getAdapterPosition()).getCategory_id(),datalist.get(getAdapterPosition()).getCategory_name());
//                }
//            });
        }
    }

    public interface onItemClickeRoom {
        void onItemClick_Room(int position,String carid,String name);
    }
}
