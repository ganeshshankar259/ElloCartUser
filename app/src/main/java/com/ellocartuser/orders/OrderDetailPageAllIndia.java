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

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.R;
import com.ellocartuser.Register;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.OrdersResponce;
import com.ellocartuser.apiservices.Responce.StoresCatResponce;
import com.ellocartuser.setting.webfragment.WebLinkFragment;
import com.ellocartuser.utils.Util;
import com.ellocartuser.utils.WebViewActivity;
import com.google.android.material.appbar.AppBarLayout;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderDetailPageAllIndia#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderDetailPageAllIndia extends Fragment implements EasyPermissions.PermissionCallbacks {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    ImageView trackicon1, trackicon2, trackicon3;
    View track1view, track2view;
    TextView track1text, track2text, track3text, track2textlink;
    Handler handler;
    int delay = 6000; // 1000 milliseconds == 1 second
    Runnable runnableCode;
    // ScrollView scroll_layout;
    //   FrameLayout framelayout;
    LinearLayout cancellayout;
    ConstraintLayout constraintLayoutprofile;
    ProgressDialog pd, pd1;
    String orderidtext;
    TextView phnnumdispalay, deliveryname, estimatedelivery, discountcharge, current, orderid, orderdateschedule, canclebtn, address, storename, phonenumber, itemprice, service, total, typeofmode, deliverycharge;
    RecyclerView list;
    Button calldelivery, callsupport;
    ImageView img, imgback;
    OrderItemAdapter adapter;
    ConstraintLayout deliveryinfo;
    LinearLayout nodelivery;
    Button invoice;
    TextView textdeliverynotstarted;
    String userid;
    String calldeliverynumber, callsupportnum = "";
    ImageView deliveryboyimg;
    String orderidsend = "", ordertype = "", orderstatus = "", currentorderid = "", orderasign = "", odrdate = "";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public OrderDetailPageAllIndia() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderDetailPageAllIndia.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderDetailPageAllIndia newInstance(String param1, String param2, String param3, String param4, String param5) {
        OrderDetailPageAllIndia fragment = new OrderDetailPageAllIndia();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_detail_page_all_india, container, false);


        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        userid = pref.getString("user_id", "");

        track2textlink = view.findViewById(R.id.track2textlink);
        track1text = view.findViewById(R.id.track1text);
        track2text = view.findViewById(R.id.track2text);
        track3text = view.findViewById(R.id.track3text);
        track1view = view.findViewById(R.id.track1view);
        track2view = view.findViewById(R.id.track2view);

        trackicon1 = view.findViewById(R.id.trackicon1);
        trackicon2 = view.findViewById(R.id.trackicon2);
        trackicon3 = view.findViewById(R.id.trackicon3);

        phnnumdispalay = view.findViewById(R.id.phnnumdispalay);
        textdeliverynotstarted = view.findViewById(R.id.textdeliverynotstarted);
        callsupport = view.findViewById(R.id.callsupport);
        calldelivery = view.findViewById(R.id.calldelivery);
        estimatedelivery = view.findViewById(R.id.estimatedelivery);
        deliveryboyimg = view.findViewById(R.id.imageView5);
        deliverycharge = view.findViewById(R.id.deliverycharge);
        deliveryinfo = view.findViewById(R.id.deliveryinfo);
        nodelivery = view.findViewById(R.id.nodelivery);
        //    framelayout=view.findViewById(R.id.framelayout);
        //    framelayout.requestDisallowInterceptTouchEvent(true);
        invoice = view.findViewById(R.id.invoce);
        imgback = view.findViewById(R.id.imageback);
        current = view.findViewById(R.id.current);
        img = view.findViewById(R.id.img);
        //  track=view.findViewById(R.id.track);
        list = view.findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        typeofmode = view.findViewById(R.id.typeofmode);
        total = view.findViewById(R.id.total);
        itemprice = view.findViewById(R.id.itemprice);
        service = view.findViewById(R.id.coupon);
        storename = view.findViewById(R.id.storename);
        phonenumber = view.findViewById(R.id.phonenumber);
        orderid = view.findViewById(R.id.orderid);
        canclebtn = view.findViewById(R.id.canclebtn);
        deliverycharge = view.findViewById(R.id.deliverycharge);
        address = view.findViewById(R.id.address);
        discountcharge = view.findViewById(R.id.discountcharge);
        orderdateschedule = view.findViewById(R.id.orderdateschedule);
        cancellayout = view.findViewById(R.id.linearLayout2);
        constraintLayoutprofile = view.findViewById(R.id.constraintLayout);

//        framelayout.setNestedScrollingEnabled(false);
        // list.setNestedScrollingEnabled(true);
//        calldelivery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                callNumm(calldeliverynumber);
//            }
//        });

        callsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNumm(callsupportnum);
            }
        });


        track2textlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String url = track2textlink.getText().toString();

