package com.ellocartuser.servicesscreens;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.LocalStore;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.BannerResponce;
import com.ellocartuser.apiservices.Responce.CategoriesResponce;
import com.ellocartuser.apiservices.Responce.ServiceResponce;
import com.ellocartuser.apiservices.Responce.SubCatResponce;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.home.adapterandmodel.GridAdapter;
import com.ellocartuser.home.adapterandmodel.GridAdapterService;
import com.ellocartuser.home.adapterandmodel.PlaceAutoSuggestAdapter;
import com.ellocartuser.home.adapterandmodel.SliderAdapter;
import com.ellocartuser.home.adapterandmodel.SliderModel;
import com.ellocartuser.home.adapterandmodel.SliderModelHome;
import com.ellocartuser.home.homefragment.AdressLocationAdapter;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.servicesscreens.category.ServiceCategoryPage;
import com.ellocartuser.utils.GPSTracker;
import com.ellocartuser.utils.Util;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServicesMainScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesMainScreen extends Fragment implements ServiceMainScreenAdapter.OnItemClickeGrid,AdressLocationAdapter.OnItemClickedAdd  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<Categories> gridedata;
    // TODO: Rename and change types of parameters
    private String mParam1;
    AdressLocationAdapter.OnItemClickedAdd onclick;
    private String mParam2;
    LinearLayout noorder;
    AutoCompleteTextView autoCompleteTextView;
    RecyclerView  addresslist;
    Dialog dialoglocation;
    RecyclerView dataList;
    ServiceMainScreenAdapter.OnItemClickeGrid onItemClickeGrid;
    ProgressDialog pd1;
    ServiceMainScreenAdapter gridadapter;
    ImageView sidemenu;
    Button joinus;
    FloatingActionButton floatbtn;
    TextView current;
    SwipeRefreshLayout refresh;
    SliderView sliderView;
    private SliderAdapter adapter;
    public ServicesMainScreen() {
        // Required empty public constructor
    }
    SharedPreferences pref;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicesMainScreen.
     */
    // TODO: Rename and change types and number of parameters

    public static ServicesMainScreen newInstance(String param1, String param2) {
        ServicesMainScreen fragment = new ServicesMainScreen();
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

    private void update() {

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String lat = pref.getString("serv_latitude","");
        String longi = pref.getString("serv_longitude","");
        if(!pref.getString("typeservice","").equals("entered")) {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(getActivity(), Locale.getDefault());
            try {
//                if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
                if(!lat.equals("") && !longi.equals("")) {
                    addresses = geocoder.getFromLocation(Double.valueOf(lat), Double.valueOf(longi), 1);
                    if (addresses.size() != 0) {// Here 1 represent max location result to returned, by documents it recommended 1 to 5
                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        if (address != null) {
                            current.setText(address);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            current.setText(pref.getString("serv_currentloctext",""));
        }
        categoryApi();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services_main_screen, container, false);

        if(getActivity()==null){
            return view;
        }
         pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        onclick=this;

        noorder = view.findViewById(R.id.noorder);
        sliderView = view.findViewById(R.id.imageSlider);
        adapter = new SliderAdapter(getContext(),R.layout.home_slider_layout,null,null);

        current =view.findViewById(R.id.current);

        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(getActivity());
            }
        });
        refresh = view.findViewById(R.id.refresh);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                categoryApi();

                refresh.setRefreshing(false);

            }
        });
        refresh.setColorSchemeColors(getResources().getColor(R.color.yellow));

        dataList = view.findViewById(R.id.catList);
       // floatbtn = view.findViewById(R.id.floatbtn);
        joinus=view.findViewById(R.id.joinus);
        sidemenu = view.findViewById(R.id.sidemenu);
