package com.ellocartuser.servicesscreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.ServiceResponce;
import com.ellocartuser.apiservices.Responce.SubCatResponce;
import com.ellocartuser.home.adapterandmodel.SubCatStoreAdapter;
import com.ellocartuser.servicesscreens.category.ServiceCategoryPage;
import com.ellocartuser.tutions.Tutions_Base;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryItems extends AppCompatActivity implements SubCatServiceAdapter.OnItemClickedSubcat {

    RecyclerView subcatlist;
    ProgressDialog pd1;
    ImageView imageback;
    SubCatServiceAdapter.OnItemClickedSubcat onItemClickedSubcat;
    TextView current;
    Button joinus;
    String name,catid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_items);

        current=findViewById(R.id.current);
        joinus=findViewById(R.id.joinus);
        subcatlist=findViewById(R.id.subcatlist);
        onItemClickedSubcat=this;

        SharedPreferences pref = getApplicationContext().getSharedPreferences("SERVICES_CATIDNAME", Context.MODE_PRIVATE);
        catid = pref.getString("carid","");
        name = pref.getString("name","");


        current.setText(name);

        imageback = findViewById(R.id.imageback);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             onBackPressed();

//                Intent intent = new Intent(SubCategoryItems.this, Tutions_Base.class);
//                intent.putExtra("type","HOME_FRAGMENT");
//                startActivity(intent);
            }
        });

        joinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                apicallCheckUSersubscribe();

            }
        });

        subcatApi();

    }

    private void apicallCheckUSersubscribe() {
        pd1 = new ProgressDialog(SubCategoryItems.this);
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();
//           replacepd1();
//        pd1.show();
//        @Field("type") RequestBody type,

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<ServiceResponce> getCate = ApiClient.getApiServiceforservice().checkservice("checkservice",id);
        getCate.enqueue(new Callback<ServiceResponce>() {
            @Override
            public void onResponse(Call<ServiceResponce> call, Response<ServiceResponce> response) {
                final ServiceResponce resource = response.body();

                pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {
                    // check is alrady have posts or show to add postos , here api

                           apicallcheckpost();

                } else {

                    //send to subscription page

                    Intent subi = new Intent(SubCategoryItems.this, SubscritionScreen.class);
                    startActivity(subi);

//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }

                }

            }

            @Override
            public void onFailure(Call<ServiceResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(SubCategoryItems.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });
    }


    private void apicallcheckpost() {
        pd1 = new ProgressDialog(SubCategoryItems.this);
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();
        pd1.show();
//        pd1.show();
//        @Field("type") RequestBody type,

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<ServiceResponce> getCate = ApiClient.getApiServiceforservice().checkservice("my_posts",id);
        getCate.enqueue(new Callback<ServiceResponce>() {
            @Override
            public void onResponse(Call<ServiceResponce> call, Response<ServiceResponce> response) {
                final ServiceResponce resource = response.body();

                pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    // show my posts page

                    Intent subi = new Intent(SubCategoryItems.this, MyServicesPageActivity.class);
                    startActivity(subi);


                } else {

                    // show crate post category page

                    Intent ii =  new Intent(SubCategoryItems.this, ServiceCategoryPage.class);
                    startActivity(ii);

                }

            }

            @Override
            public void onFailure(Call<ServiceResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(SubCategoryItems.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void subcatApi() {
        pd1 = new ProgressDialog(SubCategoryItems.this);
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

//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<SubCatResponce> getCate = ApiClient.getApiServiceforservice().getServiceSubCat("subcat",catid,"");
        getCate.enqueue(new Callback<SubCatResponce>() {
            @Override
            public void onResponse(Call<SubCatResponce> call, Response<SubCatResponce> response) {
                final SubCatResponce resource = response.body();
               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd1.dismiss();
                    }
                });
                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {
//catstore
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(SubCategoryItems.this,2,GridLayoutManager.VERTICAL,false);
                    subcatlist.setLayoutManager(gridLayoutManager);
                    //  dataList.setNestedScrollingEnabled(false);
                    //  on=this;

                    SubCatServiceAdapter  subcatadapter =new SubCatServiceAdapter(SubCategoryItems.this,resource.getSubcategories(),onItemClickedSubcat);
                    subcatlist.setAdapter(subcatadapter);

                    //   subcatApi();
                } else {
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
                Toast.makeText(SubCategoryItems.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onItemClickSubCat(int position, String subcatid, String name) {
        Intent ii= new Intent(SubCategoryItems.this,Servicesdisplaylist.class);
        ii.putExtra("subcatid",subcatid);
        ii.putExtra("subcatname",name);
        startActivity(ii);

    }
}