package com.rattletech.cityofontario;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rattletech.cityofontario.config.ApiServiceConstants;
import com.rattletech.cityofontario.config.AppConstants;
import com.rattletech.cityofontario.fragments.RulesManagementFragment;
import com.rattletech.cityofontario.utilities.AppPreferences;
import com.rattletech.cityofontario.fragments.PairedDevicesFragment;
import com.rattletech.cityofontario.fragments.PairedHomesFragment;
import com.rattletech.cityofontario.interfaces.CustomModelInterface;




import java.util.Timer;



public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, CustomModelInterface.OnCustomStateListener {

    private static final String TAG_PAIRED_DEVICES = "paired_devices";
    private static final String TAG_ROOMS = "rooms";
    private static final String TAG_RULES = "rule";
    public static String CURRENT_TAG = TAG_PAIRED_DEVICES;
    public static int navItemIndex = 0;
    private Handler mHandler;
    TextView toolbar_title;

    String  timeOut = "55";
    public static final int profileResponseCode = 15;
    public static final int deviceDetailsResponseCode = 25;

    BottomNavigationView bottomNavigationView;
    ImageView signOutImageview;

    AppPreferences pref;
    Boolean addDevice = false;
    boolean openGroup = false;
    String dialogMessage = "Scanning for new devices...";


    Timer timerAutoRefersh;
    static Runnable myRunnable;
    final Handler handler = new Handler();
    private long duration = 66 * 1000;
    Dialog globalDialog;
    String strGroupName;

    public static final String KEY_TO_REFRESH_PROPERTY = "refresh_list_property";
    public static final String KEY_TO_RELOAD_LIST_DEVICE = "refresh_reload_list";

    Typeface typefaceRegular, typefaceBold, typefaceLight;

    AlertDialog alertProgressDialog;
    String deviceType;
    int currentapiVersion;
    private String androidDeviceId, deviceNAme;


    boolean fragment1Init = false;
    boolean fragment2Init = false;
    boolean fragment3Init = false;

    Fragment fragmenttest1 = new PairedDevicesFragment();
    Fragment fragmenttest2 = new PairedHomesFragment();
    Fragment fragmenttest3 = new RulesManagementFragment();

    Fragment currentFragmentState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();
        pref = new AppPreferences(MainActivity.this);

        typefaceLight = ApiServiceConstants.getFontFamily(MainActivity.this, "light");
        typefaceBold = ApiServiceConstants.getFontFamily(MainActivity.this, "bold");
        typefaceRegular = ApiServiceConstants.getFontFamily(MainActivity.this, "regular");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typefaceBold);
        signOutImageview = (ImageView) findViewById(R.id.logout_imageview);
        timerAutoRefersh = new Timer();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setItemIconTintList(null);
        Menu m = bottomNavigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);
            AppConstants.applyFontToMenuItem(mi,MainActivity.this);
        }


        Intent intnet = getIntent();
        addDevice = intnet.getBooleanExtra(getString(R.string.str_add_device_intent), false);

       if (intnet.hasExtra(getString(R.string.menu_rooms))) {
            openGroup = true;
            navItemIndex = 1;
            AppConstants.B_NAV_INDEX = 1;
            loadHomeFragment();
            CURRENT_TAG = TAG_ROOMS;
        } else {


           navItemIndex = 0;
           AppConstants.B_NAV_INDEX = 0;
           CURRENT_TAG = TAG_PAIRED_DEVICES;
           signOutImageview.setVisibility(View.VISIBLE);
           FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

           if (!fragment1Init) {
               fragmentTransaction.add(R.id.content, fragmenttest1, CURRENT_TAG);
               fragmentTransaction.commit();
               fragment1Init = true;
           }else{
               fragmentTransaction.hide(currentFragmentState);
               fragmentTransaction.show(fragmenttest1);
               fragmentTransaction.commit();
           }
           currentFragmentState = fragmenttest1;
           addDevice = false;
           invalidateOptionsMenu();

        }

        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (tabletSize) {
            deviceType = "Tablet";
        } else {
            deviceType = "Mobile";
        }
        currentapiVersion = Build.VERSION.SDK_INT;
        androidDeviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        deviceNAme = getDeviceName();

        CustomModelInterface.getInstance().setListener(this);

      signOutImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KEY_TO_RELOAD_LIST_DEVICE);
                i.putExtra("key","refresh");
                sendBroadcast(i);
            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(myRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(myRunnable, 15000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        handler.removeCallbacks(myRunnable);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        @StringRes int text;
        switch (item.getItemId()) {

            case R.id.navigation_home:

                navItemIndex = 0;
                AppConstants.B_NAV_INDEX = 0;
                CURRENT_TAG = TAG_PAIRED_DEVICES;
                signOutImageview.setVisibility(View.VISIBLE);



                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (!fragment1Init) {
                    fragmentTransaction.hide(currentFragmentState);
                    fragmentTransaction.add(R.id.content, fragmenttest1, CURRENT_TAG);
                    fragmentTransaction.commit();
                    fragment1Init = true;
                }else{
                    fragmentTransaction.hide(currentFragmentState);
                    fragmentTransaction.show(fragmenttest1);
                    fragmentTransaction.commit();
                }
                currentFragmentState = fragmenttest1;
                addDevice = false;
                invalidateOptionsMenu();

                break;
            case R.id.navigation_dashboard:

                navItemIndex = 1;
                AppConstants.B_NAV_INDEX = 1;
                CURRENT_TAG = TAG_ROOMS;
                signOutImageview.setVisibility(View.INVISIBLE);
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                if (!fragment2Init) {
                    fragmentTransaction1.hide(currentFragmentState);
                    fragmentTransaction1.add(R.id.content, fragmenttest2, CURRENT_TAG);
                    fragmentTransaction1.commit();
                    fragment2Init = true;
                }else{
                    fragmentTransaction1.hide(currentFragmentState);
                    fragmentTransaction1.show(fragmenttest2);
                    fragmentTransaction1.commit();
                }
                currentFragmentState = fragmenttest2;
                invalidateOptionsMenu();
                break;
           case R.id.rule_management:

               navItemIndex = 2;
                AppConstants.B_NAV_INDEX = 2;
                CURRENT_TAG = TAG_RULES;
                signOutImageview.setVisibility(View.INVISIBLE);
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();

                if (!fragment3Init) {
                    fragmentTransaction2.hide(currentFragmentState);
                    fragmentTransaction2.add(R.id.content, fragmenttest3, CURRENT_TAG);
                    fragmentTransaction2.commit();
                    fragment3Init = true;
                }else{
                    fragmentTransaction2.hide(currentFragmentState);
                    fragmentTransaction2.show(fragmenttest3);
                    fragmentTransaction2.commit();
                }
                currentFragmentState = fragmenttest3;
                invalidateOptionsMenu();

                 break;

            default:
                return false;
        }

        return true;
    }



    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:

                PairedDevicesFragment pairedDevicesFragment = new PairedDevicesFragment();
                return pairedDevicesFragment;

            case 1:

                PairedHomesFragment pairedRoomsFragment = new PairedHomesFragment();
                return pairedRoomsFragment;

            default:
                return new PairedDevicesFragment();
        }
    }

    private void loadHomeFragment() {
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.content, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
        invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (navItemIndex == 0) {
            if (addDevice)
                getMenuInflater().inflate(R.menu.paired_rooms_menu, menu);
            else
                getMenuInflater().inflate(R.menu.paired_device_menu, menu);
            toolbar_title.setText(R.string.str_paired_device);





        } else if(navItemIndex == 1){
            getMenuInflater().inflate(R.menu.paired_rooms_menu, menu);
            toolbar_title.setText(R.string.menu_rooms);




        }else{
            getMenuInflater().inflate(R.menu.paired_rooms_menu, menu);
            toolbar_title.setText(R.string.menu_rule);



        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nearfield) {


            return true;
        }
        if (id == R.id.add) {
            if(navItemIndex == 1){

            }

            if(navItemIndex == 2){



            }

            return true;
        }
        if (id == R.id.action_profile) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }
    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }





    @Override
    public void stateChanged() {

        String modelState = CustomModelInterface.getInstance().getState();
        if (modelState.equals("tab_device")) {
            navItemIndex = 0;
            AppConstants.B_NAV_INDEX = 0;
            CURRENT_TAG = TAG_PAIRED_DEVICES;
            toolbar_title.setText(getString(R.string.menu_paired_devices));
            signOutImageview.setVisibility(View.VISIBLE);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (!fragment1Init) {
                fragmentTransaction.add(R.id.content, fragmenttest1, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
                fragment1Init = true;
            }else{
                fragmentTransaction.hide(currentFragmentState);
                fragmentTransaction.show(fragmenttest1);
                fragmentTransaction.commitAllowingStateLoss();
            }
            currentFragmentState = fragmenttest1;
            invalidateOptionsMenu();


        } else if (modelState.equals("tab_room")) {
            navItemIndex = 1;
            AppConstants.B_NAV_INDEX = 1;
            CURRENT_TAG = TAG_ROOMS;
            toolbar_title.setText(getString(R.string.menu_rooms));
            signOutImageview.setVisibility(View.INVISIBLE);

            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            if (!fragment2Init) {
                fragmentTransaction1.add(R.id.content, fragmenttest2, CURRENT_TAG);
                fragmentTransaction1.commitAllowingStateLoss();
                fragment2Init = true;
            }else{
                fragmentTransaction1.hide(currentFragmentState);
                fragmentTransaction1.show(fragmenttest2);
                fragmentTransaction1.commitAllowingStateLoss();
            }
            currentFragmentState = fragmenttest2;
            invalidateOptionsMenu();
        }else if(modelState.equals("tab_rule")){

           navItemIndex = 2;
            AppConstants.B_NAV_INDEX = 2;
            CURRENT_TAG = TAG_RULES;

            signOutImageview.setVisibility(View.INVISIBLE);

            FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();

            if (!fragment3Init) {
                fragmentTransaction2.add(R.id.content, fragmenttest3, CURRENT_TAG);
                fragmentTransaction2.commitAllowingStateLoss();
                fragment3Init = true;
            }else{
                fragmentTransaction2.hide(currentFragmentState);
                fragmentTransaction2.show(fragmenttest3);
                fragmentTransaction2.commitAllowingStateLoss();
            }
            currentFragmentState = fragmenttest3;
            invalidateOptionsMenu();

        }
        bottomNavigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
