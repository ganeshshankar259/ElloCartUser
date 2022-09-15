package com.ellocartuser.home.mainscreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.Login;
import com.ellocartuser.MainActivity;
import com.ellocartuser.R;

import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.apiservices.Responce.StoresCatResponce;
import com.ellocartuser.cart.CartDisplay;
import com.ellocartuser.ellorooms_new.ElloRoomsHome;
import com.ellocartuser.home.MyWallet;
import com.ellocartuser.home.adapterandmodel.HomeSliderAdapterr;
import com.ellocartuser.home.adapterandmodel.StatementAdapter;
import com.ellocartuser.home.fragments.ElloAllindiaEmpty;
import com.ellocartuser.home.fragments.ElloRoomEmpty;
import com.ellocartuser.home.fragments.ServicesFragment;
import com.ellocartuser.home.homefragment.NearbystoreFragment;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.home.homefragment.viewall.AllIndiaCatgoryViewAll;
import com.ellocartuser.orders.MapsFragment;
import com.ellocartuser.orders.OrdersMainClass;
import com.ellocartuser.orders.OrdersPage;
import com.ellocartuser.servicesscreens.MyServicesPage;
import com.ellocartuser.setting.Account;
import com.ellocartuser.setting.MyProfile;
import com.ellocartuser.setting.MyProfileActivity;
import com.ellocartuser.setting.webfragment.WebLinkFragment;
import com.ellocartuser.utils.ExampleNotificationOpenedHandler;
import com.ellocartuser.utils.GPSTracker;
import com.ellocartuser.utils.Util;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.onesignal.OneSignal;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,NavigationView.OnNavigationItemSelectedListener , com.google.android.gms.location.LocationListener {
    private BroadcastReceiver mNetworkReceiver;
    public static TextView tv_check_connection,mywalletamt;
    GoogleApiClient googleApiClient;
    boolean doubleBackToExitPressedOnce=false;
 BottomNavigationView bottomNav;
 public  static TextView    cartcount;
    protected static final int REQUEST_CHECK_SETTINGS = 1000;
    protected static final int UPDATE_REQUEST_CODE = 10;
    ImageView userimg;
    DrawerLayout drawer;
    TextView profilename,phnnumprofile;
    ProgressDialog pd1;
    //LinearLayout mywallet,myprofile,elloagent,promotional,joinasseller,aboutus,contactus,termsofuse,privacy,faq,shareapp,logout,alloverindia,login;
 //   LinearLayout myorders;
//  NavigationView navigationView;
    AppUpdateManager appUpdateManager;
    private Location myLocation;

    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2;

    int RequestUpdate = 1;
    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    //    private AppUpdateManager appUpdateManager;
//    private static final int MY_REQUEST_CODE = 17326;
//
//    private static final int REQ_CODE_VERSION_UPDATE = 530;
//
//    private InstallStateUpdatedListener installStateUpdatedListener;
private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        cartcount=findViewById(R.id.cartcount);
        bottomNav=findViewById(R.id.dd);
        noDataopenLocationPopup();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.fragment_container1,
                        //      new MapsFragment().newInstance("ELOCRT680")
                        //      new FirstHome()
                        // new NewHomeScreen()
                        new homefragment()
                )
                .commit();

     // OneSignal.setNotificationOpenedHandler(new ExampleNotificationOpenedHandler(HomeScreen.this));
        OneSignal.setNotificationOpenedHandler(new ExampleNotificationOpenedHandler(this));
      //  OneSignal.init();

   //   appUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());
      //  phnnumprofile = findViewById(R.id.phnnumprofile);
