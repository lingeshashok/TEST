package com.rattletech.cityofontario.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rattletech.cityofontario.R;
import com.rattletech.cityofontario.adapters.CityLibListAdapter;
import com.rattletech.cityofontario.adapters.CityParksListAdapter;
import com.rattletech.cityofontario.config.ApiServiceConstants;
import com.rattletech.cityofontario.model.CityListIO;

import java.util.ArrayList;

/**
 * Created by android1 on 5/15/2017.
 */

public class CityFragment extends Fragment {



    ArrayList<CityListIO> cityParkArrayList;
    ArrayList<CityListIO> cityLibArrayList;
    ImageView imgPark,imgLib;
    TextView textPark,textLib;
    Typeface typefaceBold;
    LinearLayout botttomLayout;
    GridView gridView;
    public static int libLayoutHeight=0;
    public static int parkLayoutHeight=0;

    LinearLayout midLayout;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_fragment, container, false);


        gridView = (GridView)view.findViewById(R.id.city_gridlayout);

        cityParkArrayList = new ArrayList<CityListIO>();
        cityLibArrayList = new ArrayList<CityListIO>();

        typefaceBold = ApiServiceConstants.getFontFamily(getActivity(), "bold");

        imgPark = (ImageView) view.findViewById(R.id.img_park);
        imgLib = (ImageView) view.findViewById(R.id.img_lib);
        textPark = (TextView) view.findViewById(R.id.text_park);
        textLib = (TextView) view.findViewById(R.id.text_lib);
        botttomLayout = (LinearLayout) view.findViewById(R.id.bottom_layout);
        midLayout = (LinearLayout) view.findViewById(R.id.mid_layout);



        botttomLayout.setVisibility(View.INVISIBLE);
        ViewTreeObserver observer = midLayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub



                parkLayoutHeight= midLayout.getHeight();
                int b = midLayout.getWidth();



                midLayout.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
            }
        });


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

                botttomLayout.setVisibility(View.GONE);
                CityParksListAdapter adapter = new CityParksListAdapter(getActivity(),getActivity(),cityParkArrayList);
                gridView.setAdapter(adapter);
            }
        }, 100);


        imgPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgPark.setBackgroundResource(R.drawable.parks_selected);
                imgLib.setBackgroundResource(R.drawable.library_normal);
                textLib.setTextColor(ContextCompat.getColor(getActivity(), R.color.app_theme_text_color));
                textPark.setTextColor(ContextCompat.getColor(getActivity(), R.color.red));


                botttomLayout.setVisibility(View.GONE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        CityParksListAdapter adapter = new CityParksListAdapter(getActivity(),getActivity(),cityParkArrayList);
                        gridView.setAdapter(adapter);
                    }
                }, 100);

            }
        });

        imgLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgPark.setBackgroundResource(R.drawable.parks_normal);
                imgLib.setBackgroundResource(R.drawable.library_selected);

                textPark.setTextColor(ContextCompat.getColor(getActivity(), R.color.app_theme_text_color));
                textLib.setTextColor(ContextCompat.getColor(getActivity(), R.color.red));

             /*   CityLibListAdapter adapter = new CityLibListAdapter(getActivity(),getActivity(),cityParkArrayList);
                gridView.setAdapter(adapter);*/

                botttomLayout.setVisibility(View.VISIBLE);

                ViewTreeObserver observer = midLayout.getViewTreeObserver();
                observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        // TODO Auto-generated method stub



                        libLayoutHeight= midLayout.getHeight();
                        int b = midLayout.getWidth();



                        midLayout.getViewTreeObserver().removeGlobalOnLayoutListener(
                                this);
                    }
                });


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms


                        CityLibListAdapter adapter = new CityLibListAdapter(getActivity(),getActivity(),cityParkArrayList);
                        gridView.setAdapter(adapter);
                        gridView.setOnItemClickListener(OnItemclick);
                    }
                }, 100);


            }
        });





        return view;
    }

    private final AdapterView.OnItemClickListener OnItemclick = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



        }
    };
    @Override
    public void onResume() {
        super.onResume();

    }
}
