package com.ellocartuser.home.notification;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.B2BResponce;
import com.ellocartuser.apiservices.Responce.OrdersResponce;
import com.ellocartuser.home.homefragment.CategoryFragment;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.orders.MapsFragment;
import com.ellocartuser.orders.OrderDetailedPage;
import com.ellocartuser.orders.OrderDetailedPageForPending;

import com.ellocartuser.utils.Util;
import com.ellocartuser.utils.WebViewActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment implements  NotificationAdapter.OnItemClickedNoti {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters

    NotificationAdapter.OnItemClickedNoti onclick;
    RecyclerView dataList;
    ProgressDialog pd1;
    ImageView imageback;
    LinearLayout noorder;

    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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

        View view = inflater.inflate(R.layout.activity_notification_screen, container, false);

        onclick=this;

        imageback =view.findViewById(R.id.imageback);
        noorder =view.findViewById(R.id.noorder);
        dataList = view.findViewById(R.id.catList);
        dataList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        apicall();

        return view;

    }

    private void apicall() {

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
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        Call<B2BResponce> getCate = ApiClient.getApiService().getNotifications("user",id);
        getCate.enqueue(new Callback<B2BResponce>() {
            @Override
            public void onResponse(Call<B2BResponce> call, Response<B2BResponce> response) {
                final B2BResponce resource = response.body();

                pd1.dismiss();
                Log.d("responce notification", resource.toString());
                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {
                    noorder.setVisibility(View.GONE);
                    dataList.setVisibility(View.VISIBLE);
                    if(getActivity()!=null) {
                        NotificationAdapter adapter = new NotificationAdapter(getActivity(), resource.getNotifys(), onclick);
                        dataList.setAdapter(adapter);
                    }
//
                } else {
                    noorder.setVisibility(View.VISIBLE);
                    dataList.setVisibility(View.GONE);
                }

            }
            @Override
            public void onFailure(Call<B2BResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //     Toast.makeText(getContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onItemClickedNoti(int position, String p1, String p2, String p3) {
//        if(np1.equals("ORDER")){
//
//            pushFragment(OrdersMainClass.newInstance("",""));
//
//        }

        if(p1.equals("HOME")){
          //  pushFragment(OrdersMainClass.newInstance("",""));
        } else if(p1.equals("ORDER")){

          //  Util.loadFragment123(OrderDetailedPageForPending.newInstance(data.optString("p2", null), "","","",""), (FragmentActivity)context);

        //    pushFragment(OrderDetailedPageForPending.newInstance(p2, "","","",""));

            apiCall("detail",p2);

//                    Intent intent = new Intent(context, NotificationScreen.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);

        }else if(p1.equals("STORE")){
            pushFragment(CategoryFragment.newInstance(p3, p2, ""));
           // Util.loadFragment123(, (FragmentActivity)context);

        }else if(p1.equals("URL")){
            Intent i=new Intent(getActivity(), WebViewActivity.class);
            i.putExtra("url",p2);
            i.putExtra("name","");
            getActivity().startActivity(i);
        }


    }

    public void  pushFragment(Fragment selectedFragment){


        final FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                .beginTransaction();

        // put the fragment in place
        transaction.replace(R.id.fragment_container1, selectedFragment);

        // this is the part that will cause a fragment to be added to backstack,
        // this way we can return to it at any time using this tag
        //    transaction.addToBackStack(selectedFragment.getClass().getName());   //for back stack
        transaction.addToBackStack(new homefragment().getClass().getName());
        transaction.commit();


    }


    private void apiCall(String type,String OrderID) {
        ProgressDialog  pd = new ProgressDialog(getActivity());
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
//
//                    ordertype = resource.getOrder().get(0).getOrderType();
//                    orderstatus = ;
//                    orderasign = resource.getOrder().get(0).getOrderAssign();
//
                    if(resource.getOrder().get(0).getOrderStatus()!=null) {
                        if (resource.getOrder().get(0).getOrderStatus().equals("3") || resource.getOrder().get(0).getOrderStatus().equals("4")) {

                            pushFragment(OrderDetailedPage.newInstance(OrderID, "", "", "", ""));


                        } else {

                            pushFragment(OrderDetailedPageForPending.newInstance(OrderID, "", "", "", ""));

                        }
                    }


                    } else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
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


}