//        alloverindia = findViewById(R.id.alloverindia);
//        myorders = findViewById(R.id.myorders);
//        mywallet = findViewById(R.id.mywallet);
//        myprofile = findViewById(R.id.myprofile);
//     // elloagent = findViewById(R.id.elloagent);
//        promotional = findViewById(R.id.promotional);
//        joinasseller = findViewById(R.id.joinasseller);
//        aboutus = findViewById(R.id.aboutus);
//        contactus = findViewById(R.id.contactus);
//        termsofuse = findViewById(R.id.termsofuse);
//        privacy = findViewById(R.id.privacy);
//        faq = findViewById(R.id.faq);
//        shareapp = findViewById(R.id.shareapp);
//        logout = findViewById(R.id.logout);
//        login = findViewById(R.id.login);
        userimg = findViewById(R.id.imageView1);
       // profilename = findViewById(R.id.profilename);
      //  mywalletamt = findViewById(R.id.mywalletamt);

        drawer = findViewById(R.id.drawer_layout);

//       navigationView = findViewById(R.id.nav_view);
//
//       navigationView.setNavigationItemSelectedListener(this);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");


//        if(id.equals("") || id==null) {
//            login.setVisibility(View.VISIBLE);
//            logout.setVisibility(View.GONE);
//
//        }else {
//            logout.setVisibility(View.VISIBLE);
//            login.setVisibility(View.GONE);
//
//        }

     //   checkPermissionLocation();

          setUpGClient();

