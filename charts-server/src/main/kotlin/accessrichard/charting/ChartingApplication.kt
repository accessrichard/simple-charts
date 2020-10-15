package accessrichard.charting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ChartingApplication

fun main(args: Array<String>) {
    runApplication<ChartingApplication>(*args)
}
