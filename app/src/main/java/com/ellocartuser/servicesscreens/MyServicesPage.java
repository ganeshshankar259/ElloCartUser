package com.ellocartuser.servicesscreens;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.ServiceResponce;
import com.ellocartuser.servicesscreens.category.ServiceCategoryPage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyServicesPage#newInstance} factory method to
 * create an instance of this fragment.
 */

public class MyServicesPage extends Fragment implements MyServicesAdapter.OnItemClickedMyServ {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView catList;
    ImageView imageback;
    ProgressDialog pd1;
    TextView addserv;
    LinearLayout noorder;
    MyServicesAdapter.OnItemClickedMyServ onItemClicked;
    public MyServicesPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyServicesPage.
     */
    // TODO: Rename and change types and number of parameters
    public static MyServicesPage newInstance(String param1, String param2) {
        MyServicesPage fragment = new MyServicesPage();
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
       View  view = inflater.inflate(R.layout.fragment_my_services_page, container, false);


        noorder=view.findViewById(R.id.noorder);
        catList=view.findViewById(R.id.catList);
        imageback=view.findViewById(R.id.imageback);
        catList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        onItemClicked=this;
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        addserv=view.findViewById(R.id.addserv);
        addserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apicallCheckUSersubscribe();
//                Intent ii =  new Intent(getActivity(), ServiceCategoryPage.class);
//                getActivity().startActivity(ii);
            }
        });

        apicall();
        return  view;
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
//           replacepd1();
//        pd1.show();
//        @Field("type") RequestBody type,

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

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
                    // check is alrady have posts or show to add postos , here api

                    Intent ii =  new Intent(getActivity(), ServiceCategoryPage.class);
                    startActivity(ii);


                } else {

                    //send to subscription page

                    Intent subi = new Intent(getActivity(), SubscritionScreen.class);
                    startActivity(subi);

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
        pd1.show();
//        pd1.show();
//        @Field("type") RequestBody type,

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
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
                    addserv.setVisibility(View.VISIBLE);
                    addserv.setText("Add New Service");
                    MyServicesAdapter adapter = new MyServicesAdapter(getActivity(), resource.getPosts(), onItemClicked);
                    catList.setAdapter(adapter);

                    catList.setVisibility(View.VISIBLE);
                    noorder.setVisibility(View.GONE);
                } else {
                   // addserv.setVisibility(View.VISIBLE);
                    noorder.setVisibility(View.VISIBLE);
                    catList.setVisibility(View.GONE);
                    addserv.setText("Add Service");
                    //share to subscription page

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


    @Override
    public void onItemClickProduct(int position, String catid) {

        Intent ii = new Intent(getActivity(),PostDetailedPage.class);
        ii.putExtra("subcat",catid);
        ii.putExtra("from","myservice");
        startActivity(ii);

    }
}