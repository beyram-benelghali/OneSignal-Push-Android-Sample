package com.example.beyram.onesignalpushsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    String playerID = "7df85b85-63cf-438d-9722-dc243be4bb50" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OneSignal.startInit(this).init();
    }

    public void clickSend(View view) throws JSONException {
        EditText et = (EditText) findViewById(R.id.editText);
        try {
            OneSignal.postNotification(new JSONObject("{'contents': {'en':'"+et.getText().toString()+"'}, 'include_player_ids': ['" + playerID + "']}"),
                    new OneSignal.PostNotificationResponseHandler() {
                        @Override
                        public void onSuccess(JSONObject response) {
                            Log.i("OneSignalExample", "postNotification Success: " + response.toString());
                        }

                        @Override
                        public void onFailure(JSONObject response) {
                            Log.e("OneSignalExample", "postNotification Failure: " + response.toString());
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendAfter(View view) {
        String afterAddingTenMins= new Date(System.currentTimeMillis()+60*1000).toString();
        Toast.makeText(this, afterAddingTenMins, Toast.LENGTH_LONG).show();
        EditText et = (EditText) findViewById(R.id.editText);
        try {
            OneSignal.postNotification(new JSONObject("{'contents': {'en':'"+et.getText().toString()+"'}, 'send_after': '"+afterAddingTenMins+"', 'include_player_ids': ['" + playerID + "']}"),
                    new OneSignal.PostNotificationResponseHandler() {
                        @Override
                        public void onSuccess(JSONObject response) {
                            Log.i("OneSignalExample", "postNotification Success: " + response.toString());
                        }

                        @Override
                        public void onFailure(JSONObject response) {
                            Log.e("OneSignalExample", "postNotification Failure: " + response.toString());
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
