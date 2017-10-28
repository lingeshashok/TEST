package com.rattletech.cityofontario.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;
import android.util.Log;


import com.rattletech.cityofontario.config.AppConstants;
import com.rattletech.cityofontario.model.GetBeaconVO;
import com.rattletech.cityofontario.model.VenueListIO;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by vikram on 5/31/2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_Name = "CityOfOntario.sqlite";

    /*
     *  History table name
     */

    private static final String TABLE_beacon = "beacon";
    private static final String TABLE_rooms = "beacon";
    private static final String TABLE_home_beacon = "home_beacon";

    private static final String KEY_id = "id";
    private static final String KEY_beaconid = "Uuid";
    private static final String KEY_distance = "Distance";
    private static final String KEY_locationFlag = "LocationFlag";
    private static final String KEY_title = "Title";
    private static final String KEY_imageName = "Image";
    private static final String KEY_locationID = "LocationId";
    private static final String KEY_locationCount = "LocationCount";
    private static final String KEY_hasTag = "HasTag";
    private static final String KEY_audioName = "AudioName";
    private static final String KEY_address = "address";
    private static final String KEY_projectDesc = "projectDesc";
    private static final String KEY_ref_count = "ref";




    public DataBaseHandler(Context context) {
        super(context, DATABASE_Name, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {



        String str_beacon_table = "CREATE TABLE IF NOT EXISTS " + TABLE_beacon + "(" + KEY_id + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE," +
                KEY_beaconid + " TEXT," +
                KEY_distance + " REAL," +
                KEY_title + " TEXT," +
                KEY_imageName + " TEXT," +
                KEY_locationID + " TEXT," +
                KEY_locationCount + " TEXT," +
                KEY_hasTag + " TEXT," +
                KEY_audioName + " TEXT," +
                KEY_address + " TEXT," +
                KEY_projectDesc + " TEXT," +
                KEY_ref_count + " TEXT)";
        db.execSQL(str_beacon_table);


        String str_rooms_table = "CREATE TABLE IF NOT EXISTS " + TABLE_rooms + "(" + KEY_id + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE," +
                KEY_beaconid + " TEXT," +
                KEY_distance + " REAL," +
                KEY_title + " TEXT," +
                KEY_imageName + " TEXT," +
                KEY_locationID + " TEXT," +
                KEY_locationCount + " TEXT," +
                KEY_hasTag + " TEXT," +
                KEY_audioName + " TEXT," +
                KEY_address + " TEXT," +
                KEY_projectDesc + " TEXT," +
                KEY_ref_count + " TEXT)";
        db.execSQL(str_rooms_table);


        String str_home_beacon_table = "CREATE TABLE IF NOT EXISTS " + TABLE_home_beacon + "(" + KEY_id + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE," +
                KEY_beaconid + " TEXT," +
                KEY_distance + " REAL," +
                KEY_ref_count + " TEXT)";
        db.execSQL(str_home_beacon_table);
        



       
    }


    // ********* Insert Venue Status **********

    public void insertVenueData(ArrayList<VenueListIO> arrayList) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor check_cursor = null;
            ContentValues values = new ContentValues();
            for (int i = 0; i < arrayList.size(); i++) {
                values.put(KEY_beaconid, arrayList.get(i).getBeaconID());
                values.put(KEY_distance, arrayList.get(i).getDistance());
                values.put(KEY_title, arrayList.get(i).getVenueTitle());
                values.put(KEY_imageName, arrayList.get(i).getImageName());
                values.put(KEY_locationID, arrayList.get(i).getLocationID());
                values.put(KEY_locationCount, arrayList.get(i).getLocationCount());
                values.put(KEY_hasTag, arrayList.get(i).getHastag());
                values.put(KEY_audioName, arrayList.get(i).getAudioName());
                values.put(KEY_address, arrayList.get(i).getAddress());
                values.put(KEY_projectDesc, arrayList.get(i).getProjectDescription());
                values.put(KEY_ref_count, "0");
                String checkQuery = "SELECT * FROM " + TABLE_beacon + " WHERE " + KEY_locationID + " = " + "'" + arrayList.get(0).getLocationID() + "'";
                check_cursor = db.rawQuery(checkQuery, null);
                if (check_cursor.getCount() > 0) {
              /*  String delete_query="DELETE FROM " + TABLE_beacon + " WHERE " + KEY_home_id + " = "+"'" + arrayList.get(i).getHome_id() + "'";
                db.execSQL(delete_query);
                Log.e("check delete... ", "success");*/
                    db.update(TABLE_beacon, values, KEY_locationID + "='" + arrayList.get(0).getLocationID()+ "'", null);

                } else {
                    db.insert(TABLE_beacon, null, values);
                    Log.e("check insert... ", "success");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insertRoomsData(ArrayList<VenueListIO> arrayList) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor check_cursor = null;
            ContentValues values = new ContentValues();
            for (int i = 0; i < arrayList.size(); i++) {
                values.put(KEY_beaconid, arrayList.get(i).getBeaconID());
                values.put(KEY_distance, arrayList.get(i).getDistance());
                values.put(KEY_title, arrayList.get(i).getVenueTitle());
                values.put(KEY_imageName, arrayList.get(i).getImageName());
                values.put(KEY_locationID, arrayList.get(i).getLocationID());
                values.put(KEY_locationCount, arrayList.get(i).getLocationCount());
                values.put(KEY_hasTag, arrayList.get(i).getHastag());
                values.put(KEY_audioName, arrayList.get(i).getAudioName());
                values.put(KEY_address, arrayList.get(i).getAddress());
                values.put(KEY_projectDesc, arrayList.get(i).getProjectDescription());
                values.put(KEY_ref_count, "0");
                String checkQuery = "SELECT * FROM " + TABLE_rooms + " WHERE " + KEY_locationID + " = " + "'" + arrayList.get(0).getLocationID() + "'";
                check_cursor = db.rawQuery(checkQuery, null);
                if (check_cursor.getCount() > 0) {
                    db.update(TABLE_rooms, values, KEY_locationID + "='" + arrayList.get(0).getLocationID()+ "'", null);
                } else {
                    db.insert(TABLE_rooms, null, values);
                    Log.e("check insert... ", "success");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void insertArrayData(ArrayList<GetBeaconVO> beaconList) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor check_cursor = null;
            ContentValues values = new ContentValues();

            for (int i = 0; i < beaconList.size(); i++) {
                String beaconid = beaconList.get(i).getUuid();
                double distance = beaconList.get(i).getDistance();

                String checkQuery = "SELECT * FROM " + TABLE_home_beacon + " WHERE " + KEY_beaconid + " = " + "'" + beaconid + "'";

                check_cursor = db.rawQuery(checkQuery, null);
                if (check_cursor.getCount() > 0) {
                    Log.e(" distance check db", "" + String.valueOf(distance));
                    String update_distance = "UPDATE " + TABLE_home_beacon + " SET " + KEY_distance + " = " + String.valueOf(distance) + " , " + KEY_ref_count + " = " + "'" + String.valueOf(0) + "'" + " WHERE " + KEY_beaconid + " = " + "'" + beaconid + "'";
                    db.execSQL(update_distance);
                    Log.e(" update -- ", "success");
                } else {
                    values.put(KEY_beaconid, beaconid);
                    values.put(KEY_distance, String.valueOf(distance));
                    values.put(KEY_ref_count, "0");
                    db.insert(TABLE_home_beacon, null, values);
                    Log.e(" insert -- ", "success");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSameDistanceToAll() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String selectQuery = "SELECT  * FROM " + TABLE_home_beacon + " ORDER BY " + KEY_distance;

            Cursor checkcursor = db.rawQuery(selectQuery, null);
            if (checkcursor.getCount() > 0) {
                int i = 0;
                while (checkcursor.moveToNext()) {

                    String tmp_uuid = checkcursor.getString(checkcursor.getColumnIndex(KEY_beaconid));
                    String update_distance = "UPDATE " + TABLE_beacon + " SET " + KEY_distance + " = " + String.valueOf(500 + i) + " WHERE " + KEY_beaconid + " = " + "'" + tmp_uuid + "'";
                    db.execSQL(update_distance);
                    i++;
                }
            }

            Log.e(" update -- ", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_beacon);
            onCreate(db);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_home_beacon);
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void getAllBeaconDetails1(ArrayList<GetBeaconVO> beaconList) {
        ArrayList<GetBeaconVO> allbeacon_list = new ArrayList<GetBeaconVO>();
        // Select All Query
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_beacon + " ORDER BY " + KEY_distance;
            Cursor checkcursor = db.rawQuery(selectQuery, null);
            if (checkcursor.getCount() > 0) {
                while (checkcursor.moveToNext()) {
                    String tmp_uuid = checkcursor.getString(checkcursor.getColumnIndex(KEY_beaconid));
                    if (!tmp_uuid.equals("")) {
                        for (int i = 0; i < beaconList.size(); i++) {
                            if (beaconList.get(i).getUuid().equalsIgnoreCase(tmp_uuid)) {
                                String update_ref = "UPDATE " + TABLE_beacon + " SET " + KEY_distance + " = " + "'" + String.valueOf(beaconList.get(i).getDistance()) + "'" + " WHERE " + KEY_beaconid + " = " + "'" + tmp_uuid + "'";
                                db.execSQL(update_ref);
                            }
                        }
                    }
                }
                checkcursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ******** Get All Venue Details Status ************
    public ArrayList<VenueListIO> getAllVenueDetailsStatus(String home_id) {

        ArrayList<VenueListIO> project_detail_list = new ArrayList<VenueListIO>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String selectQuery = "";
             selectQuery = "SELECT  * FROM " + TABLE_beacon + " WHERE " + KEY_locationID + " = " + "'" + home_id + "'" + " ORDER BY " + KEY_distance;

            Cursor movecursor = db.rawQuery(selectQuery, null);
            if (movecursor.getCount() > 0) {
                while (movecursor.moveToNext()) {
                    VenueListIO beacon_object = new VenueListIO();

                    beacon_object.setBeaconID(movecursor.getString(movecursor.getColumnIndex(KEY_beaconid)));
                    beacon_object.setDistance(movecursor.getString(movecursor.getColumnIndex(KEY_distance)));
                    beacon_object.setVenueTitle(movecursor.getString(movecursor.getColumnIndex(KEY_title)));
                    beacon_object.setImageName(movecursor.getString(movecursor.getColumnIndex(KEY_imageName)));
                    beacon_object.setLocationID(movecursor.getString(movecursor.getColumnIndex(KEY_locationID)));
                    beacon_object.setLocationCount(movecursor.getString(movecursor.getColumnIndex(KEY_locationCount)));
                    beacon_object.setHastag(movecursor.getString(movecursor.getColumnIndex(KEY_hasTag)));
                    beacon_object.setAddress(movecursor.getString(movecursor.getColumnIndex(KEY_address)));
                    beacon_object.setAudioName(movecursor.getString(movecursor.getColumnIndex(KEY_audioName)));
                    project_detail_list.add(beacon_object);
                }
                movecursor.close();
            }
            for (int i = 0; i < project_detail_list.size(); i++) {
                Log.e("--- distance check ---", project_detail_list.get(i).getVenueTitle() + "*  && *" + project_detail_list.get(i).getDistance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return project_detail_list;
    }

    public ArrayList<VenueListIO> getAllRoomsDetailsStatus(String home_id) {

        ArrayList<VenueListIO> project_detail_list = new ArrayList<VenueListIO>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String selectQuery = "";
            selectQuery = "SELECT  * FROM " + TABLE_rooms + " WHERE " + KEY_locationID + " = " + "'" + home_id + "'" + " ORDER BY " + KEY_distance;

            Cursor movecursor = db.rawQuery(selectQuery, null);
            if (movecursor.getCount() > 0) {
                while (movecursor.moveToNext()) {
                    VenueListIO beacon_object = new VenueListIO();

                    beacon_object.setBeaconID(movecursor.getString(movecursor.getColumnIndex(KEY_beaconid)));
                    beacon_object.setDistance(movecursor.getString(movecursor.getColumnIndex(KEY_distance)));
                    beacon_object.setVenueTitle(movecursor.getString(movecursor.getColumnIndex(KEY_title)));
                    beacon_object.setImageName(movecursor.getString(movecursor.getColumnIndex(KEY_imageName)));
                    beacon_object.setLocationID(movecursor.getString(movecursor.getColumnIndex(KEY_locationID)));
                    beacon_object.setLocationCount(movecursor.getString(movecursor.getColumnIndex(KEY_locationCount)));
                    beacon_object.setHastag(movecursor.getString(movecursor.getColumnIndex(KEY_hasTag)));
                    beacon_object.setAddress(movecursor.getString(movecursor.getColumnIndex(KEY_address)));
                    beacon_object.setAudioName(movecursor.getString(movecursor.getColumnIndex(KEY_audioName)));
                    project_detail_list.add(beacon_object);
                }
                movecursor.close();
            }
            for (int i = 0; i < project_detail_list.size(); i++) {
                Log.e("--- distance check ---", project_detail_list.get(i).getVenueTitle() + "*  && *" + project_detail_list.get(i).getDistance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return project_detail_list;
    }


    public void getAllBeaconDetails(ArrayList<String> beaconList) {
        ArrayList<GetBeaconVO> allbeacon_list = new ArrayList<GetBeaconVO>();
        // Select All Query
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_home_beacon;

            Cursor checkcursor = db.rawQuery(selectQuery, null);
            if (checkcursor.getCount() > 0) {
                while (checkcursor.moveToNext()) {
                    String tmp_uuid = checkcursor.getString(checkcursor.getColumnIndex(KEY_beaconid));
                    String ref_value = checkcursor.getString(checkcursor.getColumnIndex(KEY_ref_count));
                    if (beaconList.contains(tmp_uuid)) {

                    } else {

                        if (Integer.parseInt(ref_value) < 2) {
                            int int_count = Integer.parseInt(ref_value) + 1;
                            String update_ref = "UPDATE " + TABLE_home_beacon + " SET " + KEY_ref_count + " = " + "'" + String.valueOf(int_count) + "'" + " WHERE " + KEY_beaconid + " = " + "'" + tmp_uuid + "'";
                            db.execSQL(update_ref);
                            //   Log.e("uptate refcount", "success");
                        } else if (Integer.parseInt(ref_value) >= 2) {

                            int count = AppConstants.tmp_update_count;
                            String update_distance = "UPDATE " + TABLE_beacon + " SET " + KEY_distance + " = " + String.valueOf(9900 - count) + " WHERE " + KEY_beaconid + " = " + "'" + tmp_uuid + "'";
                            db.execSQL(update_distance);

                            String delete_beacon = "DELETE FROM " + TABLE_home_beacon + " WHERE " + KEY_ref_count + " = " + "'" + ref_value + "'";
                            db.execSQL(delete_beacon);

                            AppConstants.tmp_update_count++;
                            //  Log.e("delete row", "success");

                        }

                    }

                }
                checkcursor.close();
            }


            Cursor movecursor = db.rawQuery(selectQuery, null);
            if (movecursor.getCount() > 0) {

                while (movecursor.moveToNext()) {
                    GetBeaconVO beacon_object = new GetBeaconVO();
                    beacon_object.setUuid(movecursor.getString(movecursor.getColumnIndex(KEY_beaconid)));
                    beacon_object.setDistance(Double.parseDouble(movecursor.getString(movecursor.getColumnIndex(KEY_distance))));
                    allbeacon_list.add(beacon_object);
                }
                movecursor.close();
            }
            AppConstants.tmp_home_beacon_list = allbeacon_list;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void truncateTable() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String deleteQuery = "DELETE FROM " + TABLE_beacon;
            db.execSQL(deleteQuery);
            Log.e("delete table", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void truncateHomeBeaconTable() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String deleteQuery = "DELETE FROM " + TABLE_home_beacon;
            db.execSQL(deleteQuery);
            Log.e("delete home table", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
