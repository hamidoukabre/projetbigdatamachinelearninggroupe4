package unb.esi.bigdataml.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Result(
        @JsonProperty("T")
        String symbol,

        @JsonProperty("c")
        Double closePrice,

        @JsonProperty("h")
        Double highPrice,

        @JsonProperty("l")
        Double lowPrice,

        @JsonProperty("n")
        Integer numberOfTrades,

        @JsonProperty("o")
        Double openPrice,

        @JsonProperty("t")
        Long timestamp,

        @JsonProperty("v")
        Double volume,

        @JsonProperty("vw")
        Double volumeWeightedAveragePrice
) { }
