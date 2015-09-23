package com.example.manhuynhkhuong.alarm_demo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends Activity {

    EditText an_sang, an_trua, an_toi, the_duc;
    Button ok;
    ToggleButton check;
    int hour, minute;

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ok = (Button) findViewById(R.id.btn_OK);
        check = (ToggleButton) findViewById(R.id.tog_check);
        an_sang = (EditText) findViewById(R.id.an_sang);
        an_trua = (EditText) findViewById(R.id.an_trua);
        an_toi = (EditText) findViewById(R.id.an_toi);
        the_duc = (EditText) findViewById(R.id.the_duc);
        an_sang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });
        an_trua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });
        an_toi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(2);
            }
        });
        the_duc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(3);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.isChecked()) {
                    AlarmManager manager = (AlarmManager) getSystemService(getApplicationContext().ALARM_SERVICE);
                    String time_an_sang = an_sang.getText().toString();
                    String time_an_trua = an_trua.getText().toString();
                    String time_an_toi = an_toi.getText().toString();
                    String time_the_duc = the_duc.getText().toString();
                    if (!time_an_sang.isEmpty()) {
                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                                MainActivity.this, 0, intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                        Calendar cal = Calendar.getInstance();
                        int hour = Integer.parseInt(time_an_sang.split(":")[0].trim());
                        int minute = Integer.parseInt(time_an_sang.split(":")[1].trim());
                        cal.setTimeInMillis(System.currentTimeMillis());
                        cal.set(Calendar.HOUR_OF_DAY, hour);
                        cal.set(Calendar.MINUTE, minute);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                    }
                    Toast.makeText(getApplication(), "Alarm is ON", Toast.LENGTH_SHORT).show();
//                    setTimer(time_an_sang,manager);
//                    setTimer(time_an_trua,manager);
//                    setTimer(time_an_toi,manager);
//                    setTimer(time_the_duc,manager);
                }
            }
        });
    }

    protected void setTimer(String field, AlarmManager manager) {

    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
                        an_sang.setText(hourOfDay + " : " + minutes);
                    }
                }, hour, minute, false);
            case 1:
                return new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
                        an_trua.setText(hourOfDay + " : " + minutes);
                    }
                }, hour, minute, false);
            case 2:
                return new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
                        an_toi.setText(hourOfDay + " : " + minutes);
                    }
                }, hour, minute, false);
            case 3:
                return new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
                        the_duc.setText(hourOfDay + " : " + minutes);
                    }
                }, hour, minute, false);

        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
