package com.example.moviepicker.domain.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat

class NotificationFactory {
    companion object {
        fun sendNotification(
            context: Context,
            reviewIntent: Intent,
            channelId: String,
            channelName: String,
            notificationId: Int,
            @DrawableRes icon: Int,
            title: String,
            content: String
        ) {
            val pendingIntent = createPendingIntent(context, reviewIntent, notificationId)
            val mBuilder = createBuilder(
                context, pendingIntent, title, content, icon, channelId
            )
            val mNotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createChannel(mNotificationManager, mBuilder, channelId, channelName)
            }
            mNotificationManager.notify(notificationId, mBuilder.build())
        }

        private fun createBuilder(
            context: Context,
            pendingIntent: PendingIntent?,
            title: String,
            content: String,
            @DrawableRes icon: Int,
            channelId: String
        ): NotificationCompat.Builder {
            val mBuilder = NotificationCompat.Builder(
                context, channelId
            )

            mBuilder.setContentIntent(pendingIntent)
                .setSmallIcon(icon)
                .setContentTitle(
                    title
                )
                .setAutoCancel(true)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentText(content)
            return mBuilder
        }

        private fun createPendingIntent(
            context: Context,
            reviewIntent: Intent,
            notificationId: Int
        ): PendingIntent? {
            return PendingIntent.getActivity(
                context,
                notificationId, reviewIntent,
                notificationId
            )
        }

        private fun createChannel(
            mNotificationManager: NotificationManager,
            mBuilder: NotificationCompat.Builder,
            channelId: String,
            channelName: String
        ) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            mNotificationManager.createNotificationChannel(channel)
            mBuilder.setChannelId(channelId)
        }
    }
}