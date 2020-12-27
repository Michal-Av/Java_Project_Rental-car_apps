package com.example.talia.android5778_5956_6419_02.models.backend;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.talia.android5778_5956_6419_02.R;

/**
 * Created by michalus.av on 23/01/2018.
 */

public class MyService extends IntentService {

    final String TAG="update service";
    IDB_Manager backend = new Factory_Method().getBackend();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *i
     */
    public MyService(String s){super(s);}
    public MyService() {
        super("update service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"OnCreate");
        Intent notificationIntent=new Intent(this,MyService.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,notificationIntent,0);
        Notification notification=
                new Notification.Builder(getBaseContext())
                        .setContentTitle("Foregroung Service!!")
                        .setContentText("Service Is Running! ")
                        .setSmallIcon(R.drawable.ic_free_cars)
                        .setContentIntent(pendingIntent).build();

        startForeground(1234,notification);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Intent intent1 = new Intent();
        intent1.setAction("INVITATION_SET");

        while (true) {
            try {
                Thread.sleep(10000);//check every 10 second
                Log.d(TAG,"thread run..");
                if (backend.checkClosedOrder())
                    sendBroadcast(intent1);
            } catch (Exception e) {
                Thread.currentThread().interrupt();

            }
        }
    }
}