package art.example.w4tlocompse.Screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Service2(navController: NavController) {
    Scaffold(
        topBar = { PasekGora(Modifier, Screens.Service2.name) },
        bottomBar = {
            PasekDol(navController = navController, modifier = Modifier)
        },
    ) { paddingValues -> Srodek(paddingValues, Screens.Service2.name) }
}