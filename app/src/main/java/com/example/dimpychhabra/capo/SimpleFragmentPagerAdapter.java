package com.example.dimpychhabra.capo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
/*
*Project : CAPO, fully created by
* Dimpy Chhabra, IGDTUW, BTech, IT
* Second year (as of 2017)
* Expected Class of 2019
* Please do not circulate as your own
* Criticism is appreciated to work on memory leaks and bugs
* Contact Info : Find me on Linked in : linkedin.com/in/dimpy-chhabra
*
*/
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
