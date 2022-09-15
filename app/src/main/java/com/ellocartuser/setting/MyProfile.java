package com.ellocartuser.setting;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.utils.RealPathUtil;
import com.ellocartuser.utils.Util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} su
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * bclass.
 * Use the {@link MyProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfile extends Fragment {
        TextView current;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText name,address,phone;
    CircleImageView img,imgselect,cam;
    ProgressDialog pd;
    ImageView imageback;
    TextView edit;
    String[] type=new String[5];
    String realPathuserimg=null;
    String presentselectedimg;
    Button update;

    private static final int TAKE_PICTURE = 2;
    public static final int FILE_PICKER_REQUEST_CODE = 4;
    int ch=0,whichh=0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static MyProfile newInstance(String param1, String param2) {
        MyProfile fragment = new MyProfile();
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
    View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        current=view.findViewById(R.id.current);
        phone=view.findViewById(R.id.phone);
        imageback=view.findViewById(R.id.imageback);
        name=view.findViewById(R.id.storename);
        cam=view.findViewById(R.id.cam);
        img=view.findViewById(R.id.img);
        imgselect=view.findViewById(R.id.imgselect);
        edit=view.findViewById(R.id.edit);
        update=view.findViewById(R.id.update);
        address=view.findViewById(R.id.address);
        apiCall();
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
       // img.setClickable(false);
        Util.PleaseLogin(getContext());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current.setText("Edit Profile");
                img.setVisibility(View.GONE);
                imgselect.setVisibility(View.VISIBLE);
                cam.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                update.setVisibility(View.VISIBLE);
                edit.setVisibility(View.INVISIBLE);
             //   img.setImageDrawable(R.drawable.myprofile_3x);

            }
        });

        imgselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // selectImageForGetPath("userimg");
                selectImageForGetPath();
            }
        });

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageForGetPath();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().length()!=0) {
                    apiCallUpdate();
                }else{
                    if(realPathuserimg==null){
                        Toast.makeText(getActivity(), "please enter name", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return view;
    }


    @AfterPermissionGranted(345)
    private void selectImage() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("image/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(chooseFile, FILE_PICKER_REQUEST_CODE);
    }

    @AfterPermissionGranted(345)
    public void takePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PICTURE);

    }
//    private String getRealPathFromURI(Uri contentURI) {
//        //Log.e("in","conversion"+contentURI.getPath());
//        String path;
//        Cursor cursor = getContentResolver()
//                .query(contentURI, null, null, null, null);
//        if (cursor == null)
//            path=contentURI.getPath();
//
//        else {
//            cursor.moveToFirst();
//            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//            path=cursor.getString(idx);
//
//        }
//        if(cursor!=null)
//            cursor.close();
//        return path;
//    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
// Check for the integer request code originally supplied to startResolutionForResult().

            case FILE_PICKER_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // Get the Image from data
                        //  Uri selectedImage = data.getData();
                        Uri fullPhotoUri = data.getData();

                            if (Build.VERSION.SDK_INT < 11) {
                                realPathuserimg = RealPathUtil.getRealPathFromURI_BelowAPI11(getActivity(), data.getData());
                            } else if (Build.VERSION.SDK_INT < 19) {
                                realPathuserimg = RealPathUtil.getRealPathFromURI_API11to18(getActivity(), data.getData());
                            } else {
                                realPathuserimg = RealPathUtil.getRealPathFromURI_API19(getActivity(), data.getData());
                            }
                        imgselect.setImageURI(fullPhotoUri);

                    }

                }
//                break;
//            case TAKE_PICTURE:
//
//                if (resultCode == RESULT_OK && data != null && resultCode == RESULT_OK) {
////                    bitmap = (Bitmap) data.getExtras().get("data");
////                    profile_image.setImageBitmap(bitmap);
//
//                    Uri fullPhotoUri = data.getData();
////
////                    userimg.setImageURI(fullPhotoUri);
//
//                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//                    thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//                    File destination = new File(Environment.getExternalStorageDirectory(),"temp.jpg");
//                    FileOutputStream fo;
//                    try {
//                        fo = new FileOutputStream(destination);
//                        fo.write(bytes.toByteArray());
//                        fo.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                        Bitmap myBitmap = BitmapFactory.decodeFile(destination.getAbsolutePath());
//                        realPathuserimg =  destination.getAbsolutePath();
//
//                        img.setImageBitmap(myBitmap);
//
//
//
//                }
//                break;

        }


    }

    private void selectImageForGetPath() {
     //   presentselectedimg=imgfor;
//        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Select");
//        builder.setSingleChoiceItems(type, ch, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                whichh=which;
//            }
//        });
//
//// add OK and Cancel buttons
//        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getBaseContext(), paymode.get(ch), Toast.LENGTH_SHORT).show();
                String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if (EasyPermissions.hasPermissions(getActivity(), perms)) {

                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)  != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }

                        selectImage();

                } else {
                    EasyPermissions.requestPermissions(getActivity(), "We need permissions ",345, perms);
                }
