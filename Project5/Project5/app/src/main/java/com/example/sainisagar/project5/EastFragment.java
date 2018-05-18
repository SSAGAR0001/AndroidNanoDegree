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
public class EastFragment extends Fragment {


    public EastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false);
        ArrayList<location> locations = new ArrayList<location>();
        locations.add(new location(getString(R.string.ecity1), getString(R.string.ename1)));
        locations.add(new location(getString(R.string.ecity2), getString(R.string.ename2)));
        locations.add(new location(getString(R.string.ecity3), getString(R.string.ename3)));
        locations.add(new location(getString(R.string.ecity4), getString(R.string.ename4)));
        locations.add(new location(getString(R.string.ecity5), getString(R.string.ename5)));
        locations.add(new location(getString(R.string.ecity6), getString(R.string.ename6)));
        locations.add(new location(getString(R.string.ecity7), getString(R.string.ename7)));
        locations.add(new location(getString(R.string.ecity8), getString(R.string.ename8)));
        locations.add(new location(getString(R.string.ecity9), getString(R.string.ename9)));
        locations.add(new location(getString(R.string.ecity10), getString(R.string.ename10)));

        locationAdapter adapter = new locationAdapter(getActivity(), locations);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;
    }

}
