package art.example.w4tlocompse.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PasekGora(modifier: Modifier, zadanie: String) {

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
                text = "Techniki\nuruchamiania\nzadań w tle\n\n${zadanie}",
                fontSize = 30.sp
            )
        }
    }
}