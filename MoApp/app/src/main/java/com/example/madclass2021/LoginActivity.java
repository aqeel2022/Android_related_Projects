package com.example.madclass2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    String read_email;
    EditText userEmailID;
    TextView tokenStatusID;
    ImageButton LoginButtonID;
    TextView loginInstructionID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginInstructionID= (TextView) findViewById(R.id.login_Instruction) ;
        tokenStatusID= (TextView) findViewById(R.id.Token_Status) ;
        userEmailID= (EditText) findViewById(R.id.email);
        LoginButtonID = (ImageButton) findViewById(R.id.Login);

        tokenStatusID.setVisibility(View.INVISIBLE);


        LoginButtonID.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                read_email= userEmailID.getText().toString();
               String read_email1="generate_token.php?e="+read_email;
              CTower getToken = new CTower(read_email1);
                getToken.execute();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        if(getToken.serverStatus.equals("1-FAIL")){
                            tokenStatusID.setVisibility(View.VISIBLE);
                            tokenStatusID.setText("Cannot Identify Email\n"+read_email);

                        }
                        if(getToken.serverStatus.equals("1-OK")){
                            LoginButtonID.setVisibility(View.INVISIBLE);
                            loginInstructionID.setVisibility(View.INVISIBLE);
                            userEmailID.setVisibility(View.INVISIBLE);
                            tokenStatusID.setVisibility(View.VISIBLE);
                            tokenStatusID.setText(getToken.serverMessage);
                        }

                    }
                }, 2000);
            }
        });

        Toast.makeText(LoginActivity.this,"Authentication Failed",Toast.LENGTH_LONG ).show();
    }

/*
private class CTower extends AsyncTask<String, String, String> {

    HttpURLConnection conn;
    URL url = null;
    String phpResponse;


    // This method  pass result to onPostExecute to perfrom actions
    @Override
    protected String doInBackground(String... message) {
        try {
            // Enter URL address where your php file resides
            String makeUrl = "http://mad.mywork.gr/generate_token.php?e=" + read_email;
            url = new URL(makeUrl);

        }
        catch (Exception e) {
            return e.toString();
        }
        try {

            // Setup HttpURLConnection class to send and receive data from php
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");

        }
        catch (Exception e1) {

            return e1.toString();
        }

        try {

            int response_code = conn.getResponseCode();

            // Check if successful connection made
            if (response_code == HttpURLConnection.HTTP_OK) {

                // Read data sent from server
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // Pass data to onPostExecute method

                return (result.toString());

            }
            else {

                return ("unsuccessful");
            }

        }
        catch (IOException e) {
            return e.toString();
        }
        finally {
            conn.disconnect();
        }
    }

    protected void onPostExecute(String result) {
        //  phpResponse=result;
        // phpmsg.setText(result);

        String[] separated1 = result.split("<status>");
        String[] separated2 = separated1[1].split("</status>");
        String[] separated3 = separated2[1].split("<msg>");
        String[] separated4 = separated3[1].split("</msg>");

        //String phpResp = separated2[0]+" "+separated4[0];
        if(separated2[0].equals("1-FAIL")){

            tokenStatusID.setVisibility(View.VISIBLE);
            tokenStatusID.setText("Cannot Identify Email\n"+read_email);
        }
        if(separated2[0].equals("1-OK")){

            LoginButtonID.setVisibility(View.INVISIBLE);
            loginInstructionID.setVisibility(View.INVISIBLE);
            userEmailID.setVisibility(View.INVISIBLE);
            tokenStatusID.setVisibility(View.VISIBLE);
            tokenStatusID.setText(separated4[0]);

        }

    }

    }*/
}