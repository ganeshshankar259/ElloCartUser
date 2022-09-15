package com.ellocartuser.cart;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.home.adapterandmodel.Address;
import com.ellocartuser.home.adapterandmodel.PlaceAutoSuggestAdapter;
import com.ellocartuser.utils.GPSTracker;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddressFriendScreen extends AppCompatActivity {

    EditText name,phnnum,address,city,pincode,nearlocation,landmark;
    Button update;
    AutoCompleteTextView nearlocationauto;
    Intent intent;
    TextView current;
    ImageView imgback;
    ProgressDialog pd;
    Address addressdata;
    String currentfrom="";
    String seller_type="";
    String latitude=null,longitude=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address_friend);

        intent=getIntent();
        seller_type="0";

        pincode=findViewById(R.id.pincode);
        landmark=findViewById(R.id.landmark);
       // address1=findViewById(R.id.address1);
        nearlocationauto=findViewById(R.id.nearlocationauto);
        imgback=findViewById(R.id.imageback);
        current=findViewById(R.id.current);
        name=findViewById(R.id.name);
        phnnum=findViewById(R.id.phnnum);
        nearlocation=findViewById(R.id.nearlocation);
        address=findViewById(R.id.address);
        city=findViewById(R.id.city);
        pincode=findViewById(R.id.pincode);
        update=findViewById(R.id.update);
        currentfrom=intent.getStringExtra("current");

        if(intent.getStringExtra("from").equals("edit")){
            update.setText("Update");
            current.setText("Update Address");
            addressdata= (Address) intent.getParcelableExtra("addressdata");
            name.setText(addressdata.getAddrName());
            phnnum.setText(addressdata.getAddrPhone());
            address.setText(addressdata.getAddrAddress());
            city.setText(addressdata.getAddrCity());
            pincode.setText(addressdata.getAddrPincode());
        }else  if(intent.getStringExtra("from").equals("add")){
            update.setText("Add");
            current.setText("Add Receiver Address");
        }

        nearlocationauto.setAdapter(new PlaceAutoSuggestAdapter(getApplicationContext(), android.R.layout.simple_list_item_1));

        nearlocationauto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ", nearlocationauto.getText().toString());
                LatLng latLng = getLatLngFromAddress(nearlocationauto.getText().toString());
//                LocalStore.newInstance("","").categoryApi(String.valueOf(latLng.latitude),String.valueOf(latLng.longitude),getActivity());
                //categoryApi(); //call after click location
                if (latLng != null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);

                    latitude= String.valueOf(latLng.latitude);
                    longitude= String.valueOf(latLng.longitude);

                    android.location.Address address = getAddressFromLatLng(latLng);
                    if (address != null) {

//                        Log.d("Address : ", "" + address.toString());
//                        Log.d("Address Line : ", "" + address.getAddressLine(0));
//                        Log.d("Phone : ", "" + address.getPhone());
//                        Log.d("Pin Code : ", "" + address.getPostalCode());
//                        Log.d("Feature : ", "" + address.getFeatureName());
//                        Log.d("More : ", "" + address.getLocality());
                    } else {
                        Log.d("Adddress", "Address Not Found");
                    }
                } else {
                    Log.d("Lat Lng", "Lat Lng Not Found");
                }
            }
        });

        if(false){
            nearlocation.setVisibility(View.VISIBLE);
            nearlocationauto.setVisibility(View.GONE);
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                GPSTracker gps = new GPSTracker(getApplicationContext());
                if (gps.canGetLocation()) {

                  latitude = String.valueOf(gps.getLatitude());
                  longitude = String.valueOf(gps.getLongitude());
                    if (!latitude.equals("0.0") && !longitude.equals("0.0")) {

                        Log.d("lati", String.valueOf(latitude));
                        Log.d("lati long", String.valueOf(longitude));


                    } else {

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//                        String id = pref.getString("user_id","");
                        latitude = pref.getString("latitude","");
                        longitude = pref.getString("longitude","");

                       // Toast.makeText(AddAddress.this, "please wait 5 to 10 seconds we are getting location status", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(getApplicationContext(),"can't get location data",Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(),"turn on gps to add current address",Toast.LENGTH_LONG).show();
            }
            Geocoder geocoder;
            List<android.location.Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            try {

                addresses = geocoder.getFromLocation(Double.valueOf(latitude), Double.valueOf(longitude), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                if(addresses!=null) {
                    if(addresses.size()>0) {
                        String addressdata = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        String cityy = addresses.get(0).getLocality();
                        String state = addresses.get(0).getAdminArea();
                        String country = addresses.get(0).getCountryName();
                        String postalCode = addresses.get(0).getPostalCode();
                        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                        pincode.setText(postalCode);
                        address.setText(addressdata);
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"can't get location data",Toast.LENGTH_LONG).show();

                }
//                city.setText(cityy);
//                pincode.setText(postalCode);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{

            nearlocation.setVisibility(View.GONE);
            nearlocationauto.setVisibility(View.VISIBLE);

        }

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validation()){
                    if(latitude!=null && longitude !=null) {
                        if (intent.getStringExtra("from").equals("edit")) {
                            editApiCall();
                        } else {
                            //addApiCall();
                            addApiCallFriend();
                        }
                    }else{
                        if(currentfrom.equals("true")) {
                            Toast.makeText(AddAddressFriendScreen.this, "Please check Gps and try again", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(AddAddressFriendScreen.this, "Please select the location from the recomended location in the nearlocation field ", Toast.LENGTH_LONG).show();

                        }
                    }
                }
            }
        });

