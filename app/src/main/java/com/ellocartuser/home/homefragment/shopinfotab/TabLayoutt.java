package com.ellocartuser.home.homefragment.shopinfotab;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CartResponce;
import com.ellocartuser.cart.CartDisplay;
import com.ellocartuser.home.homefragment.ProductsPage;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.utils.Util;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabLayoutt#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabLayoutt extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    String seller_id,cat_id,subcat_id,subcat_name;
    ImageView imageback;
    FrameLayout simpleFrameLayout;
    TabLayout tabLayout;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TabLayoutt() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabLayoutt.
     */
    // TODO: Rename and change types and number of parameters
    public static TabLayoutt newInstance(String param1, String param2,  String param3,String param4) {
        TabLayoutt fragment = new TabLayoutt();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }
   static public ImageView carticon;
    public static TextView cartcount,current;
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
        View view = inflater.inflate(R.layout.fragment_tab_layoutt, container, false);
        simpleFrameLayout =  view.findViewById(R.id.simpleFrameLayout);
        tabLayout =  view.findViewById(R.id.simpleTabLayout);

        current = view.findViewById(R.id.current);
        imageback = view.findViewById(R.id.imageback);
        cartcount = view.findViewById(R.id.cartcount);
        carticon = view.findViewById(R.id.imageback1);
        current.setText(subcat_name);
        carticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadFragment(new CartDisplay(),getActivity(),TabLayoutt.this);
            }
        });

        //  Util.PleaseLogin(getContext());

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        cartcount = view.findViewById(R.id.cartcount);
        carticon = view.findViewById(R.id.imageback1);

        carticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadFragment(new CartDisplay(),getActivity(),TabLayoutt.this);
            }
        });


        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("Products"); // set the Text for the first Tab
       // firstTab.setIcon(R.drawable.ic_launcher); // set an icon for the
        // first tab
        tabLayout.addTab(firstTab); // add  the tab at in the TabLayout
        // Create a new Tab named "Second"
        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Shop Info"); // set the Text for the second Tab
       // secondTab.setIcon(R.drawable.ic_launcher); // set an icon for the second tab
        tabLayout.addTab(secondTab);
        TabLayout.Tab thirdTab = tabLayout.newTab();
        thirdTab.setText("Shop Reviews"); // set the Text for the first Tab
     //   thirdTab.setIcon(R.drawable.ic_launcher); // set an icon for the first tab
        tabLayout.addTab(thirdTab);

        // perform setOnTabSelectedListener event on TabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
// get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new ProductsPage().newInstance(seller_id,cat_id,subcat_id);

                    break;
                    case 1:
                        fragment = new  ShopeInfo().newInstance(seller_id,"");
                        break;
                    case 2:
                        fragment = new ShopReviews().newInstance(seller_id,"");
                        break;
                }
                FragmentManager fm = getChildFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.simpleFrameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                //fm.remove(fragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
   //    tabLayout.getTabAt(0).select();

        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.simpleFrameLayout, new ProductsPage().newInstance(seller_id,cat_id,subcat_id));
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.commit();

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        tabLayout.getTabAt(0).select();
//        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        apiCallForCart(id,"get","","","","");

        if(getActivity()!=null){

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                    String id = pref.getString("user_id","");
                    if(!id.equals("") || id!=null){
                        apiCallForCart(id,"get","","","","");
                    }

                }
            });
        }
    }


     public static void apiCallForCart(String userid, String type, String product_id, String product_qty, String sproduct_id, String seller_id){

//        ProgressDialog pd1 = new ProgressDialog(getActivity());
//        pd1.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//         pd1.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//        pd1.setCancelable(false);
//        pd1.show();
        Call<CartResponce> getCate = ApiClient.getApiService().setCart(userid,type,product_id,product_qty,sproduct_id,seller_id);
        getCate.enqueue(new Callback<CartResponce>() {
            @Override
            public void onResponse(Call<CartResponce> call, Response<CartResponce> response) {
                final CartResponce resource = response.body();
             //   pd1.dismiss();

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    try {
                        if(String.valueOf(resource.getCartCount()).equals("0")){
                            cartcount.setText(String.valueOf(resource.getCartCount()));
                            HomeScreen.cartcount.setText(String.valueOf(resource.getCartCount()));


                        }else{

                            HomeScreen.cartcount.setText(String.valueOf(resource.getCartCount()));
                            cartcount.setText(String.valueOf(resource.getCartCount()));
                            cartcount.setVisibility(View.VISIBLE);

                        }
                        ProductsPage.updateCardDown(String.valueOf(resource.getCartTotal()),String.valueOf(resource.getCartCount()));
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }

                } else {
                    try {

                        if(String.valueOf(resource.getCartCount()).equals("0")){
                            cartcount.setText(String.valueOf(resource.getCartCount()));
                            cartcount.setVisibility(View.INVISIBLE);
                        }else{
                            cartcount.setText(String.valueOf(resource.getCartCount()));
                            cartcount.setVisibility(View.VISIBLE);

                        }
                        ProductsPage.updateCardDown("0","0");

                    }catch (Exception ex){
                    ex.printStackTrace();
                }
                }

            }

            @Override
            public void onFailure(Call<CartResponce> call, Throwable t) {
                //   pd.dismiss();
                //   pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        System.out.println("rajuuu ss "+hidden);
        if (hidden) {
            //do when hidden
        } else {
            //do when show
            SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            String id = pref.getString("user_id","");
            apiCallForCart(id,"get","","","","");

            tabLayout.getTabAt(0).select();
           Fragment fragment = new ProductsPage().newInstance(seller_id,cat_id,subcat_id);

            FragmentManager fm = getChildFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.simpleFrameLayout, fragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.commit();
        }
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        System.out.println("rajuuu "+isVisibleToUser);
//
//        if (isVisibleToUser) {
//            // Do some your work
//        } else {
            // Do your Work
//            SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//            String id = pref.getString("user_id","");
//            apiCallForCart(id,"get","","","","");
//
//        }
//    }

}