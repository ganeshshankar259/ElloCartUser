package com.ellocartuser.servicesscreens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.Register;

import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.apiservices.Responce.ServiceResponce;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.utils.GPSTracker;
import com.ellocartuser.utils.RealPathUtil;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;

import static com.ellocartuser.utils.Util.saveBitmapToFile;

public class AddService extends AppCompatActivity  implements EasyPermissions.PermissionCallbacks, View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener  {

    String realPathuserimg=null,realPathlicimg=null,realPathidtyimg=null;
    String presentselectedimg,postcat,postsubcat;
    EditText address,posttitle,desc;
    CircleImageView userimg;
    ImageView licimg,idtyimg,cam;
    Button submitbtn,licnumbtn,idtybtn,continuebtn,applybtn;
    private static final int TAKE_PICTURE = 2;
    public static final int FILE_PICKER_REQUEST_CODE = 4;
    int ch=0,whichh=0;
    GoogleApiClient googleApiClient;
    String[] type=new String[5];
    List<String> list;
    String slatitude=null,slongitude=null,boy_id;
    ProgressDialog pd;
    ImageView imageback;
    Spinner spionner;
    TextView my_text_view,couponmsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        Util.PleaseLogin(AddService.this);

        postcat=getIntent().getStringExtra("catid");
        postsubcat=getIntent().getStringExtra("subcatids");

        String[] arraySpinner = new String[] {
                "0-1",
                "1-2",
                "2-3",
                "3-4",
                "4-5",
                "5-6",
                "6-7",
                "7-8",
                "8-9",
                "9-10",
                "10-11",
                "11-12",
                "12-13",
                "13-14",
                "14-15",
        };
         spionner = findViewById(R.id.experience);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spionner.setAdapter(adapter);

        cam=findViewById(R.id.cam);
        userimg=findViewById(R.id.userimg);
        posttitle=findViewById(R.id.posttitle);
        desc=findViewById(R.id.editText);
        address=findViewById(R.id.address);
        submitbtn=findViewById(R.id.submitbtn);
  //    posttitle=findViewById(R.id.posttitle);

        try {
            openGps();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageback=findViewById(R.id.imageback);

        //   cam.setOnClickListener(this);
//        userimg.setOnClickListener(this);
        cam.setOnClickListener(this);
        submitbtn.setOnClickListener(this);
        userimg.setOnClickListener(this);
//        continuebtn.setOnClickListener(this);
      //  imageback.setOnClickListener(this);
        //  applybtn.setOnClickListener(this);

        list = new ArrayList<>();
        list.add("Upload");
        list.add("Take Photo");
        type=GetStringArray((ArrayList<String>) list);



    }

