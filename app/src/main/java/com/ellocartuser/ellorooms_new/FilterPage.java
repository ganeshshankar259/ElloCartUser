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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.ElloRoomFilterResponse;
import com.ellocartuser.cart.AddAddress;
import com.ellocartuser.ellorooms_new.adapter.Fillter2Adapter;
import com.ellocartuser.ellorooms_new.adapter.FillterAdapter;
import com.ellocartuser.ellorooms_new.models.Amenities;
import com.ellocartuser.ellorooms_new.models.Languages;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterPage extends AppCompatActivity implements FillterAdapter.OnItemClicked , Fillter2Adapter.OnItemClicked {
    FillterAdapter.OnItemClicked onclick1;
    Fillter2Adapter.OnItemClicked onclick2;
    ImageView imageback;
    RecyclerView list1,list2,list3;
    ProgressDialog pd;
    TextView apply;
    CheckBox high_to_low,low_to_high;
    List<Languages> languages=new ArrayList<>();
    List<Amenities> amenities=new ArrayList<>();
    Fillter2Adapter adapter2=null;
    FillterAdapter adapter=null;
    String lang_select="",am_select="",price_select="";
    public static final String EXTRA_DATA = "EXTRA_DATA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_page);
        imageback=findViewById(R.id.imageback);


        onclick1=this;
        onclick2=this;
        apply=findViewById(R.id.filter);
        high_to_low=findViewById(R.id.high_to_low);
        low_to_high=findViewById(R.id.low_to_high);
        list1=findViewById(R.id.list1);
        list2=findViewById(R.id.list2);
        list3=findViewById(R.id.list3);

        list1.setNestedScrollingEnabled(false);
        list1.setHasFixedSize(true);
        list1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        list2.setNestedScrollingEnabled(false);
        list2.setHasFixedSize(true);
        list2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        list3.setNestedScrollingEnabled(false);
        list3.setHasFixedSize(true);
        list3.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        addApiCall();

imageback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        onBackPressed();
    }
});

        //
        low_to_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                low_to_high.setChecked(true);
                high_to_low.setChecked(false);
                price_select="0";
            }
        });
        high_to_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                low_to_high.setChecked(false);
                high_to_low.setChecked(true);
                price_select="1";

            }
        });


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent data = new Intent();

                // Add the required data to be returned to the MainActivity
                data.putExtra(EXTRA_DATA, "Some interesting data!");
                data.putExtra("price", price_select);
                data.putExtra("lang", lang_select);
                data.putExtra("am", am_select);

                // Set the resultCode as Activity.RESULT_OK to
                // indicate a success and attach the Intent
                // which contains our result data
                setResult(Activity.RESULT_OK, data);

                // With finish() we close the DetailActivity to
                // return back to MainActivity
                finish();
            }
        });

    }

    private void addApiCall() {

        pd = new ProgressDialog(FilterPage.this);
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

        Call<ElloRoomFilterResponse> getCate = ApiClient.getApiServiceforRooms().getlanguages("1");
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

                    languages = resource.getLanguages();
                     adapter = new FillterAdapter(FilterPage.this,resource.getLanguages(),onclick1);
                    list2.setAdapter(adapter);
                    addApiCall2();
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


    private void addApiCall2() {

        pd = new ProgressDialog(FilterPage.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        if(!pd.isShowing()) {
            pd.show();
        }
        // pd.show();

        Call<ElloRoomFilterResponse> getCate = ApiClient.getApiServiceforRooms().get_amenities("1");
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
                    amenities = resource.getAmenities();
                   adapter2 = new Fillter2Adapter(FilterPage.this,resource.getAmenities(),onclick2);
                    list3.setAdapter(adapter2);

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
    public void onItemClickedAmb(int position, String mParam1, String mParam2) {

        for(int i=0;i<amenities.size();i++){
           amenities.get(i).setSelect(0);
        }
        amenities.get(position).setSelect(1);
        am_select=amenities.get(position).getaId();
        adapter2.setData(amenities);

    }

    @Override
    public void onItemClickedLang(int position, String mParam1, String mParam2) {

        for(int i=0;i<languages.size();i++){
            languages.get(i).setSelect(0);
        }
        languages.get(position).setSelect(1);
        lang_select=languages.get(position).getLang_id();
        adapter.setData(languages);

    }


    @Override
    public void onBackPressed() {
        // When the user hits the back button set the resultCode
        // as Activity.RESULT_CANCELED to indicate a failure
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }
}