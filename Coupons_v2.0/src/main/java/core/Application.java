package core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		try (ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);) {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
