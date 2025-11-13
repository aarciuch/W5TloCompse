package art.example.w4tlocompse.Screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import art.example.w4tlocompse.MyService1BroadcastReceiver
import art.example.w4tlocompse.VM
import org.koin.androidx.compose.koinViewModel

@Composable
fun Srodek(paddingValues: PaddingValues, zadanie: String, vm: VM = koinViewModel() ) {

    val progress by vm.progress.collectAsState()
    val iteracje by vm.iteracje.collectAsState()
    val result by vm.result.collectAsState()
    var input by remember { mutableStateOf(10) }


    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var myService1Receiver : BroadcastReceiver? = null
    val myService1Filter = IntentFilter("MY_SERVICE1_UPDATE")

    DisposableEffect(lifecycleOwner) {

        if (zadanie == Screens.Service1.name) {
            myService1Receiver = object : BroadcastReceiver() {
                override fun onReceive(
                    context: Context?,
                    intent: Intent?
                ) {
                    intent?.let { intent ->
                        vm.updateDataFromMyService1Broadcast(intent = intent)
                        Log.i("TLO", "MyService1Receiver onReceive")
                    }
                }
            }
            //myService1Receiver = MyService1BroadcastReceiver(vm)
        }
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    if (zadanie == Screens.Service1.name) {
                        context.registerReceiver(
                            myService1Receiver, myService1Filter,
                            Context.RECEIVER_EXPORTED
                        )
                        Log.i("TLO", "Receiver registered")
                    }
                }

                Lifecycle.Event.ON_STOP -> {
                    if (zadanie == Screens.Service1.name) {
                        context.unregisterReceiver(myService1Receiver)
                        Log.i("TLO", "Receiver unregistered")
                    }
                }
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer = observer)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Progress: ${progress}")
        LinearProgressIndicator(
            progress = { (progress.toFloat() - input.toFloat())/(input+1).toFloat() },
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,

            )
        Spacer(modifier = Modifier.height(16.dp))

        Text("Iterations: $iteracje")
        LinearProgressIndicator(
            progress = { (iteracje + 1).toFloat()/(input+1).toFloat() },
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("Result: $result")
        LinearProgressIndicator(
            progress = { result.toFloat() },
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = input.toString(),
            onValueChange = { input = it.toInt()},
            label = { Text("Wprowadź tekst") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE0F7FA)),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
                when (zadanie) {
                    Screens.Thread.name -> vm.startThread(input)
                    Screens.Coroutine.name -> vm.starCoroutine(input)
                    Screens.WorkMan.name -> vm.startWorkManagerTask(input)
                    Screens.Service1.name -> vm.startMyService1(input)
                    Screens.Service2.name -> vm.startMyService2(input)
                }
            }
        ) {
            Text("Start Work")
        }
    }
}