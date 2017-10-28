package com.rattletech.cityofontario.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.MyOptionsPickerView;
import com.rattletech.cityofontario.R;
import com.rattletech.cityofontario.adapters.CalendarListAdapter;
import com.rattletech.cityofontario.model.CalendarIO;

import java.util.ArrayList;


public class CalendarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView idMonthDisplay;
    ArrayList<CalendarIO> calendarArrayList;
    ListView calendarListview;
    MyOptionsPickerView singlePicker;
    EditText categoryFilter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CalendarFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarArrayList = new ArrayList<CalendarIO>();

        idMonthDisplay= (TextView) view.findViewById(R.id.idMonthDisplay);
        calendarListview = (ListView) view.findViewById(R.id.calendar_listview);
        categoryFilter = (EditText) view.findViewById(R.id.edittext_filter);
        categoryFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singlePicker.show();
            }
        });

        CalendarListAdapter adapter = new CalendarListAdapter(getActivity(),calendarArrayList);
        calendarListview.setAdapter(adapter);




        singlePicker = new MyOptionsPickerView(getActivity());
        final ArrayList<String> items = new ArrayList<String>();

        items.add("Clear All");
        items.add("City Council Meetings");
        items.add("Colony High Branch Library");
        items.add("Armstrong Community Center");
        items.add("Item 4");
        items.add("Item 5");
        items.add("Item 6");



        singlePicker.setPicker(items);
        singlePicker.setCyclic(false);
        singlePicker.setSelectOptions(0);


        singlePicker.setOnoptionsSelectListener(new MyOptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
               // txtGroupIconSettings.setText(items.get(options1));


            }

        });


       /* FragmentManager fm = getFragmentManager();
        MyDialogFragment dialogFragment = new MyDialogFragment ();
        dialogFragment.show(fm, "Sample Fragment");*/

       openDialog();


        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void openDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_calendar_details);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = (int) (displaymetrics.widthPixels * 0.97);
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        dialog.getWindow().setLayout(width, height);
        dialog.show();
    }
}
