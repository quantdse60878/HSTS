package com.example.quyhkse61160.hstsapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quyhkse61160.hstsapp.R;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by QUYHKSE61160 on 9/29/2015.
 */
public class MedicineAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;

    public MedicineAdapter(Activity activity, ArrayList<HashMap<String, String>> data) {
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
            vi = inflater.inflate(R.layout.home_fragment_tab_2_item, null);
        }
        TextView txtMedicineName = (TextView) vi.findViewById(R.id.item_medicine_name);
//        TextView txtAdvice = (TextView) vi.findViewById(R.id.item_advice_of_medicine);
        TextView txtNumberOfMedicine = (TextView) vi.findViewById(R.id.item_number_of_medicine);

        HashMap<String, String> item = new HashMap<>();
        item = data.get(position);

        txtMedicineName.setText(item.get("MedicineName"));
//        txtAdvice.setText(item.get("Advice"));
        txtNumberOfMedicine.setText(item.get("NumberOfMedicine"));


        return vi;
    }
}
