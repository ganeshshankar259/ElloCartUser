package com.ellocartuser.orders;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ellocartuser.LocalStore;

import java.util.ArrayList;
import java.util.List;

class OrdersTabAdapter  extends FragmentPagerAdapter {

    FragmentActivity mContext ;
    private List<String> names = new ArrayList<>();
    //  List<String> names;

    public OrdersTabAdapter(FragmentManager context, List<String> names) {
        super(context);
        //  this.mContext=context;
        this.names = names;
        //  this.names = subcatlistid;
        //  this.recylist = recylist;
    }

    @Override
    public Fragment getItem(int position) {

        if(position==0){
            return new OrdersPage().newInstance("pending","");
        }else if(position==1){
            return new OrdersPage().newInstance("completed","");
            //  return new OnGoing();
        }else {
            return new OrdersPage().newInstance("cancelled","");
        }
        
    }

//    public void setRoomList(List<String> roomList) {
//        this.subcatlist = roomList;
//        notifyDataSetChanged();
//    }


    @Override
    public int getCount() {
        return names.size();
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return names.get(position);
    }
}


