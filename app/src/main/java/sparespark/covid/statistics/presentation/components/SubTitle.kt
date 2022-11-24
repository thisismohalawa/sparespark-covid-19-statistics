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
fun SubTitle(title: String, windowSize: WindowSize) {
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 12.sp else 24.sp)
    }
    Text(
        text = title,
        color = Color.Gray,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        fontSize = titleSize,
    )
}