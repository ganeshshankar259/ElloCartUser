package com.ellocartuser.setting;

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
import android.widget.LinearLayout;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.B2BResponce;
import com.ellocartuser.apiservices.Responce.B2categoryResponce;
import com.ellocartuser.home.adapterandmodel.B2BAdapterSettingpage;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link B2b#newInstance} factory method to
 * create an instance of this fragment.
 */
public class B2b extends Fragment {
    ProgressDialog pd1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView dataList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView imageback;
    LinearLayout noorder;
    public B2b() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment B2b.
     */
    // TODO: Rename and change types and number of parameters
    public static B2b newInstance(String param1, String param2) {
        B2b fragment = new B2b();
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
        View view = inflater.inflate(R.layout.fragment_b2b, container, false);

        noorder = view.findViewById(R.id.noorder);
        imageback = view.findViewById(R.id.imageback);
        dataList = view.findViewById(R.id.catList);
        dataList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        apicall();

        return view;
    }

    private void apicall() {

        pd1 = new ProgressDialog(getContext());
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        Call<B2BResponce> getCate = ApiClient.getApiService().getB2Borders(id);
        getCate.enqueue(new Callback<B2BResponce>() {
            @Override
            public void onResponse(Call<B2BResponce> call, Response<B2BResponce> response) {
                final B2BResponce resource = response.body();

                pd1.dismiss();
                // Log.d("resss", resource.toString());
                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {

                    noorder.setVisibility(View.GONE);
                    dataList.setVisibility(View.VISIBLE);

                    B2BAdapterSettingpage adapter = new B2BAdapterSettingpage(getActivity(),resource.getB2orders());
                    dataList.setAdapter(adapter);

//                    productAdapter=new B2BSubCatAdapter(getActivity(),resource.getProduct(),onclicksubcat);
//                    subcatlist.setAdapter(productAdapter);

//                    if(resource.getB2subcategories().size()!=0) {
//                        subDisplayProduct(resource.getB2subcategories().get(0).getB2subcategory_id());
//                    }
//                    for (int i = 0; i < resource.getB2categories().size(); i++) {
//                        gridedata.add(resource.getB2categories().get(i));
//                    }
//                    setAdapterGrid();

                } else {

                    noorder.setVisibility(View.VISIBLE);
                    dataList.setVisibility(View.GONE);

//                    if (resource.get() != "") {
//                        Toast.makeText(getContext(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }
            @Override
            public void onFailure(Call<B2BResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                //     Toast.makeText(getContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });


    }
}