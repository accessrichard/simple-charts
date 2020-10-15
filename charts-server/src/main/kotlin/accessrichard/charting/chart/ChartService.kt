package accessrichard.charting.chart

import accessrichard.charting.chart.transformers.IChartTransformer
import accessrichard.charting.chart.models.ClientChart
import accessrichard.charting.config.ChartConfigService
import accessrichard.charting.config.models.ServerOptions
import org.springframework.stereotype.Service

@Service
class ChartService constructor(val configService: ChartConfigService, val repo: ChartRepository) {
    fun getChart(chartName: String): ClientChart {
        val chart = configService.findServerOptionsByName(chartName)

        accessrichard.charting.db.set(chart.dataSource.name)

        return getData(chart)
    }

    private fun getData(serverOptions: ServerOptions): ClientChart {
        if (isSqlDb(serverOptions)) {
            val pckg = "accessrichard.charting.chart.transformers."
            val transformer =  Class.forName(pckg + serverOptions.transformer.name)?.newInstance() as  IChartTransformer
            return transformer.transform(repo, serverOptions)
        }

        throw NotImplementedError("Only jdbc connections are supported.")
    }

    private fun isSqlDb(serverOptions: ServerOptions) =
            serverOptions.dataSource.url.toLowerCase().startsWith("jdbc:")
}