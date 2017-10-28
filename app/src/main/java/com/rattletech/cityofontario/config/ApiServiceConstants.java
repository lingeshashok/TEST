package com.rattletech.cityofontario.config;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;



/**
 * Created by nithya on 5/17/2017.
 */

public class ApiServiceConstants {

    public static String SERVICE_NAME="";
    public static String SERVICE_HOSTIP="";
    public static String SERVICE_HOSTPORT="";
    public static String SERVICE_HOSTName="";
    public static String SERVICE_MACID="";
    public static String SERVICE_STATUS="";

    public static String SERVICE_SIGNIN="signInUser";
    public static String SERVICE_SIGNIN_VIA_PIN="pinLogin";
    public static String SERVICE_VERIFY_PIN="verifyPinToOnboard";

    public static String SERVICE_VIEW_PROFILE="viewUserProfile";
    public static String SERVICE_REQUEST_OTP="requestPasswordOTP";
    public static String SERVICE_VERIFY_OTP="validatePasswordOTP";
    public static String SERVICE_FORGOT_PASSWORD="forgotPassword";
    public static String SERVICE_SIGNUP="registerUser";
    public static String SERVICE_UPDATE="updateUserProfile";
    public static String SERVICE_REGISTER_WITH_GATEWAY="registerUserWithGateway";
    public static String SERVICE_SAVE_USER_ON_GATEWAY="saveUserOnGateway";
    public static String SERVICE_UPDATE_USER_PASSWORD="updateUserPassword";
    public static String SERVICE_INITIATESCAN="initiateScan";
    public static String SERVICE_CREATENEWGROUP="createGroup";
    public static String SERVICE_PAIR_HUE_BRIDGE="onboardHueBridgeWithSHDAP";
    public static String SERVICE_CREATE_UPDATE_RULE="createUpdateRule";
    public static String SERVICE_LISTGROUPS="listGroups";
    public static String SERVICE_LIST_RULES="listAllRules";
    public static String SERVICE_LISTGROUPDEVICES="listDevicesInGroup";
    public static String SERVICE_REMOVEDEVCEFROMGROUP="removeDeviceFromGroup";
    public static String SERVICE_DELETEGROUP="deleteGroup";
    public static String SERVICE_DELETE_RULE="deleteRule";
    public static String SERVICE_WRITEATTRIBUTE="writeDeviceAttribute";
    public static String SERVICE_RULE_STATUS="togglerule";
    public static String SERVICE_WRITEATTRIBUTEGROUP="toggleGroupDevices";
    public static String SERVICE_ADDDEVICE="addDevice";
    public static String SERVICE_ADDDEVICE_BY_ALIAS="addDeviceByAlias";
    public static String SERVICE_REMOVEDEVICE="removeDevice";
    public static String SERVICE_FINDDEVICE="findDevices";
    public static String SERVICE_LIST_DEVICES_RULE="listDevicesForRules";
    public static String SERVICE_READ_PROPERTY="readDefaultProperty";
    public static String SERVICE_FORGOTPASSWORD="forgotPassword";
    public static String SERVICE_HOSTIP_CLOUD="https://uakwal0dhf.execute-api.us-east-1.amazonaws.com/v1/";

    //Parameter constanta
    public static  String PARAMS_EMAIL="email";
    public static  String PARAMS_OTP_PASSWORD="otp";
    public static  String PARAMS_PASSWORD="password";
    public static  String PARAMS_USERID="userId";
    public static  String PARAMS_GATEWAY_ID="gatewayId";


    public static  String PARAMS_FIRST_NAME="firstName";
    public static  String PARAMS_LAST_NAME="lastName";
    public static  String PARAMS_MOBILE="mobile";
    public static  String PARAMS_GATEWAY_MAC_ID="gatewayMacId";
    public static  String PARAMS_CLIENT="Client";
    public static  String PARAMS_NOTIFICATION_TOKEN="notificationToken";
    public static  String PARAMS_DEVICEID="deviceId";
    public static  String PARAMS_READ_FROM_CACHE="readFromCache";
    public static  String PARAMS_RULE_ID="ruleId";
    public static  String PARAMS_TOGGLE="toggle";
    public static  String PARAMS_DEVICEID_ALIAS="deviceAlias";
    public static  String PARAMS_GROUP_NAME="groupName";
    public static  String PARAMS_CONNCETIVITY_OPTION="connectivityOption";
    public static  String PARAMS_TIMEOUT="timeout";
    public static  String PARAMS_ATTRIBUTE_NAMES="attributeName";
    public static  String PARAMS_ATTRIBUTE_VALUES="attributeValue";
    public static  String PARAMS_EXISTING_GROUP_NAME="existingGroupName";
    public static  String PARAMS_DEVICE_TYPE="deviceType";
    public static  String PARAMS_CONNECTION_TYPE="connectionType";
    public static  String PARAMS_NEW_GROUP_NAME="newGroupName";

    public static String ENCRYPTION_PASSWORD="inTelSHdap";
    public static String ENCRYPTION_IV="1234432156788765";

    public static String SERVICE_EDIT_ALIAS_NAME="setDeviceAlias";
    public static String SERVICE_LIST_GROUP="listGroups";
    public static String SERVICE_DEVICE_SETTING_DETAIL="deviceInfo";

    public static String SERVICE_ADDDEVICETOGROUP="addDeviceToGroup";
    public static String SERVICE_RENAMEGROUP="renameGroup";

    public static String SERVICE_READ_DEVICE_ATTRIBUTES="readDeviceAttribute";
    public static String SERVICE_FIND_DEVICE_BY_ID="findDeviceById";






    /* Typeface setup */


    public static final String strFontBold="OpenSans-CondBold.ttf";
    public static final String strFontMedium="Dosis-Medium.ttf";
    public static final String strFontRegular="Dosis-Regular.ttf";
    public static final String strFontLight="Dosis-Light.ttf";
    public static final String strFontExtraBold="Dosis-ExtraBold.ttf";


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
    public static String getDeviceId(Context context){
       String androidDeviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return androidDeviceId;
    }
    public static Typeface getFontFamily(Context context,String type){
        Typeface typeface = null;
        if(type.equals("bold")) {
            typeface = Typeface.createFromAsset(context.getResources().getAssets(), strFontBold);
        }else if(type.equals("regular")){
        }else if(type.equals("light")){
        }else if(type.equals("extra_bold")){
        }else if(type.equals("medium")){
        }

        return typeface;
    }
}
