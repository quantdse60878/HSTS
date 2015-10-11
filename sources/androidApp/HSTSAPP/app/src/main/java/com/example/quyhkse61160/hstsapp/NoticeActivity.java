package com.example.quyhkse61160.hstsapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.quyhkse61160.hstsapp.Adapter.NoticeAdapter;
import com.example.quyhkse61160.hstsapp.Adapter.PracticeAdapter;
import com.example.quyhkse61160.hstsapp.Classes.ToDoItem;
import com.example.quyhkse61160.hstsapp.Classes.ToDoTime;
import com.example.quyhkse61160.hstsapp.Classes.Treatment;
import com.example.quyhkse61160.hstsapp.Common.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoticeActivity extends ActionBarActivity {
    NoticeAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3ea000")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#4ABC02")));
        Bundle bundle = getIntent().getExtras();
        String field = bundle.getString("field");
        ArrayList<HashMap<String,String>> sections = new ArrayList<>();
        List<Treatment> treatments = Constant.TREATMENTS;
        if(field.equals("food")){
            for(Treatment treatment : treatments){
                for(ToDoTime time : treatment.getListFoodTreatment()){
                        HashMap<String,String> d = new HashMap<>();
                        d.put("Name",time.getName());
                        d.put("Number",time.getNumberOfTime() + "");
                        d.put("Quantity",time.getQuantitative());
                        sections.add(d);
                }
            }
        }
        if(field.equals("medicine")){
            for(Treatment treatment : treatments){
                for(ToDoTime time : treatment.getListMedicineTreatment()){
                    HashMap<String,String> d = new HashMap<>();
                    d.put("Name",time.getName());
                    d.put("Number",time.getNumberOfTime() + "");
                    d.put("Quantity",time.getQuantitative());
                    sections.add(d);
                }
            }
        }
        if(field.equals("practice")){
            for(Treatment treatment : treatments){
                for(ToDoTime time : treatment.getListPracticeTreatment()){
                    HashMap<String,String> d = new HashMap<>();
                    d.put("Name",time.getName());
                    d.put("Number",time.getNumberOfTime() + "");
                    d.put("Quantity",time.getQuantitative());
                    sections.add(d);
                }
            }
        }
        listView = (ListView) findViewById(R.id.notice_listview);
        adapter = new NoticeAdapter(this, sections);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notice, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
