package com.ellocartuser.orders;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.LocationResponce;
import com.ellocartuser.apiservices.Responce.OrdersResponce;
import com.ellocartuser.apiservices.Responce.StoresCatResponce;
import com.ellocartuser.apiservices.model.HomeOrders;
import com.ellocartuser.cart.ThankyouScreen;
import com.ellocartuser.setting.webfragment.WebLinkFragment;
import com.ellocartuser.utils.Util;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderDetailedPageForPending#newInstance} factory method to
 * create an instance of this fragment.
 */

public class OrderDetailedPageForPending extends Fragment implements EasyPermissions.PermissionCallbacks {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
RecyclerView detaildata;
DetailedAdapter detailedAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;

    Handler handler;
    int delay = 6000; // 1000 milliseconds == 1 second
    Runnable runnableCode;
   // ScrollView scroll_layout;
    FrameLayout framelayout;
    LinearLayout cancellayout;
    ConstraintLayout constraintLayoutprofile;
    private String mParam2;
    ProgressDialog pd,pd1;
    String orderidtext;
    TextView scheduledate,phnnumdispalay,deliveryname,estimatedelivery,discountcharge,current,track,orderid,orderdateschedule,canclebtn,address,storename,phonenumber,itemprice,service,total,typeofmode,deliverycharge;
    RecyclerView list;
    Button calldelivery,callsupport;
    ImageView img,imgback;
    OrderItemAdapter adapter;
    ConstraintLayout deliveryinfo;
    LinearLayout nodelivery;
    Button invoice;
    TextView textdeliverynotstarted,name,useraddress;
    String userid;
    String calldeliverynumber,callsupportnum="";
    ImageView deliveryboyimg;
    String orderidsend="",ordertype="",orderstatus="",currentorderid="",orderasign="";
    SwipeRefreshLayout refresh;
    public OrderDetailedPageForPending() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderDetailedPage.
     */

    // TODO: Rename and change types and number of parameters
    public static OrderDetailedPageForPending newInstance(String param1, String param2, String param3, String param4, String param5) {
        OrderDetailedPageForPending fragment = new OrderDetailedPageForPending();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            ordertype = getArguments().getString(ARG_PARAM3);
            orderstatus = getArguments().getString(ARG_PARAM4);
            orderasign = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(handler==null) return;
        handler.post(runnableCode);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.order_detailed_page_pending, container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        userid = pref.getString("user_id","");

        name=view.findViewById(R.id.name);
        useraddress=view.findViewById(R.id.useraddress);

        scheduledate=view.findViewById(R.id.scheduledate);
        detaildata=view.findViewById(R.id.detaildata);
        deliveryname=view.findViewById(R.id.textView3);
        phnnumdispalay=view.findViewById(R.id.phnnumdispalay);
        textdeliverynotstarted=view.findViewById(R.id.textdeliverynotstarted);
        callsupport=view.findViewById(R.id.callsupport);
        calldelivery=view.findViewById(R.id.calldelivery);
        estimatedelivery=view.findViewById(R.id.estimatedelivery);
        deliveryboyimg=view.findViewById(R.id.imageView5);
        deliverycharge=view.findViewById(R.id.deliverycharge);
        deliveryinfo=view.findViewById(R.id.deliveryinfo);
        nodelivery=view.findViewById(R.id.nodelivery);
        framelayout=view.findViewById(R.id.framelayout);
        framelayout.requestDisallowInterceptTouchEvent(true);
        invoice=view.findViewById(R.id.invoce);
        imgback=view.findViewById(R.id.imageback);
        current=view.findViewById(R.id.current);
        img=view.findViewById(R.id.img);
        track=view.findViewById(R.id.track);
        list=view.findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        detaildata.setNestedScrollingEnabled(false);
//        typeofmode=view.findViewById(R.id.typeofmode);
//        total=view.findViewById(R.id.total);
//        itemprice=view.findViewById(R.id.itemprice);
//        service=view.findViewById(R.id.coupon);
        storename=view.findViewById(R.id.new_storename);
        phonenumber=view.findViewById(R.id.phonenumber);
        orderid=view.findViewById(R.id.orderid);
        canclebtn=view.findViewById(R.id.canclebtn);
        deliverycharge=view.findViewById(R.id.deliverycharge);
        address=view.findViewById(R.id.address);
        discountcharge=view.findViewById(R.id.discountcharge);
        orderdateschedule=view.findViewById(R.id.orderdateschedule);
        cancellayout=view.findViewById(R.id.linearLayout2);
        constraintLayoutprofile=view.findViewById(R.id.constraintLayout);


        framelayout.setNestedScrollingEnabled(false);
       // list.setNestedScrollingEnabled(true);
        calldelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNumm(calldeliverynumber);
            }
        });

        callsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNumm(callsupportnum);
            }
        });

        AppBarLayout appBarLayout = view.findViewById(R.id.appbar);
        if (appBarLayout.getLayoutParams() != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
            AppBarLayout.Behavior appBarLayoutBehaviour = new AppBarLayout.Behavior();
            appBarLayoutBehaviour.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
                @Override
                public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                    return false;
                }
            });
            layoutParams.setBehavior(appBarLayoutBehaviour);
        }

