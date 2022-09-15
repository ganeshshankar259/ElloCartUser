package com.ellocartuser.rooms_old;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.AddressResponce;
import com.ellocartuser.home.adapterandmodel.PlaceAutoSuggestAdapter;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.rooms_old.ModelsandResponces.Hotels_List_Adapter;
import com.ellocartuser.rooms_old.ModelsandResponces.Hotels_M;
import com.ellocartuser.rooms_old.ModelsandResponces.Hotels_Responce;
import com.ellocartuser.utils.GPSTracker;
import com.ellocartuser.utils.Util;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Room_Subc_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Room_Subc_Fragment extends Fragment implements Hotels_List_Adapter.onItemClick_Room{


    RecyclerView recyclerView;

    ProgressDialog pd, pd1;
    Hotels_List_Adapter adapter;
    Hotels_List_Adapter.onItemClick_Room onClick;
    List<Hotels_M> datalist=new ArrayList<>();
    //String cartsellerid=null,carttotalcount="",store_o="0";
    ImageView backbutton;
    SharedPreferences pref;
    String r_cattitle,r_catid;
    Dialog dialoglocation,dialog;
    TextView recentsearch,myaddress,current;//
    AutoCompleteTextView autoCompleteTextView;
    EditText auto;

    String locality,fullloca;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Room_Subc_Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Room_Subc_Fragment newInstance(String param1, String param2, String param3, String param4, String param5) {
        Room_Subc_Fragment fragment = new Room_Subc_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_room_subcategory, container, false);

        View view=inflater.inflate(R.layout.fragment_room_subcategory, container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("ROOM_CATID", Context.MODE_PRIVATE);
        r_catid = pref.getString("r_catid", "");
        r_cattitle = pref.getString("r_cattitle", "");

        auto = view.findViewById(R.id.auto);

        SharedPreferences pref1 = getActivity().getSharedPreferences("LOCALITY123", Context.MODE_PRIVATE);
        locality = pref1.getString("locality", "");
        fullloca = pref1.getString("fullloca", "");

//        auto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDialogSearch(getActivity());
//            }
//        });

        current = view.findViewById(R.id.current);

        ImageView refresh = view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiCall(r_catid,locality);
            }
        });

        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(getActivity());
            }
        });

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        backbutton = view.findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().onBackPressed();

                // Util.loadFragment(new homefragment(),getActivity(), Room_Subc_Fragment.this);
            }
        });
        onClick=this;
        adapter = new Hotels_List_Adapter(getActivity(), null, onClick);
        recyclerView.setAdapter(adapter);

//        apiCall();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

