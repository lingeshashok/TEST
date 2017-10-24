package com.rattletech.cityofontario.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.rattletech.cityofontario.R;

/**
 * Created by android1 on 5/12/2017.
 */

public class PairedDevicesFragment extends Fragment {


    SwipeRefreshLayout swipeLayout;
    LayoutInflater refInflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.paired_device_fragment, container, false);
        refInflater = inflater;

        return view;
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
