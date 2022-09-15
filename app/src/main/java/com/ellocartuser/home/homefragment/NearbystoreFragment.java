package com.ellocartuser.home.homefragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CartResponce;
import com.ellocartuser.apiservices.Responce.StoresCatResponce;
import com.ellocartuser.apiservices.model.Stores;
import com.ellocartuser.cart.CartDisplay;
import com.ellocartuser.home.adapterandmodel.NearByStoreAdapter;
import com.ellocartuser.home.adapterandmodel.Products;
import com.ellocartuser.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NearbystoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NearbystoreFragment extends Fragment implements NearByStoreAdapter.OnItemClickedNear {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    RecyclerView dataList;
    TextView current;
    ImageView wishlist;
    NearByStoreAdapter adapter;
    List<Stores> datastore=null;
    ProgressDialog pd1;
    LinearLayout sdfg;
    // TODO: Rename and change types of parameters
    private String catid;
    private String mParam2,bannerid="",sellerid="",type="";
    NearByStoreAdapter.OnItemClickedNear onItemClicked;
    ImageView imageback;
    ImageView carticon;
    TextView cartcount;
    public NearbystoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NearbystoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NearbystoreFragment newInstance(String param1, String param2, String param3, String param4,String param5) {
        NearbystoreFragment fragment = new NearbystoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        fragment.setArguments(args);
        return fragment;
    }
    EditText etSearch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            catid = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            bannerid = getArguments().getString(ARG_PARAM3);
            sellerid = getArguments().getString(ARG_PARAM4);
            type = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_nearbystore, container, false);
        sdfg = view.findViewById(R.id.sdfg);
        dataList = view.findViewById(R.id.catList);
       // processtype = view.findViewById(R.id.processtype);
        current = view.findViewById(R.id.current);
        wishlist = view.findViewById(R.id.wishlist);
        etSearch = view.findViewById(R.id.etSearch);

        current.setText(mParam2);
        dataList.setLayoutManager(new LinearLayoutManager(getContext()));
        dataList.setHasFixedSize(true);
        dataList.setItemAnimator(null);
        dataList.setItemViewCacheSize(50);

//        if(type.equals("1")){
//            processtype.setText("All India Delivery");
//        }

        onItemClicked=this;
        // dataList.setLayoutManager(gridLayoutManager);

        imageback = view.findViewById(R.id.imageback);
                imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadFragment(Wishlist.newInstance(catid,""),getActivity(),NearbystoreFragment.this);

            }
        });
        cartcount = view.findViewById(R.id.cartcount);
        carticon = view.findViewById(R.id.imageback1);

        carticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadFragment(new CartDisplay(),getActivity(),NearbystoreFragment.this);
            }
        });

        if(getActivity()!=null) {
            adapter = new NearByStoreAdapter(getActivity(), null, onItemClicked,"list");
            dataList.setAdapter(adapter);
        }

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // filter your list from your input
                if(s.toString()!=null) {
                    filter(s.toString().toLowerCase());
                }
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });




        apiCall();

        return view;
    }



    void filter(String text){
        List<Stores> temp = new ArrayList();
        try {
            for (int i = 0; i < datastore.size(); i++) {
                if (datastore.get(i).getSellerStoreName().contains(text) || datastore.get(i).getSellerStoreName().toLowerCase().contains(text)) {
                    temp.add(datastore.get(i));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
//        for(Products d: displayedList){
//            //or use .equal(text) with you want equal match
//            //use .toLowerCase() for better matches
//            if(d.get.contains(text)){
//                temp.add(d);
//            }
//        }
        //update recyclerview

        if(temp.size()==0){
            sdfg.setVisibility(View.VISIBLE);
            dataList.setVisibility(View.GONE);
        }else{
            dataList.setVisibility(View.VISIBLE);
            sdfg.setVisibility(View.GONE);
        }
        adapter.updateList(temp);
    }


    @Override
    public void onResume() {
        super.onResume();
        if(getActivity()!=null){

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                    String id = pref.getString("user_id","");
                    if(!id.equals("") || id!=null){
                        apiCallForCart(id,"get","","","","");
                    }

                }
            });
System.out.println("rajuu : onresume");
        }
    }

    public void apiCallForCart(String userid,String type,String product_id,String product_qty,String sproduct_id,String seller_id){

        if(userid.trim().length()==0){
            cartcount.setText("0");
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
        Call<CartResponce> getCate = ApiClient.getApiService().setCart(userid,type,product_id,product_qty,sproduct_id,seller_id);
        getCate.enqueue(new Callback<CartResponce>() {
            @Override
            public void onResponse(Call<CartResponce> call, Response<CartResponce> response) {
                final CartResponce resource = response.body();
               // pd1.dismiss();

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    if(String.valueOf(resource.getCartCount()).equals("0")){
                        cartcount.setText(String.valueOf(resource.getCartCount()));
                        cartcount.setVisibility(View.INVISIBLE);
                    }else{
                        cartcount.setText(String.valueOf(resource.getCartCount()));
                        cartcount.setVisibility(View.VISIBLE);

                    }

                } else {

                    if(String.valueOf(resource.getCartCount()).equals("0")){
                        cartcount.setText(String.valueOf(resource.getCartCount()));
                        cartcount.setVisibility(View.INVISIBLE);
                    }else{
                        cartcount.setText(String.valueOf(resource.getCartCount()));
                        cartcount.setVisibility(View.VISIBLE);

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

    private void apiCall() {
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
        //  pd1.show();
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        String lat = pref.getString("latitude","");
        String longi = pref.getString("longitude","");

        Call<StoresCatResponce> getCate = ApiClient.getApiService().getStoreCategory(id,catid,sellerid,bannerid,lat, longi, "10",type);
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
                   // datastore=resource.getStores();
                    datastore = resource.getStores();
                   adapter.updateList(resource.getStores());
                    Log.d("raju fav",resource.getStores().get(0).getWish_status());

                } else {
                    if (resource.getMessage() != "") {
                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<StoresCatResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                if(getActivity()!=null) {
                    Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void loadFragment(final Fragment fragment) {

        // create a transaction for transition here
        final FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                .beginTransaction();

        // put the fragment in place
        transaction.replace(R.id.fragment_container, fragment);

        // this is the part that will cause a fragment to be added to backstack,
        // this way we can return to it at any time using this tag
        transaction.addToBackStack(fragment.getClass().getName());

        transaction.commit();
    }
//    public void adapterUpdate(List<Stores> store){
//
//     }

    @Override
    public void onItemClicked(int position,String sellid,String storename,String type) {
        if(type.equals("fav")){
                apiCallForAddFav(sellid);
        }else {
            SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            //   editor.putString("boy",resource.getBoy());
            editor.putString("currentstore",storename);
            editor.commit();
            hideKeyboard();
            Util.loadFragment(CategoryFragment.newInstance(catid, sellid, storename), getActivity(),NearbystoreFragment.this);
        }
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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

                    if(resource.getType().equals("added")){
                        Toast.makeText(getContext(), "wishlist added successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "wishlist removed successfully", Toast.LENGTH_LONG).show();
                    }

//                    adapterUpdate(resource.getStores());
                    apiCall();

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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //do when hidden
        } else {
            //do when show
            SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            String id = pref.getString("user_id","");
            apiCallForCart(id,"get","","","","");

        }
    }
}