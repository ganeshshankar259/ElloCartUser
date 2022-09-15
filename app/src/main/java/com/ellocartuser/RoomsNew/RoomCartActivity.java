package com.ellocartuser.RoomsNew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ellocartuser.AdaptersAndModel.RoomCart.RoomCardAdapter;
import com.ellocartuser.AdaptersAndModel.RoomCart.RoomCartModel;
import com.ellocartuser.AdaptersAndModel.RoomsLitsBased.RoomsListBasedAdapter;
import com.ellocartuser.AdaptersAndModel.RoomsLitsBased.RoomsListBasedModel;
import com.ellocartuser.R;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.utils.Constant;
import com.ellocartuser.utils.CustomItemFive;
import com.ellocartuser.utils.Util;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoomCartActivity extends AppCompatActivity implements View.OnClickListener, PaymentResultWithDataListener{
    private static final String TAG = "RoomCartActivity";

    AlertDialog dialog;

    ProgressDialog pd1;

    ImageView back;

    CardView cardview_details;
    TextView txt_totalrooms;
    TextView txt_total_amount;
    TextView txt_advance_amount;
    TextView txt_remaining_amount;

    CardView card_view_clear;
    CardView card_view_continue;

    LottieAnimationView empty_card;
    RecyclerView recyclerview;
    private RoomCardAdapter roomsListBasedAdapter;
    private RecyclerView.LayoutManager layoutManagertrending;
    private ArrayList<RoomCartModel> home_data_list;


    String advance_amount = "";
    String razor_id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_cart);

        Util.loadhome(RoomCartActivity.this);
        Checkout.preload(RoomCartActivity.this);


        back = findViewById(R.id.back);
        cardview_details = findViewById(R.id.cardview_details);
        txt_totalrooms = findViewById(R.id.txt_totalrooms);
        txt_total_amount = findViewById(R.id.txt_total_amount);
        txt_advance_amount = findViewById(R.id.txt_advance_amount);
        txt_remaining_amount = findViewById(R.id.txt_remaining_amount);
        card_view_clear = findViewById(R.id.card_view_clear);
        card_view_continue = findViewById(R.id.card_view_continue);
        empty_card = findViewById(R.id.empty_card);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setHasFixedSize(true);
        layoutManagertrending = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManagertrending);
        home_data_list = new ArrayList<>();

        cardview_details.setVisibility(View.GONE);

        cart_details();

        back.setOnClickListener(this);
        card_view_clear.setOnClickListener(this);
        card_view_continue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.back:
               finish();
               break;
           case R.id.card_view_clear:
               break;
           case R.id.card_view_continue:
               book_rooms();
               break;
       }
    }


    private void cart_details() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"add_room_to_cart", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                home_data_list.clear();
                Log.e(TAG, "onResponse:add_room_to_cart "+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("status");
                    if (result.equalsIgnoreCase("ok")){
                        JSONArray jsonArray=jsonObject.getJSONArray("list");
                        if (jsonArray.length()==0){
                            recyclerview.setVisibility(View.INVISIBLE);
                            empty_card.setVisibility(View.VISIBLE);
                            cardview_details.setVisibility(View.GONE);
                        }else{
                            empty_card.setVisibility(View.GONE);
                            recyclerview.setVisibility(View.VISIBLE);
                            cardview_details.setVisibility(View.VISIBLE);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String bcart_id = jsonObject1.getString("bcart_id");
                                String bcart_childcat = jsonObject1.getString("bcart_childcat");
                                String bcart_per = jsonObject1.getString("bcart_per");
                                String bcart_date = jsonObject1.getString("bcart_date");
                                String bcart_price = jsonObject1.getString("bcart_price");
                                String rchild_id = jsonObject1.getString("rchild_id");
                                String rchild_title = jsonObject1.getString("rchild_title");
                                String rchild_image1 = jsonObject1.getString("rchild_image1");


                                RoomCartModel homeModel = new RoomCartModel();
                                homeModel.setBcart_id(bcart_id);
                                homeModel.setBcart_childcat(bcart_childcat);
                                homeModel.setBcart_per(bcart_per);
                                homeModel.setBcart_date(bcart_date);
                                homeModel.setBcart_price(bcart_price);
                                homeModel.setRchild_id(rchild_id);
                                homeModel.setRchild_title(rchild_title);
                                homeModel.setRchild_image1(rchild_image1);
                                home_data_list.add(homeModel);
                            }
                        }



                        String total_rooms=jsonObject.getString("total_rooms");
                        String total_amount=jsonObject.getString("total_amount");
                        advance_amount=jsonObject.getString("advance_amount");
                        String remaining_amount=jsonObject.getString("remaining_amount");

                        cardview_details.setVisibility(View.VISIBLE);

                        txt_totalrooms.setText(total_rooms);
                        txt_total_amount.setText("₹"+total_amount);
                        txt_advance_amount.setText("₹"+advance_amount);
                        txt_remaining_amount.setText("₹"+remaining_amount);

                    }
                    else {
                        empty_card.setVisibility(View.VISIBLE);
                        cardview_details.setVisibility(View.GONE);
                    }
                    roomsListBasedAdapter=new RoomCardAdapter(home_data_list, getApplicationContext(),new CustomItemFive() {
                        @Override
                        public void onItemClick(View v, String Bcart_id, String delete, String name2, String name3, String name4) {
                            delete_cart_item(Bcart_id);
                        }
                    });
                    recyclerview.setAdapter(roomsListBasedAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                empty_card.setVisibility(View.VISIBLE);
                cardview_details.setVisibility(View.GONE);
                Log.e(TAG, "onErrorResponse:error"+error );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                String user_id = pref.getString("user_id","");
                Map<String,String> params = new HashMap<>();
                params.put("type","get");
                params.put("user_id",user_id);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                999999990,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }


    private void delete_cart_item(String Bcart_id) {
        pd1 = new ProgressDialog(RoomCartActivity.this);
        pd1.setMessage("Loading...");
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"room_delete_cart", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd1.dismiss();
                Log.e(TAG, "onResponse:room_delete_cart "+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("status");
                    if (result.equalsIgnoreCase("ok")){
                        cart_details();
                    }else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd1.dismiss();
                Log.e(TAG, "onErrorResponse:error"+error );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                String user_id = pref.getString("user_id","");
                Map<String,String> params = new HashMap<>();
                params.put("type","single");
                params.put("user_id",user_id);
                params.put("bcart_id",Bcart_id);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                999999990,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }


    private void book_rooms() {
        pd1 = new ProgressDialog(RoomCartActivity.this);
        pd1.setMessage("Loading...");
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"book_rooms", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd1.dismiss();
                Log.e(TAG, "onResponse:book_rooms "+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("status");
                    razor_id=jsonObject.getString("razor_id");
                    if (result.equalsIgnoreCase("ok")){
                        if (advance_amount.equalsIgnoreCase("")){
                        }else {
                            if (razor_id.equalsIgnoreCase("")){
                            }else {
                                payment_razarpay();
                            }
                        }
                    }else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd1.dismiss();
                Log.e(TAG, "onErrorResponse:error"+error );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                String user_id = pref.getString("user_id","");
                Map<String,String> params = new HashMap<>();
                params.put("user_id",user_id);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                999999990,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }


    private void payment_razarpay() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String userMobile = pref.getString("user_phone", "");
        String userEmail = pref.getString("user_email", "");

        final Activity activity = this;
        final Checkout co = new Checkout();
        try {
            JSONObject options = new JSONObject();
            options.put("name", "ElloCart");
            options.put("description", "Re-Discovering Local Online Shopping");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://cdn.razorpay.com/logos/IUa4SiVrn6pGtz_original.png");
            options.put("theme.color", "#yellow");
            options.put("currency", "INR");
            options.put("amount", advance_amount+"00");

            JSONObject preFill = new JSONObject();
            preFill.put("email", userEmail);
            preFill.put("contact", userMobile);
            options.put("prefill", preFill);
            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        if (s.equalsIgnoreCase("")||s==null){
            Toast.makeText(RoomCartActivity.this, "Payment Failed Contact to Customer Support", Toast.LENGTH_SHORT).show();
        }else {
            payment_success();
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(RoomCartActivity.this, "Payment Failed", Toast.LENGTH_SHORT).show();
    }


    private void payment_success() {
        pd1 = new ProgressDialog(RoomCartActivity.this);
        pd1.setMessage("Loading...");
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"checkout_rooms", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd1.dismiss();
                Log.e(TAG, "onResponse:checkout_rooms "+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("status");
                    if (result.equalsIgnoreCase("ok")){
                        payment_successpopup();
                    }else {
                        Toast.makeText(RoomCartActivity.this, "Payment Failed Contact to Customer Support", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(RoomCartActivity.this, "Payment Failed Contact to Customer Support", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RoomCartActivity.this, "Payment Failed Contact to Customer Support", Toast.LENGTH_SHORT).show();
                pd1.dismiss();
                Log.e(TAG, "onErrorResponse:error"+error );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                String user_id = pref.getString("user_id","");
                Map<String,String> params = new HashMap<>();
                params.put("user_id",user_id);
                params.put("razor_id",razor_id);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                999999990,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void payment_successpopup() {
        AlertDialog.Builder mbuilder =new AlertDialog.Builder(RoomCartActivity.this);
        View mview =  LayoutInflater.from(RoomCartActivity.this).inflate(R.layout.dailog_payment_success,null);
        CardView cardview_ok = mview.findViewById(R.id.cardview_ok);

        mbuilder.setView(mview);
        dialog=mbuilder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        cardview_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(getApplicationContext(), HomeScreen.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(ii);
                dialog.dismiss();
            }
        });
    }


}