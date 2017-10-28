package com.rattletech.cityofontario.fragments;

/**
 * Created by android1 on 8/31/2017.
 */

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rattletech.cityofontario.R;
import com.rattletech.cityofontario.adapters.ContactListViewAdapter;
import com.rattletech.cityofontario.config.ApiServiceConstants;
import com.rattletech.cityofontario.model.ContactIO;

import java.util.ArrayList;


public class ContactFragment extends Fragment {


    ListView contactListView;
    ContactListViewAdapter adapter;
    ArrayList<ContactIO> contactArrayList;

    Button btnEmailUS;
    Typeface typefaceBold;
    TextView textSiteLink;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_fragment, container, false);

        contactArrayList = new ArrayList<ContactIO>();
        contactListView = (ListView) view.findViewById(R.id.contact_listview);
        btnEmailUS = (Button) view.findViewById(R.id.btn_email_us);
        typefaceBold = ApiServiceConstants.getFontFamily(getActivity(), "bold");
        btnEmailUS.setTypeface(typefaceBold);

        textSiteLink = (TextView) view.findViewById(R.id.web_link_text);
        textSiteLink.setPaintFlags(textSiteLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        ContactListViewAdapter adapter = new ContactListViewAdapter(getActivity(), contactArrayList);
        contactListView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();



    }

}
