package com.ellocartuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.RegisterResponce;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.utils.GPSTracker;
import com.ellocartuser.utils.Util;
import com.ellocartuser.utils.WebViewActivity;

import java.io.IOException;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    EditText editextphn;
    TextView btnskip,tvSkip,tvtc,tvterm,tvpc;
    Button btnlogin,btnSendOTP;
    AppCompatEditText editextphone;
    ProgressDialog pd;
   // Button register;
    Boolean  doubleBackToExitPressedOnce=false;
    TextView my_text_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

//       SharedPreferences pref=getApplicationContext()
//                .getSharedPreferences("user", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        //   editor.putString("user_id","346");
//        editor.putBoolean("loadhome",true);
//        editor.commit();
        my_text_view=findViewById(R.id.privacytext);

        editextphn=findViewById(R.id.editextphn);
        editextphone=findViewById(R.id.editextphone);
        btnlogin=findViewById(R.id.btnlogin);
        btnSendOTP=findViewById(R.id.btnSendOTP);
        tvSkip=findViewById(R.id.tvSkip);

        tvtc=findViewById(R.id.tvtc);
        tvterm=findViewById(R.id.tvterm);
        tvpc=findViewById(R.id.tvpc);
      //  register=findViewById(R.id.register);
       // register=findViewById(R.id.registerbtn);
        checkPermissionLocation();
        btnSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editextphone.getText().toString().trim().length()==0){
                    Toast.makeText(Login.this, "please enter Mobile number", Toast.LENGTH_LONG).show();
                    return;
                }

                if(editextphone.getText().toString().trim().length()==10){
                    apiCall();
                }else{
                    Toast.makeText(Login.this, "please enter valid Mobile number", Toast.LENGTH_LONG).show();
                }
            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadhome(Login.this);
                Intent i= new Intent(Login.this, HomeScreen.class);
                startActivity(i);

            }
        });


        String text = "BY CONTINUING, YOU AGREE TO OUR\n" +
                "TERMS OF SERVICE & PRIVACY POLICY";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                String url = "https://www.ellocart.com/terms";

//              Intent i = new Intent(Intent.ACTION_VIEW);
//              i.setData(Uri.parse(url));
                Intent i=new Intent(Login.this, WebViewActivity.class);
                i.putExtra("url",url);
                i.putExtra("name","TERMS OF SERVICE");
                startActivity(i);
            }
        };
        tvtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.ellocart.com/terms";

//              Intent i = new Intent(Intent.ACTION_VIEW);
//              i.setData(Uri.parse(url));
                Intent i=new Intent(Login.this, WebViewActivity.class);
                i.putExtra("url",url);
                i.putExtra("name","TERMS OF SERVICE");
                startActivity(i);
            }
        });
        tvterm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.ellocart.com/terms";

//              Intent i = new Intent(Intent.ACTION_VIEW);
//              i.setData(Uri.parse(url));
                Intent i=new Intent(Login.this, WebViewActivity.class);
                i.putExtra("url",url);
                i.putExtra("name","TERMS OF SERVICE");
                startActivity(i);
            }
        });

        tvpc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.ellocart.com/privacy";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
                Intent i=new Intent(Login.this, WebViewActivity.class);
                i.putExtra("url",url);
                i.putExtra("name","PRIVACY POLICY");
                startActivity(i);
            }
        });

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                String url = "https://www.ellocart.com/privacy";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
                Intent i=new Intent(Login.this, WebViewActivity.class);
                i.putExtra("url",url);
                i.putExtra("name","PRIVACY POLICY");
                startActivity(i);
            }
        };


        spannableString.setSpan(clickableSpan1, 31,49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan2, 51,65, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

//      spannableString.setSpan(clickableSpan3, 27,34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        my_text_view.setText(spannableString);
        my_text_view.setMovementMethod(LinkMovementMethod.getInstance());


//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i= new Intent(Login.this,Register.class);
//                startActivity(i);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();



    }
    public void checkPermissionLocation() {

        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {

            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                try {
//                    openGps();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                settingsrequest();
            }
        } else {
            EasyPermissions.requestPermissions(this, "We need permissions to get location",
                    123, perms);
        }
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



            }else{
                //settingsrequest();
            }
        } else {
            EasyPermissions.requestPermissions(this, "We need permissions to get location",
                    123, perms);
        }
    }

    private void apiCall() {

        pd = new ProgressDialog(Login.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        pd.show();
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<RegisterResponce> getCate = ApiClient.getApiService().checkuser(editextphone.getText().toString(),"+91");
        getCate.enqueue(new Callback<RegisterResponce>() {
            @Override
            public void onResponse(Call<RegisterResponce> call, Response<RegisterResponce> response) {
                final RegisterResponce resource = response.body();
                pd.dismiss();


                if(resource==null){
                    return;
                }
                try {
                    //   Log.d("resss",resource.toString());
                    if (resource.getStatus().equals("ok")) {

                        Intent ii = new Intent(Login.this, OtpScreen.class);
                        ii.putExtra("mobile", editextphone.getText().toString());
                        ii.putExtra("type", "login");
                        startActivity(ii);

                    } else {
                        if (resource.getMessage() != "") {
                            Toast.makeText(Login.this, resource.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }catch (Exception ex){

                }

            }

            @Override
            public void onFailure(Call<RegisterResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                Toast.makeText(Login.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }


        @Override
        public void onBackPressed() {
        //    super.onBackPressed();
            //Checking for fragment count on backstack
         if (!doubleBackToExitPressedOnce) {
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

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}