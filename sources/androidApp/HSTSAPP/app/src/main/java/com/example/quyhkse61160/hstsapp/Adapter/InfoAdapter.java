package com.example.quyhkse61160.hstsapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.quyhkse61160.hstsapp.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Man Huynh Khuong on 9/14/2015.
 */
public class InfoAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String,String>> data;
    private static LayoutInflater inflater = null;

    public InfoAdapter(Activity activity, ArrayList<HashMap<String,String>> data){
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        if(view==null){
            vi = inflater.inflate(R.layout.home_fragment_tab_4_listiem,null);
        }
        ImageView image = (ImageView) vi.findViewById(R.id.list_image);
        TextView title = (TextView) vi.findViewById(R.id.tab1_title);
        TextView info = (TextView) vi.findViewById(R.id.tab1_info);

        HashMap<String,String> section = new HashMap<String,String>();
        section = data.get(i);

        title.setText(section.get("Title"));
        //image.setImageResource(Integer.parseInt(section.get("Image")));
        info.setText(section.get("Info"));
        return vi;
    }
}
