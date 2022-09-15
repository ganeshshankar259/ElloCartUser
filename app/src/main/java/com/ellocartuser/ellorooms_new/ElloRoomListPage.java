package com.ellocartuser.ellorooms_new;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.RoomsNew.room_info.RoomInfo;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.ElloRoomFilterResponse;
import com.ellocartuser.ellorooms_new.adapter.HomeDisplayAdapter;
import com.ellocartuser.ellorooms_new.adapter.ListDisplayAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElloRoomListPage extends AppCompatActivity implements ListDisplayAdapter.OnItemClickedCart{
    ListDisplayAdapter.OnItemClickedCart onclick;
    RecyclerView list;
    ProgressDialog pd;
    TextView filter;
    int REQUEST_CODE_EXAMPLE=1;
    ImageView imageback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ello_room_list_page);
        onclick=this;
        filter=findViewById(R.id.filter);
        list=findViewById(R.id.list);
        imageback=findViewById(R.id.imageback);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        list.setNestedScrollingEnabled(false);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii= new Intent(ElloRoomListPage.this,FilterPage.class);
                // Start DetailActivity with the request code
                startActivityForResult(ii, REQUEST_CODE_EXAMPLE);
            }
        });
        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);


        apiCall(pref.getString("rooms_latitude",""),pref.getString("rooms_longitude",""),"","","");


    }


    private void apiCall(String lat,String longi,String price,String lang,String am) {

        pd = new ProgressDialog(ElloRoomListPage.this);
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

        Call<ElloRoomFilterResponse> getCate = ApiClient.getApiServiceforRooms().get_rooms1("filter",getIntent().getStringExtra("fromdate"),getIntent().getStringExtra("todate"),getIntent().getStringExtra("persons"),getIntent().getStringExtra("rooms"),lat,longi,price,lang,"",am);
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

                    ListDisplayAdapter adapter = new ListDisplayAdapter(ElloRoomListPage.this,resource.getRooms(),onclick);
                    list.setAdapter(adapter);

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


    @Override
    public void onItemClicked(int position, String mParam1, String mParam2) {

        Intent ii= new Intent(ElloRoomListPage.this, RoomInfo.class);
        ii.putExtra("todate",getIntent().getStringExtra("todate"));
        ii.putExtra("fromdate",getIntent().getStringExtra("fromdate"));
        ii.putExtra("persons",getIntent().getStringExtra("persons"));
        ii.putExtra("rooms",getIntent().getStringExtra("rooms"));
        ii.putExtra("room_id",mParam1);
        startActivity(ii);

    }

    @Override
    public void onItemClickedView(int position, String mParam1, String mParam2) {
        Intent ii= new Intent(ElloRoomListPage.this,ElloDetailPage.class);
        ii.putExtra("todate",getIntent().getStringExtra("todate"));
        ii.putExtra("fromdate",getIntent().getStringExtra("fromdate"));
        ii.putExtra("persons",getIntent().getStringExtra("persons"));
        ii.putExtra("rooms",getIntent().getStringExtra("rooms"));
        ii.putExtra("room_id",mParam1);
        startActivity(ii);
    }


    // onActivityResult only get called
    // when the other Activity previously started using startActivityForResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // First we need to check if the requestCode matches the one we used.
        if(requestCode == REQUEST_CODE_EXAMPLE) {

            // The resultCode is set by the DetailActivity
            // By convention RESULT_OK means that whatever
            // DetailActivity did was executed successfully
            if(resultCode == Activity.RESULT_OK) {
                // Get the result from the returned Intent
                final String result = data.getStringExtra(FilterPage.EXTRA_DATA);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

                apiCall(pref.getString("rooms_latitude",""),pref.getString("rooms_longitude",""),data.getStringExtra("price"),data.getStringExtra("lang"),data.getStringExtra("am"));

                // Use the data - in this case, display it in a Toast.
              //  Toast.makeText(this, "Result: " + result, Toast.LENGTH_LONG).show();
            } else {
                // setResult wasn't successfully executed by DetailActivity
                // Due to some error or flow of control. No data to retrieve.
            }
        }
    }
}