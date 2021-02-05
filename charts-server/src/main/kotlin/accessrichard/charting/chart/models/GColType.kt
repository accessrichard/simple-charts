package accessrichard.charting.chart.models

import com.fasterxml.jackson.annotation.JsonProperty

enum class GColType {
    @JsonProperty("number")
    Number,

    @JsonProperty("date")
    Date,

    @JsonProperty("datetime")
    DateTime,

    @JsonProperty("timeofday")
    TimeOfDay,

    @JsonProperty("boolean")
    Boolean,

    @JsonProperty("string")
    String;

    override fun toString(): kotlin.String {
        return super.toString().toLowerCase();
    }
}