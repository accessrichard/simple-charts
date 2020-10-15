package accessrichard.charting.charts

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ChartsController(val chartsService: ChartsService) {
    @GetMapping("/charts")
    fun get() = chartsService.getCharts()
}

    