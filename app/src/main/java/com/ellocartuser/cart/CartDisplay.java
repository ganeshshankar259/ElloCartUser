package com.ellocartuser.cart;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.Login;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CartResponce;
import com.ellocartuser.home.adapterandmodel.Cart;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartDisplay#newInstance} factory method to
 * create an instance of this fragment.
 */


public class CartDisplay extends Fragment implements CartAdapter.OnItemClickedCart {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressDialog pd1;
    CardView carddetail;
    int producttotal;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView saveprise,productprice;
    RecyclerView cartList;
    Integer item_total,item_save;
    CartAdapter adapter;
    TextView totalamt,storename,minimuorder,ttlitems;
    CartAdapter.OnItemClickedCart onclick;
    Button clear,checkout;
    ProgressDialog pd2;
    CartResponce cartresource=null;
    LinearLayout toplay1,toplay2,downlayout,noorder;
    String minimumamt=null;
    String cartcod=null,seller_type="";
    ImageView imageback;
        String seller_lat=null,seller_long=null,status_seller_on;
    //   LinearLayout downlayout,cartList;
    public CartDisplay() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cart.
     */
    // TODO: Rename and change types and number of parameters
    public static CartDisplay newInstance(String param1, String param2) {
        CartDisplay fragment = new CartDisplay();
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

        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        productprice = view.findViewById(R.id.productprice);
        saveprise = view.findViewById(R.id.saveprise);
        ttlitems = view.findViewById(R.id.ttlitems);
        totalamt = view.findViewById(R.id.totalamt);
        imageback = view.findViewById(R.id.imageback);
        minimuorder = view.findViewById(R.id.minimuorder);
        storename = view.findViewById(R.id.storename);
        clear = view.findViewById(R.id.clear);
        carddetail = view.findViewById(R.id.carddetail);
        checkout = view.findViewById(R.id.checkout);
        cartList = view.findViewById(R.id.cartList);
        toplay1 = view.findViewById(R.id.linearLayout4);
        toplay2 = view.findViewById(R.id.linearLayout3);
        downlayout = view.findViewById(R.id.downlayout);
        noorder = view.findViewById(R.id.noorder);
        cartList.setLayoutManager(new LinearLayoutManager(getContext()));
        cartList.setNestedScrollingEnabled(false);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartresource != null && minimumamt != null) {

                    if(status_seller_on.equals("0")){
                        Util.AlertWithOK(getActivity(),"Currently Shop Closed");
                        return;
                    }

                    if (producttotal >= Integer.valueOf(minimumamt)) {
                     //   showDialog(getActivity());
                        if(seller_type.equals("1")){
                            Intent ii = new Intent(getActivity(), CheckoutNext.class);
                            ii.putExtra("name", cartresource.getSellerStoreName());
                            ii.putExtra("delivery_charges", String.valueOf(cartresource.getDelivery_charges()));
                            ii.putExtra("phonenumer", cartresource.getSellerPhone());
                            ii.putExtra("address", cartresource.getSellerStoreAddress() + "," + cartresource.getSellerCity() + "," + cartresource.getSellerPincode());
                            ii.putExtra("imagename", cartresource.getSellerStoreImage());
                            ii.putExtra("cartcod", cartcod);
                            ii.putExtra("seller_time", cartresource.getSeller_time());
                            // Log.d("sriram cod",cartcod);
                            ii.putExtra("item_save", String.valueOf(item_save));
                            ii.putExtra("count", String.valueOf(cartresource.getCart().size()));
                            ii.putExtra("carttotal", String.valueOf(cartresource.getCartTotal()));
                            ii.putExtra("seller_lat", seller_lat);
                            ii.putExtra("seller_long", seller_long);
                            ii.putExtra("seller_day", cartresource.getSeller_day());
                            ii.putExtra("order_type", "1");
                            ii.putExtra("seller_type", "1");  // for type local or india
                            startActivity(ii);
                        } else {
                            Intent ii = new Intent(getActivity(), CheckoutNext.class);
                            ii.putExtra("name", cartresource.getSellerStoreName());
                            ii.putExtra("delivery_charges", String.valueOf(cartresource.getDelivery_charges()));
                            ii.putExtra("phonenumer", cartresource.getSellerPhone());
                            ii.putExtra("address", cartresource.getSellerStoreAddress() + "," + cartresource.getSellerCity() + "," + cartresource.getSellerPincode());
                            ii.putExtra("imagename", cartresource.getSellerStoreImage());
                            ii.putExtra("cartcod", cartcod);
                            ii.putExtra("seller_time", cartresource.getSeller_time());
                            // Log.d("sriram cod",cartcod);
                            ii.putExtra("seller_day", cartresource.getSeller_day());

                            ii.putExtra("item_save", String.valueOf(item_save));
                            ii.putExtra("count", String.valueOf(cartresource.getCart().size()));
                            ii.putExtra("carttotal", String.valueOf(cartresource.getCartTotal()));
                            ii.putExtra("seller_lat", seller_lat);
                            ii.putExtra("seller_long", seller_long);
                            ii.putExtra("order_type", "1");
                            ii.putExtra("seller_type", "0");  // for type local or india
                            startActivity(ii);
                        }
                    } else {
                        try {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            //  builder.setTitle("C");
                            builder.setMessage("Minimum Order quantity ₹"+minimumamt+". Add more to place order");
                            builder.setCancelable(true);
                            builder.setPositiveButton(
                                    "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();

                                        }

                                    }
                            );

                            AlertDialog alert = builder.create();
                            alert.show();
                        } catch (RuntimeException ex) {
                            ex.printStackTrace();
                        }
                    }
                }else{
                    Toast.makeText(getActivity(), "minimun amt "+minimumamt, Toast.LENGTH_LONG).show();
                }
            }

        });

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        if(id.equals("") || id==null){
            Util.PleaseLogin(getContext());
            downlayout.setVisibility(View.GONE);
        }else{
            apiCallForCart(id,"get","","","","");
        }

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    // builder.setTitle("De");
                    builder.setMessage(R.string.clear_cart);
                    builder.setCancelable(true);
                    builder.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                                    String idd = pref.getString("user_id","");
                                    apiCallForCart(idd,"clear","","","","");
                                }
                            });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                } catch (RuntimeException ex) {
                    ex.printStackTrace();
                }

            }
        });

        onclick=this;

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        if(id.equals("") || id==null){
            Util.PleaseLogin(getContext());
            downlayout.setVisibility(View.GONE);
        }else{
            apiCallForCart(id,"get","","","","");
        }

    }

    public void apiCallForCart(String userid, String type, String product_id, String product_qty, String sproduct_id, String seller_id){

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
                cartresource=resource;
             //   pd1.dismiss();

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    if (type.equals("get")) {
                        status_seller_on=resource.getSeller_ostatus();
                        seller_type=resource.getSeller_type();
                        if(getActivity()!=null) {
                            ((HomeScreen) getActivity()).updatecartcount(String.valueOf(resource.getCartCount()));

                        }
                      //  ((HomeScreen) getActivity()).updatecartcount(String.valueOf(resource.getCartCount()));

                    }
//                    if(resource.getType().equals("delete") || resource.getType().equals("clear")){
//                        apiCallForCart("66","get","","","","");
//                        return;
//                    }
                    //catstore
                   // item_total=resource.getItems_total();

                    item_save=resource.getCart_save();
                    seller_lat=resource.getSellerLat();
                    seller_long=resource.getSellerLong();
                    producttotal=resource.getCart_save()+resource.getCartTotal();
                    productprice.setText("₹"+producttotal);
                 // saveprise.setText("-₹"+item_save);
                    cartcod=resource.getCart_cod();
                    minimumamt=resource.getSeller_minimum_order();
                    ttlitems.setText(""+String.valueOf(resource.getCartCount()));
                    minimuorder.setText("Minimum Order ₹"+resource.getSeller_minimum_order());
                  //  totalamt.setText("₹"+String.valueOf(resource.getCartTotal()));
                    storename.setText(resource.getSellerStoreName());
                    adapter=new CartAdapter(getActivity(),resource.cart,onclick);
                    cartList.setAdapter(adapter);

                    toplay1.setVisibility(View.VISIBLE);
                    toplay2.setVisibility(View.VISIBLE);
                    downlayout.setVisibility(View.VISIBLE);
                    cartList.setVisibility(View.VISIBLE);
                    noorder.setVisibility(View.GONE);

                    carddetail.setVisibility(View.VISIBLE);

                } else {

                    if(resource.type!=null) {

                        carddetail.setVisibility(View.GONE);

                        if (resource.type.equals("delete") || resource.type.equals("clear")) {

                            if(resource.getStatus().equals("error")){
                                List<Cart> cart = new ArrayList<>();
                                adapter=new CartAdapter(getActivity(),cart,onclick);
                                cartList.setAdapter(adapter);

                                toplay1.setVisibility(View.GONE);
                                toplay2.setVisibility(View.GONE);
                                downlayout.setVisibility(View.GONE);
                                cartList.setVisibility(View.GONE);
                                noorder.setVisibility(View.VISIBLE);
                                if(getActivity()!=null) {
                                    ((HomeScreen) getActivity()).updatecartcount("0");
                                }
//                                apiCallForCart("66","get","","","","");
//                                return;

                            }

                        }
                    }
                    if (resource.getMessage() != "") {

                        toplay1.setVisibility(View.GONE);
                        toplay2.setVisibility(View.GONE);
                        downlayout.setVisibility(View.GONE);
                        cartList.setVisibility(View.GONE);
                        noorder.setVisibility(View.VISIBLE);

                        //    Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                if(resource.type!=null) {
                    if (resource.type.equals("delete")) {
                        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                        String id = pref.getString("user_id", "");
                        if (id.equals("") || id == null) {
                            Util.PleaseLogin(getContext());
                            downlayout.setVisibility(View.GONE);
                        } else {
                            apiCallForCart(id, "get", "", "", "", "");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CartResponce> call, Throwable t) {
                //   pd.dismiss();
            //    pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void apiCallForCartChange(String userid,String type,String product_id,Integer product_qty,String sproduct_id,String seller_id){


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
        pd1.show();
        Call<CartResponce> getCate = ApiClient.getApiService().setCart(userid,type,product_id,String.valueOf(product_qty),sproduct_id,seller_id);
        getCate.enqueue(new Callback<CartResponce>() {
            @Override
            public void onResponse(Call<CartResponce> call, Response<CartResponce> response) {
                final CartResponce resource = response.body();
                cartresource=resource;
                pd1.dismiss();

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    //catstore

                    totalamt.setText("Total Amt : ₹"+String.valueOf(resource.getCartTotal()));
                    storename.setText(resource.getSellerStoreName());

                    adapter=new CartAdapter(getActivity(),resource.cart,onclick);
                    cartList.setAdapter(adapter);
                    SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                    String id = pref.getString("user_id","");
                    apiCallForCart(id,"get","","","","");

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

    @Override
    public void onItemClickedCart(int position, String mParam1, String qty, String productid, String sproductid,String sellerid) {

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        if(mParam1.equals("plus")){
            //      public void apiCallForCart(String userid,String type,String product_id,String product_qty,String sproduct_id,String seller_id){

            apiCallForCartChange(id,"add",productid,Integer.parseInt(qty)+1,sproductid,sellerid);
            //  Toast.makeText(getActivity(), "plus", Toast.LENGTH_LONG).show();


        }else  if(mParam1.equals("minus")) {
            apiCallForCartChange(id,"add",productid,Integer.parseInt(qty)-1,sproductid,sellerid);

            // Toast.makeText(getActivity(), "minus", Toast.LENGTH_LONG).show();

        }else  if(mParam1.equals("delet")) {


            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // builder.setTitle("De");
                builder.setMessage(getResources().getString(R.string.delete));
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                                String idd = pref.getString("user_id","");
                                apiCallForCart(idd,"delete",productid,"",sproductid,sellerid);

                            }
                        });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }

            //  Toast.makeText(getActivity(), "delete", Toast.LENGTH_LONG).show();

        }

    }

    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.deliverytype_dialog);

        RadioButton rbpickup = dialog.findViewById(R.id.rbpickup);
        RadioButton rbdelivery = dialog.findViewById(R.id.rbdelivery);

        ImageView close=dialog.findViewById(R.id.close);
        Button continues=dialog.findViewById(R.id.continues);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        continues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cartresource!=null) {
                    String selected="";
                    if(rbpickup.isChecked()){
                        selected="0";
                    }else if(rbdelivery.isChecked()){
                        selected="1";
                    }

                    if(selected.equals("")){
                        Toast.makeText(getActivity(), "please select delivery mode", Toast.LENGTH_LONG).show();
                        return;
                    }
                    dialog.dismiss();

                    if(selected.equals("0")){
                        Intent ii = new Intent(activity, PickUpDetailScreen.class);
                        ii.putExtra("name", cartresource.getSellerStoreName());
                        ii.putExtra("phonenumer", cartresource.getSellerPhone());
                        ii.putExtra("address", cartresource.getSellerStoreAddress() + "," + cartresource.getSellerCity() + "," + cartresource.getSellerPincode());
                        ii.putExtra("imagename", cartresource.getSellerStoreImage());
                        ii.putExtra("seller_time", cartresource.getSeller_time());
                        ii.putExtra("cartcod", cartcod);
                        ii.putExtra("count", String.valueOf(cartresource.getCart().size()));
                        ii.putExtra("carttotal", String.valueOf(cartresource.getCartTotal()));
                        ii.putExtra("item_save",String.valueOf(item_save));
                        ii.putExtra("seller_lat", seller_lat);
                        ii.putExtra("seller_long", seller_long);
                        ii.putExtra("order_type", selected);
                        startActivity(ii);

                    }else  if(selected.equals("1")) {

                        Intent ii = new Intent(activity, CheckoutNext.class);
                        ii.putExtra("name", cartresource.getSellerStoreName());
                        ii.putExtra("delivery_charges", String.valueOf(cartresource.getDelivery_charges()));
                        ii.putExtra("phonenumer", cartresource.getSellerPhone());
                        ii.putExtra("address", cartresource.getSellerStoreAddress() + "," + cartresource.getSellerCity() + "," + cartresource.getSellerPincode());
                        ii.putExtra("imagename", cartresource.getSellerStoreImage());
                        ii.putExtra("cartcod", cartcod);
                        ii.putExtra("seller_time", cartresource.getSeller_time());
                        // Log.d("sriram cod",cartcod);
                        ii.putExtra("item_save", String.valueOf(item_save));

                        ii.putExtra("count", String.valueOf(cartresource.getCart().size()));
                        ii.putExtra("carttotal", String.valueOf(cartresource.getCartTotal()));
                        ii.putExtra("seller_lat", seller_lat);
                        ii.putExtra("seller_long", seller_long);
                        ii.putExtra("order_type", selected);
                        startActivity(ii);
                    }

                }

            }
        });

        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
}