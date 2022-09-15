package com.ellocartuser.servicesscreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.ServicePostDetailedResponce;
import com.ellocartuser.apiservices.Responce.ServiceProfileResponce;
import com.ellocartuser.home.adapterandmodel.ProductDetailedAdapter;
import com.ellocartuser.servicesscreens.adapters.ServicesAdapter;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailedPage extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    String subcatid="";
    ImageView img;
    TextView title,description,name,address,expirence,phonenumber;
    ProgressDialog pd1;
    RecyclerView recyclerView,recyclerViewother;
    ServicesAdapter seradapter;
    ImageView callbtn,imgback;
    String num=null,from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detailed_page);

        subcatid=getIntent().getStringExtra("subcat");

        from=getIntent().getStringExtra("from");

        phonenumber = findViewById(R.id.phonenumber);
        recyclerView = findViewById(R.id.recyclerView2);
        expirence = findViewById(R.id.expirence);
        recyclerViewother = findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostDetailedPage.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewother.setLayoutManager(new LinearLayoutManager(PostDetailedPage.this, LinearLayoutManager.HORIZONTAL, false));
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        img = findViewById(R.id.gridIconne);
        callbtn = findViewById(R.id.callbtn);
        title = findViewById(R.id.titlene);
        imgback = findViewById(R.id.imageback);
        description = findViewById(R.id.description);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if(from.equals("myservice")){
            callbtn.setVisibility(View.GONE);
        }


        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNumm(num);
            }
        });


        apiDetaildPage();

    }

    @AfterPermissionGranted(345)
    public void callNumm(String num){
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(PostDetailedPage.this, perms)) {

            if (ActivityCompat.checkSelfPermission(PostDetailedPage.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            if (num != null) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
                startActivity(intent);
            }
        } else {
            EasyPermissions.requestPermissions(PostDetailedPage.this, "We need permission to make call ", 345, perms);
        }

    }

    private void apiDetaildPage() {

        pd1 = new ProgressDialog(PostDetailedPage.this);
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

//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<ServicePostDetailedResponce> getCate = ApiClient.getApiServiceforservice().getPostDetails("get_post_detail",subcatid);
        getCate.enqueue(new Callback<ServicePostDetailedResponce>() {
            @Override
            public void onResponse(Call<ServicePostDetailedResponce> call, Response<ServicePostDetailedResponce> response) {
                final ServicePostDetailedResponce resource = response.body();

                        pd1.dismiss();

                // Log.d("ress siri", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    title.setText(resource.getPostTitle());
                    name.setText(resource.getUserName());
                    address.setText(resource.getPostAddress());
                    description.setText(resource.getPostDescription());
                    Glide.with(getApplicationContext())
                            .load(resource.getPostImg1())
                            .fitCenter().placeholder(R.drawable.placeholderello)
                            .into(img);
                    num=resource.getUserPhone();
                    seradapter = new ServicesAdapter(PostDetailedPage.this,resource.getServsubcategories(),resource.getOtherServices(),"yellow");
                    recyclerView.setAdapter(seradapter);

                    seradapter = new ServicesAdapter(PostDetailedPage.this,resource.getServsubcategories(),resource.getOtherServices(),"");
                    recyclerViewother.setAdapter(seradapter);
                    expirence.setText("Experience : "+resource.getPost_expr() + " Year");

                } else {
//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<ServicePostDetailedResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(PostDetailedPage.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}