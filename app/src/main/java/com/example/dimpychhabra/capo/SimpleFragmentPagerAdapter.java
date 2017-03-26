package com.example.dimpychhabra.capo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Dimpy Chhabra on 3/26/2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Main_found_Frag();
        } else
            return new Main_offered_Frag();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
