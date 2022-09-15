package com.ellocartuser.rooms_old;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.rooms_old.ModelsandResponces.BookingsHistory_Responce;
import com.ellocartuser.rooms_old.ModelsandResponces.BookingsHistory_adapter;
import com.ellocartuser.rooms_old.ModelsandResponces.BookinsHistory_M;
import com.ellocartuser.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Bookings_History_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bookings_History_Fragment extends Fragment implements BookingsHistory_adapter.OnItemClickedproduct{



    RecyclerView recyclerView;
    ProgressDialog pd1;
    BookingsHistory_adapter adapter;
    BookingsHistory_adapter.OnItemClickedproduct onClick;
    List<BookinsHistory_M> datalist=new ArrayList<>();
    List<BookingsHistory_Responce> datalist1=new ArrayList<>();
    //String cartsellerid=null,carttotalcount="",store_o="0";
    ImageView backbutton;
    SharedPreferences pref;
    TextView txt_totalrooms,txt_grandtotal,txt_advance,txt_remain_amount,txt_hotelname,txt_cancel;
    String orderid;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Bookings_History_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Bookings_History_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Bookings_History_Fragment newInstance(String param1, String param2) {
        Bookings_History_Fragment fragment = new Bookings_History_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_bookings_history, container, false);

        View view=inflater.inflate(R.layout.fragment_bookings_history, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        txt_totalrooms = view.findViewById(R.id.txt_totalrooms);
        txt_grandtotal = view.findViewById(R.id.txt_grandtotal);
        txt_advance = view.findViewById(R.id.txt_advance);
        txt_remain_amount = view.findViewById(R.id.txt_remain_amount);
        txt_hotelname = view.findViewById(R.id.txt_hotelname);
        txt_cancel = view.findViewById(R.id.txt_cancel);


        backbutton = view.findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Util.loadFragment(new BookedRoomsList_Fragment(),getActivity(), Bookings_History_Fragment.this);
            }
        });
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Util.loadFragment(new BookedRoomsList_Fragment(),getActivity(), Bookings_History_Fragment.this);
                String type = "cancel";
               // String order = "623ef735d383e";

                SharedPreferences pref = getActivity().getSharedPreferences("GM_B_ORDERID", Context.MODE_PRIVATE);
                String b_orderid = pref.getString("b_orderid","");
                apiCall_forcancel(type,b_orderid);

            }
        });
        onClick=this;
        adapter = new BookingsHistory_adapter(getActivity(), null, onClick);
        recyclerView.setAdapter(adapter);

        apiCall();
        return view;
    }


    private void apiCall() {
        pd1 = new ProgressDialog(getActivity());
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();

        SharedPreferences pref = getActivity().getSharedPreferences("GM_B_ORDERID", Context.MODE_PRIVATE);
        String b_orderid = pref.getString("b_orderid","");

        // String d_id = "1";
        String id = "66";
        String type = "get";
//        String b_roomsubcat = "1";
        Call<BookingsHistory_Responce> getCate = ApiClient.getApiServiceforservice().bookings_history_display(id,type,b_orderid);
        getCate.enqueue(new Callback<BookingsHistory_Responce>() {
            @Override
            public void onResponse(Call<BookingsHistory_Responce> call, Response<BookingsHistory_Responce> response) {
                final BookingsHistory_Responce resource = response.body();
                pd1.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    //catstore
                    //store_o=resource.getdSpecialid();
                    datalist=resource.getRoomsData();

                    if (getActivity()!=null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                List<Doc_Profile_List_M> temp = new ArrayList();
//                                for(int i=0;i<datalist.size();i++){
////                                    if(datalist.get(i).().contains(text) || datalist.get(i).getProductName().toLowerCase().contains(text)){
////                                        temp.add(datalist.get(i));
////                                    }
//                                    //resource.getProfile().get(i);
//                                    temp.add(datalist.get(i));
//
//                                }
//                                int i = 1;
                                txt_totalrooms.setText(String.valueOf(resource.getTotalRooms()));
                                txt_grandtotal.setText("\u20B9"+String.valueOf(resource.getGrandTotal()));
                                txt_advance.setText("\u20B9"+String.valueOf(resource.getEllocartPercentage()));
                                txt_remain_amount.setText("\u20B9"+String.valueOf(resource.getBalanceAmount()));
//                                txt_hotelname.setText(resource.getRsubTitle());

                                adapter.updateList(resource.getRoomsData());
                                //adapter.updateList(resource.getProduct());
                                //  recyclerView.setAdapter(adapter);
                            }
                        });

                    }

                } else {

//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<BookingsHistory_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public void onItemClickProduct(int position, String order_id) {

        orderid = order_id;
//        String type = "cancel";
//        apiCall_forcancel(type,order_id);
    }

    @Override
    public void onItemClickaddtocart(int position, String catid) {

    }

    @Override
    public void onItemClickaddtocartupdatecount(int position, String proid, String qty) {

    }


    private void apiCall_forcancel(String type,String order_id) {
        pd1 = new ProgressDialog(getActivity());
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();

        //SharedPreferences pref = getActivity().getSharedPreferences("GM_B_ORDERID", Context.MODE_PRIVATE);
        //String b_orderid = pref.getString("b_orderid","");

        // String d_id = "1";
        String id = "63";
        //String type = "get";
//        String b_roomsubcat = "1";
        Call<BookingsHistory_Responce> getCate = ApiClient.getApiServiceforservice().cancle_booking(type,order_id);
        getCate.enqueue(new Callback<BookingsHistory_Responce>() {
            @Override
            public void onResponse(Call<BookingsHistory_Responce> call, Response<BookingsHistory_Responce> response) {
                final BookingsHistory_Responce resource = response.body();
                pd1.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    //catstore
                    //store_o=resource.getdSpecialid();
                    //datalist=resource.getRoomsData();
                    Toast.makeText(getActivity(), "Success" + resource.getStatus(), Toast.LENGTH_LONG).show();
                    if (getActivity()!=null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                List<Doc_Profile_List_M> temp = new ArrayList();
//                                for(int i=0;i<datalist.size();i++){
////                                    if(datalist.get(i).().contains(text) || datalist.get(i).getProductName().toLowerCase().contains(text)){
////                                        temp.add(datalist.get(i));
////                                    }
//                                    //resource.getProfile().get(i);
//                                    temp.add(datalist.get(i));
//
//                                }
//                                int i = 1;
//                                txt_totalrooms.setText(String.valueOf(resource.getTotalRooms()));
//                                txt_grandtotal.setText("\u20B9"+String.valueOf(resource.getGrandTotal()));
//                                txt_advance.setText("\u20B9"+String.valueOf(resource.getEllocartPercentage()));
//                                txt_remain_amount.setText("\u20B9"+String.valueOf(resource.getBalanceAmount()));
////                                txt_hotelname.setText(resource.getRsubTitle());
//
//                                adapter.updateList(resource.getRoomsData());
//                                //adapter.updateList(resource.getProduct());
//                                //  recyclerView.setAdapter(adapter);
                            }
                        });

                    }

                } else {

//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<BookingsHistory_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }
}