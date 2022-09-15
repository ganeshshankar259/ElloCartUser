package com.ellocartuser.home.homefragment.viewall;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CategoriesResponce;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.home.adapterandmodel.GridAdapterService;
import com.ellocartuser.home.adapterandmodel.ServicesAdapter;
import com.ellocartuser.home.homefragment.NearbystoreFragment;
import com.ellocartuser.servicesscreens.SubCategoryItems;
import com.ellocartuser.utils.Util;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServiceBottomSheet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceBottomSheet extends Fragment implements  ServicesAdapter.OnItemClickeGrid{
    ProgressDialog pd1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ServicesAdapter.OnItemClickeGrid onItemClickeService;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView imageback;

    List<Categories> gridedataserv;
TextView current;
    public ServiceBottomSheet() {
        // Required empty public constructor
    }
    RecyclerView dataList;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoresBottomSheet.
     */
    // TODO: Rename and change types and number of parameters
    public static ServiceBottomSheet newInstance(String param1, String param2) {
        ServiceBottomSheet fragment = new ServiceBottomSheet();
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
        View view=inflater.inflate(R.layout.fragment_stores_bottom_sheet, container, false);
        dataList = view.findViewById(R.id.catList);
        current = view.findViewById(R.id.current);
        current.setText("Services");
        onItemClickeService=this;
        servicecategoryApi();

        imageback = view.findViewById(R.id.imageback);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }



    public void servicecategoryApi() {
        gridedataserv=new ArrayList<>();
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
//
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        String lat = pref.getString("latitude","");
        String longi = pref.getString("longitude","");

        Call<CategoriesResponce> getCate = ApiClient.getApiServiceforservice().getHomeService("home",lat,longi);
        getCate.enqueue(new Callback<CategoriesResponce>() {
            @Override
            public void onResponse(Call<CategoriesResponce> call, Response<CategoriesResponce> response) {
                final CategoriesResponce resource = response.body();

                pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                if (resource.getStatus().equals("ok")) {

                    for (int i = 0; i < resource.getCategories().size(); i++) {
                        gridedataserv.add(resource.getCategories().get(i));
                    }
                    if(getContext()!=null) {
                        dataList.setLayoutManager(new LinearLayoutManager(getContext()));


                        ServicesAdapter apter = new ServicesAdapter(getContext(), gridedataserv, onItemClickeService,R.layout.servicersinglevertical,"detail");
                        dataList.setAdapter(apter);
                    }

                } else {

//                    if (resource.getMessage() != "") {
//                        //   Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<CategoriesResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(getContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }


    private void setAdapterGrid() {
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
//        dataList.setLayoutManager(gridLayoutManager);
        //  dataList.setNestedScrollingEnabled(false);


        //   gridadapter = new GridAdapter(getContext(),gridedata);

    }

    @Override
    public void onItemClickservice(int position, String carid, String name) {

        SharedPreferences  pref=getActivity()
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("user_id","346");
        editor.putBoolean("loadhome",true);
        editor.commit();

        Intent ii= new Intent(getActivity(), SubCategoryItems.class);
        ii.putExtra("catid",carid);
        ii.putExtra("name",name);
        startActivity(ii);

    }
}