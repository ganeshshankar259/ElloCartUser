package com.ellocartuser.RoomsNew.room_info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ellocartuser.Login;
import com.ellocartuser.OtpScreen;
import com.ellocartuser.R;
import com.ellocartuser.RoomsNew.room_info.model.Example;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.ElloRoom2Response;
import com.ellocartuser.apiservices.Responce.RegisterResponce;
import com.ellocartuser.ellorooms_new.ElloDetailPage;
import com.ellocartuser.ellorooms_new.ElloRoomListPage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomInfo extends AppCompatActivity {

    ImageView back,mainimg,imageback;
    TextView book;
    RecyclerView catList,chips;
    TextView room,room_des,check_in,check_out,amount,amount_strick,r_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);

        book=findViewById(R.id.book);
        imageback=findViewById(R.id.imageback);
        book=findViewById(R.id.book);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        amount= findViewById(R.id.amount);
        amount_strick= findViewById(R.id.amount_strick);
        back= findViewById(R.id.back);
        room= findViewById(R.id.room);
        room_des= findViewById(R.id.room_des);
        check_in= findViewById(R.id.check_in);
        check_out= findViewById(R.id.check_out);
        mainimg= findViewById(R.id.mainimg);
        r_type= findViewById(R.id.r_type);
        catList= findViewById(R.id.catList);
        chips= findViewById(R.id.chips);

        catList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false);
//        chips.setLayoutManager(gridLayoutManager);
        chips.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii= new Intent(RoomInfo.this,ElloDetailPage.class);
                ii.putExtra("todate",getIntent().getStringExtra("todate"));
                ii.putExtra("fromdate",getIntent().getStringExtra("fromdate"));
                ii.putExtra("persons",getIntent().getStringExtra("persons"));
                ii.putExtra("rooms",getIntent().getStringExtra("rooms"));
                ii.putExtra("room_id",getIntent().getStringExtra("room_id"));

                startActivity(ii);
            }
        });

        apiCall();

    }

    private void apiCall() {

        ProgressDialog pd = new ProgressDialog(RoomInfo.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        pd.show();
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<ElloRoom2Response> getCate = ApiClient.getApiServiceforRooms().get_room_detail(getIntent().getStringExtra("room_id"));
        getCate.enqueue(new Callback<ElloRoom2Response>() {
            @Override
            public void onResponse(Call<ElloRoom2Response> call, Response<ElloRoom2Response> response) {
                final ElloRoom2Response resource = response.body();
                pd.dismiss();


                if(resource==null){
                    return;
                }
                try {
                    //   Log.d("resss",resource.toString());
                    if (resource.getStatus().equals("ok")) {

                        Glide.with(RoomInfo.this)
                                .load(resource.getDetails().get(0).getRoomImage())
                                .fitCenter().placeholder(R.drawable.placeholderello)
                                .into(mainimg);

                        r_type.setText(resource.getDetails().get(0).getRoomName());
                        room_des.setText(resource.getDetails().get(0).getRoom_description());
                        room.setText(resource.getDetails().get(0).getRoomName());
                        room.setText(resource.getDetails().get(0).getHotelAddress());
                        check_in.setText(resource.getDetails().get(0).getRoom_checkin());
                        check_out.setText(resource.getDetails().get(0).getRoom_checkout());

                        amount.setText("₹"+resource.getDetails().get(0).getRoom_price());
                        amount_strick.setPaintFlags(amount_strick.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        amount_strick.setText("₹"+resource.getDetails().get(0).getRoom_max_price());

                            List<String> gal_img=new ArrayList<>();
                        if(resource.getGallery().get(0).getRoomImage1()!=null &&resource.getGallery().get(0).getRoomImage1().trim()!=""){
                            gal_img.add(resource.getGallery().get(0).getRoomImage1());
                        }
                        if(resource.getGallery().get(0).getRoomImage2()!=null &&resource.getGallery().get(0).getRoomImage2().trim()!=""){
                               gal_img.add(resource.getGallery().get(0).getRoomImage2());
                            } if(resource.getGallery().get(0).getRoomImage3()!=null &&resource.getGallery().get(0).getRoomImage3().trim()!=""){
                                gal_img.add(resource.getGallery().get(0).getRoomImage3());
                            } if(resource.getGallery().get(0).getRoomImage4()!=null &&resource.getGallery().get(0).getRoomImage4().trim()!=""){
                                gal_img.add(resource.getGallery().get(0).getRoomImage4());
                            }  if(resource.getGallery().get(0).getRoomImage5()!=null && resource.getGallery().get(0).getRoomImage5().trim()!=""){
                                gal_img.add(resource.getGallery().get(0).getRoomImage5());
                            } if(resource.getGallery().get(0).getRoomImage6()!=null &&resource.getGallery().get(0).getRoomImage6().trim()!=""){
                                gal_img.add(resource.getGallery().get(0).getRoomImage6());
                            }

                            RoomGallaryAdapter adapter=new RoomGallaryAdapter(RoomInfo.this,gal_img,R.layout.roomgallarysingle);
                            catList.setAdapter(adapter);

//
//                        "swimming pool": "0",
//                                "wifi": "0",
//                                "ac": "0",
//                                "free parking": "0",
//                                "breakfast": "0",
//                                "spa": "0",
//                                "room service": "0",
//                                "gym": "0"
                        RoomChipsAdapter adapter1=new RoomChipsAdapter(RoomInfo.this,resource.getAmenities(),R.layout.roomchipssingle);
                        chips.setAdapter(adapter1);

                    } else {
//                        if (resource.getMessage() != "") {
//                            Toast.makeText(RoomInfo.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                        }
                    }
                }catch (Exception ex){

                }

            }

            @Override
            public void onFailure(Call<ElloRoom2Response> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                Toast.makeText(RoomInfo.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }
}