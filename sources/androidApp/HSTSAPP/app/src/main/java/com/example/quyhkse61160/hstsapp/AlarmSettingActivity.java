package com.example.quyhkse61160.hstsapp;

import android.app.Dialog;
import android.content.ContentValues;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quyhkse61160.hstsapp.Common.Constant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AlarmSettingActivity extends ActionBarActivity {

    EditText time;
    TextView ringPath, folder;
    Button changeRing, accept, openDialog, up;

    String temp = "";
    static final int CUSTOM_DIALOG = 0;
    ListView dialog_ListView;

    File root, curFolder;

    private List<String> fileList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_setting);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3ea000")));

        time = (EditText) findViewById(R.id.et_time);
        time.setText(Constant.ALARM_TIME + "");
        ringPath = (TextView) findViewById(R.id.tv_ringstonePath);
        changeRing = (Button) findViewById(R.id.btnChangeRingstone);
        accept = (Button) findViewById(R.id.btn_alarm_accept);

        Uri defaultRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
        Ringtone ringtone = RingtoneManager.getRingtone(AlarmSettingActivity.this, defaultRintoneUri);
        ringPath.setText(ringtone.getTitle(this));

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time.getText().toString().isEmpty()) {
                    Toast.makeText(AlarmSettingActivity.this, "Time field must not empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (Integer.parseInt(time.getText().toString()) <= 0) {
                    Toast.makeText(AlarmSettingActivity.this, "Value must over 1.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!temp.isEmpty()) {
                    String[] temp1 = temp.split("\\.");
                    if(!temp1[temp1.length-1].equalsIgnoreCase("mp3")){
                        Toast.makeText(AlarmSettingActivity.this,"Please choose MP3 file.",Toast.LENGTH_LONG).show();
                        return;
                    }
                    changeRingstone(temp);
                }
                Constant.ALARM_TIME = Integer.parseInt(time.getText().toString());
                Toast.makeText(AlarmSettingActivity.this,"Cập Nhật Thành Công.",Toast.LENGTH_LONG).show();
            }
        });

        changeRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(CUSTOM_DIALOG);
            }
        });
        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        curFolder = root;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id){
            case CUSTOM_DIALOG:
                dialog = new Dialog(AlarmSettingActivity.this);
                dialog.setContentView(R.layout.activity_alarm_setting_alarmdialog);
                dialog.setTitle("HSTS Application");
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                folder = (TextView) dialog.findViewById(R.id.folder);
                up = (Button) dialog.findViewById(R.id.btnup);
                up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ListDir(curFolder.getParentFile());
                    }
                });
                dialog_ListView = (ListView) dialog.findViewById(R.id.dialogList);
                dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        File selected = new File(fileList.get(position));
                        if (selected.isDirectory()) {
                            ListDir(selected);
                        } else {
                            ringPath.setText(selected.getPath());
                            temp = selected.getPath();
                            dismissDialog(CUSTOM_DIALOG);
                        }
                    }
                });
                break;
        }
        return dialog;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id){
            case CUSTOM_DIALOG:
                ListDir(curFolder);
                break;
        }
    }

    void ListDir(File f){
        if(f.equals(root)){
            up.setEnabled(false);
        } else {
            up.setEnabled(true);
        }

        curFolder = f;
        folder.setText(f.getPath());
        File[] files = f.listFiles();
        fileList.clear();

        for(File file : files){
            fileList.add(file.getPath());
        }
        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,fileList);
        dialog_ListView.setAdapter(directoryList);
    }

    public void changeRingstone(String filepath){
        File k = new File(filepath);

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath());
        values.put(MediaStore.MediaColumns.TITLE, filepath);
        values.put(MediaStore.MediaColumns.SIZE, 215454);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
        values.put(MediaStore.Audio.Media.DURATION, 230);
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
        values.put(MediaStore.Audio.Media.IS_ALARM, false);
        values.put(MediaStore.Audio.Media.IS_MUSIC, false);

        Uri uri = MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath());
        Uri newUri = this.getContentResolver().insert(uri, values);

        RingtoneManager.setActualDefaultRingtoneUri(
                getApplicationContext(),
                RingtoneManager.TYPE_RINGTONE,
                newUri
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_alarm_setting, menu);
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
