package com.ellocartuser.home.homefragment.viewall;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.BannerResponce;
import com.ellocartuser.apiservices.Responce.CategoriesResponce;
import com.ellocartuser.apiservices.model.Banners;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.home.adapterandmodel.AllIndiaAdapter;
import com.ellocartuser.home.adapterandmodel.AllIndiaAdapterNew;
import com.ellocartuser.home.adapterandmodel.GridAdapterService;
import com.ellocartuser.home.adapterandmodel.SliderAdapter;
import com.ellocartuser.home.adapterandmodel.SliderModel;
import com.ellocartuser.home.adapterandmodel.SliderModelHome;
import com.ellocartuser.home.homefragment.CategoryFragment;
import com.ellocartuser.home.homefragment.NearbystoreFragment;
import com.ellocartuser.home.homefragment.ProductDetailedPage;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.utils.Util;
import com.jama.carouselview.CarouselViewListener;
import com.jama.carouselview.enums.IndicatorAnimationType;
import com.jama.carouselview.enums.OffsetType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllIndiaCatgoryViewAll#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllIndiaCatgoryViewAll extends Fragment implements AllIndiaAdapterNew.OnItemClickeGrid,SliderAdapter.OnItemClickedSlider {
    RecyclerView dataList;
    ImageView imageback;
    SliderAdapter.OnItemClickedSlider onclick;
    TextView current;
    AllIndiaAdapterNew.OnItemClickeGrid onItemClickeGrid;
    GridAdapterService gridadapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SliderView sliderView;
    private SliderAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllIndiaCatgoryViewAll() {
        // Required empty public constructor
    }
    List<Categories> gridedatacat;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocalCatgoryViewAll.
     */
    // TODO: Rename and change types and number of parameters
    public static AllIndiaCatgoryViewAll newInstance(String param1, String param2) {
        AllIndiaCatgoryViewAll fragment = new AllIndiaCatgoryViewAll();
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

        View view =inflater.inflate(R.layout.fragment_allindia_catgory_view_all, container, false);
        onclick=this;
        sliderView = view.findViewById(R.id.imageSlider);
        adapter = new SliderAdapter(getContext(), R.layout.image_slider_layout_home,null,onclick);
        dataList = view.findViewById(R.id.catList);
        imageback = view.findViewById(R.id.imageback);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        current = view.findViewById(R.id.current);
        current.setText("All India Categories");

          GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false);

          dataList.setLayoutManager(gridLayoutManager);
          onItemClickeGrid=this;

        if(mParam1!=null) {
            if (mParam1.equals("hide")) {
                sliderView.setVisibility(View.GONE);
            } else {
                sliderView.setVisibility(View.VISIBLE);
            }
        }else{
            sliderView.setVisibility(View.VISIBLE);

        }

        categoryApi();
        sliderApi();
        return  view;
    }

    public void categoryApi() {
        gridedatacat=new ArrayList<>();
        ProgressDialog  pd1 = new ProgressDialog(getActivity());
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(true);
        pd1.show();
        // pd1.show();
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        String lat = pref.getString("latitude","");
        String longi = pref.getString("longitude","");

        Call<CategoriesResponce> getCate = ApiClient.getApiService().getCategory(lat, longi, "10");
        getCate.enqueue(new Callback<CategoriesResponce>() {
            @Override
            public void onResponse(Call<CategoriesResponce> call, Response<CategoriesResponce> response) {
                final CategoriesResponce resource = response.body();

                pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus_2().equals("ok")) {
                    gridedatacat.clear();

                    if (getActivity() != null) {

                        if (resource.getCategories_2().size() != 0) {
                            AllIndiaAdapterNew allindiaadapter = new AllIndiaAdapterNew(getActivity(), resource.getCategories_2(), onItemClickeGrid, R.layout.homesingle_cat);
                            dataList.setAdapter(allindiaadapter);
                        } else {

                        }

                    } else {

                        if (resource.getMessage() != "") {
                            //   Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<CategoriesResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //     Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }


    private void sliderApi() {

        ProgressDialog  pd = new ProgressDialog(getContext());
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");
        String lat = pref.getString("latitude", "");
        String longi = pref.getString("longitude", "");
        // pd.show();
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<BannerResponce> getCate = ApiClient.getApiService().getBanners(id, lat, longi);
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

//
                    if (resource.getBanners2().size() != 0) {
                        sliderView.setVisibility(View.VISIBLE);

                        adapter.setEmpty();
                        for (int i = 0; i < resource.getBanners2().size(); i++) {
                            adapter.addItem(new SliderModelHome("", resource.getBanners2().get(i).getBanner_image(),resource.getBanners2()));

                        }
                        setSliderAdapter();
                    } else {
                        sliderView.setVisibility(View.GONE);
                    }
                }else    if (resource.getBanners2().size() != 0) {
                    sliderView.setVisibility(View.VISIBLE);

                    adapter.setEmpty();
                    for (int i = 0; i < resource.getBanners2().size(); i++) {
                        adapter.addItem(new SliderModelHome("", resource.getBanners2().get(i).getBanner_image(),resource.getBanners2()));

                    }
                    setSliderAdapter();
                } else {
                    sliderView.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<BannerResponce> call, Throwable t) {
                //   pd.dismiss();
                try {
                    pd.dismiss();
                    t.printStackTrace();
                    Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {

                }
            }
        });

    }

    private void setSliderAdapter() {

        sliderView.setSliderAdapter(adapter);
        // sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.YELLOW);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

    }
//
//    @Override
//    public void onItemClick(int position, String carid, String name) {
//
//        SharedPreferences  pref=getActivity()
//                .getSharedPreferences("user", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        //   editor.putString("user_id","346");
//        editor.putBoolean("loadhome",true);
//        editor.commit();
//
//        Util.loadFragment(NearbystoreFragment.newInstance(carid,name,"","",""),getActivity(), AllIndiaCatgoryViewAll.this);
//
//    }
//
//    private void setAdapterGrid() {
//
//        if(getContext()!=null) {
//
//         //   List<Categories> data=new ArrayList<>();
//
//            gridadapter = new GridAdapterService(getContext(), gridedatacat, onItemClickeGrid,R.layout.homecatsingle);
//            dataList.setAdapter(gridadapter);
//
//        }
//
//    }

    @Override
    public void onItemClickedtrendAllIndia(int position, String catid, String storename) {
        SharedPreferences pref = getActivity()
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("user_id","346");
        editor.putBoolean("loadhome", true);
        editor.commit();

        Util.loadFragment(NearbystoreFragment.newInstance(catid, storename, "", "", "1"), getActivity(), AllIndiaCatgoryViewAll.this);

    }

    @Override
    public void onItemClickslide(int position, List<Banners> bnr) {

        if (bnr.get(position).getBanner_page().equals("product")) {

            Util.loadFragment(new ProductDetailedPage().newInstance(bnr.get(position).getSeller_id(), bnr.get(position).getProduct_id()), getActivity(), AllIndiaCatgoryViewAll.this);

        } else if (bnr.get(position).getBanner_page().equals("seller")) {
            // Util.AlertWithOK(getActivity(), "pos " + position);
            Util.loadFragment(CategoryFragment.newInstance("", bnr.get(position).getSeller_id(), ""), getActivity(), AllIndiaCatgoryViewAll.this);
            //      Util.loadFragment(NearbystoreFragment.newInstance("",bnr.get(position).getBanner_name(),"",bnr.get(position).getSeller_id()), getActivity(),AllIndiaCatgoryViewAll.this);

        } else if (bnr.get(position).getBanner_page().equals("percentage")) {
            // Util.AlertWithOK(getActivity(), "pos " + position);

            Util.loadFragment(NearbystoreFragment.newInstance("", bnr.get(position).getBanner_name(), bnr.get(position).getBanner_id(), "", ""), getActivity(), AllIndiaCatgoryViewAll.this);

        } else if (bnr.get(position).getBanner_page().equals("all_in")) {
            // Util.AlertWithOK(getActivity(), "pos " + position);
            Util.loadFragment(AllIndiaCatgoryViewAll.newInstance("hide",""), getActivity(), AllIndiaCatgoryViewAll.this);

//                                        Util.loadFragment(NearbystoreFragment.newInstance("", bnr.get(position).getBanner_name(), bnr.get(position).getBanner_id(), "", ""), getActivity(), AllIndiaCatgoryViewAll.this);

        }
    }
}