package com.example.dannyang27.sportpoints.activities.PruebasDanny;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.example.dannyang27.sportpoints.activities.Promocion.PromocionFragmento;

/**
 * Created by Dannyang27 on 13/11/16.
 */
public class CustomAdapter extends FragmentStatePagerAdapter {
    private String [] fragments = {"EQUIPOS", "EVENTOS", "PROMOS"};
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
            case 2:
                return new PromocionFragmento();

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
