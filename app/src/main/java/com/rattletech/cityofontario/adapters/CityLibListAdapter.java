package com.rattletech.cityofontario.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rattletech.cityofontario.R;
import com.rattletech.cityofontario.fragments.CityFragment;
import com.rattletech.cityofontario.model.CityListIO;

import java.util.ArrayList;


public class CityLibListAdapter extends BaseAdapter {

    private LayoutInflater mInflater= null;
    private ArrayList<CityListIO> cityArrayList=null;
    Context context;
    Activity activity;
    public CityLibListAdapter(Context context, Activity activity , ArrayList<CityListIO> cityArrayList)
    {
        mInflater= LayoutInflater.from(context);
        this.context = context;
        this.activity = activity;
        this.cityArrayList=cityArrayList;
    }


    @Override
    public View getView(int pos, View convertView, ViewGroup arg2) {
        ViewHolder holder=null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_city_lib_list, null);
            holder = new ViewHolder();

            holder.frameLayout = (RelativeLayout)convertView.findViewById(R.id.frame_layout);

            holder.cat_titleText = (TextView)convertView.findViewById(R.id.cat_title_textview);
            holder.cat_imageView = (ImageView)convertView.findViewById(R.id.cat_imageview);


            RelativeLayout.LayoutParams rel_btn = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, CityFragment.libLayoutHeight-10);
            rel_btn.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            holder.frameLayout.setLayoutParams(rel_btn);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.cat_titleText.setText("Title Text");
        String imagePath ="";
        if(!imagePath.equals("")){
            imagePath = "https://" + imagePath.replace(" ", "%20").trim();
        }else {
            imagePath = "";
        }

        holder.cat_imageView.setImageResource(R.drawable.sample_image);

        /*try {
            Picasso.with(holder.cat_imageView.getContext()).load(imagePath)
                    .placeholder(R.drawable.sample_image)
                    .error(R.drawable.sample_image)
                    .resize(dp2px(220), 0)
                    .into(holder.cat_imageView);
        }catch (Exception e){
            e.printStackTrace();

        }*/

        return convertView;
    }

    class ViewHolder {

        TextView cat_titleText;
        ImageView cat_imageView;
        RelativeLayout frameLayout;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        //return categoryArrayList.size();
        return 2;
    }


    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    public int dp2px(int dp) {
        WindowManager wm = (WindowManager) activity.getBaseContext()
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        display.getMetrics(displaymetrics);
        return (int) (dp * displaymetrics.density + 0.5f);
    }
}
