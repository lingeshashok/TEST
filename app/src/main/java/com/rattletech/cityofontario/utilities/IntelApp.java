package com.rattletech.cityofontario.utilities;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import android.text.TextUtils;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;
import com.rattletech.cityofontario.config.ApiServiceConstants;



import io.fabric.sdk.android.Fabric;

import java.util.List;


import static com.android.volley.VolleyLog.TAG;

/**
 * Created by nithya on 5/18/2017.
 */

public class IntelApp extends Application {
    private static IntelApp mInstance;
    private RequestQueue mRequestQueue;

    AppPreferences pref;
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        mInstance = this;
        ApiServiceConstants.SERVICE_STATUS = "OFF";
        pref = new AppPreferences(mInstance);

    }
    public static boolean isAppInForeground(Context context) {


        boolean result = false;

        try {

            List<ActivityManager.RunningTaskInfo> task = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1);
            if (task.isEmpty()) {
                return false;
            }
            result = task.get(0).topActivity.getPackageName().equalsIgnoreCase(context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }






    public static synchronized IntelApp getInstance() {
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
