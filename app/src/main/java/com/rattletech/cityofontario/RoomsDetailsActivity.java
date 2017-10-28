package com.rattletech.cityofontario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.rattletech.cityofontario.Helper.DataBaseHandler;
import com.rattletech.cityofontario.adapters.HomeVenuePagerAdapter;
import com.rattletech.cityofontario.adapters.RoomsDetailsAdapter;
import com.rattletech.cityofontario.interfaces.CallbackServiceInterface;
import com.rattletech.cityofontario.model.VenueListIO;
import com.rattletech.cityofontario.serviceFunction.ServiceFunctions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RoomsDetailsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<VenueListIO> roomsArrayList;
    DataBaseHandler dataBaseHandler;
    RoomsDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_details);

       String url = "http://citybeam.rattletech.com/api/services/RoomsList?CityId=4&LoginId=2&PageNo=1";

        roomsArrayList = new ArrayList<VenueListIO>();
        listView = (ListView) findViewById(R.id.rooms_list);

        dataBaseHandler = new DataBaseHandler(RoomsDetailsActivity.this);

        ServiceFunctions serviceFunctions = new ServiceFunctions(RoomsDetailsActivity.this,roomsServiceRequest);
        serviceFunctions.serviceCallGET(url);
    }

    CallbackServiceInterface roomsServiceRequest = new CallbackServiceInterface() {
        @Override
        public void callbackObjectCall(JSONObject object) {

            try {
                String strResult = object.getString("Msg");
                if(strResult.equals("Success")){

                    roomsArrayList = new ArrayList<VenueListIO>();

                    JSONArray jsonArray = object.getJSONArray("Result");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        VenueListIO venueListIO = new VenueListIO();
                        venueListIO.setBeaconID(jsonObject.getString("BeaconId"));
                        venueListIO.setDistance(jsonObject.getString("Distance"));
                        venueListIO.setVenueTitle(jsonObject.getString("ObjectName"));
                        venueListIO.setImageName(jsonObject.getString("CoverImage"));
                        venueListIO.setLocationID(jsonObject.getString("ProjectId"));
                        venueListIO.setAudioName(jsonObject.getString("RoomAudio"));
                        venueListIO.setProjectDescription(jsonObject.getString("Description"));
                        roomsArrayList.add(venueListIO);
                    }

                    dataBaseHandler.insertVenueData(roomsArrayList);

                    adapter = new RoomsDetailsAdapter(RoomsDetailsActivity.this, roomsArrayList);
                    listView.setAdapter(adapter);



                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };
}
