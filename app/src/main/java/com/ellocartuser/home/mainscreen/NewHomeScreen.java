package com.ellocartuser.home.mainscreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ellocartuser.R;
import com.jama.carouselview.CarouselScrollListener;
import com.jama.carouselview.CarouselView;
import com.jama.carouselview.CarouselViewListener;
import com.jama.carouselview.enums.IndicatorAnimationType;
import com.jama.carouselview.enums.OffsetType;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewHomeScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewHomeScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
        CarouselView carouselView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
  // List<inIntt> images = arrayListOf(R.drawable.boardwalk_by_the_ocean, R.drawable.journal_and_coffee_at_table, R.drawable.tying_down_tent_fly)
    public NewHomeScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewHomeScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static NewHomeScreen newInstance(String param1, String param2) {
        NewHomeScreen fragment = new NewHomeScreen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_new_home_screen, container, false);

        carouselView=view.findViewById(R.id.carouselView);

        carouselView.setSize(2);
        carouselView.setAutoPlay(true);
        carouselView.setAutoPlayDelay(3000);
        carouselView.setResource(R.layout.center_carousel_item);
        carouselView.setIndicatorAnimationType(IndicatorAnimationType.THIN_WORM);
        carouselView.setCarouselOffset(OffsetType.CENTER);

        carouselView.setCarouselViewListener(new CarouselViewListener() {
            @Override
            public void onBindView(View view, int position) {

                ImageView imageView = view.findViewById(R.id.imageView);
                if(position==0) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.bar));
                }else{
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.bar2));

                }

            }
        });
        carouselView.show();
//        setCarouselViewListener { view, position ->
//                val imageView = view.findViewById<ImageView>(R.id.imageView)
//                imageView.setImageDrawable(resources.getDrawable(images[position]))
//        }

        return view;
    }
}