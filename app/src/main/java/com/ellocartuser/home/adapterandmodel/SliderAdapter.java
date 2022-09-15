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

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    int Rowlayout;
    private Context context;
    private List<SliderModelHome> mSliderItems = new ArrayList<>();
 //   private List<SliderModelHome> mSliderItems = new ArrayList<>();

    OnItemClickedSlider onclick;
    public SliderAdapter(Context context, int check_single,List<SliderModelHome> bnr,OnItemClickedSlider onclick) {
        this.context = context;
        this.mSliderItems = bnr;
          this.onclick = onclick;

        this.Rowlayout = check_single;
    }

    public void renewItems(List<SliderModelHome> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void setEmpty() {
        this.mSliderItems = new ArrayList<>();
        notifyDataSetChanged();
    }

    public void addItem(SliderModelHome sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(Rowlayout, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        SliderModelHome sliderItem = mSliderItems.get(position);

//        viewHolder.textViewDescription.setText(sliderItem.getDescription());
//        viewHolder.textViewDescription.setTextSize(16);
//        viewHolder.textViewDescription.setTextColor(Color.WHITE);

        if (Rowlayout == R.layout.image_slider_layout_home || Rowlayout == R.layout.home_slider_layout) {


            Glide.with(viewHolder.itemView)
                    .load(sliderItem.getImageUrl())
                    .fitCenter().placeholder(R.drawable.placeholderello)
                    .into(viewHolder.imageViewBackground);

        } else if(Rowlayout == R.layout.image_slider_layout_item)  {

            Glide.with(viewHolder.itemView)
                    .load(sliderItem.getImageUrl())
                    .fitCenter().placeholder(R.drawable.placeholderello)
                    .into(viewHolder.imageViewBackgroundtouch);

        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (onclick != null && sliderItem.getBanner() != null) {
                        onclick.onItemClickslide(position, sliderItem.getBanner());
                        //  Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){

                }
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        TouchImageView imageViewBackgroundtouch;
        ImageView imageGifContainer, imageViewBackground;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);

            if (Rowlayout == R.layout.image_slider_layout_home) {
                imageViewBackground = itemView.findViewById(R.id.iv_auto_image_sliderr);
            } else if(Rowlayout == R.layout.image_slider_layout_item) {
                imageViewBackgroundtouch = itemView.findViewById(R.id.iv_auto_image_slider);
            }else   if (Rowlayout == R.layout.home_slider_layout){
                imageViewBackground = itemView.findViewById(R.id.iv_auto_image_sliderr);
            }
            //  imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }

    public interface OnItemClickedSlider {
        void onItemClickslide(int position, List<Banners> bnr);
    }

}

