package sparespark.covid.statistics.presentation.regionslist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sparespark.covid.statistics.R
import sparespark.covid.statistics.data.model.region.RegionsData
import sparespark.covid.statistics.presentation.components.TangoSansFont
import sparespark.covid.statistics.presentation.window.WindowSize
import sparespark.covid.statistics.presentation.window.WindowType

@Composable
fun RegionsGridList(
    regions: List<RegionsData>,
    windowSize: WindowSize,
    onItemClick: (RegionsData) -> Unit
) {
    val cellCount by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 2 else 3)
    }
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 12.sp else 24.sp)
    }

    LazyVerticalGrid(
        GridCells.Fixed(cellCount),
        content = {
            items(regions.size) { i ->
                val regionName = regions[i].name
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .aspectRatio(3f)
                        .clip(RoundedCornerShape(5.dp))
                        .background(color = colorResource(id = R.color.mid_gray))
                        .clickable {
                            onItemClick(regions[i])
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (!regionName.isNullOrEmpty()) regionName else "Unknown Region",
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        fontFamily = TangoSansFont,
                        fontSize = titleSize
                    )
                }
            }
        })
}