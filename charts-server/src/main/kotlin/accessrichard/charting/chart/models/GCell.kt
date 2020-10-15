package accessrichard.charting.chart.models

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class GCell(

        // The cell value
        val v: Any,

        // Optional string version of v
        val f: String? = null,

        // Optional properties of cell, e.g. p:{style: 'border: 1px solid green;'}
        val p: Any? = null
)