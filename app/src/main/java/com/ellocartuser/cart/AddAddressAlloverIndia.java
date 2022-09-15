package com.ellocartuser.cart;

import androidx.appcompat.app.AppCompatActivity;

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

public class AddAddressAlloverIndia extends AppCompatActivity {
    EditText name,phnnum,address,city,state,pincode,nearlocation,landmark;
    Button update;

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
        setContentView(R.layout.activity_add_address_allover_india);

        intent=getIntent();
        seller_type="1";

        pincode=findViewById(R.id.pincode);
        landmark=findViewById(R.id.landmark);
        // address1=findViewById(R.id.address1);
       // nearlocationauto=findViewById(R.id.nearlocationauto);
        imgback=findViewById(R.id.imageback);
        current=findViewById(R.id.current);
        name=findViewById(R.id.name);
        phnnum=findViewById(R.id.phnnum);
        nearlocation=findViewById(R.id.nearlocation);
        address=findViewById(R.id.address);
        city=findViewById(R.id.city);
        state=findViewById(R.id.state);
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
            current.setText("Add Address");
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

                            addApiCall();

                        }
            }
        });

//        name.setText(intent.getStringExtra("name"));
//        phnnum.setText(intent.getStringExtra("phonenumer"));
//        address.setText(intent.getStringExtra("name"));

    }

    private void addApiCall() {

        pd = new ProgressDialog(AddAddressAlloverIndia.this);
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

        String addressstring=address.getText().toString()+city.getText().toString()+state.getText().toString()+city.getText().toString()+pincode.getText().toString()+"\nLandMark : "+landmark.getText().toString();

        Call<MyProfileResponce> getCate = ApiClient.getApiService().addAddress("add",id,addressstring,pincode.getText().toString(),
                city.getText().toString(),name.getText().toString(),phnnum.getText().toString(),"0.00","0.00",seller_type);
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
                        Toast.makeText(AddAddressAlloverIndia.this, resource.getMessage(), Toast.LENGTH_LONG).show();
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

        pd = new ProgressDialog(AddAddressAlloverIndia.this);
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
                        Toast.makeText(AddAddressAlloverIndia.this, resource.getMessage(), Toast.LENGTH_LONG).show();
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
//                Toast.makeText(AddAddressAlloverIndia.this,"please fill nearlocation field",Toast.LENGTH_SHORT).show();
//                return  false;
//            }
//        }else{
//            if(nearlocationauto.getText().toString().trim().length()==0){
//                Toast.makeText(AddAddressAlloverIndia.this,"please fill nearlocation field",Toast.LENGTH_SHORT).show();
//                return  false;
//            }
//        }
        if(name.getText().toString().trim().length()==0){
            Toast.makeText(AddAddressAlloverIndia.this,"please fill name field",Toast.LENGTH_SHORT).show();
            return  false;
        }else    if(name.getText().toString().trim().length()<3){
            Toast.makeText(AddAddressAlloverIndia.this,"please enter name minimum 3 characters",Toast.LENGTH_SHORT).show();
            return  false;
        }else if(phnnum.getText().toString().trim().length()==0){
            Toast.makeText(AddAddressAlloverIndia.this,"please fill phone number field",Toast.LENGTH_SHORT).show();
            return  false;
        }else     if(phnnum.getText().toString().trim().length()!=10){
            Toast.makeText(AddAddressAlloverIndia.this,"please enter 10 digits phone number",Toast.LENGTH_SHORT).show();
            return  false;
        } else     if(address.getText().toString().trim().length()==0){
            Toast.makeText(AddAddressAlloverIndia.this,"please fill address field",Toast.LENGTH_SHORT).show();
            return  false;
        }  else     if(city.getText().toString().trim().length()==0){
            Toast.makeText(AddAddressAlloverIndia.this,"please fill city field",Toast.LENGTH_SHORT).show();
            return  false;
        }
        else     if(state.getText().toString().trim().length()==0){
            Toast.makeText(AddAddressAlloverIndia.this,"please fill state field",Toast.LENGTH_SHORT).show();
            return  false;
        } else     if(landmark.getText().toString().trim().length()==0){
            Toast.makeText(AddAddressAlloverIndia.this,"please fill landmark field",Toast.LENGTH_SHORT).show();
            return  false;
        } else     if(pincode.getText().toString().trim().length()==0){
            Toast.makeText(AddAddressAlloverIndia.this,"please fill Pincode field",Toast.LENGTH_SHORT).show();
            return  false;
        }
//        else if(city.getText().toString().trim().length()==0){
//            Toast.makeText(AddAddressAlloverIndia.this,"please fill city field",Toast.LENGTH_SHORT).show();
//            return  false;
//        }else if(pincode.getText().toString().trim().length()==0){
//            Toast.makeText(AddAddressAlloverIndia.this,"please fill pincode field",Toast.LENGTH_SHORT).show();
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