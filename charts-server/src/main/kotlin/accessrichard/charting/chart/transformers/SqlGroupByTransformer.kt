package accessrichard.charting.chart.transformers

import accessrichard.charting.chart.IChartRepository
import accessrichard.charting.chart.models.*
import accessrichard.charting.config.models.ServerOptions

class SqlGroupByTransformer : IChartTransformer {

    override fun transform(repo: IChartRepository, serverOptions: ServerOptions): ClientChart {
        var labelValues = repo.queryLabelValue(serverOptions)
        val rows = labelValues.map {
            GRow(arrayListOf(GCell(it.label), GCell(it.value)))
        }

        return ClientChart(serverOptions.chartOptions, GChart(getLabels(serverOptions), rows))
    }

    private fun getLabels(serverOptions: ServerOptions): List<GCols> {
        if (serverOptions.labels.size < 2) {
            return arrayListOf(
                    GCols("Item", "Item", GColType.String),
                    GCols("Value", "Value", GColType.Number)
            )
        }

        return serverOptions.labels
    }
}
