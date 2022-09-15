package com.ellocartuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.RegisterResponce;
import com.ellocartuser.utils.GPSTracker;
import com.ellocartuser.utils.WebViewActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;

import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity  implements EasyPermissions.PermissionCallbacks, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener  {

    ProgressDialog pd;
    GoogleApiClient googleApiClient;
    String slatitude=null,slongitude=null;
    EditText refernumber,editextphn,name,email;
    TextView privacytext,my_text_view;
    ImageView imageback;
    Button login;
    PackageInfo pInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        email=findViewById(R.id.email);
        my_text_view=findViewById(R.id.privacytext);
        refernumber=findViewById(R.id.refernumber);
        editextphn=findViewById(R.id.editextphn);
        name=findViewById(R.id.storename);
        privacytext=findViewById(R.id.privacytext);
        login=findViewById(R.id.login);
        imageback=findViewById(R.id.imageback);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        checkPermissionLocation();

        String text = "By Register, you agree to Ellocart Terms of Use and Privacy Policy.";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                String url = "https://www.ellocart.com/terms";

//              Intent i = new Intent(Intent.ACTION_VIEW);
//              i.setData(Uri.parse(url));
                Intent i=new Intent(Register.this, WebViewActivity.class);
                i.putExtra("url",url);
                i.putExtra("name","Terms of Use");
                startActivity(i);
            }
        };

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                String url = "https://www.ellocart.com/privacy";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
                Intent i=new Intent(Register.this, WebViewActivity.class);
                i.putExtra("url",url);
                i.putExtra("name","Privacy Policy");
                startActivity(i);
            }
        };


        spannableString.setSpan(clickableSpan1, 35,47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan2, 52,66, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//      spannableString.setSpan(clickableSpan3, 27,34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        my_text_view.setText(spannableString);
        my_text_view.setMovementMethod(LinkMovementMethod.getInstance());


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // if(slongitude==null && slatitude==null) {
                if(validationn()) {
                    if(editextphn.getText().toString().trim().length()==10) {
                        apiCall();
                    }else{
                        Toast.makeText(Register.this, "Phnone Number Length to be 10 digits", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

//    private boolean validation() {
//
//        if(name.getText().toString().trim().length()==0){
//            return  false;
//        }else if(editextphn.getText().toString().trim().length()!=10){
//            return  false;
//        }else if(email.getText().toString().trim().length()==0){
//            return  false;
//        }
//
//        return  true;
//    }

    private void apiCall() {

        pd = new ProgressDialog(Register.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        pd.show();
        // pd.show();

//        @Field("user_phone") RequestBody user_phone,
//        @Field("user_phone_code") RequestBody user_phone_code,
//        @Field("user_name") RequestBody user_name,
//        @Field("user_lat") RequestBody user_lat,
//        @Field("user_long") RequestBody user_long,
//        @Field("user_device") RequestBody user_device

        try {
             pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo.versionName;


        slatitude="0";slongitude="0";
        Call<RegisterResponce> getCate = ApiClient.getApiService().register(email.getText().toString().trim(),editextphn.getText().toString().trim(),"+91",name.getText().toString().trim(),slatitude,slongitude,"Android",version,refernumber.getText().toString());
        getCate.enqueue(new Callback<RegisterResponce>() {
            @Override
            public void onResponse(Call<RegisterResponce> call, Response<RegisterResponce> response) {
                final RegisterResponce resource = response.body();
                pd.dismiss();
            //    Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){

                    Toast.makeText(Register.this, resource.getMessage(), Toast.LENGTH_LONG).show();

                    Intent ii= new Intent(Register.this,OtpScreen.class);
                    ii.putExtra("mobile",editextphn.getText().toString());
                    ii.putExtra("type","reg");
                    startActivity(ii);

//                    onBackPressed();
//                    try {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                        builder.setMessage("Registerd successfully");
//                        builder.setCancelable(true);
//
//                        builder.setPositiveButton(
//                                "OK",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                        onBackPressed();
//                                    }
//                                });
//
//
//                        AlertDialog alert = builder.create();
//                        alert.show();
//                    } catch (RuntimeException ex) {
//                        ex.printStackTrace();
//                    }
                }else {
                    if(resource.getMessage()!=""){
                        Toast.makeText(Register.this, resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<RegisterResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                Toast.makeText(Register.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }


    public void checkPermissionLocation(){

        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {

            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                try {
                    openGps();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                settingsrequest();
            }

        } else {
            EasyPermissions.requestPermissions(this, "We need permissions to get location",
                    123, perms);
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    private boolean validationn() {

        if(name.getText().toString().trim().length()==0){
            Toast.makeText(Register.this,"please enter Fullname",Toast.LENGTH_SHORT).show();
            return  false;
        }else if(email.getText().toString().trim().length()==0){
            Toast.makeText(Register.this,"please enter Email",Toast.LENGTH_SHORT).show();

            return  false;
        }
        else if(editextphn.getText().toString().trim().length()==0){
            Toast.makeText(Register.this,"please enter Mobile number",Toast.LENGTH_SHORT).show();

            return  false;
        }else if(!email.getText().toString().contains("@") && !email.getText().toString().contains(".")){
            Toast.makeText(Register.this,"please enter valid Email Id",Toast.LENGTH_SHORT).show();

            return  false;
        }

        return true;

    }

    @AfterPermissionGranted(123)
    private void openGps() throws IOException {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
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

                GPSTracker gps = new GPSTracker(Register.this);
                if(gps.canGetLocation()) {

                    String latitude = String.valueOf(gps.getLatitude());
                    String longitude = String.valueOf(gps.getLongitude());
                   // openPopup();
                    if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
                        Log.d("lati", String.valueOf(latitude));
                        Log.d("lati long", String.valueOf(longitude));
                        slatitude=latitude;
                        slongitude=longitude;


                    }else{
                        //  Toast.makeText(HomeDashboard.this, "please wait 5 to 10 seconds we are getting location status", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(Register.this, "please Turn On your GPS", Toast.LENGTH_SHORT).show();
                }
//                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                try {
//                    double longitude = location.getLongitude();
//                    double latitude = location.getLatitude();
//

//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }
            }else{
                settingsrequest();
            }
        } else {
            EasyPermissions.requestPermissions(this, "We need permissions to get location",
                    123, perms);
        }
    }

    public void settingsrequest()
    {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(Register.this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks( this)
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

            LocationSettingsRequest.Builder builder1 = new LocationSettingsRequest.Builder();
// ...

            SettingsClient client = LocationServices.getSettingsClient(this);
            Task<LocationSettingsResponse> task = client.checkLocationSettings(builder1.build());

            task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                @Override
                public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                    // All location settings are satisfied. The client can initialize
                    // location requests here.
                    // ...
                }
            });

            task.addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof ResolvableApiException) {
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(Register.this,
                                    1000);
                        } catch (IntentSender.SendIntentException sendEx) {
                            // Ignore the error.
                        }
                    }
                }
            });


//
//            PendingResult<LocationSettingsResult> result =
//                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
//            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
//                @Override
//                public void onResult(LocationSettingsResult result) {
//                    final Status status = result.getStatus();
//                    final LocationSettingsStates state = result.getLocationSettingsStates();
//                    switch (status.getStatusCode()) {
//                        case LocationSettingsStatusCodes.SUCCESS:
//                            // All location settings are satisfied. The client can initialize location
//                            // requests here.
//
//                            break;
//                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                            // Location settings are not satisfied. But could be fixed by showing the user
//                            // a dialog.
//                            try {
//                                // Show the dialog by calling startResolutionForResult(),
//                                // and check the result in onActivityResult().
//                                status.startResolutionForResult(Register.this, 1000);
//                            } catch (IntentSender.SendIntentException e) {
//                                // Ignore the error.
//                            }
//                            break;
//                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                            // Location settings are not satisfied. However, we have no way to fix the
//                            // settings so we won't show the dialog.
//                            break;
//                    }
//                }
//            });
        }
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }


}