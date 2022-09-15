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

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.rooms_old.ModelsandResponces.BookedHotelsList_Adapter;
import com.ellocartuser.rooms_old.ModelsandResponces.BookedHotels_M;
import com.ellocartuser.rooms_old.ModelsandResponces.BookedHotels_Responce;
import com.ellocartuser.setting.Account;
import com.ellocartuser.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookedRoomsList_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookedRoomsList_Fragment extends Fragment implements BookedHotelsList_Adapter.OnItemClickedproduct{

    RecyclerView recyclerView;
    ProgressDialog pd1;
    BookedHotelsList_Adapter adapter;
    BookedHotelsList_Adapter.OnItemClickedproduct onClick;
    List<BookedHotels_M> datalist=new ArrayList<>();
    //String cartsellerid=null,carttotalcount="",store_o="0";
    ImageView backbutton;
    SharedPreferences pref;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookedRoomsList_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookedRoomsList_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookedRoomsList_Fragment newInstance(String param1, String param2) {
        BookedRoomsList_Fragment fragment = new BookedRoomsList_Fragment();
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
        //return inflater.inflate(R.layout.fragment_booked_rooms_list, container, false);

        View view=inflater.inflate(R.layout.fragment_booked_rooms_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        backbutton = view.findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Util.loadFragment(new Account(),getActivity(), BookedRoomsList_Fragment.this);
//                Intent ii = new Intent(getContext(), Account.class);
//                ii.putExtra("from","");
//                startActivity(ii);
            }
        });
        onClick=this;
        adapter = new BookedHotelsList_Adapter(getActivity(), null, onClick);
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

//        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String d_id = pref.getString("u_id", "");
        // String d_id = "1";
        String u_id = "66";
        String type = "get";
        Call<BookedHotels_Responce> getCate = ApiClient.getApiServiceforservice().get_rooms_booked(u_id,type);
        getCate.enqueue(new Callback<BookedHotels_Responce>() {
            @Override
            public void onResponse(Call<BookedHotels_Responce> call, Response<BookedHotels_Responce> response) {
                final BookedHotels_Responce resource = response.body();
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
            public void onFailure(Call<BookedHotels_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public void onItemClickProduct(int position, String catid,String name) {
        Util.loadFragment(Bookings_History_Fragment.newInstance(catid,name), getActivity(), BookedRoomsList_Fragment.this);
    }

    @Override
    public void onItemClickaddtocart(int position, String catid) {

    }

    @Override
    public void onItemClickaddtocartupdatecount(int position, String proid, String qty) {

    }
}