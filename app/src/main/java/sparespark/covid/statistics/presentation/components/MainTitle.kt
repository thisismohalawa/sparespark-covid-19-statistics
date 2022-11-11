package sparespark.covid.statistics.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainTitle(title: String) {
    Text(
        text = title,
        color = Color.Black,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp, 10.dp, 10.dp, 20.dp)
            .padding(vertical = 5.dp),
        fontSize = 20.sp,
        fontFamily = TangoSansFont
    )
}