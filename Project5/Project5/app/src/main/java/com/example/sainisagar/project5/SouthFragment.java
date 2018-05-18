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
public class SouthFragment extends Fragment {


    public SouthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container,false);
        ArrayList<location> locations = new ArrayList<location>();
        locations.add(new location(getString(R.string.scity1), getString(R.string.sname1)));
        locations.add(new location(getString(R.string.scity2), getString(R.string.sname2)));
        locations.add(new location(getString(R.string.scity3), getString(R.string.sname3)));
        locations.add(new location(getString(R.string.scity4), getString(R.string.sname4)));
        locations.add(new location(getString(R.string.scity5), getString(R.string.sname5)));
        locations.add(new location(getString(R.string.scity6), getString(R.string.sname6)));
        locations.add(new location(getString(R.string.scity7), getString(R.string.sname7)));
        locations.add(new location(getString(R.string.scity8), getString(R.string.sname8)));
        locations.add(new location(getString(R.string.scity9), getString(R.string.sname9)));
        locations.add(new location(getString(R.string.scity10), getString(R.string.sname10)));

        locationAdapter adapter = new locationAdapter(getActivity(), locations);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;
    }

}
