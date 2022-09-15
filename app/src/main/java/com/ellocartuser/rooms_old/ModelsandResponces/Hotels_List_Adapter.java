package com.ellocartuser.rooms_old.ModelsandResponces;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
//import com.sreelatha.elloroomslatest.Fragment_Base;
//import com.sreelatha.elloroomslatest.R;
import com.ellocartuser.R;
import java.util.List;

public class Hotels_List_Adapter extends RecyclerView.Adapter<Hotels_List_Adapter.ViewHolder>{


    SharedPreferences pref;
    //List<String> titles;
    List<Hotels_M> datalist;
    LayoutInflater inflater;
    Context ctx;
    FragmentActivity aty;
    boolean animi=true;
    //declare interface
    Hotels_List_Adapter.onItemClick_Room onItemClick_Room;

    public Hotels_List_Adapter(Context ctx,List<Hotels_M> datalist, Hotels_List_Adapter.onItemClick_Room onItemClick_Room) {
        this.datalist = datalist;
        this.onItemClick_Room = onItemClick_Room;
        this.ctx=ctx;
        this.aty= (FragmentActivity) ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<Hotels_M> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    public void setDataList( List<Hotels_M> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public Hotels_List_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_hotels_list_home,parent,false);
        return new Hotels_List_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Hotels_List_Adapter.ViewHolder holder, int position) {

        holder.hotel_name.setText(datalist.get(position).getRsubTitle());


        Glide.with(holder.itemView)
                .load(datalist.get(position).getRusbImage1())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.imag1);

        holder.relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //onClick.onItemClickProduct(position,datalist.get(position).getRsubId());
                onItemClick_Room.onItemClick_Room(position,datalist.get(position).getRsubId(),datalist.get(position).getRsubTitle());
                pref=((FragmentActivity) ctx)
                        .getSharedPreferences("ROOM_SUBID", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("r_subid",datalist.get(position).getRsubId());
                editor.putString("r_subtitle",datalist.get(position).getRsubTitle());

                editor.commit();

//                Intent ii = new Intent(Payment_Activity.this, Fragmentbase.class);
//                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                ii.putExtra("type", "PAYTOGMA");
//                startActivity(ii);

//                Intent i = new Intent(ctx, Fragment_Base.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //add this line
//                i.putExtra("type", "ROOMS");
//                ctx.startActivity(i);


            }
        });
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView hotel_name;
        ImageView imag1;
        RelativeLayout relativeLayout1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hotel_name = itemView.findViewById(R.id.hotel_name);
            imag1= itemView.findViewById(R.id.imag1);
            relativeLayout1= itemView.findViewById(R.id.rla1);



        }
    }

    public interface onItemClick_Room {
        void onItemClick_Room(int position,String catid,String name);
//        void onItemClickaddtocart(int position,String catid);
//        void onItemClickaddtocartupdatecount(int position,String proid,String qty);
    }
}
