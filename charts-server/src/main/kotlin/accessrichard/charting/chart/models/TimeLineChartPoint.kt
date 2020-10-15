package accessrichard.charting.chart.models

import java.time.LocalDateTime

data class TimeLineChartPoint(val dateTime: LocalDateTime,
                              val label: String,
                              val value: Int
)
