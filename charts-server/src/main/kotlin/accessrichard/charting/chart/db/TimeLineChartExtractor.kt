package accessrichard.charting.chart.db

import accessrichard.charting.chart.models.TimeLineChartPoint
import org.springframework.jdbc.core.ResultSetExtractor
import java.sql.ResultSet
import java.sql.SQLException

class TimeLineChartExtractor : ResultSetExtractor<List<TimeLineChartPoint>> {

    @Throws(SQLException::class)
    override fun extractData(rs: ResultSet): List<TimeLineChartPoint> {
        val points =  ArrayList<TimeLineChartPoint>()
        while (rs.next()) {
            points.add(TimeLineChartPoint(rs.getTimestamp("date").toLocalDateTime(),
                    rs.getString("label"),
                    rs.getInt("value")))
        }

        return points
    }
}