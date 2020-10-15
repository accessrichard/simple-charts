package accessrichard.charting.chart.transformers

import accessrichard.charting.chart.IChartRepository
import accessrichard.charting.chart.models.*
import accessrichard.charting.config.models.ServerOptions
import accessrichard.charting.config.models.TransformerConfig
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.max

class SqlGroupByTimeTransformer : IChartTransformer {

    override fun transform(repo: IChartRepository, serverOptions: ServerOptions): ClientChart {
        var points = repo.queryTime(serverOptions)
        val formatter = getFormat(serverOptions.transformer)
        val chartDates = points.map { it.dateTime }.distinct()
        val chartLabels = points.map { it.label }.distinct()
        val dataTable = chartDates.map { it to Array(chartLabels.size) { GCell(0) } }.toMap()

        for (row in dataTable) {
            val chartPointsForLabel = points.filter { it.dateTime == row.key }

            for (chartPointForLabel in chartPointsForLabel) {
                val index = max(chartLabels.indexOf(chartPointForLabel.label), 0)
                dataTable[chartPointForLabel.dateTime]?.set(index, GCell(chartPointForLabel.value))
            }
        }

        val labels = getLabels(serverOptions, chartLabels)
        val data = dataTable.map {
            GRow(arrayListOf(GCell(it.key, this.getDisplayValue(it.key, formatter))) + it.value.toList())
        }

        return ClientChart(serverOptions.chartOptions, GChart(labels, data))
    }

    private fun getDisplayValue(dateTime: LocalDateTime, formatter: DateTimeFormatter?): String? {
        if (formatter == null) {
            return null
        }

        return dateTime.format(formatter)
    }

    private fun getFormat(config: TransformerConfig): DateTimeFormatter? {
        val pattern = config.pattern ?: "yyyy-MM-dd HH"
        return DateTimeFormatter.ofPattern(pattern)
    }

    private fun getLabels(serverOptions: ServerOptions, dataLabels: List<String>): List<GCols> {
        if (serverOptions.labels.size > 1) {
            return serverOptions.labels
        }

        val labelsFromData = dataLabels.map { GCols(it, it, GColType.Number) }
        return listOf(serverOptions.labels.firstOrNull()
                ?: GCols("Time", "Time", GColType.DateTime)) + labelsFromData
    }
}