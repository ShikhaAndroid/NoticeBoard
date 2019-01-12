package com.rajinder.noticeboard.Test;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONObject;



/*
 * This service is designed to run in the background and receive messages from gcm. If the app is in the foreground
 * when a message is received, it will immediately be posted. If the app is not in the foreground, the message will be saved
 * and a notification is posted to the NotificationManager.
 */
public class MessageReceivingService extends Service {
    private GoogleCloudMessaging gcm;
        private static SharedPreferences savedValues;
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

private final class ServiceHandler extends Handler {
    public ServiceHandler(Looper looper) {
        super(looper);
    }
    @Override
    public void handleMessage(final Message msg) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        String token;
        gcm = GoogleCloudMessaging.getInstance(getBaseContext());
        try {

            token = gcm.register("5445");
        }catch (Exception e){
        }
    }
//}
}
    public void onCreate() {
        super.onCreate();



        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);

        // Let AndroidMobilePushApp know we have just initialized and there may be stored messages
//        sendToApp(new Bundle(), this);
    }

    public void saveToLog(Bundle extras, Context context) {
        try {

            String message = "";
            message = extras.getString("default");
            String text = message;
            JSONObject obj=new JSONObject(message);
//            Log.e("bollywood tadka", obj.toString());
            JSONObject obj1= obj.getJSONObject("GCM");
//            Log.e("bollywood tadka", obj1.toString());
            JSONObject obj2= obj1.getJSONObject("data");

//
            String id = obj2.getString("id");
            message=obj2.getString("message");
            String type=obj2.getString("type");
            String shareurl=obj2.getString("url");
//            if (text.indexOf(')') != -1) {
//                id = message.substring(1, message.indexOf(')'));
//                message = text.substring(message.indexOf(')') + 1);
//
//            }
//            Log.d("bollywood tadka", id);54
//            Log.e("bollywood tadka", message);
            String savedcat="";//  PrefsManager.getNotificaticationSelected(context);
            String[] allnotselected = new String[0];
            if(savedcat!=null) {
                 allnotselected = savedcat.split("~");
            }

         if(checkIfnotSelected(allnotselected,type)){

         }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void saveToLog(String message, Context context) {
        try {


            JSONObject obj=new JSONObject(message);
//            Log.e("bollywood tadka", obj.toString());
            JSONObject obj1= obj.getJSONObject("GCM");
//            Log.e("bollywood tadka", obj1.toString());
            JSONObject obj2= obj1.getJSONObject("data");

//
            String id = obj2.getString("id");
            message=obj2.getString("message");
            String type=obj2.getString("type");
            String shareurl=obj2.getString("url");
//            if (text.indexOf(')') != -1) {
//                id = message.substring(1, message.indexOf(')'));
//                message = text.substring(message.indexOf(')') + 1);
//
//            }
//            Log.d("bollywood tadka", id);
//            Log.e("bollywood tadka", message);
            String savedcat="";//PrefsManager.getNotificaticationSelected(context);
            String[] allnotselected = new String[0];
            if(savedcat!=null) {
                allnotselected = savedcat.split("~");
            }

            if(checkIfnotSelected(allnotselected,type)){


            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    private Integer notification_text_color = null;
    private float notification_text_size = 11;
    private final String COLOR_SEARCH_RECURSE_TIP = "SOME_SAMPLE_TEXT";
    boolean checkIfnotSelected(String[] allnotselected, String name){
        for (int i = 0; i < allnotselected.length; i++) {
            if(allnotselected[i].equalsIgnoreCase(name)){

                return false;
            }
        }
        return true;
    }


    protected void postNotification(Intent intentAction, final Context context, String message, String id, String shareurl) {
     //   DbDataSource dataSource = new DbDataSource(context);
       // dataSource.insertContentNotification(message,id);
        final NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        int intid;
        try {
            intid = Integer.valueOf(id);
        } catch (Exception ex) {
            intid = 1;
        }
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "PunjabKesari News(punjabkesari.in) :");

        shareIntent.putExtra(Intent.EXTRA_TEXT, shareurl);
        Intent openInChooser = Intent.createChooser(shareIntent, "Share with");
        final PendingIntent shareintent = PendingIntent.getActivity(context, 253, openInChooser, PendingIntent.FLAG_UPDATE_CURRENT);
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, intid, intentAction, PendingIntent.FLAG_UPDATE_CURRENT);

// Locate and set the Image into customnotificationtext.xml ImageViews
        // http://app.punjabkesari.in/app_log.aspx?type=imgredirect&newsid=468775
        String url = "http://app.punjabkesari.in/mobilefeed.aspx?contenttype=imgredirect&newsid=" + intid;




        // Locate and set the Text into customnotificationtext.xml TextViews



        final int finalIntid = intid;
        NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(context)

                .setContentTitle("Punjab Kesari")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(Html.fromHtml(message))).setContentText(message)
                .setTicker(message).setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)

                .setColor(Color.RED)

                .setAutoCancel(true);


        if (VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationbuilder = new NotificationCompat.Builder(context)


                    .setContentTitle("Punjab Kesari")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(Html.fromHtml(message))).setContentText(message)
                    .setTicker(message).setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    .setColor(Color.RED)


                    .setAutoCancel(true);

        }

        if(shareurl.trim().length()>0){
        }

        try {
           Glide.with(context)
                    .load(url)
                    .into(100, 100)
                    .get();

          //  notificationbuilder.setLargeIcon(bitmap);
            Notification notification=notificationbuilder.build();
            mNotificationManager.notify(finalIntid, notification);
          /*  if (PrefsManager.getNotificaticationSubUnsub(context) != null) {
                if (PrefsManager.getNotificaticationSubUnsub(context).equals("sub")) {

                        if(PrefsManager.getNotificationSound(context)==0){
                            notification.sound=uri;

                        }

                        else{

                        }
                    mNotificationManager.notify(finalIntid, notification);
                } else {

                }*/
           // }
        } catch (Exception ex) {
          Notification notification=notificationbuilder.build();

/*
            if (PrefsManager.getNotificaticationSubUnsub(context) != null) {
                if (PrefsManager.getNotificaticationSubUnsub(context).equals("sub")) {
                    if(PrefsManager.getNotificationSound(context)==0){

                        notification.sound=uri;

                    }

                    else{

                    }*/
                    mNotificationManager.notify(finalIntid, notification);
              /*  } else {

                }*/
       //     }
        }
    }



  /*  private static int getNotificationIcon() {
        boolean whiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return whiteIcon ? R.drawable.silhute : R.drawable.tickericon;
    }*/

    private static Bitmap buildUpdate(String text, Context ctx) {

        Typeface clock = Typeface.createFromAsset(ctx.getAssets(), "");

        if (text.length() >= 40) {
            text = text.substring(0, 40) + "...";
        }

        Paint paint = new Paint();
        paint.setTextSize(40);
        paint.setColor(Color.WHITE);
        paint.setTypeface(clock);
        paint.setTextAlign(Paint.Align.LEFT);
        int width = (int) (paint.measureText(text) + 0.5f); // round
        float baseline = (int) (-paint.ascent() + 0.5f); // ascent() is negative
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }

    private boolean recurseGroup(ViewGroup gp) {
        final int count = gp.getChildCount();
        for (int i = 0; i < count; ++i) {
            if (gp.getChildAt(i) instanceof TextView) {
                final TextView text = (TextView) gp.getChildAt(i);
                final String szText = text.getText().toString();
                if (COLOR_SEARCH_RECURSE_TIP.equals(szText)) {
                    notification_text_color = text.getTextColors().getDefaultColor();
                    notification_text_size = text.getTextSize();
                    DisplayMetrics metrics = new DisplayMetrics();
                    WindowManager systemWM = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                    systemWM.getDefaultDisplay().getMetrics(metrics);
                    notification_text_size /= metrics.scaledDensity;
                    return true;
                }
            } else if (gp.getChildAt(i) instanceof ViewGroup)
                return recurseGroup((ViewGroup) gp.getChildAt(i));
        }
        return false;
    }

    private void extractColors() {
        if (notification_text_color != null)
            return;

        try {
            Notification ntf = new Notification();
//            ntf.setLatestEventInfo(this, COLOR_SEARCH_RECURSE_TIP, "Utest", null);
            LinearLayout group = new LinearLayout(this);
            ViewGroup event = (ViewGroup) ntf.contentView.apply(this, group);
            recurseGroup(event);
            group.removeAllViews();
        } catch (Exception e) {
            notification_text_color = android.R.color.black;
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String preferences ="dffds"; //getString(R.string.preferences);
//        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        savedValues = getSharedPreferences(preferences, Context.MODE_PRIVATE);
        // In later versions multi_process is no longer the default
        if (VERSION.SDK_INT > 9) {
            savedValues = getSharedPreferences(preferences, Context.MODE_MULTI_PROCESS);
        }
        gcm = GoogleCloudMessaging.getInstance(getBaseContext());

        SharedPreferences savedValues = PreferenceManager.getDefaultSharedPreferences(this);
        Log.e("punjabkesarib", "we are here 2");
       /* if (PrefsManager.getNotificaticationSubUnsub(MessageReceivingService.this) == null) {
            if (Connectivity.isConnected(MessageReceivingService.this)) {
                // For each start request, send a message to start a job and deliver the
                // start ID so we know which request we're stopping when we finish the job
                Message msg = mServiceHandler.obtainMessage();
                msg.arg1 = startId;
                mServiceHandler.sendMessage(msg);
            }


        }*/
        extractColors();


        // If we get killed, after returning from here, restart
        return START_NOT_STICKY;
    }
    public IBinder onBind(Intent arg0) {
        return null;
    }


}
