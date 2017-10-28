package com.rattletech.cityofontario.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by vikram on 7/5/2016.
 */
public class BackgroundReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("service started","service started");
        Intent intentservice = new Intent(context,AltBeaconService.class);
        context.startService(intentservice);
    }
}
