package com.ellocartuser.setting;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ellocartuser.Login;
import com.ellocartuser.R;
import com.ellocartuser.RoomsNew.MyRoomsList;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.home.MyWallet;
import com.ellocartuser.home.fragments.ElloAllindiaEmpty;
import com.ellocartuser.home.fragments.ElloRoomEmpty;
import com.ellocartuser.home.fragments.ServicesFragment;
import com.ellocartuser.home.homefragment.homefragment;
import com.ellocartuser.home.homefragment.viewall.AllIndiaCatgoryViewAll;
import com.ellocartuser.orders.OrdersMainClass;
import com.ellocartuser.rooms_old.BookedRoomsList_Fragment;
import com.ellocartuser.setting.webfragment.WebLinkFragment;
import com.ellocartuser.tutions.Tut_ApointList_Uid_Fragment;
import com.ellocartuser.utils.Util;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Account#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Account extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RelativeLayout rlogin,myprofile,myorder,allindia,elloservices,ellorooms;
    RelativeLayout rlogout;
    ImageView imageback,cam;
    LinearLayout about,wallet,support,ellosales,joinseller,deliveryjoin,faqs,terms,privacy,logout,login,shareapp;
    CircleImageView userimg;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
   // LinearLayout logout,myprofile,mywallet,b2b,aboutus,contactus,term,privacy,shareapp,faq,login;
    SharedPreferences pref;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Account.
     */
    // TODO: Rename and change types and number of parameters
    public static Account newInstance(String param1, String param2) {
        Account fragment = new Account();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Account() {
        // Required empty public constructor
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

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        rlogout=view.findViewById(R.id.rlogout);
        rlogin=view.findViewById(R.id.rlogin);
        cam=view.findViewById(R.id.cam);
        userimg=view.findViewById(R.id.userimg);
        imageback=view.findViewById(R.id.imageback);
        myprofile=view.findViewById(R.id.myprofile);
        myorder=view.findViewById(R.id.myorder);
        wallet=view.findViewById(R.id.wallet);
        allindia=view.findViewById(R.id.allindia);
        about=view.findViewById(R.id.about);
        support=view.findViewById(R.id.support);
        joinseller=view.findViewById(R.id.joinseller);
        deliveryjoin=view.findViewById(R.id.deliveryjoin);
        ellosales=view.findViewById(R.id.ellosales);
        faqs=view.findViewById(R.id.faqs);
        terms=view.findViewById(R.id.terms);
        privacy=view.findViewById(R.id.privacy);
        shareapp=view.findViewById(R.id.shareapp);
        logout=view.findViewById(R.id.logout);
        login=view.findViewById(R.id.login);
        ellorooms=view.findViewById(R.id.ellorooms);
        elloservices=view.findViewById(R.id.elloservices);


//        rllogin=view.findViewById(R.id.rllogin);
//        login=view.findViewById(R.id.login);
//        logout=view.findViewById(R.id.logout);
//        myprofile=view.findViewById(R.id.myprofile);
//        mywallet=view.findViewById(R.id.mywallet);
//        b2b=view.findViewById(R.id.b2b);
//        aboutus=view.findViewById(R.id.aboutus);
//        contactus=view.findViewById(R.id.contactus);
//        term=view.findViewById(R.id.term);
//        privacy=view.findViewById(R.id.privacy);
//        shareapp=view.findViewById(R.id.shareapp);
//        faq=view.findViewById(R.id.faq);
//
//        login.setOnClickListener(this::onClick);
//        logout.setOnClickListener(this::onClick);
//        myprofile.setOnClickListener(this::onClick);
//        mywallet.setOnClickListener(this::onClick);
//        b2b.setOnClickListener(this::onClick);
//        aboutus.setOnClickListener(this::onClick);
//        contactus.setOnClickListener(this::onClick);
//        term.setOnClickListener(this::onClick);
//        privacy.setOnClickListener(this::onClick);
//        shareapp.setOnClickListener(this::onClick);
//        faq.setOnClickListener(this::onClick);
//
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        if(id.equals("") || id==null) {
            rlogin.setVisibility(View.VISIBLE);
            rlogout.setVisibility(View.GONE);
        }else{
            rlogin.setVisibility(View.GONE);
            rlogout.setVisibility(View.VISIBLE);
        }

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        ellorooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushFragment(ElloRoomEmpty.newInstance("",""));
//                Intent intent = new Intent(getActivity(), MyRoomsList.class);
//                startActivity(intent);
            }
        });

        elloservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(id.equals("") || id==null) {
//                    Util.PleaseLogin(getActivity());
//                    return;
//                }
              //  pushFragment(Tut_ApointList_Uid_Fragment.newInstance("",""));
                pushFragment(ServicesFragment.newInstance("",""));
            }
        });


        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.equals("") || id==null) {
                    Util.PleaseLogin(getActivity());
                    return;
                }
                //  pushFragment(new MyProfile());
                Intent ii = new Intent(getActivity(), MyProfileActivity.class);
                ii.putExtra("from","");
                startActivity(ii);

            }
        });

        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.equals("") || id==null) {
                    Util.PleaseLogin(getActivity());
                    return;
                }
                pushFragment(OrdersMainClass.newInstance("",""));
            }
        });

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.equals("") || id==null) {
                    Util.PleaseLogin(getActivity());
                    return;
                }
                pushFragment(MyWallet.newInstance("",""));
            }
        });

        allindia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushFragment(new ElloAllindiaEmpty());
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebLinkFragment webLinkFragment = WebLinkFragment.newInstance("https://www.ellocart.com/about","About us");
                pushFragment(webLinkFragment);
            }
        });

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebLinkFragment webLinkFragment1 = WebLinkFragment.newInstance("https://www.ellocart.com/contact","Contact us");
                pushFragment(webLinkFragment1);
            }
        });

        joinseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse("https://play.google.com/store/apps/details?id=com.sunraise.ellocartseller"));
                    startActivity(viewIntent);
                }catch(Exception e) {
                    Toast.makeText(getActivity(),"Unable to Connect Try Again...",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }



            }
        });

        deliveryjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse("https://play.google.com/store/apps/details?id=com.ellocartdelivery.sunraise"));
                    startActivity(viewIntent);
                }catch(Exception e) {
                    Toast.makeText(getActivity(),"Unable to Connect Try Again...",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });

        ellosales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse("https://play.google.com/store/apps/details?id=com.sunraise.ellosales"));
                    startActivity(viewIntent);
                }catch(Exception e) {
                    Toast.makeText(getActivity(),"Unable to Connect Try Again...",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });

        faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebLinkFragment webLinkFragmentf = WebLinkFragment.newInstance("https://www.ellocart.com/faqs","FAQ");
                pushFragment(webLinkFragmentf);
            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebLinkFragment webLinkFragment2 = WebLinkFragment.newInstance("https://www.ellocart.com/terms","Terms and Conditions");
                pushFragment(webLinkFragment2);

            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebLinkFragment webLinkFragment3 = WebLinkFragment.newInstance("https://www.ellocart.com/privacy","PRIVACY POLICY");
                pushFragment(webLinkFragment3);
            }
        });

        shareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "ElloCart Driver");
                    String shareMessage= "\nEllocart:Rediscovering Local shopping experience\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.ellocart.sunraise\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(getActivity(), Login.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ii);
            }
        });

            return view;
    }



    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.logout_dialog);

        Button cancle = dialog.findViewById(R.id.cancle);
        Button ok = dialog.findViewById(R.id.ok);


        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                pref=getActivity()
                        .getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear().commit();