//
//        floatbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                apicallCheckUSersubscribe();
//               // ((HomeScreen)getActivity()).toggleDrawer();
//            }
//        });

        sidemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeScreen)getActivity()).toggleDrawer();
            }
        });

        joinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                apicallCheckUSersubscribe();

            }
        });



  //      sliderApi();

        return  view;

    }

    @Override
    public void onStart() {
        super.onStart();
        update();
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

        ProgressDialog   pd = new ProgressDialog(getContext());
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
        Call<BannerResponce> getCate = ApiClient.getApiService().getBanners("14","0","0");
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


    private void apicallcheckpost() {
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
        pd1.show();
//        pd1.show();
//        @Field("type") RequestBody type,

        SharedPreferences pref =getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<ServiceResponce> getCate = ApiClient.getApiServiceforservice().checkservice("my_posts",id);
        getCate.enqueue(new Callback<ServiceResponce>() {
            @Override
            public void onResponse(Call<ServiceResponce> call, Response<ServiceResponce> response) {
                final ServiceResponce resource = response.body();

                pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    // show my posts page

                    Intent subi = new Intent(getActivity(), MyServicesPageActivity.class);
                    startActivity(subi);


                } else {

                    // show crate post category page

                    Intent ii =  new Intent(getActivity(), ServiceCategoryPage.class);
                    startActivity(ii);

                }

            }

            @Override
            public void onFailure(Call<ServiceResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });
    }


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
        String lat = pref.getString("serv_latitude","");
        String longi = pref.getString("serv_longitude","");

        Call<CategoriesResponce> getCate = ApiClient.getApiServiceforservice().getHomeService("home",lat,longi);
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
                    if(resource.getServcat_status().equals("ok")) {
                        noorder.setVisibility(View.GONE);
                        refresh.setVisibility(View.VISIBLE);
                    }else{
                        noorder.setVisibility(View.VISIBLE);
                        refresh.setVisibility(View.GONE);
                    }

                    adapter.setEmpty();
                    for (int i = 0; i < resource.getBanners().size(); i++) {
                        adapter.addItem(new SliderModelHome("", resource.getBanners().get(i).getBanner_image(),null));

                    }
                    setSliderAdapter();
                }
                if (resource.getStatus().equals("ok")) {

                    for (int i = 0; i < resource.getCategories().size(); i++) {
                        gridedata.add(resource.getCategories().get(i));
                    }
                    setAdapterGrid();

                } else {

//                    if (resource.getMessage() != "") {
//                        //   Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<CategoriesResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(getContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void apicallCheckUSersubscribe() {
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
        pd1.show();
//        pd1.show();
//        @Field("type") RequestBody type,

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        if(id==null || id.equals("")){
            Util.PleaseLogin(getContext());
            return;
        }

//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<ServiceResponce> getCate = ApiClient.getApiServiceforservice().checkservice("checkservice",id);
        getCate.enqueue(new Callback<ServiceResponce>() {
            @Override
            public void onResponse(Call<ServiceResponce> call, Response<ServiceResponce> response) {
                final ServiceResponce resource = response.body();

                        pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {

                    apicallcheckpost();



                } else {

                    //share to subscription page

                    Intent subi = new Intent(getActivity(), SubscritionScreen.class);
                    getActivity().startActivity(subi);

//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }

                }

            }

            @Override
            public void onFailure(Call<ServiceResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void setAdapterGrid() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        //  dataList.setNestedScrollingEnabled(false);
        onItemClickeGrid=this;
        if(getContext()!=null) {
            gridadapter = new ServiceMainScreenAdapter(getContext(), gridedata, onItemClickeGrid);
            dataList.setAdapter(gridadapter);
        }
        //   gridadapter = new GridAdapter(getContext(),gridedata);

    }

    @Override
    public void onItemClick(int position, String carid, String name) {

        SharedPreferences  pref=getActivity()
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("user_id","346");
        editor.putBoolean("loadhome",true);
        editor.commit();

        Intent ii= new Intent(getActivity(),SubCategoryItems.class);
        ii.putExtra("catid",carid);
        ii.putExtra("name",name);
        startActivity(ii);

    }


    public void showDialog(Activity activity){

        dialoglocation= new Dialog(activity,android.R.style.Theme_Holo_Light_NoActionBar);
        dialoglocation.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialoglocation.setCancelable(true);
        dialoglocation.setContentView(R.layout.locationdialog);

        // ConstraintLayout currentloc = dialog.findViewById(R.id.constraintLayout2);
        ImageView close=dialoglocation.findViewById(R.id.imageView3);

        addresslist  = dialoglocation.findViewById(R.id.addresslist);
        addresslist.setLayoutManager(new LinearLayoutManager(getActivity()));

        com.ellocartuser.home.adapterandmodel.Address add=new com.ellocartuser.home.adapterandmodel.Address();
        List<com.ellocartuser.home.adapterandmodel.Address>  addlist = new ArrayList<>();
        String latitude="",longitude="";
        GPSTracker gps = new GPSTracker(getActivity());
        if (gps.canGetLocation()) {
            latitude = String.valueOf(gps.getLatitude());
            longitude = String.valueOf(gps.getLongitude());
//loci
//            SharedPreferences.Editor editor = pref.edit();
//            //   editor.putString("boy",resource.getBoy());
//            editor.putString("serv_latitude", latitude);
//            editor.putString("serv_longitude", longitude);
//            editor.putString("typeservice", "device");
//            editor.commit();
//            update();

            //            if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
            //
            //            }else{
            //
            //            }
        } else {

            ((HomeScreen) getActivity()).openLocationPopup();

        }
        List<Address> addresses=new ArrayList<>();
        add.setAddrName("Use Current Location");
        add.setAddrLat(latitude);
        add.setAddrLong(longitude);

        Geocoder geocoder;
       // List<Address> addresses;
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
//                if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
            if(!latitude.equals("") && !longitude.equals("")) {
                addresses = geocoder.getFromLocation(Double.valueOf(latitude), Double.valueOf(longitude), 1);
                if(addresses.size() != 0) { // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    if (address != null) {
                        add.setAddrAddress(address);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        addlist.add(add);

        AdressLocationAdapter adressAdapter1=new AdressLocationAdapter(getActivity(),addlist,onclick);
        addresslist.setAdapter(adressAdapter1);

        //apiCallForAddress();
        autoCompleteTextView = dialoglocation.findViewById(R.id.auto);
        autoCompleteTextView.setAdapter(new PlaceAutoSuggestAdapter(getContext(), android.R.layout.simple_list_item_1));
        autoCompleteTextView.setHint("Search location by village, city, state");
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ", autoCompleteTextView.getText().toString());
                LatLng latLng = getLatLngFromAddress(autoCompleteTextView.getText().toString());
//                LocalStore.newInstance("","").categoryApi(String.valueOf(latLng.latitude),String.valueOf(latLng.longitude),getActivity());
                //categoryApi(); //call after click location
                if (latLng != null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);

                    SharedPreferences.Editor editor = pref.edit();
                    //   editor.putString("boy",resource.getBoy());
                    editor.putString("serv_latitude", String.valueOf(latLng.latitude));
                    editor.putString("serv_longitude",String.valueOf(latLng.longitude));
                    editor.putString("typeservice","entered");
                    editor.putString("serv_currentloctext",autoCompleteTextView.getText().toString());
                    editor.commit();
                    current.setText(autoCompleteTextView.getText().toString());
                    update();

                    Address address = getAddressFromLatLng(latLng);
                    if (address != null) {

                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ", "" + address.getAddressLine(0));
                        Log.d("Phone : ", "" + address.getPhone());
                        Log.d("Pin Code : ", "" + address.getPostalCode());
                        Log.d("Feature : ", "" + address.getFeatureName());
                        Log.d("More : ", "" + address.getLocality());
                    } else {
                        Log.d("Adddress", "Address Not Found");
                    }
                } else {
                    Log.d("Lat Lng", "Lat Lng Not Found");
                }
                dialoglocation.dismiss();
            }
        });

//        currentloc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                GPSTracker gps = new GPSTracker(getActivity());
//                if (gps.canGetLocation()) {
//                    String latitude = String.valueOf(gps.getLatitude());
//                    String longitude = String.valueOf(gps.getLongitude());
//
//                    SharedPreferences.Editor editor = pref.edit();
//                    //   editor.putString("boy",resource.getBoy());
//                    editor.putString("latitude", latitude);
//                    editor.putString("longitude", longitude);
//                    editor.putString("type", "device");
//                    editor.commit();
//                    update();
//
//                    //            if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
//                    //
//                    //            }else{
//                    //
//                    //            }
//                } else {
//
//                    ((HomeScreen) getActivity()).openLocationPopup();
//
//                }
//                dialog.dismiss();
//            }
//        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialoglocation.dismiss();

            }
        });


        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialoglocation.show();

    }

    private LatLng getLatLngFromAddress(String address) {

        Geocoder geocoder = new Geocoder(getContext());
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(address, 1);
            if (addressList != null) {
                Address singleaddress = addressList.get(0);
                LatLng latLng = new LatLng(singleaddress.getLatitude(), singleaddress.getLongitude());
                return latLng;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private Address getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getContext());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);
            if (addresses != null) {
                Address address = addresses.get(0);
                return address;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void onItemClickedCart(int position, String mParam1, com.ellocartuser.home.adapterandmodel.Address address) {



        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("boy",resource.getBoy());
        editor.putString("serv_latitude", address.getAddrLat());
        editor.putString("serv_longitude", address.getAddrLong());
        editor.putString("typeservice","device");
//        editor.putString("type","entered");
//        editor.putString("currentloctext",address.getAddrAddress()+","+address.getAddrCity()+","+address.getAddrPincode());
        editor.commit();
        update();

        try{
            dialoglocation.dismiss();
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }
}