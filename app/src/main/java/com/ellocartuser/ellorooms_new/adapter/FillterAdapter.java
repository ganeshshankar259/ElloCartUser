package com.ellocartuser.ellorooms_new.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.ellorooms_new.models.Languages;
import com.ellocartuser.ellorooms_new.models.RoomModel;

import java.util.List;

public class FillterAdapter extends RecyclerView.Adapter<FillterAdapter.ViewHolderNear> {

    List<String> titles;
    List<Languages> datalist;
    LayoutInflater inflater;
    Context ctx;
    //declare interface
    OnItemClicked onClick;

    public FillterAdapter(Context ctx, List<Languages> datalist, OnItemClicked onclick){
        this.datalist = datalist;
        this.onClick = onclick;
        this.ctx=ctx;
        //  this.images = images;
        if(ctx!=null) {
            this.inflater = LayoutInflater.from(ctx);
        }
    }

    @NonNull
    @Override
    public FillterAdapter.ViewHolderNear onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ellorooms_fillter_single,parent,false);
        return new FillterAdapter.ViewHolderNear(view);
    }
    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull FillterAdapter.ViewHolderNear holder, int position) {

        holder.name.setText(datalist.get(position).getLang_title());

        holder.ckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClick.onItemClickedLang(position,datalist.get(position).getLang_id(),"");

            }
        });
        if(datalist.get(position).getSelect()==1){
            holder.ckbox.setChecked(true);
        }else{
            holder.ckbox.setChecked(false);

        }

//        holder.gridIcon.setImageResource(datalist.get(position).getImageUrl());
    }

    public void setData(List<Languages> list){
        this.datalist=list;
        notifyDataSetChanged();
    }
    public class ViewHolderNear extends RecyclerView.ViewHolder{
        TextView name;
        CheckBox ckbox;
        ConstraintLayout llayout;
        public ViewHolderNear(@NonNull View itemView) {
            super(itemView);

            llayout = itemView.findViewById(R.id.llayout);
            name = itemView.findViewById(R.id.name);
            ckbox = itemView.findViewById(R.id.checkBox2);

        }
    }

    public interface OnItemClicked {
        void onItemClickedLang(int position,String mParam1,String mParam2);
    }

}