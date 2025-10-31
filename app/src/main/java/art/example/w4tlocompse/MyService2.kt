package art.example.w4tlocompse

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.util.Log

class MyService2 : Service() {

    companion object {
        const val MSG_START = 1
        const val MSG_RESULT = 2
        const val MSG_PROGRESS = 3
        const val MSG_ITERATION = 4
        const val MSG_CUSTOM = 5
    }

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_START -> {
                    val input = msg.data.getInt("input", 10)
                    val client = msg.replyTo // Messenger klienta

                    Thread {
                        var progress = input
                        var result = 0
                        for (i in 0..input) {
                            progress++

                            // Wysyłanie postępu
                            val progressMsg = Message.obtain(null, MSG_PROGRESS)
                            val progressBundle = Bundle()
                            progressBundle.putInt("progress", progress)
                            progressMsg.data = progressBundle
                            client.send(progressMsg)

                            // Wysyłanie aktualnej iteracji
                            val iterMsg = Message.obtain(null, MSG_ITERATION)
                            val iterBundle = Bundle()
                            iterBundle.putInt("iteration", i)
                            iterMsg.data = iterBundle
                            client.send(iterMsg)

                            if (i == input) {
                                result = progress
                                val reply = Message.obtain(null, MSG_RESULT)
                                val bundle = Bundle()
                                bundle.putInt("result", result)
                                reply.data = bundle
                                client.send(reply)
                            }
                            Thread.sleep(500)
                        }
                    }.start()
                }
                MSG_CUSTOM -> {
                    val tresc = msg.data.getString("text", "nic")
                    val client = msg.replyTo // Messenger klienta
                    Log.i("TLO", "MyService2: ${MSG_CUSTOM}\nTreść: ${tresc}")
                }
                else -> Unit
            }
        }
    }
    private val messenger = Messenger(handler)


    override fun onBind(intent: Intent?): IBinder {
        Log.i("TLO", "Service2 is bounded")
        return messenger.binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("TLO", "Service2 is unbounded")
        return super.onUnbind(intent)
    }
}