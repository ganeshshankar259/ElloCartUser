package com.ellocartuser.cart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
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
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CheckOutPaymentResponce;
import com.ellocartuser.apiservices.Responce.CouponResponce;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.apiservices.Responce.StoreInfoResponce;
import com.ellocartuser.apiservices.model.OtherCharges;
import com.ellocartuser.cart.checkoutmodel.CheckoutCheckResponce;
import com.ellocartuser.cart.checkoutmodel.OffersModel;
import com.ellocartuser.home.adapterandmodel.Address;
import com.ellocartuser.home.adapterandmodel.Coupons;
import com.ellocartuser.home.adapterandmodel.SliderModel;
import com.ellocartuser.home.homefragment.shopinfotab.MapsFragmentShopInfo;
import com.ellocartuser.home.homefragment.viewall.StoresBottomSheet;
import com.ellocartuser.orders.DetailedAdapter;
import com.razorpay.PaymentResultListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

import dev.skymansandy.scratchcardlayout.listener.ScratchListener;
import dev.skymansandy.scratchcardlayout.ui.ScratchCardLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class Checkout extends AppCompatActivity implements PaymentResultListener, DialogPayment.OnItemClickedpayment, ScratchListener , TipsAdapter.OnItemClickeGrid {
String live_key="";
    Dialog dialog;
    OffersModel offmodel=null;
    boolean scratchcmp=false;
    private final static int MY_REQUEST_CODE = 1;
    private static final int TEZ_REQUEST_CODE = 123;
    private static final String GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    ConstraintLayout walletblock,promowalletblock;
    String wallet_temp="0",wallet_avail="0";
    TextView wallettxt,promowallettxt ;//,walletprise;
    TipsAdapter.OnItemClickeGrid onclicktip;
    AppCompatButton wall_checkbox,promowall_checkbox;
    boolean promowall_checkbox_sts=false;
  //LinearLayout walletlayout;
    String distance,coup_name="";
    String elloorderid = "", paymentid = "";
    Address addressdata;
    String offer_status, count, total, date, order_type, finalamt, couponid = "0", couponamt = "";
    TextView new_storename,new_storeaddress,saveprise, name, address, totalitems, productprice, couponprice, totalamt, txtcoupon, deliverycharge;
    ProgressDialog pd;
    Button datee, orderbtn;
    String addrid = "", cartcod, deliveycharge, firstfinal;
    List<OtherCharges> other_charges;
    ImageView imgback;
    TextView offers;
    CardView carddetail,viewofferblock;
    String item_total, item_save;
    DialogPayment.OnItemClickedpayment onclick;
    HashMap<String, String> otherchargesmap = new HashMap<>();
   // LinearLayout dummy1, dummy2, dummy3, savepricelayout;
    TextView promoapplyed, offertxt;   //, dummy1txt, dummy2txt, dummy3txt, dummytxtvalue1, dummytxtvalue2, dummytxtvalue3;
    String  wallet="",tips="",pwallet="",type="";
    RecyclerView detaildata;
    Dialog dialogscratch;
    RecyclerView tiplist;
    TextView pay_useing,pay_type_useing;
    CheckBox pay_online,pay_cod;
    String current="online";
    TextView onn,cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        pay_online=findViewById(R.id.amt_online);
        pay_cod=findViewById(R.id.amt_cod);
        onn=findViewById(R.id.onn);
        cod=findViewById(R.id.cod);


//        pay_type_useing=findViewById(R.id.amt);
//        pay_useing=findViewById(R.id.textView22);

        detaildata=findViewById(R.id.detaildata);
        detaildata.setNestedScrollingEnabled(false);
        detaildata.setLayoutManager(new LinearLayoutManager(Checkout.this));

        new_storename=findViewById(R.id.new_storename);
        new_storeaddress=findViewById(R.id.new_storeaddress);

        onclick = this;
        onclicktip = this;
        //com.razorpay.Checkout.setKeyID("<YOUR_KEY_ID>");
        com.razorpay.Checkout.preload(Checkout.this);
        viewofferblock = findViewById(R.id.viewofferblock);

        try {

            addressdata = (Address) getIntent().getParcelableExtra("addressdata");
            item_save = getIntent().getStringExtra("item_save");

            new_storename.setText(getIntent().getStringExtra("nameseller"));
            new_storeaddress.setText(getIntent().getStringExtra("addressseller"));

            distance = getIntent().getStringExtra("distance");
            wallet = getIntent().getStringExtra("wallet");
            pwallet = getIntent().getStringExtra("pwallet");

            type = getIntent().getStringExtra("type");

            addrid = getIntent().getStringExtra("addressid");
            count = getIntent().getStringExtra("count");
            date = getIntent().getStringExtra("date");
          //  total = getIntent().getStringExtra("carttotal");
            offer_status = getIntent().getStringExtra("offer_status");
            Integer tt = Integer.parseInt(getIntent().getStringExtra("item_save")) + Integer.parseInt(getIntent().getStringExtra("carttotal"));
            total = String.valueOf(tt);
            deliveycharge = getIntent().getStringExtra("delivery_charges");
            other_charges = getIntent().getParcelableArrayListExtra("other_charges");
            //   int totalamtshow = Integer.valueOf(total) + Integer.valueOf(deliveycharge);

            order_type = getIntent().getStringExtra("order_type");
            cartcod = getIntent().getStringExtra("cartcod");

        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(Checkout.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

//        savepricelayout = findViewById(R.id.savepricelayout);
//        walletprise = findViewById(R.id.walletprise);
//        walletlayout = findViewById(R.id.walletlayout);
        walletblock = findViewById(R.id.walletblock);
        promowalletblock = findViewById(R.id.promowalletblock);
        wall_checkbox = findViewById(R.id.wall_checkbox);
        promowall_checkbox = findViewById(R.id.promowall_checkbox);
        wallettxt = findViewById(R.id.offerapplyed);
        promowallettxt = findViewById(R.id.promoofferapplyed);

        tiplist = findViewById(R.id.catList);

        saveprise = findViewById(R.id.saveprise);

        offertxt = findViewById(R.id.textView11);
        promoapplyed = findViewById(R.id.promoapplyed);
        saveprise = findViewById(R.id.saveprise);
      //  finalprice = findViewById(R.id.finalprice);
        deliverycharge = findViewById(R.id.deliverycharge);
        offers = findViewById(R.id.offers);
        txtcoupon = findViewById(R.id.txtcoupon);
        orderbtn = findViewById(R.id.orderbtn);
        datee = findViewById(R.id.datee);
        totalamt = findViewById(R.id.total);
        couponprice = findViewById(R.id.couponprice);
        totalitems = findViewById(R.id.totalitems);
        productprice = findViewById(R.id.productprice);
        name = findViewById(R.id.name);

        address = findViewById(R.id.address);
        imgback = findViewById(R.id.imageback);
        carddetail = findViewById(R.id.carddetail);
        datee.setText(date);

        name.setText(addressdata.getAddrName());
        address.setText(addressdata.getAddrAddress());

//        couponprice.setText("₹ 0");
//        totalitems.setText(count);
//        Integer tt = Integer.parseInt(getIntent().getStringExtra("item_save")) + Integer.parseInt(getIntent().getStringExtra("carttotal"));
//        productprice.setText("₹" + tt);
//        saveprise.setText("-₹" + item_save);
//        totalamt.setText("₹" + finalamt);

        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(offmodel!=null){
                    if(!offmodel.getP2().get(0).getAmt().equals("0")) {
                        showDialog(offmodel);
                    }else{
                        couponid="";
                        Toast.makeText(Checkout.this, offmodel.getP2().get(0).getMsg() , Toast.LENGTH_LONG).show();
                    }
                }

//                Intent ii = new Intent(getApplicationContext(), OfferScreenActivity.class);
//                SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//
//                String lat = pref.getString("latitude", "");
//                String longi = pref.getString("longitude", "");
//
//                ii.putExtra("addr_id", addrid);
//                ii.putExtra("longi", longi);
//                ii.putExtra("lat", lat);
//                startActivityForResult(ii, MY_REQUEST_CODE);
            }
        });

        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!total.equals("0")) {

                    if(pay_online.isChecked()){
                        apicallcheckout2("online");
                    }else  if(pay_cod.isChecked()) {
                        apicallcheckout2("cod");
                    }

//                 if(current.toString().toUpperCase().equals("ONLINE")) {
//
//                        apicallcheckout2("online");
//                    }else {
//                        apicallcheckout2("cod");
//                    }
//
                } else {

                    apicallcheckout2("online");

                }

            }
        });

        pay_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay_online.setChecked(true);
                pay_cod.setChecked(false);
            }
        });

        onn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay_online.setChecked(true);
                pay_cod.setChecked(false);
            }
        });


        pay_cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay_cod.setChecked(true);
                pay_online.setChecked(false);

            }
        });

        cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay_cod.setChecked(true);
                pay_online.setChecked(false);
            }
        });

