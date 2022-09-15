package com.ellocartuser.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.ellocartuser.R;
import com.ellocartuser.Register;
import com.ellocartuser.cart.CartDisplay;
import com.ellocartuser.home.homefragment.CategoryFragment;
import com.ellocartuser.home.homefragment.NearbystoreFragment;
import com.ellocartuser.home.homefragment.ProductDetailedPage;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.home.notification.NotificationFragment;
import com.ellocartuser.home.notification.NotificationScreen;
import com.ellocartuser.orders.OrderDetailedPageForPending;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class ExampleNotificationOpenedHandler implements OneSignal.OSNotificationOpenedHandler {

    Context context;
    public ExampleNotificationOpenedHandler(Context context) {
        this.context = context;
    }
    @Override
    public void notificationOpened(OSNotificationOpenedResult result) {
        OSNotificationAction.ActionType actionType = result.getAction().getType();
        JSONObject data = result.getNotification().getAdditionalData();
        String p1,p2,p3;
        System.out.println("custom raju "+data);

        if (data != null) {
            p1 = data.optString("p1", null);
            if (p1 != null){

//                SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
//                String id = pref.getString("user_id","");
//                if(id.equals("") || id==null){
//                    Util.PleaseLogin(context);
//                    return;
//                }else{
//
//                }

                if(p1.equals("HOME")){
                    Util.loadFragment123(new homefragment(), (FragmentActivity) context);
                } else if(p1.equals("ORDER")){

                    Util.loadFragment123(OrderDetailedPageForPending.newInstance(data.optString("p2", null), "","","",""), (FragmentActivity)context);


//                    Intent intent = new Intent(context, NotificationScreen.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);

                }else if(p1.equals("STORE")){

                    Util.loadFragment123(CategoryFragment.newInstance(data.optString("p3", null), data.optString("p2", null), ""), (FragmentActivity)context);

                }else if(p1.equals("URL")){
                    Intent i=new Intent(context, WebViewActivity.class);
                    i.putExtra("url",data.optString("p2", null));
                    i.putExtra("name","");
                    context.startActivity(i);
                }


            }
               // Log.e("OneSignalExample", "customkey set with value: " + p1);
        }

        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.getAction().getActionId());

      //  Util.loadFragment123(new CartDisplay(), (FragmentActivity) context);


//        Intent intent = new Intent(context, NotificationScreen.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
    }

}