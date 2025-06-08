package unb.esi.bigdataml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BigdatamlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BigdatamlApplication.class, args);
	}

}