//        pay_useing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                        DialogPayment bottomSheet = new DialogPayment(onclick, getIntent().getStringExtra("cod_status"));
//                        bottomSheet.show(getSupportFragmentManager(),
//                                "ModalBottomSheet");
//
//            }
//        });
//        pay_type_useing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                DialogPayment bottomSheet = new DialogPayment(onclick, getIntent().getStringExtra("cod_status"));
//                bottomSheet.show(getSupportFragmentManager(),
//                        "ModalBottomSheet");
//
//
//            }
//        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        wall_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//        {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//            {
//                if ( isChecked )
//                {
//                    wallet="1";
//                    apiCallrefdata();
//
//                }else{
//
//                    wallet="0";
//                    apiCallrefdata();
//
//                }
//
//            }
//        });

        wall_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    wallet="1";
                    apiCallrefdata();

                    wall_checkbox.setBackground(ContextCompat.getDrawable(Checkout.this, R.drawable.btncornorforcart_disable));
                    wall_checkbox.setText("Applied");
                    wall_checkbox.setEnabled(false);

            }
        });

        promowall_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pwallet="1";
                promowall_checkbox_sts=true;
                apiCallrefdata();
                promowall_checkbox.setBackground(ContextCompat.getDrawable(Checkout.this, R.drawable.btncornorforcart_disable));

                promowall_checkbox.setText("Applied");
                promowall_checkbox.setEnabled(false);

            }
        });

       // GridLayoutManager gridLayoutManager = new GridLayoutManager(Checkout.this,4,GridLayoutManager.VERTICAL,false);
       // tiplist.setLayoutManager(gridLayoutManager);
       tiplist.setLayoutManager(new LinearLayoutManager(Checkout.this, LinearLayoutManager.HORIZONTAL, false));


        apiCallrefdata();


    }

    public void apiCallrefdata() {

        pd = new ProgressDialog(Checkout.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        pd.show();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");

        Call<CheckoutCheckResponce> getCate = ApiClient.getApiService().check_delivery_checkout(id,addrid,couponid,wallet,tips,pwallet,type);
        getCate.enqueue(new Callback<CheckoutCheckResponce>() {
            @Override
            public void onResponse(Call<CheckoutCheckResponce> call, Response<CheckoutCheckResponce> response) {
                final CheckoutCheckResponce resource = response.body();
                pd.dismiss();
                //  Log.d("resss",resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    live_key=resource.getLive_key();
                    if(promowall_checkbox_sts){
                        String p_amt="";
                        for(int i=0;i<resource.getCharges().size();i++){

                            if(resource.getCharges().get(i).getP1().toLowerCase().contains("promo-wallet")){
                                p_amt = resource.getCharges().get(i).getP2();
                            }
                        }
                        if(!p_amt.equals("")) {
                            Toast.makeText(Checkout.this, "Cheers! You have redeemed " + p_amt + " on this order.", Toast.LENGTH_LONG).show();
                        }
                        promowall_checkbox_sts=false;
                    }

                    DetailedAdapter detailedAdapter= new DetailedAdapter(Checkout.this,resource.getCharges());
                    detaildata.setAdapter(detailedAdapter);

                    offmodel=resource.getOffers();
                    total=resource.getTotal();
                    if(total.equals("0")){
                        pay_type_useing.setText("ONLINE");
                    }
                    totalamt.setText("₹ " + resource.getTotal());
                    // wallet
                if(resource.getWallets().get(0).getWallet()!=null) {
                    wallettxt.setText("Available Balance ₹" + resource.getWallets().get(0).getWallet().get(0).getP2().get(0).getAmt());

                    if(resource.getWallets().get(0).getWallet().get(0).getP2().get(0).getAmt()!=null) {
                        if (resource.getWallets().get(0).getWallet().get(0).getP2().get(0).getAmt().equals("0")) {

                            walletblock.setVisibility(View.GONE);

                        } else {

                            walletblock.setVisibility(View.VISIBLE);

                        }
                    }else{
                        walletblock.setVisibility(View.GONE);
                    }

}else{
    walletblock.setVisibility(View.GONE);
}
                    if(resource.getWallets().get(0).getPromowallet().get(0).getP2().get(0).getAmt()!=null) {
                        if (resource.getWallets().get(0).getPromowallet().get(0).getP2().get(0).getAmt().equals("0")) {

                            promowalletblock.setVisibility(View.GONE);

                        } else {
                            promowallettxt.setText("Oops !! You are not eligible for the coupon. The coupon is valid only when the cart value is more than ₹"+resource.getSellerdtls().get(0).getSeller_promo_eligible());
                            promowall_checkbox.setVisibility(View.GONE);
                        //    System.out.println("sasas " + getIntent().getStringExtra("carttotal") + " as " + resource.getSeller_promo_eligible());
                            if (Integer.parseInt(resource.getSellerdtls().get(0).getSeller_promo_eligible()) <= Integer.parseInt(getIntent().getStringExtra("carttotal")) && resource.getSellerdtls().get(0).getSeller_promo_status().equals("1") &&
                                    resource.getUser_promo_elligible().equals("1")) {
                                promowallettxt.setText("Hurray!! You are eligible for this Promo ello wallet");
                                promowall_checkbox.setVisibility(View.VISIBLE);

                            }
                            promowalletblock.setVisibility(View.VISIBLE);

                        }
                    }else{
                        promowalletblock.setVisibility(View.GONE);
                    }
                    //coupon offers
                    if (offer_status.equals("1")) {
                        //visable
                        viewofferblock.setVisibility(View.VISIBLE);

                    } else {
                        //invisable
                        viewofferblock.setVisibility(View.GONE);
                    }

                    TipsAdapter tipadapter= new TipsAdapter(Checkout.this,resource.getTips().getP2(),onclicktip);
                    tiplist.setAdapter(tipadapter);
                    for(int i=0;i<resource.getTips().getP2().size();i++){
                        if(resource.getTips().getP2().get(i).getSelected().equals("1")){
                            tiplist.scrollToPosition(i);

                            break;
                        }
                    }


                } else {

                    if (resource.getMessage() != "") {
                        Toast.makeText(Checkout.this, resource.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<CheckoutCheckResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();

            }
        });

    }

//
//    public void updateCharges(){
//        if (other_charges != null) {
//            String dum1 = null, dum2 = null, dum3 = null;
//            for (int i = 0; i < other_charges.size(); i++) {
//                if (other_charges.get(i).getP3().equals("1")) {
//
//                    if (other_charges.get(i).getP1().equals("Delivery Charges")) {
//
//                        total = String.valueOf(Integer.valueOf(total) + Integer.valueOf(other_charges.get(i).getP2()));
//                        if (!other_charges.get(i).getP2().equals("0")) {
//
//                            deliverycharge.setText("₹" + other_charges.get(i).getP2()+"("+distance+")");
//
//                        } else {
//                            deliverycharge.setText("FREE "+"("+distance+")");
//                            //   deliverycharge.setTextColor(Color.parseColor("#57cc99"));
//                        }
//                        otherchargesmap.put(other_charges.get(i).getP0(), other_charges.get(i).getP2());
//
//                        continue;
//                    }
//
//                    if (dum1 == null) {
//                        dum1 = "0";
//
//                        try {
//                            total = String.valueOf(Integer.valueOf(total) + Integer.valueOf(other_charges.get(i).getP2()));
//
//                            dummy1.setVisibility(View.VISIBLE);
//
//                            dummy1txt.setText(other_charges.get(i).getP1());
//                            dummytxtvalue1.setText("₹" + other_charges.get(i).getP2());
//
//                            otherchargesmap.put(other_charges.get(i).getP0(), other_charges.get(i).getP2());
//
//
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//
//                    } else if (dum2 == null) {
//
//                        try {
//                            total = String.valueOf(Integer.valueOf(total) + Integer.valueOf(other_charges.get(i).getP2()));
//                            dummy2.setVisibility(View.VISIBLE);
//
//                            dummy2txt.setText(other_charges.get(i).getP1());
//                            dummytxtvalue2.setText("₹" + other_charges.get(i).getP2());
//                            otherchargesmap.put(other_charges.get(i).getP0(), other_charges.get(i).getP2());
//
//                            //   otherchargesmap.put(other_charges.get(i).getP0(), other_charges.get(i).getP2());
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//
//                    } else if (dum3 == null) {
//
//                        try {
//                            total = String.valueOf(Integer.valueOf(total) + Integer.valueOf(other_charges.get(i).getP2()));
//                            dummy3.setVisibility(View.VISIBLE);
//
//                            dummy3txt.setText(other_charges.get(i).getP1());
//                            dummytxtvalue3.setText("₹" + other_charges.get(i).getP2());
//                            otherchargesmap.put(other_charges.get(i).getP0(), other_charges.get(i).getP2());
//
//                            //otherchargesmap.put(other_charges.get(i).getP0(), other_charges.get(i).getP2());
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//
//                    }
//                }
//            }
//        }
//
//        if(Integer.parseInt(wallet)>0){
//            walletblock.setVisibility(View.VISIBLE);
//        }else{
//            walletblock.setVisibility(View.GONE);
//        }
//        wallettxt.setText("Available Balance ₹"+wallet);
//        wall_checkbox.setText("₹"+wallet);
//
////        finalamt = total;
////        firstfinal = total;
//    }

    public void apiCallOrder(String Type, String payRefid) {

        pd = new ProgressDialog(Checkout.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        pd.show();
        if(wallet.equals("1")){
            wallet_temp=wallet;
        }else{
            wallet_temp = "0";
        }


        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id", "");

        Call<MyProfileResponce> getCate = ApiClient.getApiService().placeorderPicked(id,addrid,couponid,wallet,pwallet,type,wallet_temp, date, "", couponid, order_type, payRefid, addrid, Type, finalamt, "0", otherchargesmap);
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                pd.dismiss();
                //  Log.d("resss",resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {

                    if(resource.getOrder_type2().equals("0")) {

                        Intent ii = new Intent(Checkout.this, ThankyouScreen.class);
                        ii.putExtra("imagename", getIntent().getStringExtra("imagename"));
                        ii.putExtra("storename", new_storename.getText().toString());
                        ii.putExtra("orderid", resource.getOrder_id());
                        ii.putExtra("address", new_storeaddress.getText().toString());
                        ii.putExtra("phonenumer", getIntent().getStringExtra("phonenumer"));
                        ii.putExtra("orderdate", resource.getOrder_date());
                        ii.putExtra("ordertime", resource.getOrder_time());
                        ii.putExtra("seller_time", getIntent().getStringExtra("seller_time"));
                        startActivity(ii);

                    }else  if(resource.getOrder_type2().equals("1")){

                        ThankyoupageAllOverIndia bottomSheet = new ThankyoupageAllOverIndia(resource.getOrder_id());
                        bottomSheet.setCancelable(false);
                        bottomSheet.show(getSupportFragmentManager(),
                                "ModalBottomSheet");

                    }

                } else {
                    if (resource.getMessage() != "") {
                        Toast.makeText(Checkout.this, resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<MyProfileResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();

            }
        });


    }


    public void showDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.deliverymode_dialog);

        RadioButton rbonline = dialog.findViewById(R.id.rbonline);
        RadioButton rbcod = dialog.findViewById(R.id.rbcod);
        // Toast.makeText(getApplicationContext(), "cartcod "+cartcod, Toast.LENGTH_LONG).show();
        if (cartcod == null) {
            cartcod = "1";
        }

        if (cartcod.equals("1")) {
            rbcod.setVisibility(View.VISIBLE);
        } else if (cartcod.equals("0")) {
            rbcod.setVisibility(View.INVISIBLE);
        }

        ImageView close = dialog.findViewById(R.id.close);
        Button continues = dialog.findViewById(R.id.continues);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        continues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String selected = "";
                if (rbonline.isChecked()) {
                    selected = "0";
                } else if (rbcod.isChecked()) {
                    selected = "1";
                }

                if (selected.equals("")) {
                    Toast.makeText(getApplicationContext(), "please select Payment type", Toast.LENGTH_LONG).show();
                    return;
                }
                dialog.dismiss();

                if (selected.equals("0")) {
                    //online
                    //     startPayment(Integer.parseInt(finalamt));
                } else if (selected.equals("1")) {
                    //cod
                    apiCallOrder("cod", "0");
                }
            }
        });

        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }


    public void startPayment(int amt, String payid) {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        // this.amt=amt;
        if(live_key==""){
            Toast.makeText(Checkout.this, "Error in payment: No Key " , Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        final Activity activity = Checkout.this;

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
//        Toast.makeText(Checkout.this, "Payment Success", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        //   orderStatusCheckApi();
        Toast.makeText(Checkout.this, "Payment Failed", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == MY_REQUEST_CODE) {

               // showDialogSave(data.getStringExtra("amt"));
//                finalamt = firstfinal;
                couponprice.setText("₹ 0");
                //savepricelayout.setVisibility(View.VISIBLE);
                saveprise.setText("-₹" + data.getStringExtra("amt"));

                int amtfinal = Integer.parseInt(finalamt) - Integer.valueOf(data.getStringExtra("amt"));
                finalamt = String.valueOf(amtfinal);
                totalamt.setText("₹ " + finalamt);
                couponprice.setText("₹ " + String.valueOf(data.getStringExtra("amt")));
                couponid = data.getStringExtra("id");
                couponamt = data.getStringExtra("amt");

                promoapplyed.setText(data.getStringExtra("Coupon Applied"));
                offertxt.setText("Offer Applied");
                offers.setVisibility(View.GONE);

                //  Toast.makeText(Checkout.this, data.getStringExtra("Coupon Applyed"), Toast.LENGTH_SHORT).show();
            } else if (requestCode == TEZ_REQUEST_CODE) {
                // Process based on the data in response.

                Log.d(" googlepay result", data.getStringExtra("Status"));
                System.out.println("googlepay data" + data);
                String statusdata = data.getStringExtra("Status");
                //  FAILURE  ,
                if (statusdata.equals("FAILURE")) {

                } else if (statusdata.equals("SUCCESS")) {

                }

            }
        }
    }

    public void showDialogSave(OffersModel cpn) {

        dialogscratch = new Dialog(Checkout.this);
        dialogscratch.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogscratch.setCancelable(false);
        dialogscratch.setContentView(R.layout.quote_download_dialog);

        //   ImageView close = dialogscratch.findViewById(R.id.close);
        ImageView done = dialogscratch.findViewById(R.id.close);
        ScratchCardLayout scratchCardLayout = dialogscratch.findViewById(R.id.scratchCard);
        TextView txt = dialogscratch.findViewById(R.id.txt);
        TextView title = dialogscratch.findViewById(R.id.title);

        txt.setText("₹ " + cpn.getP2().get(0).getAmt());
        title.setText(cpn.getP2().get(0).getTitle());
        scratchCardLayout.setRevealFullAtPercent(80);
        scratchCardLayout.setScratchListener(this);
        couponid = cpn.getP2().get(0).getId();
        couponamt = cpn.getP2().get(0).getAmt();
        coup_name = cpn.getP2().get(0).getTitle();

        dialogscratch.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogscratch.show();

    }

    public void showDialogForPaymentPayMode(String amount, String orderid, String payid) {

        final Dialog dialog = new Dialog(Checkout.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.paymentspaytypedialog);

        //   ImageView close = dialog.findViewById(R.id.close);
        ImageView gpay = dialog.findViewById(R.id.imageView17);
        ImageView ppay = dialog.findViewById(R.id.imageView18);
        ImageView razorpay = dialog.findViewById(R.id.imageView19);
        TextView razorpaytxt = dialog.findViewById(R.id.razorpaycharge);

        int razorpayprice = Integer.parseInt(amount) / 100 * 3;

        razorpaytxt.setText("CHARGE : ₹" + razorpayprice + " (include 3% Gateway Charges)");

        gpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPaymentgooglepay(amount, orderid);
            }
        });

        ppay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//
