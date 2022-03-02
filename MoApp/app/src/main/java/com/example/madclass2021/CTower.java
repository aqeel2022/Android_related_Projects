package com.example.madclass2021;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CTower extends AsyncTask<String, String, String> {

    String Link;
  public CTower (String getLink){
this.Link=getLink;
  }
    HttpURLConnection conn;
    URL url = null;
    String phpResponse;
   public  String serverStatus=null;
   public  String serverMessage=null;

/*public CTower(Context context){

}*/



    // This method  pass result to onPostExecute to perfrom actions
    @Override
    public String doInBackground(String... message) {
        try {
            // Enter URL address where your php file resides
            url = new URL("http://mad.mywork.gr/"+Link);

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

   public void onPostExecute(String result) {
         phpResponse=result;
        // phpmsg.setText(result);
    String[] separated1 = result.split("<status>");
    String[] separated2 = separated1[1].split("</status>");
    String[] separated3 = separated2[1].split("<msg>");
    String[] separated4 = separated3[1].split("</msg>");

    serverStatus=separated2[0];
    serverMessage=separated4[0];

    }


}