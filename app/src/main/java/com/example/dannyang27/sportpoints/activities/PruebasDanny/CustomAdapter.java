package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

/**
 * Created by Dannyang27 on 13/11/16.
 */
public class CustomAdapter extends FragmentStatePagerAdapter {
    private String [] fragments = {"EQUIPOS", "EVENTOS"};
    int numTabs;
    public CustomAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new EquipoFragmento();
            case 1:
                return new EventoFragmento();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}