//        scroll_layout=view.findViewById(R.id.scroll_layout);
//
//        scroll_layout.setOnTouchListener( new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                if(v.getId()==R.id.scroll_layout){
//                    return false;
//                }
//                return true;
//            }
//        });

     //   framelayout.setNestedScrollingEnabled(true);
        //pickup conditions
        current.setText("Details");

//        refresh = view.findViewById(R.id.refresh);
//
//        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                update();
//                apiCall("detail",mParam1);
//                refresh.setRefreshing(false);
//            }
//        });
//
//        refresh.setColorSchemeColors(getResources().getColor(R.color.yellow));
//        refresh.setEnabled(false);
        invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                String id = pref.getString("user_id","");
                if(!orderidsend.equals("")) {
                    WebLinkFragment webLinkFragment2 = WebLinkFragment.newInstance("https://www.ellocart.com/app/apiu/invoice/" + id + "/0/" + orderidsend, "Invoice");
                    Util.loadFragment(webLinkFragment2, getActivity(),OrderDetailedPageForPending.this);
                }
            }
        });

//        if( orderasign.equals("2")){
//            canclebtn.setVisibility(View.INVISIBLE);
//        }else if( orderasign.equals("3")){  //delivery confirm
//            track.setVisibility(View.VISIBLE);
//            canclebtn.setVisibility(View.INVISIBLE);
//        }

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        canclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apicancelOrder();

            }
        });

        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //   Util.loadFragment(MapFragment.newInstance(),getActivity());
                Intent i= new Intent(getActivity(),MapsActivity.class);
                i.putExtra("orderid",orderidsend);
                startActivity(i);
            }
        });

        detaildata.setLayoutManager(new LinearLayoutManager(getContext()));
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        apiCall("detail",mParam1);
        update();
