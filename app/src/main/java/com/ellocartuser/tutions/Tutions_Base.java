package com.ellocartuser.tutions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ellocartuser.R;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.rooms_old.Room_Subc_Fragment;
import com.ellocartuser.rooms_old.Room_info_Fragment;
import com.ellocartuser.rooms_old.SeeMore_HotelsList_Fragment;

public class Tutions_Base extends AppCompatActivity {

    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutions_base);

        type= getIntent().getStringExtra("type");
        if(type.equals("TUTAPPOINT")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container123,new Tut_Appoint_Fragment()).commit();
        }
        else if(type.equals("ROOM_INFO")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container123,new Room_info_Fragment()).commit();
        }
        else if(type.equals("ROOM_CATOGERY")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container123,new Room_Subc_Fragment()).commit();
        }
        else if(type.equals("ROOM_SEEMORE")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container123,new SeeMore_HotelsList_Fragment()).commit();
        }
        else if(type.equals("HOME_FRAGMENT")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container123,new homefragment()).commit();
        }

    }
}