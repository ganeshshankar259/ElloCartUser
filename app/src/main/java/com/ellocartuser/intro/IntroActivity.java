package com.ellocartuser.intro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ellocartuser.Login;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.SplashResponce;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0 ;
    Button btnGetStarted;
    Animation btnAnim ;
    TextView tvSkip;
    ProgressDialog pd;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        // make the activity on full screen

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // when this activity is about to be launch we need to check if its openened before or not


        setContentView(R.layout.activity_intro);

        // hide the action bar

//        getSupportActionBar().hide();

        // ini views
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
       // btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
    //    tvSkip = findViewById(R.id.tv_skip);

        // fill list screen



        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);


        // setup tablayout with viewpager

        tabIndicator.setupWithViewPager(screenPager);

        // next button click Listner

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainActivity = new Intent(getApplicationContext(), Login.class);
                startActivity(mainActivity);


            }
        });

        // tablayout add change listener


        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

//                if (tab.getPosition() == mList.size()-1) {
//
//                    loaddLastScreen();
//
//                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        // Get Started button click listener

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //open main activity

                Intent mainActivity = new Intent(getApplicationContext(), Login.class);
                startActivity(mainActivity);
                // also we need to save a boolean value to storage so next time when the user run the app
                // we could know that he is already checked the intro screen activity
                // i'm going to use shared preferences to that process
                savePrefsData();
                finish();



            }
        });

        // skip button click listener

//        tvSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                screenPager.setCurrentItem(mList.size());
//            }
//        });

        apiCall();

    }


    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();


    }

    // show the GETSTARTED Button and hide the indicator and the next button
//    private void loaddLastScreen() {
//
//        btnNext.setVisibility(View.INVISIBLE);
//        btnGetStarted.setVisibility(View.VISIBLE);
//        tabIndicator.setVisibility(View.INVISIBLE);
//        // TODO : ADD an animation the getstarted button
//        // setup animation
//        btnGetStarted.setAnimation(btnAnim);
//
//
//
//    }


    private void apiCall() {

        pd = new ProgressDialog(IntroActivity.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        // pd.show();
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<SplashResponce> getCate = ApiClient.getApiService().getSplash();
        getCate.enqueue(new Callback<SplashResponce>() {
            @Override
            public void onResponse(Call<SplashResponce> call, Response<SplashResponce> response) {
                final SplashResponce resource = response.body();
                pd.dismiss();
          //      Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){

                    final List<ScreenItem> mList = new ArrayList<>();

                    if(resource.getSplash()==null){
                        return;
                    }
                    for(int i=0;i<resource.getSplash().size();i++) {
                        mList.add(new ScreenItem("", "", resource.getSplash().get(i).getSplImage()));
                    }
                    introViewPagerAdapter = new IntroViewPagerAdapter(IntroActivity.this,mList);
                    screenPager.setAdapter(introViewPagerAdapter);

                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            screenPager.post(new Runnable(){
                                @Override
                                public void run() {
                                    screenPager.setCurrentItem((screenPager.getCurrentItem()+1)%resource.getSplash().size());
                                }
                            });
                        }
                    };
                    timer = new Timer();
                    timer.schedule(timerTask, 3000, 3000);

//                    name.setText(resource.getBoy_name());
//                    address.setText(resource.getBoy_address());
//                    phone.setText(resource.getBoy_phone());
//
//                    Glide.with(getApplicationContext())
//                            .load(resource.getBoy_image())
//                            .fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
//                            .into(img);

                }else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(IntroActivity.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<SplashResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
               // Toast.makeText(IntroActivity.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }
}
