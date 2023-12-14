package cvb.com.br.composestockmarket.presentation.page_detail_stock.component

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cvb.com.br.composestockmarket.R
import cvb.com.br.composestockmarket.domain.model.StockIntraDay
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
fun CompStockChart(modifier: Modifier, infoList: List<StockIntraDay>) {

    val graphColor = colorResource(id = R.color.yellow)
    val transparentGraphColorIni = graphColor.copy(alpha = 0.65f)
    val transparentGraphColorEnd = graphColor.copy(alpha = 0.05f)

    val spacing = 100f

    val maxValue = remember(infoList) {
        (infoList.maxOfOrNull { intraDay -> intraDay.value }?.plus(1))?.roundToInt() ?: 0
    }
    val minValue = remember(infoList) {
        infoList.minOfOrNull { intraDay -> intraDay.value }?.toInt() ?: 0
    }

    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Canvas(modifier = modifier) {
        // Horizontal label´s -> Hours
        val spacePerHour = (size.width - spacing) / infoList.size

        (0 until (infoList.size - 1) step 2).forEach { idx ->
            val info = infoList[idx]
            val hour = info.dateTime.hour

            val xHourLabel = spacing + (spacePerHour * idx)
            val yHourLabel = size.height - 5
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    hour.toString(),
                    xHourLabel,
                    yHourLabel,
                    textPaint
                )
            }
        }

        // Vertical label´s -> Values
        val numberOfPoints = 4
        val spacePerValue = (maxValue - minValue) / numberOfPoints.toFloat()

        (0..< numberOfPoints).forEach { idx ->
            val value = round(minValue + (idx * spacePerValue)).toString()
            val xValueLabel = 30f
            val yValueLabel = size.height - spacing - (idx * (size.height / numberOfPoints.toFloat()))

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    value,
                    xValueLabel,
                    yValueLabel,
                    textPaint
                )
            }
        }

        var lastX = 0f

        val linePath = Path().apply {
            for (idx in infoList.indices) {
                val info = infoList[idx]
                val nextInfo = infoList.getOrNull(idx + 1) ?: infoList.last()

                val iniRatio = (info.value - minValue) / (maxValue - minValue)
                val endRatio = (nextInfo.value - minValue) / (maxValue - minValue)

                val x1 = spacing + (idx * spacePerHour)
                val y1 = (size.height - spacing - (iniRatio * size.height)).toFloat()

                val x2 = spacing + ((idx + 1) * spacePerHour)
                val y2 = (size.height - spacing - (endRatio * size.height)).toFloat()

                if (idx == 0) {
                    moveTo(x1, y1)
                }

                val controlPointX = (x1 + x2) / 2
                val controlPointY = (y1 + y2) / 2

                lastX = controlPointX

                quadraticBezierTo(x1, y1, controlPointX, controlPointY)
            }
        }

        val fillPath = android.graphics.Path(linePath.asAndroidPath()).asComposePath().apply {
            lineTo(lastX, (size.height - spacing))
            lineTo(spacing, (size.height - spacing))
            close()
        }

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(transparentGraphColorIni, transparentGraphColorEnd),
                endY = size.height - spacing
            )
        )

        drawPath(
            path = linePath,
            color = graphColor,
            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
        )
   }
}