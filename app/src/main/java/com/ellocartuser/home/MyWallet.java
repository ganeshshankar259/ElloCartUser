package com.ellocartuser.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.home.adapterandmodel.PromoTransactionAdapter;
import com.ellocartuser.home.adapterandmodel.PromoWallet;
import com.ellocartuser.home.adapterandmodel.Statement;
import com.ellocartuser.home.adapterandmodel.WalletTransactionAdapter;
import com.ellocartuser.home.mainscreen.HomeScreen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyWallet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyWallet extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    List<Statement> statementslist=new ArrayList<>();
    List<PromoWallet> promoWalletslist=new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView dataList;
    ProgressDialog pd1;
    TextView amount,amountpromo,text;
    AppCompatButton ellowalletlist,promowalletlist;
    ImageView imageback;
    LinearLayout noorder;

    public MyWallet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyWallet.
     */
    // TODO: Rename and change types and number of parameters
    public static MyWallet newInstance(String param1, String param2) {
        MyWallet fragment = new MyWallet();
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
        View view = inflater.inflate(R.layout.fragment_my_wallet, container, false);

        text=view.findViewById(R.id.textView19);
        noorder =view.findViewById(R.id.noorder);
        amount =view.findViewById(R.id.amountello);
        amountpromo =view.findViewById(R.id.amountpromo);
        ellowalletlist =view.findViewById(R.id.ellowalletlist);
        promowalletlist =view.findViewById(R.id.promowalletlist);
        imageback =view.findViewById(R.id.imageback);
        dataList = view.findViewById(R.id.catList);
        dataList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        ellowalletlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(statementslist.size()>0){
                    text.setText("Ello Wallet List");

                    loadData("wallet");
                }else{
                  //  apicall();
                }

            }
        });


        promowalletlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(promoWalletslist.size()>0){
                    text.setText("Ello Promo List");

                    loadData("promo");
                }else{
                 //   apicall();
                }
            }
        });

        apicall();
        text.setText("");

        return view;
    }

    private void apicall() {

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<MyProfileResponce> getCate = ApiClient.getApiService().get_wallet(id);
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                //   pd.dismiss();
                //  Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }

                if(resource.getStatus().equals("ok")){
                    amount.setText("₹"+resource.getEllo_wallet());
                    amountpromo.setText("₹"+resource.getPromo_wallet());
                    if(resource.getWalletlist()!=null) {
                        if (resource.getWalletlist().size() != 0) {
                            noorder.setVisibility(View.GONE);
                            dataList.setVisibility(View.VISIBLE);
                            statementslist = resource.getWalletlist();

                            text.setText("Ello Wallet List");
                            loadData("wallet");
                        } else {

                            noorder.setVisibility(View.VISIBLE);
                            dataList.setVisibility(View.GONE);
                        }
                    }
                    if(resource.getPromowallet()!=null) {

                        if (resource.getPromowallet().size() != 0) {
                            noorder.setVisibility(View.GONE);
                            dataList.setVisibility(View.VISIBLE);
                            promoWalletslist = resource.getPromowallet();

                        } else {

                            noorder.setVisibility(View.VISIBLE);
                            dataList.setVisibility(View.GONE);
                        }

                    }
                }else {

                    noorder.setVisibility(View.VISIBLE);
                    dataList.setVisibility(View.GONE);
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<MyProfileResponce> call, Throwable t) {
                //   pd.dismiss();
                //     pd.dismiss();
                t.printStackTrace();
                //   Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void loadData(String type){

        if(type=="wallet"){
            WalletTransactionAdapter adapter =  new WalletTransactionAdapter(getActivity(),statementslist);
            dataList.setAdapter(adapter);
        }else if(type=="promo"){
            PromoTransactionAdapter adapter =  new PromoTransactionAdapter(getActivity(),promoWalletslist);
            dataList.setAdapter(adapter);
        }

    }
 }
