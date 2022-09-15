package com.ellocartuser.AdaptersAndModel.RoomsDetailsList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.ellocartuser.AdaptersAndModel.RoomsLitsBased.RoomsListBasedModel;
import com.ellocartuser.R;
import com.ellocartuser.RoomsNew.RoomDetailInfoActivtiy;
import com.ellocartuser.RoomsNew.RoomDetailingListActivtiy;
import com.ellocartuser.utils.CustomItemFive;
import java.util.List;

public class RoomsDetailsListAdapter extends RecyclerView.Adapter {
    private static final String TAG = "CategoryAdapter";
    private CustomItemFive customItemClick;
    Context context;
    List<RoomsDetailsListModel> my_data;
    RoomsDetailsListModel deliveryEarningListModel;
    public RoomsDetailsListAdapter(List<RoomsDetailsListModel> my_data, Context context, CustomItemFive customItemClick) {
        this.context = context;
        this.my_data = my_data;
        this.customItemClick = customItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rooms_detailing_list_items, parent, false);
        return new RoomsDetailsListAdapter.MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,@SuppressLint("RecyclerView") final int position) {
        RoomsDetailsListAdapter.MenuViewHolder holder = (RoomsDetailsListAdapter.MenuViewHolder) viewHolder;
        deliveryEarningListModel = my_data.get(position);
        holder.txt_title.setText(deliveryEarningListModel.getRchild_title());
        holder.txt_Available.setText(deliveryEarningListModel.getRchid_htime()+" Available");
        holder.txt_Members.setText("Maximum: "+deliveryEarningListModel.getMax()+" Members");
        holder.txt_min.setText("₹"+deliveryEarningListModel.getRchild_price());
        holder.txt_max.setText("₹"+deliveryEarningListModel.getSell_min_price());
        holder.txt_max.setPaintFlags(holder.txt_max.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);



        if (deliveryEarningListModel.getRchild_image1()==null || deliveryEarningListModel.getRchild_image1().equalsIgnoreCase("")){
            holder.img_product.setImageResource(R.drawable.placeholderello);
        }else {
            Glide.with(holder.itemView)
                    .load(deliveryEarningListModel.getRchild_image1())
                    .fitCenter().placeholder(R.drawable.placeholderello)
                    .into(holder.img_product);
        }


    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private class MenuViewHolder extends RecyclerView.ViewHolder {
        public CardView cardview;
        public ImageView img_product;
        public TextView txt_title;
        public TextView txt_Available;
        public TextView txt_Members;
        public TextView txt_min;
        public TextView txt_max;
        public CardView card_check_availability;


        MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            cardview = itemView.findViewById(R.id.cardview);
            img_product = itemView.findViewById(R.id.img_product);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_Available = itemView.findViewById(R.id.txt_Available);
            txt_Members = itemView.findViewById(R.id.txt_Members);
            txt_min = itemView.findViewById(R.id.txt_min);
            txt_max = itemView.findViewById(R.id.txt_max);
            card_check_availability = itemView.findViewById(R.id.card_check_availability);

            card_check_availability.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    String Seller_r_id = my_data.get(getAdapterPosition()).getSeller_r_id();
                    String rchild_id = my_data.get(getAdapterPosition()).getRchild_id();
                    String rchild_main = my_data.get(getAdapterPosition()).getRchild_main();
                    String rchild_title = my_data.get(getAdapterPosition()).getRchild_title();
                    String rchild_total = my_data.get(getAdapterPosition()).getRchild_total();
                    String rchild_totalper = my_data.get(getAdapterPosition()).getRchild_totalper();
                    String min = my_data.get(getAdapterPosition()).getMin();
                    String max = my_data.get(getAdapterPosition()).getMax();
                    String rchild_checkin = my_data.get(getAdapterPosition()).getRchild_checkin();
                    String rchild_checkout = my_data.get(getAdapterPosition()).getRchild_checkout();
                    String rchild_desc = my_data.get(getAdapterPosition()).getRchild_desc();
                    String rchild_price = my_data.get(getAdapterPosition()).getRchild_price();
                    String rchid_htime = my_data.get(getAdapterPosition()).getRchid_htime();
                    String rchild_image1 = my_data.get(getAdapterPosition()).getRchild_image1();
                    String rchild_status = my_data.get(getAdapterPosition()).getRchild_status();
                    String rchild_created = my_data.get(getAdapterPosition()).getRchild_created();
                    Intent intent = new Intent(context, RoomDetailInfoActivtiy.class);
                    intent.putExtra("Seller_r_id",Seller_r_id);
                    intent.putExtra("rchild_id",rchild_id);
                    intent.putExtra("rchild_main",rchild_main);
                    intent.putExtra("rchild_title",rchild_title);
                    intent.putExtra("rchild_total",rchild_total);
                    intent.putExtra("rchild_totalper",rchild_totalper);
                    intent.putExtra("min",min);
                    intent.putExtra("max",max);
                    intent.putExtra("rchild_checkin",rchild_checkin);
                    intent.putExtra("rchild_checkout",rchild_checkout);
                    intent.putExtra("rchild_desc",rchild_desc);
                    intent.putExtra("rchild_price",rchild_price);
                    intent.putExtra("rchid_htime",rchid_htime);
                    intent.putExtra("rchild_image1",rchild_image1);
                    intent.putExtra("rchild_status",rchild_status);
                    intent.putExtra("rchild_created",rchild_created);
                    context.startActivity(intent);
                }
            });


        }
    }

    public void updateList(List<RoomsDetailsListModel> list){
        my_data = list;
        notifyDataSetChanged();
    }

}
