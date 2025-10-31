package art.example.w4tlocompse.Screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import art.example.w4tlocompse.VM
import org.koin.compose.viewmodel.koinActivityViewModel


@Composable
fun MainScreen(navController: NavController, modifier: Modifier = Modifier, vm: VM = koinActivityViewModel()) {

    Scaffold(
        topBar = { PasekGora(modifier, Screens.Thread.name) },
        bottomBar = { PasekDol(navController,modifier) },
        content = {
            paddingValues -> Srodek(paddingValues, Screens.Thread.name)
            //Zawartosc(navController, modifier.padding(it))
        }
    )
}

