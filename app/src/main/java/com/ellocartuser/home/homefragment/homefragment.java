package com.ellocartuser.home.homefragment;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.ellocartuser.AdaptersAndModel.RoomsAdapter;
import com.ellocartuser.AdaptersAndModel.RoomsModel;
import com.ellocartuser.R;
import com.ellocartuser.RoomsNew.RoomsListActivity;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.AddressResponce;
import com.ellocartuser.apiservices.Responce.BannerResponce;
import com.ellocartuser.apiservices.Responce.CartResponce;
import com.ellocartuser.apiservices.Responce.CategoriesResponce;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.apiservices.Responce.RegisterResponce;
import com.ellocartuser.apiservices.Responce.StoresCatResponce;
import com.ellocartuser.apiservices.model.Banners;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.cart.CartDisplay;
import com.ellocartuser.home.adapterandmodel.AllIndiaAdapter;
import com.ellocartuser.home.adapterandmodel.CatogeryNewAdapter;
import com.ellocartuser.home.adapterandmodel.GridAdapterService;
import com.ellocartuser.home.adapterandmodel.SliderAdapter;
import com.ellocartuser.home.adapterandmodel.OffersAdapter;
import com.ellocartuser.home.adapterandmodel.PlaceAutoSuggestAdapter;
import com.ellocartuser.home.adapterandmodel.ProductSearchAdapter;
import com.ellocartuser.home.adapterandmodel.ProductSearchList;
import com.ellocartuser.home.adapterandmodel.ServicesAdapter;
import com.ellocartuser.home.adapterandmodel.SliderModelHome;
import com.ellocartuser.home.adapterandmodel.SubAdapter;
import com.ellocartuser.home.adapterandmodel.TrendingAdapter;
import com.ellocartuser.home.homefragment.homescreen_down_section.SectionsHomeAdapterService;
import com.ellocartuser.home.homefragment.viewall.AllIndiaCatgoryViewAll;
import com.ellocartuser.home.homefragment.viewall.FeaturedBottomSheet;
import com.ellocartuser.home.homefragment.viewall.LocalCatgoryViewAll;
import com.ellocartuser.home.homefragment.viewall.OffersViewAll;
import com.ellocartuser.home.homefragment.viewall.ServiceBottomSheet;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.home.notification.NotificationScreen;
import com.ellocartuser.orders.OrdersMainClass;
import com.ellocartuser.rooms_old.ModelsandResponces.Roomcategories_M;
import com.ellocartuser.rooms_old.adapters.Rcategories_Adapter;
import com.ellocartuser.servicesscreens.SubCategoryItems;
import com.ellocartuser.setting.Account;
import com.ellocartuser.tutions.Tutions_Base;
import com.ellocartuser.utils.Constant;
import com.ellocartuser.utils.CustomItemFive;
import com.ellocartuser.utils.GPSTracker;
import com.ellocartuser.utils.Util;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.appbar.AppBarLayout;
import com.jama.carouselview.CarouselView;
import com.jama.carouselview.CarouselViewListener;
import com.jama.carouselview.enums.IndicatorAnimationType;
import com.jama.carouselview.enums.OffsetType;
import com.onesignal.OSDeviceState;
import com.onesignal.OneSignal;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homefragment extends Fragment implements SliderAdapter.OnItemClickedSlider,SectionsHomeAdapterService.OnItemClickeGrid, OffersAdapter.OnItemClickeGrid, AllIndiaAdapter.OnItemClickeGrid, ServicesAdapter.OnItemClickeGrid, TrendingAdapter.OnItemClickeGrid, GridAdapterService.OnItemClickeGrid, Rcategories_Adapter.onItemClickeRoom, ProductSearchAdapter.OnItemClickedProductsearch, AdressLocationAdapter.OnItemClickedAdd,RecentLocationAdapter.OnItemClickedAdd, AppBarLayout.OnOffsetChangedListener ,
CatogeryNewAdapter.OnItemClickeGridNew{

    TextView recentsearch,myaddress;//
    SliderAdapter.OnItemClickedSlider sliderclick;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "scroll";
    SectionsHomeAdapterService.OnItemClickeGrid onclicksection;
    AllIndiaAdapter.OnItemClickeGrid onclickallindia;
    OffersAdapter.OnItemClickeGrid onclickoffer;
    SliderView sliderView,imageSliderservice,carouselView,carouselViewNew;
    ShimmerFrameLayout shimmerlayout;
    LinearLayout mainlayoutstart;
    ImageView santhaimg, explore_img;
    ConstraintLayout floatingbutton;
    TextView ordertext, ordertextheading,tv_LocalViewAll;
    ProgressDialog pd, pd1;
    PackageInfo pInfo;
    AllIndiaAdapter allindiaadapter;
    LinearLayout nosearchdata = null;
    ImageView arroeexplore, closeordercard;
    TextView storeviewall, featuredviewall, subexplorelocal, explorelocal, subexploreindia, exploreindia   ,localcategories,kms;
    ImageView catshopviewall, serviceviewall, shopviewall;
    ConstraintLayout roomstrip,servicestrip, categorydownstrip, offerstrip, constraintlocal, constraintallindia;
    //  SliderView sliderView;
    //  GridAdapterService.OnItemClickeGrid onItemClickeGrid;
    GridAdapterService gridadapter;
    CatogeryNewAdapter catogeryadapter;
    Rcategories_Adapter rcategoriesadapter;
    TrendingAdapter.OnItemClickeGrid ontrend;
    //  GridAdapter gridadapter;
    AdressLocationAdapter.OnItemClickedAdd onclick;
    RecentLocationAdapter.OnItemClickedAdd onclickrecent;
    SubAdapter subAdapter;
    Dialog dialoglocation;
    ImageView sidemenu, imageView, locationicon;
    TextView cartcount, notificationcount;
    RecyclerView offerslist, services, catList, storewiselist, allindiacatList ,newrcCatogery;
    ServicesAdapter.OnItemClickeGrid onItemClickeService;
    ImageView imgdownicon;
    LinearLayout noorder;   //,showmorelayout;
    SwipeRefreshLayout nestedcatdata;
    SharedPreferences pref,pref1;
    AutoCompleteTextView autoCompleteTextView;
    ProductSearchAdapter.OnItemClickedProductsearch productsearchlistioner;
    //    AutoCompleteAdapter adapter;
    RecyclerView addresslist,recentlist;
    TextView responseView;
    PlacesClient placesClient;
    List<Categories> gridedatacat, gridedatatrend, gridedataserv;
    List<Roomcategories_M> roomcatogorieslist;
    TextView current,tvNewCurren;
    GridAdapterService.OnItemClickeGrid onItemClickeGrid;
    CatogeryNewAdapter.OnItemClickeGridNew onItemClickeGridNew;
    Rcategories_Adapter.onItemClickeRoom onItemClickeRoom;

    RecyclerView recyclerViewproductsearch;
    ProductSearchAdapter productsearchadapter;
    Dialog dialog;
    ImageView notification,allcatshopviewall,imgNotifiy;
    // SwipeRefreshLayout refresh;
    ImageView carticon;
  //  CarouselView carouselView, carouselView1;
    private SliderAdapter adapter,adapter_carosal;
    private Rcategories_Adapter adapterrooms;
    private SliderAdapter adapterservice;
    private List<String> names = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    // RecyclerView dataList;
    private AppBarLayout appBarLayout;
    ImageView backtotop;
    CircleImageView profilemenu,CIVprofile;
    public homefragment() {
        // Required empty public constructor
    }



    //recyclerview
    ImageView rooms_reload;
    AutoCompleteTextView autocomplete;
    ImageView txt_no_data;
    ProgressBar progressBar_rooms;
    RecyclerView recyclerView_rooms;
    private RoomsAdapter roomsAdapter;
    private RecyclerView.LayoutManager layoutManagertrending;
    private ArrayList<RoomsModel> home_data_list;
    String final_lat;
    String final_lng;


    // TODO: Rename and change types and number of parameters
    public static homefragment newInstance(String param1, String param2) {
        homefragment fragment = new homefragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static void slideView(View view,
                                 int currentHeight,
                                 int newHeight) {

//        ValueAnimator slideAnimator = ValueAnimator
//                .ofInt(currentHeight, newHeight)
//                .setDuration(2000);
//
//        /* We use an update listener which listens to each tick
//         * and manually updates the height of the view  */
//
//        slideAnimator.addUpdateListener(animation1 -> {
//            Integer value = (Integer) animation1.getAnimatedValue();
//            view.getLayoutParams().height = value.intValue();
//            view.requestLayout();
//        });
//
//        /*  We use an animationSet to play the animation  */
//
//        AnimatorSet animationSet = new AnimatorSet();
//        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
//        animationSet.play(slideAnimator);
//        animationSet.start();

// set the values we want to animate between and how long it takes
// to run
        ValueAnimator slideAnimator = ValueAnimator
                .ofInt(currentHeight, newHeight)
                .setDuration(400);

// we want to manually handle how each tick is handled so add a
// listener
        slideAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // get the value the interpolator is at
                Integer value = (Integer) animation.getAnimatedValue();
                // I'm going to set the layout's height 1:1 to the tick
                try {
                    view.getLayoutParams().height = value.intValue();
                    view.getLayoutParams().width = value.intValue();
                    view.requestLayout();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

// create a new animationset
        AnimatorSet set = new AnimatorSet();
// since this is the only animation we are going to run we just use
// play
        set.play(slideAnimator);
// this is how you set the parabola which controls acceleration
        set.setInterpolator(new AccelerateDecelerateInterpolator());
// start the animation
        set.start();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

//    private void apicallFOrNotification() {
//
//        pd1 = new ProgressDialog(getActivity());
//        pd1.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//         pd1.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//        pd1.setCancelable(false);
//        pd1.show();
//        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        Call<B2BResponce> getCate = ApiClient.getApiService().getNotifications("user",id);
//        getCate.enqueue(new Callback<B2BResponce>() {
//            @Override
//            public void onResponse(Call<B2BResponce> call, Response<B2BResponce> response) {
//                final B2BResponce resource = response.body();
//
//                pd1.dismiss();
////                Log.d("responce notification", resource.toString());
//                if (resource == null) {
//                    return;
//                }
//                if (resource.getStatus().equals("ok")) {
//
//                    notificationcount.setText(String.valueOf(resource.getNotifys().size()));
////
//                } else {
//
//                    notificationcount.setText("0");
//
//                }
//
//            }
//            @Override
//            public void onFailure(Call<B2BResponce> call, Throwable t) {
//                //   pd.dismiss();
//                pd1.dismiss();
//                t.printStackTrace();
//                //     Toast.makeText(getContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

    @Override
    public void onResume() {
        super.onResume();

        if(current.getText().equals("Click Here To Get Location Data")){
            ((HomeScreen) getActivity()).openLocationPopup();
        }
        if(tvNewCurren.getText().equals("Click Here To Get Location Data")){
            ((HomeScreen) getActivity()).openLocationPopup();
        }

//        SharedPreferences  pref=getActivity()
//                .getSharedPreferences("user", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        //   editor.putString("user_id","346");
//        editor.putBoolean("loadhome",true);
//        editor.commit();
//        appBarLayout.addOnOffsetChangedListener(this);
//        if(getActivity()!=null){
//
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//                    String id = pref.getString("user_id","");
//                    if(!id.equals("") || id!=null){
//                        apiCallForCart(id,"get","","","","");
//                      //   apicallFOrNotification();
//                    }
//
//                }
//            });

    }

    @Override
    public void onPause() {
        super.onPause();
        //  appBarLayout.removeOnOffsetChangedListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        //    OneSignal.setNotificationOpenedHandler(new ExampleNotificationOpenedHandler(getActivity()));
        OSDeviceState device = OneSignal.getDeviceState();

//        String email = device.getEmailAddress();
//        String emailId = device.getEmailUserId();
//        String pushToken = device.getPushToken();

        try {
            String userId = device.getUserId();
            System.out.println("raj tocken " + userId);
            if (userId != null) {
                boolean enabled = device.areNotificationsEnabled();
                boolean subscribed = device.isSubscribed();
                boolean pushDisabled = device.isPushDisabled();
                SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                String id = pref.getString("user_id", "");
                if (id != null && !id.equals("")) {
                    updateTocken(userId);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        update();
    }

    private void updateTocken(String pushToken) {

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");
        String phnnum = pref.getString("user_phone", "");
        String lat = pref.getString("latitude", "");
        String longi = pref.getString("longitude", "");

        try {
            pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo.versionName;

//        @Field("user_phone") String user_phone,
//        @Field("user_phone_code") String user_phone_code,
//        @Field("user_id") String user_id,
//        @Field("user_token") String user_token,
//        @Field("user_device") String user_device,
//        @Field("user_lat") String user_lat,
//        @Field("user_long") String user_long

        Call<RegisterResponce> getCate = ApiClient.getApiService().updateToken(phnnum, "+91", id, pushToken, "ANDROID", version, lat, longi);
        getCate.enqueue(new Callback<RegisterResponce>() {
            @Override
            public void onResponse(Call<RegisterResponce> call, Response<RegisterResponce> response) {
                final RegisterResponce resource = response.body();

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {

                    if(resource.getNotify_count().equals("0")){
                        notificationcount.setText(resource.getNotify_count());
                        notificationcount.setVisibility(View.INVISIBLE);
                    }else{
                        notificationcount.setText(resource.getNotify_count());
                        notificationcount.setVisibility(View.VISIBLE);
                    }


                    //     Toast.makeText(OtpScreen.this, resource.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    //  otpNumberOne.clearValue();
                    notificationcount.setText("0");
                    if (resource.getMessage() != "") {

                        //   Toast.makeText(OtpScreen.this, resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<RegisterResponce> call, Throwable t) {
                //   pd.dismiss();
                t.printStackTrace();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.homefragment, container, false);
        if (getActivity() == null) {
            return view;
        }

        ontrend = this;
        onItemClickeService = this;
        onItemClickeGrid = this;
        onItemClickeRoom = this;
        onclickallindia = this;
        onclickoffer = this;
        sliderclick = this;
        onclicksection = this;
        onItemClickeGridNew=this;


//      santhaimg = view.findViewById(R.id.textView61); //allshop
        profilemenu = view.findViewById(R.id.profilemenu);
        CIVprofile = view.findViewById(R.id.CIVprofile);
        allcatshopviewall = view.findViewById(R.id.allcatshopviewall);
        subexploreindia = view.findViewById(R.id.subexploreindia);
        exploreindia = view.findViewById(R.id.exploreindia);
        subexplorelocal = view.findViewById(R.id.subexplorelocal);
        explorelocal = view.findViewById(R.id.explorelocal);
        localcategories = view.findViewById(R.id.localcategories);
        kms = view.findViewById(R.id.kms);

        // explore_img = view.findViewById(R.id.textView6);
//        Glide.with(getActivity())
//                .load("https://www.ellocart.com/demo/bg-in.png")
//                .fitCenter()//.placeholder(R.drawable.placeholderello)
//                .into(santhaimg);
//
//        Glide.with(getActivity())
//                .load("https://www.ellocart.com/demo/bg-cat.png")
//                .fitCenter()//.placeholder(R.drawable.placeholderello)
//                .into(explore_img);

        rooms_reload=view.findViewById(R.id.rooms_reload);
        autocomplete=view.findViewById(R.id.autocomplete);
        autocomplete_data();
        txt_no_data = view.findViewById(R.id.txt_no_data);
        progressBar_rooms = view.findViewById(R.id.progressBar_rooms);
        recyclerView_rooms = view.findViewById(R.id.recyclerView_rooms);
        recyclerView_rooms.setNestedScrollingEnabled(false);
        recyclerView_rooms.setHasFixedSize(true);
        layoutManagertrending = new LinearLayoutManager(getActivity());
        recyclerView_rooms.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        home_data_list = new ArrayList<>();


        mainlayoutstart = view.findViewById(R.id.mainlayoutstart);
        shimmerlayout = view.findViewById(R.id.shimmerLoadingView);
        catshopviewall = view.findViewById(R.id.catshopviewall);
        tv_LocalViewAll = view.findViewById(R.id.tv_LocalViewAll);
        allindiacatList = view.findViewById(R.id.allindiacatList);
        closeordercard = view.findViewById(R.id.closeordercard);
        ordertextheading = view.findViewById(R.id.textView18);
        ordertext = view.findViewById(R.id.ordertext);
        floatingbutton = view.findViewById(R.id.fab1);

        //   showmorelayout=view.findViewById(R.id.showmorelayout);
        backtotop = view.findViewById(R.id.backtotop);
        arroeexplore = view.findViewById(R.id.imageView14);
        noorder = view.findViewById(R.id.noorder);
        nestedcatdata = view.findViewById(R.id.nestedcatdata);
        offerslist = view.findViewById(R.id.offerslist);
        services = view.findViewById(R.id.services);
        roomstrip = view.findViewById(R.id.roomstrip);

        catList = view.findViewById(R.id.catList);
        newrcCatogery = view.findViewById(R.id.rcCatogery);
        storewiselist = view.findViewById(R.id.storewiselist);
        featuredviewall = view.findViewById(R.id.featuredviewall);
        constraintlocal = view.findViewById(R.id.constraintlocal);
        constraintallindia = view.findViewById(R.id.constraintallindia);
        //  imgdownicon=view.findViewById(R.id.imageView9);
        sliderView = view.findViewById(R.id.imageSlider);
        imageSliderservice = view.findViewById(R.id.imageSliderservice);
        adapter = new SliderAdapter(getContext(), R.layout.image_slider_layout_home,null,sliderclick);
        adapter_carosal = new SliderAdapter(getContext(), R.layout.image_slider_layout_home,null,sliderclick);
        adapterservice = new SliderAdapter(getContext(), R.layout.image_slider_layout_home,null,sliderclick);


        allindiacatList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        offerslist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        services.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        catList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        newrcCatogery.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


//          GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
//        offerslist.setLayoutManager(gridLayoutManager);
        //  catList.setLayoutManager(gridLayoutManager);
//        catList.setNestedScrollingEnabled(true);

        newrcCatogery.setNestedScrollingEnabled(true);


        offerslist.setNestedScrollingEnabled(true);
        services.setNestedScrollingEnabled(true);
        storewiselist.setNestedScrollingEnabled(true);
        //  catList.setLayoutManager(new GridLayoutManager(getContext(), 4));
        //   catList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        storewiselist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        try {
            offerslist.setHasFixedSize(true);
            offerslist.setItemAnimator(null);
            offerslist.setItemViewCacheSize(50);

            allindiacatList.setHasFixedSize(true);
            allindiacatList.setItemAnimator(null);
            allindiacatList.setItemViewCacheSize(50);

            services.setHasFixedSize(true);
            services.setItemAnimator(null);
            services.setItemViewCacheSize(50);


            catList.setHasFixedSize(true);
            catList.setItemAnimator(null);
            catList.setItemViewCacheSize(50);

            newrcCatogery.setHasFixedSize(true);
            newrcCatogery.setItemAnimator(null);
            newrcCatogery.setItemViewCacheSize(50);

            storewiselist.setHasFixedSize(true);
            storewiselist.setItemAnimator(null);
            storewiselist.setItemViewCacheSize(50);
        } catch (Exception ex) {

        }
        serviceviewall = view.findViewById(R.id.serviceviewall);
        //  storeviewall=view.findViewById(R.id.storeviewall);
        shopviewall = view.findViewById(R.id.shopviewall);
        carouselView = view.findViewById(R.id.carouselView);
        carouselViewNew = view.findViewById(R.id.carouselViewNew);
//        carouselView1 = view.findViewById(R.id.carouselView1);
//        carouselView1.hideIndicator(true);
//        carouselView.hideIndicator(true);
        onclick = this;
        onclickrecent = this;
        pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        cartcount = view.findViewById(R.id.cartcount);
        notificationcount = view.findViewById(R.id.notificationcount);
        carticon = view.findViewById(R.id.imageback1);
        // sliderView = view.findViewById(R.id.imageSlider);

        // dataList = view.findViewById(R.id.catList);
        offerstrip = view.findViewById(R.id.offerstrip);
        categorydownstrip = view.findViewById(R.id.categorydownstrip);
        servicestrip = view.findViewById(R.id.servicestrip);
        imageView = view.findViewById(R.id.imageView);
        sidemenu = view.findViewById(R.id.sidemenu);
        notification = view.findViewById(R.id.notification);
        imgNotifiy = view.findViewById(R.id.imgNotifiy);
        EditText auto = view.findViewById(R.id.auto);
        AppCompatEditText edAuto = view.findViewById(R.id.edAuto);
        locationicon = view.findViewById(R.id.imageback);
        //    appBarLayout = view.findViewById(R.id.app_bar_head);
        productsearchlistioner = this;
        //adapter = new SliderAdapter(getContext(),R.layout.home_slider_layout);
        NestedScrollView scroller = view.findViewById(R.id.myScroll);
        backtotop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scroller.fullScroll(View.FOCUS_UP);
            }
        });
        backtotop.setVisibility(View.INVISIBLE);
        if (scroller != null) {

            scroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    if (scrollY > oldScrollY) {
                        Log.i(TAG, "Scroll DOWN");
                        if(nestedcatdata.getVisibility()==View.VISIBLE) {
                            backtotop.setVisibility(View.VISIBLE);
                        }else{
                            backtotop.setVisibility(View.INVISIBLE);
                        }

                    }

                    if (scrollY < oldScrollY) {
                        Log.i(TAG, "Scroll UP");
                        backtotop.setVisibility(View.INVISIBLE);
                    }

                    if (scrollY == 0) {
                        Log.i(TAG, "TOP SCROLL");
                        backtotop.setVisibility(View.INVISIBLE);

                    }

                    if (scrollY == ( v.getMeasuredHeight() - v.getChildAt(0).getMeasuredHeight() )) {
                        Log.i(TAG, "BOTTOM SCROLL");
                    }
                }
            });
        }

        profilemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadFragment(new Account(), getActivity(), homefragment.this);
            }
        });
        CIVprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadFragment(new Account(), getActivity(), homefragment.this);
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    Util.loadFragment(new NotificationFragment(),getActivity());
                Intent ii = new Intent(getActivity(), NotificationScreen.class);
                startActivity(ii);
            }
        });
        imgNotifiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    Util.loadFragment(new NotificationFragment(),getActivity());
                Intent ii = new Intent(getActivity(), NotificationScreen.class);
                startActivity(ii);
            }
        });

        sidemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                //((HomeScreen) getActivity()).toggleDrawer();

                showDialog(getActivity());
