package art.example.w4tlocompse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import art.example.w4tlocompse.Screens.CoroutineA
import art.example.w4tlocompse.Screens.MainScreen
import art.example.w4tlocompse.Screens.Screens
import art.example.w4tlocompse.Screens.Service1
import art.example.w4tlocompse.Screens.Service2
import art.example.w4tlocompse.Screens.WorkMan
import art.example.w4tlocompse.ui.theme.W4TloCompseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            W4TloCompseTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.systemBars.asPaddingValues())
                ) {
                    MainNavHost()
                }
            }
        }
    }
}

@Composable
fun MainNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Thread.name ) {
        composable(route = Screens.Thread.name) {
            MainScreen(navController)
        }
        composable(route = Screens.Coroutine.name) {
            CoroutineA(navController)
        }
        composable(route = Screens.WorkMan.name) {
            WorkMan(navController)
        }
        composable(Screens.Service1.name) {
            Service1(navController = navController)
        }
        composable(Screens.Service2.name) {
            Service2(navController = navController)
        }
    }


}