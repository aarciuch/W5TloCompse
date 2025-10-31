package art.example.w4tlocompse.Screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Service1(navController: NavController) {
    Scaffold(
        topBar = { PasekGora(Modifier, Screens.Service1.name) },
        bottomBar = {
            PasekDol(navController = navController, modifier = Modifier)
        },
    ) { paddingValues -> Srodek(navController = navController,paddingValues, Screens.Service1.name) }
}