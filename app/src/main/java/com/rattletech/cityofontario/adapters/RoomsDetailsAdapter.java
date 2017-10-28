package com.rattletech.cityofontario.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rattletech.cityofontario.R;
import com.rattletech.cityofontario.config.AppConstants;
import com.rattletech.cityofontario.model.VenueListIO;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vikram on 4/12/2016.
 */
public class RoomsDetailsAdapter extends ArrayAdapter<VenueListIO> {

    Context mContext;
    private static LayoutInflater inflater;
    ViewHolder holder = null;
    Activity activity;
    int width, height;

    View layout_beacon;
    int selectedposition = 0;
    int beaconclickedposition;
    String beacon = "";
    Button save;

    ListView beaconlistview;
    PopupWindow popupWindowList;

    String fn_room_title;
    String fn_room_count;
    Typeface tf_room_title;
    Typeface tf_room_count;

    int audioselectedposition;
    int count=0;
    String login_id;
    public static final String KEY_REFRESH_LIST="hide_list";
    public static final String KEY_OPEN_DETAIL_FRAGMENT = "fragment";

    public RoomsDetailsAdapter(Context context, ArrayList<VenueListIO> project_detail_list) {
        super(context, 0, project_detail_list);
        this.mContext = context;
        this.activity = activity;
        inflater = LayoutInflater.from(context);

    }

    /* @Override
     public int getCount() {
         return project_details_arrayList.size();
     }*/
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_rooms_list, null);


            holder.project_imageview = (ImageView) convertView.findViewById(R.id.project_cover_image);
            holder.project_title =(TextView)convertView.findViewById(R.id.room_title_text);
            holder.project_title.setTypeface(tf_room_title);
            holder.project_title.setShadowLayer(1,0,0,Color.GREEN);

            ViewTreeObserver vto = holder.project_imageview.getViewTreeObserver();
            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    holder.project_imageview.getViewTreeObserver().removeOnPreDrawListener(this);
                    width = holder.project_imageview.getMeasuredWidth();
                    height = holder.project_imageview.getMeasuredHeight();
                    AppConstants.tmp_imageview_height = height;
                    return true;
                }
            });




            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        holder.project_title.setText("");

            Picasso.with(mContext)
                    .load("")
                    .resize(512, 512)
                    .centerCrop()
                    .placeholder(R.drawable.sample_image)
                    .error(R.drawable.sample_image)
                    .into(holder.project_imageview);

        return convertView;
    }

    public class ViewHolder {
        ImageView project_imageview;
        TextView project_title;

    }
}
