package com.ellocartuser.home.adapterandmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;

import java.util.List;

public class PromoTransactionAdapter extends RecyclerView.Adapter<PromoTransactionAdapter.ViewHolder> {

    List<String> titles;
    List<PromoWallet> datalist;
    LayoutInflater inflater;
    PromoTransactionAdapter.OnItemClickeGrid onItemClickeGrid;
    int layout;
    String clr;
    Context ctx;

    public PromoTransactionAdapter(Context ctx, List<PromoWallet> datalist){
       this.datalist = datalist;
       this.clr = clr;
       this.ctx = ctx;
//        this.layout = layout;
     this.onItemClickeGrid = onItemClickeGrid;
      //  this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.walletsingle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (datalist==null)?0: datalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

      holder.currentdate.setText(datalist.get(position).getProme_ref());
        holder.type.setText("₹"+datalist.get(position).getProme_amount());
      holder.status.setText(datalist.get(position).getProme_date());
        holder.view.setBackgroundColor(ContextCompat.getColor(ctx, R.color.white));
//        if(datalist.get(position).getUsrtType().equals("1")){
//            holder.view.setBackgroundColor(ContextCompat.getColor(ctx, R.color.green));
//            holder.type.setText("Amonut Credited ₹"+datalist.get(position).get());
//        }else{
//            holder.view.setBackgroundColor(ContextCompat.getColor(ctx, R.color.quantum_googred));
//            holder.type.setText("Amonut Debited ₹"+datalist.get(position).getUsrtAmount());
//
//        }



    }

    public class ViewHolder extends RecyclerView.ViewHolder{
     //   private RelativeLayout llayout;
        private TextView currentdate,type,status;
        CardView llayout;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        //    llayout = itemView.findViewById(R.id.layout);
            llayout = itemView.findViewById(R.id.llayout);
            currentdate = itemView.findViewById(R.id.currentdate);
            type = itemView.findViewById(R.id.type);
            status = itemView.findViewById(R.id.status);
            view=itemView.findViewById(R.id.view);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//               //     onItemClickeGrid.onItemClick(getAdapterPosition(),datalist.get(getAdapterPosition()).getCategory_id(),datalist.get(getAdapterPosition()).getCategory_name());
//                }
//            });
        }
    }

    public interface OnItemClickeGrid {
        void onItemClick(int position,String carid,String name);
        void onItemClickedtrend(int position,String mParam1,String storename,String catid,String sellerid,String Statementtatus);
    }
}