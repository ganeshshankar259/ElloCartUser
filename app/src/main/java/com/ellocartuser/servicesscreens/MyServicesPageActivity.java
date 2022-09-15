package com.ellocartuser.servicesscreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.ServiceResponce;
import com.ellocartuser.servicesscreens.category.ServiceCategoryPage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyServicesPageActivity extends AppCompatActivity implements MyServicesAdapter.OnItemClickedMyServ {

    private String mParam1;
    private String mParam2;
    RecyclerView catList;
    ImageView imageback;
    ProgressDialog pd1;
    LinearLayout noorder;
    MyServicesAdapter.OnItemClickedMyServ onItemClicked;
    TextView addserv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_services_page);

        noorder=findViewById(R.id.noorder);
        catList=findViewById(R.id.catList);
        imageback=findViewById(R.id.imageback);
        catList.setLayoutManager(new LinearLayoutManager(MyServicesPageActivity.this, LinearLayoutManager.VERTICAL, false));
        onItemClicked=this;
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        addserv=findViewById(R.id.addserv);
        addserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii =  new Intent(MyServicesPageActivity.this, ServiceCategoryPage.class);
                startActivity(ii);
            }
        });

        apicall();



    }

    private void apicall() {

        pd1 = new ProgressDialog(MyServicesPageActivity.this);
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
                    addserv.setVisibility(View.VISIBLE);
                    addserv.setText("Add New Service");

                    MyServicesAdapter adapter = new MyServicesAdapter(MyServicesPageActivity.this, resource.getPosts(), onItemClicked);
                    catList.setAdapter(adapter);

                    catList.setVisibility(View.VISIBLE);
                    noorder.setVisibility(View.GONE);

                } else {
                    addserv.setText("Add Service");
                  //  addserv.setVisibility(View.VISIBLE);

                    noorder.setVisibility(View.VISIBLE);
                    catList.setVisibility(View.GONE);
                    //share to subscription page
                    addserv.setVisibility(View.INVISIBLE);



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
                Toast.makeText(MyServicesPageActivity.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public void onItemClickProduct(int position, String catid) {

        Intent ii = new Intent(MyServicesPageActivity.this,PostDetailedPage.class);
        ii.putExtra("subcat",catid);
        ii.putExtra("from","myservice");
        startActivity(ii);

    }

}