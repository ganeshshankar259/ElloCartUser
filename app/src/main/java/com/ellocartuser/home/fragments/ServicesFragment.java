package com.ellocartuser.home.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CategoriesResponce;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.home.adapterandmodel.PlaceAutoSuggestAdapter;
import com.ellocartuser.home.adapterandmodel.ServicesAdapter;
import com.ellocartuser.servicesscreens.SubCategoryItems;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesFragment extends Fragment implements ServicesAdapter.OnItemClickeGrid {
    String latitude=null,longitude=null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ServicesAdapter.OnItemClickeGrid onItemClickeService;
    AutoCompleteTextView nearlocationauto;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView offerslist, services;
    public ServicesFragment() {
        // Required empty public constructor
    }
    List<Categories>  gridedataserv;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesFragment newInstance(String param1, String param2) {
        ServicesFragment fragment = new ServicesFragment();
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
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        services=view.findViewById(R.id.services);
        onItemClickeService=this;
        services.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        services.setNestedScrollingEnabled(true);
        nearlocationauto=view.findViewById(R.id.nearlocationauto);


        nearlocationauto.setAdapter(new PlaceAutoSuggestAdapter(getActivity(), android.R.layout.simple_list_item_1));

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
                    servicecategoryApilatlong(latitude,longitude);
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

        services.setHasFixedSize(true);
        services.setItemAnimator(null);
        services.setItemViewCacheSize(50);

        servicecategoryApi();
        return view;
    }


    private android.location.Address getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getActivity());
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

    public void servicecategoryApi() {
        gridedataserv = new ArrayList<>();
        ProgressDialog  pd1 = new ProgressDialog(getActivity());
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        // pd1.show();
//
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");
        String lat = pref.getString("latitude", "");
        String longi = pref.getString("longitude", "");

        Call<CategoriesResponce> getCate = ApiClient.getApiServiceforservice().getHomeService("home", lat, longi);
        getCate.enqueue(new Callback<CategoriesResponce>() {
            @Override
            public void onResponse(Call<CategoriesResponce> call, Response<CategoriesResponce> response) {
                final CategoriesResponce resource = response.body();

                pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    if (resource.getCategories().size() == 0) {
                       // servicestrip.setVisibility(View.GONE);
                        services.setVisibility(View.GONE);
                    } else {
                        //    categories_clr
//                        servicestrip.setBackgroundColor(Color.parseColor(resource.getCategories_clr().toString().trim()));
//                        services.setBackgroundColor(Color.parseColor(resource.getCategories_clr().toString().trim()));

                     //   servicestrip.setVisibility(View.VISIBLE);
                        services.setVisibility(View.VISIBLE);
                    }
                    if (resource.getCategories().size() == 0) {
                       // servicestrip.setVisibility(View.GONE);
                        //featuredstores.setVisibility(View.GONE);
                    }
                    for (int i = 0; i < resource.getCategories().size(); i++) {
                        gridedataserv.add(resource.getCategories().get(i));
                    }
                    if (getContext() != null) {
                        ServicesAdapter apter = new ServicesAdapter(getContext(), gridedataserv, onItemClickeService, R.layout.service_single_vertical,"");
                        services.setAdapter(apter);
                    }

                } else {
                   // servicestrip.setVisibility(View.GONE);
                    services.setVisibility(View.GONE);

                    //   featuredstores.setVisibility(View.GONE);
//                    if (resource.getMessage() != "") {
//                        //   Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<CategoriesResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void servicecategoryApilatlong(String lat1,String longi1) {
        gridedataserv = new ArrayList<>();
        ProgressDialog  pd1 = new ProgressDialog(getActivity());
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        // pd1.show();
//
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");
        String lat = lat1;
        String longi = longi1;

        Call<CategoriesResponce> getCate = ApiClient.getApiServiceforservice().getHomeService("home", lat, longi);
        getCate.enqueue(new Callback<CategoriesResponce>() {
            @Override
            public void onResponse(Call<CategoriesResponce> call, Response<CategoriesResponce> response) {
                final CategoriesResponce resource = response.body();

                pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    if (resource.getCategories().size() == 0) {
                        // servicestrip.setVisibility(View.GONE);
                        services.setVisibility(View.GONE);
                    } else {
                        //    categories_clr
//                        servicestrip.setBackgroundColor(Color.parseColor(resource.getCategories_clr().toString().trim()));
//                        services.setBackgroundColor(Color.parseColor(resource.getCategories_clr().toString().trim()));

                        //   servicestrip.setVisibility(View.VISIBLE);
                        services.setVisibility(View.VISIBLE);
                    }
                    if (resource.getCategories().size() == 0) {
                        // servicestrip.setVisibility(View.GONE);
                        //featuredstores.setVisibility(View.GONE);
                    }
                    for (int i = 0; i < resource.getCategories().size(); i++) {
                        gridedataserv.add(resource.getCategories().get(i));
                    }
                    if (getContext() != null) {
                        ServicesAdapter apter = new ServicesAdapter(getContext(), gridedataserv, onItemClickeService, R.layout.service_single_vertical,"");
                        services.setAdapter(apter);
                    }

                } else {
                    // servicestrip.setVisibility(View.GONE);
                    services.setVisibility(View.GONE);

                    //   featuredstores.setVisibility(View.GONE);
//                    if (resource.getMessage() != "") {
//                        //   Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<CategoriesResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public void onItemClickservice(int position, String carid, String name) {
        SharedPreferences pref = getActivity()
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("user_id","346");
        editor.putBoolean("loadhome", true);
        editor.commit();

        Intent ii = new Intent(getActivity(), SubCategoryItems.class);
//        ii.putExtra("catid", carid);
//        ii.putExtra("name", name);
        startActivity(ii);

        SharedPreferences pref_services = getContext().getSharedPreferences("SERVICES_CATIDNAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorserv = pref_services.edit();
        //   editor.putString("boy",resource.getBoy());
        editorserv.putString("carid", carid);
        editorserv.putString("name", name);


        editorserv.commit();


    }


    private LatLng getLatLngFromAddress(String address) {

        Geocoder geocoder = new Geocoder(getActivity());
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