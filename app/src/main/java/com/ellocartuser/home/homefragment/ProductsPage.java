package com.ellocartuser.home.homefragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CartResponce;
import com.ellocartuser.apiservices.Responce.CatStoreAllResponce;
import com.ellocartuser.apiservices.Responce.ProductsResponce;

import com.ellocartuser.cart.CartDisplay;
import com.ellocartuser.cart.DialogPayment;
import com.ellocartuser.home.adapterandmodel.CarStoreAdapter;
import com.ellocartuser.home.adapterandmodel.Products;
import com.ellocartuser.home.adapterandmodel.ProductsAdapter;
import com.ellocartuser.home.homefragment.shopinfotab.TabLayoutt;
import com.ellocartuser.utils.Util;
import com.google.android.gms.common.data.DataHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsPage extends Fragment implements ProductsAdapter.OnItemClickedproduct {
    String previousstore="",currentstore="";
    String cartsellerid=null,carttotalcount="",store_o="0";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    String seller_id,cat_id,subcat_id;
    LinearLayout sdfg;
   static TextView items,totalamt;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;
    RecyclerView recyclerView;
    ProgressDialog pd1;
    ProductsAdapter adapter;
    ImageView imageback;
    EditText etSearch;
    ProductsAdapter.OnItemClickedproduct onclick;
    ImageView carticon;
    TextView cartcount;
   static ConstraintLayout bottomcard;
    List<Products> datalist=new ArrayList<>();
    public ProductsPage() {
        // Required empty public constructor
    }

   int clickposition=0;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductsPage.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsPage newInstance(String param1, String param2,String param3) {
        ProductsPage fragment = new ProductsPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            seller_id = getArguments().getString(ARG_PARAM1);
            cat_id = getArguments().getString(ARG_PARAM2);
            subcat_id = getArguments().getString(ARG_PARAM3);
        }

        if(savedInstanceState==null){

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_products_page, container, false);
        etSearch = view.findViewById(R.id.etSearch);
        totalamt = view.findViewById(R.id.totalamt);
        bottomcard = view.findViewById(R.id.bottomcard);
        items = view.findViewById(R.id.items);
        recyclerView = view.findViewById(R.id.catList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        sdfg = view.findViewById(R.id.sdfg);
        cartcount = view.findViewById(R.id.cartcount);
        carticon = view.findViewById(R.id.imageback1);

//        carticon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Util.loadFragment(new CartDisplay(),getActivity(),ProductsPage.this);
//            }
//        });

            onclick=this;

            adapter = new ProductsAdapter(getActivity(), null, onclick);
            recyclerView.setAdapter(adapter);

           SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
           String id = pref.getString("user_id","");
            apiCallForCart(id,"get","","","","",1,"","");

            apiCall();

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
                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });

//        imageback = view.findViewById(R.id.imageback);
        bottomcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayoutt.carticon.callOnClick();
            }
        });

        return view;
    }


    public static  void updateCardDown(String totall,String item){

        if(item.equals("0")){
            bottomcard.setVisibility(View.GONE);
        }   else if(item.equals("1")){
            bottomcard.setVisibility(View.VISIBLE);
            items.setText("Items : "+item);
        } else {
            bottomcard.setVisibility(View.VISIBLE);
            items.setText("Items : "+item);

        }

            totalamt.setText("₹"+totall);

    }

    public void apiCallForCart(String userid,String type,String product_id,String product_qty,String sproduct_id,String seller_id,int position,String proid,String qty){

        ProgressDialog pd2 = new ProgressDialog(getActivity());
        pd2.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd2.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd2.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd2.setCancelable(true);
        pd2.show();

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        Call<CartResponce> getCate = ApiClient.getApiService().setCart(userid,type,product_id,product_qty,sproduct_id,seller_id);
        getCate.enqueue(new Callback<CartResponce>() {
            @Override
            public void onResponse(Call<CartResponce> call, Response<CartResponce> response) {
                final CartResponce resource = response.body();

                pd2.dismiss();

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    //catstore

                    TabLayoutt.apiCallForCart(id, "get", "", "", "", "");

                    if(resource.getType().equals("get")){
                        cartsellerid=resource.getSellerId();
                        carttotalcount=String.valueOf(resource.getCartCount());
                        previousstore=resource.getSellerStoreName();
                    }

                    if(resource.getType().equals("clear")){
                        carttotalcount="0";
                       // addtoCartFun();
                        Toast.makeText(getActivity(),"Cart Cleared",Toast.LENGTH_LONG).show();
//                        if(qty.equals("0")){
//                            SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//                            String idd = pref.getString("user_id","");
//                            apiCallForCart(idd,"delete",proid,"0","",seller_id,position,"","");
//                        }else {
//                            apiCallForCartChange(position, "add", proid, qty, "", seller_id);
//                        }
                    }

                    if(resource.getType().equals("delete")){
                        adapter.updateqty(position,Integer.valueOf(product_qty));

                    }
                }else {
//
//                    if (resource.getType().equals("clear")) {
//                        if (qty.equals("0")) {
//                            SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//                            String idd = pref.getString("user_id", "");
//                            apiCallForCart(idd, "delete", proid, "0", "", seller_id, position, "", "");
//                        } else {
//                            apiCallForCartChange(position, "add", proid, qty, "", seller_id);
//                        }
//                    }

                    if(resource.getType()!=null) {
                        if (resource.getType().toLowerCase().equals("clear") || resource.getType().toLowerCase().equals("delete")) {
                            carttotalcount = "0";

                            TabLayoutt.apiCallForCart(id, "get", "", "", "", "");

                            //  addtoCartFun();
                        }
                        if(resource.getType().equals("delete")){
                            adapter.updateqty(position,Integer.valueOf(product_qty));

                        }

                        if(resource.getType().equals("get")){
                            cartsellerid=resource.getSellerId();
                            carttotalcount=String.valueOf(resource.getCartCount());
                            previousstore=resource.getSellerStoreName();
                        }
                    }

                }
               // pd2.dismiss();
            }

            @Override
            public void onFailure(Call<CartResponce> call, Throwable t) {
                //   pd.dismiss();
                pd2.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

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
                     //   apiCallForCart(id,"get","","","","");
                    }

                }
            });

        }
    }

    @Override
    public void onStart() {
        super.onStart();
       // recyclerView.getLayoutManager().scrollToPosition(clickposition);
    }

    //
