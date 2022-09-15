package com.ellocartuser.orders;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ellocartuser.R;
import com.ellocartuser.home.adapterandmodel.SubAdapter;
import com.ellocartuser.utils.Util;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersMainClass#newInstance} factory method to
 * create an instance of this fragment.
 */

public class OrdersMainClass extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageView imageback;
    TabLayout tab_layout;
    OrdersTabAdapter subAdapter;
    private ViewPager screenPager;
    private List<String> names = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrdersMainClass() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdersMainClass.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdersMainClass newInstance(String param1, String param2) {
        OrdersMainClass fragment = new OrdersMainClass();
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

        View view = inflater.inflate(R.layout.fragment_orders_main_class, container, false);
        screenPager =view.findViewById(R.id.screen_viewpager);
        tab_layout = view.findViewById(R.id.tab_layout);
        imageback = view.findViewById(R.id.imageback);




        Util.PleaseLogin(getContext());


        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        names=new ArrayList<>();
        names.add("Pending");
        names.add("Completed");
        names.add("Cancelled");
        update();

        return view;
    }

    private void update() {
        subAdapter = new OrdersTabAdapter(getChildFragmentManager(),names);
        screenPager.setAdapter(subAdapter);
        tab_layout.setupWithViewPager(screenPager);
        tab_layout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab.getPosition() == mList.size()-1) {
//                    loaddLastScreen();
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });

    }
}