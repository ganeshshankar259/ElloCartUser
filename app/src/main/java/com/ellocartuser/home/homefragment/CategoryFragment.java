package com.ellocartuser.home.homefragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CartResponce;
import com.ellocartuser.apiservices.Responce.CatStoreAllResponce;
import com.ellocartuser.apiservices.Responce.StoresCatResponce;
import com.ellocartuser.apiservices.Responce.SubCatResponce;
import com.ellocartuser.cart.CartDisplay;
import com.ellocartuser.home.adapterandmodel.CarStoreAdapter;
import com.ellocartuser.home.adapterandmodel.CategoryStoreAll;
import com.ellocartuser.home.adapterandmodel.GridAdapter;
import com.ellocartuser.home.adapterandmodel.ProductSearchAdapter;
import com.ellocartuser.home.adapterandmodel.SubCatStoreAdapter;
import com.ellocartuser.home.adapterandmodel.SubcategoryStoreAll;
import com.ellocartuser.home.homefragment.shopinfotab.ShopTabMainClass;
import com.ellocartuser.home.homefragment.shopinfotab.TabLayoutt;
import com.ellocartuser.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment implements CarStoreAdapter.OnItemClickedcat, SubCatStoreAdapter.OnItemClickedSubcat,ProductSearchAdapter.OnItemClickedProductsearch {
    CarStoreAdapter.OnItemClickedcat OnItemClickedcat;
    SubCatStoreAdapter.OnItemClickedSubcat onItemClickedSubcat;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    ImageView carticon;
    TextView cartcount;
    // TODO: Rename and change types of parameters
    private String catid,subcatid="0";
    private String sellerid,storename;
    RecyclerView recyclerView,subcatlist;
    SubCatStoreAdapter subcatadapter;
    RecyclerView recyclerViewproductsearch;
    Dialog dialog;
    ProgressDialog pd1;
    CarStoreAdapter adapter;
    EditText auto;
    String categoryid="";
    TextView current;
    ImageView imageback;
    LinearLayout nosearchdata=null;
    List<CategoryStoreAll> catfilterdata;
    public CategoryFragment() {
        // Required empty public constructor
    }

    ProductSearchAdapter.OnItemClickedProductsearch productsearchlistioner;
    ProductSearchAdapter productsearchadapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */

    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2,String storename) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, storename);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            catid = getArguments().getString(ARG_PARAM1);
            sellerid = getArguments().getString(ARG_PARAM2);
            storename = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        auto = view.findViewById(R.id.auto);
        subcatlist = view.findViewById(R.id.subcatlist);
        recyclerView = view.findViewById(R.id.catList);
        current = view.findViewById(R.id.current);
        current.setText(storename);
  //    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
   //   onItemClicked=this;
        OnItemClickedcat=this;
        onItemClickedSubcat=this;
        catfilterdata=new ArrayList<>();
        imageback = view.findViewById(R.id.imageback);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        productsearchlistioner=this;

        cartcount = view.findViewById(R.id.cartcount);
        carticon = view.findViewById(R.id.imageback1);

        carticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadFragment(new CartDisplay(),getActivity(),CategoryFragment.this);
            }
        });

        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogSearch(getActivity());
            }
        });

        apiCall();
        return view;
    }

    public void showDialogSearch(Activity activity){
        dialog = new Dialog(activity,android.R.style.Theme_Holo_Light_NoActionBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.homeproductsearch);

//      ConstraintLayout currentloc = dialog.findViewById(R.id.constraintLayout2);
        nosearchdata=dialog.findViewById(R.id.sdfg);
        ImageView close=dialog.findViewById(R.id.imageback);
        EditText editText=dialog.findViewById(R.id.productsearch);
        recyclerViewproductsearch=dialog.findViewById(R.id.catList);
        recyclerViewproductsearch.setLayoutManager(new LinearLayoutManager(getContext()));
        editText.requestFocus();
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    //Clear focus here from edittext
                    editText.clearFocus();
                }
                return false;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                apicallSearch(editText.getText().toString());
            }
        });



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

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
//        pd1.show();
        Call<CartResponce> getCate = ApiClient.getApiService().setCart(userid,type,product_id,product_qty,sproduct_id,seller_id);
        getCate.enqueue(new Callback<CartResponce>() {
            @Override
            public void onResponse(Call<CartResponce> call, Response<CartResponce> response) {
                final CartResponce resource = response.body();
              //  pd1.dismiss();

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
        Call<CatStoreAllResponce> getCate = ApiClient.getApiService().getStoreall(catid,sellerid);
        getCate.enqueue(new Callback<CatStoreAllResponce>() {
            @Override
            public void onResponse(Call<CatStoreAllResponce> call, Response<CatStoreAllResponce> response) {
                final CatStoreAllResponce resource = response.body();

                try {
                    pd1.dismiss();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                String  subcatid="0";
                if (resource.getStatus().equals("ok")) {
                    //catstore
                    current.setText(resource.getSeller_store_name());

                    catfilterdata=new ArrayList<>();
                    for(int i=0;i<resource.getCategories().size();i++){
                      if(resource.getCategories().get(i).getSelected().equals("1")){
                        subcatid =  resource.getCategories().get(i).getCategory_id();
                  //    resource.getCategories().get(0).setSelected("1");
                          categoryid= resource.getCategories().get(i).getCategory_id();
                          //to get fist
                          CategoryStoreAll d= resource.getCategories().get(i);
                          resource.getCategories().remove(i);
                          resource.getCategories().add(0,d);

                     //   categoryid= resource.getCategories().get(i).getCategory_id();
                        }
                     //   resource.getCategories().get(i).setSelected("0");
                    }
//                    if(resource.getCategories().size()!=0){
//                      subcatid =  resource.getCategories().get(0).getCategory_id();
//                        resource.getCategories().get(0).setSelected("1");
//                        categoryid= resource.getCategories().get(0).getCategory_id();
//                    }else{
//                        subcatid="0";
//                    }

                    catfilterdata=resource.getCategories();
                    if(catid.equals("no_cat") || subcatid.equals("0")){
                        if(catfilterdata.size()>0) {
                            catfilterdata.get(0).setSelected("1");
                            subcatid=  catfilterdata.get(0).getCategory_id();
                            categoryid= catfilterdata.get(0).getCategory_id();
                            catid= catfilterdata.get(0).getCategory_id();
                        }
                    }
                    if(getActivity()!=null) {
                        adapter = new CarStoreAdapter(getActivity(), catfilterdata, OnItemClickedcat);
                        recyclerView.setAdapter(adapter);
                    }
                    subcatApi(subcatid);
                } else {
//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<CatStoreAllResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                if(getActivity()!=null) {
                    Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void subcatApi(String catid) {

        if(getActivity()==null){
            return;
        }
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
//         pd1.show();
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<SubCatResponce> getCate = ApiClient.getApiService().getSubCat(catid,sellerid);
        getCate.enqueue(new Callback<SubCatResponce>() {
            @Override
            public void onResponse(Call<SubCatResponce> call, Response<SubCatResponce> response) {
                final SubCatResponce resource = response.body();


                        pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {
//catstore
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                    subcatlist.setLayoutManager(gridLayoutManager);
                  //  dataList.setNestedScrollingEnabled(false);
                  //  on=this;

                        if(getActivity()!=null) {
                            subcatadapter = new SubCatStoreAdapter(getActivity(), resource.getSubcategories(), onItemClickedSubcat);
                            subcatlist.setAdapter(subcatadapter);
                        }
                 //   subcatApi();
                } else {
                    if(getActivity()!=null) {
                        subcatadapter = new SubCatStoreAdapter(getActivity(), resource.getSubcategories(), onItemClickedSubcat);
                        subcatlist.setAdapter(subcatadapter);
                    }
//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<SubCatResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

                }
        });
    }

    @Override
    public void onItemClickCat(int position,String catid) {
        categoryid=catid;
        for(int i=0;i<catfilterdata.size();i++){
            catfilterdata.get(i).setSelected("0");
        }
        catfilterdata.get(position).setSelected("1");
        adapter.setDataList(catfilterdata);
        adapter.notifyDataSetChanged();
        subcatApi(catid);

    }

    @Override
    public void onItemClickSubCat(int position,String subcatid,String name) {


        Util.loadFragment(TabLayoutt.newInstance(sellerid,categoryid,subcatid,name),getActivity(),CategoryFragment.this);



    }


    @Override
    public void onHiddenChanged(boolean hidden) {

        System.out.println("rajuuu "+hidden);

        if (hidden) {
            //do when hidden
        } else {
            //do when show
            SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            String id = pref.getString("user_id","");
            apiCallForCart(id,"get","","","","");

        }
    }




    public void apicallSearch(String search){

//      pd1 = new ProgressDialog(getActivity());
//        pd1.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//         pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
//        pd1.setCancelable(false);
//        pd1.show();

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        String lat = pref.getString("latitude","");
        String longi = pref.getString("longitude","");

        Call<StoresCatResponce> getCate = ApiClient.getApiService().productsearch(search,lat, longi,sellerid);
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
//                    adapterUpdate(resource.getList());
                    //      Log.d("raju fav",resource.getStores().get(0).getWish_status());

                    if(getActivity()!=null) {
                        recyclerViewproductsearch.setVisibility(View.VISIBLE);
                        if(nosearchdata!=null) {
                            nosearchdata.setVisibility(View.GONE);
                        }
                        productsearchadapter = new ProductSearchAdapter(getActivity(), resource.getList(), productsearchlistioner,"listcat");
                        recyclerViewproductsearch.setAdapter(productsearchadapter);
                    }

                } else {
                    recyclerViewproductsearch.setVisibility(View.GONE);
                    if(nosearchdata!=null) {
                        nosearchdata.setVisibility(View.VISIBLE);
                    }
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


//    @Override
//    public void onItemClicked(int position, String cat, String name) {
//
//    }

    @Override
    public void onItemClickedcatsearch(int position, String sellerid, String productid) {

        try {
            dialog.dismiss();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        Util.loadFragment(new ProductDetailedPage().newInstance(sellerid, productid), getActivity(), CategoryFragment.this);

    }
}