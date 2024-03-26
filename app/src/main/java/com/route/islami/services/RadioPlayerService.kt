package com.route.islami.services

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.islami.R
import com.route.islami.application.MyApplication

class RadioPlayerService : Service() {

    private var channelName = ""
    private var mediaPlayer = MediaPlayer()
    private var isBound = false
    private var isServiceRunning = false
    override fun onBind(intent: Intent?): IBinder {
        isBound = true
        return MyBinder()
    }

    inner class MyBinder : Binder() {

        fun getService(): RadioPlayerService {

            return this@RadioPlayerService
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        isBound = false
        return super.onUnbind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val action = intent?.getStringExtra("action") ?: ""

        when (action) {
            "play" -> {
                playPauseMediaPlayer()

                return super.onStartCommand(intent, flags, startId)
            }

            "pause" -> {
                playPauseMediaPlayer()
                if (!isBound) {
                    stopForeground(STOP_FOREGROUND_REMOVE)
                    stopSelf()
                }
                return super.onStartCommand(intent, flags, startId)
            }

            "stop" -> {
                stopMediaPlayer()
                return super.onStartCommand(intent, flags, startId)
            }
        }


        return super.onStartCommand(intent, flags, startId)
    }

    fun getIsPlaying() = mediaPlayer.isPlaying
    private fun playPauseMediaPlayer() {

        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()

        } else {
            mediaPlayer.start()
        }
        updateNotification()
    }

    fun pauseMediaPlayer() {
        mediaPlayer.pause()
        updateNotification()
    }

    private fun updateNotification() {

        val notificationView = RemoteViews(packageName, R.layout.notification_view)
        notificationView.apply {
            setTextViewText(R.id.notification_text, channelName)
            setImageViewResource(
                R.id.notification_play,
                if (mediaPlayer.isPlaying) R.drawable.ic_pause else R.drawable.ic_play
            )
            setOnClickPendingIntent(R.id.notification_play, getPlayButtonPendingIntent())
        }

        val notification = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
            .setSound(null)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSmallIcon(R.drawable.ic_quran)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationView)
            .build()

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }

    private fun stopMediaPlayer() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.reset()
        }
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    fun startMediaPlayer(urlToPlay: String, newChannelName: String, id: Int) {

        if (channelName == newChannelName) {
            playPauseMediaPlayer()
        } else {
            channelName = newChannelName
            mediaPlayer.reset()
            mediaPlayer.setDataSource(this, Uri.parse(urlToPlay))
            mediaPlayer.prepare()
            mediaPlayer.setOnPreparedListener {
                it.start()
                createNotification(channelName)
            }
        }
    }

    private fun createNotification(channelName: String) {

        val notificationView = RemoteViews(packageName, R.layout.notification_view)

        notificationView.apply {
            setTextViewText(R.id.notification_text, channelName)
            setImageViewResource(
                R.id.notification_play,
                if (mediaPlayer.isPlaying) R.drawable.ic_pause else R.drawable.ic_play
            )
            setOnClickPendingIntent(R.id.notification_play, getPlayButtonPendingIntent())
        }

        val notification = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
            .setSound(null)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSmallIcon(R.drawable.ic_quran)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationView)
            .build()
        startForeground(1, notification)

    }

    val playAction = "play"
    val pauseAction = "pause"
    val stopAction = "stop"

    private fun getPlayButtonPendingIntent(): PendingIntent? {

        val intent = Intent(this, RadioPlayerService::class.java)

        val action = if (mediaPlayer.isPlaying) pauseAction else playAction
        intent.putExtra("action", action)
        return PendingIntent.getService(this, 12, intent, PendingIntent.FLAG_IMMUTABLE)
    }

}




