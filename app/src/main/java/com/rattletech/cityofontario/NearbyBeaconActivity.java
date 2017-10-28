package com.rattletech.cityofontario;

import android.app.Dialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;

import com.rattletech.cityofontario.Helper.DataBaseHandler;
import com.rattletech.cityofontario.adapters.HomeVenuePagerAdapter;
import com.rattletech.cityofontario.interfaces.CallbackServiceInterface;
import com.rattletech.cityofontario.model.VenueListIO;
import com.rattletech.cityofontario.serviceFunction.ServiceFunctions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NearbyBeaconActivity extends AppCompatActivity {
    ViewPager viewPager;
    HomeVenuePagerAdapter adapter;
    ArrayList<VenueListIO> venueArrayList;
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_beacon);


        dataBaseHandler = new DataBaseHandler(NearbyBeaconActivity.this);

        String url = "http://citybeam.rattletech.com/api/services/AppCityList?LoginId=1&DeviceId=&BeaconId=74278BDA-B644-4520-8F0C-720EAF059930-100-2,74278BDA-B644-4520-8F0C-720EAF059930-100-4&PageNo=1&Latitude=0.0&Longitude=0.0";

        venueArrayList = new ArrayList<VenueListIO>();
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(5, 0, 5, 0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("first","1");
            }
            @Override
            public void onPageSelected(int position) {
                Log.e("second","2");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("third","3");
            }
        });

        ServiceFunctions serviceFunctions = new ServiceFunctions(NearbyBeaconActivity.this,nearbyRequest);
        serviceFunctions.serviceCallGET(url);



      //  openDialog();
    }

    public void openDialog() {
        final Dialog dialog = new Dialog(NearbyBeaconActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_nearby_details);


        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = (int) (displaymetrics.widthPixels * 0.97);
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        dialog.getWindow().setLayout(width, height);
        dialog.show();
    }

    CallbackServiceInterface nearbyRequest = new CallbackServiceInterface() {
        @Override
        public void callbackObjectCall(JSONObject object) {

            try {
                String strResult = object.getString("Msg");
                if(strResult.equals("Success")){

                    venueArrayList = new ArrayList<VenueListIO>();

                    JSONArray jsonArray = object.getJSONArray("Result");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        VenueListIO venueListIO = new VenueListIO();
                        venueListIO.setBeaconID(jsonObject.getString("BeaconId"));
                        venueListIO.setDistance(jsonObject.getString("Distance"));
                        venueListIO.setVenueTitle(jsonObject.getString("ProjectTitle"));
                        venueListIO.setImageName(jsonObject.getString("CoverImage"));
                        venueListIO.setLocationID(jsonObject.getString("ProjectId"));
                        venueListIO.setAudioName(jsonObject.getString("Audio"));
                        venueListIO.setAddress(jsonObject.getString("Address"));
                        venueListIO.setProjectDescription(jsonObject.getString("ProjectDescription"));
                        venueArrayList.add(venueListIO);
                    }

                    dataBaseHandler.insertRoomsData(venueArrayList);

                    adapter = new HomeVenuePagerAdapter(NearbyBeaconActivity.this, venueArrayList);
                    viewPager.setAdapter(adapter);



                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };
}
