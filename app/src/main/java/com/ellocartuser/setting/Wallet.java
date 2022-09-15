package com.ellocartuser.setting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.apiservices.Responce.StoresCatResponce;
import com.ellocartuser.home.adapterandmodel.ProductSearchAdapter;
import com.ellocartuser.home.adapterandmodel.StatementAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Wallet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Wallet extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
RecyclerView catList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView imgback;
    TextView amt;
    EditText refrel;
    Button btnrefrel;
    ProgressDialog pd;
    String phnnum="";
    public Wallet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Wallet.
     */
    // TODO: Rename and change types and number of parameters
    public static Wallet newInstance(String param1, String param2) {
        Wallet fragment = new Wallet();
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
        View view =inflater.inflate(R.layout.fragment_wallet, container, false);
        btnrefrel=view.findViewById(R.id.btnrefrel);
        amt=view.findViewById(R.id.amt);
        refrel=view.findViewById(R.id.refrel);
        imgback=view.findViewById(R.id.imageback);
        catList=view.findViewById(R.id.catList);
        catList.setLayoutManager(new LinearLayoutManager(getContext()));

        apiCall();
        apicallTransaction();

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        btnrefrel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phnnum.length() != 0) {
                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "ElloCart Driver");
                        String shareMessage = "\nHi install Ellocart – local online shop made easy. Enter my code (" + phnnum + ") after you install. Once you installed you will get 50 Rs voucher into your wallet as a gift.\n\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.ellocartdelivery.sunraise\n\n";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "choose one"));
                    } catch (Exception e) {
                        //e.toString();
                    }
                }
            }
        });

        return view;
    }


    private void apiCall() {

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
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        Call<MyProfileResponce> getCate = ApiClient.getApiService().getProgile("user",id);
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                pd.dismiss();
               // Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){

                    phnnum=resource.getProfile().get(0).getUserPhone();
                    refrel.setText(resource.getProfile().get(0).getUserPhone());
                    //  address.setText(resource.getProfile().get(0).get());
                    amt.setText("₹ "+resource.getProfile().get(0).getUserWallet());

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
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }


    public void apicallTransaction(){

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

        Call<StoresCatResponce> getCate = ApiClient.getApiService().getStatements(id);
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

                    StatementAdapter adapter=new StatementAdapter(getActivity(), resource.getStatement(),"list");
                    catList.setAdapter(adapter);

//                    adapterUpdate(resource.getList());
                    //      Log.d("raju fav",resource.getStores().get(0).getWish_status());

                    if(getActivity()!=null) {
//                        productsearchadapter = new ProductSearchAdapter(getActivity(), resource.getList(), productsearchlistioner,"list");
//                        recyclerViewproductsearch.setAdapter(productsearchadapter);
                    }

                } else {

                }

            }

            @Override
            public void onFailure(Call<StoresCatResponce> call, Throwable t) {
                //   pd.dismiss();
                //  pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

}