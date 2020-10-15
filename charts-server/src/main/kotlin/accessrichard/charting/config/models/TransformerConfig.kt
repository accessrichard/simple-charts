package accessrichard.charting.config.models

data class TransformerConfig(val name: String = "LabelValue",
                             val formatter: String? = null,
                             val pattern: String? = null
)