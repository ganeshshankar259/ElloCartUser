package com.ellocartuser.orders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.apiservices.Responce.OrdersResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class ReviewScreen extends AppCompatActivity {
    ProgressDialog pd;
    String orderid,productid,type,amount,image,address,sellerid;
    RatingBar ratingBar;
    EditText msg;
    Button submitbtn;
    ImageView img,imageback;
    TextView storename,amt;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_review_screen);

        constraintLayout=findViewById(R.id.constraintLayout);
        submitbtn=findViewById(R.id.submitbtn);
        amt=findViewById(R.id.amt);
        storename=findViewById(R.id.storename);
        img=findViewById(R.id.img);
        msg=findViewById(R.id.editText);
        ratingBar=findViewById(R.id.rateing);
        imageback=findViewById(R.id.imageback);

            submitbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(validation()){
                        if(type.equals("product")) {
                            apiCall(type, productid, orderid);
                        }else if(type.equals("seller")){
                            apiCallStore(type,sellerid,orderid);
                        }
                    }
                }
            });

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

            image=getIntent().getStringExtra("img");
            orderid=getIntent().getStringExtra("orderid");

            type=getIntent().getStringExtra("type");

            Glide.with(getApplicationContext())
                    .load(image)
                    .fitCenter().placeholder(R.drawable.placeholderello)
                    .into(img);

            if(type.equals("product")) {
                productid = getIntent().getStringExtra("productid");
                amount = getIntent().getStringExtra("amt");
                constraintLayout.setVisibility(View.VISIBLE);

                amt.setText("₹"+amount);
            }else if(type.equals("seller")) {
                address = getIntent().getStringExtra("address");
                sellerid = getIntent().getStringExtra("sellerid");
                constraintLayout.setVisibility(View.VISIBLE);

                amt.setText(address);
            }

            storename.setText(getIntent().getStringExtra("name"));

       // sellerid

    }

    private boolean validation() {
        if(msg.getText().toString().length()==0){
            Toast.makeText(ReviewScreen.this, "Please Enter Msg and Rating", Toast.LENGTH_LONG).show();
            return false;
        }
        return  true;
    }

    private void apiCall(String type,String productid, String orderid) {

        pd = new ProgressDialog(getApplicationContext());
        pd.setMessage("Loading...");
   //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
//        // pd.show();
//        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");

//        @Field("type") String type,
//        @Field("user_id") String category_id,
//        @Field("product_id") String product_id,
//        @Field("order_id") String order_id,
//        @Field("review_rate") String review_rate,
//        @Field("review_message") String review_message

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        Call<MyProfileResponce> getCate = ApiClient.getApiService().setReview(type,id,productid,orderid,"",String.valueOf(ratingBar.getRating()),msg.getText().toString());
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                pd.dismiss();
                //  Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){

                        onBackPressed();

                }else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<MyProfileResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void apiCallStore(String type,String sellerid, String orderid) {

        pd = new ProgressDialog(ReviewScreen.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
    //    // pd.show();
//        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");

//        @Field("type") String type,
//        @Field("user_id") String category_id,
//        @Field("product_id") String product_id,
//        @Field("order_id") String order_id,
//        @Field("review_rate") String review_rate,
//        @Field("review_message") String review_message

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        Call<MyProfileResponce> getCate = ApiClient.getApiService().setStoreReview(type,id,sellerid,orderid,String.valueOf(ratingBar.getRating()),msg.getText().toString());
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                pd.dismiss();
                //  Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){

                    onBackPressed();

                }else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<MyProfileResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }
}