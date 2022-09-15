package com.ellocartuser.rooms_old;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.rooms_old.ModelsandResponces.CartList_Adapter;
import com.ellocartuser.rooms_old.ModelsandResponces.Cartdisplay_M;
import com.ellocartuser.rooms_old.ModelsandResponces.Cartdisplay_Responce;
import com.ellocartuser.rooms_old.ModelsandResponces.Delete_Cart_ById_Responce;
import com.ellocartuser.tutions.Tutions_Base;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Rooms_Payment_Activity extends AppCompatActivity implements CartList_Adapter.OnItemClickedproduct, PaymentResultListener {

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
    String b_user_id,advance_amount,adavanceforrazor;
    LinearLayout noorder;
    ScrollView scrollview;
    String u_id;
    Dialog dialogappoint,dialogsuccess,dialogfailed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_payment);

        SharedPreferences pref1 = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        u_id = pref1.getString("user_id","");


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(Rooms_Payment_Activity.this));

        txt_totalrooms = findViewById(R.id.txt_totalrooms);
        txt_grandtotal = findViewById(R.id.txt_grandtotal);
        txt_advance = findViewById(R.id.txt_advance);
        txt_remain_amount = findViewById(R.id.txt_remain_amount);
        txt_hotelname = findViewById(R.id.txt_hotelname);

        noorder = findViewById(R.id.noorder);
        scrollview = findViewById(R.id.scrollview);

        b_user_id = "63";
        //razorpayid ="63635282";
        advance_amount = txt_advance.getText().toString();

        backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Util.loadFragment(new Room_info_Fragment(), Rooms_Payment_Activity.this, Rooms_Payment_Activity.this);
                //onBackPressed();
                Intent intent = new Intent(getApplicationContext(), Tutions_Base.class);
                intent.putExtra("type","ROOM_INFO");
                //ii.putExtra("type", "");
                startActivity(intent);
            }
        });
//        Button gohistory = findViewById(R.id.gohistory);
//        gohistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Util.loadFragment(new BookedRoomsList_Fragment(), Rooms_Payment_Activity.this, Rooms_Payment_Activity.this);
//            }
//        });
        TextView clear_cart = findViewById(R.id.clear_cart);
        clear_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "delete";
               // String u_id ="63";


                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Rooms_Payment_Activity.this);
                    // builder.setTitle("De");
                    builder.setMessage("Do you want to Clear Cart ?");
                    builder.setCancelable(true);
                    builder.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();

                                    SharedPreferences pref = Rooms_Payment_Activity.this.getSharedPreferences("user", Context.MODE_PRIVATE);
                                    String idd = pref.getString("user_id","");
                                    apiCall_for_clearcart(type,u_id);
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

        TextView book_now = findViewById(R.id.book_now);
        book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Rooms_Payment_Activity.this);
                    // builder.setTitle("De");
                    builder.setMessage("Do you want to Book Rooms?");
                    builder.setCancelable(true);
                    builder.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();

                                    SharedPreferences pref = Rooms_Payment_Activity.this.getSharedPreferences("user", Context.MODE_PRIVATE);
                                    String idd = pref.getString("user_id","");
                                    startPayment(adavanceforrazor);
