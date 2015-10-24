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
 * Created by Man Huynh Khuong on 10/9/2015.
 */
public class NoticeAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;

    public NoticeAdapter(Activity activity, ArrayList<HashMap<String, String>> data) {
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
            vi = inflater.inflate(R.layout.activity_notice_item, null);
        }
        TextView txtDate = (TextView) vi.findViewById(R.id.notice_item_hour);
//        TextView txtName = (TextView) vi.findViewById(R.id.notice_item_name);
        TextView txtQuantity = (TextView) vi.findViewById(R.id.notice_item_quantity);

        HashMap<String, String> item = new HashMap<>();
        item = data.get(position);

        txtDate.setText(item.get("Name"));
//        txtName.setText(item.get("Number"));
        txtQuantity.setText(item.get("Quantity"));

        return vi;
    }
}
