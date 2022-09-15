package com.ellocartuser.home.mainscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ellocartuser.R;
import com.ellocartuser.cart.CartDisplay;
import com.ellocartuser.home.homefragment.B2BMainPage;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.orders.OrdersMainClass;
import com.ellocartuser.servicesscreens.ServicesMainScreen;
import com.ellocartuser.setting.Account;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstHome newInstance(String param1, String param2) {
        FirstHome fragment = new FirstHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    BottomNavigationView bottomNav;

    @Override
    public void onStart() {
        super.onStart();

        if(getActivity()==null){
            return;
        }
//        SharedPreferences pref=getActivity()
//                .getSharedPreferences("user", Context.MODE_PRIVATE);
//        if(!pref.getBoolean("loadhome",true)) {
//            bottomNav.setSelectedItemId(R.id.home);
//
//
//            getChildFragmentManager()
//                    .beginTransaction()
//                    .replace(
//                            R.id.fragment_container,
//                            new homefragment()
//                            //   new MapsFragment()
//                    )
//                    .commit();
//        }else{
//
//        }
//
//        SharedPreferences.Editor editor = pref.edit();
//        //   editor.putString("user_id","346");
//        editor.putBoolean("loadhome",false);
//        editor.commit();

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
        View view = inflater.inflate(R.layout.fragment_first_home, container, false);

        bottomNav = view.findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

//      checkPermissionLocation();


            getChildFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.fragment_container,
                            new homefragment()
                            //   new MapsFragment()
                    )
                    .commit();

        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener
            navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(
                @NonNull MenuItem item) {
            //   checkPermissionLocation();
            // By using switch we can easily get
            // the selected fragment
            // by using there id.
            Fragment selectedFragment = null;

            switch (item.getItemId()) {

                case R.id.home:
                    // selectedFragment = new HomeFragment();
                    selectedFragment = new homefragment();

                    break;

               case R.id.service:
                    selectedFragment = new ServicesMainScreen();

                    break;


                case R.id.b2b:
                    selectedFragment = new B2BMainPage();

                    break;


//                case R.id.order:
//                    selectedFragment = new OrdersMainClass();
//
//                    break;
//                case R.id.cart:
//                    selectedFragment = new CartDisplay();
//
//                    //     selectedFragment =  new MyDelivery();
//                    break;
//                case R.id.account:
//                    selectedFragment = new Account();
//                    break;


            }
            if (selectedFragment == null) {
                selectedFragment = new homefragment();
            }
            // It will help to replace the one fragment to other.
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.fragment_container,
                            selectedFragment)
                    .commit();
            return true;
        }
    };

}