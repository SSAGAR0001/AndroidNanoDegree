package com.example.sainisagar.project5;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PunjabFragment extends Fragment {


    public PunjabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        ArrayList<location> locations = new ArrayList<location>();
        locations.add(new location(getString(R.string.pcity1), getString(R.string.pname1)));
        locations.add(new location(getString(R.string.pcity2), getString(R.string.pname2)));
        locations.add(new location(getString(R.string.pcity3), getString(R.string.pname3)));
        locations.add(new location(getString(R.string.pcity4), getString(R.string.pname4)));
        locations.add(new location(getString(R.string.pcity5), getString(R.string.pname5)));
        locations.add(new location(getString(R.string.pcity6), getString(R.string.pname6)));
        locations.add(new location(getString(R.string.pcity7), getString(R.string.pname7)));
        locations.add(new location(getString(R.string.pcity8), getString(R.string.pname8)));
        locations.add(new location(getString(R.string.pcity9), getString(R.string.pname9)));
        locations.add(new location(getString(R.string.pcity10), getString(R.string.pname10)));

        locationAdapter adapter = new locationAdapter(getActivity(), locations);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;
    }

}
