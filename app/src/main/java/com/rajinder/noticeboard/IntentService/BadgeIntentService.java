package com.rajinder.noticeboard.IntentService;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.rajinder.noticeboard.R;

import me.leolin.shortcutbadger.ShortcutBadger;

public class BadgeIntentService extends IntentService {

    private int notificationId = 0;

    public BadgeIntentService() {
        super("BadgeIntentService");
    }

    private NotificationManager mNotificationManager;

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            int badgeCount = intent.getIntExtra("badgeCount", 0);

           /* mNotificationManager.cancel(notificationId);
            notificationId++;

            Notification.Builder builder = new Notification.Builder(getApplicationContext())
                    .setContentTitle("")
                    .setContentText("")
                    .setSmallIcon(R.drawable.svg_circle);
            Notification notification = builder.build();
            ShortcutBadger.applyCount(getApplicationContext(),30);
           mNotificationManager.notify(notificationId, notification);
*/


            Log.e("INTENT","SERvice");
        }
    }
}