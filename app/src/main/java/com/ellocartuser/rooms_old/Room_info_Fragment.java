package com.ellocartuser.rooms_old;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.rooms_old.ModelsandResponces.Availdates_Responce;
import com.ellocartuser.rooms_old.ModelsandResponces.CartResponce_R;
import com.ellocartuser.rooms_old.ModelsandResponces.Dates_Adapter;
import com.ellocartuser.rooms_old.ModelsandResponces.Dates_M;
import com.ellocartuser.rooms_old.ModelsandResponces.Dates_Responce;
import com.ellocartuser.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Room_info_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Room_info_Fragment extends Fragment implements Dates_Adapter.OnItemClickedproduct{


    TextView roomtype,r_price,chcktime,checkin_time1,avail_rooms,checkout_time2,incriment,cartcount,txt_addroom,incriment_person;
    EditText edt_nofpersions;
    ImageView reportimage,plus,minus,imageback1,backbutton,plus_person,minus_person;
    String roomid,price,time,checkin,checkout,noofpersions,img,r_availrooms,hotelid;
    int count,countpersons;
    ProgressDialog pd1,pd2;
    public String  dateforcart;
    RecyclerView recyclerview;
    Dates_Adapter adapter;
    Dates_Adapter.OnItemClickedproduct onClick;
    List<Dates_M> datalist=new ArrayList<>();
    List<Availdates_Responce> datalist1=new ArrayList<>();
    Dialog dialogappoint,dialogsuccess,dialogfailed;
    public String count_type,uid;
    public String roomcatid;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "roomid1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String roomid1;
    private String mParam2;

    public Room_info_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param roomid1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Room_info_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Room_info_Fragment newInstance(String roomid1, String param2) {
        Room_info_Fragment fragment = new Room_info_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, roomid1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //roomid1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences pref = getActivity().getSharedPreferences("GET_ROOMINFO", Context.MODE_PRIVATE);
        roomid = pref.getString("r_id", "");

        count_type = "count";
        apiCallForCart();
        //apiCall();
    }

    private void apiCallfor_availrooms(String r_date,String room_id) {
        pd2 = new ProgressDialog(getActivity());
        pd2.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd2.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd2.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd2.setCancelable(false);
        pd2.show();

//        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");

        //String doc_id = "1";
        //String date_id = "1";

        Call<Availdates_Responce> getCate = ApiClient.getApiServiceforservice().avail_rooms(r_date,room_id);
        getCate.enqueue(new Callback<Availdates_Responce>() {
            @Override
            public void onResponse(Call<Availdates_Responce> call, Response<Availdates_Responce> response) {
                final Availdates_Responce resource = response.body();
                pd2.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    //catstore
                    //store_o=resource.getdSpecialid();
                    avail_rooms.setText(String.valueOf(resource.getRoomsAvialble()));

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

                                //adapter1.updateList(resource.getTimes());


                                //adapter.updateList(resource.getProduct());
                                //  recyclerView.setAdapter(adapter);
                            }
                        });

                    }

                } else {

                    //if (resource.getStatus() != "") {
                    if (resource.getStatus().equals("error")) {

                        //datalist1.clear();
                        //recylerview_time.setAdapter(null);
                        //Toast.makeText(getActivity(), resource.getStatus(), Toast.LENGTH_LONG).show();
                        Toast.makeText(getActivity(), "No Time slots for this date", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Availdates_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd2.dismiss();
               // t.printStackTrace();
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onItemClickProduct(int position, String Date) {
        dateforcart = Date;
        apiCallfor_availrooms(dateforcart,roomid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_room_info, container, false);

        View view=inflater.inflate(R.layout.fragment_room_info, container, false);

        SharedPreferences pref1 = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = pref1.getString("user_id","");

        cartcount = view.findViewById(R.id.cartcount);

        roomtype = view.findViewById(R.id.roomtype);
        r_price = view.findViewById(R.id.price);
        chcktime = view.findViewById(R.id.chcktime);
        checkin_time1 = view.findViewById(R.id.checkin_time1);
        checkout_time2 = view.findViewById(R.id.checkout_time2);
        avail_rooms = view.findViewById(R.id.avail_rooms);

        txt_addroom = view.findViewById(R.id.txt_addroom);

        imageback1 = view.findViewById(R.id.imageback1);
        backbutton = view.findViewById(R.id.backbutton);


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.loadFragment(new Rooms_List_Fragment(),getActivity(), Room_info_Fragment.this);
            }
        });
        imageback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Util.loadFragment(new Room_Cart_Fragment(),getActivity(), Room_info_Fragment.this);

//                Intent i = new Intent(getContext(), Rooms_Payment_Activity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //add this line
//                //i.putExtra("type", "ROOMS");
//                getContext().startActivity(i);
                getActivity().onBackPressed();

//                Intent intent = new Intent(getContext(), Rooms_Payment_Activity.class);
//                intent.putExtra("frag", "fragmentB");
//                startActivity(intent);
            }
        });

        txt_addroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dateforcart ==null)
                {
                    Toast.makeText(getActivity(), "Please select date", Toast.LENGTH_LONG).show();
                }
                else if(count > Integer.parseInt(avail_rooms.getText().toString())){
                    Toast.makeText(getActivity(), "Please check avail rooms", Toast.LENGTH_LONG).show();

                }
                else{
                    apiCallForCart_addroom();
                }
            }
        });
