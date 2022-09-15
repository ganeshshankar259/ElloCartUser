package com.ellocartuser.home.homefragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.ProductDetailedResponce;
import com.ellocartuser.home.adapterandmodel.Review;
import com.ellocartuser.home.adapterandmodel.ReviewAdapter;
import com.ellocartuser.orders.DeliveryReviewScreen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserReviews#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserReviews extends Fragment implements ReviewAdapter.OnItemClickedNear {
        ReviewAdapter reviewAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<Review> revdata=null;
    ReviewAdapter.OnItemClickedNear onclick;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Boolean dd=false;
    LinearLayout noreview;
    public UserReviews() {
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
    public static UserReviews newInstance(List<Review> param1, String param2) {
        UserReviews fragment = new UserReviews();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    ImageView imageback;
RecyclerView reviewlist;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            revdata = getArguments().getParcelableArrayList(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
       // apicall();
        if(dd) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { // 8317683180
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user_reviews, container, false);
        imageback = view.findViewById(R.id.imageback);
        reviewlist = view.findViewById(R.id.reviewlist);
        noreview = view.findViewById(R.id.noreview);
        reviewlist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        onclick=this;
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        if(revdata!=null || revdata.size()!=0) {
            reviewAdapter = new ReviewAdapter(getActivity(), revdata, "productpage",id,onclick);
            reviewlist.setAdapter(reviewAdapter);

            noreview.setVisibility(View.GONE);
            reviewlist.setVisibility(View.VISIBLE);

        }  if( revdata.size()==0) {

            noreview.setVisibility(View.VISIBLE);
            reviewlist.setVisibility(View.GONE);
//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
        }
        //apicall();
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

        System.out.println("rajj called");
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
dd=true;
        Intent ii = new Intent(getActivity(), DeliveryReviewScreen.class);
        ii.putExtra("type","useredit");
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

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//
//        super.onHiddenChanged(hidden);
//
//        System.out.println("rajj called "+hidden);
//
//
//        if (hidden) {
//            //do when hidden
//        } else {
//            //do when show
//           apicall();
//        }
//    }
//
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        System.out.println("rajj called  2"+isVisibleToUser );
//        if (isVisibleToUser) {
//            apicall();
//        }
//        else {
//        }
//    }
//
//    @Override
//    public void setMenuVisibility(boolean isvisible) {
//        super.setMenuVisibility(isvisible);
//        if (isvisible){
//            Log.d("Viewpager", "fragment is visible ");
//        }else {
//            Log.d("Viewpager", "fragment is not visible ");
//        }
//    }
}