//        name.setText(intent.getStringExtra("name"));
//        phnnum.setText(intent.getStringExtra("phonenumer"));
//        address.setText(intent.getStringExtra("name"));

    }

    private void addApiCall() {

        pd = new ProgressDialog(AddAddressFriendScreen.this);
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
     //   SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        String lat = pref.getString("latitude","");
        String longi = pref.getString("longitude","");

//        if(currentfrom.equals("true")){
//            lat=latitude;
//            longi=longitude;
//        }

        Call<MyProfileResponce> getCate = ApiClient.getApiService().addAddress("add",id,address.getText().toString()+"\nLandMark : "+landmark.getText().toString(),pincode.getText().toString(),
                city.getText().toString(),name.getText().toString(),phnnum.getText().toString(),latitude,longitude,seller_type);
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                pd.dismiss();
              //  Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){

                    onBackPressed();

                }else {
                    if(resource.getMessage()!=""){
                        Toast.makeText(AddAddressFriendScreen.this, resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<MyProfileResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();


            }
        });

    }

    private void addApiCallFriend() {

        pd = new ProgressDialog(AddAddressFriendScreen.this);
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
        //   SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        String lat = pref.getString("latitude","");
        String longi = pref.getString("longitude","");

//        if(currentfrom.equals("true")){
//            lat=latitude;
//            longi=longitude;
//        }

        Call<MyProfileResponce> getCate = ApiClient.getApiService().addAddressFriend("add",id,nearlocationauto.getText().toString()+"\nLandMark : "+landmark.getText().toString(),pincode.getText().toString(),
                city.getText().toString(),name.getText().toString(),phnnum.getText().toString(),latitude,longitude,seller_type);
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                pd.dismiss();
                //  Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){

                    onBackPressed();

                }else {
                    if(resource.getMessage()!=""){
                        Toast.makeText(AddAddressFriendScreen.this, resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<MyProfileResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();


            }
        });

    }


    private void editApiCall() {

        pd = new ProgressDialog(AddAddressFriendScreen.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        // pd.show();
        //   SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        Call<MyProfileResponce> getCate = ApiClient.getApiService().editAddress(intent.getStringExtra("id"),"edit",id,address.getText().toString(),pincode.getText().toString(),
                city.getText().toString(),name.getText().toString(),phnnum.getText().toString(),"0.00","0.00");
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                pd.dismiss();
                //Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){

                    onBackPressed();

                }else {
                    if(resource.getMessage()!=""){
                        Toast.makeText(AddAddressFriendScreen.this, resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<MyProfileResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();


            }
        });

    }

    public boolean  validation(){
//        if(currentfrom.equals("true")){
//            if(nearlocation.getText().toString().trim().length()==0){
//                Toast.makeText(AddAddress.this,"please fill nearlocation field",Toast.LENGTH_SHORT).show();
//                return  false;
//            }
//        }else{
            if(nearlocationauto.getText().toString().trim().length()==0){
                Toast.makeText(AddAddressFriendScreen.this,"please fill nearlocation field",Toast.LENGTH_SHORT).show();
                return  false;
            }else
//        }
                if(latitude==null || longitude==null ){
                    Toast.makeText(AddAddressFriendScreen.this,"please fill nearlocation field to get location",Toast.LENGTH_SHORT).show();
                    return  false;
                }

        if(name.getText().toString().trim().length()==0){
            Toast.makeText(AddAddressFriendScreen.this,"please fill name field",Toast.LENGTH_SHORT).show();
            return  false;
        }else    if(name.getText().toString().trim().length()<3){
            Toast.makeText(AddAddressFriendScreen.this,"please enter name minimum 3 characters",Toast.LENGTH_SHORT).show();
            return  false;
        }else if(phnnum.getText().toString().trim().length()==0){
            Toast.makeText(AddAddressFriendScreen.this,"please fill phone number field",Toast.LENGTH_SHORT).show();
            return  false;
        }else     if(phnnum.getText().toString().trim().length()!=10){
            Toast.makeText(AddAddressFriendScreen.this,"please enter 10 digits phone number",Toast.LENGTH_SHORT).show();
            return  false;
        }  else     if(landmark.getText().toString().trim().length()==0){
            Toast.makeText(AddAddressFriendScreen.this,"please fill landmark field",Toast.LENGTH_SHORT).show();
            return  false;
        }else
        if(landmark.getText().toString().trim().length()<3){
            Toast.makeText(AddAddressFriendScreen.this,"landmark field should contain atleast 3 charesters",Toast.LENGTH_SHORT).show();
            return  false;
        }else     if(pincode.getText().toString().trim().length()==0){
            Toast.makeText(AddAddressFriendScreen.this,"please fill Pincode field",Toast.LENGTH_SHORT).show();
            return  false;
        }
        else     if(pincode.getText().toString().trim().length()!=6){
            Toast.makeText(AddAddressFriendScreen.this,"Enter Valid Pincode ",Toast.LENGTH_SHORT).show();
            return  false;
        }
//        else if(city.getText().toString().trim().length()==0){
//            Toast.makeText(AddAddress.this,"please fill city field",Toast.LENGTH_SHORT).show();
//            return  false;
//        }else if(pincode.getText().toString().trim().length()==0){
//            Toast.makeText(AddAddress.this,"please fill pincode field",Toast.LENGTH_SHORT).show();
//            return  false;
//        }

        return true;

    }

    private android.location.Address getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getApplicationContext());
        List<android.location.Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);
            if (addresses != null) {
                android.location.Address address = addresses.get(0);
                return address;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private LatLng getLatLngFromAddress(String address) {

        Geocoder geocoder = new Geocoder(getApplicationContext());
        List<android.location.Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(address, 1);
            if (addressList != null) {
                android.location.Address singleaddress = addressList.get(0);
                LatLng latLng = new LatLng(singleaddress.getLatitude(), singleaddress.getLongitude());
                return latLng;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}