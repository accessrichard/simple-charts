package accessrichard.charting.chart

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ChartController(val chartService: ChartService) {
    @GetMapping("/chart/{name}")
    fun get(@PathVariable name: String) =
            chartService.getChart(name)
}