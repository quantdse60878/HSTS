package com.example.manhuynhkhuong.myapplication2.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.manhuynhkhuong.myapplication2.Fragment.Tab1;
import com.example.manhuynhkhuong.myapplication2.Fragment.Tab2;
import com.example.manhuynhkhuong.myapplication2.Fragment.Tab3;

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
            case 0: return new Tab1();
            case 1: return new Tab2();
            case 2: return new Tab3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
