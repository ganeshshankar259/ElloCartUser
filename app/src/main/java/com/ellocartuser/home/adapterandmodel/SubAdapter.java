package com.ellocartuser.home.adapterandmodel;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ellocartuser.LocalStore;
import com.ellocartuser.home.homefragment.B2BMainPage;

import java.util.ArrayList;
import java.util.List;


public class SubAdapter extends FragmentPagerAdapter {

   FragmentActivity mContext ;
    private List<String> names = new ArrayList<>();
  //  List<String> names;

    public SubAdapter(FragmentManager context, List<String> names) {
        super(context);
     //  this.mContext=context;
        this.names = names;
      //  this.names = subcatlistid;
      //  this.recylist = recylist;
    }

    @Override
    public Fragment getItem(int position) {

        if(position==0){
            return new LocalStore();
        }else{
            return new B2BMainPage();
          //  return new OnGoing();
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

