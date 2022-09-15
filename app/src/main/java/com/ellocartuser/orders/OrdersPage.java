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
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.apiservices.Responce.OrdersResponce;
import com.ellocartuser.home.adapterandmodel.NearByStoreAdapter;
import com.ellocartuser.utils.Util;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersPage extends Fragment implements PendingOrdersAdapter.OnItemClickedpend,CompletedAdapter.OnItemClickedcomp,EasyPermissions.PermissionCallbacks {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressDialog pd;
    // TODO: Rename and change types of parameters
    private String from;
    private String mParam2;
    RecyclerView recyclerView;
    PendingOrdersAdapter pendingOrdersAdapter;
    CompletedAdapter completedAdapter;

    PendingOrdersAdapter.OnItemClickedpend pendonclick;
    CompletedAdapter.OnItemClickedcomp componclick;
    LinearLayout noorder;
    SwipeRefreshLayout refresh;
    public OrdersPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompletedOrders.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdersPage newInstance(String param1, String param2) {
        OrdersPage fragment = new OrdersPage();
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
            from = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_completed_orders, container, false);
        pendonclick=this;
        componclick=this;
        noorder=view.findViewById(R.id.noorder);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refresh = view.findViewById(R.id.refresh);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(from.equals("pending")){
                    apiCall("pending");
                }else if(from.equals("completed")){
                    apiCall("completed");
                }else if(from.equals("cancelled")){
                    apiCall("cancelled");
                }
                refresh.setRefreshing(false);
            }
        });
        refresh.setColorSchemeColors(getResources().getColor(R.color.yellow));


//        if(from.equals("pending")){
//            apiCall("pending");
//        }else if(from.equals("completed")){
//            apiCall("completed");
//        }else if(from.equals("cancelled")){
//            apiCall("cancelled");
//        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(from.equals("pending")){
            apiCall("pending");
        }else if(from.equals("completed")){
            apiCall("completed");
        }else if(from.equals("cancelled")){
            apiCall("cancelled");
        }
    }

    private void apiCall(String type) {
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
        Call<OrdersResponce> getCate = ApiClient.getApiService().getOrders(type,id,"");
        getCate.enqueue(new Callback<OrdersResponce>() {
            @Override
            public void onResponse(Call<OrdersResponce> call, Response<OrdersResponce> response) {
                final OrdersResponce resource = response.body();
                pd.dismiss();
              //  Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){
                    noorder.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                     if(resource.type.equals("pending")){

                         if(getActivity()!=null) {

                             pendingOrdersAdapter=new PendingOrdersAdapter(getActivity(),resource.getOrders(),pendonclick);
                             recyclerView.setAdapter(pendingOrdersAdapter);
                         }

                     }else   if(resource.type.equals("completed")){ //(resource.type.equals("completed"))

                         if(getActivity()!=null) {
                             completedAdapter=new CompletedAdapter(getActivity(),resource.getOrders(),componclick,resource.type);
                             recyclerView.setAdapter(completedAdapter);
                         }
                     }

                }else {
                    noorder.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
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


    @Override
    public void onItemClickComp(int position, String orderid,String type,String num,String from,String order2) {
            if(type.equals("call")) {

                callNumm(num);

            }else if(order2.equals("1")) {

                Util.loadFragment(new OrderDetailPageAllIndia().newInstance(orderid,from,"","",""),getActivity(),OrdersPage.this);

            }else {
                    Util.loadFragment(new OrderDetailedPage().newInstance(orderid,from,"","",""),getActivity(),OrdersPage.this);
            }
    }

    @Override
    public void onItemClickPend(int position, String orderid,String type,String num,String ordertype,String orderstatus,String order_asign,String ordertype2) {

        if(type.equals("call")) {
          callNumm(num);
        }else if(ordertype2.equals("1")){
            // Util.loadFragment(new OrderDetailedPage().newInstance(orderid,"pending",ordertype,orderstatus,order_asign),getActivity());
            Util.loadFragment(new OrderDetailPageAllIndia().newInstance(orderid,"pending",ordertype,orderstatus,order_asign),getActivity(),OrdersPage.this);
        }else{
            Util.loadFragment(new OrderDetailedPageForPending().newInstance(orderid,"pending",ordertype,orderstatus,order_asign),getActivity(),OrdersPage.this);

        }

    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

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
}