package com.rattletech.cityofontario.utilities;

import android.app.ActivityManager;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;

import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.rattletech.cityofontario.Helper.DataBaseHandler;
import com.rattletech.cityofontario.config.ApiServiceConstants;
import com.rattletech.cityofontario.config.AppConstants;
import com.rattletech.cityofontario.model.GetBeaconVO;


import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


import static com.android.volley.VolleyLog.TAG;

/**
 * Created by nithya on 5/18/2017.
 */

public class CityOfOntarioApp extends Application  implements BeaconConsumer {
    private static CityOfOntarioApp mInstance;

    AppPreferences pref;

    public static final String TAG = CityOfOntarioApp.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private BackgroundPowerSaver backgroundPowerSaver;
    BeaconManager beaconManager;
    ArrayList<GetBeaconVO> getbeaconlist,originalbeaconlist;
    String[] uuid_array;
    List<String> stringList;
    List<String> stringList1;
    DataBaseHandler dbhandler;
    int count=0;
    int range_flag=1;
    String uuid_listed="";
    int NOTIFICATION_ID;
    //ArrayList<ProjectsIO> notificationlist;
    public static final String KEY_REFRESH_LIST="refresh_list";
    public static NotificationManager notificationManager;




    public void onCreate() {
        super.onCreate();

        mInstance = this;
        ApiServiceConstants.SERVICE_STATUS = "OFF";
        pref = new AppPreferences(mInstance);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.setForegroundScanPeriod(2000);
        beaconManager.setBackgroundScanPeriod(3000);
        setupBeaconManager();
        backgroundPowerSaver = new BackgroundPowerSaver(this);

    }

    private void setupBeaconManager()
    {
        if (!beaconManager.isBound(this))
            beaconManager.bind(this);
    }

   

    public ArrayList<GetBeaconVO> show_nearby_beacons(){

        return originalbeaconlist;
    }

    public String getbeacons()
    {
        return uuid_listed;
    }

