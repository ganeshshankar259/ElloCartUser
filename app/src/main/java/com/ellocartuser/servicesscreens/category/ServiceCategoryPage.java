package com.ellocartuser.servicesscreens.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.CategoriesResponce;
import com.ellocartuser.apiservices.Responce.SubCatResponce;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.home.adapterandmodel.CarStoreAdapter;
import com.ellocartuser.home.adapterandmodel.CategoryStoreAll;
import com.ellocartuser.home.adapterandmodel.SubCatStoreAdapter;
import com.ellocartuser.home.adapterandmodel.SubcategoryStoreAll;
import com.ellocartuser.servicesscreens.AddService;
import com.ellocartuser.servicesscreens.Servicesdisplaylist;
import com.ellocartuser.servicesscreens.SubCategoryItems;
import com.ellocartuser.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceCategoryPage extends AppCompatActivity implements CatServiceAdapter.OnItemClickedcat,SubCatServiceAdapter.OnItemClickedSubcat {

    private String catid,subcatid="0";
    private String sellerid,storename;
    RecyclerView recyclerView,subcatlist;
    SubCatServiceAdapter subcatadapter;
    ProgressDialog pd1;
    CatServiceAdapter adapter;
    String categoryid="";
    TextView current;
    ImageView imageback;
    List<Categories> catfilterdata;
    CatServiceAdapter.OnItemClickedcat OnItemClickedcat;
    SubCatServiceAdapter.OnItemClickedSubcat onItemClickedSubcat;
    List<SubcategoryStoreAll> subcatdatatemp;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_category_page);

        next = findViewById(R.id.next);
        subcatlist = findViewById(R.id.subcatlist);
        recyclerView = findViewById(R.id.catList);
        current = findViewById(R.id.current);
        current.setText(storename);
        //    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        //   onItemClicked=this;
        OnItemClickedcat=this;
        onItemClickedSubcat=this;
        catfilterdata=new ArrayList<>();
        imageback = findViewById(R.id.imageback);
        subcatdatatemp=new ArrayList<>();
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           onBackPressed();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apicallRoCheckIsCategoryExitAlready();

            }
        });

        categoryApi();

    }

    private void apicallRoCheckIsCategoryExitAlready() {

        pd1 = new ProgressDialog(ServiceCategoryPage.this);
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();
//        pd1.show();
//        @Field("type") RequestBody type,

//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        Call<SubCatResponce> getCate = ApiClient.getApiServiceforservice().getServiceSubCat("add_post_check",categoryid,id);
        getCate.enqueue(new Callback<SubCatResponce>() {
            @Override
            public void onResponse(Call<SubCatResponce> call, Response<SubCatResponce> response) {
                final SubCatResponce resource = response.body();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd1.dismiss();
                    }
                });
                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {

                    String subcat = "";
                    for (int i=0;i<subcatdatatemp.size();i++){
                        if(subcatdatatemp.get(i).getSelected().equals("1")){
                            if(subcat.equals("")){
                                subcat = subcat.concat(subcatdatatemp.get(i).getScat_id());

                            }else {
                                subcat = subcat.concat(",");
                                subcat = subcat.concat(subcatdatatemp.get(i).getScat_id());
                            }
                        }
                    }

                    if(!subcat.equals("")){
                        Intent ii= new Intent(ServiceCategoryPage.this, AddService.class);
                        ii.putExtra("catid",categoryid);
                        ii.putExtra("subcatids",subcat);
                        startActivity(ii);
                    }else{
                        Toast.makeText(ServiceCategoryPage.this, "Please select atleast one", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Util.AlertWithOK(ServiceCategoryPage.this,"Category Already Added To your service");
//                    if (resource.getMessage() != "") {
//                       Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<SubCatResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(ServiceCategoryPage.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });

    }



    public void categoryApi() {
        //gridedata=new ArrayList<>();
        pd1 = new ProgressDialog(ServiceCategoryPage.this);
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

//        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String id = pref.getString("user_id","");
//        String lat = pref.getString("latitude","");
//        String longi = pref.getString("longitude","");

        Call<CategoriesResponce> getCate = ApiClient.getApiServiceforservice().getHomeService("get_all_cat","","");
        getCate.enqueue(new Callback<CategoriesResponce>() {
            @Override
            public void onResponse(Call<CategoriesResponce> call, Response<CategoriesResponce> response) {
                final CategoriesResponce resource = response.body();

                pd1.dismiss();

                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }

                String  subcatid="0";
                if (resource.getStatus().equals("ok")) {
                    //catstore
                    catfilterdata=new ArrayList<>();

                    if (resource.getCategories().size() != 0) {

                        for(int i=0;i<resource.getCategories().size();i++){
                            resource.getCategories().get(i).setSelected("0");
                        }

                        resource.getCategories().get(0).setSelected("1");
                        subcatid=   resource.getCategories().get(0).getCat_id();
                        categoryid=resource.getCategories().get(0).getCat_id();
                        catfilterdata=resource.getCategories();

                        adapter = new CatServiceAdapter(ServiceCategoryPage.this, catfilterdata, OnItemClickedcat);
                        recyclerView.setAdapter(adapter);

                    }

                    subcatApi(subcatid);

                } else {
//                    noorder.setVisibility(View.VISIBLE);
//                    datalayout.setVisibility(View.GONE);
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
                Toast.makeText(ServiceCategoryPage.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void subcatApi(String subcatid) {
        pd1 = new ProgressDialog(ServiceCategoryPage.this);
        pd1.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd1.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd1.setCancelable(false);
        pd1.show();
//        pd1.show();
//        @Field("type") RequestBody type,

//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<SubCatResponce> getCate = ApiClient.getApiServiceforservice().getServiceSubCat("get_all_subcat",subcatid,"");
        getCate.enqueue(new Callback<SubCatResponce>() {
            @Override
            public void onResponse(Call<SubCatResponce> call, Response<SubCatResponce> response) {
                final SubCatResponce resource = response.body();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd1.dismiss();
                    }
                });
                // Log.d("resss", resource.toString());

                if (resource == null) {
                    return;
                }
                if (resource.getStatus().equals("ok")) {
                    //catstore
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(ServiceCategoryPage.this,3,GridLayoutManager.VERTICAL,false);
                    subcatlist.setLayoutManager(gridLayoutManager);
                    //  dataList.setNestedScrollingEnabled(false);
                    //  on=this;
                    for(int i=0;i<resource.getSubcategories().size();i++){
                        resource.getSubcategories().get(i).setSelected("0");
                    }
                    subcatdatatemp=new ArrayList<>();
                    subcatdatatemp=resource.getSubcategories();
                    subcatadapter =new SubCatServiceAdapter(ServiceCategoryPage.this,subcatdatatemp,onItemClickedSubcat);
                    subcatlist.setAdapter(subcatadapter);

                    //   subcatApi();
                } else {
//                    if (resource.getMessage() != "") {
//                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<SubCatResponce> call, Throwable t) {
                //   pd.dismiss();
                pd1.dismiss();
                t.printStackTrace();
                Toast.makeText(ServiceCategoryPage.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();

            }
        });
    }



    @Override
    public void onItemClickCat(int position, String catid) {

        categoryid=catid;
        for(int i=0;i<catfilterdata.size();i++){
            catfilterdata.get(i).setSelected("0");
        }
        catfilterdata.get(position).setSelected("1");
        adapter.setDataList(catfilterdata);
        adapter.notifyDataSetChanged();

        SubCatServiceAdapter subcatadapter =new SubCatServiceAdapter(ServiceCategoryPage.this,null,onItemClickedSubcat);
        subcatlist.setAdapter(subcatadapter);

        subcatApi(catid);

    }

    @Override
    public void onItemClickSubCat(int position, String subcatid) {
        if(subcatdatatemp.get(position).getSelected().equals("1")){
            subcatdatatemp.get(position).setSelected("0");
        }else {
            subcatdatatemp.get(position).setSelected("1");
        }

        subcatadapter.setDataList(subcatdatatemp);
        subcatadapter.notifyDataSetChanged();


    }
}