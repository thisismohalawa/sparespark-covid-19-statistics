package sparespark.covid.statistics.presentation.provinceslist.components

import androidx.compose.foundation.ExperimentalFoundationApi
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
import sparespark.covid.statistics.data.model.province.ProvincesData
import sparespark.covid.statistics.presentation.components.TangoSansFont
import sparespark.covid.statistics.presentation.window.WindowSize
import sparespark.covid.statistics.presentation.window.WindowType

@ExperimentalFoundationApi
@Composable
fun ProvincesGridList(
    provinces: List<ProvincesData>,
    windowSize: WindowSize,
    onItemClick: (ProvincesData) -> Unit
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
            items(provinces.size) { i ->
                val provName = provinces[i].province
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .aspectRatio(3f)
                        .clip(RoundedCornerShape(5.dp))
                        .background(color = colorResource(id = R.color.mid_gray))
                        .clickable {
                            onItemClick(provinces[i])
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (!provName.isNullOrEmpty()) provName else "Unknown Province",
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        fontSize = titleSize,
                        fontFamily = TangoSansFont
                    )
                }
            }

        })
}