package com.ellocartuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.setting.MyProfileActivity;

public class EmptyOfferPage extends AppCompatActivity {

    ImageView img;
    Button claim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_offer_page);

        img=findViewById(R.id.img);
        claim=findViewById(R.id.claim);

        if(getIntent().getStringExtra("img_url")!="") {
            Glide.with(getApplicationContext())
                    .load(getIntent().getStringExtra("img_url"))
                    .fitCenter()//.placeholder(R.drawable.placeholderello)
                    .into(img);
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent dash = new Intent(EmptyOfferPage.this, HomeScreen.class);
                dash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(dash);

            }
        });
    }
}