//        myprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                closeDrawer();
//                if(id.equals("") || id==null) {
//                    Util.PleaseLogin(HomeScreen.this);
//                    return;
//                }
//              //  pushFragment(new MyProfile());
//                Intent ii = new Intent(HomeScreen.this, MyProfileActivity.class);
//                ii.putExtra("from","");
//                startActivity(ii);
//
//            }
//        });
//
//        myorders.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              //  Fragment selectedFragment =  new OrdersMainClass();
//                closeDrawer();
//                if(id.equals("") || id==null) {
//                    Util.PleaseLogin(HomeScreen.this);
//                    return;
//                }
//                pushFragment(OrdersMainClass.newInstance("",""));
//            }
//        });
//
//
//        mywallet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //  Fragment selectedFragment =  new OrdersMainClass();
//                closeDrawer();
//                if(id.equals("") || id==null) {
//                    Util.PleaseLogin(HomeScreen.this);
//                    return;
//                }
//                pushFragment(MyWallet.newInstance("",""));
//            }
//        });
//
//        alloverindia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                pushFragment(new AllIndiaCatgoryViewAll());
//
//            }
//        });
//
//        promotional.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        joinasseller.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        aboutus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                WebLinkFragment webLinkFragment = WebLinkFragment.newInstance("https://www.ellocart.com/about","About us");
//                pushFragment(webLinkFragment);
//            }
//        });
//        contactus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                WebLinkFragment webLinkFragment1 = WebLinkFragment.newInstance("https://www.ellocart.com/contact","Contact us");
//                pushFragment(webLinkFragment1);
//            }
//        });
//        termsofuse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                WebLinkFragment webLinkFragment2 = WebLinkFragment.newInstance("https://www.ellocart.com/terms","Terms and Conditions");
//                pushFragment(webLinkFragment2);
//
//            }
//        });
//        privacy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                WebLinkFragment webLinkFragment3 = WebLinkFragment.newInstance("https://www.ellocart.com/privacy","Privacy");
//                pushFragment(webLinkFragment3);
//            }
//        });
//        faq.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                WebLinkFragment webLinkFragmentf = WebLinkFragment.newInstance("https://www.ellocart.com/faqs","FAQ");
//                pushFragment(webLinkFragmentf);
//            }
//        });
//        shareapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                closeDrawer();
//                try {
//                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                    shareIntent.setType("text/plain");
//                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "ElloCart Driver");
//                    String shareMessage= "\nEllocart:Rediscovering Local shopping experience\n\n";
//                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.ellocart.sunraise\n\n";
//                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
//                    startActivity(Intent.createChooser(shareIntent, "choose one"));
//                } catch(Exception e) {
//                    //e.toString();
//                }
//            }
//        });
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                closeDrawer();
//                showDialog();
//            }
//        });
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent ii=new Intent(HomeScreen.this, Login.class);
//                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(ii);
//
//            }
//        });

        appUpdateManager = AppUpdateManagerFactory.create(this);
        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {
                if((result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE)
                        && result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE))
                {
                    try {
                        appUpdateManager.startUpdateFlowForResult(
                                result,
                                AppUpdateType.IMMEDIATE,
                                HomeScreen.this,
                                RequestUpdate);
                    }
                    catch (IntentSender.SendIntentException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });


        bottomNav.setOnNavigationItemSelectedListener(new
     BottomNavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item) {
             Fragment selectedFragment = null;

             switch (item.getItemId()){
                 case R.id.home:
                     getSupportFragmentManager()
                             .beginTransaction()
                             .replace(
                                     R.id.fragment_container1,
                                     //      new MapsFragment().newInstance("ELOCRT680")
                                     //      new FirstHome()
                                     // new NewHomeScreen()
                                     new homefragment()
                             )
                             .commit();
                     return true;

                 case R.id.order:
                  //room
                     getSupportFragmentManager()
                             .beginTransaction()
                             .replace(
                                     R.id.fragment_container1,
                                     //      new MapsFragment().newInstance("ELOCRT680")
                                     //      new FirstHome()
                                     // new NewHomeScreen()
                                     new ElloRoomsHome()
                             )
                             .commit();
                     return true;

                 case R.id.action_add:
                     //cart
                     getSupportFragmentManager()
                             .beginTransaction()
                             .replace(
                                     R.id.fragment_container1,
                                     //      new MapsFragment().newInstance("ELOCRT680")
                                     //      new FirstHome()
                                     // new NewHomeScreen()
                                     new CartDisplay()
                             )
                             .commit();
                     return true;
                 case R.id.service:
                     //service
                     getSupportFragmentManager()
                             .beginTransaction()
                             .replace(
                                     R.id.fragment_container1,
                                     //      new MapsFragment().newInstance("ELOCRT680")
                                     //      new FirstHome()
                                     // new NewHomeScreen()
                                     new ServicesFragment()
                             )
                             .commit();
                     return true;
                 case R.id.b2b:
                     //all india
                     getSupportFragmentManager()
                             .beginTransaction()
                             .replace(
                                     R.id.fragment_container1,
                                     //      new MapsFragment().newInstance("ELOCRT680")
                                     //      new FirstHome()
                                     // new NewHomeScreen()
                                     new ElloAllindiaEmpty()
                             )
                             .commit();
                     return true;
             }

             return true;
         }
     });

    }

    @Override
    protected void onStart() {
        try {
            super.onStart();
        } catch (Exception ex){

        }
    }

    public void  pushFragment(Fragment selectedFragment){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                closeDrawer();
            }
        });

       final FragmentTransaction transaction = getSupportFragmentManager()
               .beginTransaction();

       // put the fragment in place
       transaction.replace(R.id.fragment_container1, selectedFragment);

       // this is the part that will cause a fragment to be added to backstack,
       // this way we can return to it at any time using this tag
   //    transaction.addToBackStack(selectedFragment.getClass().getName());   //for back stack
       transaction.addToBackStack(new homefragment().getClass().getName());
       transaction.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
      //  apiCallProfile();
        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {
                if(result.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS)
                {
                    try {
                        appUpdateManager.startUpdateFlowForResult(
                                result,
                                AppUpdateType.IMMEDIATE,
                                HomeScreen.this,
                                RequestUpdate);
                    }
                    catch (IntentSender.SendIntentException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    private void closeDrawer() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
            }
        });

    }

    public void checkPermissionLocation() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            //    try {
                   // openGps();
                getMyLocation();
                 //   setUpGClient();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            } else {
              //  settingsrequest();

            }
        } else {

            EasyPermissions.requestPermissions(this, "We need permissions to get location",
                    123, perms);
        }

    }



    //

    private synchronized void setUpGClient() {

        if(googleApiClient==null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, 0, this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        myLocation = location;
        if (myLocation != null) {

            String   latitudeCurrent =String.valueOf( myLocation.getLatitude());
            String  longitudeCurrent =String.valueOf( myLocation.getLongitude());

            SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("latitude", latitudeCurrent);
            editor.putString("longitude", longitudeCurrent);
            editor.putString("type", "device");
            editor.commit();


            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.fragment_container1,
                            //      new MapsFragment().newInstance("ELOCRT680")
                            //      new FirstHome()
                            // new NewHomeScreen()
                            new homefragment()
                    )
                    .commit();

            googleApiClient.disconnect();
//            Log.i("SplashActivity", " Latitude:- " + latitudeCurrent.toString());
//            Log.i("SplashActivity", "Longitude:- " + longitudeCurrent.toString());
//

        }
    }

    @Override
    public void onConnected(Bundle bundle) {

         //   checkPermissions();

    }

    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(HomeScreen.this, android.Manifest.permission.ACCESS_FINE_LOCATION);
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
        int permissionLocation = ContextCompat.checkSelfPermission(HomeScreen.this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            getMyLocation();
        } else {
            checkPermissions();
        }
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
                int permissionLocation = ContextCompat.checkSelfPermission(HomeScreen.this, android.Manifest.permission.ACCESS_FINE_LOCATION);
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
                                        .checkSelfPermission(HomeScreen.this, android.Manifest.permission.ACCESS_FINE_LOCATION);
                                if (permissionLocation1 == PackageManager.PERMISSION_GRANTED) {
                                    myLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                                    noDataopenLocationPopup();
                                }
                                break;
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                // Location settings are not satisfied.
                                // But could be fixed by showing the user a dialog.
                                try {
                                    // Show the dialog by calling startResolutionForResult(),
                                    // and check the result in onActivityResult().
                                    // Ask to turn on GPS automatically
                                    status.startResolutionForResult(HomeScreen.this, REQUEST_CHECK_SETTINGS_GPS);
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


    }


    public void openLocationPopup() {
        checkPermissionLocation();
    }

   public void noDataopenLocationPopup(){

       GPSTracker gps = new GPSTracker(getApplicationContext());

       if (gps.canGetLocation()) {

           SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
           SharedPreferences.Editor editor = pref.edit();
           editor.putString("latitude", String.valueOf(gps.getLatitude()));
           editor.putString("longitude", String.valueOf(gps.getLongitude()));
           editor.putString("type", "device");
           editor.commit();

           getSupportFragmentManager()
                   .beginTransaction()
                   .replace(
                           R.id.fragment_container1,
                           //      new MapsFragment().newInstance("ELOCRT680")
                           //      new FirstHome()
                           // new NewHomeScreen()
                           new homefragment()
                           )
                   .commit();
           //  startService(new Intent(this, GetLocation.class));
       } else {
setUpGClient();
       }

    }

    public void openPopup() {
        GPSTracker gps = new GPSTracker(getApplicationContext());

        if (gps.canGetLocation()) {
            //  startService(new Intent(this, GetLocation.class));
        } else {
//           try {
//               openGps();
//           } catch (IOException e) {
//               e.printStackTrace();
//           }
        }

    }

    public void settingsrequest() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(HomeScreen.this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build();
            googleApiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            //**************************
            builder.setAlwaysShow(true); //this is the key ingredient
            //**************************

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    final LocationSettingsStates state = result.getLocationSettingsStates();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            // All location settings are satisfied. The client can initialize location
                            // requests here.

                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the user
                            // a dialog.
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(HomeScreen.this, 1000);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            });
        }
    }