//              Intent i = new Intent(Intent.ACTION_VIEW);
//              i.setData(Uri.parse(url));
                Intent i = new Intent(getActivity(), WebViewActivity.class);
                i.putExtra("url", url);
                i.putExtra("name", "Terms of Use");
                startActivity(i);
            }
        });
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


        invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                String id = pref.getString("user_id", "");
                if (!orderidsend.equals("")) {
                    WebLinkFragment webLinkFragment2 = WebLinkFragment.newInstance("https://www.ellocart.com/app/apiu/invoice/" + id + "/0/" + orderidsend, "Invoice");
                    Util.loadFragment(webLinkFragment2, getActivity(), OrderDetailPageAllIndia.this);
                }
            }
        });

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


        list.setLayoutManager(new LinearLayoutManager(getContext()));
        apiCall("detail", mParam1);
        update();
        phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            @AfterPermissionGranted(345)
            public void onClick(View view) {

//                String[] perms = {Manifest.permission.CALL_PHONE};
//                if (EasyPermissions.hasPermissions(getActivity(), perms)) {
//
//                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return;
//                    }
//
//                    if(shpphn!=null){
//                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + shpphn));
//                        startActivity(intent);
//                    }
//                } else {
//                    EasyPermissions.requestPermissions(OrderDetaiedView.this, "We need permission to make call ",345, perms);
//                }

            }
        });

        return view;
    }

    public void update() {

        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (mParam2.equals("complete")) {
                        canclebtn.setVisibility(View.GONE);
                        invoice.setVisibility(View.VISIBLE);
                    }

                    if (ordertype.equals("0")) {
                        nodelivery.setVisibility(View.GONE);
                        deliveryinfo.setVisibility(View.GONE);
                    }
                    if (ordertype.equals("0") && orderstatus.equals("1")) {

                        canclebtn.setVisibility(View.VISIBLE);
                        //  track.setVisibility(View.VISIBLE);

                    } else if (ordertype.equals("0") && orderstatus.equals("2")) {

                        canclebtn.setVisibility(View.GONE);
                        // track.setVisibility(View.VISIBLE);

                    }

                    //delivery
                    if (orderasign.equals("0")) { //ekada 0 nunchi one chesa need to confirm
                        if (orderstatus.equals("1")) {
                            //  holder.bar.setImageResource(R.drawable.bar);  dotundali
                            //orderasign
                            canclebtn.setVisibility(View.VISIBLE);
                            //   track.setVisibility(View.INVISIBLE);
                        } else if (orderstatus.equals("2")) {
                            //sellerconfirem
                            // holder.bar.setImageResource(R.drawable.bar);
                            canclebtn.setVisibility(View.GONE);
                            //   track.setVisibility(View.INVISIBLE);
                        }
                    }

                    if (orderasign.equals("1")) {

                        if (orderstatus.equals("2")) {
                            //sellerconfirem
                            canclebtn.setVisibility(View.GONE);
                            //    track.setVisibility(View.INVISIBLE);
                        }
                    }

                    if (orderstatus.equals("2")) {
                        canclebtn.setVisibility(View.GONE);
                        //    track.setVisibility(View.INVISIBLE);
                    }
                    if (orderasign.equals("2")) {
                        //delivery accept
                        canclebtn.setVisibility(View.GONE);
                        //  track.setVisibility(View.INVISIBLE);

                    } else if (orderasign.equals("3")) {
                        //boy start
                        //   track.setVisibility(View.VISIBLE);
                        canclebtn.setVisibility(View.GONE);
                    } else if (orderstatus.equals("3")) {
                        //delivery confirm
                        canclebtn.setVisibility(View.GONE);
                        //     track.setVisibility(View.VISIBLE);
                    } else if (orderstatus.equals("4")) {
                        //cancle
                        canclebtn.setVisibility(View.GONE);
                        //    track.setVisibility(View.INVISIBLE);
                    }

//                    if(mParam2.equals("complete")){
//
//
//                        track1text.setText("Order Placed ");
//                        track1text.setTextColor(Color.BLACK);
//                        trackicon1.setImageResource(R.drawable.trackactive);
//
//                        track2text.setText("Order Confirmed Intransit");
//                        track2text.setTextColor(Color.BLACK);
//                        track1view.setBackgroundResource(R.drawable.dottrackyellow);
//                        trackicon2.setImageResource(R.drawable.trackactive);
//
//                        track3text.setText("Order Completed");
//                        track3text.setTextColor(Color.BLACK);
//                        track2view.setBackgroundResource(R.drawable.dottrackyellow);
//                        trackicon3.setImageResource(R.drawable.trackactive);
//
//                        canclebtn.setVisibility(View.GONE);
//                    }

                }
            });
        }

