package com.ellocartuser.cart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.MainActivity;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.AddressResponce;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.apiservices.Responce.StoresCatResponce;
import com.ellocartuser.home.adapterandmodel.Address;
import com.ellocartuser.home.adapterandmodel.AddressFriend;
import com.ellocartuser.utils.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutNext extends AppCompatActivity implements  AdressAdapter.OnItemClickedAdd,FriendAdressAdapter.OnItemClickedAdd {
    boolean flag_loacal=true,flag_friend=false;
    RecyclerView addresslist;
    ProgressDialog pd;
    AdressAdapter adressAdapter;
    AdressAdapter.OnItemClickedAdd onclick;
    FriendAdressAdapter friendadressAdapter;
    FriendAdressAdapter.OnItemClickedAdd friendonclick;
    Intent intent;
    TextView phnnum, name, address, amt,localtxt,friendtxt;
    Calendar myCalendar;
    ImageView circleImageView;
    Button dateshow, addaddress;
    DatePickerDialog.OnDateSetListener date;
    ImageView imgback;
    Button continuebtn;
    RadioGroup addgroup;
    LinearLayout textnoaddress;
    RadioButton currentlocation, addaddressrb;
    List<Address> addressdata = new ArrayList<>();
    List<AddressFriend> friendaddressdata = new ArrayList<>();

    TextView currentlocationtxt;
    String seller_type = "";
    CheckBox addr_local, addr_friend;
    Button addr_local_btn, addr_friend_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_next);

        seller_type = getIntent().getStringExtra("seller_type");

        localtxt = findViewById(R.id.onn);
        friendtxt = findViewById(R.id.cod);
        addr_local = findViewById(R.id.addr_local);
        addr_friend = findViewById(R.id.addr_friend);
        addr_local_btn = findViewById(R.id.addr_local_btn);
        addr_friend_btn = findViewById(R.id.addr_friend_btn);

        localtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addr_local.setChecked(true);
                addr_friend.setChecked(false);
                addr_local_btn.setVisibility(View.VISIBLE);
                addr_friend_btn.setVisibility(View.GONE);
                apiCallForAddress();

            }
        });

        friendtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addr_friend.setChecked(true);
                addr_local.setChecked(false);
                addr_friend_btn.setVisibility(View.VISIBLE);
                addr_local_btn.setVisibility(View.GONE);
                apiCallForAddressFriend();

            }
        });
        addr_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addr_local.setChecked(true);
                addr_friend.setChecked(false);
                addr_local_btn.setVisibility(View.VISIBLE);
                addr_friend_btn.setVisibility(View.GONE);
                apiCallForAddress();
            }
        });


        addr_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addr_friend.setChecked(true);
                addr_local.setChecked(false);
                addr_friend_btn.setVisibility(View.VISIBLE);
                addr_local_btn.setVisibility(View.GONE);
                apiCallForAddressFriend();
            }
        });


        amt = findViewById(R.id.amt);
        currentlocationtxt = findViewById(R.id.currentlocationtxt);
        textnoaddress = findViewById(R.id.textnoaddress);
        addgroup = findViewById(R.id.addgroup);
        imgback = findViewById(R.id.imageback);
        intent = getIntent();
        addresslist = findViewById(R.id.addresslist);
        name = findViewById(R.id.name);
        phnnum = findViewById(R.id.phnnum);
        address = findViewById(R.id.address);
        circleImageView = findViewById(R.id.imgicon);
        dateshow = findViewById(R.id.date);
        continuebtn = findViewById(R.id.continuebtn);
        addaddress = findViewById(R.id.addaddress);
        currentlocation = findViewById(R.id.currentlocation);
        addaddressrb = findViewById(R.id.addaddressrb);
        // addresslist.setLayoutManager(new LinearLayoutManager(this));
        addresslist.setLayoutManager(new LinearLayoutManager(CheckoutNext.this));


        onclick = this;
        friendonclick = this;

        amt.setText("₹" + intent.getStringExtra("carttotal"));
        name.setText(intent.getStringExtra("name"));
        phnnum.setText(intent.getStringExtra("phonenumer"));
        address.setText(intent.getStringExtra("address"));

        Glide.with(getApplicationContext())
                .load(intent.getStringExtra("imagename"))
                .fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).placeholder(R.drawable.placeholderello)
                .into(circleImageView);

        myCalendar = Calendar.getInstance();

        if (seller_type.equals("1")) {
            currentlocationtxt.setText("Add Delivery Address");
        }
        currentlocationtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (seller_type.equals("1")) {
                    Intent ii = new Intent(CheckoutNext.this, AddAddressAlloverIndia.class);
                    ii.putExtra("from", "add");
                    ii.putExtra("current", "true");
                    ii.putExtra("seller_type", seller_type);
                    startActivity(ii);
                } else {
                    Intent ii = new Intent(CheckoutNext.this, AddAddress.class);
                    ii.putExtra("from", "add");
                    ii.putExtra("current", "true");
                    ii.putExtra("seller_type", seller_type);
                    startActivity(ii);
                }

            }
        });


        addr_friend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent ii = new Intent(CheckoutNext.this, AddAddressFriendScreen.class);
                    ii.putExtra("from", "add");
                    ii.putExtra("current", "");
                    startActivity(ii);

            }
        });

        addr_local_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentlocation.isChecked()) {
                    Intent ii = new Intent(CheckoutNext.this, AddAddress.class);
                    ii.putExtra("from", "add");
                    ii.putExtra("current", "true");
                    startActivity(ii);
                }
            }
        });

        addr_local_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (true) {
                    Intent ii = new Intent(CheckoutNext.this, AddAddress.class);
                    ii.putExtra("from", "add");
                    ii.putExtra("current", "");
                    startActivity(ii);
                }
            }
        });

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
            }

        };

