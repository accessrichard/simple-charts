package accessrichard.charting.config

import accessrichard.charting.chart.models.GColType
import accessrichard.charting.chart.models.GCols
import accessrichard.charting.config.models.*
import accessrichard.charting.lib.enumValueOfIgnoreCase
import accessrichard.charting.lib.enumValueOfIgnoreCaseOrDefault
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File

@Service
class ChartConfigService @Autowired constructor(private val config: ChartDatSourceConfigProperties) {

    fun getChartNames(): List<String> =
            config.charts.map { it.name }

    fun findServerOptionsByName(name: String): ServerOptions {
        val serverConfig = config.charts.firstOrNull { it.name == name }
                ?: throw Exception("Required configuration missing: name: $name")

        val dataSource = config.sources.firstOrNull { it.name == serverConfig.sourceName }
                ?: throw Exception("Required configuration missing: dataSource: $name}")

        val query = getQuery(serverConfig.query)
        val clientOptions = ChartOptions(
                GChartOptions(serverConfig.name),
                enumValueOfIgnoreCase(serverConfig.type))

        return ServerOptions(query,
                serverConfig.transformer,
                dataSource,
                serverConfig.params,
                clientOptions,
                getLabels(serverConfig.labels))
    }

    private fun getQuery(query: String): String {
        if (!query.startsWith("file:")) {
            return query
        }

        val fileName = query.replace("file://", "")
        return File(fileName).readText()
    }

    private fun getLabels(labels: List<ChartConfigLabels>): List<GCols> {
        if (labels.isNullOrEmpty()) {
            return arrayListOf()
        }

        return labels.map {
            GCols(if (it.id.isNullOrBlank()) it.label else it.id,
                    it.label,
                    enumValueOfIgnoreCaseOrDefault(it.type, GColType.String)
            )
        }
    }
}