package com.example.sainisagar.project5;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Saini Sagar on 2017-12-20.
 */

public class CategoryAdapter extends FragmentPagerAdapter {
    private Context mcontext;

    public CategoryAdapter(Context context, FragmentManager fm)
    {
        super(fm);
        mcontext = context;
    }
    @Override
    public Fragment getItem(int position) {
        if(position == 0) return new HimachalFragment();
        else if(position == 1) return new PunjabFragment();
        else if(position == 2) return new NorthFragment();
        else if(position == 3) return new EastFragment();
        else if(position == 4) return new WestFragment();
        else return new SouthFragment();
    }
    @Override
    public int getCount() {
        return 6;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) return mcontext.getString(R.string.hp);
        else if(position == 1) return mcontext.getString(R.string.pb);
        else if(position == 2) return mcontext.getString(R.string.north);
        else if(position == 3) return mcontext.getString(R.string.east);
        else if(position == 4) return mcontext.getString(R.string.west);
        else return mcontext.getString(R.string.north);
    }
}
