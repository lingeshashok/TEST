package com.rattletech.cityofontario.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.rattletech.cityofontario.utilities.CityOfOntarioApp;

/**
 * Created by vikram on 7/5/2016.
 */
public class AltBeaconService  extends Service {

    @Override
    public void onStart(Intent intent, int startId) {

        ((CityOfOntarioApp)this.getApplicationContext()).onCreate();


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}
