package com.example.quyhkse61160.hstsapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quyhkse61160.hstsapp.Common.Constant;

public class SetupActivity extends ActionBarActivity {

    Button btnSetup;
    EditText txtip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3ea000")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#4ABC02")));
        txtip = (EditText) findViewById(R.id.txt_ip);
        btnSetup = (Button) findViewById(R.id.setupIP);
        btnSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.hostURL = "http://" + txtip.getText().toString() + ":8080";
                Toast.makeText(SetupActivity.this,"Đổi IP Thành Công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SetupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
