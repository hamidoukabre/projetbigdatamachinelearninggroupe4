package unb.esi.bigdataml.model;


import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "results")
@Data
@Builder
public class Result {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String symbol;

    @Field(type = FieldType.Double)
    private Double closePrice;

    @Field(type = FieldType.Double)
    private Double highPrice;

    @Field(type = FieldType.Double)
    private Double lowPrice;

    @Field(type = FieldType.Integer)
    private Integer numberOfTrades;

    @Field(type = FieldType.Double)
    private Double openPrice;

    @Field(type = FieldType.Long)
    private Long timestamp;

    @Field(type = FieldType.Double)
    private Double volume;

    @Field(type = FieldType.Double)
    private Double volumeWeightedAveragePrice;

public Result(){}

    public Result(String id, String symbol, Double closePrice,
                  Double highPrice, Double lowPrice, Integer numberOfTrades,
                  Double openPrice, Long timestamp, Double volume,
                  Double volumeWeightedAveragePrice) {
        this.id = id;
        this.symbol = symbol;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.numberOfTrades = numberOfTrades;
        this.openPrice = openPrice;
        this.timestamp = timestamp;
        this.volume = volume;
        this.volumeWeightedAveragePrice = volumeWeightedAveragePrice;
    }
}
