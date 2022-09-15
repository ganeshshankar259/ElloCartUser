package com.ellocartuser.home.adapterandmodel;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Banners;
import com.ellocartuser.utils.TouchImageView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeSliderAdapterr {
//
//    int Rowlayout;
//    private Context context;
//    private List<SliderModelHome> mSliderItems = new ArrayList<>();
//
//    OnItemClickedSlider onclick;
//
//    public HomeSliderAdapterr(Context context, int check_single,List<SliderModelHome> bnr,OnItemClickedSlider onclick) {
//        this.mSliderItems = bnr;
//        this.context = context;
//        this.onclick = onclick;
//        this.Rowlayout = check_single;
//    }
//
//    public void renewItems(List<SliderModelHome> sliderItems) {
//        this.mSliderItems = sliderItems;
//        notifyDataSetChanged();
//    }
//
//    public void deleteItem(int position) {
//        this.mSliderItems.remove(position);
//        notifyDataSetChanged();
//    }
//
//    public void setEmpty() {
//        this.mSliderItems = new ArrayList<>();
//        notifyDataSetChanged();
//    }
//
//    public void addItem(SliderModelHome sliderItem) {
//        this.mSliderItems.add(sliderItem);
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public SliderAdapterVHHH onCreateViewHolder(ViewGroup parent) {
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(Rowlayout, null);
//        return new SliderAdapterVHHH(inflate);
//    }
//
//    @Override
//    public void onBindViewHolder(SliderAdapterVHHH viewHolder, final int position) {
//
//        SliderModelHome sliderItem = mSliderItems.get(position);
//
////        viewHolder.textViewDescription.setText(sliderItem.getDescription());
////        viewHolder.textViewDescription.setTextSize(16);
////        viewHolder.textViewDescription.setTextColor(Color.WHITE);
//
//        if (Rowlayout == R.layout.image_slider_layout_home) {
//
//
//            Glide.with(viewHolder.itemView)
//                    .load(sliderItem.getImageUrl())
//                    .fitCenter().placeholder(R.drawable.placeholderello)
//                    .into(viewHolder.imageViewBackground);
//
//        } else {
//
//
//            Glide.with(viewHolder.itemView)
//                    .load(sliderItem.getImageUrl())
//                    .fitCenter().placeholder(R.drawable.placeholderello)
//                    .into(viewHolder.imageViewBackgroundtouch);
//
//        }
//
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onclick.onItemClickslide(position,sliderItem.getBanner());
//                //  Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    @Override
//    public int getCount() {
//        //slider view count could be dynamic size
//        return mSliderItems.size();
//    }
//
//    class SliderAdapterVHHH extends HomeSliderAdapterr.ViewHolder {
//
//        View itemView;
//        TouchImageView imageViewBackgroundtouch;
//        ImageView imageGifContainer, imageViewBackground;
//        TextView textViewDescription;
//
//        public SliderAdapterVHHH(View itemView) {
//            super(itemView);
//
//            if (Rowlayout == R.layout.image_slider_layout_home) {
//                imageViewBackground = itemView.findViewById(R.id.iv_auto_image_sliderr);
//            } else {
//                imageViewBackgroundtouch = itemView.findViewById(R.id.iv_auto_image_slider);
//
//            }
//            //  imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
//            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
//            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
//            this.itemView = itemView;
//        }
//    }
//    public interface OnItemClickedSlider {
//        void onItemClickslide(int position, List<Banners> bnr);
//    }


}