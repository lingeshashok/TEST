package com.rattletech.cityofontario.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.provider.Settings;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rattletech.cityofontario.R;
import com.rattletech.cityofontario.model.GetBeaconVO;
import com.rattletech.cityofontario.model.VenueListIO;
import com.rattletech.cityofontario.utilities.AppPreferences;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeVenuePagerAdapter extends PagerAdapter {

    Context context;
    ArrayList<VenueListIO> project_list;
    LayoutInflater inflater;



    public HomeVenuePagerAdapter(Context context, ArrayList<VenueListIO> project_list) {
        this.context = context;
        this.project_list = project_list;
    }

    @Override
    public int getCount() {
        return project_list.size();
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        final int pos = position;

        TextView titleText,titleAddress,titleDesc,titleMore;
        ImageView imgVenue;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_home_venue_list, container,
                false);

        titleText = (TextView) itemView.findViewById(R.id.title_text);
        titleAddress = (TextView) itemView.findViewById(R.id.web_link_text_address);
        titleDesc = (TextView) itemView.findViewById(R.id.textDesc);
        titleMore = (TextView) itemView.findViewById(R.id.text_more);

        imgVenue = (ImageView) itemView.findViewById(R.id.imageView_venue);



        titleText.setText(project_list.get(pos).getVenueTitle());
        titleAddress.setText(project_list.get(pos).getAddress());
        titleDesc.setText(project_list.get(pos).getProjectDescription());

        titleMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        Picasso.with(context)
                .load(project_list.get(pos).getImageName())
                .placeholder(R.drawable.sample_image)
                .error(R.drawable.sample_image)
                .into(imgVenue);


        itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        container.addView(itemView);

        return itemView;
    }

    public void update_count(ArrayList<VenueListIO> articleList) {
        project_list = articleList;
        notifyDataSetChanged();
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
