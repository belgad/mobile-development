package com.example.lab4

import android.app.*
import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Task11 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task11_activity)

        findViewById<Button>(R.id.task11_create).setOnClickListener {
            startService(Intent(this,
                NotificationService::class.java).apply {action = "start"})}
        findViewById<Button>(R.id.task11_delete).setOnClickListener {
            startService(Intent(this,
                NotificationService::class.java).apply {action = "delete"})}
    }
}

class NotificationService : Service() {
    private var counter: Int = 0
    private var notification_builder: NotificationCompat.Builder? = null
    private lateinit var notification_manager: NotificationManagerCompat

    override fun onBind(p0: Intent?): IBinder {
        return Binder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "start" -> {
                val add_intent = Intent(this,
                    NotificationService::class.java).apply {action = "add"}
                val zero_intent = Intent(this,
                    NotificationService::class.java).apply {action = "zero"}

                notification_manager = NotificationManagerCompat.from(this)
                notification_builder = NotificationCompat.Builder(this, "Task11Notification")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentText(counter.toString())
                        .addAction(0, "+1", PendingIntent.getService(
                            this, 0, add_intent, PendingIntent.FLAG_IMMUTABLE))
                        .addAction(0, "Clear", PendingIntent.getService(
                            this, 0, zero_intent, PendingIntent.FLAG_IMMUTABLE))

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    val notification_channel = NotificationChannel(
                        "Task11NotificationChannel",
                        "Task11NotificationChannel",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                    notification_manager.createNotificationChannel(notification_channel)
                    notification_builder?.setChannelId("Task11NotificationChannel")
                }

                notification_builder!!.build().let {
                    it.flags = Notification.FLAG_ONGOING_EVENT
                    notification_manager.notify("Task11Notification".hashCode(), it)
                }
            }
            "add" -> {
                counter++
                notification_builder!!.setContentText(counter.toString())
                notification_builder!!.build().let {
                    it.flags = Notification.FLAG_ONGOING_EVENT
                    notification_manager.notify("Task11Notification".hashCode(), it)
                }
            }
            "zero" -> {
                counter = 0
                notification_builder!!.setContentText("0")
                notification_builder!!.build().let {
                    it.flags = Notification.FLAG_ONGOING_EVENT
                    notification_manager.notify("Task11Notification".hashCode(), it)
                }
            }
            "delete" -> {
                notification_manager.cancel("Task11Notification".hashCode())
                notification_builder = null
                counter = 0
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
}