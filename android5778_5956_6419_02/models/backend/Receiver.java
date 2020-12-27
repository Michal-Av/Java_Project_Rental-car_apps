package com.example.talia.android5778_5956_6419_02.models.backend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by michalus.av on 22/01/2018.
 */

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"car list updated",Toast.LENGTH_SHORT).show();
    }
}
