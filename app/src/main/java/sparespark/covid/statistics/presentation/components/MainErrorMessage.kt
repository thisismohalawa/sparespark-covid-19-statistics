package sparespark.covid.statistics.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sparespark.covid.statistics.R
import sparespark.covid.statistics.presentation.window.WindowSize
import sparespark.covid.statistics.presentation.window.WindowType

val TangoSansFont = FontFamily(
    Font(R.font.tango_sans1, FontWeight.Normal)
)

@Composable
fun MainErrorMessage(msg: String, windowSize: WindowSize) {
    /*

    :(
    Error Message

    * */
    val subTitleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 15.sp else 25.sp)
    }
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 70.sp else 80.sp)
    }
    val showIcon by remember(key1 = windowSize) {
        mutableStateOf(windowSize.width == WindowType.Expanded)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = titleSize)) {
                    append(":(")
                }
                append("\n\n${msg}")
            },
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .align(Alignment.CenterStart),
            fontSize = subTitleSize,
            fontFamily = TangoSansFont
        )
    }
}