//                whichh=0;
//                // user clicked OK
//            }
//        });
//        builder.setNegativeButton("Cancel", null);
//        // create and show the alert dialog
//        AlertDialog dialog = builder.create();
//        dialog.show();
    }


    private void apiCallUpdate() {
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(true);
        pd.show();
        // pd.show();
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        RequestBody rtype = RequestBody.create(MediaType.parse("multipart/form-data"), "update");
        RequestBody rid = RequestBody.create(MediaType.parse("multipart/form-data"), id);
        RequestBody rname = RequestBody.create(MediaType.parse("multipart/form-data"), name.getText().toString());
        MultipartBody.Part rprofileimg = null;

        if (realPathuserimg != null) {
            try {
                File file = new File(realPathuserimg);
                if (file.exists()) {
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    rprofileimg = MultipartBody.Part.createFormData("user_image", file.getName(), requestBody);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }else{
            RequestBody attachmentEmpty = RequestBody.create(MediaType.parse("text/plain"), "");

            rprofileimg = MultipartBody.Part.createFormData("user_image", "", attachmentEmpty);
        }


//        if(realPathuserimg!=null) {
//            File fileprofile = new File(realPathuserimg);
//
//
//            RequestBody reqimgpro = RequestBody.create(MediaType.parse("multipart/form-data"), fileprofile);
//            rprofileimg = MultipartBody.Part.createFormData("user_image", fileprofile.getName(), reqimgpro);
//        }else{
////            RequestBody reqimgpro = RequestBody.create(MediaType.parse("multipart/form-data"), new File("/"));
////            rprofileimg = MultipartBody.Part.createFormData("user_image", "", reqimgpro);
//        }

//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code
//        Call<MyProfileResponce> getCate = ApiClient.getApiService().setProfile(rtype,rid,rname,rname,rprofileimg);
//        getCate.enqueue(new Callback<MyProfileResponce>() {
//            @Override
//            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
//                final MyProfileResponce resource = response.body();
//                pd.dismiss();
//             //   Log.d("resss",resource.toString());
//
//                if(resource==null){
//                    return;
//                }
//
//                if(resource.getStatus().equals("ok")){
//                    Toast.makeText(getActivity(), "updated", Toast.LENGTH_LONG).show();
//                    getActivity().onBackPressed();
////                    name.setText(resource.getProfile().get(0).getUserName());
////                    //  address.setText(resource.getProfile().get(0).get());
////                    phone.setText(resource.getProfile().get(0).getUserPhone());
////
////                    Glide.with(getActivity())
////                            .load(resource.getProfile().get(0).getUserImage())
////                            .fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
////                            .into(img);
//                }else {
////                    if(resource.getMessage()!=""){
////                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
////                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MyProfileResponce> call, Throwable t) {
//                //   pd.dismiss();
//                pd.dismiss();
//                t.printStackTrace();
//               // Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
//            }
//        });

    }

    private void apiCall() {
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
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
                pd.dismiss();
              //  Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }
                if(resource.getStatus().equals("ok")){

                    name.setText(resource.getProfile().get(0).getUserName());
                //  address.setText(resource.getProfile().get(0).get());
                    phone.setText(resource.getProfile().get(0).getUserPhone());

                    Glide.with(getActivity())
                            .load(resource.getProfile().get(0).getUserImage())
                            .fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                            .placeholder(R.drawable.placeholderello) .into(img);

                    Glide.with(getActivity())
                            .load(resource.getProfile().get(0).getUserImage())
                            .fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                            .placeholder(R.drawable.placeholderello) .into(imgselect);

                }else {
//                    if(resource.getMessage()!=""){
//                        Toast.makeText(MyProfile.this, resource.getMessage(), Toast.LENGTH_LONG).show();
//                    }
                }

            }

            @Override
            public void onFailure(Call<MyProfileResponce> call, Throwable t) {
                //   pd.dismiss();
                pd.dismiss();
                t.printStackTrace();
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }
}