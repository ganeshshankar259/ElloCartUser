package com.ellocartuser.ellorooms_new;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.ElloRoomFilterResponse;
import com.ellocartuser.cart.CheckoutNext;
import com.ellocartuser.ellorooms_new.adapter.HomeDisplayAdapter;
import com.ellocartuser.home.adapterandmodel.PlaceAutoSuggestAdapter;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ElloRoomsHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ElloRoomsHome extends Fragment implements HomeDisplayAdapter.OnItemClickedCart{
    HomeDisplayAdapter.OnItemClickedCart onclick;
    RecyclerView list;
    Button search_card;
    DatePickerDialog.OnDateSetListener date,date2;
    Calendar myCalendar,myCalendar2;
    EditText todate,fromdate;
    TextView room_count,person_count;
    AutoCompleteTextView autoCompleteTextView;
    ImageView room_minus,room_plus,person_minus,person_plus;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ProgressDialog pd;
    public ElloRoomsHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ElloRoomsHome.
     */
    // TODO: Rename and change types and number of parameters
    public static ElloRoomsHome newInstance(String param1, String param2) {
        ElloRoomsHome fragment = new ElloRoomsHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        View view = inflater.inflate(R.layout.fragment_ello_rooms_home, container, false);
        onclick =this;
        todate=view.findViewById(R.id.todate);
        fromdate=view.findViewById(R.id.fromdate);
        list=view.findViewById(R.id.list);
        search_card=view.findViewById(R.id.search_card);
        room_minus=view.findViewById(R.id.room_minus);
        room_plus=view.findViewById(R.id.room_plus);
        person_minus=view.findViewById(R.id.person_minus);
        person_plus=view.findViewById(R.id.person_plus);
        person_count=view.findViewById(R.id.person_count);
        room_count=view.findViewById(R.id.room_count);

        list.setNestedScrollingEnabled(false);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        search_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii= new Intent(getActivity(),ElloRoomListPage.class);
                ii.putExtra("todate",todate.getText().toString());
                ii.putExtra("fromdate",fromdate.getText().toString());
                ii.putExtra("persons",person_count.getText().toString());
                ii.putExtra("rooms",room_count.getText().toString());
                getActivity().startActivity(ii);
            }
        });
        room_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                room_count.setText(String.valueOf(Integer.parseInt(room_count.getText().toString())+1));
            }
        });

        room_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!room_count.getText().toString().equals("0"))
                room_count.setText(String.valueOf(Integer.parseInt(room_count.getText().toString())-1));
            }
        });
        person_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_count.setText(String.valueOf(Integer.parseInt(person_count.getText().toString())+1));
            }
        });

        person_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!room_count.getText().toString().equals("0"))
                    person_count.setText(String.valueOf(Integer.parseInt(person_count.getText().toString())-1));
            }
        });

        //date
        myCalendar  = Calendar.getInstance();
        myCalendar2  = Calendar.getInstance();

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

        date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelto();
            }

        };

        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalendar
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

        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date2, myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH));
                long now = System.currentTimeMillis() - 1000;

                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH, +7);

                // datePickerDialog.getDatePicker().setMaxDate(now+(1000*60*60*24*7));
                datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                datePickerDialog.getDatePicker().setMinDate(now);
                datePickerDialog.show();
            }
        });

        //auto fill location
        autoCompleteTextView = view.findViewById(R.id.nearlocation);
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

                    SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    //   editor.putString("boy",resource.getBoy());
                    editor.putString("rooms_latitude", String.valueOf(latLng.latitude));
                    editor.putString("rooms_longitude", String.valueOf(latLng.longitude));

                    editor.commit();

                    apiCall(String.valueOf(latLng.latitude),String.valueOf(latLng.longitude));
                   // current.setText(autoCompleteTextView.getText().toString());

                    Address address = getAddressFromLatLng(latLng);
                    if (address != null) {

//                        SharedPreferences pref1 = getActivity()
//                                .getSharedPreferences("LOCALITY1", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor1 = pref1.edit();
//                        editor1.putString("locality",address.getLocality());
//                        //editor.putBoolean("loadhome", true);
//                        editor1.commit();

//                        SharedPreferences pref = getActivity().getSharedPreferences("LOCALITY", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = pref.edit();
//                        //   editor.putString("boy",resource.getBoy());
//                        editor.putString("locality", address.getLocality());
//                        editor.commit();


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

            }
        });

        apiCall("17.0005","81.8040");

        return view;

    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        fromdate.setText(sdf.format(myCalendar.getTime()));

    }

    private void updateLabelto() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        todate.setText(sdf.format(myCalendar2.getTime()));

    }

    private void apiCall(String lat,String longi) {

        pd = new ProgressDialog(getActivity());
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

        Call<ElloRoomFilterResponse> getCate = ApiClient.getApiServiceforRooms().get_rooms("list",lat,longi);
        getCate.enqueue(new Callback<ElloRoomFilterResponse>() {
            @Override
            public void onResponse(Call<ElloRoomFilterResponse> call, Response<ElloRoomFilterResponse> response) {
                final ElloRoomFilterResponse resource = response.body();
                pd.dismiss();
                //  Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){

                    HomeDisplayAdapter adapter = new HomeDisplayAdapter(getActivity(),resource.getRooms(),onclick);
                    list.setAdapter(adapter);

                }else {

                }

            }

            @Override
            public void onFailure(Call<ElloRoomFilterResponse> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();


            }
        });

    }

    @Override
    public void onItemClicked(int position, String mParam1, String mParam2) {

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
}