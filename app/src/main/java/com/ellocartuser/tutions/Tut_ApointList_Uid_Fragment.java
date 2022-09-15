package com.ellocartuser.tutions;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.tutions.tutmodelsandresponces.TAppointListBUid_Adapter;
import com.ellocartuser.tutions.tutmodelsandresponces.TAppointListBUid_M;
import com.ellocartuser.tutions.tutmodelsandresponces.TAppointListBUid_Responce;
import com.ellocartuser.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tut_ApointList_Uid_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tut_ApointList_Uid_Fragment extends Fragment implements TAppointListBUid_Adapter.onItemClickeRoom{

    RecyclerView recyclerView;
    LinearLayout noorder;
    ProgressDialog pd, pd1;
    TAppointListBUid_Adapter adapter;
    TAppointListBUid_Adapter.onItemClickeRoom onClick;
    List<TAppointListBUid_M> datalist=new ArrayList<>();
    //String cartsellerid=null,carttotalcount="",store_o="0";
    ImageView backbutton;
    SharedPreferences pref;
    String r_cattitle,r_catid;
    Dialog dialoglocation,dialog;
    TextView recentsearch,myaddress,current;//
    AutoCompleteTextView autoCompleteTextView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tut_ApointList_Uid_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tut_ApointList_Uid_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tut_ApointList_Uid_Fragment newInstance(String param1, String param2) {
        Tut_ApointList_Uid_Fragment fragment = new Tut_ApointList_Uid_Fragment();
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
        //return inflater.inflate(R.layout.fragment_tut_apoint_list_uid, container, false);
        View view=inflater.inflate(R.layout.fragment_tut_apoint_list_uid, container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("ROOM_CATID", Context.MODE_PRIVATE);
        r_catid = pref.getString("r_catid", "");
        r_cattitle = pref.getString("r_cattitle", "");

        //auto = view.findViewById(R.id.auto);

//        auto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDialogSearch(getActivity());
//            }
//        });

//        current = view.findViewById(R.id.current);



        noorder = view.findViewById(R.id.noorder);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        backbutton = view.findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Util.loadFragment(new homefragment(),getActivity(), Tut_ApointList_Uid_Fragment.this);
            }
        });
        onClick=this;
        adapter = new TAppointListBUid_Adapter(getActivity(), null, onClick);
        recyclerView.setAdapter(adapter);

//        apiCall();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        apiCall();
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

        //user_id
        SharedPreferences prefs = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String user_id = prefs.getString("user_id","");



        Call<TAppointListBUid_Responce> getCate = ApiClient.getApiServiceforservice().getappointlist_byuid(user_id);
        getCate.enqueue(new Callback<TAppointListBUid_Responce>() {
            @Override
            public void onResponse(Call<TAppointListBUid_Responce> call, Response<TAppointListBUid_Responce> response) {
                final TAppointListBUid_Responce resource = response.body();
                pd1.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    noorder.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    //catstore
                    //store_o=resource.getdSpecialid();
                    datalist=resource.getAppointmentDetails();

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

                                adapter.updateList(resource.getAppointmentDetails());
                                //adapter.updateList(resource.getProduct());
                                //  recyclerView.setAdapter(adapter);
                            }
                        });

                    }

                } else {
                    noorder.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), resource.getStatus()+" No Hotels for this location", Toast.LENGTH_LONG).show();
                   // adapter.updateList(resource.getAppointmentDetails());
//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<TAppointListBUid_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public void onItemClick_Room(int position, String catid,String name) {
       // Util.loadFragment(Rooms_List_Fragment.newInstance(catid, name, "", "", ""), getActivity(), Room_Subc_Fragment.this);
    }

}