package com.ellocartuser.tutions;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.ellocartuser.Login;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.servicesscreens.PostDetailedPage;
import com.ellocartuser.servicesscreens.Servicesdisplaylist;
import com.ellocartuser.tutions.tutmodelsandresponces.DateNewAdapter;
import com.ellocartuser.tutions.tutmodelsandresponces.DateNewModel;
import com.ellocartuser.tutions.tutmodelsandresponces.Dates_Adapter_TUT;
import com.ellocartuser.tutions.tutmodelsandresponces.NewDates_Responce;
import com.ellocartuser.tutions.tutmodelsandresponces.TAppoint_Responce;
import com.ellocartuser.tutions.tutmodelsandresponces.TDTimes_Responce;
import com.ellocartuser.tutions.tutmodelsandresponces.TDates_M;
import com.ellocartuser.tutions.tutmodelsandresponces.TDates_Responce;
import com.ellocartuser.tutions.tutmodelsandresponces.TTime_M;
import com.ellocartuser.tutions.tutmodelsandresponces.TimeNewAdapter;
import com.ellocartuser.tutions.tutmodelsandresponces.TimeNewModel;
import com.ellocartuser.tutions.tutmodelsandresponces.Times_Adapter;
import com.ellocartuser.utils.Constant;
import com.ellocartuser.utils.CustomItemFourListener;
import com.ellocartuser.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Tut_Appoint_Fragment extends Fragment implements EasyPermissions.PermissionCallbacks  {
    private static final String TAG = "Tut_Appoint_Fragment";

    TextView doc_name,doc_edu,doc_price,txt_dateid,txt_timeid,txt_title;
    public String d_name,d_image,d_edu,d_price,select_date,select_time,d_special,doc_id;
    ImageView doc_image;
    Button btn_send;
    ProgressDialog pd1,pd2;
    Dates_Adapter_TUT adapter;
    Dates_Adapter_TUT.OnItemClickedproduct onclick;
    List<TDates_M> datalist=new ArrayList<>();
    LinearLayout linearLayout_fordata;
    Times_Adapter adapter1;
    Times_Adapter.OnItemClickedtimes onclick1;
    List<TTime_M> datalist1=new ArrayList<>();
    LinearLayoutManager HorizontalLayout;
    TextView bookappointment;
    String post_id,title_tut,image,tut_id;
    ImageView image_t;
    Dialog dialogappoint,dialogsuccess,dialogfailed;
    CardView card122;
    AlertDialog.Builder builder;


    //latest Dates list
    //date list
    TextView txt_no_data_dates;
    RecyclerView recyclerview;
    private DateNewAdapter homeCategoryAdapter;
    private RecyclerView.LayoutManager categorylayoutManagertrending;
    private ArrayList<DateNewModel> category_data_list;

    //time list
    String phn_num="";
    TextView txt_no_data_time;
    RecyclerView recylerview_time;
    private TimeNewAdapter timehomeCategoryAdapter;
    private ArrayList<TimeNewModel> timecategory_data_list;

ConstraintLayout phn_lay;
AppCompatButton phonenumber;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tut_Appoint_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tut_Appoint_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tut_Appoint_Fragment newInstance(String param1, String param2) {
        Tut_Appoint_Fragment fragment = new Tut_Appoint_Fragment();
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
        //return inflater.inflate(R.layout.fragment_tut_appoint, container, false);

        View view=inflater.inflate(R.layout.fragment_tut_appoint, container, false);
        // return inflater.inflate(R.layout.fragment_gm_appoint, container, false);
        // Inflate the layout for this fragment

        SharedPreferences pref = getActivity().getSharedPreferences("SERVICES_DATA", Context.MODE_PRIVATE);
        post_id = pref.getString("post_id", "");
        title_tut = pref.getString("title", "");
        image = pref.getString("image", "");
        tut_id = pref.getString("tut_id", "");

        builder = new AlertDialog.Builder(getContext());

        phn_lay = view.findViewById(R.id.constraintLayout8);
        phonenumber = view.findViewById(R.id.phonenumber);
        card122 = view.findViewById(R.id.card122);
        //card122.setVisibility(View.INVISIBLE);

        txt_title = view.findViewById(R.id.title);
        image_t = view.findViewById(R.id.image_tut);

        txt_title.setText(title_tut);

        Glide.with(view)
                .load(image)
                .fitCenter().placeholder(R.drawable.placeholderello)
                .into(image_t);

        ImageView backbutton = view.findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
               // Util.loadFragment(new doctorsprofileslist_fragment(),getActivity(), Tut_Appoint_Fragment.this);
//                Intent intent = new Intent(getContext(), Servicesdisplaylist.class);
////                intent.putExtra("type","TUTAPPOINT");
//                startActivity(intent);
            }
        });
        bookappointment = view.findViewById(R.id.txt_addappointment);
        bookappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select_time ==null) {
                    Toast.makeText(getContext(),"Please Select Time : ", Toast.LENGTH_SHORT).show();
                }else
                {
                    SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                    String id = pref.getString("user_id","");


                    if(id==null || id.equals("")){
                        pleaselogin();
                        return;
                    }else {
                        apiCall_for_AptBooking();
                    }
                }
            }
        });

        //Search list

        txt_no_data_dates = view.findViewById(R.id.txt_no_data_dates);
        recyclerview = view.findViewById(R.id.recylerview_date);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setHasFixedSize(true);
        categorylayoutManagertrending = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        category_data_list = new ArrayList<>();



        txt_no_data_time = view.findViewById(R.id.txt_no_data_time);
        recylerview_time = view.findViewById(R.id.recylerview_time);
        recylerview_time.setNestedScrollingEnabled(false);
        recylerview_time.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),3);
        recylerview_time.setLayoutManager(layoutManager);
        timecategory_data_list = new ArrayList<>();


        phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNumm(phn_num);
            }
        });

        apiCallDate();

        return view;

    }

    @AfterPermissionGranted(345)
    public void callNumm(String num){
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(getActivity(), perms)) {

            if (ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            if (num != null) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
                startActivity(intent);
            }
        } else {
            EasyPermissions.requestPermissions(getActivity(), "We need permission to make call ", 345, perms);
        }

    }

    private void apiCallDate() {
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

        txt_no_data_dates.setVisibility(View.GONE);
        recyclerview.setVisibility(View.VISIBLE);

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL1+"get_post_detail", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd1.dismiss();
                category_data_list.clear();
                Log.e(TAG, "onResponse:post_id_response "+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equalsIgnoreCase("ok")){
                        JSONArray jsonArray=jsonObject.getJSONArray("post_date_times");
                        JSONArray jsonArray2=jsonObject.getJSONArray("servsubcategories");

                        phn_num = jsonObject.getString("user_phone");

                        if (jsonArray.length()==0){
                            txt_no_data_dates.setVisibility(View.VISIBLE);
                            recyclerview.setVisibility(View.GONE);
                        }else {
                            txt_no_data_dates.setVisibility(View.GONE);
                            recyclerview.setVisibility(View.VISIBLE);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                String date = jsonObject2.getString("date");


                                DateNewModel cartModel = new DateNewModel();
                                cartModel.setDate(date);
                                category_data_list.add(cartModel);
                            }

                        }
                    }else {
                        txt_no_data_dates.setVisibility(View.VISIBLE);
                        recyclerview.setVisibility(View.GONE);
                    }
                    homeCategoryAdapter=new DateNewAdapter(category_data_list, getActivity(),new CustomItemFourListener() {
                        @Override
                        public void onItemClick(View v, String getDate,String Product_id,String Productname,String type) {
                            select_date = getDate;
                            apiCalltime();
                        }
                    });
                    recyclerview.setAdapter(homeCategoryAdapter);