//        Date c = Calendar.getInstance().getTime();
//        System.out.println("Current time => " + c);
//
//        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
//        String formattedDate = df.format(c);


        dateshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(CheckoutNext.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                long now = System.currentTimeMillis() - 1000;

                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH, +7);

                // datePickerDialog.getDatePicker().setMaxDate(now+(1000*60*60*24*7));
                datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                datePickerDialog.getDatePicker().setMinDate(now);
                datePickerDialog.show();
            }
        });

        updateLabel();

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Address addata = null;
                String id = "";
                if(addr_local.isChecked()) {
                    for (int i = 0; i < addressdata.size(); i++) {
                        if (addressdata.get(i).getSelect().equals("1")) {
                            addata = addressdata.get(i);
                            id = addressdata.get(i).getAddrId();
                            continue;
                        }

                    }
                }
//                addr_local.setChecked(true);
//                addr_friend.setChecked(false);
                if(addr_friend.isChecked()) {
                    for (int i = 0; i < friendaddressdata.size(); i++) {
                        if (friendaddressdata.get(i).getSelect().equals("1")) {
                            addata=new Address();
                            addata.setAddrId(friendaddressdata.get(i).getAddrId());
                            addata.setAddrAddress(friendaddressdata.get(i).getAddrAddress());
                            addata.setAddrCity(friendaddressdata.get(i).getAddrCity());
                            addata.setAddrLat(friendaddressdata.get(i).getAddrLat());
                            addata.setAddrLong(friendaddressdata.get(i).getAddrLong());
                            addata.setAddrName(friendaddressdata.get(i).getAddrName());
                            addata.setAddrPhone(friendaddressdata.get(i).getAddrPhone());
                            addata.setAddrPincode(friendaddressdata.get(i).getAddrPincode());
                            id = friendaddressdata.get(i).getAddrId();
                            continue;
                        }

                    }
                }
                if (addata == null) {
                    Toast.makeText(CheckoutNext.this, "Please Select Address", Toast.LENGTH_LONG).show();
                    //error msg not selected address
                } else {

                    apiCallToCheckAddress(addata);

                }

