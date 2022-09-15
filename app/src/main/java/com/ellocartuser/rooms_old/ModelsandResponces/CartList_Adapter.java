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
//import com.sreelatha.elloroomslatest.Fragment_Base;
//import com.sreelatha.elloroomslatest.R;
import com.ellocartuser.R;

import java.util.List;

public class CartList_Adapter extends RecyclerView.Adapter<CartList_Adapter.ViewHolder>{

    SharedPreferences pref;
    //List<String> titles;
    List<Cartdisplay_M> datalist;
    LayoutInflater inflater;
    Context ctx;
    FragmentActivity aty;
    boolean animi=true;
    //declare interface
    private CartList_Adapter.OnItemClickedproduct onClick;

    public CartList_Adapter(Context ctx,List<Cartdisplay_M> datalist, CartList_Adapter.OnItemClickedproduct onClick) {
        this.datalist = datalist;
        this.onClick = onClick;
        this.ctx=ctx;
//        this.aty= (FragmentActivity) ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<Cartdisplay_M> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartList_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_cart_display,parent,false);
        return new CartList_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartList_Adapter.ViewHolder holder, int position) {

        holder.roomtype.setText(datalist.get(position).getRchildTitleS());
        holder.price.setText("\u20B9"+datalist.get(position).getRchildPrice());
        holder.roomsno.setText(datalist.get(position).getBcartQty());
        holder.amount.setText("\u20B9"+Integer.toString(datalist.get(position).getTotalAmount()));


        Glide.with(holder.itemView)
                .load(datalist.get(position).getRchildImage1())
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(holder.reportimage);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             onClick.onItemClickProduct(position,datalist.get(position).getBcartId());

////                pref=((FragmentActivity) ctx)
////                        .getSharedPreferences("GM_APPOINT", Context.MODE_PRIVATE);
////                SharedPreferences.Editor editor = pref.edit();
////                editor.putString("d_name",datalist.get(position).getd_name());
////                editor.putString("d_image",datalist.get(position).getd_image());
////                editor.putString("d_edu",datalist.get(position).getd_edu());
////                editor.putString("d_price",datalist.get(position).getd_price());
////                editor.putString("d_special",datalist.get(position).getd_specialization());
////                editor.putString("d_id",datalist.get(position).getd_id());
////
////                editor.commit();
//
////                Intent ii = new Intent(Payment_Activity.this, Fragmentbase.class);
////                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                ii.putExtra("type", "PAYTOGMA");
////                startActivity(ii);
//
//                Intent i = new Intent(ctx, Fragment_Base.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //add this line
//                i.putExtra("type", "ROOMS");
//                ctx.startActivity(i);
//
//
            }
        });
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView roomtype,price,roomsno,amount;
        ImageView reportimage,delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roomtype = itemView.findViewById(R.id.roomtype);
            price = itemView.findViewById(R.id.price);
            roomsno = itemView.findViewById(R.id.roomsno);
            amount = itemView.findViewById(R.id.amount);
            reportimage= itemView.findViewById(R.id.reportimage);

            delete = itemView.findViewById(R.id.delete);


        }
    }

    public interface OnItemClickedproduct {
        void onItemClickProduct(int position,String BcartId);
        void onItemClickaddtocart(int position,String catid);
        void onItemClickaddtocartupdatecount(int position,String proid,String qty);
    }
}