//                    if(category_data_list.size()>0) {
//                        recyclerview.findViewHolderForAdapterPosition(0).itemView.performClick();
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txt_no_data_dates.setVisibility(View.VISIBLE);
                recyclerview.setVisibility(View.GONE);
                pd1.dismiss();
                Log.e(TAG, "onErrorResponse:error"+error );
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("post_id",post_id);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                999999990,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }


    private void apiCalltime() {
        card122.setVisibility(View.GONE);
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

        txt_no_data_time.setVisibility(View.GONE);
        recylerview_time.setVisibility(View.VISIBLE);

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Base_URL1+"get_post_detail_times", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd1.dismiss();
                timecategory_data_list.clear();
                Log.e(TAG, "onResponse:time_re "+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equalsIgnoreCase("ok")){
                        JSONArray jsonArray=jsonObject.getJSONArray("times");
                        if (jsonArray.length()==0){
                            txt_no_data_time.setVisibility(View.VISIBLE);
                            recylerview_time.setVisibility(View.GONE);
                        }else {
                            txt_no_data_time.setVisibility(View.GONE);
                            recylerview_time.setVisibility(View.VISIBLE);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                String id = jsonObject2.getString("id");
                                String time = jsonObject2.getString("time");
                                String status1 = jsonObject2.getString("status");

                                TimeNewModel cartModel = new TimeNewModel();
                                cartModel.setId(id);
                                cartModel.setTime(time);
                                cartModel.setStatus(status1);
                                timecategory_data_list.add(cartModel);
                            }
                        }
                    }else {

                    }
                    timehomeCategoryAdapter=new TimeNewAdapter(timecategory_data_list, getActivity(),new CustomItemFourListener() {
                        @Override
                        public void onItemClick(View v, String getId,String getTime,String getStatus,String type) {
                            select_time = getId;
                            if (getStatus.equalsIgnoreCase("1")){
                                card122.setVisibility(View.GONE);
                            }else {
                                card122.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                    recylerview_time.setAdapter(timehomeCategoryAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txt_no_data_time.setVisibility(View.VISIBLE);
                recylerview_time.setVisibility(View.GONE);
                pd1.dismiss();
                Log.e(TAG, "onErrorResponse:error"+error );
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("post_id", post_id);
                params.put("date", select_date);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                999999990,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }


    private void apiCall_for_AptBooking() {
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

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String user_id = pref.getString("user_id","");


        Call<TAppoint_Responce> getCate = ApiClient.getApiServiceforservice().check_booking_availability(post_id,select_time,user_id,tut_id,select_date);
        getCate.enqueue(new Callback<TAppoint_Responce>() {

            @Override
            public void onResponse(Call<TAppoint_Responce> call, Response<TAppoint_Responce> response) {
                final TAppoint_Responce resource = response.body();
                pd1.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    //catstore
                    //store_o=resource.getdSpecialid();
//                    datalist=resource.getDates();
                    //Toast.makeText(getActivity(), resource.getMsg(), Toast.LENGTH_LONG).show();
                   // showDialog_ok(getActivity());
                    Custom_showDialog_success(getActivity());

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

//                                adapter.updateList(resource.getDates());
                                //adapter.updateList(resource.getProduct());
                                //  recyclerView.setAdapter(adapter);
                            }
                        });

                    }

                } else {

                    if (resource.getStatus() != "") {
                        //Toast.makeText(getActivity(), resource.getMsg(), Toast.LENGTH_LONG).show();
                        //Toast.makeText(getActivity(), "Service not avialble", Toast.LENGTH_LONG).show();
                        Custom_showDialog_failed(getActivity());
                    }
                }

            }

            @Override
            public void onFailure(Call<TAppoint_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void showDialog(Activity activity) {
        dialogappoint = new Dialog(activity);
        dialogappoint.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogappoint.setCancelable(true);
        dialogappoint.setContentView(R.layout.dialog_for_tut);

        TextView close = dialogappoint.findViewById(R.id.txt_close);
        TextView txt_date = dialogappoint.findViewById(R.id.txt_date);
        TextView txt_time = dialogappoint.findViewById(R.id.txt_time);
        TextView txt_submit = dialogappoint.findViewById(R.id.txt_submit);

        txt_date.setText(select_date);
        txt_time.setText(select_time);

        txt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                String id = pref.getString("user_id","");


                if(id==null || id.equals("")){
                    pleaselogin();
                    return;
                }else {
                    apiCall_for_AptBooking();
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogappoint.dismiss();
            }
        });
        dialogappoint.show();
    }

    public void showDialog_ok(Activity activity) {
        //Uncomment the below code to Set the message and title from the strings.xml file
//        String message = "HELLO";
//        String title = "HAI";
//        builder.setMessage(message) .setTitle(title);

        //Setting message manually and performing action on button click
        builder.setMessage("Appointment Booked Successfully")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Util.loadFragment(new homefragment(),getActivity(), Tut_Appoint_Fragment.this);

                        //finish();
//                        Toast.makeText(getContext(),"you choose yes action for alertbox",
//                                Toast.LENGTH_SHORT).show();
                    }
                });
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        //  Action for 'NO' Button
//                        dialog.cancel();
//                        Toast.makeText(getContext(),"you choose no action for alertbox",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        //alert.setTitle("AlertDialogExample");
        alert.show();
    }

    public void showDialog_error(Activity activity) {
        //Uncomment the below code to Set the message and title from the strings.xml file
//        String message = "HELLO";
//        String title = "HAI";
//        builder.setMessage(message) .setTitle(title);

        //Setting message manually and performing action on button click
        builder.setMessage("Service not available")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //finish();
//                        Toast.makeText(getContext(),"you choose yes action for alertbox",
//                                Toast.LENGTH_SHORT).show();
                    }
                });
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        //  Action for 'NO' Button
//                        dialog.cancel();
//                        Toast.makeText(getContext(),"you choose no action for alertbox",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        //alert.setTitle("AlertDialogExample");
        alert.show();
    }


    public void Custom_showDialog_success(Activity activity) {
        dialogsuccess = new Dialog(activity);
        dialogsuccess.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogsuccess.setCancelable(true);
        dialogsuccess.setContentView(R.layout.dialog_for_success);
        dialogsuccess.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView ok_btn = dialogsuccess.findViewById(R.id.ok_btn);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // dialogsuccess.dismiss();
                Util.loadFragment(new homefragment(),getActivity(), Tut_Appoint_Fragment.this);
                 dialogsuccess.dismiss();
            }
        });
        dialogsuccess.show();
    }

    public void Custom_showDialog_failed(Activity activity) {
        dialogfailed = new Dialog(activity);
        dialogfailed.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogfailed.setCancelable(true);
        dialogfailed.setContentView(R.layout.dialog_for_failed);
        dialogfailed.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView ok_btn = dialogfailed.findViewById(R.id.ok_btn);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogfailed.dismiss();
            }
        });
        dialogfailed.show();
    }


    public void pleaselogin(){
        try {
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
            builder.setMessage("Please Login to Add Products to cart");
            builder.setCancelable(false);

            builder.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Util.loadhome(getActivity());
                            Intent ii=new Intent(getActivity(), Login.class);
                            ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(ii);
                        }
                    });
            builder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
//                                    Intent ii=new Intent(getActivity(), HomeScreen.class);
//                                    ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(ii);
                        }
                    });
            androidx.appcompat.app.AlertDialog alert = builder.create();
            alert.show();

        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}