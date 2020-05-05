package com.jash.catpicture;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WebLaunchAlarm extends BroadcastReceiver {

    // Triggered by the Alarm periodically (starts the service to run task)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, WebLaunchService.class);
        i.putExtra("foo", "bar");
        context.startService(i);
    }
}