//                editor.putString("boy_id","");
//                editor.putString("boy_phone","");
//                editor.putString("boy_name","");
//                editor.commit();

                Intent i=new Intent(getActivity(), Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    public void  pushFragment(Fragment selectedFragment){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                closeDrawer();
//            }
//        });

        final FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                .beginTransaction();

        // put the fragment in place
        transaction.replace(R.id.fragment_container1, selectedFragment);

        // this is the part that will cause a fragment to be added to backstack,
        // this way we can return to it at any time using this tag
        //    transaction.addToBackStack(selectedFragment.getClass().getName());   //for back stack
        transaction.addToBackStack(new homefragment().getClass().getName());
        transaction.commit();

    }

    public void showDialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.logout_dialog);

        Button cancle = dialog.findViewById(R.id.cancle);
        Button ok = dialog.findViewById(R.id.ok);


        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                SharedPreferences   pref=getActivity()
                        .getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear().commit();
//                editor.putString("boy_id","");
//                editor.putString("boy_phone","");
//                editor.putString("boy_name","");
//                editor.commit();

                Intent i=new Intent(getActivity(), Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    @Override
    public void onResume() {
        super.onResume();
        apiCallProfile();
    }

    private void apiCallProfile() {
//        pd = new ProgressDialog(getActivity());
//        pd.setMessage("Loading...");
//        //   pd.setProgressStyle(R.style.ProgressBar);
//        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//         pd.setIndeterminate(true);
//        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
//            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
//        }
//        pd.setCancelable(false);
        // pd.show();
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
        Call<MyProfileResponce> getCate = ApiClient.getApiService().getProgile("user",id);
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                //   pd.dismiss();
                //  Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){

                 //   profilename.setText(resource.getProfile().get(0).getUserName());
                 //   phnnumprofile.setText(resource.getProfile().get(0).getUserPhone());


                    try {
                        if (resource.getProfile().get(0).getUserImage() != null) {

                            Glide.with(getActivity())
                                    .load(resource.getProfile().get(0).getUserImage())
                                    .centerCrop()
                                    .placeholder(R.drawable.placeholderello).into(userimg);

//                            Glide.with(getActivity())
//                                    .load(resource.getProfile().get(0).getUserImage())
//                                    .centerCrop()
//                                    .placeholder(R.drawable.placeholderello).into(cam);

//
//                            mywalletamt.setText("My Wallet (₹"+resource.getProfile().get(0).getUserWallet()+")");
//

                        }
                    }catch (Exception ex){

                    }
                }else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<MyProfileResponce> call, Throwable t) {
                //   pd.dismiss();
                //     pd.dismiss();
                t.printStackTrace();
                //   Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

}