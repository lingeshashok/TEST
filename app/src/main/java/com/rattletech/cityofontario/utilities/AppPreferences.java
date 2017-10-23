package com.rattletech.cityofontario.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by android1 on 1/17/2017.
 */

public class AppPreferences {
    String PRIVATE_PREF = "HomeBeamSharedPreference";
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    Context context;


    String username = "username";
    String userId = "userid";
    String password = "password";
    String remember = "remember";
    String loginStatus = "true";
    String serviceStatus = "on";
    String gatewayStatus = "off";
    String groupIcon = "icon";
    String gatewayInstanceId = "gatewayInstanceId";
    String mqttUrl = "mqttUrl";

    String firstName  = "firstName";
    String lastName = "lastName";
    String phNumber = "phNumber";
    String macId = "macID";
    String token ="token";
    String autoRefreshFlag = "autoRefreshFlag";
    String rememberLogin = "rememberLogin";
    String localHostUrl = "localHostUrl";
    String registeredGatewayId = "registeredGatewayId";


    public AppPreferences(Context context) {
        this.context = context;
        sharedPrefs = this.context.getSharedPreferences(PRIVATE_PREF, Context.MODE_PRIVATE);
    }
    public void setFirstName(String firstName1) {
        editor = sharedPrefs.edit();
        editor.putString(firstName, firstName1);
        editor.commit();
    }

    public void setRegisteredGatewayId(String registeredGatewayId1) {
        editor = sharedPrefs.edit();
        editor.putString(registeredGatewayId, registeredGatewayId1);
        editor.commit();
    }
    public void setLocalHostUrl(String localHostUrl1) {
        editor = sharedPrefs.edit();
        editor.putString(localHostUrl, localHostUrl1);
        editor.commit();
    }
    public void setRememberLoginstatus(String rememberLogin1) {
        editor = sharedPrefs.edit();
        editor.putString(rememberLogin, rememberLogin1);
        editor.commit();
    }
    public void setAutoRefreshFlag(String autoRefreshFlag1) {
        editor = sharedPrefs.edit();
        editor.putString(autoRefreshFlag, autoRefreshFlag1);
        editor.commit();
    }
    public void setUserId(String userId1) {
        editor = sharedPrefs.edit();
        editor.putString(userId, userId1);
        editor.commit();
    }
    public void setMqttUrl(String mqttUrl1) {
        editor = sharedPrefs.edit();
        editor.putString(mqttUrl, mqttUrl1);
        editor.commit();
    }
    public void setGatewayInstanceId(String gatewayInstanceId1) {
        editor = sharedPrefs.edit();
        editor.putString(gatewayInstanceId, gatewayInstanceId1);
        editor.commit();
    }


    public void setUsername(String username1) {
        editor = sharedPrefs.edit();
        editor.putString(username, username1);
        editor.commit();
    }

    public void setLastName(String lastName1) {
        editor = sharedPrefs.edit();
        editor.putString(lastName, lastName1);
        editor.commit();
    }

    public void setPhNumber(String phNumber1) {
        editor = sharedPrefs.edit();
        editor.putString(phNumber, phNumber1);
        editor.commit();
    }

    public void setMacId(String macId1) {
        editor = sharedPrefs.edit();
        editor.putString(macId, macId1);
        editor.commit();
    }
    public void setToken(String token1) {
        editor = sharedPrefs.edit();
        editor.putString(token, token1);
        editor.commit();
    }

    public void setLoginResStatus(String status) {
        editor = sharedPrefs.edit();
        editor.putString(loginStatus, status);
        editor.commit();
    }
    public void setPassword(String PasswordStr) {
        editor = sharedPrefs.edit();
        editor.putString(password, PasswordStr);
        editor.commit();
    }
    public void setRemember(String Remember) {
        editor = sharedPrefs.edit();
        editor.putString(remember, Remember);
        editor.commit();
    }

    public void setGroupIcon(String groupIcon1) {
        editor = sharedPrefs.edit();
        editor.putString(groupIcon, groupIcon1);
        editor.commit();
    }

    public void setServiceStatus(String serviceStatus1) {
        editor = sharedPrefs.edit();
        editor.putString(serviceStatus, serviceStatus1);
        editor.commit();
    }

    public void setGatewayStatus(String gatewayStatus1) {
        editor = sharedPrefs.edit();
        editor.putString(gatewayStatus, gatewayStatus1);
        editor.commit();
    }
    public String getUsername() {
        return sharedPrefs.getString(username, null);
    }
    public String getRegisteredGatewayId() {
        return sharedPrefs.getString(registeredGatewayId, null);
    }

    public String getLocalHostUrl() {
        return sharedPrefs.getString(localHostUrl, null);
    }


    public String getRememberLoginStatus() {
        return sharedPrefs.getString(rememberLogin, null);
    }


    public String getAutoRefreshFlag() {
        return sharedPrefs.getString(autoRefreshFlag, null);
    }
    public String getUserId() {
        return sharedPrefs.getString(userId, null);
    }
    public String getFirstName() {
        return sharedPrefs.getString(firstName, null);
    }

    public String getGatewayInstanceId() {
        return sharedPrefs.getString(gatewayInstanceId, null);
    }
    public String getMqttUrl() {
        return sharedPrefs.getString(mqttUrl, null);
    }
    public String getLastName() {
        return sharedPrefs.getString(lastName, null);
    }
    public String getPhNumber() {
        return sharedPrefs.getString(phNumber, null);
    }
    public String getMacId() {
        return sharedPrefs.getString(macId, null);
    }
    public String getToken() {
        return sharedPrefs.getString(token, null);
    }

    public String getServiceStatus() {
        return sharedPrefs.getString(serviceStatus, null);
    }
    public String getGroupIcon() {
        return sharedPrefs.getString(groupIcon, null);
    }
    public String getPassword() {
        return sharedPrefs.getString(password, null);
    }
    public String getRemember() {
        return sharedPrefs.getString(remember, null);
    }
    public String getLoginResStatus() {
        return sharedPrefs.getString(loginStatus, null);
    }
    public String getGatewayStatus() {
        return sharedPrefs.getString(gatewayStatus, null);
    }
}
