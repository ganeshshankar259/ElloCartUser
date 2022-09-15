package com.ellocartuser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CategoriesResponce;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.home.adapterandmodel.GridAdapter;
import com.ellocartuser.home.adapterandmodel.GridAdapterService;
import com.ellocartuser.home.homefragment.NearbystoreFragment;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.servicesscreens.ServiceMainScreenAdapter;
import com.ellocartuser.utils.GPSTracker;
import com.ellocartuser.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocalStore#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocalStore extends Fragment implements GridAdapterService.OnItemClickeGrid{

    // normal   GridAdapterService  , for style = ServiceMainScreenAdapter
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    GridAdapterService gridadapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView dataList;
    ProgressDialog pd1;
    List<Categories> gridedata;
    // ServiceMainScreenAdapter.OnItemClickeGrid onItemClickeGrid;
    GridAdapterService.OnItemClickeGrid onItemClickeGrid;
    LinearLayout datalayout,noorder;
    public LocalStore() {
        // Required empty public constructor
    }
    SwipeRefreshLayout refresh;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocalStore.
     */
    // TODO: Rename and change types and number of parameters
    public static LocalStore newInstance(String param1, String param2) {
        LocalStore fragment = new LocalStore();
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
        View view = inflater.inflate(R.layout.fragment_local_store, container, false);
        dataList = view.findViewById(R.id.catList);
        noorder = view.findViewById(R.id.noorder);
        datalayout = view.findViewById(R.id.datalayout);



        refresh = view.findViewById(R.id.refresh);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                categoryApi();

                refresh.setRefreshing(false);

            }
        });
        refresh.setColorSchemeColors(getResources().getColor(R.color.yellow));

        categoryApi();


        // Inflate the layout for this fragment
        return view;
    }

//  public void  callApiwithLocation(){
//        GPSTracker gps = new GPSTracker(getActivity());
//        if (gps.canGetLocation()) {
//            String  latitude = String.valueOf(gps.getLatitude());
//            String longitude = String.valueOf(gps.getLongitude());
//            if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
//                categoryApi(latitude, longitude,getActivity());
//            }else{
////                pd1 = new ProgressDialog(getContext());
////                pd1.setMessage("get LocationData...");
////                //   pd.setProgressStyle(R.style.ProgressBar);
////                pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
////                 pd1.setIndeterminate(true);
   //     if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
     //       pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
       // }
////                pd1.setCancelable(false);
////                pd1.dismiss();
////                pd1.show();
////                callApiwithLocation();
//            }
//        }else{
//
//            ((HomeScreen)getActivity()).openLocationPopup();
//
//        }
//    }
    public void categoryApi() {
        gridedata=new ArrayList<>();
        pd1 = new ProgressDialog(getActivity());
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
                if (resource.getStatus().equals("ok")) {
                    noorder.setVisibility(View.GONE);
                    datalayout.setVisibility(View.VISIBLE);
                    for (int i = 0; i < resource.getCategories().size(); i++) {
                        gridedata.add(resource.getCategories().get(i));
                    }
                    setAdapterGrid();

                } else {
                    noorder.setVisibility(View.VISIBLE);
                    datalayout.setVisibility(View.GONE);
                    if (resource.getMessage() != "") {
                     //   Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
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

    @Override
    public void onItemClick(int position,String catid,String name) {

            Util.loadFragment(NearbystoreFragment.newInstance(catid,name,"","",""),getActivity(),LocalStore.this);

    }

    private void setAdapterGrid() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
      //  dataList.setNestedScrollingEnabled(false);
        onItemClickeGrid=this;
        if(getContext()!=null) {
            gridadapter = new GridAdapterService(getContext(), gridedata, onItemClickeGrid,R.layout.servicersingle);
            dataList.setAdapter(gridadapter);
        }
     //   gridadapter = new GridAdapter(getContext(),gridedata);

    }



}