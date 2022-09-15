package com.ellocartuser.home.homefragment.shopinfotab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ellocartuser.R;
import com.ellocartuser.cart.CartDisplay;
import com.ellocartuser.utils.Util;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopTabMainClass#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ShopTabMainClass extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    ImageView imageback;
    TabLayout tab_layout;
    ShopTabAdapter subAdapter;
    private ViewPager screenPager;
    private List<String> names = new ArrayList<>();
    String seller_id,cat_id,subcat_id,subcat_name;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView carticon;
    TextView cartcount,current;
    public ShopTabMainClass() {
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

    public static ShopTabMainClass newInstance(String param1, String param2,String param3,String param4) {
        ShopTabMainClass fragment = new ShopTabMainClass();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            seller_id = getArguments().getString(ARG_PARAM1);
            cat_id = getArguments().getString(ARG_PARAM2);
            subcat_id = getArguments().getString(ARG_PARAM3);
            subcat_name = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_shop_main_class, container, false);
        screenPager =view.findViewById(R.id.screen_viewpager);
        tab_layout = view.findViewById(R.id.tab_layout);
        imageback = view.findViewById(R.id.imageback);
        current = view.findViewById(R.id.current);

        cartcount = view.findViewById(R.id.cartcount);
        carticon = view.findViewById(R.id.imageback1);
        current.setText(subcat_name);
        carticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadFragment(new CartDisplay(),getActivity(),ShopTabMainClass.this);
            }
        });

      //  Util.PleaseLogin(getContext());


        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        names=new ArrayList<>();
        names.add("Products");
        names.add("Shop Info");
        names.add("Shop Reviews");

        update();
        return view;
    }



    private void update() {
        screenPager.setOffscreenPageLimit(0);
        subAdapter = new ShopTabAdapter(getChildFragmentManager(),names,seller_id,cat_id,subcat_id);
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