//                Address addata=null;
//                String id="";
//
//                for (int i=0;i<addressdata.size();i++){
//
//                    if(addressdata.get(i).getSelect().equals("1")){
//                        addata=addressdata.get(i);
//                        id=addressdata.get(i).getAddrId();
//                        continue;
//                    }
//
//                }
//
//                if(addata==null){
//                    Toast.makeText(CheckoutNext.this, "Please Select Address", Toast.LENGTH_LONG).show();
//                    //error msg not selected address
//                }else{
//                    Intent ii= new Intent(CheckoutNext.this,Checkout.class);
//                    ii.putExtra("addressdata",addata);
//                    ii.putExtra("addressid",id);
//                    ii.putExtra("phonenumer",intent.getStringExtra("phonenumer"));
//                    ii.putExtra("address",intent.getStringExtra("address"));
//                    ii.putExtra("date",dateshow.getText().toString());
//                    ii.putExtra("count",intent.getStringExtra("count"));
//                    ii.putExtra("carttotal",intent.getStringExtra("carttotal"));
//                    ii.putExtra("order_type",intent.getStringExtra("order_type"));
//                    ii.putExtra("imagename",intent.getStringExtra("imagename"));
//                    ii.putExtra("name",intent.getStringExtra("name"));
//                    ii.putExtra("cartcod",intent.getStringExtra("cartcod"));
//                    ii.putExtra("delivery_charges",intent.getS    tringExtra("delivery_charges"));
//                    startActivity(ii);
//                }
            }
        });

        currentlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(CheckoutNext.this, AddAddress.class);
                ii.putExtra("from", "add");
                ii.putExtra("current", "true");
                startActivity(ii);
            }
        });
