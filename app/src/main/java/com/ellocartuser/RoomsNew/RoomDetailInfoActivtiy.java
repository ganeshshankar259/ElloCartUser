package com.ellocartuser.RoomsNew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.ellocartuser.AdaptersAndModel.RoomDate.RoomDateAdapter;
import com.ellocartuser.AdaptersAndModel.RoomDate.RoomsdateModel;
import com.ellocartuser.AdaptersAndModel.RoomsDetailsList.RoomsDetailsListAdapter;
import com.ellocartuser.AdaptersAndModel.RoomsDetailsList.RoomsDetailsListModel;
import com.ellocartuser.R;
import com.ellocartuser.utils.Constant;
import com.ellocartuser.utils.CustomItemFive;
import com.ellocartuser.utils.CustomItemFourListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoomDetailInfoActivtiy extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RoomDetailInfoActivtiy";


    ImageView back;
    ProgressBar progressbar;
    ImageView img_product;
    TextView txt_product_title;
    TextView txt_product_title_1;
    TextView txt_price;
    TextView txt_time;
    TextView txt_check_ic_time;
    TextView txt_check_out_time;
    TextView txt_persons_remove_quantity;
    TextView txt_persons_quantity;
    TextView txt_persons_add_quantity;
    TextView txt_rooms_remove_quantity;
    TextView txt_rooms_quantity;
    TextView txt_rooms_add_quantity;
    TextView txt_r_price;
    TextView txt_av_rooms;
    CardView card_card_room;

    RelativeLayout relative_cart_c;
    TextView cartcount;

    LinearLayout linear_gotocart;

    //recyclerview
    ImageView txt_no_data;
    RecyclerView recyclerview;
    private RoomDateAdapter datesAdapter;
    private RecyclerView.LayoutManager layoutManagertrending;
    private ArrayList<RoomsdateModel> home_data_list;


    String Seller_r_id ="";
    String rchild_id ="";
    String rchild_main = "";
    String rchild_title = "";
    String rchild_total = "";
    String rchild_totalper = "";
    String min = "";
    String max = "";
    String rchild_checkin = "";
    String rchild_checkout = "";
    String rchild_desc = "";
    String rchild_price = "";
    String rchid_htime = "";
    String rchild_image1 = "";
    String rchild_status = "";
    String rchild_created = "";

    String d_date = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail_info_activtiy);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null) {
            Seller_r_id = b.getString("Seller_r_id");
            rchild_id = b.getString("rchild_id");
            rchild_main = b.getString("rchild_main");
            rchild_title = b.getString("rchild_title");
            rchild_total = b.getString("rchild_total");
            rchild_totalper = b.getString("rchild_totalper");
            min = b.getString("min");
            max = b.getString("max");
            rchild_checkin = b.getString("rchild_checkin");
            rchild_checkout = b.getString("rchild_checkout");
            rchild_desc = b.getString("rchild_desc");
            rchild_price = b.getString("rchild_price");
            rchid_htime = b.getString("rchid_htime");
            rchild_image1 = b.getString("rchild_image1");
            rchild_status = b.getString("rchild_status");
            rchild_created = b.getString("rchild_created");
        }

        Log.e(TAG, "onCreate:rchild_id "+rchild_id );

        back = findViewById(R.id.back);
        progressbar = findViewById(R.id.progressbar);
        img_product = findViewById(R.id.img_product);
        txt_product_title = findViewById(R.id.txt_product_title);
        txt_product_title_1 = findViewById(R.id.txt_product_title_1);
        txt_price = findViewById(R.id.txt_price);
        txt_time = findViewById(R.id.txt_time);
        txt_check_ic_time = findViewById(R.id.txt_check_ic_time);
        txt_check_out_time = findViewById(R.id.txt_check_out_time);
        txt_persons_remove_quantity = findViewById(R.id.txt_persons_remove_quantity);
        txt_persons_quantity = findViewById(R.id.txt_persons_quantity);
        txt_persons_add_quantity = findViewById(R.id.txt_persons_add_quantity);
        txt_rooms_remove_quantity = findViewById(R.id.txt_rooms_remove_quantity);
        txt_rooms_quantity = findViewById(R.id.txt_rooms_quantity);
        txt_rooms_add_quantity = findViewById(R.id.txt_rooms_add_quantity);
        txt_av_rooms = findViewById(R.id.txt_av_rooms);
        txt_r_price = findViewById(R.id.txt_r_price);
        card_card_room = findViewById(R.id.card_card_room);

        relative_cart_c = findViewById(R.id.relative_cart_c);
        cartcount = findViewById(R.id.cartcount);
        linear_gotocart = findViewById(R.id.linear_gotocart);


        set_data();
        cart_count_service();
        dates_list();


        txt_no_data = findViewById(R.id.txt_no_data);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setHasFixedSize(true);
        layoutManagertrending =
                new GridLayoutManager(getApplicationContext(),3, GridLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManagertrending);
        home_data_list = new ArrayList<>();

        back.setOnClickListener(this);
        relative_cart_c.setOnClickListener(this);
        txt_persons_remove_quantity.setOnClickListener(this);
        txt_persons_add_quantity.setOnClickListener(this);
        txt_rooms_remove_quantity.setOnClickListener(this);
        txt_rooms_add_quantity.setOnClickListener(this);
        card_card_room.setOnClickListener(this);
        linear_gotocart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.relative_cart_c:
                Intent intent = new Intent(getApplicationContext(),RoomCartActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_persons_remove_quantity:
                if (Integer.parseInt(txt_persons_quantity.getText().toString()) > 1) {
                    Integer q = Integer.parseInt(txt_persons_quantity.getText().toString()) - 1;
                    txt_persons_quantity.setText(q.toString());
                    if (d_date.equalsIgnoreCase("")||d_date== null){

                    }else {
                        avail_dates_rooms();
                    }
                } else {
                }
                break;
            case R.id.txt_persons_add_quantity:
                if (Integer.parseInt(txt_persons_quantity.getText().toString()) < Integer.parseInt(max)) {
                    if (Integer.parseInt(txt_persons_quantity.getText().toString()) >= 1) {
                        Integer q = Integer.parseInt(txt_persons_quantity.getText().toString()) + 1;
                        txt_persons_quantity.setText(q.toString());
                        if (d_date.equalsIgnoreCase("")||d_date== null){

                        }else {
                            avail_dates_rooms();
                        }
                    }
                } else {
                    Toast toast = Toast.makeText(RoomDetailInfoActivtiy.this, "No.of Persons only for " + max, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                break;
            case R.id.txt_rooms_remove_quantity:
                if (Integer.parseInt(txt_rooms_quantity.getText().toString()) > 1) {
                    Integer q = Integer.parseInt(txt_rooms_quantity.getText().toString()) - 1;
                    txt_rooms_quantity.setText(q.toString());
                } else {
                }
                break;
            case R.id.txt_rooms_add_quantity:
                if (Integer.parseInt(txt_rooms_quantity.getText().toString()) < Integer.parseInt(rchild_total)) {
                    if (Integer.parseInt(txt_rooms_quantity.getText().toString()) >= 1) {
                        Integer q = Integer.parseInt(txt_rooms_quantity.getText().toString()) + 1;
                        txt_rooms_quantity.setText(q.toString());
                    }
                } else {
                    Toast toast = Toast.makeText(RoomDetailInfoActivtiy.this, "Availability Rooms only for " + rchild_total, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                break;
            case R.id.card_card_room:
                Add_room_to_card();
                break;
            case R.id.linear_gotocart:
                Intent intentg = new Intent(getApplicationContext(),RoomCartActivity.class);
                startActivity(intentg);
                break;
        }
    }

    private void set_data() {
        if (rchild_image1==null || rchild_image1.equalsIgnoreCase("")){
            img_product.setImageResource(R.drawable.placeholderello);
        }else {

            Glide.with(getApplicationContext())
                    .load(rchild_image1)
                    .fitCenter().placeholder(R.drawable.placeholderello)
                    .into(img_product);
        }

        txt_product_title.setText(rchild_title);
        txt_product_title_1.setText(rchild_title);
        txt_price.setText("₹"+rchild_price);
        txt_time.setText("Checkout "+rchid_htime);
        txt_check_ic_time.setText(rchild_checkin);
        txt_check_out_time.setText(rchild_checkout);
    }

    private void dates_list() {
        txt_av_rooms.setVisibility(View.GONE);
        txt_r_price.setVisibility(View.GONE);
        card_card_room.setVisibility(View.GONE);

        progressbar.setVisibility(View.VISIBLE);
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"get_room_dates", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                home_data_list.clear();
                progressbar.setVisibility(View.GONE);
                Log.e(TAG, "onResponse:get_room_dates "+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("status");
                    if (result.equalsIgnoreCase("ok")){
                        JSONArray jsonArray=jsonObject.getJSONArray("roomsdata");
                        if (jsonArray.length()==0){
                            recyclerview.setVisibility(View.GONE);
                            txt_no_data.setVisibility(View.VISIBLE);
                        }else{
                            recyclerview.setVisibility(View.VISIBLE);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String sell_assign_id = jsonObject1.getString("sell_assign_id");
                                String sell_assign_date = jsonObject1.getString("sell_assign_date");


                                RoomsdateModel homeModel = new RoomsdateModel();
                                homeModel.setSell_assign_id(sell_assign_id);
                                homeModel.setSell_assign_date(sell_assign_date);
                                home_data_list.add(homeModel);
                            }
                        }
                    }
                    else {
                        recyclerview.setVisibility(View.GONE);
                        txt_no_data.setVisibility(View.VISIBLE);
                    }
                    datesAdapter=new RoomDateAdapter(home_data_list, getApplicationContext(),new CustomItemFourListener() {
                        @Override
                        public void onItemClick(View v, String r_date, String name, String pincode, String pincode1) {
                            d_date = r_date;
                            avail_dates_rooms();
                        }
                    });
                    recyclerview.setAdapter(datesAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    recyclerview.setVisibility(View.GONE);
                    txt_no_data.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressbar.setVisibility(View.GONE);
                recyclerview.setVisibility(View.GONE);
                txt_no_data.setVisibility(View.VISIBLE);
                Log.e(TAG, "onErrorResponse:error"+error );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("room_id",rchild_id);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                999999990,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void avail_dates_rooms() {
        progressbar.setVisibility(View.VISIBLE);
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"avail_dates_rooms", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressbar.setVisibility(View.GONE);
                Log.e(TAG, "onResponse:avail_dates_rooms "+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("status");
                    if (result.equalsIgnoreCase("ok")){
                        String rooms_available=jsonObject.getString("rooms_available");
                        String sell_max_per=jsonObject.getString("sell_max_per");
                        String noof_persons=jsonObject.getString("noof_persons");
                        String price=jsonObject.getString("price");


                        txt_av_rooms.setText("Available: "+rooms_available);
                        txt_r_price.setText("Price: ₹"+price);

                        txt_av_rooms.setVisibility(View.VISIBLE);
                        txt_r_price.setVisibility(View.VISIBLE);
                        card_card_room.setVisibility(View.VISIBLE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressbar.setVisibility(View.GONE);
                Log.e(TAG, "onErrorResponse:error"+error );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                String user_id = pref.getString("user_id","");
                Map<String,String> params = new HashMap<>();
                params.put("room_id",rchild_id);
                params.put("rd_date",d_date);
                params.put("noof_persons",txt_persons_quantity.getText().toString());
                params.put("noof_rooms","1");
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

    private void Add_room_to_card() {
        progressbar.setVisibility(View.VISIBLE);
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"add_room_to_cart", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressbar.setVisibility(View.GONE);
                Log.e(TAG, "onResponse:add_room_to_cart "+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("status");
                    if (result.equalsIgnoreCase("ok")){
                        linear_gotocart.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),"Item Added",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Item Added Failed",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressbar.setVisibility(View.GONE);
                Log.e(TAG, "onErrorResponse:error"+error );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                String user_id = pref.getString("user_id","");
                Map<String,String> params = new HashMap<>();
                params.put("type","add");
                params.put("user_id",user_id);
                params.put("hotel_id",Seller_r_id);
                params.put("room_id",rchild_id);
                params.put("rd_date",d_date);
                params.put("noof_persons",txt_rooms_quantity.getText().toString());
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                999999990,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void cart_count_service() {
        progressbar.setVisibility(View.VISIBLE);
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"cart_count_rooms", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressbar.setVisibility(View.GONE);
                Log.e(TAG, "onResponse:cart_count_rooms "+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("status");
                    if (result.equalsIgnoreCase("ok")){
                        String cart_count=jsonObject.getString("cart_count");
                        cartcount.setText(cart_count);
                        dates_list();
                    }else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressbar.setVisibility(View.GONE);
                Log.e(TAG, "onErrorResponse:error"+error );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                String user_id = pref.getString("user_id","");
                Map<String,String> params = new HashMap<>();
                params.put("type","count");
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

}