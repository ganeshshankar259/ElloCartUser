package com.ellocartuser.cart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CouponResponce;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickUpDetailScreen extends AppCompatActivity implements PaymentResultListener  {

    TextView txtcoupon;
    ProgressDialog pd;
    Calendar myCalendar;
    String count,total,order_type,finalamt,couponid="",firstfinal;
    DatePickerDialog.OnDateSetListener date;
    ImageView imgback;
    TextView phnnum,name,address,totalitems,productprice,couponprice,totalamt;
    Button datee,orderbtn;
    Intent intent;
    ImageView circleImageView;
    Button offers;
    TextView saveprise;
    private final static int MY_REQUEST_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_detail_screen);

        com.razorpay.Checkout.preload(getApplicationContext());

       String item_save = getIntent().getStringExtra("item_save");
        if(Integer.parseInt(item_save)>0){
            //   if(true){
            showDialogSave(item_save);
        }


        intent=getIntent();
        saveprise=findViewById(R.id.saveprise);
        offers=findViewById(R.id.offers);
        orderbtn = findViewById(R.id.orderbtn);
        datee = findViewById(R.id.datee);
        txtcoupon = findViewById(R.id.txtcoupon);
        name=findViewById(R.id.name);
        phnnum=findViewById(R.id.phnnum);
        address=findViewById(R.id.address);
        circleImageView=findViewById(R.id.imgicon);
        totalamt=findViewById(R.id.total);
        couponprice=findViewById(R.id.couponprice);
        totalitems=findViewById(R.id.totalitems);
        productprice=findViewById(R.id.productprice);
        imgback=findViewById(R.id.imageback);
        saveprise.setText("₹ "+getIntent().getStringExtra("item_save"));
        count = getIntent().getStringExtra("count");
//      date = getIntent().getStringExtra("date");
        total = getIntent().getStringExtra("carttotal");
        order_type = getIntent().getStringExtra("order_type");

        finalamt=total;
        firstfinal=total;
        couponprice.setText("₹ 0");
        totalitems.setText(count);
        productprice.setText("₹ "+total);
        totalamt.setText("₹ "+finalamt);

        name.setText(intent.getStringExtra("name"));
        phnnum.setText(intent.getStringExtra("phonenumer"));
        address.setText(intent.getStringExtra("address"));

        Glide.with(getApplicationContext())
                .load(intent.getStringExtra("imagename"))
                .fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).placeholder(R.drawable.placeholderello)
                .into(circleImageView);

        myCalendar  = Calendar.getInstance();

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

        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii= new Intent(getApplicationContext(),OfferScreenActivity.class);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

                String lat = pref.getString("latitude","");
                String longi = pref.getString("longitude","");

                ii.putExtra("longi",longi);
                ii.putExtra("lat",lat);
                startActivityForResult(ii, MY_REQUEST_CODE);
            }
        });

        datee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(PickUpDetailScreen.this, date, myCalendar
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

        updateLabel();

        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // apiCallPickUp();
              //  apiCallPickUp("online","1234");
                startPayment(Integer.valueOf(finalamt));
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txtcoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //apiCallCoupon();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        datee.setText(sdf.format(myCalendar.getTime()));
//        if(startt) {
//            start.setText(sdf.format(myCalendar.getTime()));
//        }
//        if(endd){
//            end.setText(sdf.format(myCalendar1.getTime()));
//        }

    }

