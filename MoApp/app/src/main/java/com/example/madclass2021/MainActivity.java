package com.example.madclass2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView phpmsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //phpmsg = (TextView) findViewById(R.id.textView);
// Enter token in constructor parameter [t=your token] ( if token is unknown then enter ramdon strind it will tale you to loginActivity)
        CTower connectToServer = new CTower ("authenticate.php?t=XYZ");
        connectToServer.execute();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                if(connectToServer.serverStatus.equals("0-FAIL")){

                    Intent goToLoginActivity = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.finish();
                    startActivity(goToLoginActivity);
                }
                if(connectToServer.serverStatus.equals("0-OK")){
                    Bundle sendMsgToMenuActivity= new Bundle();
                    sendMsgToMenuActivity.putString("msgTag", connectToServer.serverMessage);
                    Intent goToMenuActivity = new Intent(MainActivity.this, MenuActivity.class);
                    goToMenuActivity.putExtras(sendMsgToMenuActivity);
                    MainActivity.this.finish();
                    startActivity(goToMenuActivity);
                }
            }
        }, 2000);




    }

}