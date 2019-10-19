package moonage.date.marlin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MarlinApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarlinApplication.class, args);
	}

}
