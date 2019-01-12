package com.rajinder.noticeboard.Test;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMNotificationIntentService extends IntentService {

	public static final int NOTIFICATION_ID = 1;
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;

	public GCMNotificationIntentService() {
		super("GcmIntentService");
	}

	public static final String TAG = "GCMNotificationIntentService";

	@Override
	protected void onHandleIntent(Intent intent) {
//		Util.setErrorHandler(this);

		if(intent!=null){
			Bundle extras = intent.getExtras();

            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
            String messageType = gcm.getMessageType(intent);

            // Filter messages based on message type. It is likely that GCM will be extended in the future
            // with new message types, so just ignore message types you're not interested in, or that you
            // don't recognize.
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                // It's an error.

            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                // Deleted messages on the server.
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
				MessageReceivingService service=new MessageReceivingService();
				service.saveToLog(extras, this);
				// It's a regular GCM message, do some work.

			}

//            }
        }
		ExternalReceiver.completeWakefulIntent(intent);
	}


}
