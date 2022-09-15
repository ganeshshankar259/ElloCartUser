package com.ellocartuser.home.homefragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;
import com.ellocartuser.home.adapterandmodel.Address;

import java.util.List;

public class AdressLocationAdapter extends RecyclerView.Adapter<AdressLocationAdapter.ViewHolderNear> {

    List<String> titles;
    List<Address> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    AdressLocationAdapter.OnItemClickedAdd onClick;

    public AdressLocationAdapter(Context ctx, List<Address> datalist, AdressLocationAdapter.OnItemClickedAdd onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public AdressLocationAdapter.ViewHolderNear onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.locationaddresssingle,parent,false);
        return new AdressLocationAdapter.ViewHolderNear(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull AdressLocationAdapter.ViewHolderNear holder, int position) {

        holder.name.setText(datalist.get(position).getAddrName());
       // holder.address.setText(datalist.get(position).getAddrAddress()+","+datalist.get(position).getAddrCity()+","+datalist.get(position).getAddrPincode());

//        if(position==0){
//            holder.name.setText(datalist.get(position).getAddrName());
//       //     holder.name(datalist.get(position).getAddrName());
//            holder.address.setText(datalist.get(position).getAddrAddress());
//            holder.name.setTextColor(ContextCompat.getColor(ctx, R.color.yellow));
//
//        }else {

            holder.name.setText(datalist.get(position).getAddrName());
            holder.name.setTextColor(Color.BLACK);
            //     holder.name(datalist.get(position).getAddrName());
            holder.address.setText(datalist.get(position).getAddrAddress());

//            holder.select.setTag(R.drawable.btncornorblue);
//            holder.select.setBackgroundResource(R.drawable.btncornorblue);
           // holder.select.setBackgroundResource(R.drawable.btncornorgreen);

    //    }


//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }


    public class ViewHolderNear extends RecyclerView.ViewHolder{
        TextView name,address;
        public ViewHolderNear(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);


            itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClick.onItemClickedCart(getAdapterPosition(),"",datalist.get(getAdapterPosition()));

                }
            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(ctx,"clicked",Toast.LENGTH_LONG).show();
//                }
//            });


        }
    }

    public interface OnItemClickedAdd {
        void onItemClickedCart(int position,String mParam1,Address address);
    }

}