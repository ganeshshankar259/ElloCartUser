package com.ellocartuser.servicesscreens;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.ServiceResponce;
import com.ellocartuser.apiservices.model.ServiveSubscription;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;


public class SubscritionScreen extends AppCompatActivity implements  SubscriptionsAdapter.OnItemClick, PaymentResultListener {
    RecyclerView dataList;
    TextView plan,amount,expiredate,date;
    CardView subscriptionlayout;
    ProgressDialog pd1;
    ImageView imageback;
    SubscriptionsAdapter.OnItemClick onItemClick;
    ServiveSubscription subctemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscrition_screen);

        Checkout.preload(getApplicationContext());

        imageback=findViewById(R.id.imageback);
        amount=findViewById(R.id.amount);
        date=findViewById(R.id.date);
        dataList=findViewById(R.id.catList);
        dataList.setLayoutManager(new LinearLayoutManager(SubscritionScreen.this, LinearLayoutManager.VERTICAL, false));

        onItemClick=this;

        apicallgetsubscribtion();

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void apicallgetsubscribtion() {
        pd1 = new ProgressDialog(SubscritionScreen.this);
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();
        pd1.show();
//        pd1.show();
//        @Field("type") RequestBody type,

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<ServiceResponce> getCate = ApiClient.getApiServiceforservice().checkservice("subscriptions",id);
        getCate.enqueue(new Callback<ServiceResponce>() {
            @Override
            public void onResponse(Call<ServiceResponce> call, Response<ServiceResponce> response) {
                final ServiceResponce resource = response.body();

                pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {
                    SubscriptionsAdapter subscriptionsAdapter= new SubscriptionsAdapter(SubscritionScreen.this,resource.getSubscriptions(),onItemClick);
                    dataList.setAdapter(subscriptionsAdapter);
                    //   subcatApi();
                } else {

                    //share to subscription page


//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<ServiceResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(SubscritionScreen.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public void onItemClicked(int position, ServiveSubscription subc) {

        if(subc!=null) {
            subctemp = subc;
            startPayment(subc.getSubscTotal());
        }

    }

    public void startPayment(int amt) {

        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        // this.amt=amt;

        final Activity activity = SubscritionScreen.this;

        final Checkout co = new Checkout();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String user_phone = pref.getString("user_phone","");
        String user_email = pref.getString("user_email","");

        try {
            JSONObject options = new JSONObject();
            options.put("name", "ElloCart");
            options.put("description", "Re-Discovering Local Online Shopping");
            //Re-Discovering Local Online Shopping
            //You can omit the image option to fetch the image from dashboard
            // options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", amt*100);

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

        Toast.makeText(SubscritionScreen.this, "Payment Success", Toast.LENGTH_SHORT).show();
        apiCallPaymentSuccess(s);
    }


    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(SubscritionScreen.this, "Payment Failed", Toast.LENGTH_SHORT).show();
    }

    private void apiCallPaymentSuccess(String payref) {

        pd1 = new ProgressDialog(SubscritionScreen.this);
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();
        pd1.show();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        Log.d("from ", String.valueOf(subctemp));

//        @Field("api") String api,
//        @Field("user_id") String user_id,
//        @Field("subsc_id") String subsc_id,
//        @Field("pay_ref") String pay_ref,
//        @Field("subsc_amount") String subsc_amount

        Call<ServiceResponce> getCate = ApiClient.getApiServiceforservice().serviceonPaymentSuccess("add_subscriptions",id,String.valueOf(subctemp.getSubscId()),payref,String.valueOf(subctemp.getSubscAmount()));
        getCate.enqueue(new Callback<ServiceResponce>() {

            @Override
            public void onResponse(Call<ServiceResponce> call, Response<ServiceResponce> response) {
                final ServiceResponce resource = response.body();

                pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {
                    onBackPressed();
                    //   subcatApi();
                } else {

                    //share to subscription page

//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<ServiceResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(SubscritionScreen.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });


    }

}