package art.example.w4tlocompse

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyService1BroadcastReceiver(private val vm: VM): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let { intent ->
            vm.updateDataFromMyService1Broadcast(intent = intent)
        }
    }

}
