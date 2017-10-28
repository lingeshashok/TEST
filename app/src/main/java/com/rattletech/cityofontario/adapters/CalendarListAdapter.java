package com.rattletech.cityofontario.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rattletech.cityofontario.R;
import com.rattletech.cityofontario.model.CalendarIO;
import com.rattletech.cityofontario.model.NewsIO;

import java.util.ArrayList;

/**
 * Created by mahendran on 24/10/17.
 */

public class CalendarListAdapter extends BaseAdapter {
    private LayoutInflater mInflater = null;
    private Context context = null;
    private ArrayList<CalendarIO> calendarArrayList;

    public CalendarListAdapter(Context context, ArrayList<CalendarIO> calendarArrayList) {
        mInflater = LayoutInflater.from(context);
        this.calendarArrayList = calendarArrayList;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item_press_release_list, null);
            holder = new ViewHolder();




            /*holder.contactName = (TextView) convertView.findViewById(R.id.contact_name);
            holder.contactNumber = (TextView) convertView.findViewById(R.id.contact_number);
            holder.contactName.setPaintFlags(holder.contactName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.contactNumber.setPaintFlags(holder.contactNumber.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);*/

//            holder.DescText = (TextView) convertView.findViewById(R.id.description_text);
//            holder.DescText.setTextColor(Color.BLACK);
//            holder.heritageImageThumb = (ImageView) convertView.findViewById(R.id.thumbnail);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //holder.Titletext.setText(pairedArrayList.get(po s));
        //holder.DescText.setText(MediaInfo.get(pos).getMediaFileType());


        return convertView;
    }

    class ViewHolder {
        TextView contactName,contactNumber;
        FrameLayout frameLayout;
    }
}