    private void apiCallAgentRegister() {
        pd = new ProgressDialog(AddService.this);
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
        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
Log.d("expp",spionner.getSelectedItem().toString());
        //  RequestBody rtype = RequestBody.create(MediaType.parse("multipart/form-data"), "update");
        RequestBody rid = RequestBody.create(MediaType.parse("multipart/form-data"), id);
        RequestBody raddpost = RequestBody.create(MediaType.parse("multipart/form-data"), "add_post");
        RequestBody rpostcat = RequestBody.create(MediaType.parse("multipart/form-data"), postcat);
        RequestBody rlat = RequestBody.create(MediaType.parse("multipart/form-data"), slatitude);
        RequestBody rlong = RequestBody.create(MediaType.parse("multipart/form-data"), slongitude);
        RequestBody rpostsubcat= RequestBody.create(MediaType.parse("multipart/form-data"), postsubcat);
        RequestBody rposttitle= RequestBody.create(MediaType.parse("multipart/form-data"), posttitle.getText().toString());
        RequestBody rpostaddress= RequestBody.create(MediaType.parse("multipart/form-data"), address.getText().toString());
        RequestBody rpostdescription= RequestBody.create(MediaType.parse("multipart/form-data"), desc.getText().toString());
        RequestBody rexp= RequestBody.create(MediaType.parse("multipart/form-data"), spionner.getSelectedItem().toString());
        RequestBody rpoststatus= RequestBody.create(MediaType.parse("multipart/form-data"), "1");

//      File fileprofile = new File(realPathuserimg);

        File file2 = new File(realPathuserimg);
        File filelic = Util.getCompressedImageFile(file2,AddService.this);

        if(filelic==null){
            filelic=file2;
        }

//        File fileidty = new File(realPathidtyimg);

        MultipartBody.Part rlicimg=null,ridtyimg=null;

//        RequestBody reqimgpro = RequestBody.create(MediaType.parse("multipart/form-data"), fileprofile);
//        rprofileimg=MultipartBody.Part.createFormData("boy_image",fileprofile.getName(),reqimgpro);

        RequestBody reqimg = RequestBody.create(MediaType.parse("multipart/form-data"), filelic);
        rlicimg=MultipartBody.Part.createFormData("post_img1",filelic.getName(),reqimg);  // pannum

//        @Part("api") RequestBody api,
//        @Part("user_id") RequestBody user_id,
//        @Part("post_cat") RequestBody post_cat,
//        @Part("post_scats") RequestBody post_scats,
//        @Part("post_title") RequestBody post_title,
//        @Part("post_address") RequestBody post_address,
//        @Part("post_description") RequestBody post_description,
//        @Part("post_status") RequestBody post_status,
//        @Part("post_img1") RequestBody post_img1

        Call<ServiceResponce> getCate = ApiClient.getApiServiceforservice().serviceonaddpost(raddpost,rid,rpostcat,rpostsubcat,rposttitle,rpostaddress,rpostdescription,rpoststatus,rlat,rlong,rexp,rlicimg);
        getCate.enqueue(new Callback<ServiceResponce>() {

            @Override
            public void onResponse(Call<ServiceResponce> call, Response<ServiceResponce> response) {
                final ServiceResponce resource = response.body();
                pd.dismiss();
                //   Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }

                if(resource.getStatus().equals("ok")){
                  //  Toast.makeText(AddService.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                   // onBackPressed();

                    try {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddService.this);
//            builder.setTitle("UPDATE AVILABLE");
                        builder.setMessage("Registered Successfully");
                        builder.setCancelable(false);

                        builder.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Util.loadhome(AddService.this);
                                        Intent ii=new Intent(AddService.this, HomeScreen.class);
                                        ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                       startActivity(ii);

                                    }
                                });

                        AlertDialog alert = builder.create();
                        alert.show();

                    } catch (RuntimeException ex) {
                        ex.printStackTrace();
                    }

                }else {
                    if(resource.getMessage()!=""){
                        Toast.makeText(AddService.this, resource.getStatus()+"    ,   "+ resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ServiceResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                // Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean validation() {

        if(posttitle.getText().toString().trim().length()==0){
            Toast.makeText(AddService.this,"please fill post title field",Toast.LENGTH_SHORT).show();
            return  false;
        }else if(address.getText().toString().trim().length()==0){
            Toast.makeText(AddService.this,"please fill address field",Toast.LENGTH_SHORT).show();

            return  false;
        }else if(desc.getText().toString().trim().length()==0){
            Toast.makeText(AddService.this,"please enter description field",Toast.LENGTH_SHORT).show();

            return  false;
        }else if(realPathuserimg==null){
            Toast.makeText(AddService.this,"please select user image",Toast.LENGTH_SHORT).show();

            return  false;
        }
        else if(slatitude==null && slongitude==null){
            Toast.makeText(AddService.this,"Please turn gps we are not getting location data",Toast.LENGTH_SHORT).show();

            return  false;
        }

        return true;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
// Check for the integer request code originally supplied to startResolutionForResult().

            case FILE_PICKER_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // Get the Image from data
                        //  Uri selectedImage = data.getData();
                        Uri fullPhotoUri = data.getData();
                     //   if(presentselectedimg=="userimg") {
                            if (Build.VERSION.SDK_INT < 11) {
                                realPathuserimg = RealPathUtil.getRealPathFromURI_BelowAPI11(this, data.getData());
                            } else if (Build.VERSION.SDK_INT < 19) {
                                realPathuserimg = RealPathUtil.getRealPathFromURI_API11to18(this, data.getData());
                            } else {
                                realPathuserimg = RealPathUtil.getRealPathFromURI_API19(this, data.getData());
                            }
                            userimg.setImageURI(fullPhotoUri);


                    }

                }
                break;
            case TAKE_PICTURE:

                if (resultCode == RESULT_OK && data != null && resultCode == RESULT_OK) {
//                    bitmap = (Bitmap) data.getExtras().get("data");
//                    profile_image.setImageBitmap(bitmap);

                    Uri fullPhotoUri = data.getData();
//
//                    userimg.setImageURI(fullPhotoUri);

                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                    File destination = new File(Environment.getExternalStorageDirectory(),"temp.jpg");
                    FileOutputStream fo;
                    try {
                        fo = new FileOutputStream(destination);
                        fo.write(bytes.toByteArray());
                        fo.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                 //   if(presentselectedimg=="userimg") {
                        Bitmap myBitmap = BitmapFactory.decodeFile(destination.getAbsolutePath());
                        realPathuserimg =  destination.getAbsolutePath();

                        userimg.setImageBitmap(myBitmap);
                //    }else if(presentselectedimg=="licimg") {



                }
                break;

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }


    @AfterPermissionGranted(345)
    private void selectImage() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("image/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(chooseFile, FILE_PICKER_REQUEST_CODE);

    }

    @AfterPermissionGranted(345)
    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, TAKE_PICTURE);
    }
    private String getRealPathFromURI(Uri contentURI) {
        //Log.e("in","conversion"+contentURI.getPath());
        String path;
        Cursor cursor = getContentResolver()
                .query(contentURI, null, null, null, null);
        if (cursor == null)
            path=contentURI.getPath();

        else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            path=cursor.getString(idx);

        }
        if(cursor!=null)
            cursor.close();
        return path;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case  R.id.submitbtn:

                if(validation()) {

                    apiCallAgentRegister();

                }else{
                    // Toast.makeText(AgentRegister.this, "Please Fill All The Fields", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.cam:
                selectImageForGetPath("userimg");
                break;
            case R.id.userimg:
                selectImageForGetPath("userimg");
                break;


//            case R.id.licnumbtn:
//                selectImageForGetPath("licimg");
//                break;
//
//            case R.id.idtybtn:
//                selectImageForGetPath("idtyimg");
//                break;

            case R.id.imageback:
                onBackPressed();
                break;

        }
    }

    private void selectImageForGetPath(String imgfor) {
        presentselectedimg=imgfor;
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddService.this);
        builder.setTitle("Select");
        builder.setSingleChoiceItems(type, ch, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                whichh=which;
            }
        });

