package com.example.revuk.lunch;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Created by Revuk on 29-Jan-16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;


    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Tabfragment1 tab1 = null;

        switch (position) {

            case 0:
                tab1 = new Tabfragment1();
                return tab1;
            case 1:
                TabFragment2 tab2 = new TabFragment2();
                return tab2;
            case 2:
                TabFragment3 tab3 = new TabFragment3();
                
                                return tab3;

            default:
                return tab1;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}
