package accessrichard.charting.chart.transformers

import accessrichard.charting.chart.IChartRepository
import accessrichard.charting.chart.models.ClientChart
import accessrichard.charting.config.models.ServerOptions

interface IChartTransformer {
    fun transform(repo: IChartRepository,
                  serverOptions: ServerOptions): ClientChart
}