//        View track1view,track2view;
//        TextView track1text,track2text,track3text;


    }

    private void apiCall(String type, String OrderID) {
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
        String id = pref.getString("user_id", "");
        Call<OrdersResponce> getCate = ApiClient.getApiService().getOrders(type, id, OrderID);
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

                    odrdate = resource.getOrder().get(0).getOrder_time() + "," + resource.getOrder().get(0).getOrderDate();
                    track1text.setText("Order placed at " + odrdate);

                    ordertype = resource.getOrder().get(0).getOrderType();
                    orderstatus = resource.getOrder().get(0).getOrderStatus();
                    orderasign = resource.getOrder().get(0).getOrderAssign();
                    callsupportnum = resource.getOrder().get(0).getSupport_phone();
                    if (resource.getOrder().get(0).getOrderStatus().equals("4")) {
                        canclebtn.setVisibility(View.GONE);
                        //track.setVisibility(View.INVISIBLE);
                    }

                    //  transaction.commitAllowingStateLoss();

                    if (getActivity() != null) {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (resource.getOrder().get(0).getOrder_track() != null) {
                                    track2textlink.setText(resource.getOrder().get(0).getOrder_track());
                                    track2textlink.setTextColor(Color.BLUE);
                                }
                                orderidsend = resource.getOrder().get(0).getOrderId();
                                itemprice.setText("Item price : ₹" + resource.getOrder().get(0).getOrderTotal());
                                total.setText("Total : ₹" + resource.getOrder().get(0).getOrderFinal());
                                typeofmode.setText(resource.getOrder().get(0).getOrderPayType());
                                service.setText("Service tax : ₹" + resource.getOrder().get(0).getOrderTax());
                                if (resource.getOrder().get(0).getOrderDelivery().equals("0")) {
                                    deliverycharge.setText("Delivery Charges : Free");
                                } else {
                                    deliverycharge.setText("Delivery Charges : ₹" + resource.getOrder().get(0).getOrderDelivery());
                                }
                                discountcharge.setText("Coupon Amount : ₹" + resource.getOrder().get(0).getOrder_coupon());
                                address.setText(resource.getOrder().get(0).getSellerStoreAddress() + "," + resource.getOrder().get(0).getSellerCity());
                                phonenumber.setText(resource.getOrder().get(0).getSellerPhone());
                                storename.setText(resource.getOrder().get(0).getSellerStoreName());
                                orderid.setText("ORDER ID : " + resource.getOrder().get(0).getOrderId());
                                //orderdateschedule.setText("Odered : " + resource.getOrder().get(0).getOrderDate() + ", Scheduled : " + resource.getOrder().get(0).getOrderPickDate());
                                orderdateschedule.setText("Date : " + resource.getOrder().get(0).getOrderDate() + " , Time : " + resource.getOrder().get(0).getOrder_time());

                                adapter = new OrderItemAdapter(getActivity(), resource.getProducts(), resource.getOrder(), mParam2);
                                list.setAdapter(adapter);

                                orderidtext = resource.getOrder().get(0).getOrderId();

                                Glide.with(getActivity())
                                        .load(resource.getOrder().get(0).getSellerStoreImage())
                                        .fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                                        .placeholder(R.drawable.placeholderello).into(img);
                            }
                        });

                    }


                    if (orderstatus.equals("1")) {
                        //placed order
                        //     track1text.setText("Order Placed ");
                        track1text.setTextColor(Color.BLACK);
                        trackicon1.setImageResource(R.drawable.trackactive);

                    } else if (orderstatus.equals("2")) {
                        //order confirmed

                        //  track1text.setText("Order Placed ");
                        track1text.setTextColor(Color.BLACK);
                        trackicon1.setImageResource(R.drawable.trackactive);

                        track2text.setText("Order Confirmed In transit");
                        track2text.setTextColor(Color.BLACK);
                        track1view.setBackgroundResource(R.drawable.dottrackyellow);
                        trackicon2.setImageResource(R.drawable.trackactive);


                    } else if (orderstatus.equals("3")) {
                        //order completed

                        //  track1text.setText("Order Placed ");
                        track1text.setTextColor(Color.BLACK);
                        trackicon1.setImageResource(R.drawable.trackactive);

                        track2text.setText("Order confirmed In transit");
                        track2text.setTextColor(Color.BLACK);
                        track1view.setBackgroundResource(R.drawable.dottrackyellow);
                        trackicon2.setImageResource(R.drawable.trackactive);

                        track3text.setText("Order completed");
                        track3text.setTextColor(Color.BLACK);
                        track2view.setBackgroundResource(R.drawable.dottrackyellow);
                        trackicon3.setImageResource(R.drawable.trackactive);

                    } else if (orderstatus.equals("4")) {
                        //order completed

                        //  track1text.setText("Order Placed ");
                        track1text.setTextColor(Color.BLACK);
                        trackicon1.setImageResource(R.drawable.trackactive);


                        track1view.setBackgroundResource(R.drawable.dottrackyellow);
                        //    trackicon2.setImageResource(R.drawable.trackactive);

                        track3text.setText("Order cancelled");
                        track3text.setTextColor(Color.RED);
                        track2view.setBackgroundResource(R.drawable.dottrackyellow);

                    }
//apiCallTrack();

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

    public void apicancelOrder() {

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
        String id = pref.getString("user_id", "");
        Call<StoresCatResponce> getCate = ApiClient.getApiService().cancelorder(id, "update", orderidsend, "4");
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
    public void callNumm(String num) {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(getActivity(), perms)) {

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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


}