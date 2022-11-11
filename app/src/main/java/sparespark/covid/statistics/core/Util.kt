package sparespark.covid.statistics.core

import java.text.DecimalFormat


fun toStringDecimalFormatting(intValue: Int): String {
    return try {
        DecimalFormat("###,###,###").format(intValue)
    } catch (e: Exception) {
        "0"
    }
}