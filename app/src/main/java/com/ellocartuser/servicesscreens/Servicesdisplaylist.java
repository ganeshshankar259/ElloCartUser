  package com.ellocartuser.servicesscreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.ProductsResponce;
import com.ellocartuser.apiservices.Responce.ServiceProfileResponce;
import com.ellocartuser.apiservices.Responce.SubCatResponce;
import com.ellocartuser.apiservices.model.ServiceProfile;
import com.ellocartuser.home.adapterandmodel.Products;
import com.ellocartuser.home.adapterandmodel.ProductsAdapter;
import com.ellocartuser.home.homefragment.CategoryFragment;
import com.ellocartuser.home.homefragment.ProductDetailedPage;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.tutions.Tut_Appoint_Fragment;
import com.ellocartuser.tutions.Tutions_Base;
import com.ellocartuser.utils.Util;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

  public class Servicesdisplaylist extends AppCompatActivity implements ServiceListAdapter.OnItemClickedproduct{
      private static final String TAG = "Servicesdisplaylist";

      RecyclerView recyclerView;
      ProgressDialog pd1;
      ProductsAdapter adapter;
      ImageView imageback;
      EditText etSearch;
      TextView current;
      LinearLayout noorder;
      ServiceListAdapter seradapter;
      List<ServiceProfile> datalist=new ArrayList<>();
      ServiceListAdapter.OnItemClickedproduct onclick;
      String subcatname,subcatid;

      public static void hideKeyboard(Activity activity) {
          InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
          //Find the currently focused view, so we can grab the correct window token from it.
          View view = activity.getCurrentFocus();
          //If no view currently has focus, create a new one, just so we can grab a window token from it
          if (view == null) {
              view = new View(activity);
          }
          imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
      }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicesdisplaylist);

        hideKeyboard(Servicesdisplaylist.this);

        noorder = findViewById(R.id.noorder);
        etSearch = findViewById(R.id.etSearch);
        current = findViewById(R.id.current);

        SharedPreferences pref1 = getApplicationContext().getSharedPreferences("SERVICES_SUBCATID1", Context.MODE_PRIVATE);
        subcatid = pref1.getString("subcatid","");
        subcatname = pref1.getString("subcatname","");

        //current.setText(getIntent().getStringExtra("subcatname"));
        current.setText(subcatname);
        recyclerView = findViewById(R.id.catList);
        recyclerView.setLayoutManager(new LinearLayoutManager(Servicesdisplaylist.this));

        onclick=this;

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

        imageback = findViewById(R.id.imageback);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
//                Intent intent = new Intent(Servicesdisplaylist.this, SubCategoryItems.class);
////                intent.putExtra("type","TUTAPPOINT");
//                startActivity(intent);
            }
        });

        //getProfileApi();

    }

      @Override
      public void onResume() {
          super.onResume();
          SharedPreferences pref = getApplicationContext().getSharedPreferences("GET_ROOMINFO", Context.MODE_PRIVATE);
          //roomid = pref.getString("r_id", "");
          getProfileApi();

          //apiCall();
      }

        void filter(String text){
            List<ServiceProfile> temp = new ArrayList();
            for(int i=0;i<datalist.size();i++){
                if(datalist.get(i).getPost_title().contains(text) || datalist.get(i).getPost_title().toLowerCase().contains(text)){
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
            seradapter.updateList(temp);
        }
      private void getProfileApi() {

          pd1 = new ProgressDialog(Servicesdisplaylist.this);
          pd1.setMessage("Loading...");
          //   pd.setProgressStyle(R.style.ProgressBar);
          pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
           pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
          pd1.setCancelable(false);
          pd1.show();
//        pd1.show();
//        @Field("type") RequestBody type,

          SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
          String id = pref.getString("user_id","");
          String lat = pref.getString("latitude","");
          String longi = pref.getString("longitude","");


//          getIntent().getStringExtra("subcatid")

//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
          Call<ServiceProfileResponce> getCate = ApiClient.getApiServiceforservice().getServiceProfiles("get_posts",lat,longi,subcatid);
          getCate.enqueue(new Callback<ServiceProfileResponce>() {
              @Override
              public void onResponse(Call<ServiceProfileResponce> call, Response<ServiceProfileResponce> response) {
                  final ServiceProfileResponce resource = response.body();
                  runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          pd1.dismiss();
                      }
                  });
                 // Log.d("ress siri", resource.toString());

                  if (resource == null) {
                      return;
                  }
                  if (resource.getStatus().equals("ok")) {

                      noorder.setVisibility(View.GONE);
                      etSearch.setVisibility(View.VISIBLE);
                      recyclerView.setVisibility(View.VISIBLE);

                      seradapter = new ServiceListAdapter(getApplicationContext(), resource.getProfiles(), onclick);
                      recyclerView.setAdapter(seradapter);

                      datalist= resource.getProfiles();


                  } else {
                      noorder.setVisibility(View.VISIBLE);
                      etSearch.setVisibility(View.GONE);
                      recyclerView.setVisibility(View.GONE);

//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                  }

              }

              @Override
              public void onFailure(Call<ServiceProfileResponce> call, Throwable t) {
                  //   pd.dismiss();
                  pd1.dismiss();
                  t.printStackTrace();
                  Toast.makeText(Servicesdisplaylist.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();

              }
          });
      }

      @Override
      public void onItemClickProduct(int position, String catid,String name) {
          //getSupportFragmentManager().beginTransaction().replace(R.id.container,new Tut_Appoint_Fragment()).commit();
//          Intent ii = new Intent(Servicesdisplaylist.this,PostDetailedPage.class);
//          ii.putExtra("type",TUTAPPOINT);
//          startActivity(ii);
          Intent intent = new Intent(this, Tutions_Base.class);
          intent.putExtra("type","TUTAPPOINT");
          startActivity(intent);
          //Util.loadFragment(Tut_Appoint_Fragment.newInstance(catid,name), getApplicationContext(), Servicesdisplaylist.this);
      }


//      @Override
//        public void onItemClickProduct(int position, String productid) {
//
//            Util.loadFragment(new ProductDetailedPage().newInstance(seller_id,productid),getActivity());
//
//        }



}