//        phonenumber .setOnClickListener(new View.OnClickListener() {
//            @Override
//            @AfterPermissionGranted(345)
//            public void onClick(View view) {
//
////                String[] perms = {Manifest.permission.CALL_PHONE};
////                if (EasyPermissions.hasPermissions(getActivity(), perms)) {
////
////                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ) {
////                        // TODO: Consider calling
////                        //    ActivityCompat#requestPermissions
////                        // here to request the missing permissions, and then overriding
////                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
////                        //                                          int[] grantResults)
////                        // to handle the case where the user grants the permission. See the documentation
////                        // for ActivityCompat#requestPermissions for more details.
////                        return;
////                    }
////
////                    if(shpphn!=null){
////                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + shpphn));
////                        startActivity(intent);
////                    }
////                } else {
////                    EasyPermissions.requestPermissions(OrderDetaiedView.this, "We need permission to make call ",345, perms);
////                }
//
//            }
//        });

        return view;
    }
    private void apiCallTrack() {

//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
//       String id = pref.getString("user_id","");
        Call<LocationResponce> getCate = ApiClient.getApiService().getLocationData("track",userid,orderidtext);
        getCate.enqueue(new Callback<LocationResponce>() {
            @Override
            public void onResponse(Call<LocationResponce> call, Response<LocationResponce> response) {
                final LocationResponce resource = response.body();
                //  pd.dismiss();
                //    Log.d("resss",resource.toString());
                //      setMapData("17.024532","81.800118","17.0239761","81.7925818");

                if(resource==null){
                    return;
                }

                if(resource.getStatus().equals("ok")){
                    if(resource.getOrder_status().equals("1")) {
                        textdeliverynotstarted.setText("Awaiting Seller Confirmation");
                    }
                    if(resource.getOrder_status().equals("2")) {
                        textdeliverynotstarted.setText("Awaiting delivery boy to pick up the delivery from seller");


                        if(resource.getOrder_assign().equals("3")){
                            //boy start
                            textdeliverynotstarted.setText("You will receive your delivery soon..");

                        }

                    }
                    if(resource.getOrder_status().equals("3")){
                        if(getActivity()!=null) {
                            Toast.makeText(getActivity(), "Order Delivered Successfully", Toast.LENGTH_LONG).show();
                            try {
                                getActivity().onBackPressed();
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                    }else if(resource.getOrder_status().equals("4")){
                        if(getActivity()!=null) {

                        //    Toast.makeText(getActivity(), "Order Cancelled By Seller", Toast.LENGTH_LONG).show();
                            getActivity().onBackPressed();

                        }
                    }

                    if(resource.getOrder_assign().equals("2") || resource.getOrder_assign().equals("3")){
                        estimatedelivery.setText("Estimated Delivery Time  " + resource.getDelivery_time());
                        if(getActivity()!=null) {
                            if (deliveryinfo.getVisibility() != View.VISIBLE) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        deliveryinfo.setVisibility(View.VISIBLE);
                                        nodelivery.setVisibility(View.GONE);

                                        Glide.with(getActivity())
                                                .load(resource.getBoy_image())
                                                .fitCenter().placeholder(R.drawable.placeholderello)
                                                .into(deliveryboyimg);

                                        deliveryname.setText(resource.getBoyName());
                                        phnnumdispalay.setText(resource.getBoy_phone());

                                        calldeliverynumber = resource.getBoy_phone();

                                    }
                                });
                            }
                        }
                    }else{

                        if(nodelivery.getVisibility()!=View.VISIBLE) {
                            if (getActivity() != null) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        deliveryinfo.setVisibility(View.GONE);
                                        nodelivery.setVisibility(View.VISIBLE);

                                    }
                                });
                            }
                        }

                    }
     //             ordertype = resource.g();
                    orderstatus = resource.getOrder_status();
                    orderasign = resource.getOrder_assign();
                    if(getActivity()!=null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                update();
                            }
                        });
                    }


                    //dummy values
                    //   setMapData("17.024532","81.800118","17.0239761","81.7925818");

                }else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<LocationResponce> call, Throwable t) {
                //   pd.dismiss();
                //   pd.dismiss();
                t.printStackTrace();

            }
        });

    }


