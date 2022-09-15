package com.ellocartuser.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ellocartuser.EmptyOfferPage;
import com.ellocartuser.MainActivity;
import com.ellocartuser.OtpScreen;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.ApiClient;
import com.ellocartuser.apiservices.Responce.MyProfileResponce;
import com.ellocartuser.home.mainscreen.HomeScreen;
import com.ellocartuser.utils.RealPathUtil;
import com.ellocartuser.utils.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ellocartuser.utils.Util.saveBitmapToFile;

public class MyProfileActivity extends AppCompatActivity {

    EditText name,address,phone,email;
    CircleImageView img,imgselect,cam;
    ProgressDialog pd;
    ImageView imageback;
    TextView edit,current;
    String[] type=new String[5];
    String realPathuserimg=null;
    String presentselectedimg;
    Button update;
    private static final int TAKE_PICTURE = 2;
    public static final int FILE_PICKER_REQUEST_CODE = 4;
    int ch=0,whichh=0;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_profile);

        email=findViewById(R.id.email);
        current=findViewById(R.id.current);
        phone=findViewById(R.id.phone);
        imageback=findViewById(R.id.imageback);
        name=findViewById(R.id.storename);
        cam=findViewById(R.id.cam);
        img=findViewById(R.id.img);
        imgselect=findViewById(R.id.imgselect);
        edit=findViewById(R.id.edit);
        update=findViewById(R.id.update);
        address=findViewById(R.id.address);
        apiCall();
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        // img.setClickable(false);
        Util.PleaseLogin(getApplicationContext());

        if(getIntent().getStringExtra("from").equals("edit")){
            img.setVisibility(View.GONE);
            imgselect.setVisibility(View.VISIBLE);
            cam.setVisibility(View.VISIBLE);
            name.setEnabled(true);
            email.setEnabled(true);
            update.setVisibility(View.VISIBLE);
            edit.setVisibility(View.INVISIBLE);
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current.setText("Edit Profile");

                img.setVisibility(View.GONE);
                imgselect.setVisibility(View.VISIBLE);
                cam.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                email.setEnabled(true);
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
                    if(checkemail()) {
                        apiCallUpdate();
                    }
                }else{
                    if(realPathuserimg==null){
                        Toast.makeText(MyProfileActivity.this, "please enter name", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean checkemail() {

        if (email.getText().toString().matches(emailPattern) && email.getText().toString().length() > 0){
            return true;
        } else{
            Toast.makeText(MyProfileActivity.this, "please enter valid Email", Toast.LENGTH_LONG).show();

        }
            return false;
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
                            realPathuserimg = RealPathUtil.getRealPathFromURI_BelowAPI11(MyProfileActivity.this, data.getData());
                        } else if (Build.VERSION.SDK_INT < 19) {
                            realPathuserimg = RealPathUtil.getRealPathFromURI_API11to18(MyProfileActivity.this, data.getData());
                        } else {
                            realPathuserimg = RealPathUtil.getRealPathFromURI_API19(MyProfileActivity.this, data.getData());
                        }
                        imgselect.setImageURI(fullPhotoUri);
                        img.setImageURI(fullPhotoUri);

//                        try {
//                         Bitmap bitmap =   rotateImage(realPathuserimg);
//                            imgselect.setImageBitmap(bitmap);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }

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
        if (EasyPermissions.hasPermissions(MyProfileActivity.this, perms)) {

            if (ActivityCompat.checkSelfPermission(MyProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MyProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)  != PackageManager.PERMISSION_GRANTED) {
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
            EasyPermissions.requestPermissions(MyProfileActivity.this, "We need permissions ",345, perms);
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
        pd = new ProgressDialog(MyProfileActivity.this);
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

        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");

        Map<String, String> params = new HashMap<>();

        params.put("empty", "");

        RequestBody rtype = RequestBody.create(MediaType.parse("multipart/form-data"), "update");
        RequestBody rid = RequestBody.create(MediaType.parse("multipart/form-data"), id);
        RequestBody rname = RequestBody.create(MediaType.parse("multipart/form-data"), name.getText().toString());
        RequestBody remail = RequestBody.create(MediaType.parse("multipart/form-data"), email.getText().toString());
        MultipartBody.Part rprofileimg = null;

        if (realPathuserimg != null) {
            try {
                File filebefore = new File(realPathuserimg);

                File file = Util.getCompressedImageFile(filebefore,MyProfileActivity.this);

                if(file==null){
                    file=filebefore;
                }

                if (file.exists()) {
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    rprofileimg = MultipartBody.Part.createFormData("user_image", file.getName(), requestBody);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                Toast.makeText(MyProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }else{
            RequestBody attachmentEmpty = RequestBody.create(MediaType.parse("text/plain"), "");

            rprofileimg = MultipartBody.Part.createFormData("user_imagee", "", attachmentEmpty); // for dummy send

            params.put("user_image", "");
        }

//        if(realPathuserimg!=null) {
//            File fileprofile = new File(realPathuserimg);
//            RequestBody reqimgpro = RequestBody.create(MediaType.parse("multipart/form-data"), fileprofile);
//            rprofileimg = MultipartBody.Part.createFormData("user_image", fileprofile.getName(), reqimgpro);
//        }else{
//            RequestBody reqimgpro = RequestBody.create(MediaType.parse("multipart/form-data"), new File("/"));
//           rprofileimg = MultipartBody.Part.createFormData("user_image", "", reqimgpro);
//        }
//        @Field("type") RequestBody type,
//        @Field("boy_phone") RequestBody boy_phone,
//        @Field("boy_phone_code") RequestBody boy_phone_code

        Call<MyProfileResponce> getCate = ApiClient.getApiService().setProfile(rtype,rid,rname,remail,params,rprofileimg);
        getCate.enqueue(new Callback<MyProfileResponce>() {
            @Override
            public void onResponse(Call<MyProfileResponce> call, Response<MyProfileResponce> response) {
                final MyProfileResponce resource = response.body();
                pd.dismiss();
                //   Log.d("resss",resource.toString());

                if(resource==null){
                    return;
                }

                if(resource.getStatus().equals("ok")){

                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("user_profile","1");
                    editor.commit();

                    Toast.makeText(MyProfileActivity.this, "updated", Toast.LENGTH_LONG).show();
                    if(getIntent().getStringExtra("from").equals("edit")) {
                    if(resource.getUser_pwallet().equals("500")) {
                        Intent ii = new Intent(MyProfileActivity.this, EmptyOfferPage.class);
                        ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        ii.putExtra("img_url", resource.getUser_pwallet_image());
                        startActivity(ii);
                    }else{
                        // to offer page then go to home screen page
                        Intent dash = new Intent(MyProfileActivity.this, HomeScreen.class);
                        dash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(dash);
                    }


                    }else {
                        onBackPressed();
                    }
//                    name.setText(resource.getProfile().get(0).getUserName());
//                    //  address.setText(resource.getProfile().get(0).get());
//                    phone.setText(resource.getProfile().get(0).getUserPhone());
//
//                    Glide.with(getActivity())
//                            .load(resource.getProfile().get(0).getUserImage())
//                            .fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
//                            .into(img);
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
                // Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void apiCall() {
        pd = new ProgressDialog(MyProfileActivity.this);
        pd.setMessage("Loading...");
        //   pd.setProgressStyle(R.style.ProgressBar);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pd.setIndeterminate(true);
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1rotate, null));
        }
        pd.setCancelable(false);
        // pd.show();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
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
                    email.setText(resource.getProfile().get(0).getUser_email());

                    if(resource.getProfile().get(0).getUserImage()!=null) {
                        Glide.with(MyProfileActivity.this)
                                .load(resource.getProfile().get(0).getUserImage())
                                .fitCenter()
                                .placeholder(R.drawable.placeholderello).into(img);

                        Glide.with(MyProfileActivity.this)
                                .load(resource.getProfile().get(0).getUserImage())
                                .fitCenter()
                                .placeholder(R.drawable.placeholderello).into(imgselect);
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
                pd.dismiss();
                t.printStackTrace();
                Toast.makeText(MyProfileActivity.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
            }
        });

    }

}