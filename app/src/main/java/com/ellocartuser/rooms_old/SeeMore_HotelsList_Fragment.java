package com.ellocartuser.rooms_old;

import android.app.Dialog;
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
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.rooms_old.ModelsandResponces.Roomcategories_M;
import com.ellocartuser.rooms_old.ModelsandResponces.Roomcategories_Responce;
import com.ellocartuser.rooms_old.adapters.Rcat_seemore_Adapter;
import com.ellocartuser.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeeMore_HotelsList_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeeMore_HotelsList_Fragment extends Fragment implements Rcat_seemore_Adapter.onItemClickeRoom{

    RecyclerView recyclerView;

    ProgressDialog pd, pd1;
    Rcat_seemore_Adapter adapter;
    Rcat_seemore_Adapter.onItemClickeRoom onClick;
    List<Roomcategories_M> datalist=new ArrayList<>();
    //String cartsellerid=null,carttotalcount="",store_o="0";
    ImageView backbutton;
    SharedPreferences pref;
    String r_cattitle,r_catid;
    Dialog dialoglocation,dialog;
    TextView recentsearch,myaddress,current;//
    AutoCompleteTextView autoCompleteTextView;
    EditText auto;

    String locality,fullloca;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SeeMore_HotelsList_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SeeMore_HotelsList_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SeeMore_HotelsList_Fragment newInstance(String param1, String param2) {
        SeeMore_HotelsList_Fragment fragment = new SeeMore_HotelsList_Fragment();
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
        //return inflater.inflate(R.layout.fragment_see_more_hotels_list, container, false);
        View view=inflater.inflate(R.layout.fragment_see_more_hotels_list, container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("ROOM_CATID", Context.MODE_PRIVATE);
        r_catid = pref.getString("r_catid", "");
        r_cattitle = pref.getString("r_cattitle", "");

        auto = view.findViewById(R.id.auto);

        SharedPreferences pref1 = getActivity().getSharedPreferences("LOCALITY123", Context.MODE_PRIVATE);
        locality = pref1.getString("locality", "");
        fullloca = pref1.getString("fullloca", "");

//        auto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDialogSearch(getActivity());
//            }
//        });

       // current = view.findViewById(R.id.current);

//        ImageView refresh = view.findViewById(R.id.refresh);
//        refresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                apiCall(r_catid,locality);
//            }
//        });

//        auto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDialog(getActivity());
//            }
//        });

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        backbutton = view.findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().onBackPressed();

                //  Util.loadFragment(new homefragment(),getActivity(), SeeMore_HotelsList_Fragment.this);
            }
        });
        onClick=this;
        adapter = new Rcat_seemore_Adapter(getActivity(), null, onClick);
        recyclerView.setAdapter(adapter);

//        apiCall();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

//        SharedPreferences pref = getActivity().getSharedPreferences("LOCALITY1", Context.MODE_PRIVATE);
//        String locality = pref.getString("locality", "");

        //String loc= "RAJAHMUNDRY";
        apiCall();
        // Log.d("Ashok : ", "" + locality);
        //  appBarLayout.removeOnOffsetChangedListener(this);
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

//        SharedPreferences pref = getActivity().getSharedPreferences("LOCALITY", Context.MODE_PRIVATE);
//        String locality = pref.getString("locality", "");
        // String d_id = "1";
        String id = "1";
//        String city = "rajahmundry";
        Call<Roomcategories_Responce> getCate = ApiClient.getApiServiceforservice().getHomeSeemore();
        getCate.enqueue(new Callback<Roomcategories_Responce>() {
            @Override
            public void onResponse(Call<Roomcategories_Responce> call, Response<Roomcategories_Responce> response) {
                final Roomcategories_Responce resource = response.body();
                pd1.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    //catstore
                    //store_o=resource.getdSpecialid();
                    datalist=resource.getRoomcategories();

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

                                adapter.updateList(resource.getRoomcategories());
                                //adapter.updateList(resource.getProduct());
                                //  recyclerView.setAdapter(adapter);
                            }
                        });

                    }

                } else {
                    // Toast.makeText(getActivity(), resource.getStatus(), Toast.LENGTH_LONG).show();
                    //adapter.updateList(resource.getRoomsubcategories());
//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<Roomcategories_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                //t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onItemClick_Room(int position, String catid,String name) {
        Util.loadFragment(Room_Subc_Fragment.newInstance(catid, name, "", "", ""), getActivity(), SeeMore_HotelsList_Fragment.this);
    }
}