package com.example.quyhkse61160.hstsapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.quyhkse61160.hstsapp.Adapter.NavDrawerListAdapter;
import com.example.quyhkse61160.hstsapp.Classes.NavDrawerItem;
import com.example.quyhkse61160.hstsapp.Fragment.Tab1;
import com.example.quyhkse61160.hstsapp.Fragment.Tab2;
import com.example.quyhkse61160.hstsapp.Fragment.Tab3;
import com.example.quyhkse61160.hstsapp.Fragment.Tab4;

import java.util.ArrayList;

public class HomeActivity2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