// add OK and Cancel buttons
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getBaseContext(), paymode.get(ch), Toast.LENGTH_SHORT).show();
                String[] perms = {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if (EasyPermissions.hasPermissions(AddService.this, perms)) {

                    if (ActivityCompat.checkSelfPermission(AddService.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(AddService.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    if(whichh==0){



                        selectImage();


                    }else{

                        takePhoto();

                    }
                } else {
                    EasyPermissions.requestPermissions(AddService.this, "We need permissions ",345, perms);
                }
                whichh=0;
                // user clicked OK
            }
        });
        builder.setNegativeButton("Cancel", null);
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static String[] GetStringArray(ArrayList<String> arr) {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
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

                GPSTracker gps = new GPSTracker(AddService.this);
                if(gps.canGetLocation()) {
                    String latitude = String.valueOf(gps.getLatitude());
                    String longitude = String.valueOf(gps.getLongitude());

                    if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
                        Log.d("lati", String.valueOf(latitude));
                        Log.d("lati long", String.valueOf(longitude));
                        slatitude=latitude;
                        slongitude=longitude;
                        Geocoder geocoder;
                        List<Address> addresses;
                        geocoder = new Geocoder(this, Locale.getDefault());

//                        addresses = geocoder.getFromLocation(Double.valueOf(latitude),Double.valueOf(longitude), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//
//                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//                        String city = addresses.get(0).getLocality();
//                        String state = addresses.get(0).getAdminArea();
//                        String country = addresses.get(0).getCountryName();
//                        String postalCode = addresses.get(0).getPostalCode();
//                        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                    }else{
                        Toast.makeText(AddService.this, "please wait 5 to 10 seconds we are getting location status", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(AddService.this, "please Turn On your GPS", Toast.LENGTH_SHORT).show();
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
            googleApiClient = new GoogleApiClient.Builder(AddService.this)
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
//
//            LocationSettingsRequest.Builder builder1 = new LocationSettingsRequest.Builder();
//
//// ...
//
//            SettingsClient client = LocationServices.getSettingsClient(this);
//            Task<LocationSettingsResponse> task = client.checkLocationSettings(builder1.build());
//
//            task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
//                @Override
//                public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
//                    // All location settings are satisfied. The client can initialize
//                    // location requests here.
//                    // ...
//                }
//            });
//
//            task.addOnFailureListener(this, new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    if (e instanceof ResolvableApiException) {
//                        // Location settings are not satisfied, but this can be fixed
//                        // by showing the user a dialog.
//                        try {
//                            // Show the dialog by calling startResolutionForResult(),
//                            // and check the result in onActivityResult().
//                            ResolvableApiException resolvable = (ResolvableApiException) e;
//                            resolvable.startResolutionForResult(Register.this,
//                                    1000);
//                        } catch (IntentSender.SendIntentException sendEx) {
//                            // Ignore the error.
//                        }
//                    }
//                }
//            });


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
                                status.startResolutionForResult(AddService.this, 1000);
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

//    public void getLocationPopup(){
//        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
//        if (EasyPermissions.hasPermissions(this, perms)) {
//
//            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                try {
//                    openGps();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                settingsrequest();
//            }
//        } else {
//            EasyPermissions.requestPermissions(this, "We need permissions to get location",
//                    123, perms);
//        }
//    }

}



