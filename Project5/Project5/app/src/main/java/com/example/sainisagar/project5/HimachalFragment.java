package com.example.sainisagar.project5;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class HimachalFragment extends Fragment {


    public HimachalFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false);
        ArrayList<location> locations = new ArrayList<location>();
        locations.add(new location(getString(R.string.hpcity1), getString(R.string.hpname1),R.drawable.badri));
        locations.add(new location(getString(R.string.hpcity2), getString(R.string.hpname2), R.drawable.devi2));
        locations.add(new location(getString(R.string.hpcity3), getString(R.string.hpname3), R.drawable.temple1));
        locations.add(new location(getString(R.string.hpcity4), getString(R.string.hpname4), R.drawable.kunja));
        locations.add(new location(getString(R.string.hpcity5), getString(R.string.hpname5), R.drawable.temple2));
        locations.add(new location(getString(R.string.hpcity6), getString(R.string.hpname6), R.drawable.kartik));
        locations.add(new location(getString(R.string.hpcity7), getString(R.string.hpname7), R.drawable.kedarnath));
        locations.add(new location(getString(R.string.hpcity8), getString(R.string.hpname8), R.drawable.temple8));
        locations.add(new location(getString(R.string.hpcity9), getString(R.string.hpname9), R.drawable.temple7));
        locations.add(new location(getString(R.string.hpcity10), getString(R.string.hpname10), R.drawable.neel));

        locationAdapter adapter = new locationAdapter(getActivity(), locations);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;
    }

}
