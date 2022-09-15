package com.ellocartuser.rooms_old.ModelsandResponces;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
//import com.sreelatha.elloroomslatest.Fragment_Base;
//import com.sreelatha.elloroomslatest.R;
import com.ellocartuser.R;
import java.util.List;

public class Rooms_List_Adapter extends RecyclerView.Adapter<Rooms_List_Adapter.ViewHolder>{

    //List<String> titles;
    SharedPreferences pref;
    List<Rooms_M> datalist;
    LayoutInflater inflater;
    Context ctx;
    FragmentActivity aty;
    boolean animi=true;
    //declare interface
    private Rooms_List_Adapter.OnItemClickedproduct onClick;

    public Rooms_List_Adapter(Context ctx,List<Rooms_M> datalist, Rooms_List_Adapter.OnItemClickedproduct onClick) {
        this.datalist = datalist;
        this.onClick = onClick;
        this.ctx=ctx;
        this.aty= (FragmentActivity) ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<Rooms_M> list){
        this.datalist = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Rooms_List_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_rooms_list,parent,false);
        return new Rooms_List_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Rooms_List_Adapter.ViewHolder holder, int position) {

        holder.roomtype.setText(datalist.get(position).getRchildTitle());
        holder.roomtype_time.setText(datalist.get(position).getRchidHtime());
        holder.tot_avialrooms.setText(datalist.get(position).getRchildTotal());
        holder.roomtype_price.setText("\u20B9"+datalist.get(position).getRchildPrice());
        holder.tot_members.setText(datalist.get(position).getRchildTotalper());


        Glide.with(holder.itemView)
                .load(datalist.get(position).getRchildImage1())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.imag1);

        holder.btn_checkvail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClick.onItemClickProduct(position,datalist.get(position).getRchildId(),datalist.get(position).getRchildTitle());

                pref=((FragmentActivity) ctx)
                        .getSharedPreferences("GET_ROOMINFO", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("r_id",datalist.get(position).getRchildId());
                editor.putString("r_type",datalist.get(position).getRchildTitle());
                editor.putString("r_price",datalist.get(position).getRchildPrice());
                editor.putString("r_time",datalist.get(position).getRchidHtime());
                editor.putString("r_chechin",datalist.get(position).getRchildCheckin());
                editor.putString("r_chechout",datalist.get(position).getRchildCheckout());
                editor.putString("r_nop",datalist.get(position).getRchildTotalper());
                editor.putString("r_hotelid",datalist.get(position).getRchildMain());
                editor.putString("r_img",datalist.get(position).getRchildImage1());
                editor.putString("r_availrooms",datalist.get(position).getRchildTotal());

                editor.commit();

//                Intent ii = new Intent(Payment_Activity.this, Fragmentbase.class);
//                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                ii.putExtra("type", "PAYTOGMA");
//                startActivity(ii);



//                Intent i = new Intent(ctx, Fragment_Base.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //add this line
//                i.putExtra("type", "ROOM_INFO");
//                ctx.startActivity(i);


            }
        });
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView roomtype,roomtype_time,tot_avialrooms,roomtype_price,tot_members;
        ImageView imag1;
        Button btn_checkvail;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roomtype = itemView.findViewById(R.id.roomtype);
            roomtype_time = itemView.findViewById(R.id.roomtype_time);
            tot_avialrooms = itemView.findViewById(R.id.tot_avialrooms);
            roomtype_price = itemView.findViewById(R.id.roomtype_price);
            tot_members = itemView.findViewById(R.id.tot_members);

            btn_checkvail = itemView.findViewById(R.id.btn_checkvail);

            imag1= itemView.findViewById(R.id.room_img);



        }
    }

    public interface OnItemClickedproduct {
        void onItemClickProduct(int position,String catid,String title);
        void onItemClickaddtocart(int position,String catid);
        void onItemClickaddtocartupdatecount(int position,String proid,String qty);
    }
}