//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                // Disallow ScrollView to intercept touch events.
//                this.getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//
//            case MotionEvent.ACTION_UP:
//                // Allow ScrollView to intercept touch events.
//                this.getParent().requestDisallowInterceptTouchEvent(false);
//                break;
//        }
//
//        // Handle MapView's touch events.
//        super.onTouchEvent(ev);
//        return true;
//    }

   public void update(){

        if(getActivity()!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(mParam2.equals("complete")){
                        canclebtn.setVisibility(View.GONE);
                        invoice.setVisibility(View.VISIBLE);
                    }

                    if(ordertype.equals("0") ){
                        nodelivery.setVisibility(View.GONE);
                        deliveryinfo.setVisibility(View.GONE);
                    }
                    if (ordertype.equals("0") && orderstatus.equals("1")) {

                        canclebtn.setVisibility(View.VISIBLE);
                        track.setVisibility(View.VISIBLE);



                    } else if (ordertype.equals("0") && orderstatus.equals("2")) {

                        canclebtn.setVisibility(View.INVISIBLE);
                        track.setVisibility(View.VISIBLE);

                    }

                    //delivery
                    if(orderasign.equals("0")){ //ekada 0 nunchi one chesa need to confirm
                        if( orderstatus.equals("1")){
                            //  holder.bar.setImageResource(R.drawable.bar);  dotundali
                            //orderasign
                            canclebtn.setVisibility(View.VISIBLE);
                            track.setVisibility(View.INVISIBLE);
                        } else
                        if(orderstatus.equals("2")){
                            //sellerconfirem
                            // holder.bar.setImageResource(R.drawable.bar);
                            canclebtn.setVisibility(View.INVISIBLE);
                            track.setVisibility(View.INVISIBLE);
                        }
                    }

                    if(orderasign.equals("1")){

                        if(orderstatus.equals("2")){
                            //sellerconfirem
                            canclebtn.setVisibility(View.INVISIBLE);
                            track.setVisibility(View.INVISIBLE);
                        }
                    }

                    if( orderstatus.equals("2")){
                        canclebtn.setVisibility(View.INVISIBLE);
                        track.setVisibility(View.INVISIBLE);
                    }
                    if(orderasign.equals("2") ){
                        //delivery accept
                        canclebtn.setVisibility(View.INVISIBLE);
                        track.setVisibility(View.INVISIBLE);

                    }else  if(orderasign.equals("3")){
                        //boy start
                        track.setVisibility(View.VISIBLE);
                        canclebtn.setVisibility(View.INVISIBLE);
                    }
                    else if( orderstatus.equals("3")){
                        //delivery confirm
                        canclebtn.setVisibility(View.INVISIBLE);
                        track.setVisibility(View.VISIBLE);
                    } else if( orderstatus.equals("4")){
                        //cancle
                        canclebtn.setVisibility(View.INVISIBLE);
                        track.setVisibility(View.INVISIBLE);
                    }

                    if(mParam2.equals("complete")){
                        canclebtn.setVisibility(View.GONE);
                    }

                }
            });
        }



   }

    public static void loadFragment(final Fragment fragment, FragmentActivity activity) {

        // create a transaction for transition here
        final FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction();

        // put the fragment in place
        transaction.replace(R.id.fragment_container, fragment);

        // this is the part that will cause a fragment to be added to backstack,
        // this way we can return to it at any time using this tag
        transaction.addToBackStack(fragment.getClass().getName());

        transaction.commit();
    }

    private void apiCall(String type,String OrderID) {
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
//        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        Call<OrdersResponce> getCate = ApiClient.getApiService().getOrders(type,id,OrderID);
        getCate.enqueue(new Callback<OrdersResponce>() {
            @Override
            public void onResponse(Call<OrdersResponce> call, Response<OrdersResponce> response) {
                final OrdersResponce resource = response.body();
                pd.dismiss();
                //  Log.d("resss",resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    System.out.println("custom raju  yes");

                    ordertype = resource.getOrder().get(0).getOrderType();
                    orderstatus = resource.getOrder().get(0).getOrderStatus();
                    orderasign = resource.getOrder().get(0).getOrderAssign();
                    callsupportnum = resource.getOrder().get(0).getSupport_phone();
                    if (resource.getOrder().get(0).getOrderStatus().equals("4")) {
                        canclebtn.setVisibility(View.INVISIBLE);
                        track.setVisibility(View.INVISIBLE);
                    }

                    if(getActivity()!=null) {
                        final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                       // transaction.replace(R.id.framelayout, new MapsFragment().newInstance(resource.getOrder().get(0).getOrderId(), id));
                        transaction.replace(R.id.framelayout, new MapsFragment().newInstance(resource.getOrder().get(0).getOrderId(), id));
                        //   transaction.replace(R.id.framelayout, new MapsFragment().newInstance("ELOCRT680"));
                        transaction.addToBackStack(null);

                        transaction.commit();
                    }
                  //  transaction.commitAllowingStateLoss();

                    if (getActivity() != null) {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                List<HomeOrders> datalist= new ArrayList<>();
                                if(resource.getOrder().get(0).getOrderPayType().toUpperCase().equals("COD")){
                                    datalist.add(new HomeOrders("Payment Mode","Cash On Delivery"));
                                }else {
                                    datalist.add(new HomeOrders("Payment Mode", resource.getOrder().get(0).getOrderPayType().toUpperCase()));
                                }
                                datalist.add(new HomeOrders("Item price","₹" + resource.getOrder().get(0).getOrderTotal()));

                                for(int i=0;i<resource.getOrder().get(0).getOther_charges().size();i++){

                                    if(resource.getOrder().get(0).getOther_charges().get(i).getP3().equals("1")){

                                        if(resource.getOrder().get(0).getOther_charges().get(i).getP4().equals("0")) {
                                            datalist.add(new HomeOrders(resource.getOrder().get(0).getOther_charges().get(i).getP1(), "-₹" + resource.getOrder().get(0).getOther_charges().get(i).getP2()));

                                        }else {
                                            datalist.add(new HomeOrders(resource.getOrder().get(0).getOther_charges().get(i).getP1(), "₹" + resource.getOrder().get(0).getOther_charges().get(i).getP2()));
                                        }
                                    }
                                }
                                datalist.add(new HomeOrders("Total","₹" + resource.getOrder().get(0).getOrderFinal()));
                                detailedAdapter= new DetailedAdapter(getActivity(),datalist);
                                detaildata.setAdapter(detailedAdapter);

                                orderidsend = resource.getOrder().get(0).getOrderId();
                            //    itemprice.setText("Item price : ₹" + resource.getOrder().get(0).getOrderTotal() );
                             //   total.setText("Total : ₹" + resource.getOrder().get(0).getOrderFinal());
                           //     typeofmode.setText(resource.getOrder().get(0).getOrderPayType());
                          //      service.setText("Service tax : ₹" + resource.getOrder().get(0).getOrderTax());
                           //   deliverycharge.setText("Delivery Charges : ₹" + resource.getOrder().get(0).getOrderDelivery());

                                name.setText(resource.getAddress().get(0).getAddrName());
                                useraddress.setText(resource.getAddress().get(0).getAddrAddress() + "," + resource.getAddress().get(0).getAddrCity());

                                address.setText(resource.getOrder().get(0).getSellerStoreAddress() + "," + resource.getOrder().get(0).getSellerCity());
                               // phonenumber.setText(resource.getOrder().get(0).getSellerPhone());
                                storename.setText(resource.getOrder().get(0).getSellerStoreName());
                                orderid.setText("ORDER ID : " + resource.getOrder().get(0).getOrderId());
                                //orderdateschedule.setText("Odered : " + resource.getOrder().get(0).getOrderDate() + ", Scheduled : " + resource.getOrder().get(0).getOrderPickDate());
                                orderdateschedule.setText("Date : "+resource.getOrder().get(0).getOrderDate()+" , Time : "+resource.getOrder().get(0).getOrder_time());

                                //schedule

                                if(resource.getOrder().get(0).getOrderPickDate().equals(resource.getOrder().get(0).getOrderDate())){

                                    scheduledate.setBackgroundColor( Color.parseColor("#ffffff"));
                                    scheduledate.setTextColor( Color.parseColor("#000000"));

                                }else{

                                    scheduledate.setBackgroundResource(R.drawable.btncornorred);

                                    scheduledate.setTextColor( Color.parseColor("#ffffff"));

                                }

                                scheduledate.setText("Scheduled : "+resource.getOrder().get(0).getOrderPickDate());

                                adapter = new OrderItemAdapter(getActivity(), resource.getProducts(), resource.getOrder(), mParam2);
                                list.setAdapter(adapter);

                                orderidtext = resource.getOrder().get(0).getOrderId();

//                                Glide.with(getActivity())
//                                        .load(resource.getOrder().get(0).getSellerStoreImage())
//                                        .fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
//                                        .placeholder(R.drawable.placeholderello).into(img);
                            }
                        });

//                    String data="";
//                    for(int i=0;i<resource.getOrder().get(0).getOther_charges().size();i++){
//                        if(resource.getOrder().get(0).getOther_charges().get(i).getP3().equals("1")){
//                            data= data+ resource.getOrder().get(0).getOther_charges().get(i).getP1()+"  : ₹"+ resource.getOrder().get(0).getOther_charges().get(i).getP2()+"\n";
//                        }
//                    }
//
//                       discountcharge.setText(data);

                    }
//apiCallTrack();
                    if (!ordertype.equals("0")) {

//
//                        handler.postDelayed(new Runnable() {
//                            public void run() {
//                              //  System.out.println("myHandler: here!"); // Do your work here
//
//                                handler.postDelayed(this, delay);
//                            }
//                        }, delay);
//                    }


                        handler = new Handler();


                        // Define the task to be run here
                        runnableCode = new Runnable() {
                            @Override
                            public void run() {
                                //  System.out.println("myHandler: here!"); // Do your work here
                                apiCallTrack();
                                handler.postDelayed(runnableCode, delay);
                            }
                        };

                        handler.post(runnableCode);


                        //  coupon,total,typeofmode;

//                    if(resource.getOrder().size()!=0) {
//                        if (resource.getOrder().get(0).getOrderType().equals("0")) {
//                            track.setVisibility(View.VISIBLE);
//                        } else {
//                            track.setVisibility(View.GONE);
//                        }
//                    }

                    } else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                    }

                }

            }

            @Override
            public void onFailure(Call<OrdersResponce> call, Throwable t) {
           //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    public void apicancelOrder(){

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
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        Call<StoresCatResponce> getCate = ApiClient.getApiService().cancelorder(id,"update",orderidsend,"4");
        getCate.enqueue(new Callback<StoresCatResponce>() {
            @Override
            public void onResponse(Call<StoresCatResponce> call, Response<StoresCatResponce> response) {
                final StoresCatResponce resource = response.body();

                pd1.dismiss();
                // Log.d("resss", resource.toString());
                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {
                      getActivity().onBackPressed();
//                    adapterUpdate(resource.getStores());
//                    B2BAdapterSettingpage adapter = new B2BAdapterSettingpage(getActivity(),resource.getB2orders());
//                    dataList.setAdapter(adapter);

                } else {
//                    if (resource.get() != "") {
//                        Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }
            @Override
            public void onFailure(Call<StoresCatResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //     Toast.makeText(getContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });


    }

//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//
//        switch (view.getId()){
//            case  R.id.framelayout:
//                scroll_layout.requestDisallowInterceptTouchEvent(true);
//
//                break;
//
//            default:
//                scroll_layout.requestDisallowInterceptTouchEvent(false);
//        }
//
//        return true;
//    }

    @AfterPermissionGranted(345)
    public void callNumm(String num){
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(getActivity(), perms)) {

            if (ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            if (num != null) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
                startActivity(intent);
            }
        } else {
            EasyPermissions.requestPermissions(getActivity(), "We need permission to make call ", 345, perms);
        }

    }



    @Override
    public void onPause() {
        super.onPause();
        if(handler==null) return;
        handler.removeCallbacks(runnableCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(handler==null) return;

        handler.removeCallbacks(runnableCode);
    }


}