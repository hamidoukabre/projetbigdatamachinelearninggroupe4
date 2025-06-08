package unb.esi.bigdataml.Component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import unb.esi.bigdataml.dto.ApiResponse;
import unb.esi.bigdataml.service.CollecteService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class StreamLayer
 {


    private final CollecteService collecteService;
    private final KafkaTemplate<String, ApiResponse> kafkaTemplate;
    private final String topic = "api-data-topic";
    private LocalDate dateDebut;



    public StreamLayer(CollecteService collecteService,
            KafkaTemplate<String, ApiResponse> kafkaTemplate,
            @Value("${collecte.dateDebut}") String dateDebutString
    ) {
        this.collecteService = collecteService;
        this.kafkaTemplate = kafkaTemplate;


        this.dateDebut = LocalDate.parse(dateDebutString);

    }

    @Scheduled(fixedDelay = 12000)
    public void recuperation() {

        if(this.dateDebut.isBefore(LocalDate.now().plusDays(1)))
        {

            ApiResponse response = collecteService.collecte(dateDebut);
            if (response != null) {
                kafkaTemplate.send(topic, response);
                System.out.println("les Données du " +this.dateDebut.toString() +" sont envoyées à Kafka ! ");
                System.out.println("les Données sont au nombres " +response.queryCount());
                this.dateDebut = this.dateDebut.plusDays(1);
            } else {
                System.out.println("⚠️ Aucune donnée reçue.");

                this.dateDebut = this.dateDebut.plusDays(1);
            }

        }


    }


}
