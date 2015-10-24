package com.example.quyhkse61160.hstsapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quyhkse61160.hstsapp.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by QUYHKSE61160 on 9/30/2015.
 */
public class PracticeAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;

    public PracticeAdapter(Activity activity, ArrayList<HashMap<String, String>> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(vi == null) {
            vi = inflater.inflate(R.layout.home_fragment_tab_3_item, null);
        }
        TextView txtFoodName = (TextView) vi.findViewById(R.id.item_practice_name);
        TextView txtQuantitativeOfMedicine = (TextView) vi.findViewById(R.id.item_time_practice);
//        TextView txtAdvice = (TextView) vi.findViewById(R.id.item_advice_of_practice);


        HashMap<String, String> item = new HashMap<>();
        item = data.get(position);

        txtFoodName.setText(item.get("PracticeName"));
//        txtAdvice.setText(item.get("Advice"));
        txtQuantitativeOfMedicine.setText(item.get("PracticeTime"));


        return vi;
    }
}
