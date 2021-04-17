package com.example.moviepicker.domain.workers

import android.content.Context
import android.content.Intent
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.moviepicker.R
import com.example.moviepicker.domain.notification.NotificationFactory.Companion.sendNotification
import com.example.moviepicker.presentation.activity.ReviewWatchedMovieActivity

class WatchedWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    companion object {
        const val movieId = "movieId"
        const val movieTitle = "movieTitle"
    }

    private val channelId = "Your_channel_id"
    private val channelName = "My Channel"
    private val notificationId = 0

    override fun doWork(): Result {
        val reviewIntent = Intent(applicationContext, ReviewWatchedMovieActivity::class.java)
        reviewIntent.putExtra(movieId, inputData.getInt(movieId, -1))

        sendNotification(
            context,
            reviewIntent,
            channelId,
            channelName,
            notificationId,
            R.drawable.ic_clapperboard,
            context.getString(
                R.string.remember,
                inputData.getString(movieTitle)
            ),
            context.getString(R.string.add_review)
        )
        return Result.success()
    }
}