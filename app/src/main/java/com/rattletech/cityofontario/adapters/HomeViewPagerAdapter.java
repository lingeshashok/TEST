package com.rattletech.cityofontario.adapters;

import android.content.Context;
import android.support.v4.app.RemoteInput;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rattletech.cityofontario.R;
import com.rattletech.cityofontario.model.BannerIO;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by mahendran on 24/10/17.
 */

public class HomeViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private int[] mResources;
    ArrayList<BannerIO> bannerArrayList;


    public HomeViewPagerAdapter(Context mContext, ArrayList<BannerIO> bannerArrayList) {
        this.mContext = mContext;
        this.bannerArrayList = bannerArrayList;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_home_view_pager, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        TextView bannerName = (TextView) itemView.findViewById(R.id.banner_name);
        TextView date = (TextView) itemView.findViewById(R.id.banner_date);
        TextView findMore = (TextView) itemView.findViewById(R.id.find_more);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
