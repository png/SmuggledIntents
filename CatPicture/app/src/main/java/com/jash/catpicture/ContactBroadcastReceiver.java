package com.jash.catpicture;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class ContactBroadcastReceiver extends BroadcastReceiver {
    //Store the contact
    @Override
    public void onReceive(Context context, Intent intent) {
        // load storage
        String data = intent.getStringExtra("name");
        Log.i("BR", "Received "+intent.getStringExtra("name"));
        //Create the files
        File file = new File(context.getFilesDir(), data);
        if(!file.exists()) {
            try {
                Log.i("File", "Created file for "+data+" in "+context.getFilesDir());
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else{
            Log.i("File", data+" already exists, we got 'em!");
        }
        //for(String s : context.fileList())  Log.i("File", "Captured: "+s);
    }
}