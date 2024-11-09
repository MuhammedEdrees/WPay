package org.codeslu.wpay.ui.statistics.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.codeslu.wpay.data.model.PieChartData
import org.codeslu.wpay.data.model.PieChartEntry
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.orange1Light
import org.codeslu.wpay.ui.theme.primaryContainerLight
import org.codeslu.wpay.ui.theme.secondaryContainerLight
import org.codeslu.wpay.ui.theme.tertiaryContainerLight
import org.codeslu.wpay.ui.util.DummyValues.pieChartData

@Composable
fun CircularPieChart(
    modifier: Modifier = Modifier,
    charts: List<PieChartEntry>,
    totalValue: Double,
    size: Dp = 200.dp,
    strokeWidth: Dp = 16.dp
) {

    Canvas(
        modifier = modifier
            .size(size)
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp),
        onDraw = {
            var startAngle = 0f
            var sweepAngle: Float
            charts.forEach {
                sweepAngle = ((it.value / totalValue) * 360).toFloat()
                drawArc(
                    color = it.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
                startAngle += sweepAngle
            }
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CircularPieChartPreview() {
    WPayTheme {
        CircularPieChart(
            modifier = Modifier.padding(16.dp),
            charts = pieChartData.entries,
            totalValue = 600.0
        )
    }
}