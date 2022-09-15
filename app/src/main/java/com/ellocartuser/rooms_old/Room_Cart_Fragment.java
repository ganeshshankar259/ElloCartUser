package com.ellocartuser.rooms_old;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.rooms_old.ModelsandResponces.CartList_Adapter;
import com.ellocartuser.rooms_old.ModelsandResponces.Cartdisplay_M;
import com.ellocartuser.rooms_old.ModelsandResponces.Cartdisplay_Responce;
import com.ellocartuser.rooms_old.ModelsandResponces.Delete_Cart_ById_Responce;
import com.ellocartuser.tutions.Tutions_Base;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Room_Cart_Fragment extends Fragment implements CartList_Adapter.OnItemClickedproduct {


    RecyclerView recyclerView;
    ProgressDialog pd1;
    CartList_Adapter adapter;
    CartList_Adapter.OnItemClickedproduct onClick;
    List<Cartdisplay_M> datalist = new ArrayList<>();
    List<Cartdisplay_Responce> datalist1 = new ArrayList<>();
    //String cartsellerid=null,carttotalcount="",store_o="0";
    ImageView backbutton;
    SharedPreferences pref;
    TextView txt_totalrooms, txt_grandtotal, txt_advance, txt_remain_amount, txt_hotelname;
    public String uid;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Room_Cart_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Room_Cart_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Room_Cart_Fragment newInstance(String param1, String param2) {
        Room_Cart_Fragment fragment = new Room_Cart_Fragment();
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
        //return inflater.inflate(R.layout.fragment_room_cart, container, false);
        View view = inflater.inflate(R.layout.fragment_room_cart, container, false);

        SharedPreferences pref1 = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = pref1.getString("user_id","");

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        txt_totalrooms = view.findViewById(R.id.txt_totalrooms);
        txt_grandtotal = view.findViewById(R.id.txt_grandtotal);
        txt_advance = view.findViewById(R.id.txt_advance);
        txt_remain_amount = view.findViewById(R.id.txt_remain_amount);
        txt_hotelname = view.findViewById(R.id.txt_hotelname);


        backbutton = view.findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Util.loadFragment(new Room_info_Fragment(), getActivity(), Room_Cart_Fragment.this);
                Intent intent = new Intent(getActivity(), Tutions_Base.class);
                intent.putExtra("type","ROOM_INFO");
                //ii.putExtra("type", "");
                startActivity(intent);
            }
        });
//        Button gohistory = view.findViewById(R.id.gohistory);
//        gohistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Util.loadFragment(new BookedRoomsList_Fragment(), getActivity(), Room_Cart_Fragment.this);
//            }
//        });
        TextView clear_cart = view.findViewById(R.id.clear_cart);
        clear_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "delete";
                //String u_id ="63";


                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    // builder.setTitle("De");
                    builder.setMessage("Do you want to Clear Cart ?");
                    builder.setCancelable(true);
                    builder.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();

                                    SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                                    String idd = pref.getString("user_id","");
                                    apiCall_for_clearcart(type,uid);
                                    datalist.clear();
                                    adapter.notifyDataSetChanged ();

                                }
                            });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                } catch (RuntimeException ex) {
                    ex.printStackTrace();
                }
            }
        });

        TextView book_now = view.findViewById(R.id.book_now);
        book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String b_user_id = "63";
                String razorpayid ="63635282";
                String advance_amount = txt_advance.getText().toString();

                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    // builder.setTitle("De");
                    builder.setMessage("Do you want to Book Rooms?");
                    builder.setCancelable(true);
                    builder.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();

//                                    SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//                                    String idd = pref.getString("user_id","");
                                    apiCall_for_book_rooms(uid,advance_amount,razorpayid);
                                    datalist.clear();
                                    adapter.notifyDataSetChanged ();

                                }
                            });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                } catch (RuntimeException ex) {
                    ex.printStackTrace();
                }
            }
        });

        onClick = this;
        adapter = new CartList_Adapter(getActivity(), null, onClick);
        recyclerView.setAdapter(adapter);

        //apiCall();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

