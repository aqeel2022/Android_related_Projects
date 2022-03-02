package com.example.madclass2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //get data from bundle made in MainActivity
        Bundle receiveMsgFromMainActivity = getIntent().getExtras();
        String serverMsg = receiveMsgFromMainActivity.getString("msgTag");

        TextView display_msg= (TextView) findViewById(R.id.displayServerMsg);
        display_msg.setText(serverMsg);

        Toast.makeText(MenuActivity.this,"Authentication Successful",Toast.LENGTH_LONG ).show();
    }
}