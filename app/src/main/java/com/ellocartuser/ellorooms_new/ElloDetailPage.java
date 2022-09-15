package com.ellocartuser.ellorooms_new;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CheckOutPaymentResponce;
import com.ellocartuser.apiservices.Responce.ElloRoomFilterResponse;
import com.ellocartuser.cart.Checkout;
import com.ellocartuser.cart.ThankyouScreen;
import com.ellocartuser.cart.ThankyoupageAllOverIndia;
import com.ellocartuser.ellorooms_new.adapter.ListDisplayAdapter;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//checkout page
public class ElloDetailPage extends AppCompatActivity implements PaymentResultListener {
    ProgressDialog pd;
    TextView hotel_name,hotel_type,hotel_address,from_date,to_date,total_rooms,total_amount,advance_amount,remaining_amount;
    Button payment;
    String live_key="",paymentid="";int total_amt=0;
    ImageView img,imageback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.ello_detail_page);

        com.razorpay.Checkout.preload(ElloDetailPage.this);


        imageback=findViewById(R.id.imageback);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        img=findViewById(R.id.imageView22);
        hotel_name=findViewById(R.id.title);
        hotel_type=findViewById(R.id.type);
        hotel_address=findViewById(R.id.address);
        from_date=findViewById(R.id.from_date);
        to_date=findViewById(R.id.to_date);
        total_rooms=findViewById(R.id.p222);
        total_amount=findViewById(R.id.p223);
        advance_amount=findViewById(R.id.p323);
        remaining_amount=findViewById(R.id.p423);
        payment=findViewById(R.id.btn);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment(total_amt, paymentid);
            }
        });

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        apiCall(pref.getString("rooms_latitude",""),pref.getString("rooms_longitude",""));

    }

    private void apiCall(String lat,String longi) {

        pd = new ProgressDialog(ElloDetailPage.this);
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

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        Call<ElloRoomFilterResponse> getCate = ApiClient.getApiServiceforRooms().get_room_cart(id,getIntent().getStringExtra("room_id"),getIntent().getStringExtra("fromdate"),getIntent().getStringExtra("todate"),getIntent().getStringExtra("persons"),getIntent().getStringExtra("rooms"),lat,longi);
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

                    Glide.with(ElloDetailPage.this)
                            .load(resource.getDetails().getRoomImage1())
                            .fitCenter().placeholder(R.drawable.placeholderello)
                            .into(img);

                    live_key=resource.getKey().get(0).getLiveKey();
                    hotel_name.setText(resource.getDetails().getHotelName());
                    hotel_type.setText(resource.getDetails().getRoomName());
                    hotel_address.setText(resource.getDetails().getHotelAddress());
                    from_date.setText(resource.getDetails().getStartDate());
                    to_date.setText(resource.getDetails().getEndDate());
                    total_rooms.setText(resource.getDetails().getTotalRooms());
                    total_amount.setText("₹"+resource.getDetails().getTotalPrice());
                    advance_amount.setText("₹"+resource.getDetails().getAdvanceAmount());
                    remaining_amount.setText("₹"+resource.getDetails().getRemainingAmount());
                    total_amt=resource.getDetails().getAdvanceAmount();

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

    public void apicallcheckout2(String comeingtype) {


        ProgressDialog pd1 = new ProgressDialog(ElloDetailPage.this);
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");
        String lat = pref.getString("latitude", "");
        String longi = pref.getString("longitude", "");


        Call<CheckOutPaymentResponce> getCate = ApiClient.getApiService().getroomcheckout(id,getIntent().getStringExtra("room_id"),getIntent().getStringExtra("fromdate"),getIntent().getStringExtra("todate"),getIntent().getStringExtra("persons"),getIntent().getStringExtra("rooms"),"online");
        getCate.enqueue(new Callback<CheckOutPaymentResponce>() {
            @Override
            public void onResponse(Call<CheckOutPaymentResponce> call, Response<CheckOutPaymentResponce> response) {
                final CheckOutPaymentResponce resource = response.body();
                pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {

                    paymentid = resource.getOrderPayid();
                    // showDialogForPaymentPayMode(resource.getOrderFinal(),resource.getOrderId(),resource.getOrderPayid());

                        startPayment(Integer.parseInt(resource.getOrderFinal()), paymentid);


                } else {

                }

            }

            @Override
            public void onFailure(Call<CheckOutPaymentResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(ElloDetailPage.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }



    public void startPayment(int amt, String payid) {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        // this.amt=amt;
        if(live_key==""){
            Toast.makeText(ElloDetailPage.this, "Error in payment: No Key " , Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        final Activity activity = ElloDetailPage.this;

        final com.razorpay.Checkout co = new com.razorpay.Checkout();
        // set your id as below
        co.setKeyID(live_key);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        String user_phone = pref.getString("user_phone", "");
        String user_email = pref.getString("user_email", "");
        try {
            JSONObject options = new JSONObject();
            options.put("name", "ElloCart");
            options.put("description", "Re-Discovering Local Online Shopping");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://cdn.razorpay.com/logos/IUa4SiVrn6pGtz_original.png");
            options.put("order_id", payid);
            options.put("currency", "INR");
            options.put("amount", amt * 100);

            JSONObject preFill = new JSONObject();
            preFill.put("email", user_email);
            preFill.put("contact", user_phone);

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        orderStatusCheckApi(s);
//        apiCallOrder("online",s);
//        Toast.makeText(ElloDetailPage.this, "Payment Success", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        //   orderStatusCheckApi();
        Toast.makeText(ElloDetailPage.this, "Payment Failed", Toast.LENGTH_SHORT).show();

    }




    public void orderStatusCheckApi(String trasac) {
        ProgressDialog pd1 = new ProgressDialog(ElloDetailPage.this);
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");
        String lat = pref.getString("latitude", "");
        String longi = pref.getString("longitude", "");

        Call<CheckOutPaymentResponce> getCate = ApiClient.getApiService().getcheckoutpay(id, "", trasac);
        getCate.enqueue(new Callback<CheckOutPaymentResponce>() {
            @Override
            public void onResponse(Call<CheckOutPaymentResponce> call, Response<CheckOutPaymentResponce> response) {
                final CheckOutPaymentResponce resource = response.body();
                pd1.dismiss();


                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {
                    Toast.makeText(ElloDetailPage.this, "Payment Success", Toast.LENGTH_SHORT).show();
                    if(resource.getOrder_type2().equals("0")){
                        Intent ii = new Intent(ElloDetailPage.this, ThankyouScreen.class);
                        ii.putExtra("imagename", getIntent().getStringExtra("imagename"));
                        ii.putExtra("storename", getIntent().getStringExtra("name"));
                        ii.putExtra("orderid", resource.getOrderId());
                        ii.putExtra("address", getIntent().getStringExtra("address"));
                        ii.putExtra("phonenumer", getIntent().getStringExtra("phonenumer"));
                        ii.putExtra("orderdate", resource.getOrderDate());
                        ii.putExtra("ordertime", resource.getOrderTime());
                        ii.putExtra("seller_time", getIntent().getStringExtra("seller_time"));

                        startActivity(ii);
                    } else  if(resource.getOrder_type2().equals("1")){

                        ThankyoupageAllOverIndia bottomSheet = new ThankyoupageAllOverIndia(resource.getOrderId());
                        bottomSheet.setCancelable(false);
                        bottomSheet.show(getSupportFragmentManager(),
                                "ModalBottomSheet");

                    }


                } else {
                    Toast.makeText(ElloDetailPage.this, "Payment Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CheckOutPaymentResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(ElloDetailPage.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

}