//        razorpay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startPayment(razorpayprice, paymentid,"");
//            }
//        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    @Override
    public void onItemClickProduct(int position, String catid) {
        if (position == 0) {
            //online
            pay_type_useing.setText("Online");
          //  apicallcheckout2("online");
            //      startPayment(Integer.parseInt(finalamt));
        } else if (position == 1) {
            //cod
            // apicallcheckout2("cod");
            pay_type_useing.setText("COD");

         //   apiCallOrder("cod", "0");

        }
    }


    public void startPaymentgooglepay(String amt, String orderid) {

        Uri uri =
                new Uri.Builder()
                        .scheme("upi")
                        .authority("pay")
                        .appendQueryParameter("pa", "ellocart@kotak")
                        .appendQueryParameter("pn", "ELLOCART")
                        .appendQueryParameter("mc", "5411")
                        .appendQueryParameter("tr", orderid)
                        .appendQueryParameter("tn", "ORDER")
                        .appendQueryParameter("am", amt)
                        .appendQueryParameter("cu", "INR")
                        .appendQueryParameter("url", "https://ellocart.com/app/testpay")
                        .build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage(GOOGLE_TEZ_PACKAGE_NAME);
        startActivityForResult(intent, TEZ_REQUEST_CODE);

    }


    public void apicallcheckout2(String comeingtype) {


        ProgressDialog pd1 = new ProgressDialog(Checkout.this);
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

//        pd.show();
        if(wallet.equals("1")){
            wallet_temp=wallet;
        }else{
            wallet_temp = "0";
        }

        Call<CheckOutPaymentResponce> getCate = ApiClient.getApiService().getcheckout2(id,addrid,wallet,pwallet,type,tips,wallet_temp, date, "", couponid, couponamt, order_type, "", addrid, comeingtype, finalamt, "0", otherchargesmap);
        getCate.enqueue(new Callback<CheckOutPaymentResponce>() {
            @Override
            public void onResponse(Call< CheckOutPaymentResponce> call, Response<CheckOutPaymentResponce> response) {
                final CheckOutPaymentResponce resource = response.body();
                pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {

                 // if(comeingtype.equals("online")) {
                    elloorderid = resource.getOrderId();
                    paymentid = resource.getOrderPayid();
                    System.out.println("paymentid : " + paymentid);

                    // showDialogForPaymentPayMode(resource.getOrderFinal(),resource.getOrderId(),resource.getOrderPayid());
                    if(resource.getOrderPayStatus().equals("1")){

                        Intent ii = new Intent(Checkout.this, ThankyouScreen.class);
                        ii.putExtra("imagename", getIntent().getStringExtra("imagename"));
                        ii.putExtra("storename", new_storename.getText().toString());
                        ii.putExtra("orderid", resource.getOrderId());
                        ii.putExtra("address", new_storeaddress.getText().toString());
                        ii.putExtra("phonenumer", getIntent().getStringExtra("phonenumer"));
                        ii.putExtra("orderdate", resource.getOrderDate());
                        ii.putExtra("ordertime", resource.getOrderTime());
                        ii.putExtra("seller_time", getIntent().getStringExtra("seller_time"));
                        startActivity(ii);

                    }else if(resource.getOrderPayStatus().equals("0")){
                        startPayment(Integer.parseInt(resource.getOrderFinal()), paymentid);
                    }

                } else {

                }

            }

            @Override
            public void onFailure(Call<CheckOutPaymentResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(Checkout.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void orderStatusCheckApi(String trasac) {
        ProgressDialog pd1 = new ProgressDialog(Checkout.this);
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

        Call<CheckOutPaymentResponce> getCate = ApiClient.getApiService().getcheckoutpay(id, elloorderid, trasac);
        getCate.enqueue(new Callback<CheckOutPaymentResponce>() {
            @Override
            public void onResponse(Call<CheckOutPaymentResponce> call, Response<CheckOutPaymentResponce> response) {
                final CheckOutPaymentResponce resource = response.body();
                pd1.dismiss();


                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {
                    Toast.makeText(Checkout.this, "Payment Success", Toast.LENGTH_SHORT).show();
                 if(resource.getOrder_type2().equals("0")){
                    Intent ii = new Intent(Checkout.this, ThankyouScreen.class);
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
                    Toast.makeText(Checkout.this, "Payment Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CheckOutPaymentResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(Checkout.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }


    public void showDialog(OffersModel cpn){

        dialog = new Dialog(Checkout.this,android.R.style.Theme_Holo_Light_NoActionBar);
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
                .load(cpn.getP2().get(0).getImg())
                .fitCenter()
                .into(img);

        btnapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialogSave(cpn);

                dialog.dismiss();

//                afterOfferViewed();
//                Intent intent = new Intent();
//                intent.putExtra("id", cpn.getCoupId() );
//                intent.putExtra("amt", String.valueOf(cpn.getCoupAmount()) );
//                intent.putExtra("amt", String.valueOf(cpn.getCoupAmount()) );
//                intent.putExtra("Coupon Applyed", String.valueOf(cpn.getCoupName()) );
//                setResult(RESULT_OK, intent);
//                finish();

            }
        });

        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

//        sectextview.setText("Add Will Close in ");

        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;

                sectextview.setText("Add Will Close in "+  f.format(sec)+" seconds");

                //  sectextview.setText("Apply Button Appear in " + f.format(sec)+" seconds");
            }
            // When the task is over it will print 00:00:00 there
            public void onFinish() {



                showDialogSave(cpn);

                dialog.dismiss();

//                applylayout.setVisibility(View.VISIBLE);
//
//                sectextview.setVisibility(View.GONE);
            }
        }.start();

        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    private void afterOfferViewed() {


    }

    @Override
    public void onScratchComplete() {
        scratchcmp=true;

        dialogscratch.dismiss();
        promoapplyed.setText("Coupon Applied ->  "+coup_name);
       // offertxt.setText("Offer Applied");
        offertxt.setText("");
        offers.setVisibility(View.GONE);

        apiCallrefdata();
    }

    @Override
    public void onScratchProgress(@NotNull ScratchCardLayout scratchCardLayout, int i) {

      //  Toast.makeText(Checkout.this,"percentage = "+i,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onScratchStarted() {

    }

    @Override
    public void onItemClicktip(int position, String carid, String name) {
if(tips.equals(carid)){
    tips="0";
}else {
    tips = carid;
}
        apiCallrefdata();

    }
}