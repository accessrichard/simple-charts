package accessrichard.charting

import accessrichard.charting.config.ChartConfigService
import accessrichard.charting.config.models.*
import org.junit.jupiter.api.Test

class ConfigUnitTests {

    @Test
    fun `Query is mapped to Server Options`() =
            assert(this.getServerOptionsByName().query == "select * from person")

    @Test
    fun `Query Parameters are mapped to Server Options`() =
            assert(this.getServerOptionsByName().params.first() == "param1")

    @Test
    fun `DSN is mapped to Server Options`() =
            assert(this.getServerOptionsByName().dataSource.driverClassName == "driverClass1")

    @Test
    fun `Labels are mapped to Client Options`() =
            assert(ChartConfigService(getData()).getChartNames().first() == "test1")

    private fun getServerOptionsByName() =
            ChartConfigService(getData()).findServerOptionsByName("test1")

    private fun getData(): ChartDatSourceConfigProperties {
        val charts = arrayListOf(ChartConfigProperties(
                "test1",
                "Bar",
                TransformerConfig(),
                "select * from person",
                "dsn1",
                arrayListOf("param1", "param2"),
                arrayListOf(ChartConfigLabels("Test1"), ChartConfigLabels("Test2"))
        ))

        val sources = arrayListOf(DataSourceProps(
                "dsn1",
                "",
                "driverClass1",
                "myDriver",
                "myUrl"
        ))

        return ChartDatSourceConfigProperties(charts, sources)
    }
}