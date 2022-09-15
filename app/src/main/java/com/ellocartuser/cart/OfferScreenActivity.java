package com.ellocartuser.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CouponResponce;
import com.ellocartuser.home.adapterandmodel.Address;
import com.ellocartuser.home.adapterandmodel.Coupons;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferScreenActivity extends AppCompatActivity  implements OfferScreenAdapter.OnItemClickedAdd {

    ProgressDialog pd1,pd;
    RecyclerView dataList;
    // TODO: Rename and change types of parameters
    private String lat="",longi="";
    private String mParam2;
    ImageView imageback;
    OfferScreenAdapter.OnItemClickedAdd onclick;
    Dialog dialog;
    LinearLayout noorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_offer_screen);

        noorder=findViewById(R.id.noorder);
        dataList=findViewById(R.id.catList);
        imageback=findViewById(R.id.imageback);
        onclick=this;
        dataList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        onclick=this;
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        apiCallCoupon();

    }

    private void apiCallCoupon() {

        pd = new ProgressDialog(OfferScreenActivity.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        // pd.show();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
       String addrid = getIntent().getStringExtra("addr_id");

        Call<CouponResponce> getCate = ApiClient.getApiService().getCoupon("143",addrid,id);
        getCate.enqueue(new Callback<CouponResponce>() {
            @Override
            public void onResponse(Call<CouponResponce> call, Response<CouponResponce> response) {
                final CouponResponce resource = response.body();
                pd.dismiss();
                // Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }

                if(resource.getStatus().equals("ok")){

                    if(resource.getCoupons()!=null){


                        noorder.setVisibility(View.GONE);
                        dataList.setVisibility(View.VISIBLE);

                        OfferScreenAdapter off = new OfferScreenAdapter(OfferScreenActivity.this, resource.getCoupons(), onclick);
                        dataList.setAdapter(off);


//                        try {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(Checkout.this);
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
//
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

                    }

                }else {

                    dataList.setVisibility(View.GONE);
                    noorder.setVisibility(View.VISIBLE);


                    if(resource.getMessage()!=""){
                       // Toast.makeText(OfferScreenActivity.this, resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<CouponResponce> call, Throwable t) {
                //   pd.dismiss();

                dataList.setVisibility(View.GONE);
                noorder.setVisibility(View.VISIBLE);
                pd.dismiss();
                t.printStackTrace();
            }
        });

    }
//
    public void showDialog(Coupons cpn){

        dialog = new Dialog(OfferScreenActivity.this,android.R.style.Theme_Holo_Light_NoActionBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.offerdialog);

        LinearLayout applylayout = dialog.findViewById(R.id.applylayout);
        LinearLayout timerlayout = dialog.findViewById(R.id.timerlayout);
        TextView sectextview = dialog.findViewById(R.id.sectextview);
      //  TextView textView = dialog.findViewById(R.id.textview);
        Button btnapply = dialog.findViewById(R.id.apply);
        Button btncancle = dialog.findViewById(R.id.cancle);
        ImageView img =dialog.findViewById(R.id.imageView8);

//        VideoView videoView =dialog.findViewById(R.id.videoView);
//        MediaController mediaController= new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        Uri uri=Uri.parse("ttp://www.dailymotion.com/embed/video/x1ade3x");
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
//
//        videoView.start();

     //   textView.setText("");
      //  textView.setText("You saved ₹ "+cpn.getCoupAmount().toString());

        applylayout.setVisibility(View.GONE);
        sectextview.setVisibility(View.VISIBLE);

        Glide.with(getApplicationContext())
                .load(cpn.getCoup_image())
                .fitCenter()
                .into(img);

        btnapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.putExtra("id", cpn.getCoupId() );
                intent.putExtra("amt", String.valueOf(cpn.getCoupAmount()) );
                intent.putExtra("amt", String.valueOf(cpn.getCoupAmount()) );
                intent.putExtra("Coupon Applyed", String.valueOf(cpn.getCoupName()) );
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                sectextview.setText("Apply Button Appear in " + f.format(sec)+" seconds");
            }
            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                //   sectextview.setText("00:00:00");
                applylayout.setVisibility(View.VISIBLE);
               // textView.setVisibility(View.INVISIBLE);
                sectextview.setVisibility(View.GONE);
            }
        }.start();

        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onItemClickedCart(int position, Coupons cpn) {
       showDialog(cpn);
    }
}