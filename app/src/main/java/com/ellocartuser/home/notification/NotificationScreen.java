package com.ellocartuser.home.notification;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.B2BResponce;
import com.ellocartuser.home.mainscreen.FirstHome;
import com.ellocartuser.home.mainscreen.HomeScreen;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationScreen extends AppCompatActivity implements  NotificationAdapter.OnItemClickedNoti{
    NotificationAdapter.OnItemClickedNoti onclick;
    RecyclerView dataList;
    ProgressDialog pd1;
    ImageView imageback;
    LinearLayout noorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.fragment_container1,
                        //      new MapsFragment().newInstance("ELOCRT680")
                        new NotificationFragment()
                        // new MapsFragment()
                )
                .commit();

//        onclick=this;
//
//        imageback =findViewById(R.id.imageback);
//        noorder =findViewById(R.id.noorder);
//        dataList = findViewById(R.id.catList);
//        dataList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//
//        imageback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//
//        apicall();

    }
    private void apicall() {

        pd1 = new ProgressDialog(NotificationScreen.this);
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        Call<B2BResponce> getCate = ApiClient.getApiService().getNotifications("user",id);
        getCate.enqueue(new Callback<B2BResponce>() {
            @Override
            public void onResponse(Call<B2BResponce> call, Response<B2BResponce> response) {
                final B2BResponce resource = response.body();

                pd1.dismiss();
                Log.d("responce notification", resource.toString());
                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    noorder.setVisibility(View.GONE);
                    dataList.setVisibility(View.VISIBLE);
                    NotificationAdapter adapter = new NotificationAdapter(NotificationScreen.this,resource.getNotifys(),onclick);
                    dataList.setAdapter(adapter);
//
                } else {
                    noorder.setVisibility(View.VISIBLE);
                    dataList.setVisibility(View.GONE);
                }

            }
            @Override
            public void onFailure(Call<B2BResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //     Toast.makeText(getContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onItemClickedNoti(int position, String np1, String np2, String np3) {

    }
}