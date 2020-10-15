package accessrichard.charting

import accessrichard.charting.chart.transformers.SqlGroupByTimeTransformer
import accessrichard.charting.chart.transformers.SqlGroupByTransformer
import accessrichard.charting.config.models.*
import org.junit.jupiter.api.Test

class SqlGroupByTransformerUnitTests {

    private val repo = MockChartRepository()

    @Test
    fun `SqlGroupByTimeTransformer maps query results to gChart`() {
        val result = SqlGroupByTimeTransformer().transform(repo, getServerOptions())
        assert(result.options.options.title == "UnitTest"
                && result.gChart.cols.size == 3
                && result.gChart.rows.size == 7
                && result.gChart.cols.first().toString() == "GCols(id=Time, label=Time, type=DateTime)"
                && result.gChart.rows.first().toString() == "GRow(c=[GCell(v=2020-04-01T01:01:01, f=2020-04-01 01, p=null), GCell(v=1, f=null, p=null), GCell(v=6, f=null, p=null)])")
    }

    @Test
    fun `SqlGroupByTransformer maps query results to gChart`() {
        val result = SqlGroupByTransformer().transform(repo, getServerOptions())
        assert(result.options.options.title == "UnitTest"
                && result.gChart.cols.size == 2
                && result.gChart.rows.size == 14
                && result.gChart.cols.first().toString() == "GCols(id=Item, label=Item, type=String)"
                && result.gChart.rows.first().toString() == "GRow(c=[GCell(v=label1, f=null, p=null), GCell(v=1, f=null, p=null)])")
    }

    private fun getServerOptions(): ServerOptions {
        return ServerOptions("",
                TransformerConfig(),
                DataSourceProps(""),
                arrayListOf(),
                ChartOptions(GChartOptions("UnitTest"), ChartType.PieChart),
                arrayListOf())
    }
}