//

    @AfterPermissionGranted(123)
    private void openGps() throws IOException {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {

            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                GPSTracker gps = new GPSTracker(HomeScreen.this);
                if (gps.canGetLocation()) {

                    String latitude = String.valueOf(gps.getLatitude());
                    String longitude = String.valueOf(gps.getLongitude());
                    openPopup();
                    if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
                        Log.d("lati", String.valueOf(latitude));
                        Log.d("lati long", String.valueOf(longitude));

                    } else {
                        setUpGClient();
                      // startService();
                        //  Toast.makeText(HomeDashboard.this, "please wait 5 to 10 seconds we are getting location status", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(HomeScreen.this, "please Turn On your GPS", Toast.LENGTH_SHORT).show();
                }
//                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                try {
//                    double longitude = location.getLongitude();
//                    double latitude = location.getLatitude();
//

//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }
            } else {
                settingsrequest();
            }
        } else {
            EasyPermissions.requestPermissions(this, "We need permissions to get location",
                    123, perms);
        }
    }

//    public void setCartCheck() {
//        bottomNav.setSelectedItemId(R.id.cart);
//    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        //   Toast.makeText(HomeScreen.this, "please Turn On your GPS", Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("sriram req" + requestCode);


        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS_GPS:
                switch (resultCode) {
                    case Activity.RESULT_OK:

//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(
//                                        R.id.fragment_container1,
//                                        new FirstHome()
//                                        //   new MapsFragment()
//                                )
//                                .commit();

                        getMyLocation();
                        break;
                    case Activity.RESULT_CANCELED:
//                        finish();
getMyLocation();

                        break;
                }
                break;
        }
        switch (requestCode) {


// Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        System.out.println("sriram req yes");
            //            startService();
//                        ProgressDialog      pd1 = new ProgressDialog(HomeScreen.this);
//                        pd1.setMessage("geting Location...");
//                        //   pd.setProgressStyle(R.style.ProgressBar);
//                        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                         pd1.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//                        pd1.setCancelable(false);
//                        pd1.show();
//
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        pd1.dismiss();

                        openPopup();
                        break;
                    case Activity.RESULT_CANCELED:
                        System.out.println("sriram req no");
                        googleApiClient = null;

                        setUpGClient();
                      //  checkPermissionLocation();//keep asking if imp or do whatever
                        break;
                }
                break;

        }
    }

