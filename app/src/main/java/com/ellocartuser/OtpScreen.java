package com.ellocartuser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.RegisterResponce;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.setting.MyProfileActivity;
import com.ellocartuser.utils.Util;
import com.ellocartuser.utils.WebViewActivity;
import com.goodiebag.pinview.Pinview;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpScreen extends AppCompatActivity {

    TextView sectextview;
    Pinview otpNumberOne;
    Pinview otpNumber;
    Button confirm;
    AppCompatTextView resend;
    String phnnum,type,boy_id=null;
    SharedPreferences pref;
    TextView my_text_view;
    CardView cvSubmit;
    ImageView imgBack;
    AppCompatEditText inputCode1,inputCode2,inputCode3,inputCode4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);

        phnnum= getIntent().getStringExtra("mobile");
        type= getIntent().getStringExtra("type");
        if(type=="reg"){
            boy_id = getIntent().getStringExtra("boyid");
        }
       // phnnum="234567";
        sectextview=findViewById(R.id.sectextview);
        otpNumberOne=findViewById(R.id.otpNumberOne);
       // otpNumber=findViewById(R.id.otpNumber);
       // confirm=findViewById(R.id.confirm);
        resend=findViewById(R.id.resend);
   //     my_text_view=findViewById(R.id.privacytext);
        cvSubmit=findViewById(R.id.cvSubmit);
        imgBack=findViewById(R.id.imgBack);

        otpNumberOne.setTextColor(
                ResourcesCompat.getColor(getResources(), R.color.black, getTheme()));
//        otpNumber.setTextColor(
//                ResourcesCompat.getColor(getResources(), R.color.black, getTheme()));
//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(otpNumberOne.getValue().length()==4){
//                    apiCall();
//                }else{
//                    Toast.makeText(OtpScreen.this, "please enter OTP", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OtpScreen.super.onBackPressed();
            }
        });
        cvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otpNumberOne.getValue().length()==4){
                    apiCall();
                }else{
                    Toast.makeText(OtpScreen.this, "please enter OTP", Toast.LENGTH_LONG).show();
                }
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resend.setVisibility(View.GONE);
                     resendApi();
            }
        });
        sectextview.setVisibility(View.GONE);
//        new CountDownTimer(60000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                // Used for formatting digit to be in 2 digits only
//                NumberFormat f = new DecimalFormat("00");
//                long hour = (millisUntilFinished / 3600000) % 24;
//                long min = (millisUntilFinished / 60000) % 60;
//                long sec = (millisUntilFinished / 1000) % 60;
//                sectextview.setText("resend otp in " + f.format(sec)+" seconds");
//            }
//            // When the task is over it will print 00:00:00 there
//            public void onFinish() {
//             //   sectextview.setText("00:00:00");
//                resend.setVisibility(View.VISIBLE);
//                sectextview.setVisibility(View.GONE);
//
//            }
//        }.start();



        String text = "BY CONTINUING, YOU AGREE TO OUR\n" +
                "TERMS OF SERVICE & PRIVACY POLICY";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                String url = "https://www.ellocart.com/terms";

