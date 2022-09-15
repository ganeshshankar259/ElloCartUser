package com.ellocartuser.RoomsNew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ellocartuser.AdaptersAndModel.MyRooms.MyRoomCardAdapter;
import com.ellocartuser.AdaptersAndModel.MyRooms.MyRoomCartModel;
import com.ellocartuser.AdaptersAndModel.RoomCart.RoomCardAdapter;
import com.ellocartuser.AdaptersAndModel.RoomCart.RoomCartModel;
import com.ellocartuser.R;
import com.ellocartuser.utils.Constant;
import com.ellocartuser.utils.CustomItemFive;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyRoomsList extends AppCompatActivity {
    private static final String TAG = "MyRoomsList";

    ImageView back;


    //recyclerview
    TextView txt_no_data;
    RecyclerView recyclerview;
    private MyRoomCardAdapter roomsListBasedAdapter;
    private RecyclerView.LayoutManager layoutManagertrending;
    private ArrayList<MyRoomCartModel> home_data_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rooms_list);


        back = findViewById(R.id.back);
        txt_no_data = findViewById(R.id.txt_no_data);

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setHasFixedSize(true);
        layoutManagertrending = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManagertrending);
        home_data_list = new ArrayList<>();

        my_rooms_list();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void my_rooms_list() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"my_bookings", new Response.Listener<String>() {
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
                            recyclerview.setVisibility(View.GONE);
                            txt_no_data.setVisibility(View.VISIBLE);

                        }else{
                            recyclerview.setVisibility(View.VISIBLE);
                            txt_no_data.setVisibility(View.GONE);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String rod_id = jsonObject1.getString("rod_id");
                                String rod_hotel = jsonObject1.getString("rod_hotel");
                                String rod_total = jsonObject1.getString("rod_total");
                                String rod_due = jsonObject1.getString("rod_due");
                                String rod_paid = jsonObject1.getString("rod_paid");
                                String rod_date = jsonObject1.getString("rod_date");
                                String rod_time = jsonObject1.getString("rod_time");
                                String rod_ref = jsonObject1.getString("rod_ref");
                                String rod_status = jsonObject1.getString("rod_status");
                                String seller_store_name = jsonObject1.getString("seller_store_name");
                                String seller_img = jsonObject1.getString("seller_img");
                                String seller_lat = jsonObject1.getString("seller_lat");
                                String seller_long = jsonObject1.getString("seller_long");


                                MyRoomCartModel homeModel = new MyRoomCartModel();
                                homeModel.setRod_id(rod_id);
                                homeModel.setRod_hotel(rod_hotel);
                                homeModel.setRod_total(rod_total);
                                homeModel.setRod_due(rod_due);
                                homeModel.setRod_paid(rod_paid);
                                homeModel.setRod_date(rod_date);
                                homeModel.setRod_time(rod_time);
                                homeModel.setRod_ref(rod_ref);
                                homeModel.setRod_status(rod_status);
                                homeModel.setSeller_store_name(seller_store_name);
                                homeModel.setSeller_img(seller_img);
                                homeModel.setSeller_lat(seller_lat);
                                homeModel.setSeller_long(seller_long);
                                home_data_list.add(homeModel);
                            }
                        }
                    }
                    else {
                        recyclerview.setVisibility(View.GONE);
                        txt_no_data.setVisibility(View.VISIBLE);
                    }
                    roomsListBasedAdapter=new MyRoomCardAdapter(home_data_list, getApplicationContext(),new CustomItemFive() {
                        @Override
                        public void onItemClick(View v, String sellerlat, String sellerlong, String Seller_store_name, String name3, String name4) {
                            String strUri = "http://maps.google.com/maps?q=loc:" + sellerlat + "," + sellerlong + " (" + Seller_store_name + ")";
                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                            startActivity(intent);
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



}