package com.ellocartuser.home.homefragment.shopinfotab;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.ProductDetailedResponce;
import com.ellocartuser.home.adapterandmodel.ProductDetailedAdapter;
import com.ellocartuser.home.adapterandmodel.ProductDetailedSubVarentAdapter;
import com.ellocartuser.home.adapterandmodel.ReviewAdapter;
import com.ellocartuser.home.adapterandmodel.ScreenItem;
import com.ellocartuser.home.adapterandmodel.SliderModel;
import com.ellocartuser.orders.DeliveryReviewScreen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopReviews#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopReviews extends Fragment implements ReviewAdapter.OnItemClickedNear {
        ReviewAdapter reviewAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ReviewAdapter.OnItemClickedNear onclick;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
LinearLayout noreview;
    public ShopReviews() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopReviews.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopReviews newInstance(String param1, String param2) {
        ShopReviews fragment = new ShopReviews();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
RecyclerView reviewlist;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { // 8317683180
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_shop_reviews, container, false);
        reviewlist = view.findViewById(R.id.reviewlist);
        noreview = view.findViewById(R.id.noreview);
        reviewlist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

//        reviewAdapter=new ReviewAdapter(getActivity(),resource.getReviews());
//        reviewlist.setAdapter(reviewAdapter);
        onclick=this;
        apicall();
        return view;
    }

    public void apicall(){
        ProgressDialog pd1 = new ProgressDialog(getActivity());
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(true);
        pd1.show();

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        Call<ProductDetailedResponce> getCate = ApiClient.getApiService().get_store_reviews(mParam1,id);
        getCate.enqueue(new Callback<ProductDetailedResponce>() {
            @Override
            public void onResponse(Call<ProductDetailedResponce> call, Response<ProductDetailedResponce> response) {
                final ProductDetailedResponce resource = response.body();

                pd1.dismiss();

                // Log.d("resss", resource.toString());
                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    reviewlist.setVisibility(View.VISIBLE);
                    noreview.setVisibility(View.GONE);
                    //catstore
                    if(resource.getRatings().size()!=0){

                        reviewAdapter=new ReviewAdapter(getActivity(),resource.getRatings(),"shopereview",id,onclick);
                        reviewlist.setAdapter(reviewAdapter);
                    }else{
                  //      reviewlayout.setVisibility(View.GONE);
                    }


                } else {

                    noreview.setVisibility(View.VISIBLE);
                    reviewlist.setVisibility(View.GONE);
//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<ProductDetailedResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemClickededit(int position,String revid,String img,String name ,String msg,String rate) {

        Intent ii = new Intent(getActivity(), DeliveryReviewScreen.class);
        ii.putExtra("type","shopedit");
        ii.putExtra("name",name);
        ii.putExtra("img",img);
        ii.putExtra("address","");
        ii.putExtra("orderid","");
        ii.putExtra("sellerid","");
        ii.putExtra("reviewid",revid);
        ii.putExtra("stars",rate);
        ii.putExtra("msg",msg);
        getActivity().startActivity(ii);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            //TODO now it's visible to user
            apicall();
        } else {
            //TODO now it's invisible to user
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            apicall();

            //TODO now visible to user
        } else {
            //TODO now invisible to user
        }
    }
}