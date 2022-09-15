package com.ellocartuser.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.os.Environment;
import android.util.Base64;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ellocartuser.Login;
import com.ellocartuser.R;
import com.ellocartuser.apiservices.model.Stores;
import com.ellocartuser.home.adapterandmodel.NearByStoreAdapter;
import com.ellocartuser.home.homefragment.CategoryFragment;
import com.ellocartuser.home.homefragment.shopinfotab.TabLayoutt;
import com.ellocartuser.home.mainscreen.HomeScreen;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class Util {


    public static  String uurrll(){
          String text="";
        byte[] data = Base64.decode("Cmh0dHBzOi8vdXNlci5lbGxvY2FydC5jb20vYXBpdS8=", Base64.DEFAULT);
        //String text="";

            try {
                text = new String(data, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        return  text;
    }

    public static  String uurrllserv(){
        String text="";
        byte[] data = Base64.decode("aHR0cHM6Ly91c2VyLmVsbG9jYXJ0LmNvbS8=", Base64.DEFAULT);
        //String text="";


        try {
            text = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return  text;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static void loadFragment(final Fragment fragment, FragmentActivity activity,Fragment thiss) {


//        // create a transaction for transition here
//        final FragmentTransaction transaction = activity.getSupportFragmentManager()
//                .beginTransaction();
//
//        // put the fragment in place
//        transaction.replace(R.id.fragment_container1, fragment);
//
//        // this is the part that will cause a fragment to be added to backstack,
//        // this way we can return to it at any time using this tag
//        transaction.addToBackStack(fragment.getClass().getName());
//
//
//        transaction.commit();
//


        // create a transaction for transition here
        final FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction();

        // put the fragment in place
        //    transaction.replace(R.id.fragment_container1, fragment);

        transaction.add(R.id.fragment_container1, fragment);
   try {
       transaction.hide(thiss);
   }catch (Exception ex){

//       transaction.add(R.id.fragment_container1, fragment);
//       transaction.hide(thiss);
       //transaction.replace(R.id.fragment_container1, fragment);
   }
        // this is the part that will cause a fragment to be added to backstack,
        // this way we can return to it at any time using this tag
        transaction.addToBackStack(activity.getClass().getName());


        transaction.commit();

    }

    public static void loadFragment123(final Fragment fragment, FragmentActivity activity) {

        // create a transaction for transition here
        final FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction();

        // put the fragment in place
        transaction.replace(R.id.fragment_container1, fragment);

        // this is the part that will cause a fragment to be added to backstack,
        // this way we can return to it at any time using this tag
        transaction.addToBackStack(fragment.getClass().getName());

        try
        {
            transaction.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void openFragment(Fragment fragment, String tag, FragmentActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment existingFragment = fragmentManager.findFragmentByTag(tag);
        if (existingFragment != null) {
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container1);
            fragmentTransaction.hide(currentFragment);
            fragmentTransaction.show(existingFragment);
        }
        else {
            fragmentTransaction.add(R.id.container, fragment, tag);
        }
        fragmentTransaction.commit();
    }

    public static void loadFragmentAndClerBackStack(final Fragment fragment, FragmentActivity activity,Fragment thiss) {

        // create a transaction for transition here
        final FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction();

        // put the fragment in place
        transaction.replace(R.id.fragment_container1, fragment);

        // this is the part that will cause a fragment to be added to backstack,
        // this way we can return to it at any time using this tag
        transaction.addToBackStack(fragment.getClass().getName());
        FragmentManager fm = activity.getSupportFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction.commit();
    }

   public static void PleaseLogin(Context context){

        SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = pref.getString("user_id","");
        if(id.equals("") || id==null){
            try {
                loadhome(context);
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
                builder.setMessage("Please Login ");
                builder.setCancelable(false);

                builder.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                                Intent ii=new Intent(context, Login.class);
                                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                context.startActivity(ii);
                            }
                        });

                builder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent ii=new Intent(context, HomeScreen.class);
                                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                //  ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                context.startActivity(ii);

                            }
                        });

                androidx.appcompat.app.AlertDialog alert = builder.create();
                alert.show();

            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }

        }
    }

    public static  void AlertWithGoBAck(Activity aty, String message){
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(aty);
//            builder.setTitle("UPDATE AVILABLE");
            builder.setMessage(message);
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            aty.onBackPressed();

                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();

        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }

    }

    public static  void AlertWithOK( Activity aty, String message){
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(aty);
//            builder.setTitle("UPDATE AVILABLE");
            builder.setMessage(message);
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                          dialog.dismiss();

                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();

        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }

    }

    public  static void loadhome(Context aty){
        SharedPreferences  pref=aty
                .getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //   editor.putString("user_id","346");
        editor.putBoolean("loadhome",false);
        editor.commit();
    }


    public static File saveBitmapToFile(File file){
        try {

            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE=75;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            // here i override the original image file
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 60 , outputStream);



            return file;
        } catch (Exception e) {
            return null;
        }
    }


    public static File getCompressedImageFile(File file, Context mContext) {
        try {
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;

            if (getFileExt(file.getName()).equals("png") || getFileExt(file.getName()).equals("PNG")) {
                o.inSampleSize = 6;
            } else {
                o.inSampleSize = 6;
            }

            FileInputStream inputStream = new FileInputStream(file);
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE = 100;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);

            ExifInterface ei = new ExifInterface(file.getAbsolutePath());
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);

            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    selectedBitmap = rotateImage(selectedBitmap, 90);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    selectedBitmap = rotateImage(selectedBitmap, 180);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    selectedBitmap = rotateImage(selectedBitmap, 270);
                    break;

                case ExifInterface.ORIENTATION_NORMAL:

                default:
                    break;
            }
            inputStream.close();


            // here i override the original image file
            File folder = new File(Environment.getExternalStorageDirectory() + "/temp");
            boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdir();
            }
            if (success) {
                File newFile = new File(new File(folder.getAbsolutePath()), file.getName());
                if (newFile.exists()) {
                    newFile.delete();
                }
                FileOutputStream outputStream = new FileOutputStream(newFile);

                if (getFileExt(file.getName()).equals("png") || getFileExt(file.getName()).equals("PNG")) {
                    selectedBitmap.compress(Bitmap.CompressFormat.PNG, 60, outputStream);
                } else {
                    selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream);
                }

                return newFile;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public static String getMapKeyData(){
        String a= "AIzaSyBSh" ;
        String b= "NZA1A-uH4h" ;
        String c= "31MGromtilf_" ;
        String d= "NCCfnyHo";

        return (a+b+c+d);
    }


}
