package art.example.w4tlocompse

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger

class MyService2Client(private val context: Context,
                       private val onResult: (Int) -> Unit,
                       private val onProgress: (Int) -> Unit,
                       private val onIteration: (Int) -> Unit) {

    private var input = 10
    private var isBound = false

    fun isBound() : Boolean = isBound

    private var serviceMessenger: Messenger? = null

    private val clientHandler = Handler(Looper.getMainLooper()) {
        when (it.what) {
            MyService2.MSG_RESULT -> {
                val result = it.data.getInt("result")
                onResult(result)
                true
            }
            MyService2.MSG_PROGRESS -> {
                val progress = it.data.getInt("progress")
                onProgress(progress)
                true
            }
            MyService2.MSG_ITERATION -> {
                val iteration = it.data.getInt("iteration")
                onIteration(iteration)
                true
            }

            else -> false
        }
    }
    private val clientMessenger = Messenger(clientHandler)
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            isBound = true
            serviceMessenger = Messenger(service)
            val message = Message.obtain(null, MyService2.MSG_START)
            val bundle = Bundle()
            bundle.putInt("input", input)
            message.data = bundle
            message.replyTo = clientMessenger
            serviceMessenger?.send(message)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    fun bind(input: Int) {
        this.input = input
        val intent = Intent(context, MyService2::class.java)
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    fun unbind() {
        if (isBound) {
            context.unbindService(connection)
            isBound = false
        }
    }

    fun sendCustomMessage(s: String) {
        val message = Message.obtain(null, MyService2.MSG_CUSTOM)
        message.data.putString("text", s)
        message.replyTo = clientMessenger
        serviceMessenger?.send(message)
    }

}