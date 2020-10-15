package accessrichard.charting.chart

import accessrichard.charting.chart.models.LabelValueChartPoint
import accessrichard.charting.chart.models.TimeLineChartPoint
import accessrichard.charting.config.models.ServerOptions

interface IChartRepository {
    fun queryTime(serverOptions: ServerOptions): List<TimeLineChartPoint>
    fun queryLabelValue(serverOptions: ServerOptions): List<LabelValueChartPoint>
}