//        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        if(id.equals("") || id==null){
//            Util.PleaseLogin(getContext());
////            downlayout.setVisibility(View.GONE);
//        }else{
////            apiCallForCart(id,"get","","","","");
//        }
        apiCall();

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

        SharedPreferences pref23 = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String uid_forcart = pref23.getString("user_id","");
        // String d_id = "1";
        //String id123 = "66";
        String type = "get";
        Call<Cartdisplay_Responce> getCate = ApiClient.getApiServiceforservice().cart_display(uid_forcart, type);
        getCate.enqueue(new Callback<Cartdisplay_Responce>() {
            @Override
            public void onResponse(Call<Cartdisplay_Responce> call, Response<Cartdisplay_Responce> response) {
                final Cartdisplay_Responce resource = response.body();
                pd1.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                adapter.updateList(resource.getRoomsData());

                if (resource.getStatus().equals("ok")) {
                    //catstore
                    //store_o=resource.getdSpecialid();
                    datalist = resource.getRoomsData();
                    Toast.makeText(getActivity(), "hello" +uid_forcart, Toast.LENGTH_LONG).show();
                    if (getActivity() != null) {
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
//                                int i = 1;
                                txt_totalrooms.setText(String.valueOf(resource.getTotalRooms()));
                                txt_grandtotal.setText("\u20B9" + String.valueOf(resource.getGrandTotal()));
                                txt_advance.setText("\u20B9" + String.valueOf(resource.getEllocartPercentage()));
                                txt_remain_amount.setText("\u20B9" + String.valueOf(resource.getBalanceAmount()));
                                txt_hotelname.setText(resource.getRsubTitle());

                                //adapter.updateList(resource.getRoomsData());
                                //adapter.updateList(resource.getProduct());
                                //  recyclerView.setAdapter(adapter);
                            }
                        });

                    }

                } else {

//                    if (resource.getMessage() != "") {
                        Toast.makeText(getActivity(), "hello" +uid_forcart, Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<Cartdisplay_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public void onItemClickProduct(int position, String BcartId) {
        //String u_id = "63";
        String type = "delete";


        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // builder.setTitle("De");
            builder.setMessage("Do you want to delete ?");
            builder.setCancelable(true);
            builder.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();

                            SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                            String idd = pref.getString("user_id","");
                            apiCall_for_delete_by_BcartId(type,uid,BcartId);
                            datalist.remove(position);
                            adapter.notifyDataSetChanged ();

                        }
                    });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }



    }

    @Override
    public void onItemClickaddtocart(int position, String catid) {

    }

    @Override
    public void onItemClickaddtocartupdatecount(int position, String proid, String qty) {

    }


    private void apiCall_for_delete_by_BcartId(String type,String u_id,String bcartid) {
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
//        String id = "63";
//        String type = "get";
        Call<Delete_Cart_ById_Responce> getCate = ApiClient.getApiServiceforservice().delete_cart_bybcartId(type,u_id,bcartid);
        getCate.enqueue(new Callback<Delete_Cart_ById_Responce>() {
            @Override
            public void onResponse(Call<Delete_Cart_ById_Responce> call, Response<Delete_Cart_ById_Responce> response) {
                final Delete_Cart_ById_Responce resource = response.body();
                pd1.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    Toast.makeText(getActivity(), ""+resource.getMsg(), Toast.LENGTH_LONG).show();

                    apiCall();

                    //catstore
                    //store_o=resource.getdSpecialid();
                    //datalist = resource.getRoomsData();

                    if (getActivity() != null) {
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
//                                int i = 1;


                                //adapter.updateList(resource.getRoomsData());

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
            public void onFailure(Call<Delete_Cart_ById_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void apiCall_for_clearcart(String type,String u_id) {
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
//        String id = "63";
//        String type = "get";
        Call<Delete_Cart_ById_Responce> getCate = ApiClient.getApiServiceforservice().delete_clear_cart(type,u_id);
        getCate.enqueue(new Callback<Delete_Cart_ById_Responce>() {
            @Override
            public void onResponse(Call<Delete_Cart_ById_Responce> call, Response<Delete_Cart_ById_Responce> response) {
                final Delete_Cart_ById_Responce resource = response.body();
                pd1.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    Toast.makeText(getActivity(), ""+resource.getMsg(), Toast.LENGTH_LONG).show();

                    //apiCall();

                    //catstore
                    //store_o=resource.getdSpecialid();
                    //datalist = resource.getRoomsData();

                    if (getActivity() != null) {
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
//                                int i = 1;


                                //adapter.updateList(resource.getRoomsData());

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
            public void onFailure(Call<Delete_Cart_ById_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void apiCall_for_book_rooms(String b_user_id,String b_total,String razorpayid) {
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
//        String id = "63";
//        String type = "get";
        Call<Delete_Cart_ById_Responce> getCate = ApiClient.getApiServiceforservice().book_rooms(b_user_id,b_total,razorpayid);
        getCate.enqueue(new Callback<Delete_Cart_ById_Responce>() {
            @Override
            public void onResponse(Call<Delete_Cart_ById_Responce> call, Response<Delete_Cart_ById_Responce> response) {
                final Delete_Cart_ById_Responce resource = response.body();
                pd1.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    Toast.makeText(getActivity(), ""+resource.getMsg(), Toast.LENGTH_LONG).show();

                    //apiCall();

                    //catstore
                    //store_o=resource.getdSpecialid();
                    //datalist = resource.getRoomsData();

                    if (getActivity() != null) {
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
//                                int i = 1;


                                //adapter.updateList(resource.getRoomsData());

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
            public void onFailure(Call<Delete_Cart_ById_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }



}