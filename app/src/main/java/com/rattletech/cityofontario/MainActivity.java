package com.rattletech.cityofontario;

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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.rattletech.cityofontario.config.ApiServiceConstants;
import com.rattletech.cityofontario.config.AppConstants;
import com.rattletech.cityofontario.fragments.CalendarFragment;
import com.rattletech.cityofontario.fragments.BannerFragment;
import com.rattletech.cityofontario.fragments.CityFragment;
import com.rattletech.cityofontario.fragments.ContactFragment;
import com.rattletech.cityofontario.fragments.NewsFragment;
import com.rattletech.cityofontario.interfaces.CustomModelInterface;
import com.rattletech.cityofontario.utilities.AppPreferences;

import java.util.Timer;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, CustomModelInterface.OnCustomStateListener {

    private static final String TAG_BANNER = "banner";
    private static final String TAG_CALENDER = "calender";
    private static final String TAG_MYCITY = "mycity";
    private static final String TAG_NEWS = "news";
    private static final String TAG_CONTACTUS = "contactus";
    public static String CURRENT_TAG = TAG_BANNER;

    public static int navItemIndex = 0;
    private Handler mHandler;
    BottomNavigationView bottomNavigationView;
    ImageView nearbyBeaconImageview;

    AppPreferences pref;
    Boolean addDevice = false;
    Timer timerAutoRefersh;
    static Runnable myRunnable;
    final Handler handler = new Handler();
    Typeface typefaceBold;
    String deviceType;
    int currentapiVersion;
    private String androidDeviceId, deviceNAme;


    boolean fragment1Init = false;
    boolean fragment2Init = false;
    boolean fragment3Init = false;
    boolean fragment4Init = false;
    boolean fragment5Init = false;

    Fragment fragmenttest1 = new BannerFragment();
    Fragment fragmenttest2 = new CalendarFragment();
    Fragment fragmenttest3 = new CityFragment();
    Fragment fragmenttest4 = new NewsFragment();
    Fragment fragmenttest5 = new ContactFragment();
    Fragment currentFragmentState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();
        pref = new AppPreferences(MainActivity.this);
        typefaceBold = ApiServiceConstants.getFontFamily(MainActivity.this, "bold");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        nearbyBeaconImageview = (ImageView) findViewById(R.id.nearby_beacon_imageview);
        timerAutoRefersh = new Timer();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setItemIconTintList(null);

        AppConstants.BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu m = bottomNavigationView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);
            AppConstants.applyFontToMenuItem(mi, MainActivity.this);

        }



        navItemIndex = 0;
        AppConstants.B_NAV_INDEX = 0;
        CURRENT_TAG = TAG_BANNER;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (!fragment1Init) {
            fragmentTransaction.add(R.id.content, fragmenttest1, CURRENT_TAG);
            fragmentTransaction.commit();
            fragment1Init = true;
        } else {
            fragmentTransaction.hide(currentFragmentState);
            fragmentTransaction.show(fragmenttest1);
            fragmentTransaction.commit();
        }
        currentFragmentState = fragmenttest1;
        addDevice = false;
        invalidateOptionsMenu();


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
        nearbyBeaconImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NearbyBeaconActivity.class);
                startActivity(intent);
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

            case R.id.nav_home:
                navItemIndex = 0;
                AppConstants.B_NAV_INDEX = 0;
                CURRENT_TAG = TAG_BANNER;



                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (!fragment1Init) {
                    fragmentTransaction.hide(currentFragmentState);
                    fragmentTransaction.add(R.id.content, fragmenttest1, CURRENT_TAG);
                    fragmentTransaction.commit();
                    fragment1Init = true;
                } else {
                    fragmentTransaction.hide(currentFragmentState);
                    fragmentTransaction.show(fragmenttest1);
                    fragmentTransaction.commit();
                }
                currentFragmentState = fragmenttest1;
                addDevice = false;
                invalidateOptionsMenu();

                break;
            case R.id.nav_calendar:

                navItemIndex = 1;
                AppConstants.B_NAV_INDEX = 1;
                CURRENT_TAG = TAG_CALENDER;

                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                if (!fragment2Init) {
                    fragmentTransaction1.hide(currentFragmentState);
                    fragmentTransaction1.add(R.id.content, fragmenttest2, CURRENT_TAG);
                    fragmentTransaction1.commit();
                    fragment2Init = true;
                } else {
                    fragmentTransaction1.hide(currentFragmentState);
                    fragmentTransaction1.show(fragmenttest2);
                    fragmentTransaction1.commit();
                }
                currentFragmentState = fragmenttest2;
                invalidateOptionsMenu();
                break;
            case R.id.nav_my_city:

                navItemIndex = 2;
                AppConstants.B_NAV_INDEX = 2;
                CURRENT_TAG = TAG_MYCITY;

                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();

                if (!fragment3Init) {
                    fragmentTransaction2.hide(currentFragmentState);
                    fragmentTransaction2.add(R.id.content, fragmenttest3, CURRENT_TAG);
                    fragmentTransaction2.commit();
                    fragment3Init = true;
                } else {
                    fragmentTransaction2.hide(currentFragmentState);
                    fragmentTransaction2.show(fragmenttest3);
                    fragmentTransaction2.commit();
                }
                currentFragmentState = fragmenttest3;
                invalidateOptionsMenu();

                break;
            case R.id.nav_news:

                navItemIndex = 3;
                AppConstants.B_NAV_INDEX = 3;
                CURRENT_TAG = TAG_NEWS;

                FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();

                if (!fragment4Init) {
                    fragmentTransaction3.hide(currentFragmentState);
                    fragmentTransaction3.add(R.id.content, fragmenttest4, CURRENT_TAG);
                    fragmentTransaction3.commit();
                    fragment4Init = true;
                } else {
                    fragmentTransaction3.hide(currentFragmentState);
                    fragmentTransaction3.show(fragmenttest4);
                    fragmentTransaction3.commit();
                }
                currentFragmentState = fragmenttest4;
                invalidateOptionsMenu();

                break;

            case R.id.nav_contact_us:

                navItemIndex = 4;
                AppConstants.B_NAV_INDEX = 4;
                CURRENT_TAG = TAG_CONTACTUS;
                FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();

                if (!fragment5Init) {
                    fragmentTransaction4.hide(currentFragmentState);
                    fragmentTransaction4.add(R.id.content, fragmenttest5, CURRENT_TAG);
                    fragmentTransaction4.commit();
                    fragment5Init = true;
                } else {
                    fragmentTransaction4.hide(currentFragmentState);
                    fragmentTransaction4.show(fragmenttest5);
                    fragmentTransaction4.commit();
                }
                currentFragmentState = fragmenttest5;
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

                BannerFragment pairedDevicesFragment = new BannerFragment();
                return pairedDevicesFragment;

            case 1:

                CalendarFragment calenderFragment = new CalendarFragment();
                return calenderFragment;
            default:
                return new BannerFragment();
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


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nearfield) {


            return true;
        }
        if (id == R.id.add) {
            if (navItemIndex == 1) {

            }

            if (navItemIndex == 2) {


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
            CURRENT_TAG = TAG_BANNER;


            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (!fragment1Init) {
                fragmentTransaction.add(R.id.content, fragmenttest1, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
                fragment1Init = true;
            } else {
                fragmentTransaction.hide(currentFragmentState);
                fragmentTransaction.show(fragmenttest1);
                fragmentTransaction.commitAllowingStateLoss();
            }
            currentFragmentState = fragmenttest1;
            invalidateOptionsMenu();


        } else if (modelState.equals("tab_room")) {
            navItemIndex = 1;
            AppConstants.B_NAV_INDEX = 1;
            CURRENT_TAG = TAG_CALENDER;



            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            if (!fragment2Init) {
                fragmentTransaction1.add(R.id.content, fragmenttest2, CURRENT_TAG);
                fragmentTransaction1.commitAllowingStateLoss();
                fragment2Init = true;
            } else {
                fragmentTransaction1.hide(currentFragmentState);
                fragmentTransaction1.show(fragmenttest2);
                fragmentTransaction1.commitAllowingStateLoss();
            }
            currentFragmentState = fragmenttest2;
            invalidateOptionsMenu();
        } else if (modelState.equals("tab_rule")) {

            navItemIndex = 2;
            AppConstants.B_NAV_INDEX = 2;
            CURRENT_TAG = TAG_MYCITY;



            FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();

            if (!fragment3Init) {
                fragmentTransaction2.add(R.id.content, fragmenttest3, CURRENT_TAG);
                fragmentTransaction2.commitAllowingStateLoss();
                fragment3Init = true;
            } else {
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
