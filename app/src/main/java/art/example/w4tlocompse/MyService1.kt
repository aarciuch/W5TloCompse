package art.example.w4tlocompse

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyService1 : Service() {

    override fun onCreate() {
        super.onCreate()
        //Log.i("TLO", "MyService1 onCreate")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, createNotification())
        val input = intent?.getIntExtra("input", 10) ?: 10

        Thread {
            var progress = input
            var result = 0
            var iteration = 0
            for (i in 0..input) {
                progress++
                iteration = i
                val broadcastIntent = Intent("MY_SERVICE1_UPDATE")
                broadcastIntent.putExtra("progress", progress)
                broadcastIntent.putExtra("iteration", iteration)
                if (i==input)  {
                    result = progress
                    broadcastIntent.putExtra("result", result)
                }
                sendBroadcast(broadcastIntent)
                Thread.sleep(500)
                Log.i("TLO", "MyService1 Thread:\ninput=${input}\nprogress=${progress}")
            }
        }.start()
        return START_NOT_STICKY
    }

    private fun createNotification(): Notification {
        val channelId = "my_service1_channel"
        val channelName = "MyService1"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_LOW,
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("MyService1")
            .setContentText("MyService1 is running...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}