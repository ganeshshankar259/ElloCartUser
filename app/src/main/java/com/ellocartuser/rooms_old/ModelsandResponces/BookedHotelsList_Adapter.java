package com.ellocartuser.rooms_old.ModelsandResponces;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
//import com.sreelatha.elloroomslatest.Fragment_Base;
//import com.sreelatha.elloroomslatest.R;

import java.util.List;

public class BookedHotelsList_Adapter extends RecyclerView.Adapter<BookedHotelsList_Adapter.ViewHolder>{

    SharedPreferences pref;
    //List<String> titles;
    List<BookedHotels_M> datalist;
    LayoutInflater inflater;
    Context ctx;
    FragmentActivity aty;
    boolean animi=true;
    //declare interface
    private BookedHotelsList_Adapter.OnItemClickedproduct onClick;

    public BookedHotelsList_Adapter(Context ctx,List<BookedHotels_M> datalist, BookedHotelsList_Adapter.OnItemClickedproduct onClick) {
        this.datalist = datalist;
        this.onClick = onClick;
        this.ctx=ctx;
        this.aty= (FragmentActivity) ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<BookedHotels_M> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    public void setDataList( List<BookedHotels_M> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public BookedHotelsList_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_hotels_list,parent,false);
        return new BookedHotelsList_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookedHotelsList_Adapter.ViewHolder holder, int position) {

        holder.hotel_name.setText(datalist.get(position).getRsubTitle());
        holder.booked_status.setText(datalist.get(position).getb_reason_msg());


        Glide.with(holder.itemView)
                .load(datalist.get(position).getRusbImage1())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.imag1);

        holder.hotel_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClick.onItemClickProduct(position,datalist.get(position).getRchildMain(),datalist.get(position).getRsubTitle());

                pref=((FragmentActivity) ctx)
                        .getSharedPreferences("GM_B_ORDERID", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("b_orderid",datalist.get(position).getb_orderid());

                editor.commit();

//                Intent ii = new Intent(Payment_Activity.this, Fragmentbase.class);
//                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                ii.putExtra("type", "PAYTOGMA");
//                startActivity(ii);

//                Intent i = new Intent(ctx, Fragment_Base.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //add this line
//                i.putExtra("type", "BOOKED_ROOMS");
//                ctx.startActivity(i);


            }
        });
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView hotel_name,booked_status;
        ImageView imag1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hotel_name = itemView.findViewById(R.id.hotel_name);
            booked_status = itemView.findViewById(R.id.booked_status);

            imag1= itemView.findViewById(R.id.imag1);


        }
    }

    public interface OnItemClickedproduct {
        void onItemClickProduct(int position,String catid,String name);
        void onItemClickaddtocart(int position,String catid);
        void onItemClickaddtocartupdatecount(int position,String proid,String qty);
    }
}
