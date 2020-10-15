package accessrichard.charting.chart.models

import accessrichard.charting.config.models.ChartOptions

data class ClientChart(val options: ChartOptions,
                       val gChart: GChart)