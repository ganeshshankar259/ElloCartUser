package com.ellocartuser.home.homefragment.shopinfotab;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ellocartuser.home.homefragment.ProductsPage;
import com.ellocartuser.orders.OrdersPage;

import java.util.ArrayList;
import java.util.List;

class ShopTabAdapter extends FragmentPagerAdapter {

    FragmentActivity mContext ;
    private List<String> names = new ArrayList<>();
    String seller_id,cat_id,subcat_id;
    //  List<String> names;

    public ShopTabAdapter(FragmentManager context, List<String> names,String seller_id,String cat_id,String subcat_id) {
        super(context);
        //  this.mContext=context;
        this.names = names;
        this.seller_id = seller_id;
        this.cat_id = cat_id;
        this.subcat_id = subcat_id;
        //  this.names = subcatlistid;
        //  this.recylist = recylist;
    }

    @Override
    public Fragment getItem(int position) {

        if(position==0){
           // Util.loadFragment(ProductsPage.newInstance(sellerid,categoryid,subcatid),getActivity());
            return new ProductsPage().newInstance(seller_id,cat_id,subcat_id);
        }else if(position==1){
//            mContext.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                }
//            });
            return new ShopeInfo().newInstance(seller_id,"");
            //  return new OnGoing();
        }else {
            return new ShopReviews().newInstance("","");
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


