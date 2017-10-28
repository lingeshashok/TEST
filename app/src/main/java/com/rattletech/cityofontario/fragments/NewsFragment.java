package com.rattletech.cityofontario.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.rattletech.cityofontario.R;
import com.rattletech.cityofontario.adapters.ContactListViewAdapter;
import com.rattletech.cityofontario.adapters.NewsFeedAdapter;
import com.rattletech.cityofontario.adapters.PressReleaseAdapter;
import com.rattletech.cityofontario.config.ApiServiceConstants;
import com.rattletech.cityofontario.model.NewsIO;

import java.util.ArrayList;

/**
 * Created by android1 on 5/15/2017.
 */

public class NewsFragment extends Fragment {


    ListView newsListView;
    ArrayList<NewsIO> newsFeedArrayList;
    ArrayList<NewsIO> pressReleaseArrayList;
    ImageView imgNewsFeed,imgPressRelease;
    TextView textNewsFeed,textPressRelease;
    Typeface typefaceBold;
    EditText edit_department,edit_category;
    LinearLayout spinnerLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);

        newsListView = (ListView) view.findViewById(R.id.news_listview);

        newsFeedArrayList = new ArrayList<NewsIO>();
        pressReleaseArrayList = new ArrayList<NewsIO>();
        typefaceBold = ApiServiceConstants.getFontFamily(getActivity(), "bold");

        imgNewsFeed = (ImageView) view.findViewById(R.id.img_news_feed);
        imgPressRelease = (ImageView) view.findViewById(R.id.img_press_release);
        textNewsFeed = (TextView) view.findViewById(R.id.text_news_feed);
        textPressRelease = (TextView) view.findViewById(R.id.text_press_release);
        spinnerLayout = (LinearLayout) view.findViewById(R.id.spinner_layout);

        edit_department= (EditText) view.findViewById(R.id.editText1);
        edit_category= (EditText) view.findViewById(R.id.editText);

        edit_category.setTypeface(typefaceBold);
        edit_department.setTypeface(typefaceBold);



        NewsFeedAdapter adapter = new NewsFeedAdapter(getActivity(), newsFeedArrayList);
        newsListView.setAdapter(adapter);
        spinnerLayout.setVisibility(View.GONE);
        imgNewsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgNewsFeed.setBackgroundResource(R.drawable.sub_news_selected);
                imgPressRelease.setBackgroundResource(R.drawable.press_normal);
                textPressRelease.setTextColor(ContextCompat.getColor(getActivity(), R.color.app_theme_text_color));
                textNewsFeed.setTextColor(ContextCompat.getColor(getActivity(), R.color.red));

                NewsFeedAdapter adapter = new NewsFeedAdapter(getActivity(), newsFeedArrayList);
                newsListView.setAdapter(adapter);
                spinnerLayout.setVisibility(View.GONE);
            }
        });

        imgPressRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgNewsFeed.setBackgroundResource(R.drawable.sub_news_normal);
                imgPressRelease.setBackgroundResource(R.drawable.press_selected);

                textNewsFeed.setTextColor(ContextCompat.getColor(getActivity(), R.color.app_theme_text_color));
                textPressRelease.setTextColor(ContextCompat.getColor(getActivity(), R.color.red));

                PressReleaseAdapter adapter = new PressReleaseAdapter(getActivity(), pressReleaseArrayList);
                newsListView.setAdapter(adapter);
                spinnerLayout.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

    }
}
