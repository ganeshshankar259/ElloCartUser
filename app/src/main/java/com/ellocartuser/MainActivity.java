package com.ellocartuser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieListener;
import com.airbnb.lottie.model.LottieCompositionCache;
import com.ellocartuser.setting.MyProfileActivity;
import com.google.android.gms.location.LocationListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.SplashResponce;
import com.ellocartuser.apiservices.Responce.StoresCatResponce;
import com.ellocartuser.home.adapterandmodel.StatementAdapter;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.intro.IntroActivity;
import com.ellocartuser.intro.IntroViewPagerAdapter;
import com.ellocartuser.intro.ScreenItem;
import com.ellocartuser.utils.ExampleNotificationOpenedHandler;
import com.ellocartuser.utils.Util;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.onesignal.OneSignal;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

  //  test-> rzp_test_0gEblY7zCT6p57
  //  live -> rzp_live_NyMHfEUfuw4oiL
    SharedPreferences pref;
    public static final String ONESIGNAL_APP_ID = "7f216e3e-6f04-44e8-8e51-0554837f462d";
    PackageInfo pInfo;
    LottieAnimationView anim;
    AppUpdateManager appUpdateManager;
    int RequestUpdate = 1;
    private Location myLocation;
    private GoogleApiClient googleApiClient;
    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2;
    public Double latitudeCurrent, longitudeCurrent;
    ImageView imageView;
    long millis=1000;


    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      LottieCompositionCache.getInstance().clear();
      //  anim=findViewById(R.id.animationView);
        imageView=findViewById(R.id.imageView);
        imageView.setVisibility(View.VISIBLE);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

//        if(date.equals("2021-11-03") || date.equals("2021-11-04")){
//            anim.setVisibility(View.VISIBLE);
//            imageView.setVisibility(View.GONE);
//            anim.playAnimation();
//            millis=5000;
//        }else{
//            millis=3000;
//            anim.setVisibility(View.GONE);
//            imageView.setVisibility(View.VISIBLE);
//        }

        if(!Util.isNetworkAvailable(MainActivity.this)){
            Toast.makeText(getApplicationContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
        }else {

        }

        pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);

        OneSignal.setAppId(ONESIGNAL_APP_ID);

        SharedPreferences.Editor editor = pref.edit();
        // editor.putString("boy",resource.getBoy());
        editor.putString("pendorder","display");
        editor.commit();

        setUpGClient();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

//        try {
//            pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        String version = pInfo.versionName;
//            int verCode = pInfo.versionCode;
//
//        new ForceUpdateAsync(version,MainActivity.this).execute();

    //    try {
//            pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
//            String version = pInfo.versionName;
//            int verCode = pInfo.versionCode;
//
//            pref=getApplicationContext()
//                    .getSharedPreferences("user", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = pref.edit();
//         //   editor.putString("user_id","346");
//            editor.putBoolean("loadhome",false);
//            editor.commit();

         //   apiCallCheckVersion(String.valueOf(verCode));


