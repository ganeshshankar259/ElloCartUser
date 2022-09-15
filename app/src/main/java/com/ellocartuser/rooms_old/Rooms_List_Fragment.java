package com.ellocartuser.rooms_old;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.rooms_old.ModelsandResponces.Rooms_List_Adapter;
import com.ellocartuser.rooms_old.ModelsandResponces.Rooms_M;
import com.ellocartuser.rooms_old.ModelsandResponces.Rooms_Responce;
import com.ellocartuser.tutions.Tutions_Base;
import com.ellocartuser.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Rooms_List_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Rooms_List_Fragment extends Fragment implements Rooms_List_Adapter.OnItemClickedproduct{



    RecyclerView recyclerView;
    ProgressDialog pd1;
    Rooms_List_Adapter adapter;
    Rooms_List_Adapter.OnItemClickedproduct onClick;
    List<Rooms_M> datalist=new ArrayList<>();
    //String cartsellerid=null,carttotalcount="",store_o="0";
    ImageView backbutton;
    String r_subid,r_subtitle;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Rooms_List_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Rooms_List_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Rooms_List_Fragment newInstance(String param1, String param2, String param3, String param4, String param5) {
        Rooms_List_Fragment fragment = new Rooms_List_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
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
        //return inflater.inflate(R.layout.fragment_rooms_list, container, false);
        View view=inflater.inflate(R.layout.fragment_rooms_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TextView rooms_title = view.findViewById(R.id.rooms_title);

        SharedPreferences pref = getActivity().getSharedPreferences("ROOM_SUBID", Context.MODE_PRIVATE);
        r_subid = pref.getString("r_subid", "");
        r_subtitle = pref.getString("r_subtitle", "");

        rooms_title.setText(r_subtitle);

        backbutton = view.findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Util.loadFragment(new Room_Subc_Fragment(),getActivity(), Rooms_List_Fragment.this);
            }
        });
        onClick=this;
        adapter = new Rooms_List_Adapter(getActivity(), null, onClick);
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


        // String d_id = "1";
        String id = "1";
        //String city = "Hyderabad";
        Call<Rooms_Responce> getCate = ApiClient.getApiServiceforservice().rooms_list(r_subid);
        getCate.enqueue(new Callback<Rooms_Responce>() {
            @Override
            public void onResponse(Call<Rooms_Responce> call, Response<Rooms_Responce> response) {
                final Rooms_Responce resource = response.body();
                pd1.dismiss();


                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {
                    //catstore
                    //store_o=resource.getdSpecialid();
                    datalist=resource.getRoomchildcategories();

                    if (getActivity()!=null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                adapter.updateList(resource.getRoomchildcategories());
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
            public void onFailure(Call<Rooms_Responce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //  Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public void onItemClickProduct(int position, String catid,String title) {
        //Util.loadFragment(Room_info_Fragment.newInstance(catid,title), getActivity(), Rooms_List_Fragment.this);
//       SharedPreferences pref=(getContext())
//                .getSharedPreferences("ROOM_CATID_FOR_RINFO", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString("r_catidforinfo",catid);
//        editor.commit();

        Intent intent = new Intent(getActivity(), Tutions_Base.class);
        intent.putExtra("type","ROOM_INFO");
        //ii.putExtra("type", "");
        startActivity(intent);
    }

    @Override
    public void onItemClickaddtocart(int position, String catid) {

    }

    @Override
    public void onItemClickaddtocartupdatecount(int position, String proid, String qty) {

    }
}