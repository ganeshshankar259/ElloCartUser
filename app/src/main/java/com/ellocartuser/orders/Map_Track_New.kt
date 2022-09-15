package com.ellocartuser.orders

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ellocartuser.R
import com.ellocartuser.apiservices.ApiClient
import com.ellocartuser.apiservices.Responce.LocationResponce
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.mindorks.example.ubercaranimation.util.AnimationUtils
import com.mindorks.example.ubercaranimation.util.MapUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Map_Track_New : Fragment() {

    private lateinit var googleMap: GoogleMap
    private lateinit var defaultLocation: LatLng
    private var originMarker: Marker? = null
    private var destinationMarker: Marker? = null
    private var grayPolyline: Polyline? = null
    private var blackPolyline: Polyline? = null
    private var movingCabMarker: Marker? = null
    private var previousLatLng: LatLng? = null
    private var currentLatLng: LatLng? = null
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    var orderid:String = ""
    var userid:String = ""

    var runnableCode: Runnable? = null
    val ARG_PARAM1 = "parem"
    val ARG_PARAM2 = "parem2"


    fun newInstance(param1: String?, param2: String?): Map_Track_New? {
        val args = Bundle()
        val fragment = Map_Track_New()
        args.putString(ARG_PARAM1, param1)
        args.putString(ARG_PARAM2, param2)
        fragment.arguments = args
        return fragment
    }


    private val callback = OnMapReadyCallback { googleMap ->

        this.googleMap=googleMap

        defaultLocation = LatLng(28.435350000000003, 77.11368)
        showDefaultLocationOnMap(defaultLocation)

        Handler().postDelayed(Runnable {
            showPath(MapUtils.getListOfLocations())
            showMovingCab1(MapUtils.getListOfLocations())
        }, 3000)

//        Handler().postDelayed(Runnable {
//           apiCall()
//
//       //     showPath(MapUtils.getListOfLocations())
//       //     showMovingCab(MapUtils.getListOfLocations())
//        }, 3000)

    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

//        if(getActivity()==null){
//            return;
//        }
//       pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        if (arguments != null) {
            orderid = "7009"//requireArguments()!!.getString(MapsFragment.ARG_PARAM1).toString()
            userid =  "7539"//requireArguments()!!.getString(MapsFragment.ARG_PARAM2).toString()
        }
        return inflater.inflate(R.layout.fragment_map__track__new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }


    private fun moveCamera(latLng: LatLng) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }

    private fun animateCamera(latLng: LatLng) {
        val cameraPosition = CameraPosition.Builder().target(latLng).zoom(15.0f).build()
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    private fun addCarMarkerAndGet(latLng: LatLng): Marker {
        val bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(MapUtils.getCarBitmap(requireContext()))
        return googleMap.addMarker(
                MarkerOptions().position(latLng).flat(true).icon(bitmapDescriptor)
        )!!
    }

    private fun addOriginDestinationMarkerAndGet(latLng: LatLng): Marker {
        val bitmapDescriptor =
                BitmapDescriptorFactory.fromBitmap(MapUtils.getOriginDestinationMarkerBitmap())
        return googleMap.addMarker(
                MarkerOptions().position(latLng).flat(true).icon(bitmapDescriptor)
        )!!
    }

    private fun showDefaultLocationOnMap(latLng: LatLng) {
        moveCamera(latLng)
        animateCamera(latLng)
    }

    /**
     * This function is used to draw the path between the Origin and Destination.
     */
    private fun showPath(latLngList: ArrayList<LatLng>) {
        val builder = LatLngBounds.Builder()
        for (latLng in latLngList) {
            builder.include(latLng)
        }
        val bounds = builder.build()
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 2))

        val polylineOptions = PolylineOptions()
        polylineOptions.color(Color.GRAY)
        polylineOptions.width(5f)
        polylineOptions.addAll(latLngList)
        grayPolyline = googleMap.addPolyline(polylineOptions)

        val blackPolylineOptions = PolylineOptions()
        blackPolylineOptions.color(Color.BLACK)
        blackPolylineOptions.width(5f)
        blackPolyline = googleMap.addPolyline(blackPolylineOptions)

        originMarker = addOriginDestinationMarkerAndGet(latLngList[0])
        originMarker?.setAnchor(0.5f, 0.5f)
        destinationMarker = addOriginDestinationMarkerAndGet(latLngList[latLngList.size - 1])
        destinationMarker?.setAnchor(0.5f, 0.5f)

        val polylineAnimator = AnimationUtils.polylineAnimator()
        polylineAnimator.addUpdateListener { valueAnimator ->
            val percentValue = (valueAnimator.animatedValue as Int)
            val index = (grayPolyline?.points!!.size) * (percentValue / 100.0f).toInt()
            blackPolyline?.points = grayPolyline?.points!!.subList(0, index)
        }
        polylineAnimator.start()
    }

    /**
     * This function is used to update the location of the Cab while moving from Origin to Destination
     */

    private fun updateCarLocation(latLng: LatLng) {
        if(movingCabMarker == null) {
            movingCabMarker = addCarMarkerAndGet(latLng)
        }
        if(previousLatLng == null) {
            currentLatLng = latLng
            previousLatLng = currentLatLng
            movingCabMarker?.position = currentLatLng as LatLng
            movingCabMarker?.setAnchor(0.5f, 0.5f)
            animateCamera(currentLatLng!!)
        } else {
            previousLatLng = currentLatLng
            currentLatLng = latLng
            val valueAnimator = AnimationUtils.carAnimator()
            valueAnimator.addUpdateListener { va ->
                if (currentLatLng != null && previousLatLng != null) {
                    val multiplier = va.animatedFraction
                    val nextLocation = LatLng(
                            multiplier * currentLatLng!!.latitude + (1 - multiplier) * previousLatLng!!.latitude,
                            multiplier * currentLatLng!!.longitude + (1 - multiplier) * previousLatLng!!.longitude
                    )
                    movingCabMarker?.position = nextLocation
                    val rotation = MapUtils.getRotation(previousLatLng!!, nextLocation)
                    if (!rotation.isNaN()) {
                        movingCabMarker?.rotation = rotation
                    }
                    movingCabMarker?.setAnchor(0.5f, 0.5f)
                    animateCamera(nextLocation)
                }
            }
            valueAnimator.start()
        }
    }

    private fun showMovingCab(latLng: LatLng) {
        handler = Handler()
        var index = 0
        runnable = Runnable {
            run {
                    updateCarLocation(latLng)
                    handler.postDelayed(runnable, 3000)

                    handler.removeCallbacks(runnable)
//                    Toast.makeText(activity, "Trip Ends", Toast.LENGTH_LONG).show()

            }
        }
        handler.postDelayed(runnable, 5000)
    }

    private fun showMovingCab1(cabLatLngList: ArrayList<LatLng>) {
        handler = Handler()
        var index = 0
        runnable = Runnable {
            run {
                if (index < 10) {
                    updateCarLocation(cabLatLngList[index])
                    handler.postDelayed(runnable, 3000)
                    ++index
                } else {
                    handler.removeCallbacks(runnable)
                    Toast.makeText(activity, "Trip Ends", Toast.LENGTH_LONG).show()
                }
            }
        }
        handler.postDelayed(runnable, 5000)
    }

    private fun apiCall() {

        //  String id = pref.getString("user_id","");
        val getCate = ApiClient.getApiService().getLocationData("track", userid, orderid)
        getCate.enqueue(object : Callback<LocationResponce?> {
            override fun onResponse(call: Call<LocationResponce?>, response: Response<LocationResponce?>) {
                val resource = response.body() ?: return
                //  pd.dismiss();
                //    Log.d("resss",resource.toString());
                //      setMapData("17.024532","81.800118","17.0239761","81.7925818");
                if (resource.getStatus() == "ok") {

                    setMapData(resource.getSeller_lat(), resource.getSeller_long(), resource.getAddrLat(), resource.getAddrLong())

                    if (resource.getOrder_assign() == "3") {
                        val driver = LatLng(java.lang.Double.valueOf(resource.getBoy_lat()), java.lang.Double.valueOf(resource.getBoy_long()))
//                        showMarker(driver)
//                        animateCamera(driver)

                           showMovingCab(driver)

                    }



                } else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }
            }

            override fun onFailure(call: Call<LocationResponce?>, t: Throwable) {
                //   pd.dismiss();
                //   pd.dismiss();
                t.printStackTrace()
            }
        })
    }

    private fun setMapData(sellerlat: String, sellerlongi: String, addrlat: String, addrlong: String) {

        val builder = LatLngBounds.Builder()
        val driver = LatLng(java.lang.Double.valueOf(sellerlat), java.lang.Double.valueOf(sellerlongi))
        googleMap.addMarker(MarkerOptions().position(driver).icon(BitmapDescriptorFactory.fromResource(R.drawable.seller_3x)))
        builder.include(driver)
        val user = LatLng(java.lang.Double.valueOf(addrlat), java.lang.Double.valueOf(addrlong))
        //  mMap.addMarker(new MarkerOptions().position(user).title("User Location"));
        //  mMap.addMarker(new MarkerOptions().position(user).title("User Location"));
        googleMap.addMarker(MarkerOptions().position(user).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_3x)))
        builder.include(user)

        googleMap.getUiSettings().setZoomControlsEnabled(true)
        val bounds = builder.build()

     //   googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 140))

        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 140));

       // val cameraPosition = CameraPosition.Builder().target(latLng).zoom(15.0f).build()
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 140))

        googleMap.setMinZoomPreference(12f)

//path
        val locationList = ArrayList<LatLng>()
        locationList.add( LatLng(java.lang.Double.valueOf(sellerlat), java.lang.Double.valueOf(sellerlongi)))
        locationList.add( LatLng(java.lang.Double.valueOf(addrlat), java.lang.Double.valueOf(addrlong)))
        showPath(locationList)


    }
}