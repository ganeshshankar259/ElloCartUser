package com.ellocartuser.home.homefragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.Login;
import com.ellocartuser.OtpScreen;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CartResponce;
import com.ellocartuser.apiservices.Responce.ProductDetailedResponce;
import com.ellocartuser.cart.CartDisplay;
import com.ellocartuser.home.adapterandmodel.ProductDetailedSubVarentAdapter;
import com.ellocartuser.home.adapterandmodel.IntroViewPagerAdapter;
import com.ellocartuser.home.adapterandmodel.ProductDetailedAdapter;
import com.ellocartuser.home.adapterandmodel.ProductSubs;
import com.ellocartuser.home.adapterandmodel.ProductVariables;
import com.ellocartuser.home.adapterandmodel.Review;
import com.ellocartuser.home.adapterandmodel.ReviewAdapter;
import com.ellocartuser.home.adapterandmodel.ScreenItem;
import com.ellocartuser.home.adapterandmodel.SliderAdapter;
import com.ellocartuser.home.adapterandmodel.SliderModel;
import com.ellocartuser.home.adapterandmodel.SliderModelHome;
import com.ellocartuser.home.homefragment.shopinfotab.ShopReviews;
import com.ellocartuser.home.homefragment.shopinfotab.TabLayoutt;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.utils.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailedPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailedPage extends Fragment  implements  ProductDetailedAdapter.OnItemClickedVarient , ProductDetailedSubVarentAdapter.OnItemClickedSubVarient , View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String vid="",store_o="";
    AppCompatButton reviebtn;
    // TODO: Rename and change types of parameters
    private String sellerid;
    ImageView imageback;
    private String productid;
    ProgressDialog pd1,pd2;
    View view1;
    List<Review>  revdata=null;
    Timer timer;
    TabLayout tabIndicator;
    private ViewPager screenPager;
    SliderView sliderView;
    ImageView carticon,imageView14;
    TextView cartcount;
    BottomNavigationView bottomNav;
    IntroViewPagerAdapter introViewPagerAdapter;
    private SliderAdapter adapter;
    private ProductDetailedAdapter adapterv;
    private ProductDetailedSubVarentAdapter adaptersubv;
    TextView producttitle,itemamt,strickamt,content,count,percentage;
    Button minus,plus;
    Button addtocart,review;
    RecyclerView varentlist,subvarentlist,reviewlist;
    ProductDetailedAdapter.OnItemClickedVarient onclick;
    ProductDetailedSubVarentAdapter.OnItemClickedSubVarient onclicksubvarient;
    String cartsellerid=null,carttotalcount="";
    List<ProductVariables> varlist=new ArrayList();
    List<ProductSubs> subvarlist=new ArrayList();
    LinearLayout reviewlayout;
    Boolean inside=false;
    RatingBar rateing;
    TextView maxadd;
    SharedPreferences pref;
    ReviewAdapter reviewAdapter;
    String previousstore="",currentstore="";
    public ProductDetailedPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductDetailedPage.
     */

    // TODO: Rename and change types and number of parameters
    public static ProductDetailedPage newInstance(String param1, String param2) {
        ProductDetailedPage fragment = new ProductDetailedPage();
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
            sellerid = getArguments().getString(ARG_PARAM1);
            productid = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detailed_page, container, false);

        reviebtn = view.findViewById(R.id.reviebtn);
        imageView14 = view.findViewById(R.id.imageView14);
        maxadd = view.findViewById(R.id.maxadd);
        producttitle = view.findViewById(R.id.producttitle);
        view1 = view.findViewById(R.id.view);
        itemamt = view.findViewById(R.id.itemamt);
      //  strickamt = view.findViewById(R.id.strickamt);
        content = view.findViewById(R.id.content);
        percentage = view.findViewById(R.id.percentage);
        count = view.findViewById(R.id.count);
        minus = view.findViewById(R.id.minus);
        plus = view.findViewById(R.id.plus);
        rateing = view.findViewById(R.id.rateing);
        addtocart = view.findViewById(R.id.addtocart);
        varentlist = view.findViewById(R.id.varentlist);
        subvarentlist = view.findViewById(R.id.subvarentlist);
        sliderView = view.findViewById(R.id.imageSlider);
        imageback = view.findViewById(R.id.imageback);
        reviewlist = view.findViewById(R.id.reviewlist);
        reviewlayout = view.findViewById(R.id.reviewlayout);

