package accessrichard.charting.charts

import accessrichard.charting.config.ChartConfigService
import org.springframework.stereotype.Service

@Service
class ChartsService(val configService: ChartConfigService) {
    fun getCharts(): List<String> = configService.getChartNames()
}