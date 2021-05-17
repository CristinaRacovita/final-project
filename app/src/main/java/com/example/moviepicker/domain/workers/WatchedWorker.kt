package com.example.moviepicker.domain.workers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.moviepicker.R
import com.example.moviepicker.domain.notification.NotificationFactory.Companion.sendNotification
import com.example.moviepicker.presentation.activity.ReviewWatchedMovieActivity
import java.util.concurrent.TimeUnit

class WatchedWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    companion object {
        const val movieId = "myMovieId"
        const val movieTitle = "myMovieTitle"

        fun startWorker(title: String, id: Int, workManager: WorkManager) {
            val data: Data = workDataOf(
                movieTitle to title,
                movieId to id
            )

            val watchedPeriodicRequest =
                OneTimeWorkRequest.Builder(WatchedWorker::class.java)
                    .setInitialDelay(10, TimeUnit.SECONDS)
                    .setInputData(data)
                    .build()

            workManager.enqueue(watchedPeriodicRequest)
        }
    }

    private val channelId = "Your_channel_id"
    private val channelName = "My Channel"
    private val notificationId = 0

    override fun doWork(): Result {
        val reviewIntent = Intent(context, ReviewWatchedMovieActivity::class.java)

        val sharedPreferences =
            context.getSharedPreferences(
                context.getString(R.string.preference_file_key),
                AppCompatActivity.MODE_PRIVATE
            )
        sharedPreferences.edit().putInt(movieId, inputData.getInt(movieId, -1)).apply()

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