//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                    if (restoreuserPrefData()) {
//                        Intent dash = new Intent(MainActivity.this, HomeScreen.class);
//                        dash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(dash);
//                    } else {
//                        SharedPreferences.Editor editor = pref.edit();
//                        //   editor.putString("boy",resource.getBoy());
//                        editor.putString("typeservice","device");
//                        editor.commit();
//
//                        Intent login = new Intent(MainActivity.this, IntroActivity.class);
//                        login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(login);
//
//                    }
//
//                }
//            }, 1000);


            //  System.out.println("ver code = "+verCode);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }

  //  }

    private void apiCallCheckVersion(String verdata) {

        Call<StoresCatResponce> getCate = ApiClient.getApiService().getVersioncode(verdata,"android");
        getCate.enqueue(new Callback<StoresCatResponce>() {
            @Override
            public void onResponse(Call<StoresCatResponce> call, Response<StoresCatResponce> response) {
                final StoresCatResponce resource = response.body();
                //  pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if(resource.getStatus().equals("ok")){

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (restoreuserPrefData()) {
                                SharedPreferences.Editor editor = pref.edit();
                                //   editor.putString("boy",resource.getBoy());
                                editor.putString("pendorder","display");
                                editor.commit();
                                Intent dash = new Intent(MainActivity.this, HomeScreen.class);
                                dash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(dash);
                            } else {

                                SharedPreferences.Editor editor = pref.edit();
                                //   editor.putString("boy",resource.getBoy());
                                editor.putString("typeservice","device");
                                editor.putString("pendorder","display");
                                editor.commit();

                                Intent login = new Intent(MainActivity.this, IntroActivity.class);
                                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(login);

                            }

                        }
                    }, 1000);

                }else {

                    try {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("UPDATE AVILABLE");
                        builder.setMessage("New Update avilable Please Update");
                        builder.setCancelable(true);

                        builder.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName()));
                                        startActivity(intent);
                                    }
                                });
                        builder.setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        onBackPressed();

                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();

                    } catch (RuntimeException ex) {
                        ex.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Call<StoresCatResponce> call, Throwable t) {
                //   pd.dismiss();
                //  pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });





    }



    private boolean restoreuserPrefData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        String d = pref.getString("user_id", "");
        String dd = pref.getString("user_name", "");
        if (d.length() != 0 || dd.length() != 0) {
            return true;
        } else {
            return false;
        }

    }

    public class ForceUpdateAsync extends AsyncTask<String, String, JSONObject> {

        private String latestVersion;
        private String currentVersion;
        private Context context;
        public ForceUpdateAsync(String currentVersion, Context context){
            this.currentVersion = currentVersion;
            this.context = context;
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            try {
                latestVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + context.getPackageName()+ "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                        .first()
                        .ownText();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return new JSONObject();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            if(latestVersion!=null){
                if(!currentVersion.equalsIgnoreCase(latestVersion)){
                    // Toast.makeText(context,"update is available.",Toast.LENGTH_LONG).show();
                        if(!((Activity)context).isFinishing()){
                            showForceUpdateDialog();
                        }else{

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    if (restoreuserPrefData()) {
                                        Intent dash = new Intent(MainActivity.this, HomeScreen.class);
                                        dash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(dash);
                                    } else {
                                        SharedPreferences.Editor editor = pref.edit();
                                        //   editor.putString("boy",resource.getBoy());
                                        editor.putString("typeservice","device");
                                        editor.commit();

                                        Intent login = new Intent(MainActivity.this, IntroActivity.class);
                                        login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(login);

                                    }

                                }
                            }, 1000);
                        }

                }else{
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (restoreuserPrefData()) {
                                Intent dash = new Intent(MainActivity.this, HomeScreen.class);
                                dash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(dash);
                            } else {
                                SharedPreferences.Editor editor = pref.edit();
                                //   editor.putString("boy",resource.getBoy());
                                editor.putString("typeservice","device");
                                editor.commit();

                                Intent login = new Intent(MainActivity.this, IntroActivity.class);
                                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(login);

                            }

                        }
                    }, 1000);
                }
            }
            super.onPostExecute(jsonObject);
        }

        public void showForceUpdateDialog(){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context,
                    R.style.Theme_AppCompat_Light_Dialog));

            alertDialogBuilder.setTitle(context.getString(R.string.youAreNotUpdatedTitle));
            alertDialogBuilder.setMessage(context.getString(R.string.youAreNotUpdatedMessage) + " " + latestVersion + context.getString(R.string.youAreNotUpdatedMessage1));
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
                    dialog.cancel();
                }
            });

            alertDialogBuilder.setNegativeButton("Update Later", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.cancel();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (restoreuserPrefData()) {
                                Intent dash = new Intent(MainActivity.this, HomeScreen.class);
                                dash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(dash);
                            } else {
                                SharedPreferences.Editor editor = pref.edit();
                                //   editor.putString("boy",resource.getBoy());
                                editor.putString("typeservice","device");
                                editor.commit();

                                Intent login = new Intent(MainActivity.this, IntroActivity.class);
                                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(login);

                            }

                        }
                    }, 1000);
                }

            });
            alertDialogBuilder.show();
        }
    }


    private synchronized void setUpGClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        myLocation = location;
        if (myLocation != null) {
            latitudeCurrent = myLocation.getLatitude();
            longitudeCurrent = myLocation.getLongitude();
            Log.i("SplashActivity main", " Latitude:- " + latitudeCurrent.toString());
            Log.i("SplashActivity main", "Longitude:- " + longitudeCurrent.toString());

        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        checkPermissions();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("SplashActivity", "connectionSuspend");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("SplashActivity", connectionResult.toString());
    }

    private void getMyLocation() {
        if (googleApiClient != null) {
            if (googleApiClient.isConnected()) {
                int permissionLocation = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                    myLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    LocationRequest locationRequest = new LocationRequest();
                    locationRequest.setInterval(1000);
                    locationRequest.setFastestInterval(1000);
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                            .addLocationRequest(locationRequest);
                    builder.setAlwaysShow(true);
                    LocationServices.FusedLocationApi
                            .requestLocationUpdates(googleApiClient, locationRequest, this);
                    PendingResult<LocationSettingsResult> result =
                            LocationServices.SettingsApi
                                    .checkLocationSettings(googleApiClient, builder.build());
                    result.setResultCallback(result1 -> {
                        final Status status = result1.getStatus();
                        switch (status.getStatusCode()) {
                            case LocationSettingsStatusCodes.SUCCESS:
                                // All location settings are satisfied.
                                // You can initialize location requests here.
                                int permissionLocation1 = ContextCompat
                                        .checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION);
                                if (permissionLocation1 == PackageManager.PERMISSION_GRANTED) {
                                    myLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                                    nextScreen();
                                }
                                break;
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                // Location settings are not satisfied.
                                // But could be fixed by showing the user a dialog.
                                try {
                                    // Show the dialog by calling startResolutionForResult(),
                                    // and check the result in onActivityResult().
                                    // Ask to turn on GPS automatically
                                    status.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS_GPS);
                                } catch (IntentSender.SendIntentException e) {
                                    // Ignore the error.
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                // Location settings are not satisfied.
                                // However, we have no way
                                // to fix the
                                // settings so we won't show the dialog.
                                // finish();
                                break;
                        }
                    });
                }
            }
        }
    }

    private void nextScreen() {


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (restoreuserPrefData()) {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
                    String pro = pref.getString("user_profile", "");
                    if(pro.equals("0")) {
                        Intent ii = new Intent(MainActivity.this, MyProfileActivity.class);
                        ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        ii.putExtra("from","edit");

                        //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(ii);
                    }else{
                        Intent dash = new Intent(MainActivity.this, HomeScreen.class);
                        dash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(dash);
                    }

                } else {

                    SharedPreferences.Editor editor = pref.edit();
                    //   editor.putString("boy",resource.getBoy());
                    editor.putString("typeservice","device");
                    editor.commit();

                    Intent login = new Intent(MainActivity.this, IntroActivity.class);
                    login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(login);

                }
            }
        },millis);

//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                if (restoreuserPrefData()) {
//
//                    Intent dash = new Intent(MainActivity.this, HomeScreen.class);
//                    dash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(dash);
//
//                } else {
//
//                    SharedPreferences.Editor editor = pref.edit();
//                    //   editor.putString("boy",resource.getBoy());
//                    editor.putString("typeservice","device");
//                    editor.commit();
//
//                    Intent login = new Intent(MainActivity.this, IntroActivity.class);
//                    login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(login);
//
//                }
//
//            }
//        }, 1000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS_GPS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        getMyLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        finish();
                        break;
                }
                break;
        }
    }

    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        } else {
            getMyLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        int permissionLocation = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            getMyLocation();
        } else {
            checkPermissions();
        }
    }



}
