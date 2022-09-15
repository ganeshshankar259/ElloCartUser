package com.ellocartuser.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;
import com.ellocartuser.home.adapterandmodel.Address;
import com.ellocartuser.home.adapterandmodel.AddressFriend;

import java.util.List;

public class FriendAdressAdapter extends RecyclerView.Adapter<FriendAdressAdapter.ViewHolderNear> {

    List<String> titles;
    List<AddressFriend> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    FriendAdressAdapter.OnItemClickedAdd onClick;

    public FriendAdressAdapter(Context ctx, List<AddressFriend> datalist, FriendAdressAdapter.OnItemClickedAdd onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<AddressFriend> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FriendAdressAdapter.ViewHolderNear onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.addresssingle,parent,false);
        return new FriendAdressAdapter.ViewHolderNear(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdressAdapter.ViewHolderNear holder, int position) {

        holder.name.setText(datalist.get(position).getAddrName());
        holder.phnnumber.setText(datalist.get(position).getAddrPhone());
        holder.address.setText(datalist.get(position).getAddrAddress());

        if(datalist.get(position).getSelect().equals("0")){
   //     holder.selectimg.setImageResource(0);
            holder.selectimg.setImageResource(R.drawable.addressnontick);

           // holder.select.setBackgroundResource(R.drawable.btncornorblue);

        }else if(datalist.get(position).getSelect().equals("1")){


            holder.selectimg.setImageResource(R.drawable.addresstick);

//            holder.select.setTag(R.drawable.btncornorblue);
//            holder.select.setBackgroundResource(R.drawable.btncornorblue);
           // holder.select.setBackgroundResource(R.drawable.btncornorgreen);

        }

        holder.deleteimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                onClick.onItemClickedCart(position,"delet",datalist.get(position).getCartQty(),datalist.get(position).getProductId(),datalist.get(position).getSproductId(),datalist.get(position).getSellerId());
                    onClick.onItemClickedCartFriend(position,"delete",datalist.get(position));

            }
        });

//        holder.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                onClick.onItemClickedCart(position,"plus",datalist.get(position).getCartQty(),datalist.get(position).getProductId(),datalist.get(position).getSproductId(),datalist.get(position).getSellerId());
//                onClick.onItemClickedCart(position,"edit",datalist.get(position));
//
//            }
//        });
//
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                onClick.onItemClickedCart(position,"plus",datalist.get(position).getCartQty(),datalist.get(position).getProductId(),datalist.get(position).getSproductId(),datalist.get(position).getSellerId());
                onClick.onItemClickedCartFriend(position,"select",datalist.get(position));


            }
        });


//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }


    public class ViewHolderNear extends RecyclerView.ViewHolder{
        TextView name,phnnumber,address;
        ImageView deleteimg,selectimg;
        LinearLayout layout;
     //   Button delet,edit,select;
        public ViewHolderNear(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout);
            name = itemView.findViewById(R.id.name);
            phnnumber = itemView.findViewById(R.id.phnnumber);
            address = itemView.findViewById(R.id.address);
            selectimg = itemView.findViewById(R.id.imageView11);
            deleteimg = itemView.findViewById(R.id.deleteimg);



//            itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                onClick.onItemClicked(getAdapterPosition(),datalist.get(getAdapterPosition()).getSellerId());
//
//                }
//            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(ctx,"clicked",Toast.LENGTH_LONG).show();
//                }
//            });


        }
    }

    public interface OnItemClickedAdd {
        void onItemClickedCartFriend(int position,String mParam1,AddressFriend address);
    }

}