//    public void apiCallForCart(String userid,String type,String product_id,String product_qty,String sproduct_id,String seller_id){
//
//        pd1 = new ProgressDialog(getActivity());
//        pd1.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//         pd1.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//        pd1.setCancelable(false);
//        pd1.show();
//        Call<CartResponce> getCate = ApiClient.getApiService().setCart(userid,type,product_id,product_qty,sproduct_id,seller_id);
//        getCate.enqueue(new Callback<CartResponce>() {
//            @Override
//            public void onResponse(Call<CartResponce> call, Response<CartResponce> response) {
//                final CartResponce resource = response.body();
//                pd1.dismiss();
//
//                if (resource == null) {
//                    return;
//                }
//
//                if (resource.getStatus().equals("ok")) {
//                    cartcount.setText(String.valueOf(resource.getCartCount()));
//
//
//                } else {
//
//                    cartcount.setText("0");
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<CartResponce> call, Throwable t) {
//                    //   pd.dismiss();
//                //   pd1.dismiss();
//                t.printStackTrace();
//                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

    void filter(String text){
        List<Products> temp = new ArrayList();
        for(int i=0;i<datalist.size();i++){
            if(datalist.get(i).getProductName().contains(text) || datalist.get(i).getProductName().toLowerCase().contains(text)){
                temp.add(datalist.get(i));
            }
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
            recyclerView.setVisibility(View.GONE);
            sdfg.setVisibility(View.VISIBLE);
        } else {
            sdfg.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        adapter.updateList(temp);
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

        Call<ProductsResponce> getCate = ApiClient.getApiService().getProducts(id,seller_id,cat_id,subcat_id);
        getCate.enqueue(new Callback<ProductsResponce>() {
            @Override
            public void onResponse(Call<ProductsResponce> call, Response<ProductsResponce> response) {
                final ProductsResponce resource = response.body();
              pd1.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    //catstore
                    store_o=resource.getSeller_ostatus();
                    datalist=resource.getProduct();
                    if (getActivity()!=null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.updateList(resource.getProduct()); // = new ProductsAdapter(getActivity(), resource.getProduct(), onclick);
                              //  recyclerView.setAdapter(adapter);
                            }
                        });

                    }

                } else {
//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<ProductsResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
              //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onItemClickProduct(int position, String productid) {

        clickposition=position;
        Util.loadFragment(new ProductDetailedPage().newInstance(seller_id,productid),getActivity(),ProductsPage.this);

    }

    @Override
    public void onItemClickaddtocart(int position, String catid) {


        VarentBottomSheet bottomSheet = new VarentBottomSheet().newInstance(seller_id,catid);
        bottomSheet.show(getChildFragmentManager(),
                "VarientBottomSheet");

    }

    @Override
    public void onItemClickaddtocartupdatecount(int position, String proid, String qty) {

        if( store_o.equals("0")) {
            Util.AlertWithOK(getActivity(),"Currently not accepting orders");//Toast.makeText(getActivity(),",Toast.LENGTH_LONG).show();
            return;
        }
        if(cartsellerid==null){
            return;
        }
        if(seller_id.equals(cartsellerid) || carttotalcount.equals("0")) {

            if(qty.equals("0")){
                SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                String idd = pref.getString("user_id","");
                apiCallForCart(idd,"delete",proid,"0","",seller_id,position,"","");
            }else {
                apiCallForCartChange(position, "add", proid, qty, "", seller_id);
            }
        }else{
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("You want to discard  "+previousstore+" and add products from "+currentstore+" ?");
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                                String idd = pref.getString("user_id","");
                                apiCallForCart(idd, "clear", "", "", "", "",position,proid,qty);
                            }
                        });

                builder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();

            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }
        }

    }


    public void apiCallForCartChange(int position,String type,String product_id,String product_qty,String sproduct_id,String seller_id){


        pd1 = new ProgressDialog(getActivity());
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(true);
        pd1.show();
     //   pd1.show();
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        Call<CartResponce> getCate = ApiClient.getApiService().setCart(id,type,product_id,product_qty,sproduct_id,seller_id);
        getCate.enqueue(new Callback<CartResponce>() {
            @Override
            public void onResponse(Call<CartResponce> call, Response<CartResponce> response) {
                final CartResponce resource = response.body();

                pd1.dismiss();

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    //catstore

                    adapter.updateqty(position,Integer.valueOf(product_qty));
                    try {
                        TabLayoutt.apiCallForCart(id, "get", "", "", "", "");
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    // ((TabLayoutt)this.get()).any_thing_inside_parent_fragment;
                } else {
                    if (resource.getMessage() != "") {
                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<CartResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }


//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        System.out.println("rajuuu "+hidden);
//        if (hidden) {
//            //do when hidden
//        } else {
//            //do when show
//            SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//            String id = pref.getString("user_id","");
//            TabLayoutt.apiCallForCart(id,"get","","","","");
//        }
//    }

    @Override
    public void onHiddenChanged(boolean hidden) {

        System.out.println("rajuuu ssd "+hidden);

        if (hidden) {
            //do when hidden
        } else {
            //do when show
           // update();
        }
    }

}