//        if(count > Integer.parseInt(r_availrooms)){
//            Toast.makeText(getActivity(), "Please check avail rooms", Toast.LENGTH_LONG).show();
//            //count = Integer.parseInt(r_availrooms);
//        }


        //edt_nofpersions = view.findViewById(R.id.edt_nofpersions);
        reportimage = view.findViewById(R.id.reportimage);
        plus = view.findViewById(R.id.plus);
        minus = view.findViewById(R.id.minus);
        plus_person = view.findViewById(R.id.plus_person);
        minus_person = view.findViewById(R.id.minus_person);
        incriment = view.findViewById(R.id.incriment);
        incriment_person = view.findViewById(R.id.incriment_person);


        SharedPreferences pref = getActivity().getSharedPreferences("GET_ROOMINFO", Context.MODE_PRIVATE);
        roomid = pref.getString("r_id", "");
        String type = pref.getString("r_type", "");
        price = pref.getString("r_price", "");
        time = pref.getString("r_time", "");
        checkin = pref.getString("r_chechin", "");
        checkout = pref.getString("r_chechout", "");
        noofpersions = pref.getString("r_nop", "");
        img = pref.getString("r_img", "");
        //r_availrooms = pref.getString("r_availrooms", "");
        hotelid = pref.getString("r_hotelid", "");



        roomtype.setText(type);
        r_price.setText("\u20B9"+price);
        chcktime.setText(time);
        checkin_time1.setText(checkin);
        checkout_time2.setText(checkout);
        //avail_rooms.setText(r_availrooms);

        //edt_nofpersions.setText(noofpersions);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(getActivity()).load(img).apply(options).into(reportimage);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;

                incriment.setText(String.valueOf(count));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                if(count<1){
                    count=1;
                }
                incriment.setText(String.valueOf(count));
            }
        });

        plus_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countpersons++;

                incriment_person.setText(String.valueOf(countpersons));
            }
        });
        minus_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countpersons--;
                if(countpersons<1){
                    countpersons=1;
                }
                incriment_person.setText(String.valueOf(countpersons));
            }
        });

        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3);
        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(layoutManager);

        onClick=this;
        adapter = new Dates_Adapter(getActivity(), null, onClick);
        recyclerview.setAdapter(adapter);

        SharedPreferences pref123 = getActivity().getSharedPreferences("ROOM_CATID_FOR_RINFO", Context.MODE_PRIVATE);
        roomcatid = pref123.getString("r_catidforinfo","");

        apiCall();

        return view;

    }

    private void apiCall() {
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

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String d_id = pref.getString("u_id", "");
        // String d_id = "1";
        String id = "1";
        String city = "Hyderabad";
        Call<Dates_Responce> getCate = ApiClient.getApiServiceforservice().dates_list(roomid);
        getCate.enqueue(new Callback<Dates_Responce>() {
            @Override
            public void onResponse(Call<Dates_Responce> call, Response<Dates_Responce> response) {
                final Dates_Responce resource = response.body();
                pd1.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    //catstore
                    //store_o=resource.getdSpecialid();
                    datalist=resource.getRoomsdata();

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

                                adapter.updateList(resource.getRoomsdata());
                                //adapter.updateList(resource.getProduct());
                                //  recyclerView.setAdapter(adapter);
                            }
                        });

                    }

                } else {

//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<Dates_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                //t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }



    @Override
    public void onItemClickaddtocart(int position, String catid) {

    }

    @Override
    public void onItemClickaddtocartupdatecount(int position, String proid, String qty) {

    }
    public void apiCallForCart(){

//        if(userid.trim().length()==0){
//            cartcount.setText("0");
//            return;
//        }
        //String id = "63";
        //String count = "count";

        SharedPreferences pref1 = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
       String uid1 = pref1.getString("user_id","");

        Call<CartResponce_R> getCate = ApiClient.getApiServiceforservice().cart_count(uid1,count_type);
        getCate.enqueue(new Callback<CartResponce_R>() {
            @Override
            public void onResponse(Call<CartResponce_R> call, Response<CartResponce_R> response) {
                final CartResponce_R resource = response.body();
                // pd1.dismiss();

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    cartcount.setText(String.valueOf(resource.getCartCount()));

                } else {

                    cartcount.setText("0");

                }

            }

            @Override
            public void onFailure(Call<CartResponce_R> call, Throwable t) {
                //   pd.dismiss();
                //   pd1.dismiss();
               // t.printStackTrace();
                // Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void apiCallForCart_addroom(){

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



        //String id = "63";
        String type = "add";
        String room_id = roomid;
        String room_qty = incriment.getText().toString();
        String bcart_per = incriment_person.getText().toString();
        //String bcart_per = edt_nofpersions.getText().toString();
        //String user_date = "03-22-2022";dateforcart
        String user_date = dateforcart;
        String user_time = "12 Hours";
        String hotel_id = hotelid;
        Call<CartResponce_R> getCate = ApiClient.getApiServiceforservice().set_cart_data(uid,type,room_id,room_qty,bcart_per,user_date,user_time,hotel_id);
        getCate.enqueue(new Callback<CartResponce_R>() {
            @Override
            public void onResponse(Call<CartResponce_R> call, Response<CartResponce_R> response) {
                final CartResponce_R resource = response.body();
                pd1.dismiss();

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    apiCallForCart();
                    //cartcount.setText(String.valueOf(resource.getCartCount()));
                    //Toast.makeText(getActivity(), "Room Added To Cart Successfully" + uid, Toast.LENGTH_LONG).show();
                    Custom_showDialog_success(getActivity());
                }
                else {
                    //Toast.makeText(getActivity(), "Room " + uid, Toast.LENGTH_LONG).show();
                    //Toast.makeText(getActivity(), ""+resource.getType(), Toast.LENGTH_LONG).show();
                    Custom_showDialog_failed(getActivity());

                }

            }

            @Override
            public void onFailure(Call<CartResponce_R> call, Throwable t) {
                //   pd.dismiss();
                //   pd1.dismiss();
               // t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void Custom_showDialog_success(Activity activity) {
        dialogsuccess = new Dialog(activity);
        dialogsuccess.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogsuccess.setCancelable(true);
        dialogsuccess.setContentView(R.layout.dialog_for_room_success);
        dialogsuccess.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView ok_btn = dialogsuccess.findViewById(R.id.ok_btn);
        TextView title = dialogsuccess.findViewById(R.id.title);
        String msg = "Room Added to Cart Successfully";
        title.setText(msg);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dialogsuccess.dismiss();
                //Util.loadFragment(new homefragment(),getActivity(), Room_info_Fragment.this);
                dialogsuccess.dismiss();
            }
        });
        dialogsuccess.show();
    }

    public void Custom_showDialog_failed(Activity activity) {
        dialogfailed = new Dialog(activity);
        dialogfailed.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogfailed.setCancelable(true);
        dialogfailed.setContentView(R.layout.dialog_for_room_failed);
        dialogfailed.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView ok_btn = dialogfailed.findViewById(R.id.ok_btn);
        TextView title = dialogfailed.findViewById(R.id.title);
        String msg = "Rooms not available";
        title.setText(msg);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogfailed.dismiss();
            }
        });
        dialogfailed.show();
    }
}