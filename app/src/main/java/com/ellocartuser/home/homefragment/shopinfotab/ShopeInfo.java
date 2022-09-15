package com.ellocartuser.home.homefragment.shopinfotab;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.BannerResponce;
import com.ellocartuser.apiservices.Responce.StoreInfoResponce;
import com.ellocartuser.home.adapterandmodel.SliderAdapter;
import com.ellocartuser.home.adapterandmodel.SliderModel;
import com.ellocartuser.home.adapterandmodel.SliderModelHome;
import com.ellocartuser.home.adapterandmodel.StatementAdapter;
import com.ellocartuser.orders.MapsFragment;
import com.jama.carouselview.CarouselViewListener;
import com.jama.carouselview.enums.IndicatorAnimationType;
import com.jama.carouselview.enums.OffsetType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopeInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopeInfo extends Fragment {
ImageView img;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
String sellerid;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
TextView txt1,txt2,txt3,txt4,txt5;
    public ShopeInfo() {
        // Required empty public constructor
    }
    FrameLayout framelayout;
    SliderView sliderView;
    private SliderAdapter adapter;
TextView storename,minimumorder,address;
RatingBar rateing;
    TextView avurating,totalcount,totalcounttop;
    RatingBar ratingbar;
    ScrollView mainScrollView;
    RoundCornerProgressBar rate5,rate4,rate3,rate2,rate1;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopeInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopeInfo newInstance(String param1, String param2) {
        ShopeInfo fragment = new ShopeInfo();
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
            sellerid = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shope_info, container, false);
        sliderView = view.findViewById(R.id.imageSlider);
        storename = view.findViewById(R.id.storename);
        img = view.findViewById(R.id.img);
        minimumorder = view.findViewById(R.id.minimumorder);
        address = view.findViewById(R.id.address);
        adapter = new SliderAdapter(getContext(),R.layout.home_slider_layout,null,null);
        mainScrollView=view.findViewById(R.id.mainScrollView);
        framelayout=view.findViewById(R.id.framelayout);
        framelayout.requestDisallowInterceptTouchEvent(true);

        avurating=view.findViewById(R.id.textView2);
        totalcounttop=view.findViewById(R.id.textView33);
        totalcount=view.findViewById(R.id.textView3);
        ratingbar=view.findViewById(R.id.rateingg);

        txt1=view.findViewById(R.id.txt1);
        txt2=view.findViewById(R.id.txt2);
        txt3=view.findViewById(R.id.txt3);
        txt4=view.findViewById(R.id.txt4);
        txt5=view.findViewById(R.id.txt5);

        rate5=view.findViewById(R.id.rate5);
        rate4=view.findViewById(R.id.rate4);
        rate3=view.findViewById(R.id.rate3);
        rate2=view.findViewById(R.id.rate2);
        rate1=view.findViewById(R.id.rate1);

       // sliderApi();
        apicallshpoInfo();


        framelayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                Log.d("Event raju ", String.valueOf(event.getAction()));
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        mainScrollView.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        mainScrollView.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        mainScrollView.requestDisallowInterceptTouchEvent(true);
                        return false;


                    default:
                        return true;
                }
            }
        });

        return view;
    }


