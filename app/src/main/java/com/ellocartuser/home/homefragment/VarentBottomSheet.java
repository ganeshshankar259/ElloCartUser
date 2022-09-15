package com.ellocartuser.home.homefragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CartResponce;
import com.ellocartuser.apiservices.Responce.ProductDetailedResponce;
import com.ellocartuser.cart.CartDisplay;
import com.ellocartuser.home.adapterandmodel.ProductDetailedAdapter;
import com.ellocartuser.home.adapterandmodel.ProductDetailedSubVarentAdapter;
import com.ellocartuser.home.adapterandmodel.ProductSubs;
import com.ellocartuser.home.adapterandmodel.ProductVariables;
import com.ellocartuser.home.adapterandmodel.ScreenItem;
import com.ellocartuser.home.adapterandmodel.SliderModel;
import com.ellocartuser.home.homefragment.shopinfotab.TabLayoutt;
import com.ellocartuser.utils.Util;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VarentBottomSheet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VarentBottomSheet extends BottomSheetDialogFragment implements  ProductDetailedAdapter.OnItemClickedVarient , ProductDetailedSubVarentAdapter.OnItemClickedSubVarient {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressDialog pd1;
    TextView cattxt,subcattxt;
    List<ProductVariables> varlist=new ArrayList();
    List<ProductSubs> subvarlist=new ArrayList();
    // TODO: Rename and change types of parameters
    private String cartsellerid=null,carttotalcount="",currentstore="";
    TextView count,percentage;
    Button minus,plus;
    private String  previousstore="";
    RecyclerView varentlist,subvarentlist,reviewlist;
    private ProductDetailedAdapter adapterv;
    private ProductDetailedSubVarentAdapter adaptersubv;
    public VarentBottomSheet() {
        // Required empty public constructor
    }
    ProductDetailedAdapter.OnItemClickedVarient onclick;
    ProductDetailedSubVarentAdapter.OnItemClickedSubVarient onclicksubvarient;
    private String sellerid="",productid="",store_o="";
    Button addtocart;
    TextView producttitle,itemamt;
    ImageView img,imgclose;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VarentBottomSheet.
     */
    // TODO: Rename and change types and number of parameters
    public static VarentBottomSheet newInstance(String param1, String param2) {
        VarentBottomSheet fragment = new VarentBottomSheet();
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
        View view = inflater.inflate(R.layout.fragment_varent_bottom_sheet, container, false);

        imgclose = view.findViewById(R.id.imgclose);
        img = view.findViewById(R.id.img);
        cattxt = view.findViewById(R.id.textView14);
        subcattxt = view.findViewById(R.id.textView15);
        producttitle = view.findViewById(R.id.producttitle);
        itemamt = view.findViewById(R.id.itemamt);
        addtocart = view.findViewById(R.id.addtocart);

        count = view.findViewById(R.id.count);
        minus = view.findViewById(R.id.minus);
        plus = view.findViewById(R.id.plus);

        varentlist = view.findViewById(R.id.varentlist);
        subvarentlist = view.findViewById(R.id.subvarentlist);

        onclick=this;
        onclicksubvarient=this;

        SharedPreferences  pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        currentstore = pref.getString("currentstore","");

        varentlist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

       // subvarentlist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false);
        subvarentlist.setLayoutManager(gridLayoutManager);

     //   SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
    //    apicall(sellerid,id,productid,"","");

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                String id = pref.getString("user_id","");

                if(id==null || id.equals("")){
//                    Util.PleaseLogin(getContext());
                    Util.PleaseLogin(getContext());

                    return;
                }

                if( store_o.equals("1")) {
                    addtoCartFun();
                }else{
                    Util.AlertWithOK(getActivity(),"Currently not accepting orders");//Toast.makeText(getActivity(),",Toast.LENGTH_LONG).show();
                }

            }
        });

        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nump= Integer.parseInt(count.getText().toString());
                nump=nump+1;
                count.setText(Integer.toString(nump));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num= Integer.parseInt(count.getText().toString());
                if(num==0 || num==1){ num=1; }else {
                    num = num - 1;
                }
                count.setText(Integer.toString(num));
            }
        });


        apiCallForCart(id,"get","","","","");

        apicall(sellerid,id,productid,"","");


        return view;
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

                    store_o=resource.getStore_o();

                    if(getActivity()==null){
                        return;
                    }

                    if(resource.getProduct().get(0).getProductVariables().size()!=0){
                        //  vid=resource.getProduct().get(0).getProductVariables()
                        varlist =   resource.getProduct().get(0).getProductVariables();
//                       for(int i=0;i<resource.getProduct().get(0).getProductVariables().size();i++){
//                           resource.getProduct().get(0).getProductVariables().get(i).setSelected("0");
//                       }
                        adapterv = new ProductDetailedAdapter(getActivity(),resource.getProduct().get(0).getProductVariables(),onclick,R.layout.custom_grid_layout_vareient_dialog);
                        varentlist.setAdapter(adapterv);



                    }else{
                        cattxt.setVisibility(View.GONE);
                    }

                    if(resource.getProduct().get(0).getProductSubs().size()!=0){
                        subvarlist =  resource.getProduct().get(0).getProductSubs();
//                       for(int i=0;i<resource.getProduct().get(0).getProductSubs().size();i++){
//                           resource.getProduct().get(0).getProductSubs().get(i).setSelected("0");
//                       }
                        adaptersubv = new ProductDetailedSubVarentAdapter(getActivity(),resource.getProduct().get(0).getProductSubs(),onclicksubvarient,R.layout.custom_grid_layout_vareient_dialog_subcat);
                        subvarentlist.setAdapter(adaptersubv);

                    }else{
                        subcattxt.setVisibility(View.GONE);
                    }

                    producttitle.setText(resource.getProduct().get(0).getProductName());

                    itemamt.setText("₹"+resource.getProduct().get(0).getProductMrp()+"-"+resource.getProduct().get(0).getProductMeasure());


                try {
                    if (resource.getProduct().get(0).getProductImages().get(0) != null) {
                        Glide.with(getActivity())
                                .load(resource.getProduct().get(0).getProductImages().get(0))
                                .fitCenter().placeholder(R.drawable.placeholderello)
                                .into(img);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
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



    public void apiCallForCart(String userid,String type,String product_id,String product_qty,String sproduct_id,String seller_id){

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
                    }

                    if(resource.type.equals("clear")){
                        carttotalcount="0";
                        addtoCartFun();
                        Toast.makeText(getActivity(),"Cart Cleared",Toast.LENGTH_LONG).show();
                    }

                    if(resource.type.equals("add")){

                        //   showDialog(getActivity());

                        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                        String id = pref.getString("user_id","");
                        TabLayoutt.apiCallForCart(id,"get","","","","");
                        dismiss();

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


    @Override
    public void onItemClickVarent(int position, String catid) {
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        String id = pref.getString("user_id","");
        apicall(sellerid,id,productid,catid,"");
    }

    @Override
    public void onItemClickSubVarent(int position, String catid, String selectidv) {

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        apicall(sellerid,id,productid,selectidv,catid);

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

                SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                String id = pref.getString("user_id","");
                TabLayoutt.apiCallForCart(id,"get","","","","");


                dialog.dismiss();
                dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Util.loadFragment(new CartDisplay(),getActivity(),VarentBottomSheet.this);

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

}