package accessrichard.charting.chart

import accessrichard.charting.chart.db.LabelValueChartExtractor
import accessrichard.charting.chart.db.TimeLineChartExtractor
import accessrichard.charting.chart.models.LabelValueChartPoint
import accessrichard.charting.chart.models.TimeLineChartPoint
import accessrichard.charting.config.models.ServerOptions
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.stereotype.Repository

@Repository
class ChartRepository constructor(val jdbcTemplate: JdbcTemplate) : IChartRepository {

    override fun queryTime(serverOptions: ServerOptions): List<TimeLineChartPoint> =
            this.query(serverOptions, TimeLineChartExtractor())


    override fun queryLabelValue(serverOptions: ServerOptions): List<LabelValueChartPoint> =
            this.query(serverOptions, LabelValueChartExtractor())

    private fun <T> query(serverOptions: ServerOptions, extractor: ResultSetExtractor<T>): T =
            jdbcTemplate.query(
                    serverOptions.query,
                    serverOptions.params.toTypedArray(),
                    extractor)!!

    private fun queryTest(): Int =
            jdbcTemplate.queryForObject("select 1 + 1", Int::class.java)!!

}