//        SharedPreferences pref = getActivity().getSharedPreferences("LOCALITY1", Context.MODE_PRIVATE);
//        String locality = pref.getString("locality", "");

        //String loc= "RAJAHMUNDRY";
        apiCall(r_catid,locality);
       // Log.d("Ashok : ", "" + locality);
        //  appBarLayout.removeOnOffsetChangedListener(this);
    }



    private void apiCall(String r_catid,String locality) {
        pd1 = new ProgressDialog(getActivity());
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();

//        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");

//        SharedPreferences pref = getActivity().getSharedPreferences("LOCALITY", Context.MODE_PRIVATE);
//        String locality = pref.getString("locality", "");
        // String d_id = "1";
        String id = "1";
//        String city = "rajahmundry";
        Call<Hotels_Responce> getCate = ApiClient.getApiServiceforservice().hotels_list(r_catid,locality);
        getCate.enqueue(new Callback<Hotels_Responce>() {
            @Override
            public void onResponse(Call<Hotels_Responce> call, Response<Hotels_Responce> response) {
                final Hotels_Responce resource = response.body();
                pd1.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    //catstore
                    //store_o=resource.getdSpecialid();
                    datalist=resource.getRoomsubcategories();

                    if (getActivity()!=null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                List<Doc_Profile_List_M> temp = new ArrayList();
//                                for(int i=0;i<datalist.size();i++){
////                                    if(datalist.get(i).().contains(text) || datalist.get(i).getProductName().toLowerCase().contains(text)){
////                                        temp.add(datalist.get(i));
////                                    }
//                                    //resource.getProfile().get(i);
//                                    temp.add(datalist.get(i));
//
//                                }

                                adapter.updateList(resource.getRoomsubcategories());
                                //adapter.updateList(resource.getProduct());
                                //  recyclerView.setAdapter(adapter);
                            }
                        });

                    }

                } else {
                      // Toast.makeText(getActivity(), resource.getStatus(), Toast.LENGTH_LONG).show();
                    //adapter.updateList(resource.getRoomsubcategories());
//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<Hotels_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                //t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public void onItemClick_Room(int position, String catid,String name) {
        Util.loadFragment(Rooms_List_Fragment.newInstance(catid, name, "", "", ""), getActivity(), Room_Subc_Fragment.this);
    }

    public void showDialog(Activity activity) {
        dialoglocation = new Dialog(activity, android.R.style.Theme_Holo_Light_NoActionBar);
        dialoglocation.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialoglocation.setCancelable(true);
        dialoglocation.setContentView(R.layout.locationdialog_r);

        // ConstraintLayout currentloc = dialog.findViewById(R.id.constraintLayout2);
        ImageView close = dialoglocation.findViewById(R.id.imageView3);

//        recentlist = dialoglocation.findViewById(R.id.recentlist);
//        addresslist = dialoglocation.findViewById(R.id.addresslist);
        CardView layout = dialoglocation.findViewById(R.id.layout);
        TextView tname = dialoglocation.findViewById(R.id.name);
        TextView   taddress = dialoglocation.findViewById(R.id.address);
//        addresslist.setNestedScrollingEnabled(false);
//        recentlist.setNestedScrollingEnabled(false);
//        addresslist.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recentlist.setLayoutManager(new LinearLayoutManager(getActivity()));


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialoglocation.dismiss();
            }
        });

//use current ki
        List<com.ellocartuser.home.adapterandmodel.Address> addlist = new ArrayList<>();
        com.ellocartuser.home.adapterandmodel.Address add = new com.ellocartuser.home.adapterandmodel.Address();
        String latitude = "", longitude = "";
        GPSTracker gps = new GPSTracker(getActivity());
        if (gps.canGetLocation()) {
            latitude = String.valueOf(gps.getLatitude());
            longitude = String.valueOf(gps.getLongitude());

//            apiCall();
//            SharedPreferences.Editor editor = pref.edit();
//            //   editor.putString("boy",resource.getBoy());
//            editor.putString("latitude", latitude);
//            editor.putString("longitude", longitude);
//            editor.putString("type", "device");
//            editor.commit();
//            update();
//            apiCallDetail();

            //            if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
            //
            //            }else{
            //
            //            }
        } else {




        }

//        tname.setText("Use Current Location");
//        add.setAddrLat(latitude);
//        add.setAddrLong(longitude);

