package sparespark.covid.statistics.presentation.totalreport.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sparespark.covid.statistics.presentation.components.TangoSansFont

@Composable
fun ActionButton(
    title: String,
    onItemClicked: () -> Unit
) {
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
            fontFamily = TangoSansFont

        )
    }
}