//apiCallForAddress();
    }

    private void apiCallToCheckAddress(Address address) {

        pd = new ProgressDialog(CheckoutNext.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        pd.show();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");

//        String lat = pref.getString("latitude","");
//        String longi = pref.getString("longitude","");

        Call<StoresCatResponce> getCate = ApiClient.getApiService().getCheckDelivery(id, address.getAddrLat(), address.getAddrLong(), getIntent().getStringExtra("seller_lat"), getIntent().getStringExtra("seller_long"));
        getCate.enqueue(new Callback<StoresCatResponce>() {
            @Override
            public void onResponse(Call<StoresCatResponce> call, Response<StoresCatResponce> response) {
                final StoresCatResponce resource = response.body();
                pd.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    if (resource.getDeliver().equals("ok")) {

                        Intent ii = new Intent(CheckoutNext.this, Checkout.class);
                        ii.putExtra("addressdata", address);
                        ii.putExtra("wallet", "");
                        ii.putExtra("pwallet", "");

                        if(addr_friend.isChecked()){
                            ii.putExtra("type", "order_to_my_friend");
                        }else if(addr_local.isChecked()){
                            ii.putExtra("type", "order_to_me");
                        }

                        ii.putExtra("addressid", address.getAddrId());
                        ii.putExtra("phonenumer", intent.getStringExtra("phonenumer"));
                        ii.putExtra("date", dateshow.getText().toString());
                        ii.putExtra("count", intent.getStringExtra("count"));
                        ii.putExtra("carttotal", intent.getStringExtra("carttotal"));
                        ii.putExtra("item_save", intent.getStringExtra("item_save"));
                        ii.putExtra("order_type", intent.getStringExtra("order_type"));
                        ii.putExtra("imagename", intent.getStringExtra("imagename"));
                        ii.putExtra("nameseller", intent.getStringExtra("name"));
                        ii.putExtra("addressseller", intent.getStringExtra("address"));
                        ii.putExtra("cartcod", intent.getStringExtra("cartcod"));
                        ii.putExtra("cod_status", resource.getCod_status());
                        ii.putExtra("seller_time", intent.getStringExtra("seller_time"));
                        //  ii.putExtra("delivery_charges", intent.getStringExtra("delivery_charges"));
                        ii.putExtra("delivery_charges", resource.getDelivery_charges());
                        ii.putExtra("offer_status", resource.getOffer_status());
                        ii.putExtra("distance", resource.getDetails().getDistance());
                        ii.putParcelableArrayListExtra("other_charges", (ArrayList<? extends Parcelable>) resource.getOther_charges());
                        startActivity(ii);

                    } else {

                        Util.AlertWithOK(CheckoutNext.this, resource.getMessage());
                        // Toast.makeText(CheckoutNext.this, resource.getMessage(), Toast.LENGTH_LONG).show();

                    }

                } else {

                    Util.AlertWithOK(CheckoutNext.this, resource.getMessage());
                    // Toast.makeText(CheckoutNext.this, resource.getMessage(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<StoresCatResponce> call, Throwable t) {
                pd.dismiss();
                //  pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(CheckoutNext.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(currentlocation!=null  && addr_local!=null){

            if(addr_friend.isChecked()){
                apiCallForAddressFriend();
            }else if(addr_local.isChecked()){
                apiCallForAddress();

            }

        }
        // apiCallForAddress();
        currentlocation.setChecked(false);

    }

    private void updateLabel() {

        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        String daysc = getIntent().getStringExtra("seller_day");
        if (daysc.trim().length() != 0) {
            dateshow.setText(daysc);
        } else {
            dateshow.setText(sdf.format(myCalendar.getTime()));
        }

//        if(startt) {
//            start.setText(sdf.format(myCalendar.getTime()));
//        }
//        if(endd){
//            end.setText(sdf.format(myCalendar1.getTime()));
//        }

    }

    private void apiCallForAddress() {

        pd = new ProgressDialog(getApplicationContext());
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        //  // pd.show();
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");

        Call<AddressResponce> getCate = ApiClient.getApiService().getAddress("get", id, seller_type);
        getCate.enqueue(new Callback<AddressResponce>() {
            @Override
            public void onResponse(Call<AddressResponce> call, Response<AddressResponce> response) {
                final AddressResponce resource = response.body();
                pd.dismiss();
                //    Log.d("resss",resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {

                    addresslist.setVisibility(View.VISIBLE);
                    textnoaddress.setVisibility(View.GONE);


                    addressdata = resource.getAddress();
                    for (int i = 0; i < addressdata.size(); i++) {
                        if (i == 0) {
                            addressdata.get(i).setSelect("1");
                        } else {
                            addressdata.get(i).setSelect("0");
                        }
                    }
                    //
                    adressAdapter = new AdressAdapter(CheckoutNext.this, addressdata, onclick);
                    addresslist.setAdapter(adressAdapter);


                } else {
                    if (resource.getAddress().size() == 0) {
                        addresslist.setVisibility(View.GONE);
                        textnoaddress.setVisibility(View.VISIBLE);

                        adressAdapter = new AdressAdapter(CheckoutNext.this, resource.getAddress(), onclick);
                        addresslist.setAdapter(adressAdapter);
                    }
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<AddressResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void apiCallForAddressFriend() {

        pd = new ProgressDialog(getApplicationContext());
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        //  // pd.show();
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");

        Call<AddressResponce> getCate = ApiClient.getApiService().getAddressFriend("get", id, "2");
        getCate.enqueue(new Callback<AddressResponce>() {
            @Override
            public void onResponse(Call<AddressResponce> call, Response<AddressResponce> response) {
                final AddressResponce resource = response.body();
                pd.dismiss();
                //    Log.d("resss",resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {

                    addresslist.setVisibility(View.VISIBLE);
                    textnoaddress.setVisibility(View.GONE);


                    friendaddressdata = resource.getReceiver_address();
                    for (int i = 0; i < friendaddressdata.size(); i++) {
                        if (i == 0) {
                            friendaddressdata.get(i).setSelect("1");
                        } else {
                            friendaddressdata.get(i).setSelect("0");
                        }
                    }
                    //
                    friendadressAdapter = new FriendAdressAdapter(CheckoutNext.this, friendaddressdata, friendonclick);
                    addresslist.setAdapter(friendadressAdapter);


                } else {
                    if(resource.getAddress()==null){
                        addresslist.setVisibility(View.GONE);
                        textnoaddress.setVisibility(View.VISIBLE);

                    }else
                    if (resource.getAddress().size() == 0) {
                        addresslist.setVisibility(View.GONE);
                        textnoaddress.setVisibility(View.VISIBLE);

                        friendadressAdapter = new FriendAdressAdapter(CheckoutNext.this, resource.getReceiver_address(), friendonclick);
                        addresslist.setAdapter(friendadressAdapter);
                    }
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }

                }
            }

            @Override
            public void onFailure(Call<AddressResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onItemClickedCart(int position, String mParam1, Address address) {


        if (mParam1.equals("delete")) {
//            Intent ii = new Intent(ctx, AddAddress.class);
//            ii.putExtra("from", "add");
//            ctx.startActivity(ii);

            try {

                AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutNext.this);
                //  builder.setTitle("UPDATE AVILABLE");
                builder.setMessage(getResources().getString(R.string.delete));
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteAddrApiCall(address.getAddrId());
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }


        } else if (mParam1.equals("edit")) {
            Intent ii = new Intent(CheckoutNext.this, AddAddress.class);
            ii.putExtra("from", "edit");
            ii.putExtra("addressdata", address);
            ii.putExtra("current", "");

            ii.putExtra("id", address.getAddrId());
            startActivity(ii);
        } else if (mParam1.equals("select")) {
            flag_friend=false;flag_loacal=true;
            if (addressdata.size() == 0) {
                return;
            }

            for (int i = 0; i < addressdata.size(); i++) {


                addressdata.get(i).setSelect("0");


            }
            addressdata.get(position).setSelect("1");
            adressAdapter.updateList(addressdata);
            //     adressAdapter.notifyDataSetChanged();

        }


//            if(mParam1.equals("select")) {
//
//            if(addressdata.size()==0){ return; }
//
//            for(int i=0;i<addressdata.size();i++){
//
//
//                addressdata.get(i).setSelect("0");
//
//
//            }
//            addressdata.get(position).setSelect("1");
//            adressAdapter=new AdressAdapter(getApplicationContext(),addressdata,onclick);
//            addresslist.setAdapter(adressAdapter);
//            adressAdapter.notifyDataSetChanged();
//
//        }

    }

    private void deleteAddrApiCall(String addrId) {

        pd = new ProgressDialog(CheckoutNext.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        // pd.show();
        //  SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");
        Call<MyProfileResponce> getCate = ApiClient.getApiService().deleteaddress("delete", id, addrId);
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                pd.dismiss();
                //    Log.d("resss",resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {

                    apiCallForAddress();

                } else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
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

    private void deleteAddrApiCallFriend(String addrId) {

        pd = new ProgressDialog(CheckoutNext.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        // pd.show();
        //  SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");
        Call<MyProfileResponce> getCate = ApiClient.getApiService().deleteAddressFriend("delete", id, addrId);
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                pd.dismiss();
                //    Log.d("resss",resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {

                    apiCallForAddressFriend();

                } else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
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


    @Override
    public void onItemClickedCartFriend(int position, String mParam1, AddressFriend address) {

        if (mParam1.equals("delete")) {
//            Intent ii = new Intent(ctx, AddAddress.class);
//            ii.putExtra("from", "add");
//            ctx.startActivity(ii);

            try {

                AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutNext.this);
                //  builder.setTitle("UPDATE AVILABLE");
                builder.setMessage(getResources().getString(R.string.delete));
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteAddrApiCallFriend(address.getAddrId());
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }


        } else if (mParam1.equals("edit")) {
            Intent ii = new Intent(CheckoutNext.this, AddAddress.class);
            ii.putExtra("from", "edit");
            ii.putExtra("addressdata", address);
            ii.putExtra("current", "true");

            ii.putExtra("id", address.getAddrId());
            startActivity(ii);
        } else if (mParam1.equals("select")) {
            flag_friend=true;flag_loacal=false;
            if (friendaddressdata.size() == 0) {
                return;
            }

            for (int i = 0; i < friendaddressdata.size(); i++) {


                friendaddressdata.get(i).setSelect("0");


            }
            friendaddressdata.get(position).setSelect("1");
            friendadressAdapter.updateList(friendaddressdata);
            //     adressAdapter.notifyDataSetChanged();

        }
    }
}