//        tname.setTextColor(ContextCompat.getColor(getActivity(), R.color.yellow));

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
//                if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
            if (!latitude.equals("") && !longitude.equals("")) {
                addresses = geocoder.getFromLocation(Double.valueOf(latitude), Double.valueOf(longitude), 1);
                if (addresses.size() != 0) { // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    if (address != null) {
//                        taddress.setText(address);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String finalLatitude = latitude;
        String finalLongitude = longitude;
//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences.Editor editor = pref.edit();
//                //   editor.putString("boy",resource.getBoy());
//                editor.putString("latitude", finalLatitude);
//                editor.putString("longitude", finalLongitude);
//                editor.putString("type", "device");
////        editor.putString("type","entered");
////        editor.putString("currentloctext",address.getAddrAddress()+","+address.getAddrCity()+","+address.getAddrPincode());
//                editor.commit();
//                update();
////                apiCallDetail();
////                apiCall();
//
//                try {
//                    dialoglocation.dismiss();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//
//            }
//        });
//        myaddress = dialoglocation.findViewById(R.id.myaddress);
//        recentsearch = dialoglocation.findViewById(R.id.recentsearch);

        apiCallForAddress();



        autoCompleteTextView = dialoglocation.findViewById(R.id.auto);
        autoCompleteTextView.setAdapter(new PlaceAutoSuggestAdapter(getContext(), android.R.layout.simple_list_item_1));
        autoCompleteTextView.setHint("Search location by village, city, state");
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ", autoCompleteTextView.getText().toString());
                LatLng latLng = getLatLngFromAddress(autoCompleteTextView.getText().toString());
//                LocalStore.newInstance("","").categoryApi(String.valueOf(latLng.latitude),String.valueOf(latLng.longitude),getActivity());
                //categoryApi(); //call after click location
                if (latLng != null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);

//                    SharedPreferences.Editor editor = pref.edit();
//                    //   editor.putString("boy",resource.getBoy());
//                    editor.putString("latitude", String.valueOf(latLng.latitude));
//                    editor.putString("longitude", String.valueOf(latLng.longitude));
//                    editor.putString("type", "entered");
//                    editor.putString("currentloctext", autoCompleteTextView.getText().toString());
//                    editor.commit();
//                    current.setText(autoCompleteTextView.getText().toString());
                    update();
//                    apiCallDetail();
                    //String city = "rajahmundry";

//
//                    apiCall(autoCompleteTextView.getText().toString(),String.valueOf(latLng.latitude),String.valueOf(latLng.longitude));

                    Address address = getAddressFromLatLng(latLng);
                    if (address != null) {
                       // current.setText(address.getLocality());
                        SharedPreferences pref = getActivity().getSharedPreferences("LOCALITY", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                    //   editor.putString("boy",resource.getBoy());
                    editor.putString("locality", address.getLocality());
                    editor.commit();

                        String locality = address.getLocality();
                        apiCall(r_catid,locality);

                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ", "" + address.getAddressLine(0));
                        Log.d("Phone : ", "" + address.getPhone());
                        Log.d("Pin Code : ", "" + address.getPostalCode());
                        Log.d("Feature : ", "" + address.getFeatureName());
                        Log.d("More : ", "" + address.getLocality());
                    } else {
                        Log.d("Adddress", "Address Not Found");
                    }
                } else {
                    Log.d("Lat Lng", "Lat Lng Not Found");
                }
                dialoglocation.dismiss();

            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialoglocation.dismiss();
            }
        });


        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialoglocation.show();

        if (!gps.canGetLocation()) {
            dialoglocation.dismiss();
            ((HomeScreen) getActivity()).openLocationPopup();

        }

    }


    public void showDialogSearch(Activity activity) {
        dialog = new Dialog(activity, android.R.style.Theme_Holo_Light_NoActionBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.homeproductsearch);

//        nosearchdata = dialog.findViewById(R.id.sdfg);

//      ConstraintLayout currentloc = dialog.findViewById(R.id.constraintLayout2);
        ImageView close = dialog.findViewById(R.id.imageback);
        EditText editText = dialog.findViewById(R.id.productsearch);
        recyclerView = dialog.findViewById(R.id.catList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        editText.requestFocus();
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //Clear focus here from edittext
                    editText.clearFocus();
                }
                return false;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                apiCall(r_catid,editText.getText().toString());
//                apiCall();
//                String r_catid,String city
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
    private LatLng getLatLngFromAddress(String address) {


        Geocoder geocoder = new Geocoder(getContext());
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(address, 1);
            if (addressList != null) {
                Address singleaddress = addressList.get(0);
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

    private Address getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getContext());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);
            if (addresses != null) {
                Address address = addresses.get(0);
                return address;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    private void apiCallForAddress() {

        pd = new ProgressDialog(getActivity());
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

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");

        if(id.equals("") || id==null){
//            recentsearch.setVisibility(View.INVISIBLE);
//            myaddress.setVisibility(View.INVISIBLE);
        }

        Call<AddressResponce> getCate = ApiClient.getApiService().getAddress("get", id, "0");
        getCate.enqueue(new Callback<AddressResponce>() {
            @Override
            public void onResponse(Call<AddressResponce> call, Response<AddressResponce> response) {
                final AddressResponce resource = response.body();
                pd.dismiss();
                //    Log.d("resss",resource.toString());

                if (resource == null) {
                    return;
                }

                List<com.ellocartuser.home.adapterandmodel.Address> addlist = new ArrayList<>();
                com.ellocartuser.home.adapterandmodel.Address add = new com.ellocartuser.home.adapterandmodel.Address();
                String latitude = "", longitude = "";
                GPSTracker gps = new GPSTracker(getActivity());
                if (gps.canGetLocation()) {
                    latitude = String.valueOf(gps.getLatitude());
                    longitude = String.valueOf(gps.getLongitude());

                    SharedPreferences.Editor editor = pref.edit();
                    //   editor.putString("boy",resource.getBoy());
                    editor.putString("latitude", latitude);
                    editor.putString("longitude", longitude);
                    editor.putString("type", "device");
                    editor.commit();
                    update();
//                    apiCallDetail();
//                    apiCall();
                    //            if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
                    //
                    //            }else{
                    //
                    //                }
                } else {

                    ((HomeScreen) getActivity()).openLocationPopup();

                }

                add.setAddrName("Use Current Location");
                add.setAddrLat(latitude);
                add.setAddrLong(longitude);

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(getActivity(), Locale.getDefault());
                try {
//                if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
                    if (!latitude.equals("") && !longitude.equals("")) {
                        addresses = geocoder.getFromLocation(Double.valueOf(latitude), Double.valueOf(longitude), 1);
                        if (addresses.size() != 0) { // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            if (address != null) {
                                add.setAddrAddress(address);
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //    addlist.add(add);

                if (resource.getStatus().equals("ok")) {


                    for (int i = 0; i < resource.getAddress().size(); i++) {

                        addlist.add(resource.getAddress().get(i));

                    }


//                    AdressLocationAdapter adressAdapter1 = new AdressLocationAdapter(getActivity(), addlist, onclick);
//                    addresslist.setAdapter(adressAdapter1);
//
//                    RecentLocationAdapter recentadapter = new RecentLocationAdapter(getActivity(), resource.getPlaces(), onclickrecent);
//                    recentlist.setAdapter(recentadapter);

                } else {
//
//                    AdressLocationAdapter adressAdapter1 = new AdressLocationAdapter(getActivity(), addlist, onclick);
//                    addresslist.setAdapter(adressAdapter1);



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


    private void update() {


//        if(!pref.getString("type","").equals("entered")) {
//            GPSTracker gps = new GPSTracker(getActivity());
//            if (gps.canGetLocation()) {
//                String latitude = String.valueOf(gps.getLatitude());
//                String longitude = String.valueOf(gps.getLongitude());
//
//                SharedPreferences.Editor editor1 = pref.edit();
//                //   editor.putString("boy",resource.getBoy());
//                editor1.putString("latitude", latitude);
//                editor1.putString("longitude", longitude);
//                editor1.putString("type", "device");
//
//                if(pref.getString("serv_latitude","") =="" || pref.getString("serv_longitude","") ==""  ) {
//                    editor1.putString("serv_latitude", String.valueOf(latitude));
//                    editor1.putString("serv_longitude", String.valueOf(longitude));
//                    editor1.putString("typeservice", "device");
//                }
//
//                editor1.commit();
//
//                //            if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
//                //
//                //            }else{
//                //
//                //            }
//            } else {
//
//                ((HomeScreen) getActivity()).openLocationPopup();
//
//            }
//        }
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String lat = pref.getString("latitude", "");
        String longi = pref.getString("longitude", "");

//        if(pref.getString("type","").equals("")){
//
//        }

        if (!pref.getString("type", "").equals("entered") || pref.getString("type", "").equals("")) {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(getActivity(), Locale.getDefault());
            try {
//                if (!latitude.equals("0.0") && !longitude.equals("0.0")) {
                if (!lat.equals("") && !longi.equals("")) {
                    addresses = geocoder.getFromLocation(Double.valueOf(lat), Double.valueOf(longi), 1);
                    if (addresses.size() != 0) {// Here 1 represent max location result to returned, by documents it recommended 1 to 5
                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        if (address != null) {
                           // current.setText(address);
                        }
                    }

                } else {
                    //get Location
                    ((HomeScreen) getActivity()).openLocationPopup();
                    //  ((HomeScreen) getActivity()).noDataopenLocationPopup();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
          //  current.setText(pref.getString("currentloctext", ""));
        }

//        getChildFragmentManager()
//                .beginTransaction()
//                .replace(
//                        R.id.screen_viewpager,
//                        new LocalStore())
//                .commit();


    }
}