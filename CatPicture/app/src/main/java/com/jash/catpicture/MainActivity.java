package com.jash.catpicture;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.net.ssl.HandshakeCompletedEvent;


public class MainActivity extends AppCompatActivity {
    //From here: https://www.briantracy.com/blog/personal-success/26-motivational-quotes-for-success/
    ArrayList<String> sayings = new ArrayList<String>(Arrays.asList(
            "The way get started is to quit talking and begin doing.",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty.",
            "Don’t let yesterday take up too much of today.",
            "You learn more from failure than from success. Don’t let it stop you. Failure builds character.",
            "It’s not whether you get knocked down, it’s whether you get up."
    ));

    private ContactBroadcastReceiver ContactReceiver;
    Handler uploader = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactReceiver = new ContactBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("com.jash.mynamejeff.CONTACT");
        if(intentFilter != null) {
            Log.i("IN", "intent filter set up");
            registerReceiver(ContactReceiver, intentFilter);
        }

        scheduleAlarm();

    }

    public void scheduleAlarm() {
        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(getApplicationContext(), WebLaunchAlarm.class);
        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, 1,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Setup periodic alarm every every half hour from this point onwards
        long firstMillis = System.currentTimeMillis(); // alarm is set right away
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                60*1000, pIntent);
    }


    @Override
    protected void onResume(){
        super.onResume();
        Random rand = new Random();
        TextView quote = findViewById(R.id.textView);
        quote.setText(sayings.get(rand.nextInt(sayings.size())));
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        cancelAlarm();
    }

    public void cancelAlarm() {
        Log.i("Alarm", "Canceling alarm.");
        Intent intent = new Intent(getApplicationContext(), WebLaunchAlarm.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, 1,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }
}