//
//    public boolean startService() {
//        try {
//            // this.locatorService= new
//            // Intent(FastHomeScreen.this,LocatorService.class);
//            // startService(this.locatorService);
//
//            FetchCordinates fetchCordinates = new FetchCordinates();
//            fetchCordinates.execute();
//            return true;
//        } catch (Exception error) {
//            return false;
//        }
//
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);

//        switch (menuItem.getItemId()) {
//            case R.id.nav_home:
//                // do you click actions for the first selection
//                break;
//            case R.id.nav_gallery:
//                // do you click actions for the second selection
//                break;
//
//        }

        return true;
    }
    public  void  toggleDrawer()
    {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    } else {
                        drawer.openDrawer(GravityCompat.START);
                    }
                }
            });


    }
//
//    public class FetchCordinates extends AsyncTask<String, Integer, String> {
//        ProgressDialog progDailog = null;
//
//        public double lati = 0.0;
//        public double longi = 0.0;
//
//        public LocationManager mLocationManager;
//        public VeggsterLocationListener mVeggsterLocationListener;
//
//        @Override
//        protected void onPreExecute() {
//            mVeggsterLocationListener = new VeggsterLocationListener();
//            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//            if (ActivityCompat.checkSelfPermission(HomeScreen.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(HomeScreen.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            mLocationManager.requestLocationUpdates(
//                    LocationManager.NETWORK_PROVIDER, 0, 0,
//                    (LocationListener) mVeggsterLocationListener);
//
//            progDailog = new ProgressDialog(HomeScreen.this);
////            progDailog.setOnCancelListener(new OnCancelListener() {
////                @Override
////                public void onCancel(DialogInterface dialog) {
////                    FetchCordinates.this.cancel(true);
////                }
////            });
//            progDailog.setMessage("Getting Location Data ...");
//            progDailog.setIndeterminate(true);
//            progDailog.setCancelable(false);
//            progDailog.show();
//
//
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                    try {
//                        progDailog.dismiss();
//                    }catch (Exception ex){
//                        ex.printStackTrace();
//                    }
//
//                }
//            }, 5000);
//
//        }
//
//
//        @Override
//        protected void onPostExecute(String result) {
//            if(progDailog!=null) {
//                progDailog.dismiss();
//            }
//            Log.d(" sriram lat long",""+lati+","+longi);
////            Toast.makeText(HomeScreen.this,
////                    "LATITUDE :" + lati + " LONGITUDE :" + longi,
////                    Toast.LENGTH_LONG).show();
//            SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = pref.edit();
//            //   editor.putString("boy",resource.getBoy());
//            editor.putString("latitude", String.valueOf(lati));
//            editor.putString("longitude", String.valueOf(longi));
//            editor.putString("type", "device");
//
//            editor.putString("serv_latitude", String.valueOf(lati));
//            editor.putString("serv_longitude", String.valueOf(longi));
//            editor.putString("typeservice", "device");
//            editor.commit();
//
//try {
//    getSupportFragmentManager()
//            .beginTransaction()
//            .replace(
//                    R.id.fragment_container1,
//                    new FirstHome()
//                    //   new MapsFragment()
//            )
//            .commit();
//}catch(Exception ex){
//
//}
//        }
//
//
//        @Override
//        protected String doInBackground(String... params) {
//            // TODO Auto-generated method stub
//
//            while (this.lati == 0.0) {
//
//            }
//            return null;
//        }
//
//        public class VeggsterLocationListener implements LocationListener {
//
//            @Override
//            public void onLocationChanged(Location location) {
//
//                int lat = (int) location.getLatitude(); // * 1E6);
//                int log = (int) location.getLongitude(); // * 1E6);
//                int acc = (int) (location.getAccuracy());
//
//                String info = location.getProvider();
//                try {
//
//                    // LocatorService.myLatitude=location.getLatitude();
//
//                    // LocatorService.myLongitude=location.getLongitude();
//
//                    lati = location.getLatitude();
//                    longi = location.getLongitude();
//
//                } catch (Exception e) {
//                    // progDailog.dismiss();
//                    // Toast.makeText(getApplicationContext(),"Unable to get Location"
//                    // , Toast.LENGTH_LONG).show();
//                }
//
//            }

//            @Override
//            public void onProviderDisabled(String provider) {
//                Log.i("OnProviderDisabled", "OnProviderDisabled");
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//                Log.i("onProviderEnabled", "onProviderEnabled");
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status,
//                                        Bundle extras) {
//                Log.i("onStatusChanged", "onStatusChanged");
//
//            }

//        }
//
//    }


    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }
//
//    public void forceUpdate(){
//        PackageManager packageManager = this.getPackageManager();
//        PackageInfo packageInfo = null;
//        try {
//            packageInfo =packageManager.getPackageInfo(getPackageName(),0);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        String currentVersion = packageInfo.versionName;
//        new ForceUpdateAsync(currentVersion,HomeScreen.this).execute();
//    }
//
//    public class ForceUpdateAsync extends AsyncTask<String, String, JSONObject> {
//
//        private String latestVersion;
//        private String currentVersion;
//        private Context context;
//        public ForceUpdateAsync(String currentVersion, Context context){
//            this.currentVersion = currentVersion;
//            this.context = context;
//        }
//
//        @Override
//        protected JSONObject doInBackground(String... params) {
//
//            try {
//                latestVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + context.getPackageName()+ "&hl=en")
//                        .timeout(30000)
//                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
//                        .referrer("http://www.google.com")
//                        .get()
//                        .select("div.hAyfc:nth-child(3) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
//                        .first()
//                        .ownText();
//                Log.e("latestversion","---"+latestVersion);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return new JSONObject();
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject jsonObject) {
//            if(latestVersion!=null){
//                if(!currentVersion.equalsIgnoreCase(latestVersion)){
//                    // Toast.makeText(context,"update is available.",Toast.LENGTH_LONG).show();
//                    if(!(context instanceof HomeScreen)) {
//                        if(!((Activity)context).isFinishing()){
//                            showForceUpdateDialog();
//                        }
//                    }
//                }
//            }
//            super.onPostExecute(jsonObject);
//        }
//
//        public void showForceUpdateDialog(){
//
//            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
//        }
//
//    }



public void showDialog(){
        final Dialog dialog = new Dialog(HomeScreen.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.logout_dialog);

        Button cancle = dialog.findViewById(R.id.cancle);
        Button ok = dialog.findViewById(R.id.ok);


        cancle.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        dialog.dismiss();
        }
        });

        ok.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        dialog.dismiss();
     SharedPreferences   pref=getApplicationContext()
        .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear().commit();
