package accessrichard.charting.config.models

import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
data class ChartConfigProperties(
        val name: String = "",
        val type: String = "",
        val transformer: TransformerConfig = TransformerConfig(),
        val query: String = "",
        val sourceName: String = "",
        val params: List<String> = arrayListOf(),
        val labels: List<ChartConfigLabels> = arrayListOf()
)