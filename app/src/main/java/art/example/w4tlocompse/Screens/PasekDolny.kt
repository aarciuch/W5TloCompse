package art.example.w4tlocompse.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PasekDol(navController: NavController, modifier: Modifier) {
    Row(modifier
        .fillMaxWidth()
        .background(Color.Cyan)
        .padding(4.dp),
        Arrangement.Absolute.SpaceEvenly
    ) {
        Column(Modifier
            .padding(4.dp)
            .background(Color.Red)
            .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Thread")
            IconButton(onClick = {navController.navigate(Screens.Thread.name)}
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
            IconButton(
                onClick = {navController.navigate(Screens.Coroutine.name)}
            ) {
                Icon(Icons.Default.PlayArrow, "Coroutine")
            }
        }
        Column(Modifier
            .padding(4.dp)
            .background(Color.Gray)
            .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("WorkMan...")
            IconButton(
                onClick = {navController.navigate(Screens.WorkMan.name)}
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
            Row(horizontalArrangement = Arrangement.Absolute.SpaceEvenly) {
                IconButton(
                    onClick = { navController.navigate(Screens.Service1.name) },
                    Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.PlayArrow, "Service1")
                }
                IconButton(onClick = {}, Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Star, "Service2")
                }
            }
        }
    }
}