package accessrichard.charting.config.models

import accessrichard.charting.chart.models.GCols

data class ServerOptions(val query: String,
                         val transformer: TransformerConfig,
                         val dataSource: DataSourceProps,
                         val params: List<Any>,
                         val chartOptions: ChartOptions,
                         val labels: List<GCols>)