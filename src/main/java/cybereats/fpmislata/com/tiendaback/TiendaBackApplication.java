package cybereats.fpmislata.com.tiendaback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TiendaBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaBackApplication.class, args);
	}

}
