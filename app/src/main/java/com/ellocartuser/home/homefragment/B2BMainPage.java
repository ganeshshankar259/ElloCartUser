package com.ellocartuser.home.homefragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.B2categoryResponce;
import com.ellocartuser.apiservices.Responce.BannerResponce;
import com.ellocartuser.apiservices.Responce.CategoriesResponce;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.home.adapterandmodel.B2category;
import com.ellocartuser.home.adapterandmodel.GridAdapter;
import com.ellocartuser.home.adapterandmodel.GridAdapter2;
import com.ellocartuser.home.adapterandmodel.SliderAdapter;
import com.ellocartuser.home.adapterandmodel.SliderModel;

import com.ellocartuser.home.adapterandmodel.SliderModelHome;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.setting.B2b;
import com.ellocartuser.utils.Util;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link B2BMainPage#newInstance} factory method to
 * create an instance of this fragment.
 */

public class B2BMainPage extends Fragment implements GridAdapter2.OnItemClickeGrid {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView dataList;
    ProgressDialog pd1;
    List<B2category> gridedata;
    LinearLayout noorder;
    GridAdapter2.OnItemClickeGrid onItemClickeGrid;
    ImageView sidemenu;
    GridAdapter2 gridadapter;
    SliderView sliderView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SliderAdapter adapter;
    SwipeRefreshLayout refresh;
    ImageView b2bbag;

    public B2BMainPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment B2BMainPage.
     */
    // TODO: Rename and change types and number of parameters
    public static B2BMainPage newInstance(String param1, String param2) {
        B2BMainPage fragment = new B2BMainPage();
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
    public void onResume() {
        super.onResume();

//        SharedPreferences  pref=getActivity()
//                .getSharedPreferences("user", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        //   editor.putString("user_id","346");
//        editor.putBoolean("loadhome",true);
//        editor.commit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b2_b_main_page, container, false);
        dataList = view.findViewById(R.id.catList);
        sidemenu = view.findViewById(R.id.sidemenu);
        noorder = view.findViewById(R.id.noorder);
        b2bbag = view.findViewById(R.id.b2bbag);

        if(getActivity()==null){
            return view;
        }

        b2bbag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences  pref=getActivity()
                        .getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                //   editor.putString("user_id","346");
                editor.putBoolean("loadhome",true);
                editor.commit();

                Util.loadFragment(new B2b(),getActivity(),B2BMainPage.this);
            }
        });
        refresh = view.findViewById(R.id.refresh);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

              //  sliderApi();
                categoryApi();

                refresh.setRefreshing(false);

            }
        });
        refresh.setColorSchemeColors(getResources().getColor(R.color.yellow));


        sliderView = view.findViewById(R.id.imageSlider);
        adapter = new SliderAdapter(getContext(),R.layout.home_slider_layout,null,null);

        sidemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeScreen)getActivity()).toggleDrawer();
            }
        });

          sliderApi();
        categoryApi();

        // Inflate the layout for this fragment
        return view;
    }


    private void setSliderAdapter() {
        sliderView.setSliderAdapter(adapter);
        // sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(2);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }

    private void sliderApi() {

        ProgressDialog       pd = new ProgressDialog(getContext());
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        // pd.show();
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<BannerResponce> getCate = ApiClient.getApiService().getBannersb2b("14");
        getCate.enqueue(new Callback<BannerResponce>() {
            @Override
            public void onResponse(Call<BannerResponce> call, Response<BannerResponce> response) {
                final BannerResponce resource = response.body();

                pd.dismiss();


                //    Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {
                    adapter.setEmpty();
                    for (int i = 0; i < resource.getBanners().size(); i++) {
                        adapter.addItem(new SliderModelHome("", resource.getBanners().get(i).getBanner_image(),null));


                    }
                    setSliderAdapter();
                } else {
                    if (resource.getMessage() != "") {
                        Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<BannerResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }



    private void categoryApi() {
        gridedata=new ArrayList<>();
        pd1 = new ProgressDialog(getContext());
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<B2categoryResponce> getCate = ApiClient.getApiService().getB2b();
        getCate.enqueue(new Callback<B2categoryResponce>() {
            @Override
            public void onResponse(Call<B2categoryResponce> call, Response<B2categoryResponce> response) {
                final B2categoryResponce resource = response.body();

                pd1.dismiss();
                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {

                    dataList.setVisibility(View.VISIBLE);
                    noorder.setVisibility(View.GONE);
                    for (int i = 0; i < resource.getB2categories().size(); i++) {
                        gridedata.add(resource.getB2categories().get(i));
                    }
                    setAdapterGrid();

                } else {
                    noorder.setVisibility(View.VISIBLE);
                    dataList.setVisibility(View.GONE);
//                    if (resource.get() != "") {
//                        Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

    }

            @Override
            public void onFailure(Call<B2categoryResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
           //     Toast.makeText(getContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemClick(int position,String catid,String name) {

                SharedPreferences  pref=getActivity()
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("user_id","346");
        editor.putBoolean("loadhome",true);
        editor.commit();

      //  Util.loadFragment(NearByStoreB2B.newInstance(catid,name),getActivity());

    }

    private void setAdapterGrid() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        //  dataList.setNestedScrollingEnabled(false);
        onItemClickeGrid=this;
        if(getContext()!=null) {
            gridadapter = new GridAdapter2(getContext(), gridedata, onItemClickeGrid);
            dataList.setAdapter(gridadapter);
        }
        //   gridadapter = new GridAdapter(getContext(),gridedata);

    }
}