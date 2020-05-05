package com.jash.catpicture;

import android.app.IntentService;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class WebLaunchService extends IntentService {
    public WebLaunchService() {
        super("WebLaunchService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Do the task here
        Log.i("Alarm", "Service running");
        String allNames = "";
        boolean first = true;
        for(String s : this.fileList()) {
            Log.i("File", "Attempting to send: " + s);
            if(!first) {
                allNames += "," + s;
            }
            else{
                allNames = s;
                first = false;
            }
        }
        Intent c = new Intent(Intent.ACTION_VIEW, Uri.parse("http://10.0.2.2:8000/?name="+allNames));
        c.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.setPackage("com.android.chrome");
        try {
            this.startActivity(c);
        } catch (ActivityNotFoundException e) {
            Log.i("Alarm", "Could not start Chrome");
        }
    }
}