//                Intent ii =new Intent(getActivity(), Account.class);
//                startActivity(ii);

            }
        });

//        auto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDialogSearch(getActivity());
//            }
//        });
        edAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogSearch(getActivity());
            }
        });

        floatingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadFragment(new OrdersMainClass(), getActivity(), homefragment.this);
            }
        });

        allcatshopviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Util.loadFragment(new OrdersMainClass(), getActivity(), homefragment.this);
                Util.loadFragment(AllIndiaCatgoryViewAll.newInstance("hide",""), getActivity(), homefragment.this);

            }
        });

        closeordercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingbutton.setVisibility(View.GONE);
                SharedPreferences.Editor editor = pref.edit();
                //   editor.putString("boy",resource.getBoy());
                editor.putString("pendorder", "nodisplay");
                editor.commit();

            }
        });
//        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
//            @Override
//            public void onIndicatorClicked(int position) {
//                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
//            }
//        });

        carticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadFragment(new CartDisplay(), getActivity(), homefragment.this);
            }
        });

        // sliderApi();
        apiCallDetail();

        shopviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadFragment(new OffersViewAll(), getActivity(), homefragment.this);


//                bottomSheet.show(getChildFragmentManager(),
//                        "ModalBottomSheet");
            }
        });

//        catshopviewall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // ShopsBottomSheet bottomSheet = ;
//                Util.loadFragment(new LocalCatgoryViewAll(), getActivity(), homefragment.this);
//
////                bottomSheet.show(getChildFragmentManager(),
////                        "ModalBottomSheet");
//            }
//        });
        tv_LocalViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ShopsBottomSheet bottomSheet = ;
                Util.loadFragment(new LocalCatgoryViewAll(), getActivity(), homefragment.this);

