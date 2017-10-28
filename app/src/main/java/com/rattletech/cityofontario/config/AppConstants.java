package com.rattletech.cityofontario.config;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.MenuItem;

import com.rattletech.cityofontario.R;

import com.rattletech.cityofontario.model.GetBeaconVO;
import com.rattletech.cityofontario.utilities.CustomTypefaceSpan;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by android1 on 5/19/2017.
 */

public class AppConstants {


public static int B_NAV_INDEX = 0;
    public static int tmp_imageview_height=280;
    public static boolean tmp_db_value=true;
    public static boolean tmp_beacon_fragment_check=false;
    public static int tmp_count=0;
    public static int tmp_update_count=0;
    public static int rememberme_count=0;
    public static int tmp_audioselectedposition;
    public static String tmp_destroy_record="true";
    public static boolean nearby_beacon_check=true;
    public static boolean progress_loading=false;
    public static boolean source_activity_enable=true;


    public static String anlSessionID="";
    public static ArrayList<GetBeaconVO> tmp_beacon_list=new ArrayList<GetBeaconVO>();
    public static ArrayList<GetBeaconVO> tmp_home_beacon_list=new ArrayList<GetBeaconVO>();
    //public static ArrayList<ProjectDetailsIO> tmp_pd_list = new ArrayList<ProjectDetailsIO>();
    public static boolean tmp_tooltip_hide=false;

    public static void applyFontToMenuItem(MenuItem mi,Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "OpenSans-CondBold.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }


    public static class BottomNavigationViewHelper {
        @SuppressLint("RestrictedApi")
        public static void disableShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    //noinspection RestrictedApi
                    item.setShiftingMode(false);
                    // set once again checked value, so view will be updated
                    //noinspection RestrictedApi
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BNVHelper", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e);
            }
        }
    }

    public static boolean isNetworkAvailable(Context context) {

        if(context!=null) {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void internetNotConnectedAlert(Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("No Internet Connection");
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        alertDialogBuilder.setMessage("You are offline please check your internet connection");
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
