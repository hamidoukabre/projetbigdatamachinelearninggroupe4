package unb.esi.bigdataml.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse(
        boolean adjusted,
        int queryCount,
        @JsonProperty("request_id")
        String requestId,
        @JsonProperty("results")
        List<Result> results,
        int resultsCount,
        String status
) {
}
