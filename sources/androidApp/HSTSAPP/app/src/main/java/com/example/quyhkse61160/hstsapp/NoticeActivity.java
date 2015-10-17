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
import android.widget.TextView;

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
    TextView name, number, quantitative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        name = (TextView) findViewById(R.id.notice_title_name);
        number = (TextView) findViewById(R.id.notice_title_number);
        quantitative = (TextView) findViewById(R.id.notice_title_quantitative);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3ea000")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#4ABC02")));
        Bundle bundle = getIntent().getExtras();
        String field = bundle.getString("field");
        ArrayList<HashMap<String, String>> sections = new ArrayList<>();
        List<Treatment> treatments = Constant.TREATMENTS;
        if (field.equals("food")) {
            name.setText("Tên Món Ăn");
            number.setText("Số Bữa Ăn");
            quantitative.setText("Số Lượng");
            for (Treatment treatment : treatments) {
                for (ToDoTime time : treatment.getListFoodTreatment()) {
                    if (time.getNumberOfTime().contains(HomeActivity.timeAlert)) {
                        HashMap<String, String> d = new HashMap<>();
                        d.put("Name", time.getName());
                        d.put("Number", time.getNumberOfTime().size() + "");
                        d.put("Quantity", time.getQuantitative());
                        sections.add(d);
                    }
                }
            }
        }
        if (field.equals("medicine")) {
            name.setText("Tên Thuốc");
            number.setText("Số Lần Uống");
            quantitative.setText("Số Lượng/Lần");
            for (Treatment treatment : treatments) {
                for (ToDoTime time : treatment.getListMedicineTreatment()) {
                    if (time.getNumberOfTime().contains(HomeActivity.timeAlert)) {
                        HashMap<String, String> d = new HashMap<>();
                        d.put("Name", time.getName());
                        d.put("Number", time.getNumberOfTime().size() + "");
                        d.put("Quantity", time.getQuantitative());
                        sections.add(d);
                    }
                }
            }
        }
        if (field.equals("practice")) {
            name.setText("Tên Bài Tập");
            number.setText("Số Lần Tập");
            quantitative.setText("Thời Gian");
            for (Treatment treatment : treatments) {
                for (ToDoTime time : treatment.getListPracticeTreatment()) {
                    if (time.getNumberOfTime().contains(HomeActivity.timeAlert)) {
                        HashMap<String, String> d = new HashMap<>();
                        d.put("Name", time.getName());
                        d.put("Number", time.getNumberOfTime().size() + "");
                        d.put("Quantity", time.getQuantitative() + " phút");
                        sections.add(d);
                    }
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