//                                    apiCall_for_book_rooms(b_user_id,advance_amount,razorpayid);
//                                    datalist.clear();
//                                    adapter.notifyDataSetChanged ();

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
        adapter = new CartList_Adapter(Rooms_Payment_Activity.this, null, onClick);
        recyclerView.setAdapter(adapter);
        apiCall();
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
//        apiCall();

    }


    private void apiCall() {
        pd1 = new ProgressDialog(Rooms_Payment_Activity.this);
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

        SharedPreferences pref = Rooms_Payment_Activity.this.getSharedPreferences("user", Context.MODE_PRIVATE);
        String d_id = pref.getString("u_id", "");
        // String d_id = "1";
        //String id = "63";
        String type = "get";
        Call<Cartdisplay_Responce> getCate = ApiClient.getApiServiceforservice().cart_display(u_id, type);
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

                    txt_totalrooms.setText(String.valueOf(resource.getTotalRooms()));
                    txt_grandtotal.setText("\u20B9" + String.valueOf(resource.getGrandTotal()));
                    txt_advance.setText("\u20B9" + String.valueOf(resource.getEllocartPercentage()));
                    txt_remain_amount.setText("\u20B9" + String.valueOf(resource.getBalanceAmount()));
                    txt_hotelname.setText(resource.getRsubTitle());
                    adavanceforrazor = String.valueOf(resource.getEllocartPercentage());

//                    if (getActivity() != null) {
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                                List<Doc_Profile_List_M> temp = new ArrayList();
////                                for(int i=0;i<datalist.size();i++){
//////                                    if(datalist.get(i).().contains(text) || datalist.get(i).getProductName().toLowerCase().contains(text)){
//////                                        temp.add(datalist.get(i));
//////                                    }
////                                    //resource.getProfile().get(i);
////                                    temp.add(datalist.get(i));
////
////                                }
////                                int i = 1;
//
//
//                                //adapter.updateList(resource.getRoomsData());
//                                //adapter.updateList(resource.getProduct());
//                                //  recyclerView.setAdapter(adapter);
//                            }
//                        });
//
//                    }

                } else {
                    scrollview.setVisibility(View.GONE);
                    noorder.setVisibility(View.VISIBLE);

//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
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
       // String u_id = "63";
        String type = "delete";


        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(Rooms_Payment_Activity.this);
            // builder.setTitle("De");
            builder.setMessage("Do you want to delete ?");
            builder.setCancelable(true);
            builder.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();

                            SharedPreferences pref = Rooms_Payment_Activity.this.getSharedPreferences("user", Context.MODE_PRIVATE);
                            String idd = pref.getString("user_id","");
                            apiCall_for_delete_by_BcartId(type,u_id,BcartId);
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
        pd1 = new ProgressDialog(Rooms_Payment_Activity.this);
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

        SharedPreferences pref = Rooms_Payment_Activity.this.getSharedPreferences("user", Context.MODE_PRIVATE);
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
                    Custom_showDialog_clearitems(Rooms_Payment_Activity.this);
                   // Toast.makeText(Rooms_Payment_Activity.this, ""+resource.getMsg(), Toast.LENGTH_LONG).show();

                    apiCall();

                    //catstore
                    //store_o=resource.getdSpecialid();
                    //datalist = resource.getRoomsData();

//                    if (getActivity() != null) {
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                                List<Doc_Profile_List_M> temp = new ArrayList();
////                                for(int i=0;i<datalist.size();i++){
//////                                    if(datalist.get(i).().contains(text) || datalist.get(i).getProductName().toLowerCase().contains(text)){
//////                                        temp.add(datalist.get(i));
//////                                    }
////                                    //resource.getProfile().get(i);
////                                    temp.add(datalist.get(i));
////
////                                }
////                                int i = 1;
//
//
//                                //adapter.updateList(resource.getRoomsData());
//
//                            }
//                        });
//
//                    }

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
        pd1 = new ProgressDialog(Rooms_Payment_Activity.this);
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

        SharedPreferences pref = Rooms_Payment_Activity.this.getSharedPreferences("user", Context.MODE_PRIVATE);
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
                    Custom_showDialog_clearcart(Rooms_Payment_Activity.this);
                    //Toast.makeText(Rooms_Payment_Activity.this, ""+resource.getMsg(), Toast.LENGTH_LONG).show();
                    scrollview.setVisibility(View.GONE);
                    noorder.setVisibility(View.VISIBLE);
                    //apiCall();

                    //catstore
                    //store_o=resource.getdSpecialid();
                    //datalist = resource.getRoomsData();

//                    if (getActivity() != null) {
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                                List<Doc_Profile_List_M> temp = new ArrayList();
////                                for(int i=0;i<datalist.size();i++){
//////                                    if(datalist.get(i).().contains(text) || datalist.get(i).getProductName().toLowerCase().contains(text)){
//////                                        temp.add(datalist.get(i));
//////                                    }
////                                    //resource.getProfile().get(i);
////                                    temp.add(datalist.get(i));
////
////                                }
////                                int i = 1;
//
//
//                                //adapter.updateList(resource.getRoomsData());
//
//                            }
//                        });
//
//                    }

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
        pd1 = new ProgressDialog(Rooms_Payment_Activity.this);
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

        SharedPreferences pref = Rooms_Payment_Activity.this.getSharedPreferences("user", Context.MODE_PRIVATE);
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

                    Toast.makeText(Rooms_Payment_Activity.this, ""+resource.getMsg(), Toast.LENGTH_LONG).show();

                    //apiCall();

                    //catstore
                    //store_o=resource.getdSpecialid();
                    //datalist = resource.getRoomsData();

//                    if (Rooms_Payment_Activity.this != null) {
//                        Rooms_Payment_Activity.this.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                                List<Doc_Profile_List_M> temp = new ArrayList();
////                                for(int i=0;i<datalist.size();i++){
//////                                    if(datalist.get(i).().contains(text) || datalist.get(i).getProductName().toLowerCase().contains(text)){
//////                                        temp.add(datalist.get(i));
//////                                    }
////                                    //resource.getProfile().get(i);
////                                    temp.add(datalist.get(i));
////
////                                }
////                                int i = 1;
//
//
//                                //adapter.updateList(resource.getRoomsData());
//
//                            }
//                        });
//
//                    }

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

    public void startPayment(String amount) {

        Checkout checkout = new Checkout();
        //checkout.setKeyID("rzp_live_dSSOVHMCsns4mQ");
        checkout.setImage(R.mipmap.ic_launcher_round);
        double finalAmount = Float.parseFloat(amount)*100;
        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();
            options.put("name", R.string.app_name);
            options.put("description", "Payment for Anything");
            options.put("send_sms_hash", true);
            options.put("allow_rotation", false);

            //You can omit the image option to fetch the image from dashboard
            options.put("currency", "INR");
            options.put("amount", finalAmount+"");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@gmail.com");
            preFill.put("contact", "7093378994");

            options.put("prefill", preFill);

            checkout.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayid) {

        Toast.makeText(Rooms_Payment_Activity.this,"Payment Success" +razorpayid, Toast.LENGTH_SHORT).show();

        apiCall_for_book_rooms(u_id,advance_amount,razorpayid);
        datalist.clear();
        adapter.notifyDataSetChanged ();
    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(Rooms_Payment_Activity.this,"Payment Failed" +s, Toast.LENGTH_SHORT).show();
    }
    public void Custom_showDialog_clearitems(Activity activity) {
        dialogsuccess = new Dialog(activity);
        dialogsuccess.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogsuccess.setCancelable(true);
        dialogsuccess.setContentView(R.layout.dialog_for_room_success);
        dialogsuccess.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView ok_btn = dialogsuccess.findViewById(R.id.ok_btn);
        TextView title = dialogsuccess.findViewById(R.id.title);
        String msg = "Item Deleted";
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

    public void Custom_showDialog_clearcart(Activity activity) {
        dialogfailed = new Dialog(activity);
        dialogfailed.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogfailed.setCancelable(true);
        dialogfailed.setContentView(R.layout.dialog_for_room_failed);
        dialogfailed.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView ok_btn = dialogfailed.findViewById(R.id.ok_btn);
        TextView title = dialogfailed.findViewById(R.id.title);
        String msg = "Cart Cleared";
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