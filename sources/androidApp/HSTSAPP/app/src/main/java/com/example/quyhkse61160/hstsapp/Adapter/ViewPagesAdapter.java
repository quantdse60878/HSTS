package com.example.quyhkse61160.hstsapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.quyhkse61160.hstsapp.Fragment.Tab4;
import com.example.quyhkse61160.hstsapp.Fragment.Tab1;
import com.example.quyhkse61160.hstsapp.Fragment.Tab3;
import com.example.quyhkse61160.hstsapp.Fragment.Tab2;

/**
 * Created by Man Huynh Khuong on 9/8/2015.
 */
public class ViewPagesAdapter extends FragmentPagerAdapter {

    public ViewPagesAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
//            case 0: return new Tab1();
//            case 1: return new Tab2();
//            case 2: return new Tab3();
//            case 3: return new Tab4();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
