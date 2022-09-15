package com.ellocartuser.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.Login;
import com.ellocartuser.OtpScreen;
import com.ellocartuser.R;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.orders.MapsFragment;
import com.ellocartuser.servicesscreens.PostDetailedPage;
import com.ellocartuser.utils.Util;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ThankyouScreen extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    Button button,call;
    ImageView img;
    TextView name,addressview,ordered,timedisplay;
    FrameLayout framelayout;
    String orderid,address,phonenumber,ordertime,orderdate;
    ConstraintLayout layoutonmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou_screen);
        Util.loadhome(ThankyouScreen.this);

        layoutonmap=findViewById(R.id.layoutonmap);
        timedisplay=findViewById(R.id.textView13);
        framelayout=findViewById(R.id.framelayout);
        ordered=findViewById(R.id.ordered);
        addressview=findViewById(R.id.address);
        button=findViewById(R.id.button);
        call=findViewById(R.id.call);
        img=findViewById(R.id.img);
        name=findViewById(R.id.name);
        String namestore= getIntent().getStringExtra("storename");
        orderid= getIntent().getStringExtra("orderid");
        phonenumber= getIntent().getStringExtra("phonenumer");
        address= getIntent().getStringExtra("address");
        ordertime= getIntent().getStringExtra("ordertime");
        orderdate= getIntent().getStringExtra("orderdate");

        timedisplay.setText(getIntent().getStringExtra("seller_time"));
        addressview.setText(address);

        if(orderdate!=null) {
            ordered.setText("Order date : " + orderdate + "," + ordertime);
        }else{
            layoutonmap.setVisibility(View.GONE);
        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("call num ",getIntent().getStringExtra("phonenumer"));
                callNumm(getIntent().getStringExtra("phonenumer"));

            }
        });
          SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, new MapsFragment().newInstance(orderid,id));
   //   transaction.replace(R.id.framelayout, new MapsFragment().newInstance("ELOCRT680"));
        transaction.addToBackStack(null);
        transaction.commit();



  if(name!=null){
      name.setText(namestore);
  }

        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra("imagename"))
                .fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).placeholder(R.drawable.placeholderello)
                .into(img);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent ii=new Intent(ThankyouScreen.this, HomeScreen.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ii);
            }
        });



    }

    @Override
    public void onBackPressed()
    {
        Intent ii=new Intent(ThankyouScreen.this, HomeScreen.class);
        ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ii);

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @AfterPermissionGranted(345)
    public void callNumm(String num){
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(ThankyouScreen.this, perms)) {

            if (ActivityCompat.checkSelfPermission(ThankyouScreen.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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
            EasyPermissions.requestPermissions(ThankyouScreen.this, "We need permission to make call ", 345, perms);
        }

    }



}