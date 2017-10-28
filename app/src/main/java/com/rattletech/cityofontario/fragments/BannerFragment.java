package com.rattletech.cityofontario.fragments;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.rattletech.cityofontario.DetailViewActivity;
import com.rattletech.cityofontario.MainActivity;
import com.rattletech.cityofontario.R;
import com.rattletech.cityofontario.WebviewActivity;
import com.rattletech.cityofontario.adapters.HomeViewPagerAdapter;
import com.rattletech.cityofontario.config.ApiServiceConstants;
import com.rattletech.cityofontario.model.BannerIO;

import java.util.ArrayList;

/**
 * Created by android1 on 5/12/2017.
 */

public class BannerFragment extends Fragment implements ViewPager.OnPageChangeListener {

    ViewPager viewPager;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private HomeViewPagerAdapter mAdapter;
    private ImageView[] dots;
    Button btnReportIssue;
    Typeface typefaceBold;
    TextView textSiteLink;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.banner_fragment, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.pager_introduction);
        pager_indicator = (LinearLayout) view.findViewById(R.id.viewPagerIndicator);
        btnReportIssue = (Button) view.findViewById(R.id.btn_report_issue);
        textSiteLink = (TextView) view.findViewById(R.id.web_link_text);
        textSiteLink.setPaintFlags(textSiteLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        typefaceBold = ApiServiceConstants.getFontFamily(getActivity(), "bold");
        btnReportIssue.setTypeface(typefaceBold);
        ArrayList<BannerIO> bannerArrayList = new ArrayList<BannerIO>();




        mAdapter = new HomeViewPagerAdapter(getActivity(), bannerArrayList);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
        setUiPageViewController();


        btnReportIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailViewActivity.class);
                startActivity(intent);
            }
        });
        textSiteLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(getActivity(), DetailViewActivity.class);
                startActivity(intent);*/

                Intent intent = new Intent(getActivity(), WebviewActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.non_selected_item_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selected_item_dot));
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.non_selected_item_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selected_item_dot));

        if (position + 1 == dotsCount) {
            /*btnNext.setVisibility(View.GONE);
            btnFinish.setVisibility(View.VISIBLE);*/
        } else {
            /*btnNext.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);*/
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


}