    public static boolean isAppInForeground(
            Context context) {

        List<ActivityManager.RunningTaskInfo> task = ((ActivityManager)
                context.getSystemService(
                        Context.ACTIVITY_SERVICE))
                .getRunningTasks(1);
        if (task.isEmpty()) {
            return false;
        }
        return task
                .get(0)
                .topActivity
                .getPackageName()
                .equalsIgnoreCase(context.getPackageName());
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Log.e("i just ","saw a beacon");
                if(!isAppInForeground(getApplicationContext()))
                {
                    range_flag=0;
                }
            }
            @Override
            public void didExitRegion(Region region) {
                Log.e("beacon out of range","beacon out of range");
                try {
                    originalbeaconlist.clear();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.d("DetermineState ---", "I have just switched from seeing/not seeing iBeacons: "+state);
            }
        });

        try {
            ArrayList<Identifier> identifiers = new ArrayList<Identifier>();
            identifiers.add(null);
            beaconManager.startMonitoringBeaconsInRegion(new Region("myRangingUniqueId",identifiers));

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region arg1) {
                if (beacons.size() > 0) {
                    Log.e("beacons size","---------beacons size-----------"+beacons.size());
                    AppConstants.tmp_count=0;
                    getbeaconlist = new ArrayList<GetBeaconVO>();
                    originalbeaconlist=new ArrayList<GetBeaconVO>();

                    for (Beacon elem : beacons) {
                        double distance=elem.getDistance();
                        GetBeaconVO beaconvo = new GetBeaconVO();
                        beaconvo.uuid = (elem.getId1().toString()+"-"+elem.getId2().toString()+"-"+elem.getId3().toString()).toUpperCase();
                        beaconvo.tmp_uuid=(elem.getId1().toString()+"-"+elem.getId2().toString()+"-"+elem.getId3().toString()+":"+elem.getDistance()).toUpperCase();
                        //String uuid_check=elem.getId1().toString()+"-"+elem.getId2().toString()+"-"+elem.getId3().toString()+":"+elem.getDistance();
                        beaconvo.major = elem.getId2().toString();
                        beaconvo.minor = elem.getId3().toString();
                        beaconvo.rssi = String.valueOf(elem.getRssi());
                        beaconvo.measuredpower = String.valueOf(elem.getTxPower());
                        beaconvo.selected="false";
                        beaconvo.distance=distance;
                        getbeaconlist.add(beaconvo);
                    }
                    for (Beacon elem : beacons) {
                        double orgdisatance=elem.getDistance();
                        GetBeaconVO beaconvo = new GetBeaconVO();
                        beaconvo.uuid = elem.getId1().toString();
                        beaconvo.major = elem.getId2().toString();
                        beaconvo.minor = elem.getId3().toString();
                        beaconvo.rssi = String.valueOf(elem.getRssi());
                        beaconvo.measuredpower = String.valueOf(elem.getTxPower());
                        beaconvo.selected="false";
                        beaconvo.distance=elem.getDistance();
                        originalbeaconlist.add(beaconvo);

                    }



                    if (AppConstants.tmp_beacon_fragment_check) {

                            dbhandler = new DataBaseHandler(mInstance);
                            dbhandler.insertArrayData(getbeaconlist);
                            String[] uuid_array2 = new String[getbeaconlist.size()];
                            for (int i = 0; i < getbeaconlist.size(); i++) {
                                uuid_array2[i] = getbeaconlist.get(i).uuid;
                            }

                            ArrayList<String> ref_uuid_arraylist = new ArrayList<String>(Arrays.asList(uuid_array2));
                            dbhandler.getAllBeaconDetails(ref_uuid_arraylist);

                    }

                    uuid_array=new String[getbeaconlist.size()];
                    uuid_listed="";
                    for(int i=0;i<getbeaconlist.size();i++)
                    {
                        uuid_array[i]=getbeaconlist.get(i).uuid;
                    }
                    stringList1 = new ArrayList<String>(Arrays.asList(uuid_array));
                    if(count<2){
                        count=0;
                        stringList = new ArrayList<String>(Arrays.asList(uuid_array));
                    }else{

                    }

                    for(int j=0;j<uuid_array.length;j++)
                    {
                        if(j!=uuid_array.length-1)
                        {
                            uuid_listed=uuid_listed+uuid_array[j]+",";
                        }
                        else
                        {
                            uuid_listed=uuid_listed+uuid_array[j];
                        }
                    }

                    if(isAppInForeground(getApplicationContext())){

                            AppConstants.tmp_beacon_list = getbeaconlist;
                            Intent i = new Intent(KEY_REFRESH_LIST);
                            sendBroadcast(i);

                    }

                    if(!isAppInForeground(getApplicationContext())&&range_flag==0)
                    {
                        range_flag=1;
                       // makeJsonObjectRequest(uuid_listed);
                    }
                }else{
                    if(AppConstants.tmp_beacon_fragment_check){
                        if(beacons.isEmpty()){
                            if(AppConstants.tmp_count<=1){
                                AppConstants.tmp_count++;
                                Log.e("delete beacon count","success :"+AppConstants.tmp_count);
                            }else{

                                    dbhandler = new DataBaseHandler(mInstance);
                                    dbhandler.setSameDistanceToAll();
                                    dbhandler.truncateHomeBeaconTable();
                                    uuid_listed = "";
                                    AppConstants.tmp_beacon_list.clear();
                                    AppConstants.tmp_home_beacon_list.clear();
                                    Intent i = new Intent(KEY_REFRESH_LIST);
                                    sendBroadcast(i);

                            }
                        }else{
                            AppConstants.tmp_count=0;
                        }
                    }else{

                    }
                }
            }
        });
        try {
            beaconManager.startRangingBeaconsInRegion( new Region("myMonitoringUniqueId", null, null, null));
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
    
    public static synchronized CityOfOntarioApp getInstance() {
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }


}
