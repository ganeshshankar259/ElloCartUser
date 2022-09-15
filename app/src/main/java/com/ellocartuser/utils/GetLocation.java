package com.ellocartuser.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class GetLocation extends Service {


   // public static final int notify = 300000;  //interval between two services(Here Service run every 5 Minute)
    public static final int notify = 20000;  //interval between two services(Here Service run every 20 sec)
   //public static final int notify = 3000;  //interval between two services(Here Service run every 5 Minute)
    private Handler mHandler = new Handler();   //run on another Thread to avoid crash
    private Timer mTimer = null;    //timer handling
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    public void onCreate() {
        //Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
        if (mTimer != null) // Cancel if already existed
            mTimer.cancel();
        else
            mTimer = new Timer();   //recreate new

        mTimer.scheduleAtFixedRate(new TimeDisplay(), 0, notify);   //Schedule task

    }
    @Override
    public void onStart(Intent intent, int startid) {
      //  Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

    }
    @Override
    public void onDestroy() {

     //   Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();

    }

    public static void locationStatus(Context context) {
        String latitude,longitude;
        GPSTracker gps = new GPSTracker(context);
        if (gps.canGetLocation()) {
            latitude = String.valueOf(gps.getLatitude());
             longitude = String.valueOf(gps.getLongitude());



            if (!latitude.equals("0.0") && !longitude.equals("0.0")) {


                SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
                String id = pref.getString("boy_id","");
                sendData(latitude,longitude,id);

            } else {


                             locationStatus(context);
            }
        }else{
            //  Toast.makeText(this, "Need to Display popup ", Toast.LENGTH_LONG).show();

        }
        }


    private static void sendData(String lat,String log,String userid)  {
System.out.println("sriram lati"+lat);
System.out.println("sriram longi"+log);
        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();


        RequestBody formBody = new FormBody.Builder()
                .add("type", "boy")
                .add("boy_id", userid)
                .add("boy_device", "Android")
                .add("boy_lat", lat)
                .add("boy_long", log)
                .add("boy_token", "log")

                .build();
        Request request = new Request.Builder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .url(Constants.BASE_URL+"update_device")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                //login.setVisibility(View.GONE);
                Log.d("result dadi", e.getMessage().toString());
                e.printStackTrace();
                //pd.dismiss();
            }

            @Override
            public void onResponse(okhttp3.Call call, final okhttp3.Response response) throws IOException {
                // pd.dismiss();
                if (!response.isSuccessful()) {




                    throw new IOException("Unexpected code " + response);
                } else {
                    //  pd.dismiss();
                    Log.d("result", response.toString());
                    String responseBody = response.body().string();
               //     final JSONObject obj;

                }
            }
        });



    }

    //class TimeDisplay for handling task
    class TimeDisplay extends TimerTask {
        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    locationStatus(getBaseContext());
                    // display toast
                   // Toast.makeText(MyService.this, "Service is running", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



}