//
//    private void apiCallCoupon() {
//
//        pd = new ProgressDialog(PickUpDetailScreen.this);
//        pd.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//         pd.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//        pd.setCancelable(false);
//        // pd.show();
//
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//
//        Call<CouponResponce> getCate = ApiClient.getApiService().getCoupon(id);
//        getCate.enqueue(new Callback<CouponResponce>() {
//            @Override
//            public void onResponse(Call<CouponResponce> call, Response<CouponResponce> response) {
//                final CouponResponce resource = response.body();
//                pd.dismiss();
//             //   Log.d("resss",resource.toString());
//
//                if(resource==null){
//                    return;
//                }
//                if(resource.getStatus().equals("ok")){
//
//                    if(resource.getCoupons()!=null){
//
//                        try {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(PickUpDetailScreen.this);
//                            builder.setTitle("Coupon");
//                            builder.setMessage("Click ok to apply the Coupon ");
//                            builder.setCancelable(true);
//                            builder.setPositiveButton(
//                                    "OK",
//                                    new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int id) {
//                                            dialog.cancel();
//
//                                            if(resource.getCoupons().getCoupAmount()!=null){
//
//                                                int amtfinal=Integer.parseInt(finalamt)-resource.getCoupons().getCoupAmount();
//                                                finalamt=String.valueOf(amtfinal);
//                                                totalamt.setText("₹ "+finalamt);
//                                                couponprice.setText("₹ "+String.valueOf(resource.getCoupons().getCoupAmount()));
//
//                                                txtcoupon.setVisibility(View.INVISIBLE);
//                                            }
//
//                                        }
//                                    });
//
//                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.cancel();
//                                }
//                            });
//
//                            AlertDialog alert = builder.create();
//                            alert.show();
//                        } catch (RuntimeException ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//
//                }else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(PickUpDetailScreen.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<CouponResponce> call, Throwable t) {
//                //   pd.dismiss();
//                pd.dismiss();
//                t.printStackTrace();
//            }
//        });
//
//    }

    public void  apiCallPickUp(String type,String payref){

//        pd = new ProgressDialog(PickUpDetailScreen.this);
//        pd.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//         pd.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//        pd.setCancelable(false);
//        pd.show();
//
//
//        HashMap<String, String> map = new HashMap<>();
//        map.put("question1", "");
//        map.put("question2", "");
//        map.put("question3", "");
//
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        Call<MyProfileResponce> getCate = ApiClient.getApiService().placeorderPicked(id,"0",datee.getText().toString(),"",couponid,type,payref,"0","online",finalamt,"0",map);
//        getCate.enqueue(new Callback<MyProfileResponce>() {
//            @Override
//            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
//                final MyProfileResponce resource = response.body();
//                pd.dismiss();
//             //   Log.d("resss",resource.toString());
//
//                if(resource==null){
//                    return;
//                }
//
//                if(resource.getStatus().equals("ok")){
//
//                    Intent ii =new Intent(PickUpDetailScreen.this,ThankyouScreen.class);
//                    ii.putExtra("imagename",getIntent().getStringExtra("imagename"));
//                    ii.putExtra("phonenumer",intent.getStringExtra("phonenumer"));
//                    ii.putExtra("storename",getIntent().getStringExtra("name"));
//                    String name=getIntent().getStringExtra("name");
//                    startActivity(ii);
//
//                }else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(PickUpDetailScreen.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MyProfileResponce> call, Throwable t) {
//                //   pd.dismiss();
//                pd.dismiss();
//                t.printStackTrace();
//
//            }
//        });

    }

    public void startPayment(int amt) {

        /*
           You need to pass current activity in order to let Razorpay create CheckoutActivity
        */
        // this.amt=amt;

        final Activity activity = PickUpDetailScreen.this;

        final com.razorpay.Checkout co = new com.razorpay.Checkout();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        String user_phone = pref.getString("user_phone","");
        String user_email = pref.getString("user_email","");
        try {
            JSONObject options = new JSONObject();
            options.put("name", "ElloCart");
            options.put("description", "Re-Discovering Local Online Shopping");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://i.pinimg.com/280x280_RS/e2/76/27/e276278a6448da200bf8d06269b907c9.jpg");
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

//        apiCallOrder("online",s); //for checkout
        apiCallPickUp("online",s);
        Toast.makeText(PickUpDetailScreen.this, "Payment Success", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(PickUpDetailScreen.this, "Payment Failed", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == MY_REQUEST_CODE) {
                finalamt=firstfinal;
                couponprice.setText("₹ 0");

                int amtfinal=Integer.parseInt(finalamt)-Integer.valueOf(data.getStringExtra("amt"));
                finalamt=String.valueOf(amtfinal);
                totalamt.setText("₹ "+finalamt);
                couponprice.setText("₹ "+String.valueOf(data.getStringExtra("amt")));
                couponid=data.getStringExtra("id");
                //  Toast.makeText(Checkout.this, data.getStringExtra("Coupon Applyed"), Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void showDialogSave(String amount){
        final Dialog dialog = new Dialog(PickUpDetailScreen.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.quote_download_dialog);

        //   ImageView close = dialog.findViewById(R.id.close);
        ImageView done = dialog.findViewById(R.id.close);
    //    Button share = dialog.findViewById(R.id.share);
        TextView txt = dialog.findViewById(R.id.txt);
//
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//
//            }
//        });
        txt.setText("₹ "+amount);

//        Bloom.with(Checkout.this)
//                .setParticleRadius(8)
//                .setEffector(new BloomEffector.Builder()
//                        .setDuration(1000)
//                        .setSkewRange(0.05f, 0.15f)
//                        .setAnchor(done.getWidth() / 2, done.getHeight() / 2)
//                        .build())
//                //.setBloomListener(new MyBloomListener(done))
//                .boom(done);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

}