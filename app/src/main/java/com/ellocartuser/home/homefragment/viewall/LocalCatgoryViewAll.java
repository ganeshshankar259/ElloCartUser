package com.ellocartuser.home.homefragment.viewall;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CategoriesResponce;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.home.adapterandmodel.AllIndiaAdapter;
import com.ellocartuser.home.adapterandmodel.GridAdapterService;
import com.ellocartuser.home.homefragment.NearbystoreFragment;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocalCatgoryViewAll#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocalCatgoryViewAll extends Fragment implements GridAdapterService.OnItemClickeGrid{
    RecyclerView dataList;
    ImageView imageback;
    TextView current;
    GridAdapterService.OnItemClickeGrid onItemClickeGrid;
    GridAdapterService gridadapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LocalCatgoryViewAll() {
        // Required empty public constructor
    }
    List<Categories> gridedatacat;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocalCatgoryViewAll.
     */
    // TODO: Rename and change types and number of parameters
    public static LocalCatgoryViewAll newInstance(String param1, String param2) {
        LocalCatgoryViewAll fragment = new LocalCatgoryViewAll();
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

        View view =inflater.inflate(R.layout.fragment_local_catgory_view_all, container, false);

        dataList = view.findViewById(R.id.catList);
        imageback = view.findViewById(R.id.imageback);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        current = view.findViewById(R.id.current);
        current.setText("Explore Local Categories");

          GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false);

          dataList.setLayoutManager(gridLayoutManager);
        onItemClickeGrid=this;
        categoryApi();
        return  view;
    }

    public void categoryApi() {
        gridedatacat=new ArrayList<>();
        ProgressDialog  pd1 = new ProgressDialog(getActivity());
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(true);
        pd1.show();
        // pd1.show();
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        String lat = pref.getString("latitude","");
        String longi = pref.getString("longitude","");

        Call<CategoriesResponce> getCate = ApiClient.getApiService().getCategory(lat, longi, "10");
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
                    gridedatacat.clear();


//                    noorder.setVisibility(View.GONE);
//                    datalayout.setVisibility(View.VISIBLE);
                    for (int i = 0; i < resource.getCategories().size(); i++) {
                        gridedatacat.add(resource.getCategories().get(i));
                    }

                    setAdapterGrid();


                } else {

                    if(getContext()!=null) {
                        gridadapter = new GridAdapterService(getContext(), null, onItemClickeGrid,R.layout.servicersingle_home);
                        dataList.setAdapter(gridadapter);
                    }

                    if (resource.getMessage() != "") {
                        //   Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<CategoriesResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //     Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemClick(int position, String carid, String name) {

        SharedPreferences  pref=getActivity()
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("user_id","346");
        editor.putBoolean("loadhome",true);
        editor.commit();

        Util.loadFragment(NearbystoreFragment.newInstance(carid,name,"","",""),getActivity(), LocalCatgoryViewAll.this);

    }


    private void setAdapterGrid() {

        if(getContext()!=null) {

         //   List<Categories> data=new ArrayList<>();

            gridadapter = new GridAdapterService(getContext(), gridedatacat, onItemClickeGrid,R.layout.homecatsingle);
            dataList.setAdapter(gridadapter);

        }

    }

}