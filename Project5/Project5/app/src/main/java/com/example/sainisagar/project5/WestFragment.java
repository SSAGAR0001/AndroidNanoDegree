package com.example.sainisagar.project5;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WestFragment extends Fragment {


    public WestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false);
        ArrayList<location> locations = new ArrayList<location>();
        locations.add(new location(getString(R.string.wcity1), getString(R.string.wname1)));
        locations.add(new location(getString(R.string.wcity2), getString(R.string.wname2)));
        locations.add(new location(getString(R.string.wcity3), getString(R.string.wname3)));
        locations.add(new location(getString(R.string.wcity4), getString(R.string.wname4)));
        locations.add(new location(getString(R.string.wcity5), getString(R.string.wname5)));
        locations.add(new location(getString(R.string.wcity6), getString(R.string.wname6)));
        locations.add(new location(getString(R.string.wcity7), getString(R.string.wname7)));
        locations.add(new location(getString(R.string.wcity8), getString(R.string.wname8)));
        locations.add(new location(getString(R.string.wcity9), getString(R.string.wname9)));
        locations.add(new location(getString(R.string.wcity10), getString(R.string.wname10)));
        locationAdapter adapter = new locationAdapter(getActivity(), locations);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;
    }

}
