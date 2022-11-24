package sparespark.covid.statistics.presentation.totalreport.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sparespark.covid.statistics.presentation.components.TangoSansFont
import sparespark.covid.statistics.presentation.window.WindowSize
import sparespark.covid.statistics.presentation.window.WindowType

@Composable
fun ActionButton(
    title: String,
    windowSize: WindowSize,
    onItemClicked: () -> Unit,
) {

    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 15.sp else 25.sp)
    }

    TextButton(
        onClick = {
            onItemClicked()
        },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.Black
        ),
        shape = RoundedCornerShape(8.dp),

        elevation = ButtonDefaults.elevation(8.dp)
    ) {
        Text(
            text = title, color = Color.LightGray,
            modifier = Modifier.padding(2.dp),
            fontFamily = TangoSansFont,
            fontSize = titleSize
        )
    }
}