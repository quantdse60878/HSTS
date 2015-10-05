package com.example.quyhkse61160.hstsapp;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.example.quyhkse61160.hstsapp.Adapter.ViewPagesAdapter;
import com.example.quyhkse61160.hstsapp.Common.HSTSUtils;

public class HomeActivity extends ActionBarActivity implements ActionBar.TabListener {

    ActionBar actionBar;
    ViewPagesAdapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment);

        //KhuongMH
        actionBar = getSupportActionBar();
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new ViewPagesAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3ea000")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#4ABC02")));



        ActionBar.Tab atab1 = actionBar.newTab().setText("THỨC ĂN").setTabListener(this);
        ActionBar.Tab atab2 = actionBar.newTab().setText("THUỐC").setTabListener(this);
        ActionBar.Tab atab3 = actionBar.newTab().setText("LUYỆN TẬP").setTabListener(this);
        ActionBar.Tab atab4 = actionBar.newTab().setText("THÔNG BÁO").setTabListener(this);
        actionBar.addTab(atab1);
        actionBar.addTab(atab2);
        actionBar.addTab(atab3);
        actionBar.addTab(atab4);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //KhuongMH
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
