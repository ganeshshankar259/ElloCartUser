package com.ellocartuser.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ellocartuser.R;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ThankyoupageAllOverIndia extends BottomSheetDialogFragment {
    Button button;
    TextView ordertxt;
String orderid;

    public ThankyoupageAllOverIndia( String orderid) {
        this.orderid=orderid;
      //  this.cod_status=cod_status;
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_thankyoupage_all_over_india, container, false);


        button = view.findViewById(R.id.button);
        ordertxt = view.findViewById(R.id.ordertxt);
     //   ordered = view.findViewById(R.id.ordered);
        ordertxt.setText(orderid);
        //  ordertxt.setText("Your Order "+getIntent().getStringExtra("orderid")+" has been Placed Successfully");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii = new Intent(getActivity(), HomeScreen.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ii);
            }
        });
        return view;
    }
}