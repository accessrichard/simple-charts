package accessrichard.charting.chart.db

import accessrichard.charting.chart.models.LabelValueChartPoint
import org.springframework.jdbc.core.ResultSetExtractor
import java.sql.ResultSet
import java.sql.SQLException

class LabelValueChartExtractor : ResultSetExtractor<List<LabelValueChartPoint>> {

    @Throws(SQLException::class)
    override fun extractData(rs: ResultSet): List<LabelValueChartPoint> {
        val points = ArrayList<LabelValueChartPoint>()
        while (rs.next()) {
            points.add(LabelValueChartPoint(
                    rs.getString("label"),
                    rs.getInt("value")))
        }

        return points
    }
}