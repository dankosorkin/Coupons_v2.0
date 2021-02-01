package core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import core.services.AdminService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		try (ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);) {

			AdminService adminService = ctx.getBean(AdminService.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
