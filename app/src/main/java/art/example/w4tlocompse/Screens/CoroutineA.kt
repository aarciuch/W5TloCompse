package art.example.w4tlocompse.Screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun CoroutineA(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { PasekGora(modifier, Screens.Coroutine.name) },
        bottomBar = { PasekDol(navController,modifier) },
        content = {
                paddingValues -> Srodek(navController = navController, paddingValues, Screens.Coroutine.name)
            //Zawartosc(navController, modifier.padding(it))
        }
    )
}