//              Intent i = new Intent(Intent.ACTION_VIEW);
//              i.setData(Uri.parse(url));
                Intent i=new Intent(OtpScreen.this, WebViewActivity.class);
                i.putExtra("url",url);
                i.putExtra("name","TERMS OF SERVICE");
                startActivity(i);
            }
        };

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                String url = "https://www.ellocart.com/privacy";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
                Intent i=new Intent(OtpScreen.this, WebViewActivity.class);
                i.putExtra("url",url);
                i.putExtra("name","PRIVACY POLICY");
                startActivity(i);
            }
        };


        spannableString.setSpan(clickableSpan1, 31,49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan2, 51,65, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

//      spannableString.setSpan(clickableSpan3, 27,34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        my_text_view.setText(spannableString);
//        my_text_view.setMovementMethod(LinkMovementMethod.getInstance());




    }

    private void resendApi() {
        if(otpNumberOne.getValue().length()!=0) {
            otpNumberOne.clearValue();
        }
        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("boy_id","");
        Call<RegisterResponce> getCate = ApiClient.getApiService().resendotp(phnnum,"+91");
        getCate.enqueue(new Callback<RegisterResponce>() {
            @Override
            public void onResponse(Call<RegisterResponce> call, Response<RegisterResponce> response) {
                final RegisterResponce resource = response.body();

              //  Log.d("resss",resource.toString());
                Toast.makeText(OtpScreen.this, "Otp sent succesfuuly", Toast.LENGTH_LONG).show();
                sectextview.setVisibility(View.VISIBLE);
                if(resource.getStatus().equals("ok")){
                    new CountDownTimer(60000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            // Used for formatting digit to be in 2 digits only
                            NumberFormat f = new DecimalFormat("00");
                            long hour = (millisUntilFinished / 3600000) % 24;
                            long min = (millisUntilFinished / 60000) % 60;
                            long sec = (millisUntilFinished / 1000) % 60;
                            sectextview.setText("resend otp in " + f.format(sec)+" seconds");
                        }
                        // When the task is over it will print 00:00:00 there
                        public void onFinish() {
                            //   sectextview.setText("00:00:00");
                            resend.setVisibility(View.VISIBLE);
                            sectextview.setVisibility(View.GONE);

                        }
                    }.start();
               //     Toast.makeText(OtpScreen.this, resource.getMessage(), Toast.LENGTH_LONG).show();
                }else {
                  //  otpNumberOne.clearValue();
                    if(resource.getMessage()!=""){
                     //   Toast.makeText(OtpScreen.this, resource.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<RegisterResponce> call, Throwable t) {
                //   pd.dismiss();
                t.printStackTrace();
            }
        });

    }

    private void apiCall() {

//        @Part("type") RequestBody type,
//        @Part("boy_id") RequestBody boy_id,
//        @Part("boy_phone") RequestBody boy_phone,
//        @Part("boy_phone_code") RequestBody boy_phone_code,
//        @Part("otp") RequestBody otp

        String id;
        if(boy_id==null) {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
             id = pref.getString("boy_id", "");
        }else{
          id =  boy_id;
        }
        RequestBody rphnnumber = RequestBody.create(MediaType.parse("multipart/form-data"),phnnum);
        RequestBody rtype = RequestBody.create(MediaType.parse("multipart/form-data"),type);
        RequestBody rboy_phone_code = RequestBody.create(MediaType.parse("multipart/form-data"),"+91");
        RequestBody rboy_id = RequestBody.create(MediaType.parse("multipart/form-data"),"17");
        RequestBody rotp= RequestBody.create(MediaType.parse("multipart/form-data"),otpNumberOne.getValue());

        Call<RegisterResponce> getCate = ApiClient.getApiService().login(phnnum,"+91",otpNumberOne.getValue(),"1","1");
        getCate.enqueue(new Callback<RegisterResponce>() {
            @Override
            public void onResponse(Call<RegisterResponce> call, Response<RegisterResponce> response) {
                final RegisterResponce resource = response.body();
                if(resource==null){
                    return;
                }
              //  Log.d("resss",resource.toString());

                if(resource.getStatus().equals("ok")){
                    pref=getApplicationContext()
                            .getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("user_id",resource.getUser_id());
                    editor.putString("user_phone",resource.getBoy_phone());
                    editor.putString("user_email",resource.getUser_email());
                    editor.putString("user_name",resource.getUser_name());
                    editor.putString("user_profile",resource.getUser_profile());
                    editor.commit();
                    Util.loadhome(OtpScreen.this);

                    if(resource.getUser_profile().equals("1")) {

                        Intent ii = new Intent(OtpScreen.this, HomeScreen.class);
                        ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(ii);
                    }else{
                        Intent ii = new Intent(OtpScreen.this, MyProfileActivity.class);
                        ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        ii.putExtra("from","edit");

                        //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(ii);
                    }
                }else {
                    otpNumberOne.clearValue();
                    if(resource.getMessage()!=""){
                        Toast.makeText(OtpScreen.this, "wrong OTP", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponce> call, Throwable t) {
                //   pd.dismiss();
                t.printStackTrace();
            }
        });


    }


}