//        bottomNav = view.findViewById(R.id.bottom_navigation);

//      review.setOnClickListener(this::onClick);
        addtocart.setOnClickListener(this::onClick);
        plus.setOnClickListener(this::onClick);
        minus.setOnClickListener(this::onClick);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        adapter = new SliderAdapter(getContext(),R.layout.image_slider_layout_item,null,null);

        onclick=this;
        onclicksubvarient=this;
        pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        currentstore = pref.getString("currentstore","");
        //cat tyep
        reviewlist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        varentlist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        subvarentlist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");


        cartcount = view.findViewById(R.id.cartcount);
        carticon = view.findViewById(R.id.imageback1);

        carticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadFragment(new CartDisplay(),getActivity(),ProductDetailedPage.this);
            }
        });

        reviebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(revdata!=null){
                     // Util.loadFragment(new UserReviews().newInstance(revdata,""),getActivity(),ProductDetailedPage.this);

//        // create a transaction for transition here
        final FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                .beginTransaction();

        // put the fragment in place
        transaction.replace(R.id.fragment_container1, new UserReviews().newInstance(revdata,""));

        // this is the part that will cause a fragment to be added to backstack,
        // this way we can return to it at any time using this tag
        transaction.addToBackStack(new UserReviews().newInstance(revdata,"").getClass().getName());


        transaction.commit();
               }
            }
        });

        apiCallForCart(id,"get","","","","");

        apicall(sellerid,id,productid,"","");

        return view;
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
                        apiCallForCartc(id,"get","","","","");
                    }

                }
            });

        }
    }


    public void apiCallForCartc(String userid,String type,String product_id,String product_qty,String sproduct_id,String seller_id){

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
             //   pd1.dismiss();

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

    private void setSliderAdapter() {
        sliderView.setSliderAdapter(adapter);
     // sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(2);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }

    public void apicall(String sellerid,String userid,String productid,String vproduct,String sproduct){
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

       Call<ProductDetailedResponce> getCate = ApiClient.getApiService().getProductDetail(sellerid,userid,productid,vproduct,sproduct);
       getCate.enqueue(new Callback<ProductDetailedResponce>() {
           @SuppressLint("SetTextI18n")
           @Override
           public void onResponse(Call<ProductDetailedResponce> call, Response<ProductDetailedResponce> response) {
               final ProductDetailedResponce resource = response.body();

               pd1.dismiss();

               // Log.d("resss", resource.toString());
               if (resource == null) {
                   return;
               }

               if (resource.getStatus().equals("ok")) {

            try {
                store_o=resource.getStore_o();
                if(resource.getCart_max().equals("0")) {
                    maxadd.setVisibility(View.GONE);
                } else {
                    maxadd.setVisibility(View.VISIBLE);
                  //  maxadd.setText("You did not meet minimum purchase limit. Shop Rs:" + resource.getCart_max() + " more from this store to proceed for billing");
                    maxadd.setText("Add ₹" + resource.getCart_max() + " more to place your order");
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
                   //catstore
                   revdata = resource.getReviews();
//                   if(resource.getReviews().size()!=0){
//
//                       reviewAdapter=new ReviewAdapter(getActivity(),resource.getReviews(),"productpage");
//                       reviewlist.setAdapter(reviewAdapter);
//                   }else{
//                       reviewlayout.setVisibility(View.GONE);
//                   }
                   final List<ScreenItem> mList = new ArrayList<>();

                   if(resource.getProduct()==null || resource.getProduct().size()==0){
                       return;
                   }
                   adapter.setEmpty();
                   for(int i=0;i<resource.getProduct().get(0).getProductImages().size();i++) {
                     //  mList.add(new ScreenItem("", "", resource.getProduct().get(0).getProductImages().get(i)));
                       adapter.addItem(new SliderModelHome("", resource.getProduct().get(0).getProductImages().get(i),null));
                   }
                   setSliderAdapter();

                 producttitle.setText(resource.getProduct().get(0).getProductName());
                 itemamt.setText("₹"+resource.getProduct().get(0).getProductMrp()+"-"+resource.getProduct().get(0).getProductMeasure());
                 //  itemamt.setText("₹"+resource.getProduct().get(0).getProductSale());
                   content.setText(resource.getProduct().get(0).getProductDescription());
                   if(resource.getProduct().get(0).getProductMrp().equals(resource.getProduct().get(0).getProductSale())){

                       view1.setVisibility(View.GONE);
                    //   strickamt.setVisibility(View.GONE);
                       percentage.setVisibility(View.GONE);

                   }else{

                       view1.setVisibility(View.GONE);
                    //   strickamt.setVisibility(View.VISIBLE);
                       percentage.setVisibility(View.GONE);

                    //   strickamt.setText("");
                     //  strickamt.setText(" ₹"+resource.getProduct().get(0).getProductMrp());
//                       strickamt.setPaintFlags(strickamt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                       percentage.setText("Offer "+resource.getProduct().get(0).getProductDiscount()+"%");
                   }

                   rateing.setRating(Float.valueOf(resource.getProduct().get(0).getProduct_rating()));
                   if(resource.getProduct().get(0).getProductVariables().size()!=0){
                     //  vid=resource.getProduct().get(0).getProductVariables()
                    varlist =   resource.getProduct().get(0).getProductVariables();
//                       for(int i=0;i<resource.getProduct().get(0).getProductVariables().size();i++){
//                           resource.getProduct().get(0).getProductVariables().get(i).setSelected("0");
//                       }
                       adapterv = new ProductDetailedAdapter(getActivity(),resource.getProduct().get(0).getProductVariables(),onclick,R.layout.custom_grid_layout_vareient);
                       varentlist.setAdapter(adapterv);

                       imageView14.setVisibility(View.VISIBLE);

                   }

                   if(resource.getProduct().get(0).getProductSubs().size()!=0){
                    subvarlist =  resource.getProduct().get(0).getProductSubs();
//                       for(int i=0;i<resource.getProduct().get(0).getProductSubs().size();i++){
//                           resource.getProduct().get(0).getProductSubs().get(i).setSelected("0");
//                       }
                       adaptersubv = new ProductDetailedSubVarentAdapter(getActivity(),resource.getProduct().get(0).getProductSubs(),onclicksubvarient,R.layout.custom_grid_layout_vareient);
                       subvarentlist.setAdapter(adaptersubv);

                   }

               } else {
//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
               }

           }

           @Override
           public void onFailure(Call<ProductDetailedResponce> call, Throwable t) {
               //   pd.dismiss();
               pd1.dismiss();
               t.printStackTrace();
               //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
           }
       });

   }

    @Override
    public void onItemClickVarent(int position, String catid) {
//        for(int i=0;i<varlist.size();i++){
//            varlist.get(i).setSelected("0");
//        }
//        varlist.get(position).setSelected("1");
//        adapterv.setDataList(varlist);
//        adapterv.notifyDataSetChanged();

        String id = pref.getString("user_id","");
        apicall(sellerid,id,productid,catid,"");

      //  vproduct_id
    }

    @Override
    public void onItemClickSubVarent(int position, String catid,String selectidv) {
//        for(int i=0;i<subvarlist.size();i++){
//            subvarlist.get(i).setSelected("0");
//        }
//        subvarlist.get(position).setSelected("1");
//        adaptersubv.setDataList(subvarlist);
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        apicall(sellerid,id,productid,selectidv,catid);

    }

    public void addtoCartFun(){
        String vpro="",spro="";
        if(cartsellerid==null){
            return;
        }
        if(sellerid.equals(cartsellerid) || carttotalcount.equals("0")) {

            if(varlist.size()!=0 && varlist!=null ){

                for(int i=0;i<varlist.size();i++){
                    if(varlist.get(i).getSelected().equals("1")){
                        vpro=varlist.get(i).getVproductId();
                    }
                }

            }

            if(subvarlist.size()!=0 && subvarlist!=null ){
                for(int i=0;i<subvarlist.size();i++){
                    if(subvarlist.get(i).getSelected().equals("1")){
                        spro=subvarlist.get(i).getSproductId();
                    }
                }
            }

            SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            String id = pref.getString("user_id","");


            if(id.equals("")){

                Util.PleaseLogin(getContext());

            }

            apiCallForCart(id, "add", productid, count.getText().toString(),spro, sellerid);


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
                                apiCallForCart(idd, "clear", "", "", "", "");
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

    public void pleaselogin(){

        try {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Please Login to Add Products to cart");
            builder.setCancelable(false);

            builder.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Util.loadhome(getActivity());
                            Intent ii=new Intent(getActivity(), Login.class);
                            ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(ii);
                        }
                    });
            builder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
//                                    Intent ii=new Intent(getActivity(), HomeScreen.class);
//                                    ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(ii);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.addtocart:
                SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                String id = pref.getString("user_id","");

//        String lat = pref.getString("latitude","");
//        String longi = pref.getString("longitude","");
                if(id==null || id.equals("")){
//                    Util.PleaseLogin(getContext());
                    pleaselogin();
                    return;
                }

             if( store_o.equals("1")) {
                 addtoCartFun();
            }else{
                Util.AlertWithOK(getActivity(),"Currently not accepting orders");//Toast.makeText(getActivity(),",Toast.LENGTH_LONG).show();
            }

                break;

//            case R.id.review:
//
//                break;

            case R.id.plus:
                int nump= Integer.parseInt(count.getText().toString());
                nump=nump+1;
                count.setText(Integer.toString(nump));
                break;

            case R.id.minus:

                int num= Integer.parseInt(count.getText().toString());
                if(num==0 || num==1){ num=1; }else {
                    num = num - 1;
                }
                count.setText(Integer.toString(num));

                break;

        }


    }

    public void apiCallForCart(String userid,String type,String product_id,String product_qty,String sproduct_id,String seller_id){

        if(userid.trim().length()==0){
            cartcount.setText("0");
            return;
        }

        pd2 = new ProgressDialog(getActivity());
        pd2.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd2.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd2.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd2.setCancelable(true);
      pd2.show();

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
                    if(resource.type.equals("get")){
                        cartsellerid=resource.getSellerId();
                        carttotalcount=String.valueOf(resource.getCartCount());
                        previousstore=resource.getSellerStoreName();
                        if(getActivity()!=null) {
                            ((HomeScreen) getActivity()).updatecartcount(String.valueOf(resource.getCartCount()));

                        }
                    }

                    if(resource.type.equals("clear")){
                        carttotalcount="0";
                        addtoCartFun();
                        Toast.makeText(getActivity(),"Cart Cleared",Toast.LENGTH_LONG).show();
                    }

                    if(resource.type.equals("add")){

                        showDialog(getActivity());

//                        SharedPreferences.Editor editor = pref.edit();
//                        editor.putString("clatitude",resource.getUser_id());
//                        editor.putString("clongitude",resource.getBoy_phone());

                    }
                }else {
                    //after remove raju
                    if(resource.type!=null) {
                        if (resource.type.equals("clear")) {
                            carttotalcount = "0";
                            addtoCartFun();
                        }

                        if(resource.type.equals("get")){
                            cartsellerid=resource.getSellerId();
                            carttotalcount=String.valueOf(resource.getCartCount());
                            previousstore=resource.getSellerStoreName();
                        }
                    }

                    if(type.equals("add")){
                        Toast.makeText(getActivity(), "Already added Product.Please check cartList", Toast.LENGTH_LONG).show();
                    }
                    if (resource.getMessage() != "") {
                       // Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                pd2.dismiss();
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

    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.added_product_dialog);

        Button cancle = dialog.findViewById(R.id.cancle);
        Button ok = dialog.findViewById(R.id.ok);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                String id = pref.getString("user_id","");
                TabLayoutt.apiCallForCart(id,"get","","","","");

                getActivity().onBackPressed();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 dialog.dismiss();

                Util.loadFragmentAndClerBackStack(new CartDisplay(),getActivity(),ProductDetailedPage.this);

              //  ((HomeScreen)getActivity()).setCartCheck();
//                pref=getActivity()
//                        .getSharedPreferences("user", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putString("boy_id","");
//                editor.putString("boy_phone","");
//                editor.putString("boy_name","");
//                editor.commit();

//                Intent i=new Intent(getActivity(), PhoneNumber.class);
//                startActivity(i);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        System.out.println("rajuuu "+hidden);
        if (hidden) {
            //do when hidden
        } else {
            //do when show
            SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            String id = pref.getString("user_id","");
            //  apiCallForCart(id,"get","","","","");

        }
    }


}