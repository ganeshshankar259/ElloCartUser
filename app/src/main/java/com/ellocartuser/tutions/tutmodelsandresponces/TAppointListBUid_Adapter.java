package com.ellocartuser.tutions.tutmodelsandresponces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;

import java.util.List;

public class TAppointListBUid_Adapter extends RecyclerView.Adapter<TAppointListBUid_Adapter.ViewHolder> {
    List<String> titles;
    List<TAppointListBUid_M> datalist;
    LayoutInflater inflater;
    Context ctx;
    TAppointListBUid_Adapter.onItemClickeRoom onItemClickeRoom;
    int layout;
    FragmentActivity aty;
    SharedPreferences pref;

    public TAppointListBUid_Adapter(Context ctx, List<TAppointListBUid_M> datalist, TAppointListBUid_Adapter.onItemClickeRoom onItemClickeRoom){
        this.datalist = datalist;
        //this.layout = layout;
        this.ctx = ctx;
        this.onItemClickeRoom = onItemClickeRoom;
        this.aty= (FragmentActivity) ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<TAppointListBUid_M> list){
        this.datalist = list;
        notifyDataSetChanged();
    }
    public void setDataList( List<TAppointListBUid_M> datalist){
        this.datalist = datalist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TAppointListBUid_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_tut_list_byuid,parent,false);
        return new TAppointListBUid_Adapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull TAppointListBUid_Adapter.ViewHolder holder, int position) {

//        try {
//            if (!datalist.get(position).getCategory_clr().equals("0")) {
//                int val = Color.parseColor(datalist.get(position).getCategory_clr());
//                holder.title.setBackgroundColor(val);
//            } else {
//                int val = Color.parseColor(" #ff9e29");
//                holder.title.setBackgroundColor(val);
//            }
//        }catch (Exception ex){ ex.printStackTrace(); }


        holder.title.setText(datalist.get(position).getPost_title());
        holder.tut_address.setText(datalist.get(position).getSellerStoreAddress()+','+datalist.get(position).getSellerCity());
        holder.dateandtime.setText(datalist.get(position).getaDate()+' '+datalist.get(position).getaTime());
        holder.phone.setText(datalist.get(position).getSellerPhone());
        Glide.with(holder.itemView)
                .load(datalist.get(position).getSellerStoreImage())
                .fitCenter()//.placeholder(R.drawable.placeholderello)
                .into(holder.img);

        if (datalist.get(position).getA_pay_status().equalsIgnoreCase("CANCELLED")){
            holder.cancel.setVisibility(View.VISIBLE);
        }else {
            holder.cancel.setVisibility(View.GONE);
        }


        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickeRoom.onItemClick_Room(position,datalist.get(position).getSellerId(),datalist.get(position).getSellerStoreName());

                pref=((FragmentActivity) ctx)
                        .getSharedPreferences("ROOM_CATID000", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("r_catid",datalist.get(position).getSellerId());
                editor.putString("r_cattitle",datalist.get(position).getSellerStoreName());
                editor.commit();
            }
        });

        holder.img_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+datalist.get(position).getSellerPhone()));
                ctx.startActivity(callIntent);

            }
        });


//      holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView llayout;
        private TextView title,tut_address,dateandtime,phone,cancel;
        private ImageView img,img_call;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            llayout = itemView.findViewById(R.id.llayout);
            title = itemView.findViewById(R.id.tut_name);
            tut_address = itemView.findViewById(R.id.tut_address);
            dateandtime = itemView.findViewById(R.id.dateandtime);
            phone = itemView.findViewById(R.id.phone);
            cancel = itemView.findViewById(R.id.cancel);

            img_call = itemView.findViewById(R.id.img_call);
            img = itemView.findViewById(R.id.reportimage);


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
