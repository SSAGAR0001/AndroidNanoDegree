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
public class NorthFragment extends Fragment {


    public NorthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        ArrayList<location> locations = new ArrayList<location>();
        locations.add(new location(getString(R.string.ncity1), getString(R.string.nname1)));
        locations.add(new location(getString(R.string.ncity2), getString(R.string.nname2)));
        locations.add(new location(getString(R.string.ncity3), getString(R.string.nname3)));
        locations.add(new location(getString(R.string.ncity4), getString(R.string.nname4)));
        locations.add(new location(getString(R.string.ncity5), getString(R.string.nname5)));
        locations.add(new location(getString(R.string.ncity6), getString(R.string.nname6)));
        locations.add(new location(getString(R.string.ncity7), getString(R.string.nname7)));
        locations.add(new location(getString(R.string.ncity8), getString(R.string.nname8)));
        locations.add(new location(getString(R.string.ncity9), getString(R.string.nname9)));
        locations.add(new location(getString(R.string.ncity10), getString(R.string.nname10)));

        locationAdapter adapter = new locationAdapter(getActivity(), locations);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;
    }

}
