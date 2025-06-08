package unb.esi.bigdataml.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record RequestId(String value) {

    @JsonCreator
    public static RequestId from(String value) {
        return new RequestId(value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}