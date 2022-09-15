package com.ellocartuser.RoomsNew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ellocartuser.AdaptersAndModel.RoomsDetailsList.RoomsDetailsListAdapter;
import com.ellocartuser.AdaptersAndModel.RoomsDetailsList.RoomsDetailsListModel;
import com.ellocartuser.AdaptersAndModel.RoomsLitsBased.RoomsListBasedAdapter;
import com.ellocartuser.AdaptersAndModel.RoomsLitsBased.RoomsListBasedModel;
import com.ellocartuser.R;
import com.ellocartuser.utils.Constant;
import com.ellocartuser.utils.CustomItemFive;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoomDetailingListActivtiy extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "RoomDetailsActivtiy";

    //rooms_detailing_list_items

    ImageView back;
    ProgressBar progressBar;

    RelativeLayout relative_cart_c;
    TextView cartcount;

    //recyclerview
    ImageView txt_no_data;
    RecyclerView recyclerview;
    private RoomsDetailsListAdapter roomsListBasedAdapter;
    private RecyclerView.LayoutManager layoutManagertrending;
    private ArrayList<RoomsDetailsListModel> home_data_list;


    String Seller_r_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details_activtiy);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null) {
            Seller_r_id = b.getString("Seller_r_id");
        }

        back = findViewById(R.id.back);
        progressBar = findViewById(R.id.progressBar);
        txt_no_data = findViewById(R.id.txt_no_data);
        relative_cart_c = findViewById(R.id.relative_cart_c);
        cartcount = findViewById(R.id.cartcount);


        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setHasFixedSize(true);
        layoutManagertrending = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManagertrending);
        home_data_list = new ArrayList<>();


        rooms_list();

        cart_count_service();


        back.setOnClickListener(this);
        relative_cart_c.setOnClickListener(this);
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
        }
    }


    private void rooms_list() {
        progressBar.setVisibility(View.VISIBLE);
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"roomTypes", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse:rooms_detaulssss "+response);
                home_data_list.clear();
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("status");
                    if (result.equalsIgnoreCase("ok")){
                        JSONArray jsonArray=jsonObject.getJSONArray("roomtypes");
                        if (jsonArray.length()==0){
                            recyclerview.setVisibility(View.INVISIBLE);
                            txt_no_data.setVisibility(View.VISIBLE);
                        }else{
                            recyclerview.setVisibility(View.VISIBLE);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String rchild_id = jsonObject1.getString("rchild_id");
                                String rchild_main = jsonObject1.getString("rchild_main");
                                String rchild_title = jsonObject1.getString("rchild_title");
                                String rchild_max_per = jsonObject1.getString("rchild_max_per");
                                String rchild_checkin = jsonObject1.getString("rchild_checkin");
                                String rchild_checkout = jsonObject1.getString("rchild_checkout");
                                String rchild_price = jsonObject1.getString("rchild_price");
                                String rchid_htime = jsonObject1.getString("rchid_htime");
                                String rchild_image1 = jsonObject1.getString("rchild_image1");
                                String rchild_status = jsonObject1.getString("rchild_status");
                                String rchild_max_price = jsonObject1.getString("rchild_max_price");





                                RoomsDetailsListModel homeModel = new RoomsDetailsListModel();
                                homeModel.setSeller_r_id(Seller_r_id);
                                homeModel.setRchild_id(rchild_id);
                                homeModel.setRchild_main(rchild_main);
                                homeModel.setRchild_title(rchild_title);
                                homeModel.setRchild_total("rchild_total");
//                                homeModel.setRchild_totalper(rchild_totalper);
                                homeModel.setMax(rchild_max_per);
                                homeModel.setMin("rchild_min_per");
                                homeModel.setRchild_checkin(rchild_checkin);
                                homeModel.setRchild_checkout(rchild_checkout);
                                homeModel.setRchild_desc("rchild_desc");
                                homeModel.setRchild_price(rchild_price);
                                homeModel.setRchid_htime(rchid_htime);
                                homeModel.setRchild_image1(rchild_image1);
                                homeModel.setRchild_status(rchild_status);
                                homeModel.setSell_min_price(rchild_max_price);
                                home_data_list.add(homeModel);
                            }
                        }
                    }
                    else {
                        txt_no_data.setVisibility(View.VISIBLE);
                    }
                    roomsListBasedAdapter=new RoomsDetailsListAdapter(home_data_list, getApplicationContext(),new CustomItemFive() {
                        @Override
                        public void onItemClick(View v, String id, String name, String name2, String name3, String name4) {

                        }
                    });
                    recyclerview.setAdapter(roomsListBasedAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    txt_no_data.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                txt_no_data.setVisibility(View.VISIBLE);
                Log.e(TAG, "onErrorResponse:error"+error );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("hotel_id",Seller_r_id);
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
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"cart_count_rooms", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse:cart_count_rooms "+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("status");
                    if (result.equalsIgnoreCase("ok")){
                        String cart_count=jsonObject.getString("cart_count");
                        cartcount.setText(cart_count);
                    }else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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