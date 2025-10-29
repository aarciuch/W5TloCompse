package art.example.w4tlocompse.Screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun MainScreen(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { PasekGora(modifier) },
        bottomBar = { PasekDol(navController,modifier) },
        content = {
            paddingValues -> Zawartosc(navController, modifier.padding(paddingValues))
            //Zawartosc(navController, modifier.padding(it))
        }
    )
}

@Composable
fun PasekGora(modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .border(width = 16.dp, color = Color.Red, shape = RoundedCornerShape(16.dp)),
    ) {
        Box(modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Cyan),
            Alignment.Center

        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "Techniki\nuruchamiania\nzadań w tle",
                fontSize = 30.sp
            )
        }
    }
}

@Composable
fun PasekDol(navController: NavController, modifier: Modifier) {
    Row(modifier
        .fillMaxWidth()
        .background(Color.Cyan),
        Arrangement.SpaceEvenly
    ) {
        Column(Modifier
            .padding(4.dp)
            .background(Color.Red)
            .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Thread")
            IconButton(onClick = {navController.navigate(Screens.MainScreen.name)}
            ) {
                Icon(Icons.Default.PlayArrow, "Thread")
            }
        }
        Column(Modifier
            .padding(4.dp)
            .background(Color.Green)
            .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Coroutine")
            IconButton(onClick = {}
            ) {
                Icon(Icons.Default.PlayArrow, "Coroutine")
            }
        }
        Column(Modifier
            .padding(4.dp)
            .background(Color.Blue)
            .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("WorkMan...")
            IconButton(onClick = {navController.navigate(Screens.WorkMan.name)}
            ) {
                Icon(Icons.Default.PlayArrow, "WorkManager")
            }
        }
        Column(Modifier
            .padding(4.dp)
            .background(Color.Yellow)
            .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Service")
            IconButton(onClick = {}
            ) {
                Icon(Icons.Default.PlayArrow, "Service")
            }
        }
    }
}

@Composable
fun Zawartosc(navController: NavController, modifier: Modifier) {

}