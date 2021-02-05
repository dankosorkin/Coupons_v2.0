package core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import core.test.AdminTest;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		try (ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);) {

			AdminTest admin = ctx.getBean(AdminTest.class);
			admin.testAll();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
