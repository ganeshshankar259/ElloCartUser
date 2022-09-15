package com.ellocartuser.home.homefragment.shopinfotab;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.LocationResponce;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsFragmentShopInfo extends Fragment {
    SharedPreferences pref;
    boolean first=true;
    boolean first1=true;
    private GoogleMap mMap;
    ProgressDialog pd;
    String orderid="",userid="";
    String userlat="",userlong="";
    String sellerlat="",sellerlong="";
    Handler handler;
    Runnable runnableCode;
    public static final String  ARG_PARAM1 = "parem";
    public static final String  ARG_PARAM2 = "parem2";
    public static final String  ARG_PARAM3 = "parem3";
    public static final String  ARG_PARAM4 = "parem4";
    final int delay = 6000; // 1000 milliseconds == 1 second
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
//        @Override
//        public void onMapReady(GoogleMap googleMap) {
//            LatLng sydney = new LatLng(-34, 151);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        }


        private String TAG = "so47492459";

        @Override
        public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;

            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setZoomGesturesEnabled(false);
            mMap.getUiSettings().setCompassEnabled(false);
            mMap.getUiSettings().setScrollGesturesEnabled(false);
            mMap.getUiSettings().setScrollGesturesEnabledDuringRotateOrZoom(false);
        //    mMap.setMyLocationEnabled(false);
            mMap.animateCamera(CameraUpdateFactory.zoomTo(8f));

            mMap.getUiSettings().setZoomControlsEnabled(false);

            MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.mapstyle_night);
            mMap.setMapStyle(style);



//            MapFragment mapFragment = MapFragment.newInstance(
//                    new GoogleMapOptions()
//                            .mapId(getResources().getString(R.string.map_id)));

            setMapData(sellerlat,sellerlong,userlat,userlong);
//            apiCall();
//
//           handler = new Handler();
//
//            // Define the task to be run here
//            runnableCode = new Runnable() {
//                @Override
//                public void run() {
//
//                  //  System.out.println("myHandler: here!"); // Do your work here
//                    apiCall();
//                    handler.postDelayed(runnableCode, delay);
//                }
//            };
//
//            handler.post(runnableCode);


        }

    };

    public static MapsFragmentShopInfo newInstance(String param1, String param2,String param3, String param4) {

        Bundle args = new Bundle();

        MapsFragmentShopInfo fragment = new MapsFragmentShopInfo();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        if(getActivity()==null){
//            return;
//        }
//       pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        if (getArguments() != null) {
            userlat = getArguments().getString(ARG_PARAM1);
            userlong = getArguments().getString(ARG_PARAM2);
            sellerlat = getArguments().getString(ARG_PARAM3);
            sellerlong = getArguments().getString(ARG_PARAM4);
        }


        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void setMapData(String sellerlat,String sellerlongi,String userlat,String userlongi){
       mMap.clear();

        LatLng driver = new LatLng(Double.valueOf(sellerlat),Double.valueOf(sellerlongi));
        mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromResource(R.drawable.seller_3x)));

//        LatLng user = new LatLng(Double.valueOf(userlat),Double.valueOf(userlongi));
//        //  mMap.addMarker(new MarkerOptions().position(user).title("User Location"));
//        mMap.addMarker(new MarkerOptions().position(user).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_3x)));

        LatLng zaragoza = new LatLng(Double.valueOf(sellerlat),Double.valueOf(sellerlongi));


//        //Define list to get all latlng for the route
//        List<LatLng> path = new ArrayList();
//
//        //Execute Directions API request
//        GeoApiContext context = new GeoApiContext.Builder()
//                .apiKey("")
//                .build();
//        DirectionsApiRequest req = DirectionsApi.getDirections(context, sellerlat+","+sellerlongi, userlat+","+userlongi);
//        try {
//            DirectionsResult res = req.await();
//
//            //Loop through legs and steps to get encoded polylines of each step
//            if (res.routes != null && res.routes.length > 0) {
//                DirectionsRoute route = res.routes[0];
//
//                if (route.legs != null) {
//                    for (int i = 0; i < route.legs.length; i++) {
//                        DirectionsLeg leg = route.legs[i];
//                        if (leg.steps != null) {
//                            for (int j = 0; j < leg.steps.length; j++) {
//                                DirectionsStep step = leg.steps[j];
//                                if (step.steps != null && step.steps.length > 0) {
//                                    for (int k = 0; k < step.steps.length; k++) {
//                                        DirectionsStep step1 = step.steps[k];
//                                        EncodedPolyline points1 = step1.polyline;
//                                        if (points1 != null) {
//                                            //Decode polyline and add points to list of route coordinates
//                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
//                                            for (com.google.maps.model.LatLng coord1 : coords1) {
//                                                path.add(new LatLng(coord1.lat, coord1.lng));
//                                            }
//                                        }
//                                    }
//                                } else {
//                                    EncodedPolyline points = step.polyline;
//                                    if (points != null) {
//                                        //Decode polyline and add points to list of route coordinates
//                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
//                                        for (com.google.maps.model.LatLng coord : coords) {
//                                            path.add(new LatLng(coord.lat, coord.lng));
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            //Draw the polyline
//            if (path.size() > 0) {
//                PolylineOptions opts = new PolylineOptions().addAll(path).color(getResources().getColor(R.color.yellow)).width(5);
//                mMap.addPolyline(opts);
//            }

            if (first1) {
                first1 = false;
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(zaragoza, 15));
            }

//        catch(Exception ex) {
//            Log.e("error map ", ex.getLocalizedMessage());
//        }

    }

    private void apiCall() {
        if(first) {
//            pd = new ProgressDialog(getActivity());
//            pd.setMessage("Loading...");
//            //   pd.setProgressStyle(R.style.ProgressBar);
//            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//             pd.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//            pd.setCancelable(false);
            // pd.show();
        }
        //  SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code


      //  String id = pref.getString("user_id","");
        Call<LocationResponce> getCate = ApiClient.getApiService().getLocationData("track",userid,orderid);
        getCate.enqueue(new Callback<LocationResponce>() {
            @Override
            public void onResponse(Call<LocationResponce> call, Response<LocationResponce> response) {
                final LocationResponce resource = response.body();
              //  pd.dismiss();
                //    Log.d("resss",resource.toString());
                //      setMapData("17.024532","81.800118","17.0239761","81.7925818");

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){

                    if(first){
                        first=false;
                    }

                    setMapData(resource.getSeller_lat(),resource.getSeller_long(),resource.getAddrLat(),resource.getAddrLong());

                    if(resource.getOrder_assign().equals("3")) {
                        LatLng driver = new LatLng(Double.valueOf(resource.getBoy_lat()), Double.valueOf(resource.getBoy_long()));
                        mMap.addMarker(new MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromResource(R.drawable.driver_ello_3x)));
                    }
                  //dummy values
                 //   setMapData("17.024532","81.800118","17.0239761","81.7925818");

                }else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<LocationResponce> call, Throwable t) {
                //   pd.dismiss();
             //   pd.dismiss();
                t.printStackTrace();

            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(handler==null) return;

        handler.removeCallbacks(runnableCode);

    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * Start the handler again if coming back from any profile page
         * or hashtag search
         */


    }

}