//    private void sliderApi() {
//
//        ProgressDialog  pd = new ProgressDialog(getContext());
//        pd.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//         pd.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//        pd.setCancelable(false);
//        // pd.show();
////        @Field("type") RequestBody type,
////        @Field("boy_phone") RequestBody boy_phone,
////        @Field("boy_phone_code") RequestBody boy_phone_code
//        Call<BannerResponce> getCate = ApiClient.getApiService().getBanners("14");
//        getCate.enqueue(new Callback<BannerResponce>() {
//            @Override
//            public void onResponse(Call<BannerResponce> call, Response<BannerResponce> response) {
//                final BannerResponce resource = response.body();
//
//                pd.dismiss();
//
//
//                //    Log.d("resss", resource.toString());
//
//                if (resource == null) {
//                    return;
//                }
//                if (resource.getStatus().equals("ok")) {
//
//                    adapter.setEmpty();
//                    for (int i = 0; i < resource.getBanners().size(); i++) {
//                        adapter.addItem(new SliderModel("", resource.getBanners().get(i).getBanner_image()));
//                    }
//
//                    setSliderAdapter();
//
//                } else {
//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<BannerResponce> call, Throwable t) {
//                //   pd.dismiss();
//                try {
//                    pd.dismiss();
//                    t.printStackTrace();
//                    Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
//                }catch (Exception ex){
//
//                }
//
//
//            }
//        });
//
//    }
    public void apicallshpoInfo(){

//      pd1 = new ProgressDialog(getActivity());
//        pd1.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//         pd1.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//        pd1.setCancelable(false);
//        pd1.show();

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        String lat = pref.getString("latitude","");
        String longi = pref.getString("longitude","");

        Call<StoreInfoResponce> getCate = ApiClient.getApiService().getStoreDetail(sellerid);
        getCate.enqueue(new Callback<StoreInfoResponce>() {
            @Override
            public void onResponse(Call<StoreInfoResponce> call, Response<StoreInfoResponce> response) {
                final StoreInfoResponce resource = response.body();
                //  pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {

//                    adapter.setEmpty();
//                    for (int i = 0; i < resource.getBanners().size(); i++) {
//                        adapter.addItem(new SliderModel("", resource.getBanners().get(i).getBanner_image()));
//
//                    }
//                    setSliderAdapter();
try {
    final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
    transaction.replace(R.id.framelayout, new MapsFragmentShopInfo().newInstance(lat, longi, resource.getSellerLat(), resource.getSellerLong()));
    //   transaction.replace(R.id.framelayout, new MapsFragment().newInstance("ELOCRT680"));
    transaction.addToBackStack(null);
    transaction.commit();
}catch (Exception ex){

}
    address.setText(resource.getSellerStoreAddress());
    minimumorder.setText("Minimum Order : ₹" + resource.getSellerMinimumOrder());
    storename.setText(resource.getSellerStoreName());
if(getActivity()!=null && resource.getSellerStoreImage()!=null) {
    Glide.with(getActivity())
            .load(resource.getSellerStoreImage())
            .fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
            .placeholder(R.drawable.placeholderello).into(img);
}
    avurating.setText(resource.getRatingAvg().toString());
    totalcount.setText(Integer.toString(resource.getRatingTotal()));
    totalcounttop.setText(Integer.toString(resource.getRatingTotal()));
   // ratingbar.setRating(Float.parseFloat(String.valueOf(resource.getRatingAvg())));
    ratingbar.setRating(resource.getRatingAvg());

 //   rate1.setProgress(resource.getRatingTotal1());
                    if(resource.getRatingTotal()!=0) {

                        txt1.setText(String.valueOf(resource.getRatingTotal1()));
                        txt2.setText(String.valueOf(resource.getRatingTotal2()));
                        txt3.setText(String.valueOf(resource.getRatingTotal3()));
                        txt4.setText(String.valueOf(resource.getRatingTotal4()));
                        txt5.setText(String.valueOf(resource.getRatingTotal5()));

                        rate1.setProgress(resource.getRatingTotal1() / resource.getRatingTotal() * 100);
                        rate2.setProgress(resource.getRatingTotal2() / resource.getRatingTotal() * 100);
                        rate3.setProgress(resource.getRatingTotal3() / resource.getRatingTotal() * 100);
                        rate4.setProgress(resource.getRatingTotal4() / resource.getRatingTotal() * 100);
                        rate5.setProgress(resource.getRatingTotal5() / resource.getRatingTotal() * 100);
                    }

                    adapter.setEmpty();
                    for (int i = 0; i < resource.getSeller_banners().size(); i++) {
                        adapter.addItem(new SliderModelHome("", resource.getSeller_banners().get(i).getBnr_img(),null));
                    }

                    if(resource.getSeller_banners().size()==0){
                      //  for (int i = 0; i < resource.getSeller_banners().size(); i++) {
                          //  adapter.addItem(new SliderModel("", "https://ratnagiritourism.in/marathi/wp-content/uploads/2018/01/no-img-banner.jpg"));
                        sliderView.setVisibility(View.GONE);
                    }


                    setSliderAdapter();



                    if(getActivity()!=null) {
//                        productsearchadapter = new ProductSearchAdapter(getActivity(), resource.getList(), productsearchlistioner,"list");
//                        recyclerViewproductsearch.setAdapter(productsearchadapter);
                    }

                } else {

                }

            }

            @Override
            public void onFailure(Call<StoreInfoResponce> call, Throwable t) {
                //   pd.dismiss();
                //  pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

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


}