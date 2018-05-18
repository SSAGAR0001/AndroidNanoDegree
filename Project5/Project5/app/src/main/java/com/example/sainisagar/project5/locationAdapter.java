package com.example.sainisagar.project5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Saini Sagar on 2017-12-17.
 */

public class locationAdapter extends ArrayAdapter<location> {
    public locationAdapter(Context context, ArrayList<location> locations) {
        super(context, 0, locations);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }
        location currentlocation = getItem(position);

        TextView first = (TextView) listItemView.findViewById(R.id.name_view);
        first.setText(currentlocation.get_name());

        TextView other = (TextView) listItemView.findViewById(R.id.other_view);
        other.setText(currentlocation.getM_other());

        ImageView image = (ImageView) listItemView.findViewById(R.id.image_view);
        if(currentlocation.hasImage()) {
            image.setImageResource(currentlocation.getImageId());
            image.setVisibility(View.VISIBLE);
        }
        else {
            image.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