//                bottomSheet.show(getChildFragmentManager(),
//                        "ModalBottomSheet");
            }
        });

        serviceviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceBottomSheet bottomSheet = new ServiceBottomSheet();
                Util.loadFragment(bottomSheet, getActivity(), homefragment.this);

//                bottomSheet.show(getChildFragmentManager(),
//                        "ModalBottomSheet");
            }
        });


       ImageView rooms_reload = view.findViewById(R.id.rooms_reload);
        rooms_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        featuredviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FeaturedBottomSheet bottomSheet = new FeaturedBottomSheet();
                Util.loadFragment(bottomSheet, getActivity(), homefragment.this);

            }
        });

        nestedcatdata.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                apiCallDetail();

                nestedcatdata.setRefreshing(false);

            }
        });


        current = view.findViewById(R.id.current);
        tvNewCurren = view.findViewById(R.id.tvNewCurren);

        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(getActivity());
            }
        });

        tvNewCurren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(getActivity());
            }
        });

        rooms_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                String id = pref.getString("user_id", "");
                String lat = pref.getString("latitude", "");
                String longi = pref.getString("longitude", "");
                autocomplete.getText().clear();
                new_rooms(lat,longi);
            }
        });

        return view;

    }

    private void autocomplete_data() {
        autocomplete.setAdapter(new PlaceAutoSuggestAdapter(getActivity(),android.R.layout.simple_list_item_1));
        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hideKeyboard();
                Log.e(TAG, "onItemClick:Address:: "+autocomplete.getText().toString());
                LatLng latLng=getLatLngFromAddress(autocomplete.getText().toString());
                    if(latLng!=null) {
                        String latitude_c = latLng.latitude+"";
                        String longitude_c = latLng.longitude+"";
                        new_rooms(latitude_c,longitude_c);
                    }
                    else {
                        Log.e(TAG, "Lat LngonItemClick: "+"Lat Lng Not Found" );
                        txt_no_data.setVisibility(View.VISIBLE);
                    }
            }
        });
    }

    private void hideKeyboard() {
        // Hide keyboard...
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(autocomplete.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    private void apiCallProfile() {
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        // pd.show();
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<MyProfileResponce> getCate = ApiClient.getApiService().getProgile("user",id);
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                pd.dismiss();
                //  Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){
//
//                    name.setText(resource.getProfile().get(0).getUserName());
//                    //  address.setText(resource.getProfile().get(0).get());
//                    phone.setText(resource.getProfile().get(0).getUserPhone());
//                    email.setText(resource.getProfile().get(0).getUser_email());

                    if(resource.getProfile().get(0).getUserImage()!=null) {

                        if(getActivity()!=null) {

                            if(!resource.getProfile().get(0).getUserImage().equals("")) {

                                Glide.with(getActivity())
                                        .load(resource.getProfile().get(0).getUserImage())
                                        .fitCenter()
                                        .into(profilemenu);
                                Glide.with(getActivity())
                                        .load(resource.getProfile().get(0).getUserImage())
                                        .fitCenter()
                                        .into(CIVprofile);

                            }

                        }
//                        Glide.with(getActivity())
//                                .load(resource.getProfile().get(0).getUserImage())
//                                .fitCenter()
//                                .placeholder(R.drawable.placeholderello).into(imgselect);

                    }
                }else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<MyProfileResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                if(getActivity()!=null) {
                    Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void apiCallDetail() {
//if(carouselView!=null) {
//    carouselView.setAutoPlay(false);
//}
        allEmpty();
        categoryApi();
        //featuredApi();
      //  sliderApi();

        apiCallProfile();

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");
        apiCallForCart(id, "get", "", "", "", "");
        String lat = pref.getString("latitude", "");
        String longi = pref.getString("longitude", "");

        // trendingApi();
      //  servicecategoryApi();


       // new_rooms(lat,longi);


        //offer
//        OffersAdapter gdapter = new OffersAdapter(getContext());
//        offerslist.setAdapter(gdapter);

        //

    }

//    public void trendingApi() {
//        gridedatatrend=new ArrayList<>();
//        pd1 = new ProgressDialog(getActivity());
//        pd1.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//         pd1.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//
//
//        pd1.setCancelable(false);
//      //  pd1.show();
//
////        @Field("type") RequestBody type,
////        @Field("boy_phone") RequestBody boy_phone,
////        @Field("boy_phone_code") RequestBody boy_phone_code
//
//        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        String lat = pref.getString("latitude","");
//        String longi = pref.getString("longitude","");
//
//        Call<CategoriesResponce> getCate = ApiClient.getApiService().getStoreTrending(lat, longi);
//        getCate.enqueue(new Callback<CategoriesResponce>() {
//            @Override
//            public void onResponse(Call<CategoriesResponce> call, Response<CategoriesResponce> response) {
//                final CategoriesResponce resource = response.body();
//
//                pd1.dismiss();
//
//                // Log.d("resss", resource.toString());
//
//                if (resource == null) {
//                    return;
//                }
//                if (resource.getStatus().equals("ok")) {
////                    noorder.setVisibility(View.GONE);
////                    datalayout.setVisibility(View.VISIBLE);
//
//                    offerstrip.setVisibility(View.VISIBLE);
//                    offerstrip.setVisibility(View.VISIBLE);
//
//                    if(getContext()!=null) {
//                       TrendingAdapter gdapter = new TrendingAdapter(getContext(), resource.getStores(), ontrend,R.layout.trending_single);
//                        offerslist.setAdapter(gdapter);
//                    }
//
//                } else {
//
//                    offerstrip.setVisibility(View.GONE);
//                    offerstrip.setVisibility(View.GONE);
//                    if(getContext()!=null) {
//                        TrendingAdapter gdapter = new TrendingAdapter(getContext(), null, ontrend,R.layout.trending_single);
//                        offerslist.setAdapter(gdapter);
//                    }
//
////                    noorder.setVisibility(View.VISIBLE);
////                    datalayout.setVisibility(View.GONE);
//                    if (resource.getMessage() != "") {
//                        //   Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<CategoriesResponce> call, Throwable t) {
//                //   pd.dismiss();
//                pd1.dismiss();
//                t.printStackTrace();
//                //     Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

//    public void featuredApi() {
//       // gridedatafeatu=new ArrayList<>();
//        pd1 = new ProgressDialog(getActivity());
//        pd1.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        pd1.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//
//
//        pd1.setCancelable(false);
//        //  pd1.show();
//
////        @Field("type") RequestBody type,
////        @Field("boy_phone") RequestBody boy_phone,
////        @Field("boy_phone_code") RequestBody boy_phone_code
//
//        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        String lat = pref.getString("latitude","");
//        String longi = pref.getString("longitude","");
//
//        Call<CategoriesResponce> getCate = ApiClient.getApiService().getStoreFeatured(lat, longi);
//        getCate.enqueue(new Callback<CategoriesResponce>() {
//            @Override
//            public void onResponse(Call<CategoriesResponce> call, Response<CategoriesResponce> response) {
//                final CategoriesResponce resource = response.body();
//
//                pd1.dismiss();
//
//                // Log.d("resss", resource.toString());
//
//                if (resource == null) {
//                    return;
//                }
//                if (resource.getStatus().equals("ok")) {
////                    noorder.setVisibility(View.GONE);
////                    datalayout.setVisibility(View.VISIBLE);
//                       categorydownstrip.setVisibility(View.VISIBLE);
//                       storewiselist.setVisibility(View.VISIBLE);
//
//                    if(getContext()!=null) {
//                        TrendingAdapter gdapter = new TrendingAdapter(getContext(), resource.getStores(), ontrend,R.layout.trending_single);
//                        featuredstores.setAdapter(gdapter);
//                    }
//
//                } else {
//                    categorydownstrip.setVisibility(View.GONE);
//                    featuredstores.setVisibility(View.GONE);
//                    if(getContext()!=null) {
//                        TrendingAdapter gdapter = new TrendingAdapter(getContext(), null, ontrend,R.layout.trending_single);
//                        featuredstores.setAdapter(gdapter);
//                    }
////                    noorder.setVisibility(View.VISIBLE);
////                    datalayout.setVisibility(View.GONE);
//                    if (resource.getMessage() != "") {
//                        //   Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CategoriesResponce> call, Throwable t) {
//                //   pd.dismiss();
//                pd1.dismiss();
//                t.printStackTrace();
//                //     Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

    public void categoryApi() {
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");
        String lat = pref.getString("latitude", "");
        String longi = pref.getString("longitude", "");

        if(lat.equals("0.0") || longi.equals("0.0")) {
            ((HomeScreen) getActivity()).openLocationPopup();
            return;
        }
        mainlayoutstart.setVisibility(View.GONE);
        shimmerlayout.startShimmer();
        shimmerlayout.setVisibility(View.VISIBLE);
        gridedatacat = new ArrayList<>();
        pd1 = new ProgressDialog(getActivity());
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(true);
        // pd1.show();
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code


//        lat="17.004558";
//        longi="82.222238";


        Call<CategoriesResponce> getCate = ApiClient.getApiService().getCategory(lat, longi, "10");
        getCate.enqueue(new Callback<CategoriesResponce>() {
            @Override
            public void onResponse(Call<CategoriesResponce> call, Response<CategoriesResponce> response) {
                final CategoriesResponce resource = response.body();

                pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    shimmerlayout.stopShimmer();
                    shimmerlayout.setVisibility(View.GONE);
                    mainlayoutstart.setVisibility(View.VISIBLE);

                    return;
                }


                if (resource.getStatus().equals("ok") ) {

                    if(getActivity()==null){
                        return;
                    }
                    gridedatacat.clear();

                    nestedcatdata.setVisibility(View.VISIBLE);
                    noorder.setVisibility(View.GONE);

                    if (resource.getCategories().size() != 0) {

                        // constraintlocal.setBackgroundColor(Color.parseColor(resource.getCategories_clr().toString().trim()));
                        //  catList.setBackgroundColor(Color.parseColor(resource.getCategories_clr().toString().trim()));

                        catList.setVisibility(View.VISIBLE);

                        newrcCatogery.setVisibility(View.VISIBLE);
                        constraintlocal.setVisibility(View.VISIBLE);

                        for (int i = 0; i < resource.getCategories().size(); i++) {
                            gridedatacat.add(resource.getCategories().get(i));
                        }

                        setAdapterGrid();
                    } else {
                        catList.setVisibility(View.GONE);
                        newrcCatogery.setVisibility(View.GONE);

                        constraintlocal.setVisibility(View.GONE);
                    }

                    if (getActivity() != null) {
//                        if (resource.getCategories_2().size() != 0) {
//
//                            allindiacatList.setVisibility(View.VISIBLE);
//                            constraintallindia.setVisibility(View.VISIBLE);
//
//                            allindiaadapter = new AllIndiaAdapter(getActivity(), resource.getCategories_2(), onclickallindia, R.layout.homesingle_cat);
//                            allindiacatList.setAdapter(allindiaadapter);
//
//                            // constraintallindia.setBackgroundColor(Color.parseColor(resource.getCategories_2_clr().toString().trim()));
//                            //     allindiacatList.setBackgroundColor(Color.parseColor(resource.getCategories_2_clr().toString().trim()));
//
//
//                        } else {
                            allindiacatList.setVisibility(View.GONE);
                            constraintallindia.setVisibility(View.GONE);
                      //  }
                        if (resource.getCategories_3().size() != 0) {

                            categorydownstrip.setVisibility(View.VISIBLE);
                            storewiselist.setVisibility(View.VISIBLE);

                            SectionsHomeAdapterService secadapter = new SectionsHomeAdapterService(getActivity(), resource.getCategories_3(), onclicksection,homefragment.this);
                            storewiselist.setAdapter(secadapter);

                            //  categorydownstrip.setBackgroundColor(Color.parseColor(resource.getCategories_3_clr().toString().trim()));
                            // services.setBackgroundColor(Color.parseColor(resource.getCategories_3_clr().toString().trim()));

                        } else {
                            categorydownstrip.setVisibility(View.GONE);
                            storewiselist.setVisibility(View.GONE);
                        }

                        try {

                         //   explorelocal.setText(resource.getCategories_h1());
                            localcategories.setText(resource.getCategories_h1());
                           // subexplorelocal.setText(resource.getCategories_h2());
                            kms.setText(resource.getCategories_h2());

                            exploreindia.setText(resource.getCategories_2_h1());
                            subexploreindia.setText(resource.getCategories_2_h2());

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

//                        OffersAdapter offeradapter1 = new OffersAdapter(getActivity(),null, resource.getCategory_4_clr1().toString().trim(), onclickoffer,R.layout.offerhomestoresingle);
//                        offerslist.setAdapter(offeradapter1);

                        //FOR hide offers strip static sir
//                        offerslist.setVisibility(View.GONE);
//                        offerstrip.setVisibility(View.GONE);

//                        if (resource.getCategories_4().size() != 0) {
//                            offerslist.setVisibility(View.VISIBLE);
//                            offerstrip.setVisibility(View.VISIBLE);
//
//                            OffersAdapter offeradapter = new OffersAdapter(getActivity(), resource.getCategories_4(), resource.getCategory_4_clr1().toString().trim(), onclickoffer,R.layout.offerhomestoresingle);
//                            offerslist.setAdapter(offeradapter);
//
//                        } else {
                            offerslist.setVisibility(View.GONE);
                            offerstrip.setVisibility(View.GONE);
                     //   }


                    }

                } else {

                    nestedcatdata.setVisibility(View.GONE);
                    noorder.setVisibility(View.VISIBLE);
//                    noorder.setVisibility(View.VISIBLE);
//                    datalayout.setVisibility(View.GONE);
                    if (getContext() != null) {
//                        gridadapter = new GridAdapterService(getContext(), null, onItemClickeGrid, R.layout.servicersingle_home);
                        catogeryadapter = new CatogeryNewAdapter(getContext(), gridedatacat, onItemClickeGridNew, R.layout.item_rc_localcat);

                        // catList.setAdapter(gridadapter);
                        newrcCatogery.setAdapter(catogeryadapter);
                    }

                    if (resource.getMessage() != "") {
                        //   Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

                shimmerlayout.stopShimmer();
                shimmerlayout.setVisibility(View.GONE);
                mainlayoutstart.setVisibility(View.VISIBLE);
                if(getContext()!=null){
                    sliderApi();
                }

            }

            @Override
            public void onFailure(Call<CategoriesResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();

                shimmerlayout.stopShimmer();
                shimmerlayout.setVisibility(View.GONE);
                mainlayoutstart.setVisibility(View.VISIBLE);
                t.printStackTrace();
                //     Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void allEmpty(){
        if(gridadapter!=null) {
            gridadapter.setDataList(null);
            gridadapter.notifyDataSetChanged();
        }
        SectionsHomeAdapterService secadapter = new SectionsHomeAdapterService(getActivity(),null, onclicksection,homefragment.this);
        storewiselist.setAdapter(secadapter);
    }

    @Override
    public void onItemClickedtrend(int position, String mParam1, String storename, String catid, String sellerid, String storestatus) {


        if (storestatus.equals("1")) {

        } else {

            Toast.makeText(getActivity(), "Currently not accepting orders", Toast.LENGTH_LONG).show();
        }

        SharedPreferences pref = getActivity()
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("user_id","346");
        editor.putBoolean("loadhome", true);
        //   editor.commit();
        // SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("boy",resource.getBoy());
        editor.putString("currentstore", storename);
        editor.commit();

        Util.loadFragment(CategoryFragment.newInstance(catid, sellerid, storename), getActivity(), homefragment.this);
    }


    private synchronized void  setAdapterGrid() {
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
//        dataList.setLayoutManager(gridLayoutManager);
//        //  dataList.setNestedScrollingEnabled(false);
//        onItemClickeGrid=this;
//        if(getContext()!=null) {
//            gridadapter = new GridAdapterService(getContext(), gridedata, onItemClickeGrid);
//            dataList.setAdapter(gridadapter);
//        }
//        //   gridadapter = new GridAdapter(getContext(),gridedata);

        if (getContext() != null) {

            List<Categories> data = new ArrayList<>();
            //   data=gridedatacat;
//
//            if(true) {
//
//                for (int i = 0; i < gridedatacat.size(); i++) {
//
//                    if (i == 6) {
//                        break;
//                    } else {
//                        data.add(gridedatacat.get(i));
//                    }
//
//                }
//
//                final float scale = getResources().getDisplayMetrics().density;
//                float sizee = data.size()/ 3;
//                  int snum=0;
//                if(data.size()% 3!=0){
//                    sizee=sizee+1;
//                    snum=(int) sizee;
//                }else{
//                    snum=(int) sizee;
//
//                }
//                int newheight = (int) ((snum * 150) * scale);
//
//                System.out.println("set dd "+snum+" "+sizee+newheight );
//
//                storeviewall.setText("See More");
//                imgdownicon.setImageResource(R.drawable.seemore);
//
//                int rotationAngle = 360;  //toggle
//               // imgdownicon.animate().rotation(rotationAngle).setDuration(500).start();
//
//                ViewGroup.LayoutParams params=catList.getLayoutParams();
//                params.height=newheight;
//                catList.setLayoutParams(params);
//
//                if(gridedatacat.size()>6){
//                    showmorelayout.setVisibility(View.VISIBLE);
//                }else {
//                    showmorelayout.setVisibility(View.GONE);
//
//                }
//
//
//
//            }
//            else {
//                data=gridedatacat;
//
//                final float scale1 = getResources().getDisplayMetrics().density;
//                int newheight1 = (int) ((gridedatacat.size() / 4 * 140) * scale);
//                int oldheight1 = (int) (catList.getLayoutParams().height * scale1);
//                //  System.out.println("d="+d);
//                storeviewall.setText("See Less");
//
////                int rotationAngle = 360;  //toggle
////                imgdownicon.animate().rotation(rotationAngle).setDuration(500).start();
////                storeviewall.setText("See More");
//                slideView(catList, oldheight1, newheight1);
//            }

//            gridadapter = new GridAdapterService(getContext(), gridedatacat, onItemClickeGrid, R.layout.homesingle_cat);
            catogeryadapter = new CatogeryNewAdapter(getContext(), gridedatacat, onItemClickeGridNew, R.layout.item_rc_localcat);
//            catList.setAdapter(gridadapter);
            newrcCatogery.setAdapter(catogeryadapter);

        }
//        adapter = new CarStoreAdapter(getActivity(), catfilterdata, OnItemClickedcat);
//        recyclerView.setAdapter(adapter);
    }


    private void new_rooms(final String latitude,final String longitude) {
//        if (latitude.equalsIgnoreCase("")||longitude.equalsIgnoreCase("")){
//            final_lat = lat;
//            final_lng = longi;
////            Toast.makeText(getActivity(),"first"+final_lat+final_lng,Toast.LENGTH_LONG).show();
//        }
//        {
//            final_lat = latitude;
//            final_lng = longitude;
////            Toast.makeText(getActivity(),"second"+final_lat+final_lng,Toast.LENGTH_LONG).show();
//        }

        progressBar_rooms.setVisibility(View.VISIBLE);
        home_data_list.clear();
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"home", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse:roomslist "+response);
                progressBar_rooms.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("rcat_status");
                    if (result.equalsIgnoreCase("ok")){
                        JSONArray jsonArray=jsonObject.getJSONArray("categories");
                        if (jsonArray.length()==0){
                            recyclerView_rooms.setVisibility(View.INVISIBLE);
                            txt_no_data.setVisibility(View.VISIBLE);
                        }else{
                            recyclerView_rooms.setVisibility(View.VISIBLE);
                            txt_no_data.setVisibility(View.GONE);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String rcat_id = jsonObject1.getString("rcat_id");
                                String rcat_name = jsonObject1.getString("rcat_name");
                                String rcat_image1 = jsonObject1.getString("rcat_image1");


                                RoomsModel homeModel = new RoomsModel();
                                homeModel.setRcat_id(rcat_id);
                                homeModel.setRcat_name(rcat_name);
                                homeModel.setRcat_image1(rcat_image1);
                                home_data_list.add(homeModel);
                            }
                        }
                    }
                    else {
                        txt_no_data.setVisibility(View.VISIBLE);
                        recyclerView_rooms.setVisibility(View.GONE);
                    }
                    roomsAdapter=new RoomsAdapter(home_data_list, getActivity(),new CustomItemFive() {
                        @Override
                        public void onItemClick(View v, String Rcat_id,String Rcat_name,String Rcat_image1,String Image,String OfficerName) {
                            Intent intent = new Intent(getActivity(), RoomsListActivity.class);
                            intent.putExtra("Rcat_id",Rcat_id);
                            intent.putExtra("Rcat_name",Rcat_name);
                            intent.putExtra("Rcat_image1",Rcat_image1);
                            intent.putExtra("latitude",latitude);
                            intent.putExtra("longitude",longitude);
                            startActivity(intent);
                        }
                    });
                    recyclerView_rooms.setAdapter(roomsAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    txt_no_data.setVisibility(View.VISIBLE);
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar_rooms.setVisibility(View.GONE);
                txt_no_data.setVisibility(View.VISIBLE);
                Log.e(TAG, "onErrorResponse:error"+error );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_lat",latitude);
                params.put("user_long",longitude);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                999999990,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }


    public void servicecategoryApi() {
        gridedataserv = new ArrayList<>();
        pd1 = new ProgressDialog(getActivity());
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        // pd1.show();
//
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");
        String lat = pref.getString("latitude", "");
        String longi = pref.getString("longitude", "");

        Call<CategoriesResponce> getCate = ApiClient.getApiServiceforservice().getHomeService("home", lat, longi);
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

                    if (resource.getCategories().size() == 0) {
                        servicestrip.setVisibility(View.GONE);
                        services.setVisibility(View.GONE);
                    } else {
                        //    categories_clr
//                        servicestrip.setBackgroundColor(Color.parseColor(resource.getCategories_clr().toString().trim()));
//                        services.setBackgroundColor(Color.parseColor(resource.getCategories_clr().toString().trim()));

                        servicestrip.setVisibility(View.VISIBLE);
                        services.setVisibility(View.VISIBLE);
                    }
                    if (resource.getCategories().size() == 0) {
                        servicestrip.setVisibility(View.GONE);
                        //featuredstores.setVisibility(View.GONE);
                    }
                    for (int i = 0; i < resource.getCategories().size(); i++) {
                        gridedataserv.add(resource.getCategories().get(i));
                    }
                    if (getContext() != null) {
                        ServicesAdapter apter = new ServicesAdapter(getContext(), gridedataserv, onItemClickeService, R.layout.homesingle_cat,"");
                        services.setAdapter(apter);
                    }

                } else {
                    servicestrip.setVisibility(View.GONE);
                    services.setVisibility(View.GONE);

                    //   featuredstores.setVisibility(View.GONE);
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
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onItemClickservice(int position, String carid, String name) {
        SharedPreferences pref = getActivity()
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("user_id","346");
        editor.putBoolean("loadhome", true);
        editor.commit();

        Intent ii = new Intent(getActivity(), SubCategoryItems.class);
//        ii.putExtra("catid", carid);
//        ii.putExtra("name", name);
        startActivity(ii);

        SharedPreferences pref_services = getContext().getSharedPreferences("SERVICES_CATIDNAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorserv = pref_services.edit();
        //   editor.putString("boy",resource.getBoy());
        editorserv.putString("carid", carid);
        editorserv.putString("name", name);


        editorserv.commit();


    }

    private void update() {


//        if(!pref.getString("type","").equals("entered")) {
//            GPSTracker gps = new GPSTracker(getActivity());
//            if (gps.canGetLocation()) {
//                String latitude = String.valueOf(gps.getLatitude());
//                String longitude = String.valueOf(gps.getLongitude());
//
//                SharedPreferences.Editor editor1 = pref.edit();
//                //   editor.putString("boy",resource.getBoy());
//                editor1.putString("latitude", latitude);
//                editor1.putString("longitude", longitude);
//                editor1.putString("type", "device");
//
//                if(pref.getString("serv_latitude","") =="" || pref.getString("serv_longitude","") ==""  ) {
//                    editor1.putString("serv_latitude", String.valueOf(latitude));
//                    editor1.putString("serv_longitude", String.valueOf(longitude));
//                    editor1.putString("typeservice", "device");
//                }
//
//                editor1.commit();
//
//                //            if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
//                //
//                //            }else{
//                //
//                //            }
//            } else {
//
//                ((HomeScreen) getActivity()).openLocationPopup();
//
//            }
//        }
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String lat = pref.getString("latitude", "");
        String longi = pref.getString("longitude", "");

//        if(pref.getString("type","").equals("")){
//
//        }

        if (!pref.getString("type", "").equals("entered") || pref.getString("type", "").equals("")) {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(getActivity(), Locale.getDefault());
            try {
//                if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
                if (!lat.equals("") && !longi.equals("")) {
                    addresses = geocoder.getFromLocation(Double.valueOf(lat), Double.valueOf(longi), 1);
                    if (addresses.size() != 0) {// Here 1 represent max location result to returned, by documents it recommended 1 to 5
                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        String address1 = addresses.get(0).getLocality();
                        if (address != null) {
                           //current.setText(address);
                            current.setText(address);
                            tvNewCurren.setText(address);
                            //String loca1 = gps.getLocation().toString();

                        }
                        SharedPreferences pref1 = getActivity()
                                .getSharedPreferences("LOCALITY123", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = pref1.edit();
                        editor1.putString("locality",address1);
                        editor1.putString("fullloca",address);
                        //editor.putBoolean("loadhome", true);
                        editor1.commit();
                    }

                } else {
                    //get Location
                    ((HomeScreen) getActivity()).openLocationPopup();
                    //  ((HomeScreen) getActivity()).noDataopenLocationPopup();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            current.setText(pref.getString("currentloctext", ""));
            tvNewCurren.setText(pref.getString("currentloctext", ""));
        }

//        getChildFragmentManager()
//                .beginTransaction()
//                .replace(
//                        R.id.screen_viewpager,
//                        new LocalStore())
//                .commit();


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

    private void setSliderAdapterCarosal() {

//        carouselView.setSliderAdapter(adapter_carosal);
//        // sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
//        carouselView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//        carouselView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
////        carouselView.setIndicatorSelectedColor(Color.YELLOW);
////        carouselView.setIndicatorUnselectedColor(Color.GRAY);
//        carouselView.setScrollTimeInSec(4);
//        carouselView.setAutoCycle(true);
//        carouselView.startAutoCycle();

        carouselViewNew.setSliderAdapter(adapter_carosal);
        // sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        carouselViewNew.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        carouselViewNew.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
//        carouselView.setIndicatorSelectedColor(Color.YELLOW);
//        carouselView.setIndicatorUnselectedColor(Color.GRAY);
        carouselViewNew.setScrollTimeInSec(4);
        carouselViewNew.setAutoCycle(true);
        carouselViewNew.startAutoCycle();

    }

    private void setSliderAdapterservice() {

        imageSliderservice.setSliderAdapter(adapterservice);
        // imageSliderservice.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSliderservice.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSliderservice.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);

        imageSliderservice.setIndicatorSelectedColor(Color.YELLOW);
        imageSliderservice.setIndicatorUnselectedColor(Color.GRAY);
        imageSliderservice.setScrollTimeInSec(4);
        imageSliderservice.setAutoCycle(true);
        imageSliderservice.startAutoCycle();

    }


    private void sliderApi() {

        pd = new ProgressDialog(getContext());
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
//                    carouselView.setVisibility(View.VISIBLE);
                    carouselViewNew.setVisibility(View.VISIBLE);
               //     carouselView1.setVisibility(View.VISIBLE);
                    if (resource.getOrders() != null) {
                        if (resource.getOrders().size() == 0) {

                            floatingbutton.setVisibility(View.GONE);

                        } else {
                            //  String pendorder=;
                            if (pref.getString("pendorder", "").equals("display")) {
                                floatingbutton.setVisibility(View.VISIBLE);
                                ordertextheading.setText(resource.getOrders().get(0).getP1());
                                ordertext.setText(resource.getOrders().get(0).getP2());
                            }
                        }
                    }

                    //home top slider
//                    carouselView.setVisibility(View.VISIBLE);
                    carouselViewNew.setVisibility(View.VISIBLE);

                    adapter_carosal.setEmpty();
                    for (int i = 0; i < resource.getBanners().size(); i++) {
                        adapter_carosal.addItem(new SliderModelHome("", resource.getBanners().get(i).getBanner_image(),resource.getBanners()));

                    }
                    setSliderAdapterCarosal();
//
//                    if(resource.getBanners2().size()!=0) {
//                        sliderView.setVisibility(View.VISIBLE);
//
//                        adapter.setEmpty();
//                        for (int i = 0; i < resource.getBanners2().size(); i++) {
//                            adapter.addItem(new SliderModelHome("", resource.getBanners2().get(i).getBanner_image(),resource.getBanners2()));
//                        }
//                        setSliderAdapter();
//                    }else{
//                        sliderView.setVisibility(View.GONE);
//                    }
//
//                    if(resource.getBanners3().size()!=0) {
//                        imageSliderservice.setVisibility(View.VISIBLE);
//
//                        adapterservice.setEmpty();
//                        for (int i = 0; i < resource.getBanners3().size(); i++) {
//                            adapterservice.addItem(new SliderModelHome("", resource.getBanners3().get(i).getBanner_image(),resource.getBanners3()));
//                        }
//                        setSliderAdapterservice();
//                    }else{
//                        imageSliderservice.setVisibility(View.GONE);
//                    }

//                    carouselView.setSize(resource.getBanners().size());
//
//                    carouselView.hideIndicator(false);
//                    carouselView.setResource(R.layout.center_carousel_item);
//                    carouselView.setIndicatorAnimationType(IndicatorAnimationType.THIN_WORM);
//                    carouselView.setCarouselOffset(OffsetType.CENTER);
//
//
//                    carouselView.setCarouselViewListener(new CarouselViewListener() {
//                        @Override
//                        public void onBindView(View view, int position) {
//
//                            ImageView imageView = view.findViewById(R.id.imageView);
//
//                            Glide.with(getActivity())
//                                    .load(resource.getBanners().get(position).getBanner_image())
//                                    .fitCenter().placeholder(R.drawable.placeholderello)
//                                    .into(imageView);
//
//                            view.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    if (resource.getBanners().get(position).getBanner_page().equals("product")) {
//
//                                        Util.loadFragment(new ProductDetailedPage().newInstance(resource.getBanners().get(position).getSeller_id(), resource.getBanners().get(position).getProduct_id()), getActivity(), homefragment.this);
//
//                                    } else if (resource.getBanners().get(position).getBanner_page().equals("seller")) {
//                                        // Util.AlertWithOK(getActivity(), "pos " + position);
//                                        Util.loadFragment(CategoryFragment.newInstance("", resource.getBanners().get(position).getSeller_id(), ""), getActivity(), homefragment.this);
//                                        //      Util.loadFragment(NearbystoreFragment.newInstance("",resource.getBanners().get(position).getBanner_name(),"",resource.getBanners().get(position).getSeller_id()), getActivity(),homefragment.this);
//
//                                    } else if (resource.getBanners().get(position).getBanner_page().equals("percentage")) {
//                                        // Util.AlertWithOK(getActivity(), "pos " + position);
//
//                                        Util.loadFragment(NearbystoreFragment.newInstance("", resource.getBanners().get(position).getBanner_name(), resource.getBanners().get(position).getBanner_id(), "", ""), getActivity(), homefragment.this);
//
//                                    } else if (resource.getBanners().get(position).getBanner_page().equals("all_in")) {
//                                        // Util.AlertWithOK(getActivity(), "pos " + position);
//                                        Util.loadFragment(AllIndiaCatgoryViewAll.newInstance("hide",""), getActivity(), homefragment.this);
//
////                                        Util.loadFragment(NearbystoreFragment.newInstance("", resource.getBanners().get(position).getBanner_name(), resource.getBanners().get(position).getBanner_id(), "", ""), getActivity(), homefragment.this);
//
//                                    }
//                                }
//                            });
//
////                            if(position==0) {
//////                                imageView.setImageDrawable(getResources().getDrawable(R.drawable.bar));
////                            }else{
////                              //  imageView.setImageDrawable(getResources().getDrawable(R.drawable.bar2));
////
////                            }
//
//                        }
//                    });
//
//                    carouselView1.setSize(resource.getBanners().size());
//
//                    carouselView1.hideIndicator(false);
//                    carouselView1.setResource(R.layout.center_carousel_item);
//                    carouselView1.setIndicatorAnimationType(IndicatorAnimationType.THIN_WORM);
//                    carouselView1.setCarouselOffset(OffsetType.CENTER);
//
//                    carouselView1.setCarouselViewListener(new CarouselViewListener() {
//                        @Override
//                        public void onBindView(View view, int position) {
//
//                            ImageView imageView = view.findViewById(R.id.imageView);
//
//                            Glide.with(getActivity())
//                                    .load(resource.getBanners().get(position).getBanner_image())
//                                    .fitCenter().placeholder(R.drawable.placeholderello)
//                                    .into(imageView);
//
//                            view.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    if (resource.getBanners().get(position).getBanner_page().equals("product")) {
//
//                                        Util.loadFragment(new ProductDetailedPage().newInstance(resource.getBanners().get(position).getSeller_id(), resource.getBanners().get(position).getProduct_id()), getActivity(), homefragment.this);
//
//                                    } else if (resource.getBanners().get(position).getBanner_page().equals("seller")) {
//                                        // Util.AlertWithOK(getActivity(), "pos " + position);
//                                        Util.loadFragment(CategoryFragment.newInstance("", resource.getBanners().get(position).getSeller_id(), ""), getActivity(), homefragment.this);
//                                        //      Util.loadFragment(NearbystoreFragment.newInstance("",resource.getBanners().get(position).getBanner_name(),"",resource.getBanners().get(position).getSeller_id()), getActivity(),homefragment.this);
//
//                                    } else if (resource.getBanners().get(position).getBanner_page().equals("percentage")) {
//                                        // Util.AlertWithOK(getActivity(), "pos " + position);
//
//                                        Util.loadFragment(NearbystoreFragment.newInstance("", resource.getBanners().get(position).getBanner_name(), resource.getBanners().get(position).getBanner_id(), "", ""), getActivity(), homefragment.this);
//
//                                    }
//                                }
//                            });
//
////                            if(position==0) {
//////                                imageView.setImageDrawable(getResources().getDrawable(R.drawable.bar));
////                            }else{
////                              //  imageView.setImageDrawable(getResources().getDrawable(R.drawable.bar2));
////
////                            }
//
//                        }
//                    });


//                    try {
//                        carouselView.setAutoPlay(true);
//                        carouselView.setAutoPlayDelay(5000);
//
//                        carouselView.show();
//                        //   carouselView1.show();
//                    } catch (Exception ex) {
//
//                    }
                    //     carouselView.setCurrentItem(1);
//                    adapter.setEmpty();
//                    for (int i = 0; i < resource.getBanners().size(); i++) {
//                        adapter.addItem(new SliderModel("", resource.getBanners().get(i).getBanner_image()));
//                    }
//                    setSliderAdapter();
                } else {

//                    carouselView.setVisibility(View.GONE);
                    carouselViewNew.setVisibility(View.GONE);
                   // carouselView1.setVisibility(View.GONE);
                    if (resource.getMessage() != "") {

                        // Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
                    }

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

    public void showDialog(Activity activity) {
        dialoglocation = new Dialog(activity, android.R.style.Theme_Holo_Light_NoActionBar);
        dialoglocation.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialoglocation.setCancelable(true);
        dialoglocation.setContentView(R.layout.locationdialog);

        // ConstraintLayout currentloc = dialog.findViewById(R.id.constraintLayout2);
        ImageView close = dialoglocation.findViewById(R.id.imageView3);

        recentlist = dialoglocation.findViewById(R.id.recentlist);
        addresslist = dialoglocation.findViewById(R.id.addresslist);
        CardView layout = dialoglocation.findViewById(R.id.layout);
        TextView   tname = dialoglocation.findViewById(R.id.name);
        TextView   taddress = dialoglocation.findViewById(R.id.address);
        addresslist.setNestedScrollingEnabled(false);
        recentlist.setNestedScrollingEnabled(false);
        addresslist.setLayoutManager(new LinearLayoutManager(getActivity()));
        recentlist.setLayoutManager(new LinearLayoutManager(getActivity()));


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialoglocation.dismiss();
            }
        });

//use current ki
        List<com.ellocartuser.home.adapterandmodel.Address> addlist = new ArrayList<>();
        com.ellocartuser.home.adapterandmodel.Address add = new com.ellocartuser.home.adapterandmodel.Address();
        String latitude = "", longitude = "";
        GPSTracker gps = new GPSTracker(getActivity());
        if (gps.canGetLocation()) {
            latitude = String.valueOf(gps.getLatitude());
            longitude = String.valueOf(gps.getLongitude());

            SharedPreferences.Editor editor = pref.edit();
            //   editor.putString("boy",resource.getBoy());
            editor.putString("latitude", latitude);
            editor.putString("longitude", longitude);
            editor.putString("type", "device");
            editor.commit();
            update();
            apiCallDetail();
            //            if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
            //
            //            }else{
            //
            //            }
        } else {




        }

        tname.setText("Use Current Location");
//        add.setAddrLat(latitude);
//        add.setAddrLong(longitude);

        tname.setTextColor(ContextCompat.getColor(getActivity(), R.color.yellow));

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
//                if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
            if (!latitude.equals("") && !longitude.equals("")) {
                addresses = geocoder.getFromLocation(Double.valueOf(latitude), Double.valueOf(longitude), 1);
                if (addresses.size() != 0) { // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    if (address != null) {
                        taddress.setText(address);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String finalLatitude = latitude;
        String finalLongitude = longitude;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = pref.edit();
                //   editor.putString("boy",resource.getBoy());
                editor.putString("latitude", finalLatitude);
                editor.putString("longitude", finalLongitude);
                editor.putString("type", "device");
//        editor.putString("type","entered");
//        editor.putString("currentloctext",address.getAddrAddress()+","+address.getAddrCity()+","+address.getAddrPincode());
                editor.commit();
                update();
                apiCallDetail();

                try {
                    dialoglocation.dismiss();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
        myaddress = dialoglocation.findViewById(R.id.myaddress);
        recentsearch = dialoglocation.findViewById(R.id.recentsearch);

        apiCallForAddress();



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
                    editor.putString("latitude", String.valueOf(latLng.latitude));
                    editor.putString("longitude", String.valueOf(latLng.longitude));
                    editor.putString("area", String.valueOf(latLng.longitude));
                    editor.putString("type", "entered");
                    editor.putString("currentloctext", autoCompleteTextView.getText().toString());
                    editor.commit();
                    current.setText(autoCompleteTextView.getText().toString());
                    tvNewCurren.setText(autoCompleteTextView.getText().toString());
                    update();
                    apiCallDetail();

                    apiCallForAddAddress(autoCompleteTextView.getText().toString(),String.valueOf(latLng.latitude),String.valueOf(latLng.longitude));

                    Address address = getAddressFromLatLng(latLng);
                    if (address != null) {

//                        SharedPreferences pref1 = getActivity()
//                                .getSharedPreferences("LOCALITY1", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor1 = pref1.edit();
//                        editor1.putString("locality",address.getLocality());
//                        //editor.putBoolean("loadhome", true);
//                        editor1.commit();

//                        SharedPreferences pref = getActivity().getSharedPreferences("LOCALITY", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = pref.edit();
//                        //   editor.putString("boy",resource.getBoy());
//                        editor.putString("locality", address.getLocality());
//                        editor.commit();


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


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialoglocation.dismiss();
            }
        });


        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialoglocation.show();

        if (!gps.canGetLocation()) {
            dialoglocation.dismiss();
            ((HomeScreen) getActivity()).openLocationPopup();

        }

    }

    private void apiCallForAddAddress(String adderss,String lat, String longi) {

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate,null));
        }
        pd.setCancelable(false);
        //  // pd.show();

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");

        Call<AddressResponce> getCate = ApiClient.getApiService().addAddresshome("places", id,adderss,lat,longi);
        getCate.enqueue(new Callback<AddressResponce>() {
            @Override
            public void onResponse(Call<AddressResponce> call, Response<AddressResponce> response) {
                final AddressResponce resource = response.body();
                pd.dismiss();
                //    Log.d("resss",resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {



                } else {



                }

            }

            @Override
            public void onFailure(Call<AddressResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void apiCallForAddress() {

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        //  // pd.show();
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");

        if(id.equals("") || id==null){
            recentsearch.setVisibility(View.INVISIBLE);
            myaddress.setVisibility(View.INVISIBLE);
        }

        Call<AddressResponce> getCate = ApiClient.getApiService().getAddress("get", id, "0");
        getCate.enqueue(new Callback<AddressResponce>() {
            @Override
            public void onResponse(Call<AddressResponce> call, Response<AddressResponce> response) {
                final AddressResponce resource = response.body();
                pd.dismiss();
                //    Log.d("resss",resource.toString());

                if (resource == null) {
                    return;
                }

                List<com.ellocartuser.home.adapterandmodel.Address> addlist = new ArrayList<>();
                com.ellocartuser.home.adapterandmodel.Address add = new com.ellocartuser.home.adapterandmodel.Address();
                String latitude = "", longitude = "";
                GPSTracker gps = new GPSTracker(getActivity());
                if (gps.canGetLocation()) {
                    latitude = String.valueOf(gps.getLatitude());
                    longitude = String.valueOf(gps.getLongitude());




                    SharedPreferences.Editor editor = pref.edit();
                    //   editor.putString("boy",resource.getBoy());
                    editor.putString("latitude", latitude);
                    editor.putString("longitude", longitude);
                   // editor.putString("area", loca1);
                    editor.putString("type", "device");
                    editor.commit();
                    update();
                    apiCallDetail();
                    //            if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
                    //
                    //            }else{
                    //
                    //                }
                } else {

                    ((HomeScreen) getActivity()).openLocationPopup();

                }

                add.setAddrName("Use Current Location");
                add.setAddrLat(latitude);
                add.setAddrLong(longitude);

                Geocoder geocoder;
                List<Address> addresses;

               // String address1 = addresses.get(0).getLocality();


                geocoder = new Geocoder(getActivity(), Locale.getDefault());
                try {
//                if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
                    if (!latitude.equals("") && !longitude.equals("")) {
                        addresses = geocoder.getFromLocation(Double.valueOf(latitude), Double.valueOf(longitude), 1);
                        if (addresses.size() != 0) { // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            //String address1 = addresses.get(0).getLocality();



                            if (address != null) {
                                add.setAddrAddress(address);
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //    addlist.add(add);

                if (resource.getStatus().equals("ok")) {

                   String locality1 =  resource.getAddress().get(0).getAddrAddress();
                    Log.d("Ashok : ", "" + locality1);
                    SharedPreferences pref1 = getActivity()
                            .getSharedPreferences("LOCALITY123", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = pref1.edit();
                    editor1.putString("locality",locality1);
                    //editor.putBoolean("loadhome", true);
                    editor1.commit();



                    for (int i = 0; i < resource.getAddress().size(); i++) {

                        addlist.add(resource.getAddress().get(i));

                    }


                    AdressLocationAdapter adressAdapter1 = new AdressLocationAdapter(getActivity(), addlist, onclick);
                    addresslist.setAdapter(adressAdapter1);

                    RecentLocationAdapter recentadapter = new RecentLocationAdapter(getActivity(), resource.getPlaces(), onclickrecent);
                    recentlist.setAdapter(recentadapter);

                } else {

                    AdressLocationAdapter adressAdapter1 = new AdressLocationAdapter(getActivity(), addlist, onclick);
                    addresslist.setAdapter(adressAdapter1);



//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<AddressResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onItemClickedCart(int position, String mParam1, com.ellocartuser.home.adapterandmodel.Address address) {

        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("boy",resource.getBoy());
        editor.putString("latitude", address.getAddrLat());
        editor.putString("longitude", address.getAddrLong());
        editor.putString("type", "device");
//        editor.putString("type","entered");
//        editor.putString("currentloctext",address.getAddrAddress()+","+address.getAddrCity()+","+address.getAddrPincode());
        editor.commit();
        update();
        apiCallDetail();

        try {
            dialoglocation.dismiss();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void showDialogSearch(Activity activity) {
        dialog = new Dialog(activity, android.R.style.Theme_Holo_Light_NoActionBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.homeproductsearch);

        nosearchdata = dialog.findViewById(R.id.sdfg);

//      ConstraintLayout currentloc = dialog.findViewById(R.id.constraintLayout2);
        ImageView close = dialog.findViewById(R.id.imageback);
        EditText editText = dialog.findViewById(R.id.productsearch);
        recyclerViewproductsearch = dialog.findViewById(R.id.catList);
        recyclerViewproductsearch.setLayoutManager(new LinearLayoutManager(getContext()));
        editText.requestFocus();
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //Clear focus here from edittext
                    editText.clearFocus();
                }
                return false;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                apicallSearch(editText.getText().toString());
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    public void apicallSearch(String search) {

//      pd1 = new ProgressDialog(getActivity());
//        pd1.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//         pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
//        pd1.setCancelable(false);
//        pd1.show();

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");
        String lat = pref.getString("latitude", "");
        String longi = pref.getString("longitude", "");

        Call<StoresCatResponce> getCate = ApiClient.getApiService().productsearch(search, lat, longi, "");
        getCate.enqueue(new Callback<StoresCatResponce>() {
            @Override
            public void onResponse(Call<StoresCatResponce> call, Response<StoresCatResponce> response) {
                final StoresCatResponce resource = response.body();
                //  pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
//                    adapterUpdate(resource.getList());
                    //      Log.d("raju fav",resource.getStores().get(0).getWish_status());

                    if (getActivity() != null) {

                        recyclerViewproductsearch.setVisibility(View.VISIBLE);
                        if (nosearchdata != null) {
                            nosearchdata.setVisibility(View.GONE);
                        }

                        productsearchadapter = new ProductSearchAdapter(getActivity(), resource.getList(), productsearchlistioner, "list");
                        recyclerViewproductsearch.setAdapter(productsearchadapter);
                    }

                } else {
                    if (getActivity() != null) {
                        recyclerViewproductsearch.setVisibility(View.GONE);
                        if (nosearchdata != null) {
                            nosearchdata.setVisibility(View.VISIBLE);
                        }
                        productsearchadapter = new ProductSearchAdapter(getActivity(), null, productsearchlistioner, "list");
                        recyclerViewproductsearch.setAdapter(productsearchadapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<StoresCatResponce> call, Throwable t) {
                //   pd.dismiss();
                //  pd1.dismiss();
                t.printStackTrace();
                try {
                    if (getActivity() != null) {
                        Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
                    }
                } catch(Exception ex ){ ex.printStackTrace(); }
            }
        });

    }

//    @Override
//    public void onItemClicked(int position, String catid,String name) {
//
//        if(dialog!=null){
//            dialog.dismiss();
//        }
//    //    Util.loadFragment(NearbystoreFragment.newInstance(catid,name),getActivity(),homefragment.this);
//    }

    public void adapterUpdate(List<ProductSearchList> store) {

    }

    @Override
    public void onItemClickedcatsearch(int position, String sellerid, String productid) {

        try {
            dialog.dismiss();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Util.loadFragment(new ProductDetailedPage().newInstance(sellerid, productid), getActivity(), homefragment.this);


    }

    public void apiCallForCart(String userid, String type, String product_id, String product_qty, String sproduct_id, String seller_id) {


        if(userid.trim().length()==0){
            ((HomeScreen) getActivity()).updatecartcount("0");
            cartcount.setText("0");
            cartcount.setVisibility(View.INVISIBLE);
            return;
        }

//        pd1 = new ProgressDialog(getActivity());
//        pd1.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//         pd1.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//        pd1.setCancelable(false);
        //  pd1.show();
        Call<CartResponce> getCate = ApiClient.getApiService().setCart(userid, type, product_id, product_qty, sproduct_id, seller_id);
        getCate.enqueue(new Callback<CartResponce>() {
            @Override
            public void onResponse(Call<CartResponce> call, Response<CartResponce> response) {
                final CartResponce resource = response.body();
                //   pd1.dismiss();

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    if(String.valueOf(resource.getCartCount()).equals("0")){
                        if(getActivity()!=null) {
                            ((HomeScreen) getActivity()).updatecartcount("0");
                        }
                        cartcount.setText(String.valueOf(resource.getCartCount()));
                        cartcount.setVisibility(View.INVISIBLE);
                    }else{
                        if(getActivity()!=null) {
                            ((HomeScreen) getActivity()).updatecartcount(String.valueOf(resource.getCartCount()));
                            cartcount.setText(String.valueOf(resource.getCartCount()));
                            //cartcount.setVisibility(View.VISIBLE);
                        }
                    }

                } else {
                    if(getActivity()!=null) {
                        if (String.valueOf(resource.getCartCount()).equals("0")) {
                            ((HomeScreen) getActivity()).updatecartcount("0");
                            cartcount.setText(String.valueOf(resource.getCartCount()));
                            cartcount.setVisibility(View.INVISIBLE);
                        } else {
                            ((HomeScreen) getActivity()).updatecartcount(String.valueOf(resource.getCartCount()));

                            cartcount.setText(String.valueOf(resource.getCartCount()));
                            cartcount.setVisibility(View.VISIBLE);

                        }
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
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //The Refresh must be only active when the offset is zero :
        // refresh.setEnabled(verticalOffset == 0);
    }

    @Override
    public void onItemClick_Room(int position, String carid, String name) {

        SharedPreferences pref = getActivity()
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("user_id","346");
        editor.putBoolean("loadhome", true);
        editor.commit();
        Intent intent = new Intent(getActivity(), Tutions_Base.class);
        intent.putExtra("type","ROOM_CATOGERY");
        //ii.putExtra("type", "");
        startActivity(intent);

        //Util.loadFragment(Room_Subc_Fragment.newInstance(carid, name, "", "", ""), getActivity(), homefragment.this);

    }


    @Override
    public void onItemClick(int position, String carid, String name) {

        SharedPreferences pref = getActivity()
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("user_id","346");
        editor.putBoolean("loadhome", true);
        editor.commit();

        Util.loadFragment(NearbystoreFragment.newInstance(carid, name, "", "", ""), getActivity(), homefragment.this);

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //do when hidden

        } else {

            //do when show
            if(getActivity()!=null) {
                SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                String id = pref.getString("user_id", "");
                apiCallForCart(id, "get", "", "", "", "");
            }
        }
    }

    @Override
    public void onItemClickedtrendAllIndia(int position, String catid, String storename) {
        SharedPreferences pref = getActivity()
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("user_id","346");
        editor.putBoolean("loadhome", true);
        editor.commit();

        Util.loadFragment(NearbystoreFragment.newInstance(catid, storename, "", "", "1"), getActivity(), homefragment.this);

    }

    @Override
    public void onItemClickDownSection(int position, String carid, String name) {

        SharedPreferences pref = getActivity()
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("user_id","346");
        editor.putBoolean("loadhome", true);
        editor.commit();

        Util.loadFragment(NearbystoreFragment.newInstance(carid, name, "", "", ""), getActivity(), homefragment.this);

    }

    @Override
    public void onItemClickslide(int position, List<Banners> bnr) {

        if (bnr.get(position).getBanner_page().equals("product")) {

            Util.loadFragment(new ProductDetailedPage().newInstance(bnr.get(position).getSeller_id(), bnr.get(position).getProduct_id()), getActivity(), homefragment.this);

        } else if (bnr.get(position).getBanner_page().equals("seller")) {
            // Util.AlertWithOK(getActivity(), "pos " + position);
            Util.loadFragment(CategoryFragment.newInstance("", bnr.get(position).getSeller_id(), ""), getActivity(), homefragment.this);
            //      Util.loadFragment(NearbystoreFragment.newInstance("",bnr.get(position).getBanner_name(),"",bnr.get(position).getSeller_id()), getActivity(),homefragment.this);

        } else if (bnr.get(position).getBanner_page().equals("percentage")) {
            // Util.AlertWithOK(getActivity(), "pos " + position);

            Util.loadFragment(NearbystoreFragment.newInstance("", bnr.get(position).getBanner_name(), bnr.get(position).getBanner_id(), "", ""), getActivity(), homefragment.this);

        } else if (bnr.get(position).getBanner_page().equals("all_in")) {
            // Util.AlertWithOK(getActivity(), "pos " + position);
            Util.loadFragment(AllIndiaCatgoryViewAll.newInstance("hide",""), getActivity(), homefragment.this);

//                                        Util.loadFragment(NearbystoreFragment.newInstance("", bnr.get(position).getBanner_name(), bnr.get(position).getBanner_id(), "", ""), getActivity(), homefragment.this);

        }
    }
}