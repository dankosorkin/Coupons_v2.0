package core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import core.entities.Company;
import core.services.AdminService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		try (ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);) {

			AdminService adminService = ctx.getBean(AdminService.class);
			adminService.addCompany(new Company("AAA", "aaa@mail", "pass1234"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
