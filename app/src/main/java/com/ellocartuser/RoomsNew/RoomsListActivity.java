package com.ellocartuser.RoomsNew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.ellocartuser.AdaptersAndModel.RoomsLitsBased.RoomsListBasedAdapter;
import com.ellocartuser.AdaptersAndModel.RoomsLitsBased.RoomsListBasedModel;
import com.ellocartuser.R;
import com.ellocartuser.utils.Constant;
import com.ellocartuser.utils.CustomItemFive;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RoomsListActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "RoomsListActivity";

    ImageView back;
    TextView txt_title;
    ProgressBar progressBar;

    ProgressDialog pd1;

    RelativeLayout relative_cart_c;
    TextView cartcount;


    //recyclerview
    ImageView txt_no_data;
    EditText edit_search;
    RecyclerView recyclerview;
    private RoomsListBasedAdapter roomsListBasedAdapter;
    private RecyclerView.LayoutManager layoutManagertrending;
    private ArrayList<RoomsListBasedModel> home_data_list;


    String Rcat_id = "";
    String Rcat_name = "";
    String Rcat_image1 = "";
    String lat = "";
    String lng = "";
    String city = "";

    Double latitude;
    Double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_list);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null) {
            Rcat_id = b.getString("Rcat_id");
            Rcat_name = b.getString("Rcat_name");
            Rcat_image1 = b.getString("Rcat_image1");
            lat = b.getString("latitude");
            lng = b.getString("longitude");
        }
        latitude = Double.valueOf(lat);
        longitude = Double.valueOf(lng);


        back = findViewById(R.id.back);
        txt_title = findViewById(R.id.txt_title);
        txt_title.setText(Rcat_name);
        progressBar = findViewById(R.id.progressBar);
        txt_no_data = findViewById(R.id.txt_no_data);
        edit_search = findViewById(R.id.edit_search);
        relative_cart_c = findViewById(R.id.relative_cart_c);
        cartcount = findViewById(R.id.cartcount);

        cart_count_service();

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setHasFixedSize(true);
        layoutManagertrending = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManagertrending);
        home_data_list = new ArrayList<>();

        address_latlong();


        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                filter(text.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


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


    private void address_latlong() {
        if (lat.equalsIgnoreCase("")||lng.equalsIgnoreCase("")){

        }else {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                rools_list();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void rools_list() {
        progressBar.setVisibility(View.VISIBLE);
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"hotels", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse:roomsubcategories "+response);
                home_data_list.clear();
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String result=jsonObject.getString("status");
                    if (result.equalsIgnoreCase("ok")){
                        JSONArray jsonArray=jsonObject.getJSONArray("hotels");
                        if (jsonArray.length()==0){
                            recyclerview.setVisibility(View.INVISIBLE);
                            txt_no_data.setVisibility(View.VISIBLE);
                        }else{
                            recyclerview.setVisibility(View.VISIBLE);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String seller_r_id = jsonObject1.getString("seller_r_id");
                                String seller_r_name = jsonObject1.getString("seller_r_name");
                                String seller_r_store_name = jsonObject1.getString("seller_r_store_name");
                                String seller_r_type = jsonObject1.getString("seller_r_type");
                                String seller_r_phone_code = jsonObject1.getString("seller_r_phone_code");
                                String seller_r_phone = jsonObject1.getString("seller_r_phone");
                                String seller_r_email = jsonObject1.getString("seller_r_email");
                                String seller_r_img = jsonObject1.getString("seller_r_img");
                                String seller_r_lat = jsonObject1.getString("seller_r_lat");
                                String seller_r_long = jsonObject1.getString("seller_r_long");
                                String seller_r_cat = jsonObject1.getString("seller_r_cat");
                                String seller_r_city = jsonObject1.getString("seller_r_city");
                                String seller_r_address1 = jsonObject1.getString("seller_r_address1");
                                String seller_r_address2 = jsonObject1.getString("seller_r_address2");
                                String seller_r_pincode = jsonObject1.getString("seller_r_pincode");
                                String seller_r_proof_1 = jsonObject1.getString("seller_r_proof_1");
                                String seller_r_proof_2 = jsonObject1.getString("seller_r_proof_2");
                                String seller_r_admin_status = jsonObject1.getString("seller_r_admin_status");
                                String seller_r_status = jsonObject1.getString("seller_r_status");


                                RoomsListBasedModel homeModel = new RoomsListBasedModel();
                                homeModel.setSeller_r_id(seller_r_id);
                                homeModel.setSeller_r_name(seller_r_name);
                                homeModel.setSeller_r_store_name(seller_r_store_name);
                                homeModel.setSeller_r_type(seller_r_type);
                                homeModel.setSeller_r_phone_code(seller_r_phone_code);
                                homeModel.setSeller_r_phone(seller_r_phone);
                                homeModel.setSeller_r_email(seller_r_email);
                                homeModel.setSeller_r_img(seller_r_img);
                                homeModel.setSeller_r_lat(seller_r_lat);
                                homeModel.setSeller_r_long(seller_r_long);
                                homeModel.setSeller_r_cat(seller_r_cat);
                                homeModel.setSeller_r_city(seller_r_city);
                                homeModel.setSeller_r_address1(seller_r_address1);
                                homeModel.setSeller_r_address2(seller_r_address2);
                                homeModel.setSeller_r_pincode(seller_r_pincode);
                                homeModel.setSeller_r_proof_1(seller_r_proof_1);
                                homeModel.setSeller_r_proof_2(seller_r_proof_2);
                                homeModel.setSeller_r_admin_status(seller_r_admin_status);
                                homeModel.setSeller_r_status(seller_r_status);
                                home_data_list.add(homeModel);
                            }
                        }
                    }
                    else {
                        txt_no_data.setVisibility(View.VISIBLE);
                    }
                    roomsListBasedAdapter=new RoomsListBasedAdapter(home_data_list, getApplicationContext(),new CustomItemFive() {
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
                params.put("rcat_id",Rcat_id);
                params.put("user_lat",lat);
                params.put("user_long",lng);
                return params;
            }

        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                999999990,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void filter(String text) {
        List<RoomsListBasedModel> temp = new ArrayList();
        for(RoomsListBasedModel d: home_data_list){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getSeller_r_store_name().toLowerCase().contains(text)){
                temp.add(d);
            }
        }
        if (temp.size()==0){
            //Toast.makeText(getActivity(),"No State",Toast.LENGTH_LONG).show();
        }
        else {
            //update recyclerview
            roomsListBasedAdapter.updateList(temp);
        }
    }



    private void cart_count_service() {
        pd1 = new ProgressDialog(RoomsListActivity.this);
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();


        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL+"cart_count_rooms", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd1.dismiss();
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
                pd1.dismiss();
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