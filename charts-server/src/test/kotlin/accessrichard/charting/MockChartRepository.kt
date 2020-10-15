package accessrichard.charting

import accessrichard.charting.chart.IChartRepository
import accessrichard.charting.chart.models.LabelValueChartPoint
import accessrichard.charting.chart.models.TimeLineChartPoint
import accessrichard.charting.config.models.ServerOptions
import java.time.LocalDateTime
import java.time.Month

class MockChartRepository : IChartRepository {
    override fun queryTime(serverOptions: ServerOptions): List<TimeLineChartPoint> {
        return arrayListOf(
                TimeLineChartPoint(LocalDateTime.of(2020, Month.APRIL, 1, 1, 1, 1), "Type1", 1),
                TimeLineChartPoint(LocalDateTime.of(2020, Month.APRIL, 1, 2, 1, 1), "Type1", 2),
                TimeLineChartPoint(LocalDateTime.of(2020, Month.APRIL, 1, 3, 1, 1), "Type1", 3),
                TimeLineChartPoint(LocalDateTime.of(2020, Month.APRIL, 1, 4, 1, 1), "Type1", 4),
                TimeLineChartPoint(LocalDateTime.of(2020, Month.APRIL, 1, 5, 1, 1), "Type1", 5),
                TimeLineChartPoint(LocalDateTime.of(2020, Month.APRIL, 1, 1, 1, 1), "Type2", 6),
                TimeLineChartPoint(LocalDateTime.of(2020, Month.APRIL, 1, 2, 1, 1), "Type2", 7),
                TimeLineChartPoint(LocalDateTime.of(2020, Month.APRIL, 1, 3, 1, 1), "Type2", 8),
                TimeLineChartPoint(LocalDateTime.of(2020, Month.APRIL, 1, 9, 1, 1), "Type2", 1),
                TimeLineChartPoint(LocalDateTime.of(2020, Month.APRIL, 1, 5, 1, 1), "Type2", 3),
                TimeLineChartPoint(LocalDateTime.of(2020, Month.APRIL, 1, 11, 1, 1), "Type2", 3))
    }

    override fun queryLabelValue(serverOptions: ServerOptions): List<LabelValueChartPoint> {
        return arrayListOf(LabelValueChartPoint("label1", 1),
                LabelValueChartPoint("label1", 2),
                LabelValueChartPoint("label1", 3),
                LabelValueChartPoint("label1", 4),
                LabelValueChartPoint("label1", 5),
                LabelValueChartPoint("label1", 6),
                LabelValueChartPoint("label1", 7),
                LabelValueChartPoint("label1", 8),
                LabelValueChartPoint("label2", 1),
                LabelValueChartPoint("label2", 3),
                LabelValueChartPoint("label2", 3),
                LabelValueChartPoint("label2", 4),
                LabelValueChartPoint("label2", 5),
                LabelValueChartPoint("label2", 6))
    }
}
