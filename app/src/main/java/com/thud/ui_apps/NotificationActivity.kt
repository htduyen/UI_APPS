package com.thud.ui_apps

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.activity_notification.*
import kotlin.random.Random

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        btn_showNotification.setOnClickListener {
            showNoification()
        }
    }

    fun notificationImage():AsyncTask<String, Void, Bitmap> {


    }

    fun showNoification(){
        var notificationId = Random.nextInt(100)
        var channelId = "notification_chanel_1"
        var notificationManager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        var intent = Intent(applicationContext, SeconNotifyActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        var pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        var builder: NotificationCompat.Builder = NotificationCompat.Builder(
            applicationContext,
            channelId
        )
        builder.setSmallIcon(R.drawable.ic_notifications)
        builder.setDefaults(NotificationCompat.DEFAULT_ALL)
        builder.setContentTitle("New notification")
        builder.setContentText("This is content for notify")

        builder.setStyle(NotificationCompat.BigTextStyle().bigText("Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content"))


        builder.setContentIntent(pendingIntent)
        builder.setAutoCancel(true)
        builder.setPriority(NotificationCompat.PRIORITY_MAX)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O  ){
            if(notificationManager != null && notificationManager.getNotificationChannel(channelId) == null){
                var notificationChannel: NotificationChannel = NotificationChannel(
                    channelId,
                    "Notification channel 1",
                    NotificationManager.IMPORTANCE_HIGH
                )
                notificationChannel.description = "This notification channer is used to notify user"
                notificationChannel.enableVibration(true)
                notificationChannel.enableLights(true)
                notificationManager.createNotificationChannel(notificationChannel)
            }
        }

        var notification :Notification = builder.build()
        if(notificationManager != null){
            notificationManager.notify(notificationId, notification)
        }
    }
}
