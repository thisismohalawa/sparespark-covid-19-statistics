package sparespark.covid.statistics.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sparespark.covid.statistics.presentation.window.WindowSize
import sparespark.covid.statistics.presentation.window.WindowType

@Composable
fun MainTitle(title: String, windowSize: WindowSize) {
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 20.sp else 30.sp)
    }
    Text(
        text = title,
        color = Color.Black,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp, 10.dp, 10.dp, 20.dp)
            .padding(vertical = 5.dp),
        fontSize = titleSize,
        fontFamily = TangoSansFont
    )
}