//                editor.putString("boy_id","");
//                editor.putString("boy_phone","");
//                editor.putString("boy_name","");
//                editor.commit();

        Intent i=new Intent(HomeScreen.this, Login.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        }



    public void apiCallTOCheckAgent(){

        pd1 = new ProgressDialog(HomeScreen.this);
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

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");


        Call<StoresCatResponce> getCate = ApiClient.getApiService().agent_check(id);
        getCate.enqueue(new Callback<StoresCatResponce>() {
            @Override
            public void onResponse(Call<StoresCatResponce> call, Response<StoresCatResponce> response) {
                final StoresCatResponce resource = response.body();
                  pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
//                if (resource.getStatus().equals("ok")) {
//                    //agent main screen
//
//                    Intent ii = new Intent(HomeScreen.this, AgentMainScreen.class);
//                    ii.putExtra("agentid",resource.getAgent_id());
//                    startActivity(ii);
//
//                } else {
//                    //register
//
//                    Intent ii = new Intent(HomeScreen.this, AgentRegister.class);
//                    startActivity(ii);
//
//
//                }

            }

            @Override
            public void onFailure(Call<StoresCatResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
            //    Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void apiCallProfile() {
//        pd = new ProgressDialog(getActivity());
//        pd.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//         pd.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//        pd.setCancelable(false);
        // pd.show();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<MyProfileResponce> getCate = ApiClient.getApiService().getProgile("user",id);
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                 //   pd.dismiss();
                 //  Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){

                    profilename.setText(resource.getProfile().get(0).getUserName());
                    phnnumprofile.setText(resource.getProfile().get(0).getUserPhone());

//                    Glide.with(HomeScreen.this)
//                            .load(resource.getProfile().get(0).getUserImage())
//                            .fitCenter().placeholder(R.drawable.placeholderello)
//                            .into(userimg);
try {
    if (resource.getProfile().get(0).getUserImage() != null) {

        Glide.with(HomeScreen.this)
                .load(resource.getProfile().get(0).getUserImage())
                .centerCrop()
                .placeholder(R.drawable.placeholderello).into(userimg);

        mywalletamt.setText("My Wallet (₹"+resource.getProfile().get(0).getUserWallet()+")");


    }
}catch (Exception ex){

}
                }else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<MyProfileResponce> call, Throwable t) {
                //   pd.dismiss();
           //     pd.dismiss();
                t.printStackTrace();
             //   Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void onDestroy() {
        super.onDestroy();

    }

    public void updatecartcount(String count){

        if(cartcount!=null){
            if(count.equals("0")){
                cartcount.setText("0");
                cartcount.setVisibility(View.GONE);
            }else{
                cartcount.setText(count);
                cartcount.setVisibility(View.VISIBLE);

            }

        }

    }

}