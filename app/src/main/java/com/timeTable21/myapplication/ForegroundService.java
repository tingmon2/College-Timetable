/**
 * @author Taekmin Jeong, Yeseom Choe
 * @Version 1.0
 * file name: ForegroundService.java
 **/
package com.timeTable21.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

//foreground requires permission in manifest file
public class ForegroundService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            // this is a logcat message which is not shown in UI
                            Log.e("Foreground Service", "Foreground Service is Running");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();

        // notification in foreground service, you can find the notification in screen hidden menu on the top
        final String Channel_Id = "Foreground Service";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(Channel_Id, Channel_Id, NotificationManager.IMPORTANCE_DEFAULT);

            getSystemService(NotificationManager.class).createNotificationChannel(channel);

            Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent1,0);

            Notification.Builder builder = new Notification.Builder(this, Channel_Id)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Service Notification")
                    .setContentText("Conestoga Timetable is Running on your Device")
                    .addAction(0,"Go to App", pendingIntent)
                    .setStyle(new Notification.BigTextStyle().bigText("Conestoga Timetable is Running on your Device"))
                    .addAction(0,"Ignore",pendingIntent);
            startForeground(1001,builder.build());
        }
        return super.onStartCommand(intent, flags, startId);
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e("Foreground Service", "Foreground Service has been Terminated");
        super.onDestroy();
    }
}
