package com.example.pushnotificationfcm.firebase

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteView
import androidx.core.app.NotificationCompat
import com.example.pushnotificationfcm.R
import com.example.pushnotificationfcm.view.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.nio.file.attribute.AclEntry.Builder


const val channelId = "notification_channel"
const val channelName = "com.example.pushnotificationfcm.firebase"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    // steps to implement FCM
    // 1- Generate the Notification
    // 2- Attach the notification created with the custom layout
    // 3- show the notification


    // jab ak app me firebase kesath server bhi use horah hoto hum token ke throught send message karte han
    //The registration token may change when:
    //- The app is restored on a new device
    //- The user uninstalls/reinstall the app
    //- The user clears app data.


    /**
     * Called if the FCM registration token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the
     * FCM registration token is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
/*
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendRegistrationToServer(token)*/
    }





    // If you want foregrounded apps to receive notification messages or data messages, for mobile power ON only:
    // you’ll need to write code to handle the onMessageReceived callback
    // onMessageReceived() must handle it
    // Full control (custom UI, actions, etc.)
    // Real-time updates, chat messages, or UI changes

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.getNotification() != null){
            generateNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)
        }
    }

    fun getRemoteView(title: String, message: String): RemoteViews{
        val remoteViews = RemoteViews("com.example.pushnotificationfcm", R.layout.notification)

        remoteViews.setTextViewText(R.id.title, title)
        remoteViews.setTextViewText(R.id.message, message)
        remoteViews.setImageViewResource(R.id.app_logo, R.drawable.ic_mello)

        return remoteViews
    }


    @SuppressLint("UnspecifiedImmutableFlag")
    fun generateNotification(title: String, message: String){

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_mello)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title, message))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0, builder.build())

    }

}