package com.ellocartuser.home.homefragment;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.B2BResponce;
import com.ellocartuser.apiservices.Responce.StoresCatResponce;
import com.ellocartuser.apiservices.model.Stores;
import com.ellocartuser.home.adapterandmodel.B2BAdapterSettingpage;
import com.ellocartuser.home.adapterandmodel.NearByStoreAdapter;
import com.ellocartuser.utils.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Wishlist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Wishlist extends Fragment implements NearByStoreAdapter.OnItemClickedNear {
    ProgressDialog pd1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    NearByStoreAdapter adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    NearByStoreAdapter.OnItemClickedNear onItemClicked;
    public Wishlist() {
        // Required empty public constructor
    }
    RecyclerView catList;
    LinearLayout nowishlist;
    ImageView imageback;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Wishlist.
     */
    // TODO: Rename and change types and number of parameters
    public static Wishlist newInstance(String param1, String param2) {
        Wishlist fragment = new Wishlist();
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
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        catList=view.findViewById(R.id.catList);
        imageback=view.findViewById(R.id.imageback);
        nowishlist=view.findViewById(R.id.nowishlist);

        catList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        onItemClicked=this;
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
        String lat = pref.getString("latitude","");
        String longi = pref.getString("longitude","");

        if(id==null || id.equals("")){
            Util.PleaseLogin(getContext());
            return;
        }

        Call<StoresCatResponce> getCate = ApiClient.getApiService().getwishlist(id,"get",lat,longi,"10");
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

                    nowishlist.setVisibility(View.GONE);
                    catList.setVisibility(View.VISIBLE);

                    adapterUpdate(resource.getStores());

//                    B2BAdapterSettingpage adapter = new B2BAdapterSettingpage(getActivity(),resource.getB2orders());
//                    dataList.setAdapter(adapter);


                } else {
                    nowishlist.setVisibility(View.VISIBLE);
                    catList.setVisibility(View.GONE);
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

    public void adapterUpdate(List<Stores> store){
        if(getActivity()!=null) {
            adapter = new NearByStoreAdapter(getActivity(), store, onItemClicked,"wishlist");
            catList.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClicked(int position, String sellerid, String storename,String type) {
        if(type.equals("fav")){
            apiCallForAddFav(sellerid);
        }else {
            Util.loadFragment(CategoryFragment.newInstance(mParam1, sellerid, storename), getActivity(),Wishlist.this);
        }
    }

    private void apiCallForAddFav(String sellerid) {

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
        String lat = pref.getString("latitude","");
        String longi = pref.getString("longitude","");

        if(id==null || id.equals("")){
            Util.PleaseLogin(getContext());
            return;
        }

        Call<StoresCatResponce> getCate = ApiClient.getApiService().setwishlist(id,"add",sellerid);
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

//                    adapterUpdate(resource.getStores());
                    apicall();
                    if(resource.getType().equals("added")){
                        Toast.makeText(getContext(), "wishlist added successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "wishlist removed successfully", Toast.LENGTH_LONG).show();

                    }

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
}