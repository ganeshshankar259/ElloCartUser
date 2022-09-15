package com.ellocartuser.rooms_old.ModelsandResponces;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

//import com.sreelatha.elloroomslatest.Fragment_Base;
//import com.sreelatha.elloroomslatest.R;
import com.ellocartuser.R;
import java.util.List;

public class Dates_Adapter extends RecyclerView.Adapter<Dates_Adapter.ViewHolder>{


    SharedPreferences pref;
    //List<String> titles;
    List<Dates_M> datalist;
    LayoutInflater inflater;
    Context ctx;
    FragmentActivity aty;
    boolean animi=true;
    //declare interface
    private Dates_Adapter.OnItemClickedproduct onClick;
    int row_index = -1;
    public Dates_Adapter(Context ctx,List<Dates_M> datalist, Dates_Adapter.OnItemClickedproduct onClick) {
        this.datalist = datalist;
        this.onClick = onClick;
        this.ctx=ctx;
        this.aty= (FragmentActivity) ctx;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void updateList(List<Dates_M> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Dates_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_date,parent,false);
        return new Dates_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Dates_Adapter.ViewHolder holder, int position) {

        holder.txt_date.setText(datalist.get(position).getRdDate());

        holder.date_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClick.onItemClickProduct(position,datalist.get(position).getRdDate());
                row_index=position;
                notifyDataSetChanged();
            }
        });

        if(row_index==position){
            holder.txt_date.setBackgroundColor(Color.parseColor("#ff9e29"));
            holder.txt_date.setTextColor(Color.parseColor("#ffffff"));
            //holder.tv1.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            holder.txt_date.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.txt_date.setTextColor(Color.parseColor("#FF000000"));
            //holder.tv1.setTextColor(Color.parseColor("#000000"));
        }

    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_date;
        LinearLayout date_layout;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_date = itemView.findViewById(R.id.txt_date);
            date_layout = itemView.findViewById(R.id.date_layout);



        }
    }

    public interface OnItemClickedproduct {
        void onItemClickProduct(int position,String Date);
        void onItemClickaddtocart(int position,String catid);
        void onItemClickaddtocartupdatecount(int position,String proid,String qty);
    }
}
