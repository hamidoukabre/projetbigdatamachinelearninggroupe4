package unb.esi.bigdataml.Component;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import unb.esi.bigdataml.dto.ApiResponse;
import unb.esi.bigdataml.repository.ResultRepository;
import unb.esi.bigdataml.model.Result;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServingLayer {

    private ResultRepository resultRepository;

    public ServingLayer(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @KafkaListener(topics = "api-data-topic", groupId = "serving-group")
    public void stockageDonnee(ApiResponse apiResponse) {
        if (apiResponse == null || apiResponse.results() == null) return;

        List<Result> results = apiResponse.results().stream()
                .map(r->new Result(
                     r.symbol()+r.timestamp(),
                     r.symbol(),
                     r.closePrice(),
                     r.highPrice(),
                     r.lowPrice(),
                        r.numberOfTrades(),
                        r.openPrice(),
                        r.timestamp(),
                        r.volume(),
                        r.volumeWeightedAveragePrice()
                ))
                .collect(Collectors.toList());

        resultRepository.saveAll(results);
        System.out.println("Total des Données traitées et enregistrées dans elasticsearch : " + results.size());
    }

}
