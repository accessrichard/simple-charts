package accessrichard.charting.config.models

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component

@ConstructorBinding
@ConfigurationProperties("charting")
data class ChartDatSourceConfigProperties(
        val charts:List<ChartConfigProperties>,
        val sources: List<DataSourceProps>
)