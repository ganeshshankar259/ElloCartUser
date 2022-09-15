package com.ellocartuser.home.homefragment.homescreen_down_section;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.home.homefragment.homefragment;

import java.util.List;


public class SectionsHomeAdapterService extends RecyclerView.Adapter<SectionsHomeAdapterService.ViewHolder> {

    List<String> titles;
    List<Categories> datalist;
    LayoutInflater inflater;
    FragmentActivity ctx;
    //  com.ellocartuser.home.adapterandmodel.SectionsHomeAdapterService.OnItemClickeGrid onItemClickeGrid;
    int layout;
    OnItemClickeGrid onItemClickeGrid;
    homefragment homefragment;
    public SectionsHomeAdapterService(FragmentActivity ctx, List<Categories> datalist, OnItemClickeGrid onItemClickeGrid, homefragment homefragment) {
        this.datalist = datalist;
         this.homefragment  = homefragment;
        this.ctx = ctx;
        this.onItemClickeGrid = onItemClickeGrid;
        //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setDataList(List<Categories> datalist) {
        this.datalist = datalist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.homesectiondown_single, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist == null) ? 0 : datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(datalist.get(position).getCategory_name());

//        if(datalist.get(position).getCategory_clr1()!=null) {
//            if (!datalist.get(position).getCategory_clr1().equals("0")) {
//
//                int val = Color.parseColor(datalist.get(position).getCategory_clr1());
//                holder.catList.setBackgroundColor(val);
//                holder.layout.setBackgroundColor(val);
//
//            } else {
//
//                int val = Color.parseColor(" #ff9e29");
//                holder.layout.setBackgroundColor(val);
//                holder.catList.setBackgroundColor(val);
//
//            }
//        } else {
//
//            int val = Color.parseColor(" #ff9e29");
//            holder.layout.setBackgroundColor(val);
//
//        }

//        holder.amount.setText("₹"+datalist.get(position).getAmt());

        holder.catList.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false));
        //  holder.catList.setNestedScrollingEnabled(true);

        StoreDisplayAdapter gridadapter = new StoreDisplayAdapter(ctx, datalist.get(position).getCategory_stores(), datalist.get(position).getCategory_clr(),homefragment,datalist.get(position).getCategory_id());
        holder.catList.setAdapter(gridadapter);

        holder.viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickeGrid.onItemClickDownSection(position,datalist.get(position).getCategory_id(),datalist.get(position).getCategory_name());
            }
        });
        holder.tvViewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickeGrid.onItemClickDownSection(position,datalist.get(position).getCategory_id(),datalist.get(position).getCategory_name());
            }
        });

    }

    public interface OnItemClickeGrid {
        void onItemClickDownSection(int position, String carid, String name);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView catList;
        LinearLayout layout;
        ImageView viewall;
        //  private CardView llayout;
        private TextView title, cityrange, citydaybreak, citycod,tvViewall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            viewall = itemView.findViewById(R.id.viewall);
            layout = itemView.findViewById(R.id.layout);
            catList = itemView.findViewById(R.id.catList);
            title = itemView.findViewById(R.id.title);
            tvViewall = itemView.findViewById(R.id.tvViewall);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onItemClickeGrid.onItemClick(getAdapterPosition(),datalist.get(getAdapterPosition()).getCategory_id(),datalist.get(getAdapterPosition()).getCategory_name());
//                }
//            });
        }
    }
}